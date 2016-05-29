package Views.Store;


import Forms.Store.AddCategoryForm;
import Models.Store.Item;
import Models.Store.ItemsCategory;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class StoreCategories extends JFrame {
    private JPanel panel1;
    private JButton backButton;
    private JButton addCategoryButton;
    private JTree categoryTree;
    private JTable itemsTable;
    private StoreCategories instance;

    public StoreCategories() {

        instance = this;
        setContentPane(panel1);
        pack();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        ActionListener onAddCategoryButtonClick = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            AddCategoryForm categoryForm = new AddCategoryForm();
            }
        };

        addCategoryButton.addActionListener(onAddCategoryButtonClick);
        backButton.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    instance.dispose();
                }
            }
        );

        fillCategoryTree();
        this.setVisible(true);
    }

    private void fillCategoryBranch(DefaultMutableTreeNode branch, ItemsCategory category)
    {
        List <ItemsCategory> children = category.getAll(ItemsCategory.class);

        for (ItemsCategory child : children) {
            DefaultMutableTreeNode branchChild = new DefaultMutableTreeNode(child.get("name"));
            branchChild.setUserObject(child);
            branch.add(branchChild);
            fillCategoryBranch(branchChild, child);
        }
    }

    private void fillCategoryTree() {

        List <ItemsCategory> rootCategories = ItemsCategory.where("parent_id IS NULL");
        DefaultMutableTreeNode categories = new DefaultMutableTreeNode("Kategorie");

        for (ItemsCategory cat : rootCategories) {
            DefaultMutableTreeNode branchChild = new DefaultMutableTreeNode(cat.get("name"));
            branchChild.setUserObject(cat);
            fillCategoryBranch(branchChild, cat);
            categories.add(branchChild);
        }

        DefaultTreeModel dtm = new DefaultTreeModel(categories);
        categoryTree.setModel(dtm);

        categoryTree.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                        categoryTree.getLastSelectedPathComponent();

                    if (node == null) {
                        return;
                    }

                    ItemsCategory nodeInfo = (ItemsCategory) node.getUserObject();
                    ItemsCategory selected = ItemsCategory.findById(nodeInfo.getId());
                    int selectedId = Integer.parseInt(selected.get("id").toString());

                    ArrayList<Integer> underCategories = new ArrayList<Integer>();
                    underCategories.add(selectedId);

                    for (ItemsCategory subcategory : selected.getAll(ItemsCategory.class)) {
                        underCategories.add(Integer.parseInt(subcategory.get("id").toString()));
                    }

                    List <Item> items = Item.where("category_id IN " + underCategories
                        .toString()
                        .replace("[", "(")
                        .replace("]", ")")
                    );

                    StoreItemTableModel itemsTableModel = new StoreItemTableModel(items);
                    itemsTable.setModel(itemsTableModel);
                }
            }
        });
    }
}
