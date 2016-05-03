package App;

import Views.Dashboard;
import Views.StartWindow;
import org.javalite.activejdbc.Base;
import static Config.Application.*;
import static Config.Dev.Database.*;


public class Application implements Runnable {

    public StartWindow mainWindow;

    /**
     * Application instance
     */
    public Application(StartWindow win) {
        mainWindow = win;
        Base.open(DRIVER, TYPE + HOST + "/" +  DATABASE, USER, PASSWORD);
    }

    /**
     * Create dashboard view after successful login
     */
    public void run() {
        try {
            Dashboard dashboardView = new Dashboard();
            dashboardView.setAppHandler(this);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void handleLogout() {
        Base.close();
    }

    /**
     * Finalize application, close database connection and free memory
     */
    public void finalize() {
        try {
            Base.close();
            super.finalize();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @return Boolean
     */
    public static Boolean isDevelopment() {
        return ENVIRONMENT.equals(DEV);
    }

    /**
     *
     * @return Boolean
     */
    public static Boolean isProduction() {
        return ENVIRONMENT.equals(PROD);
    }

    /**
     *
     * @return Boolean
     */
    public static Boolean isTesting() {
        return ENVIRONMENT.equals(TESTING);
    }

}
