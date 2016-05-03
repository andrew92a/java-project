package Views.Orders;

import Forms.OrdersAddClientForm;
import Views.OrderPanel.OrderPanelMain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// klasa okna chyba zawsze powinna rozszeniac klase JFrame lub JWindow, inaczej chyba nie dziala
public class OrdersReceived extends JFrame {

    private JPanel panel1;
    private JButton addButton;
    private JButton wszystkieNaprawyButton;
    private JButton ostatnieNaprawyButton;
    private JButton naprawyWTrakcieButton;
    private JButton szukajNaprawyButton;
    private JTextField valuesearch;
    private JButton szukajButton;
    private JFormattedTextField numPeriodsField;


    /**
     * To jest konstruktor klasy, wykonuje sie z chwila utowrzenia nowego objektu tej klasy
     * i to totaj definiujesz pewne opcje i wyswietlasz okno.
     *
     */
    public OrdersReceived()
    {
        setContentPane(panel1); // ustawia glowny panel
        pack();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        // to sluzy do wysirodkowania okna
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        // pokazuje okno
        setVisible(true);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                OrdersAddClientForm newclient = new OrdersAddClientForm();

            }
        });

        szukajButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                int i = Integer.parseInt(valuesearch.getText());

                OrderPanelMain panel = new OrderPanelMain(i);

            }
        });



    }

}
