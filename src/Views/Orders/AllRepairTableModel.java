package Views.Orders;

import Models.Orders.Repair;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * Created by pawel on 5/3/16.
 */
public class AllRepairTableModel extends AbstractTableModel
{
    private static final int LAST_NAME_COL = 0;
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
            case LAST_NAME_COL:
                return u.get("Id");
            case FIRST_NAME_COL:
                return u.get("Defect");
            case SECOND_NAME_COL:
                return u.get("Status");
            default:
                return u.get("Defect");
        }
    }

    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
}
