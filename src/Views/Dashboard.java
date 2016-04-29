package Views;

import App.Application;
import Views.Orders.OrdersReceived;
import Views.User.UsersList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dashboard extends JFrame {

    private JPanel dashboard;
    private JButton exitButton;
    private JLabel authUserName;

    private JButton ordersButton;
    private JButton storyButton;
    private JButton customersButton;
    private JButton employeesButton;
    private JButton invoicesButton;
    private JButton logOutButton;
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
        exitButton.addActionListener(onExitButtonClick);

        authUserName.setText("Andrzej");
        instance = this;

        setVisible(true);
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

        // Utworzenie repair (nowej instancji klasy OrdersReceived)
        // powoduje wywołanie konstruktora tej klasy,
        // nic sie nie działa ponieważ nie bylo tego konstruktora :P
        OrdersReceived repair = new OrdersReceived();
        }
    };


    /**
     * Handle Login Button Click
     */
    protected ActionListener onExitButtonClick = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            instance.dispose();
            System.exit(0);
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
