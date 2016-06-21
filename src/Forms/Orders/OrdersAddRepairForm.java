package Forms.Orders;

import Models.Orders.Client;
import Models.Orders.Hardware;
import Models.Orders.Repair;
import Views.OrderPanel.OrderPanelMain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by pawel on 4/29/16.
 */
public class OrdersAddRepairForm extends JFrame {
    private JPanel panel1;
    private JButton repairButton;
    private JTextField Technican;
    private JTextField EndDate;
    private JTextField Cost;
    private JTextField Defect;
    private JComboBox comboBox1;

    public OrdersAddRepairForm()
    {
        setContentPane(panel1); // ustawia glowny panel
        pack();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        repairButton.addActionListener(afterRepairFormSubmit);
        // to sluzy do wysirodkowania okna
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        // pokazuje okno
        setVisible(true);

    }

    protected ActionListener afterRepairFormSubmit = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            Boolean errors = false;

            String sTechnican = "";
            String sEndDate = "";
            String sType = "";
            String sDefect = "";
            String sCost = "";
            Integer StartStatus = 1;

            if (isValid(Defect)) {
            sDefect = Defect.getText();
            } else {
                alert("Niepoprawne imie");
                errors = true;
                return;
            }
            //sTechnican = Technican.getText(); //TO DO: zmienic aby pobieralo wartosc z inputa
            sEndDate = EndDate.getText();
           // sType = Type.getText();
            String Type = comboBox1.getSelectedItem().toString();

            sCost = Cost.getText();








            if (! errors) {

                java.util.List<Client> ClientQ2 = Client.findBySQL("select iId from clients ORDER BY iId DESC LIMIT 1");
                Client ClientConst2 = ClientQ2.get(0);
                Object IdClient = ClientConst2.get("iId");   // poprawione

                java.util.List<Hardware> HardwareQ = Hardware.findBySQL("select Id from hardwares ORDER BY Id DESC LIMIT 1");
                Hardware HardwareConst = HardwareQ.get(0);
                Object HardwareId = HardwareConst.get("Id");   // poprawione

                Repair repair = new Repair();




                repair.set("Defect", sDefect);
              //  repair.set("TechnicanId", sTechnican);
                repair.set("EndDate", sEndDate);
                repair.set("Type", Type);
                repair.set("Cost", sCost);
                repair.set("ClientId", IdClient);
                repair.set("HardwareId", HardwareId);
                repair.set("Status", StartStatus);

                repair.saveIt();

                java.util.List<Repair> RepairQ = Repair.findBySQL("select Id from repairs ORDER BY Id DESC LIMIT 1");
                Repair RepairConst = RepairQ.get(0);
                Object RepairId = RepairConst.get("Id");

                OrderPanelMain neworderpanel = new OrderPanelMain((Integer) RepairId);

                setVisible(false);

            }

        }
    };
    private void alert(String message)
    {
        JOptionPane.showMessageDialog(OrdersAddRepairForm.this, message);
    }

    private Boolean isValid(JTextField filed)
    {
        return filed.getText().trim().length() > 0;
    }

    private void createUIComponents()
    {
        // TODO: place custom component creation code here
    }
}


