package Views;

import App.Application;
import Models.User;
import Models.UsersRole;
import Views.Clients.ClientsList;
import Views.Orders.OrdersReceived;
import Views.Store.StoreItems;
import Views.User.UsersList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import App.Auth.Auth;

public class Dashboard extends JFrame {

    private JPanel dashboard;
    private JButton exitButton;
    private JLabel authUserName;

    private JButton ordersButton;
    private JButton storyButton;
    private JButton customersButton;
    private JButton employeesButton;
    private JButton invoicesButton;
    private JButton logoutButton;
    private JButton configurationButton;

    private Dashboard instance;

    private Application appHandler;

    public Dashboard() {

        setContentPane(dashboard);
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        employeesButton.addActionListener(onEmployeesButtonClick);
        ordersButton.addActionListener(onOrdersButtonClick);
        logoutButton.addActionListener(onLogoutButtonClick);
        exitButton.addActionListener(onExitButtonClick);
        storyButton.addActionListener(onStoreButtonClick);

        try {
            User loggedUser = Auth.user();
            if (loggedUser != null) {
                String userName = loggedUser.get("name").toString();
                authUserName.setText(userName);
                String userRole = loggedUser.parent(UsersRole.class).get("displayed").toString();
                System.out.printf("Logged as %s - [%s] \n", userName, userRole);
            }
        } catch (NullPointerException e) {
            System.out.println("Unauthorized.");
            System.exit(1);
        }

        instance = this;

        setVisible(true);
        customersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ClientsList clientslist = new ClientsList();
            }
        });
    }

    /**
     * Handle Login Button Click
     */
    protected ActionListener onEmployeesButtonClick = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            UsersList userListForm = new UsersList();
        }
    };

    protected ActionListener onOrdersButtonClick = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        OrdersReceived repair = new OrdersReceived();
        }
    };

    protected ActionListener onStoreButtonClick = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            StoreItems store = new StoreItems();
        }
    };

    /**
     * Handle Exit Button Click
     */
    protected ActionListener onExitButtonClick = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            instance.dispose();
            System.exit(0);
        }
    };

    /**
     * Handle Logout Button Click
     */
    protected ActionListener onLogoutButtonClick = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            Auth.logout();
            getAppHandler().mainWindow.setVisible(true);
            getAppHandler().mainWindow.loginForm = null;
            getAppHandler().handleLogout();
            instance.dispose();
        }
    };

    public void setAppHandler(Application app) {
        this.appHandler = app;
    }

    public Application getAppHandler() {
        return this.appHandler;
    }

    public void finalize() {
        try {
            this.getAppHandler().finalize();
            this.dispose();
            super.finalize();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
