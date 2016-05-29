package Views.Store;

import Models.Store.Item;
import Models.Store.ItemsCategory;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * Model for Store Items List.
 */
public class StoreItemTableModel extends AbstractTableModel {

    private static final int NAME_COL = 0;
    private static final int DESC_COL = 1;
    private static final int CATEGORY_COL = 2;
    private static final int PRICE_COL = 3;
    private static final int QTY_COL = 4;

    private String[] columnNames = { "Nazwa", "Opis", "Kategoria", "Cena", "Ilosc" };

    private List<Item> items;

    public StoreItemTableModel(List<Item> items) {
        this.items = items;
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return items.size();
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {

        Item i = items.get(row);

        switch (col) {
            case NAME_COL:
                return i.get("name");
            case DESC_COL:
                return i.get("description");
            case CATEGORY_COL:
                return i.parent(ItemsCategory.class).get("name");
            case PRICE_COL:
                return i.get("cost");
            case QTY_COL:
                return i.get("quantity");
            default:
                return "-";
        }
    }

    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    public Item getSelectedItem(int index)
    {
        return items.get(index);
    }
}