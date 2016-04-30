package Views.OrderPanel;

import Models.Fieldsconf;

import javax.swing.*;
import java.awt.*;
import java.util.List; //brakowalo tej paczki

/**
 * Created by pawel on 4/30/16.
 */
public class OrderPanelMain extends JFrame{

    private JPanel panel1;
    private JTextField Name;
    private JLabel RepairId;

    public OrderPanelMain()
    {
        setContentPane(panel1); // ustawia glowny panel
        pack();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        // to sluzy do wysirodkowania okna
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        // pokazuje okno
        setVisible(true);


        List<Fieldsconf> confs = Fieldsconf.where("Name = 'Field1'"); //chce wyciagnac z tabeli fieldconfs rekord z kolumny values gdzie name = Field1
        // Fieldsconf f1 = confs.get(0);

        Fieldsconf f1 = confs.iterator().next();
        String f1name = (String) f1.get("Value");

        Name.setText(f1name);

    }
}
