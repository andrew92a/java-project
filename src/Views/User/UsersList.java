package Views.User;

import App.Auth.Auth;
import Forms.User.AddUserForm;
import Forms.User.EditUserForm;
import Models.User;
import Repository.UserRepository;
import org.javalite.activejdbc.FrozenException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

@SuppressWarnings("ConstantConditions")
public class UsersList extends JFrame {

    private JPanel panel1;
    private JTextField searchInput;
    private JButton searchButton;
    private JTable table1;
    private JButton addEmployeeButton;
    private UserTableModel tableModel;

    private UserRepository users;

    private UsersList instance;

    public UsersList() {
        instance = this;
        this.users = new UserRepository();

        setContentPane(panel1);
        pack();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        searchButton.addActionListener(searchButtonActionListener);
        addEmployeeButton.addActionListener(addEmployeeActionListener);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        List<User> users = User.findAll();
        try {
            tableModel = new UserTableModel(users);
            table1.setModel(tableModel);
            final UsersList list = this;

            table1.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int row = table1.rowAtPoint(evt.getPoint());
                    int col = table1.columnAtPoint(evt.getPoint());
                    if (row >= 0 && col >= 0) {
                        int selected = table1.convertRowIndexToModel(row);
                        User selectedUser = list.tableModel.getSelectedUser(selected);

                        // Edycja użytkownika dostepna tylko dla ADMINA
                        if ((Auth.user() != null) && Auth.user().isAdmin()) {
                            EditUserForm editForm = new EditUserForm(selectedUser, instance);
                        } else {
                            System.out.println("User edit module is only for ADMINS");
                        }
                    }
                }
                }
            });
        } catch (FrozenException e) {
            System.out.println(e.getMessage());
        }
        setVisible(true);
    }

    public void refreshUsersList()
    {
        List<User> users = User.findAll();
        tableModel = new UserTableModel(users);
        table1.setModel(tableModel);
    }

    /**
     * Handle Search Button Click
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
     * Handle add Employee Button Click
     */
    protected ActionListener addEmployeeActionListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        AddUserForm newUserForm = new AddUserForm();
        }
    };
}
