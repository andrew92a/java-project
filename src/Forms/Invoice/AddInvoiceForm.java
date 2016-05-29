package Forms.Invoice;


import App.Auth.Auth;
import Forms.BaseForm;
import Models.Invoice.Invoice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("ConstantConditions")
public class AddInvoiceForm extends BaseForm {

    private JPanel panel1;
    private JTextField number;
    private JTextField name;
    private JTextField place;
    private JComboBox customerSelect;
    private JButton backButton;
    private JButton addButton;
    private AddInvoiceForm instance;

    /**
     * Create form instance
     */
    public AddInvoiceForm()
    {
        instance = this;
        setContentPane(panel1);
        pack();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        backButton.addActionListener(onBackButtonClick);
        addButton.addActionListener(onCreateButtonClick);
        setTitle("Tworzenie nowej faktury");

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        setVisible(true);
    }

    /**
     * Handle back button click
     */
    protected ActionListener onBackButtonClick = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            instance.dispose();
        }
    };

    /**
     * Handle create button click
     */
    protected ActionListener onCreateButtonClick = new ActionListener() {
        public void actionPerformed(ActionEvent e) {

            if (!isValid(number)) {
                alert("Nieprawidlowy numer faktury");
                return;
            }

            if (!isValid(place)) {
                alert("Nieprawidlowa miejscowość");
                return;
            }

            Invoice newInvoice = new Invoice();


            newInvoice.set("name", name.getText());
            newInvoice.set("number", number.getText());
            newInvoice.set("place", place.getText());
            newInvoice.set("customer_id", 1);
            newInvoice.set("user_id", Auth.user().get("id"));

            if (newInvoice.saveIt()) {
                alert("Dodano nowa fakture");
                instance.dispose();
            } else {
                alert("Błąd podczas zapisu nowej faktury.");
            }
        }
    };

}
