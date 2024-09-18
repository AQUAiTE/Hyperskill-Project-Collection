package carsharing;

import org.h2.tools.DeleteDbFiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

// H2 Connection Code Pulled From: https://www.tutorialspoint.com/h2_database/h2_database_jdbc_connection.htm
// Console logs removed and comments removed (replaced with my own)
public class DBManager {
    private static final String JDBC_DRIVER = "org.h2.Driver";
    private String DB_URL = "jdbc:h2:./src/carsharing/db/";
    Statement stmt;
    Connection conn;

    public DBManager(String[] args) {
        String dbName = args.length > 0 ? args[1] : "carsharing";

        try {
            Class.forName(JDBC_DRIVER);
            this.DB_URL += dbName;

            // Start connection and remove existing files then replace with empty table
            this.conn = DriverManager.getConnection(DB_URL);
            DeleteDbFiles.execute("./src/carsharing/db", "carsharing", true);
            this.conn.setAutoCommit(true);

            this.stmt = this.conn.createStatement();
            this.stmt.execute("DROP TABLE IF EXISTS CAR");
            this.stmt.execute("DROP TABLE IF EXISTS COMPANY");
            this.stmt.executeUpdate(Queries.CREATE_COMPANY_TABLE);
            this.stmt.executeUpdate(Queries.CREATE_CAR_TABLE);
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(stmt!=null) stmt.close();
            } catch(SQLException se2) {
            }

            try {
                if(conn!=null) conn.close();
            } catch(SQLException se){
                se.printStackTrace();
            }

        }

    }

    public String getDbURL() {
        return this.DB_URL;
    }
    
    public void cleanup() throws SQLException {
        this.conn.close();
        this.stmt.close();
    }
}
