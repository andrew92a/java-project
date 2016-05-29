package Forms.Invoice;

import Forms.BaseForm;
import Models.Invoice.Invoice;
import Models.Invoice.InvoiceItem;
import Models.Store.Item;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddInvoiceItem extends BaseForm{
    private JPanel panel1;
    private JButton backButton;
    private JButton addButton;
    private JComboBox itemSelect;
    private JSpinner qtySpinner;
    private JSpinner vatSpinner;

    private AddInvoiceItem instance;
    private Invoice invoice;

    public AddInvoiceItem(Invoice invoice)
    {
        instance = this;
        this.invoice = invoice;
        setContentPane(panel1);
        pack();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        backButton.addActionListener(onBackButtonClick);
        addButton.addActionListener(onCreateButtonClick);
        setTitle("Dodaj nowa pozycje faktury");

        itemSelect.setModel(new InvoiceItemComboBoxModel());

        SpinnerModel model = new SpinnerNumberModel(1, 1, 999999, 1);
        qtySpinner.setModel(model);

        SpinnerModel taxModel = new SpinnerNumberModel(23, 0, 100, 1);
        vatSpinner.setModel(taxModel);
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

            Item selectedItem = (new InvoiceItemComboBoxModel()).getSelectedItem(itemSelect.getSelectedIndex());
            Object itemId = selectedItem.getId();

            InvoiceItem newItem = InvoiceItem.createIt(
                "invoice_id", instance.invoice.getId(),
                "item_id", itemId,
                "price", selectedItem.get("cost"),
                "vat", vatSpinner.getValue(),
                "quantity", qtySpinner.getValue()
            );

            instance.dispose();
        }
    };

    /**
     * Sets up and fill UsersRole Combo Box
     */
    private class InvoiceItemComboBoxModel extends AbstractListModel implements ComboBoxModel
    {
        java.util.List<Item> allItems = Item.findAll();
        String selection = null;

        public Object getElementAt(int index) {
            return allItems.get(index).get("name");
        }

        public Item getSelectedItem(int index)
        {
            return allItems.get(index);
        }

        public int getSize() {
            return allItems.size();
        }

        public void setSelectedItem(Object anItem) {
            selection = (String) anItem;
        }

        public Object getSelectedItem() {
            return selection;
        }
    }

}
