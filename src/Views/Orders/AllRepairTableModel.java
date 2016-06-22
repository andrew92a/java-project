package Views.Orders;

import Models.Orders.Repair;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * Created by pawel on 5/3/16.
 */
public class AllRepairTableModel extends AbstractTableModel
{
    private static final int ID_COL = 0;
    private static final int FIRST_NAME_COL = 1;
    private static final int SECOND_NAME_COL = 2;

    private String[] columnNames = { "Id", "Nazwa", "Statu"};

    private List<Repair> repairs;

    public AllRepairTableModel(List<Repair> repairs) {
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
            case FIRST_NAME_COL:
                return u.get("Name");
            case SECOND_NAME_COL:
                return u.get("Surname");
            default:
                return "";
        }
    }

    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
}
