package Config.Prod;

/**
 * Production database configuration
 */
public interface Database {

    String DRIVER       = "com.mysql.jdbc.Driver";
    String TYPE         = "jdbc:mysql://";
    String HOST         = "localhost";
    String DATABASE     = "project";
    String USER         = "root";
    String PASSWORD     = "ROOT";
}

