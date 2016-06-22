package Views.Orders;

import Models.Orders.Repair;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


/**
 * Created by pawel on 5/8/16.
 */
public class AllRepairs extends JFrame
{
    private JPanel panel1;
    private JTable repairstable;
    private JButton cancelButtonRepairs;

    private AllRepairs instance;


    public AllRepairs()
    {
        instance = this;

        setContentPane(panel1);
        pack();

        List <Repair> repairs = Repair.findAll();
        AllRepairTableModel repairmodel = new AllRepairTableModel(repairs);
        repairstable.setModel(repairmodel);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        cancelButtonRepairs.addActionListener(onCancelButtonRepairsClick);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        java.util.List<Repair> allrepairs = Repair.findAll();

        AllRepairTableModel u = new AllRepairTableModel(allrepairs);
        this.repairstable.setModel(u);

        setVisible(true);

    }


    private java.util.List<Repair> getLimitRepair() {
        return Repair.findAll().limit(10);
    }

    protected ActionListener onCancelButtonRepairsClick = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
       //     repairepository.dispose();
        }
    };

}
