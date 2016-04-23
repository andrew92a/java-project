package Views.User;

import Models.User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import Repository.UserRepository;
import Forms.AddUserForm;

public class UsersList extends JFrame {

    private JPanel panel1;
    private JTextField searchInput;
    private JButton searchButton;
    private JTable table1;
    private JButton addEmployeeButton;

    private UserRepository users;


    public UsersList() {

        this.users = new UserRepository();

        setContentPane(panel1);
        pack();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        searchButton.addActionListener(searchButtonActionListener);
        addEmployeeButton.addActionListener(addEmployeeActionListener);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        List<User> users = User.findAll();

        UserTableModel u = new UserTableModel(users);
        this.table1.setModel(u);

        setVisible(true);

    }


    private List<User> getLimitUser() {
        return User.findAll().limit(10);
    }

    /**
     * Handle Login Button Click
     */
    protected ActionListener searchButtonActionListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {

            List<User> searchResult;
            String search = searchInput.getText();

            if (null != search && search.trim().length() > 0) {
                searchResult = users.search(search);
            } else {
                searchResult = users.all();
            }

            UserTableModel u = new UserTableModel(searchResult);
            table1.setModel(u);
        }
    };

    /**
     * Handle Login Button Click
     */
    protected ActionListener addEmployeeActionListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            AddUserForm newUserForm = new AddUserForm();
        }
    };
}
