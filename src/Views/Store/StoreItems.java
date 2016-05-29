package Views.Store;

import Forms.Store.AddItemForm;
import Models.Store.Item;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class StoreItems extends JFrame {

    private JPanel panel1;
    private JTable table1;
    private JButton categoryButton;
    private JButton backButton;
    private JButton addItemButton;
    private JButton searchButton;
    private JTextField searchField;
    protected StoreItemTableModel tableModel;
    private StoreItems instance;

    public StoreItems() {

        setContentPane(panel1);
        instance = this;
        pack();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        ActionListener onCategoryButtonClick = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StoreCategories categoriesView = new StoreCategories();
            }
        };
        categoryButton.addActionListener(onCategoryButtonClick);
        addItemButton.addActionListener(onAddItemButtonClick);
        searchButton.addActionListener(onSearchButtonClick);

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                instance.dispose();
            }
        });

        List<Item> items = Item.findAll();
        tableModel = new StoreItemTableModel(items);
        table1.setModel(tableModel);

        setVisible(true);
    }

    ActionListener onAddItemButtonClick = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            AddItemForm addItemForm = new AddItemForm();
        }
    };

    ActionListener onSearchButtonClick = new ActionListener() {
        public void actionPerformed(ActionEvent e) {

            String searchQuery = searchField.getText();
            searchQuery = '%' + searchQuery + '%';
            List<Item> items = Item.where("name LIKE ? or description LIKE ?", searchQuery, searchQuery);
            tableModel = new StoreItemTableModel(items);
            table1.setModel(tableModel);
        }
    };
}
