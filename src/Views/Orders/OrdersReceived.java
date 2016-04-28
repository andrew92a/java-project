package Views.Orders;

import javax.swing.*;
import java.awt.*;

// klasa okna chyba zawsze powinna rozszeniac klase JFrame lub JWindow, inaczej chyba nie dziala
public class OrdersReceived extends JFrame {

    private JTextArea zleceniaTextArea;
    private JPanel panel1;

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
    }
}
