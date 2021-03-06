package Views;

import App.Application;
import App.Auth.Auth;
import Models.Orders.Repair;
import Models.User;
import Models.UsersRole;
import Views.Clients.ClientsList;
import Views.Invoice.InvoicesList;
import Views.Orders.OrdersMenu;
import Views.Repairs.RepairsTableModel;
import Views.Store.StoreItems;
import Views.User.UsersList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

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
    private JTable userRepairs;

    private Dashboard instance;

    private User logged;

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
        invoicesButton.addActionListener(onInvoiceButtonClick);

        try {
            logged = Auth.user();
            if (logged != null) {

                final RepairsTableModel userRepairsTableModel = new RepairsTableModel(logged.getRepairs());
                userRepairs.setModel(userRepairsTableModel);

                userRepairs.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        if (evt.getClickCount() == 2) {
                            int row = userRepairs.rowAtPoint(evt.getPoint());
                            int col = userRepairs.columnAtPoint(evt.getPoint());
                            if (row >= 0 && col >= 0) {
                                int selected = userRepairs.convertRowIndexToModel(row);
                                Repair selectedRepair = userRepairsTableModel.getSelectedRepair(selected);

                                int dialogResult = JOptionPane.showConfirmDialog (
                                        null,
                                        "Czy na pewno chcesz oznaczyc zadanie jako wykonane?",
                                        "Potwierdz swoja decyzje",
                                        JOptionPane.YES_NO_OPTION);
                                if(dialogResult == JOptionPane.YES_OPTION) {

                                    selectedRepair.set("Status", 1);
                                    selectedRepair.saveIt();

                                    userRepairs.setModel(
                                        new RepairsTableModel(instance.logged.getRepairs())
                                    );
                                }
                            }
                        }
                    }
                });

                String userName = logged.get("name").toString();
                authUserName.setText(userName);
                String userRole = logged.parent(UsersRole.class).get("displayed").toString();
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
     * Handle User List Button Click
     */
    protected ActionListener onEmployeesButtonClick = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            UsersList userListForm = new UsersList();
        }
    };

    /**
     * Handle Orders List Button Click
     */
    protected ActionListener onOrdersButtonClick = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        OrdersMenu repair = new OrdersMenu();
        }
    };

    /**
     * Handle Store Items List Button Click
     */
    protected ActionListener onStoreButtonClick = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            StoreItems store = new StoreItems();
        }
    };

    /**
     * Handle Invoices List Button Click
     */
    protected ActionListener onInvoiceButtonClick = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            InvoicesList invoicesList = new InvoicesList();
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
