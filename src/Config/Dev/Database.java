package Config.Dev;

/**
 * Development database configuration
 */
public interface Database {

    String DRIVER       = "com.mysql.jdbc.Driver";
    String TYPE         = "jdbc:mysql://";
    String HOST         = "localhost";
    String DATABASE     = "java_project";
    String USER         = "java_project";
    String PASSWORD     = "java_project";
}