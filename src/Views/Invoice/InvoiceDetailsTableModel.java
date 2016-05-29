package Views.Invoice;


import Models.Invoice.InvoiceItem;
import Models.Store.Item;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class InvoiceDetailsTableModel extends AbstractTableModel {

    private static final int NAME_COL = 0;
    private static final int PRICE_COL = 1;
    private static final int TAX_COL = 2;
    private static final int QTY_COL = 3;

    private String[] columnNames = { "Nazwa", "Cena", "Podatek", "Ilosc", };

    private List<InvoiceItem> items;

    public InvoiceDetailsTableModel(List<InvoiceItem> items) {
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

        InvoiceItem u = items.get(row);

        switch (col) {
            case NAME_COL:
                return u.parent(Item.class).get("name");
            case PRICE_COL:
                return u.parent(Item.class).get("cost");
            case TAX_COL:
                return u.get("vat");
            case QTY_COL:
                return  u.get("quantity");
            default:
                return "-";
        }
    }

    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    public InvoiceItem getSelectedItem(int index)
    {
        return items.get(index);
    }
}
