package Forms;

import Models.Hardware;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by pawel on 4/29/16.
 */
public class OrdersAddHardwareForm extends JFrame {
    private JPanel panel1;
    private JTextField HName;
    private JTextField Field6;
    private JTextField Field7;
    private JTextField Field8;
    private JTextField Field9;
    private JTextField Field10;
    private JTextField Field11;
    private JButton hardwareButton;
    private JTextField Field1;
    private JTextField Field2;
    private JTextField Field3;
    private JTextField Field4;
    private JTextField Field5;
    private JTextArea FieldLong;
    private JComboBox HType;

    public OrdersAddHardwareForm()
    {
        setContentPane(panel1); // ustawia glowny panel
        pack();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        hardwareButton.addActionListener(afterHardwareFormSubmit);
        // to sluzy do wysirodkowania okna
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        // pokazuje okno
        setVisible(true);

    }


    protected ActionListener afterHardwareFormSubmit = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            Boolean errors = false;

            String sHName = "";
            String sHType = "";
            String sField1 = "";
            String sField2 = "";
            String sField3 = "";
            String sField4 = "";
            String sField5 = "";
            String sField6 = "";
            String sField7 = "";
            String sField8 = "";
            String sField9 = "";
            String sField10 = "";
            String sField11 = "";
            String sField12 = "";
            String sField13 = "";
            String sField14 = "";
            String sFieldLong = "";

            if (isValid(HName)) {
                sHName = HName.getText();
            } else {
                alert("Niepoprawne imie");
                errors = true;
                return;
            }


            sField1 = Field1.getText();
            sField2 = Field2.getText();
            sField3 = Field3.getText();
            sField4 = Field4.getText();
            sField5 = Field5.getText();
            sField6 = Field6.getText();
            sField7 = Field7.getText();
            sField8 = Field8.getText();
            sField9 = Field9.getText();
            sField10 = Field11.getText();
            sField11 = Field11.getText();



            if (! errors) {

                // te zmienne String sa w
                Hardware hardware = new Hardware();

                hardware.set("HName", sHName);
                hardware.set("Field1", sField1);
                hardware.set("Field2", sField2);
                hardware.set("Field3", sField3);
                hardware.set("Field4", sField4);
                hardware.set("Field5", sField5);
                hardware.set("Field6", sField6);
                hardware.set("Field7", sField7);
                hardware.set("Field8", sField8);
                hardware.set("Field9", sField9);
                hardware.set("Field10", sField10);
                hardware.set("Field11", sField11);


                hardware.saveIt();
               OrdersAddRepairForm newrepair = new OrdersAddRepairForm();
                setVisible(false);

                  }

            }
        };
            private void alert(String message)
            {
                JOptionPane.showMessageDialog(OrdersAddHardwareForm.this, message);
            }

            private Boolean isValid(JTextField filed)
        {
                return filed.getText().trim().length() > 0;
        }
}
