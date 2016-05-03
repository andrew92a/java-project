package Views.Store;


import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Forms.Store.AddCategoryForm;
import Models.Store.ItemsCategory;

public class StoreCategories extends JFrame {
    private JPanel panel1;
    private JButton backButton;
    private JButton addCategoryButton;
    private JTree categoryTree;
    private JTable itemsTable;

    public StoreCategories() {

        setContentPane(panel1);
        pack();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        addCategoryButton.addActionListener(onAddCategoryButtonClick);

        fillCategoryTree();
        this.setVisible(true);
    }

    private ActionListener onAddCategoryButtonClick = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            AddCategoryForm categoryForm = new AddCategoryForm();
        }
    };

    private void fillCategoryBranch(DefaultMutableTreeNode branch, ItemsCategory category)
    {
        List <ItemsCategory> children = category.getAll(ItemsCategory.class);

        for (ItemsCategory child : children) {
            System.out.println("Add: " + child.get("name"));
            DefaultMutableTreeNode branchChild = new DefaultMutableTreeNode(child.get("name"));
            branch.add(branchChild);
            fillCategoryBranch(branchChild, child);
        }
    }

    private void fillCategoryTree() {

        List <ItemsCategory> rootCategories = ItemsCategory.where("parent_id IS NULL");
        DefaultMutableTreeNode categories = new DefaultMutableTreeNode("Kategorie");

        for (ItemsCategory cat : rootCategories) {
            DefaultMutableTreeNode branchChild = new DefaultMutableTreeNode(cat.get("name"));
            fillCategoryBranch(branchChild, cat);
            categories.add(branchChild);
        }

        DefaultTreeModel dtm = new DefaultTreeModel(categories);
        categoryTree.setModel(dtm);
    }
}
