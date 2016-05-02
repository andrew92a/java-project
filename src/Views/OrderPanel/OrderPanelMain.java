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
    private JButton wydrukButton;
    private JButton podsumowanieButton;
    private JButton notatkiButton;
    private JButton edycjaButton;
    private JButton EMAILButton;
    private JTextField Status;
    private JTextField HName;
    private JTextField f8r;
    private JTextField f14r;
    private JTextField f13r;
    private JTextField f12r;
    private JTextField f11r;
    private JTextField f10r;
    private JTextField f9r;
    private JTextField f1r;
    private JTextField f2r;
    private JTextField f3r;
    private JTextField f4r;
    private JTextField f5r;
    private JTextField f6r;
    private JTextField f7r;
    private JTextField AddDate;
    private JTextField EndDate;
    private JTextField Cost;
    private JTextArea Defect;
    private JTextArea Comment;
    private JLabel f1;
    private JLabel f2;
    private JLabel f3;
    private JLabel f4;
    private JLabel f5;
    private JLabel f6;
    private JLabel f7;
    private JLabel f8;
    private JLabel f9;
    private JLabel f10;
    private JLabel f11;
    private JLabel f12;
    private JLabel f13;
    private JLabel f14;
    private JLabel Surname;
    private JLabel RepairId;
    private JLabel UserId;
    private JLabel Email;
    private JLabel Phone;

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
        getFieldName();




    }

    private void createUIComponents()
    {
        // TODO: place custom component creation code here
    }

    private public void getFieldName()
    {
        List<Fieldsconf> f1Conf = Fieldsconf.where("Name = 'sField1'");
        Fieldsconf f1const = f1Conf.get(0);
        String f1Name = (String) f1const.get("Value");
        f1.setText(f1Name);

        List<Fieldsconf> f2Conf = Fieldsconf.where("Name = 'sField2'");
        Fieldsconf f2const = f1Conf.get(0);
        String f2Name = (String) f2const.get("Value");
        f2.setText(f1Name);

        List<Fieldsconf> f3Conf = Fieldsconf.where("Name = 'sField3'");
        Fieldsconf f3const = f3Conf.get(0);
        String f3Name = (String) f3const.get("Value");
        f3.setText(f1Name);

        // CZY tutaj muszę dla 14 definiowanych pól pierdzielic tyle kodu?


    }
}
