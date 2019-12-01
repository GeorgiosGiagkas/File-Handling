package dao;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.FileClass;
import utils.DBUtils;

/**
 *
 * @author giagkas
 */
public class FileClassDAO {
    
    public void insertFile(FileClass file) {
        Connection con = DBUtils.getConnection();
        String insertQuery = "insert into archive values(null, ?, ?)";
        PreparedStatement ps = null;        

        try {
            ps = con.prepareStatement(insertQuery);
            ps.setString(1, file.getFileName());
            ps.setBinaryStream(2, new ByteArrayInputStream(file.getArchive()));
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FileClassDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(FileClassDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(FileClassDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public FileClass getFileById(int id) {
        FileClass file = new FileClass();
        Connection con = DBUtils.getConnection();
        String query = "select * from archive where file_id = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                file.setFileId(rs.getInt("file_id"));
                file.setFileName(rs.getString("file_name"));
                file.setArchive(rs.getBytes("file_archive"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FileClassDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(FileClassDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(FileClassDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(FileClassDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return file;
    }

    public void deleteFileById(int id) {
        Connection con = DBUtils.getConnection();
        String query = "delete from archive where file_id = ?";
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FileClassDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(FileClassDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if(con!=null){
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(FileClassDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    public List<FileClass> getAllFiles() {
        List<FileClass> files = new ArrayList<>();
        Connection con = DBUtils.getConnection();
        PreparedStatement ps=null;
        ResultSet rs = null;
        
        String query = "select * from archive";
        
        try {
            ps= con.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()){
                FileClass file = new FileClass();
                file.setFileId(rs.getInt("file_id"));
                file.setFileName(rs.getString("file_name"));
                file.setArchive(rs.getBytes("file_archive"));
                files.add(file);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(FileClassDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(FileClassDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(FileClassDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(FileClassDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        
        return files;
    }
}
