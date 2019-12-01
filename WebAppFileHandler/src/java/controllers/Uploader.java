package controllers;

import dao.FileClassDAO;
import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.FileClass;
import utils.FileStreamer;

/**
 *
 * @author giagkas
 */
public class Uploader extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        Part part = request.getPart("file");

        String fileName = part.getSubmittedFileName();
        byte[] archive = FileStreamer.readInputStreamToBytes(part.getInputStream());

        FileClass file = new FileClass();
        file.setFileName(fileName);
        file.setArchive(archive);

        //insert to database
        FileClassDAO dao = new FileClassDAO();
        dao.insertFile(file);

        //write file to user desktop        
        try {
            String home = System.getProperty("user.home");
            part.write(home + File.separator + "Desktop" + File.separator + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        response.sendRedirect("Initial");

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
