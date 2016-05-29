package Views.Invoice;


import Models.Invoice.Invoice;
import Models.User;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class InvoiceTableModel extends AbstractTableModel {


    private static final int ID_COL = 0;
    private static final int NUMBER_COL = 1;
    private static final int NAME_COL = 2;
    private static final int USER_COL = 3;
    private static final int CUSTOMER_COL = 4;
    private static final int CREATED_COL = 5;

    private String[] columnNames = { "ID", "Numer", "Nazwa", "Uzytkownik", "Odbiorca", "Utworzona" };

    private List<Invoice> invoices;

    public InvoiceTableModel(List<Invoice> users) {
        this.invoices = users;
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return invoices.size();
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {

        Invoice u = invoices.get(row);

        switch (col) {
            case ID_COL:
                return (row + 1);
            case NUMBER_COL:
                return u.get("number");
            case NAME_COL:
                return u.get("name");
            case USER_COL:
                return u.parent(User.class).get("name").toString() + " " + u.parent(User.class).get("surname").toString();
            case CUSTOMER_COL:
                return "-";
                //return u.parent(Customer.class).get("name").toString() + u.parent(Customer.class).get("surname").toString();
            case CREATED_COL:
                return u.get("created_at");
            default:
                return "-";
        }
    }

    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    public Invoice getSelectedInvoice(int index)
    {
        return invoices.get(index);
    }
}
