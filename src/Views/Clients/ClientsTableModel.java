package Views.Clients;

import Models.Client;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * Created by pawel on 4/29/16.
 */
public class ClientsTableModel extends AbstractTableModel {
    private static final int LAST_NAME_COL = 0;
    private static final int FIRST_NAME_COL = 1;

    private String[] columnNames = { "Last Name", "First Name" };

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
            case LAST_NAME_COL:
                return u.get("sFName");
            case FIRST_NAME_COL:
                return u.get("sSName");
            default:
                return u.get("sPhone");
        }
    }

    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
}
