package Views.Clients;

import Models.Orders.Client;
import Repository.ClientRepository;

import javax.swing.*;
import java.awt.*;

/**
 * Created by pawel on 4/29/16.
 */
public class ClientsList extends JFrame {
    private JPanel panel1;
    private JTextField klienciTextField;
    private JTable table1;
    private ClientRepository client;

    public ClientsList()
    {
        this.client = new ClientRepository();

        setContentPane(panel1);
        pack();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);


        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        java.util.List<Client> clients = Client.findAll();

        ClientsTableModel u = new ClientsTableModel(clients);
        this.table1.setModel(u);

        setVisible(true);

    }


    private java.util.List<Client> getLimitClient() {
        return Client.findAll().limit(10);
    }

    /**
     * Handle Login Button Click
     */


    /**
     * Handle Login Button Click
     */


}
