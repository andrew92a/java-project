package Views.Store;

import Forms.Store.AddItemForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StoreItems extends JFrame {

    private JPanel panel1;
    private JTable table1;
    private JButton categoryButton;
    private JButton backButton;
    private JButton addItemButton;

    public StoreItems() {

        setContentPane(panel1);
        pack();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        categoryButton.addActionListener(onCategoryButtonClick);
        addItemButton.addActionListener(onAddItemButtonClick);

        setVisible(true);
    }

    private ActionListener onCategoryButtonClick = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            StoreCategories categoriesView = new StoreCategories();
        }
    };

    private ActionListener onAddItemButtonClick = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            AddItemForm addItemForm = new AddItemForm();
        }
    };
}
