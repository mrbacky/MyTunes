package mytunes.dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The ConnectDAO class is used to establish a connection to the database.
 *
 * @author Michael Haaning Pedersen
 */
public class ConnectDAO {

    private static final String PROP_FILE = "data/DBProperties.properties";
    private SQLServerDataSource ds;

    /**
     * Gets a connection to the database using a property file to fill in the
     * parameters.
     */
    public ConnectDAO() {
        try {
            Properties databaseProperties = new Properties();
            databaseProperties.load(new FileInputStream(PROP_FILE));
            ds = new SQLServerDataSource();
            ds.setServerName(databaseProperties.getProperty("Server"));
            ds.setDatabaseName(databaseProperties.getProperty("Database"));
            ds.setUser(databaseProperties.getProperty("User"));
            ds.setPassword(databaseProperties.getProperty("Password"));
            ds.setPortNumber(Integer.parseInt(databaseProperties.getProperty("PortNumber")));
        } catch (IOException e) {
            System.out.println("can find proppeti file");
        }
    }

    /**
     * Gets the connection and returns it.
     *
     * @return
     */
    public Connection getConnection() {
        try {
            return ds.getConnection();
        } catch (SQLServerException ex) {
            Logger.getLogger(ConnectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
