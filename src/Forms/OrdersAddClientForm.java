package Forms;


import Models.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Created by pawel on 4/29/16.
 */
public class OrdersAddClientForm extends JFrame {
    private JPanel panel1;
    private JTextField SName;
    private JTextField Company;
    private JTextField FName;
    private JTextField NIP;
    private JTextField Phone1;
    private JTextField Postal;
    private JTextField City;
    private JTextField Street;
    private JTextField AddrBuild;
    private JTextField AddrLoc;
    private JTextField Phone2;
    private JButton clientButton;
    private JComboBox KindOfClient;
    private JLabel Other;
    private JPanel OtherClient;
    private JTextField Description;


    public OrdersAddClientForm() {
        setContentPane(panel1); // ustawia glowny panel
        pack();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        clientButton.addActionListener(afterClientFormSubmit);

        // to sluzy do wysirodkowania okna
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        // pokazuje okno
        setVisible(true);

    }

    protected ActionListener afterClientFormSubmit = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            Boolean errors = false;
            String sFName = "";
            String sSName = "";
            String sPhone1 = "";
            String sPhone2 = "";
            String sCompany = "";
            String sNIP = "";
            String sPostal = "";
            String sCity = "";
            String sStreet = "";
            String sAddrBuild = "";
            String sAddrLoc = "";
            String sDescription = "";

            if (isValid(FName)) {
                sFName = FName.getText();
            } else {
                alert("Niepoprawne imie");
                errors = true;
                return;
            }

            if (isValid(SName)) {
                sSName = SName.getText();
            } else {
                alert("Niepoprawne nazwisko");
                errors = true;
            }

            if (isValid(Phone1)) {
                sPhone1 = Phone1.getText();
            } else {
                alert("Niepoprawny nr telefonu ");
                errors = true;
            }

            sPhone2 = Phone2.getText();
            sCompany = Company.getText();
            sNIP = NIP.getText();
            sPostal = Postal.getText();
            sCity = City.getText();
            sStreet = Street.getText();
            sAddrBuild = AddrBuild.getText();
            sAddrLoc = AddrLoc.getText();
            sDescription = Description.getText();


            if (! errors) {

                // te zmienne String sa w
                Client client = new Client();

                client.set("Name", sFName);
                client.set("Surname", sSName);
                client.set("Phone", sPhone1);
                client.set("Phone2", sPhone2);
                client.set("Company", sCompany);
                client.set("NIP", sNIP);
                client.set("Postal", sPostal);
                client.set("City", sCity);
                client.set("Street", sStreet);
                client.set("AddrBuild", sAddrBuild);
                client.set("AddrLoc", sAddrLoc);
                client.set("Description", sDescription);

                client.saveIt();

                OrdersAddHardwareForm newHardware = new OrdersAddHardwareForm();
                setVisible(false);

            }
        }
    };

    private void alert(String message) {
        JOptionPane.showMessageDialog(OrdersAddClientForm.this, message);
    }

    private Boolean isValid(JTextField filed) {
        return filed.getText().trim().length() > 0;
    }

}