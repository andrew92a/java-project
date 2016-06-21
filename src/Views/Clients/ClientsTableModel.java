package Views.Clients;

import Models.Orders.Client;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ClientsTableModel extends AbstractTableModel {

    private static final int INDEX_COL = 0;
    private static final int FIRST_NAME_COL = 1;
    private static final int LAST_NAME_COL = 2;
    private static final int PHONE_COL = 3;
    private static final int CREATED_AT_COL = 4;

    private String[] columnNames = { "Nr", "Imie", "Nazwisko", "Telefon", "Utworzony" };

    private List<Client> clients;

    public ClientsTableModel(List<Client> clients) {
        this.clients = clients;
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return clients.size();
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {

        Client u = clients.get(row);

        switch (col) {
            case INDEX_COL:
                return row + 1;
            case LAST_NAME_COL:
                return u.get("Name");
            case FIRST_NAME_COL:
                return u.get("Surname");
            case PHONE_COL:
                return u.get("Phone");
            case CREATED_AT_COL:
                return u.get("created_at");
            default:
                return "";
        }
    }
}
