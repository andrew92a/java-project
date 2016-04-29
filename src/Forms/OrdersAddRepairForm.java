package Forms;

import javax.swing.*;
import java.awt.*;

/**
 * Created by pawel on 4/29/16.
 */
public class OrdersAddRepairForm extends JFrame {
    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JComboBox comboBox1;
    private JTextArea textArea1;
    private JButton dodajNaprawęButton;

    public OrdersAddRepairForm()
    {
        setContentPane(panel1); // ustawia glowny panel
        pack();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        // to sluzy do wysirodkowania okna
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        // pokazuje okno
        setVisible(true);
    }
}
