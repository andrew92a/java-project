package Views.Orders;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Created by pawel on 4/29/16.
 */
public class OrdersAdd extends JFrame {
    private JPanel panel1;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JTextField textField2;
    private JTextField textField9;
    private JTextField textField10;
    private JTextField textField11;
    private JTextField textField12;
    private JButton dalejButton;
    private JComboBox comboBox1;


    public OrdersAdd() {
        setContentPane(panel1); // ustawia glowny panel
        pack();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        // to sluzy do wysirodkowania okna
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        // pokazuje okno
        setVisible(true);
        dalejButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                OrdersAddHardware newHardware = new OrdersAddHardware();
            }
        });
    }

}