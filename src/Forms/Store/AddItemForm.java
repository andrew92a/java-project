package Forms.Store;

import Models.Store.Item;
import Models.Store.ItemsCategory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;


public class AddItemForm extends JFrame {
    private JPanel panel1;
    private JButton cancelButton;
    private JButton addButton;
    private JComboBox categorySelect;
    private JTextField name;
    private JTextArea description;
    private JTextField cost;
    private JSpinner quantity;

    private AddItemForm instance;

    public AddItemForm() {
        instance = this;
        setContentPane(panel1);
        pack();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        addButton.addActionListener(onAddCategoryButtonClick);
        cancelButton.addActionListener(onCancelButtonClick);

        setTitle("Dodawanie nowego przedmiotu");

        categorySelect.setModel(new CategoryComboModel());
        categorySelect.setEditable(false);
        categorySelect.setSelectedIndex(0);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        setVisible(true);
    }

    protected ActionListener onAddCategoryButtonClick = new ActionListener() {
        public void actionPerformed(ActionEvent e) {

            String desc = "";

            if ( ! isValid(name)) {
                alert("Niepoprawna nazwa przedmiotu");
                return;
            }

            if (Integer.parseInt(quantity.getValue().toString()) <= 0) {
                alert("Niepoprawna ilość");
                return;
            }

            if (description.getText().trim().length() > 0) {
                desc = description.getText();
            }

            ItemsCategory category = (new CategoryComboModel())
                .getSelectedCategory(categorySelect.getSelectedIndex());

            Item newItem = new Item();
            newItem.set("name", name.getText());
            newItem.set("cost", cost.getText());
            newItem.set("quantity", quantity.getValue());
            newItem.set("description", desc);

            if (category != null) {
                category.add(newItem);
            }

            if (newItem.saveIt()) {
                alert("Dodano przedmiot");
            } else {
                alert("Wystapil blad podczas dodawania przedmiotu");
            }
        }
    };

    private ActionListener onCancelButtonClick = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            instance.dispose();
        }
    };

    private class CategoryComboModel extends AbstractListModel implements ComboBoxModel
    {
        java.util.List<ItemsCategory> categories = ItemsCategory.findAll();
        String selection = null;

        public Object getElementAt(int index) {
            return categories.get(index ).get("name");
        }

        public ItemsCategory getSelectedCategory(int index)
        {
            return categories.get(index);
        }

        public int getSize() {
            return categories.size();
        }

        public void setSelectedItem(Object anItem) {
            selection = (String) anItem;
        }

        public Object getSelectedItem() {
            return selection;
        }
    }

    private void alert(String message) {
        JOptionPane.showMessageDialog(AddItemForm.this, message);
    }

    private Boolean isValid(JTextField filed) {
        return filed.getText().trim().length() > 0;
    }
}
