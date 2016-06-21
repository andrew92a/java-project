package Views.Clients;

import Models.Orders.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class ClientsList extends JFrame {
    private JPanel panel1;
    private JButton cancelButton;
    private JTable clientsTable;

    private ClientsList instance;

    public ClientsList()
    {
        instance = this;
        setContentPane(panel1);
        pack();

        List <Client> clients = Client.findAll();
        ClientsTableModel clientsModel = new ClientsTableModel(clients);
        clientsTable.setModel(clientsModel);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        cancelButton.addActionListener(onCancelButtonClick);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        setVisible(true);
    }

    /**
     * Handle refresh button click
     */
    protected ActionListener onCancelButtonClick = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            instance.dispose();
        }
    };

}
