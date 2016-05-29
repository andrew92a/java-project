package Forms.Store;

import Forms.BaseForm;
import Models.Store.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class AddCategoryForm extends BaseForm {

    private JPanel panel1;
    private JTextField categoryName;
    private JTextArea categoryDescription;
    private JComboBox parentCategorySelect;
    private JButton cancelButton;
    private JButton addButton;

    private AddCategoryForm instance;

    public AddCategoryForm() {
        instance = this;
        setContentPane(panel1);
        pack();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        addButton.addActionListener(onAddCategoryButtonClick);
        cancelButton.addActionListener(onCancelButtonClick);

        setTitle("Dodawanie nowej kategorii");

        parentCategorySelect.setModel(new ParentCategoryComboModel());
        parentCategorySelect.setEditable(false);
        parentCategorySelect.setSelectedIndex(0);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        setVisible(true);
    }

    protected ActionListener onAddCategoryButtonClick = new ActionListener() {
        public void actionPerformed(ActionEvent e) {

            if ( ! isValid(categoryName)) {
                alert("Niepoprawna nazwa kategorii");
                return;
            }

            ItemsCategory category = (new ParentCategoryComboModel())
                .getSelectedParent(parentCategorySelect.getSelectedIndex());


            ItemsCategory newCategory = new ItemsCategory();
            newCategory.set("name", categoryName.getText());

            if (categoryDescription.getText().trim().length() > 0) {
                newCategory.set("description", categoryDescription.getText());
            }

            if (category != null) {
                category.add(newCategory);
            }

            if (newCategory.saveIt()) {
                alert("Dodano kategorie");
            } else {
                alert("Wystapil blad podczas dodawania kategorii");
            }
        }
    };

    protected ActionListener onCancelButtonClick = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            instance.dispose();
        }
    };

    private class ParentCategoryComboModel extends AbstractListModel implements ComboBoxModel
    {
        List<ItemsCategory> categories = ItemsCategory.findAll();
        String selection = null;

        public Object getElementAt(int index) {
            if (index == 0) {
                return "-brak-";
            }
            return categories.get(index - 1).get("name");
        }

        public ItemsCategory getSelectedParent(int index)
        {
            if (index == 0) {
                return null;
            }
            return categories.get(index - 1);
        }

        public int getSize() {
            return categories.size() + 1;
        }

        public void setSelectedItem(Object anItem) {
            selection = (String) anItem;
        }

        public Object getSelectedItem() {
            return selection;
        }
    }
}


