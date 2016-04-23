package Config;

public interface Application {

    /**
     * Sets Environment variables
     */
    String DEV      = "development";
    String PROD     = "production";
    String TESTING  = "testing";

    /**
     * Define Application Environment
     */
    String ENVIRONMENT = DEV;
}
