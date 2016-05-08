package Views.Orders;

import Models.Orders.Repair;
import Repository.AllRepairsRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by pawel on 5/8/16.
 */
public class AllRepairs extends JFrame
{
    private JPanel panel1;
    private JTable table1;
    private JButton ActiveRepairsButton;

    private AllRepairsRepository allrepair;


    public AllRepairs()
    {
        this.allrepair = new AllRepairsRepository();

        setContentPane(panel1);
        pack();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);


        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        java.util.List<Repair> allrepairs = Repair.findAll();

        AllRepairTableModel u = new AllRepairTableModel(allrepairs);
        this.table1.setModel(u);

        setVisible(true);

        ActiveRepairsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                java.util.List<Repair> allrepairs;


                allrepairs = allrepair.activeRepair();

                AllRepairTableModel u = new AllRepairTableModel(allrepairs);
                table1.setModel(u);
            }
        });
    }


    private java.util.List<Repair> getLimitRepair() {
        return Repair.findAll().limit(10);
    }

}
