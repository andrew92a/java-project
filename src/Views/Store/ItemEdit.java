package Views.Store;

import Forms.BaseForm;
import Models.Store.Item;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ItemEdit extends BaseForm {
    private JPanel panel1;
    private JButton cancelButton;
    private JButton saveButton;
    private JTextField priceField;
    private JSpinner quantity;
    private JLabel itemName;
    private JTextField itemNameField;
    private ItemEdit instance;
    private Item item;

    public ItemEdit(Item item) {

        this.item = item;
        itemName.setText(item.get("name").toString());
        priceField.setText(item.get("cost").toString());
        itemNameField.setText(item.get("name").toString());
        instance = this;
        setContentPane(panel1);
        pack();
        int min = 1;
        int max = 1000000;
        int step = 1;
        int initValue = Integer.parseInt(item.get("quantity").toString());

        SpinnerModel model = new SpinnerNumberModel(initValue, min, max, step);
        quantity.setModel(model);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        saveButton.addActionListener(onSaveButtonClick);
        cancelButton.addActionListener(onCancelButtonClick);

        setTitle("Edycja przedmiotu");

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        setVisible(true);
    }

    /**
     * Handle Save Button Click
     */
    protected ActionListener onSaveButtonClick = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            item.set("name", itemNameField.getText());
            item.set("cost", priceField.getText());
            item.set("quantity", quantity.getValue());
            item.saveIt();

            alert("Zapisano przedmiot");
            instance.dispose();
        }
    };


    /**
     * Handle Cancel Button Click
     */
    protected ActionListener onCancelButtonClick = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            instance.dispose();
        }
    };
}
