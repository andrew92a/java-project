package Forms;

import Models.Repair;
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
    private JTextField Type;
    private JTextField Cost;
    private JTextField Defect;

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

            if (isValid(Defect)) {
            sDefect = Defect.getText();
            } else {
                alert("Niepoprawne imie");
                errors = true;
                return;
            }
            sTechnican = Technican.getText(); //TO DO: zmienic aby pobieralo wartosc z inputa
            sEndDate = EndDate.getText();
            sType = Type.getText();
            sCost = Cost.getText();








            if (! errors) {

                Repair repair = new Repair();

                repair.set("Defect", sDefect);
                repair.set("TechnicanId", sTechnican);
                repair.set("EndDate", sEndDate);
                repair.set("Type", sType);
                repair.set("Cost", sCost);

                repair.saveIt();
                OrderPanelMain neworderpanel = new OrderPanelMain(20);

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

}


