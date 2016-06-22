package Views.OrderPanel;

import Models.Conf.Fieldsconf;
import Models.Orders.Client;
import Models.Orders.Hardware;
import Models.Orders.Repair;

import javax.swing.*;
import java.awt.*;
import java.util.List; //brakowalo tej paczki


/**
 * Created by pawel on 4/30/16.
 */
public class OrderPanelMain extends JFrame{

    private JPanel panel1;
    private JButton wydrukButton;
    private JTextField Status;
    private JTextField HName;
    private JTextField f8r;
    private JTextField f9r;
    private JTextField f1r;
    private JTextField f2r;
    private JTextField f3r;
    private JTextField f4r;
    private JTextField f5r;
    private JTextField f6r;
    private JTextField f7r;
    private JTextField AddDate;
    private JTextField EndDate;
    private JTextField Cost;
    private JTextArea Defect;
    private JTextArea Comment;
    private JLabel f1;
    private JLabel f2;
    private JLabel f3;
    private JLabel f4;
    private JLabel f5;
    private JLabel f6;
    private JLabel f7;
    private JLabel f8;
    private JLabel f9;
    private JLabel f10;
    private JLabel f11;
    private JLabel Surname;
    private JLabel RepairId;
    private JLabel UserId;
    private JLabel Name;
    private JTextField RepairType;
    private JLabel ClientId;
    private JTextField Technican;
    private JTextField EmailBox;
    private JTextField PhoneBox;
    private JTextField NameBox;
    private JTextField SurnameBox;

    public OrderPanelMain(int OrderId)
    {
        int IdNumber=OrderId;
        setContentPane(panel1);
        pack();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        setVisible(true);
        getFieldName();
        getRepair(IdNumber);

    }

    private void getFieldName()
    {
        List<Fieldsconf> f1Conf = Fieldsconf.where("Name = 'sField1'");
        Fieldsconf f1const = f1Conf.get(0);
        String f1Name = (String) f1const.get("Value");
        f1.setText(f1Name);

        List<Fieldsconf> f2Conf = Fieldsconf.where("Name = 'sField2'");
        Fieldsconf f2const = f1Conf.get(0);
        String f2Name = (String) f2const.get("Value");
        f2.setText(f2Name);

        List<Fieldsconf> f3Conf = Fieldsconf.where("Name = 'sField3'");
        Fieldsconf f3const = f3Conf.get(0);
        String f3Name = (String) f3const.get("Value");
        f3.setText(f3Name);

        List<Fieldsconf> f4Conf = Fieldsconf.where("Name = 'sField4'");
        Fieldsconf f4const = f4Conf.get(0);
        String f4Name = (String) f4const.get("Value");
        f4.setText(f4Name);

        List<Fieldsconf> f5Conf = Fieldsconf.where("Name = 'sField5'");
        Fieldsconf f5const = f5Conf.get(0);
        String f5Name = (String) f5const.get("Value");
        f5.setText(f5Name);

        List<Fieldsconf> f6Conf = Fieldsconf.where("Name = 'sField6'");
        Fieldsconf f6const = f6Conf.get(0);
        String f6Name = (String) f6const.get("Value");
        f6.setText(f6Name);

        List<Fieldsconf> f7Conf = Fieldsconf.where("Name = 'sField7'");
        Fieldsconf f7const = f7Conf.get(0);
        String f7Name = (String) f7const.get("Value");
        f7.setText(f7Name);

        List<Fieldsconf> f8Conf = Fieldsconf.where("Name = 'sField8'");
        Fieldsconf f8const = f8Conf.get(0);
        String f8Name = (String) f8const.get("Value");
        f8.setText(f8Name);

        List<Fieldsconf> f9Conf = Fieldsconf.where("Name = 'sField9'");
        Fieldsconf f9const = f9Conf.get(0);
        String f9Name = (String) f9const.get("Value");
        f9.setText(f9Name);



    }

    private void getRepair(int IdNum)
    {
        int IdNumber = IdNum;
        RepairId.setText(String.valueOf(IdNum));

        List<Repair> RepairQ = Repair.where("Id = " + IdNumber);
        Repair RepairConst = RepairQ.get(0);

        String AddDateR = String.valueOf(RepairConst.get("AddDate"));
        String EndDateR = String.valueOf(RepairConst.get("EndDate"));
        String TypeR = String.valueOf(RepairConst.get("Type"));
        String CostR = String.valueOf(RepairConst.get("Cost"));
        String DefectR = String.valueOf(RepairConst.get("Defect"));
        String CommentR = String.valueOf(RepairConst.get("Comment"));
        String StatusR = String.valueOf(RepairConst.get("Status"));
        String ClientIdR = String.valueOf(RepairConst.get("ClientId"));
        String HardwareIdR = String.valueOf(RepairConst.get("HardwareId"));
        String TechnicanIdR = String.valueOf(RepairConst.get("TechnicanId"));

        AddDate.setText(AddDateR);
        EndDate.setText(EndDateR);
        RepairType.setText(TypeR);
        Cost.setText(CostR);
        Defect.setText(DefectR);
        Status.setText(StatusR);
        Technican.setText(TechnicanIdR);

        List<Hardware> HardwareQ = Hardware.where("Id = " + HardwareIdR);
        Hardware HardwareConst = HardwareQ.get(0);

        String HNameR = String.valueOf(HardwareConst.get("HName"));
        String f1rR = String.valueOf(HardwareConst.get("Field1"));
        String f2rR = String.valueOf(HardwareConst.get("Field2"));
        String f3rR = String.valueOf(HardwareConst.get("Field3"));
        String f4rR = String.valueOf(HardwareConst.get("Field4"));
        String f5rR = String.valueOf(HardwareConst.get("Field5"));
        String f6rR = String.valueOf(HardwareConst.get("Field6"));
        String f7rR = String.valueOf(HardwareConst.get("Field7"));
        String f8rR = String.valueOf(HardwareConst.get("Field8"));
        String f9rR = String.valueOf(HardwareConst.get("Field9"));

        HName.setText(HNameR);
        f1r.setText(f1rR);
        f2r.setText(f2rR);
        f3r.setText(f3rR);
        f4r.setText(f4rR);
        f5r.setText(f5rR);
        f6r.setText(f6rR);
        f7r.setText(f7rR);
        f8r.setText(f8rR);
        f9r.setText(f9rR);

        List<Client> ClientQ = Client.where("iId = " + ClientIdR);
        Client ClientConst = ClientQ.get(0);


        String ClientNameQ = String.valueOf(ClientConst.get("Name"));
        String SurnameQ = String.valueOf(ClientConst.get("Surname"));
        String PhoneQ = String.valueOf(ClientConst.get("Phone"));
        String EmailQ = String.valueOf(ClientConst.get("Email"));

        NameBox.setText(ClientNameQ);
        SurnameBox.setText(SurnameQ);
        PhoneBox.setText(PhoneQ);
        EmailBox.setText(EmailQ);




    }
}
