package Views.Repairs;

import Models.Orders.Repair;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class RepairsTableModel extends AbstractTableModel {

    private static final int INDEX_COL = 0;
    private static final int NAME_COL = 1;
    private static final int COST_COL = 2;

    private String[] columnNames = { "Nr", "Imie", "Nazwisko" };

    private List<Repair> repairs;

    public RepairsTableModel(List<Repair> repairs) {
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
            case INDEX_COL:
                return row + 1;
            case NAME_COL:
                return u.get("Defect");
            case COST_COL:
                return u.get("Cost");
            default:
                return "";
        }
    }

    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    public Repair getSelectedUser(int index)
    {
        return repairs.get(index);
    }

}
