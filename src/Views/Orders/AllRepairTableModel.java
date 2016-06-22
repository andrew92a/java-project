package Views.Orders;

import Models.Orders.Client;
import Models.Orders.Repair;

import javax.swing.table.AbstractTableModel;
import java.util.List;


public class AllRepairTableModel extends AbstractTableModel
{
    private static final int ID_COL = 0;
    private static final int NAME_COL = 1;
    private static final int STATUS_COL = 2;
    private static final int CLIENT_COL = 3;

    private String[] columnNames = { "Id", "Nazwa", "Status", "Klient"};

    private List<Repair> repairs;

    public AllRepairTableModel(List <Repair> repairs) {
        this.repairs = repairs;
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return repairs.size();
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {

        Repair u = repairs.get(row);

        switch (col) {

            case ID_COL:
                return u.get("Id");

            case NAME_COL:
                return u.get("Defect");

            case STATUS_COL:
                if (u.get("Status") != null) {
                    return "Wykonana";
                }
                return "Nowa";
            case CLIENT_COL:

                if (u.parent(Client.class) != null) {
                    return (u.parent(Client.class).get("Name").toString()
                            + " " +
                            u.parent(Client.class).get("Surname").toString());
                } else {
                    return "-";
                }

            default:
                return "";
        }
    }
    public Repair getSelectedRepair(int index)
    {
        return repairs.get(index);
    }

}
