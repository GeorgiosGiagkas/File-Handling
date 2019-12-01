package controllers;

import dao.FileClassDAO;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.FileClass;
import utils.FileStreamer;

/**
 *
 * @author giagkas
 */
public class Downloader extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        int fileId =Integer.parseInt(request.getParameter("file-id"));
        
        FileClassDAO dao = new FileClassDAO();
        FileClass file = dao.getFileById(fileId);
        
        
        ServletContext context = getServletContext();
        String mimeType = context.getMimeType(file.getFileName());
        if(mimeType==null){
            mimeType = "application/octet-type";
        }
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", file.getFileName());
        response.setHeader(headerKey, headerValue);
        response.setContentType(mimeType);
        
        OutputStream outputStream=response.getOutputStream();
        InputStream inputStream = new ByteArrayInputStream(file.getArchive());
               
        FileStreamer.inputStreamToOutputStream(inputStream,outputStream);
        
        
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
