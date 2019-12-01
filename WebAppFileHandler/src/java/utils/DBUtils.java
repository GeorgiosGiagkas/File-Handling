package utils;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author giagkas
 */
public class DBUtils {

    public static Connection getConnection() {
        Connection conn = null;

        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/file_handling_app");
            conn = ds.getConnection();

        } catch (NamingException ex) {
            ex.getStackTrace();
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return conn;
    }
}
