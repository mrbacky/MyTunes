/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @this dao class is doing the connection to our data base
 */
public class ConnectDAO {

    private static final String PROP_FILE = "data/DBProperties.properties";
    private SQLServerDataSource ds;

    /**
    *
    * @ this connectDAO is getting a connection to our database and use a proppti file to fill in the parameters.
    * @parameter
    * sever name
    * database name
    * user name
    * password
    * protnumber
    * trow a catch ioexceptions
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
            //To DO
        }
    }
    
    /**
    *
    * @this connection get the connection and return it in to the system.
    * catch a sqlseverexception and return nulll.
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
