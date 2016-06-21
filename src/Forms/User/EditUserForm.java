package Forms.User;

import Forms.BaseForm;
import Models.User;
import Models.UsersRole;
import Repository.UserRepository;
import Views.User.UsersList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EditUserForm extends BaseForm {

    private JPanel panel1;
    private JButton backButton;
    private JButton saveButton;
    private JTextField surname;
    private JTextField name;
    private JTextField email;
    private JComboBox role;
    private JButton deleteButton;

    private EditUserForm instance;
    private UsersList view;
    private User user;

    /**
     * Create edit user form instance
     * @param user
     * User instance to edit.
     *
     * @param viewInstance
     * View instance of User List.
     */
    public EditUserForm(User user, UsersList viewInstance) {
        this.user = user;
        instance = this;
        this.view = viewInstance;

        setContentPane(panel1);
        pack();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Edycja użytkownika");

        role.setModel(new UserRoleComboBoxModel());
        role.setEditable(false);
        role.setSelectedIndex(Integer.parseInt(user.get("role_id").toString()) - 1);
        name.setText(user.get("name").toString());
        surname.setText(user.get("surname").toString());
        email.setText(user.get("email").toString());

        saveButton.addActionListener(onSaveButtonClick);
        backButton.addActionListener(onCancelButtonClick);
        deleteButton.addActionListener(onDeleteButtonClick);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        setVisible(true);
    }

    /**
     * Handle Save Button Click - save User Entry
     */
    protected ActionListener onSaveButtonClick = new ActionListener() {
        public void actionPerformed(ActionEvent e) {

            if (! isValid(name)) {
                alert("Niepoprawne imie");
                return;
            }

            if (! isValid(surname)) {
                alert("Niepoprawne nazwisko");
                return;
            }

            if (! isValid(email)) {
                alert("Niepoprawny email");
                return;
            }

            String roleId = (new UserRoleComboBoxModel()).getSelectedRole(role.getSelectedIndex()).getId().toString();

            if (UserRepository.isEmailExists(email.getText())) {

                if(User.count("email = ? and id = ?", email.getText(), user.get("id")) == 0) {
                    alert("Do tego adresu email jest juz przypisane konto.");
                    return;
                }
            }

            user.set("name", name.getText());
            user.set("surname", surname.getText());
            user.set("email",  email.getText());
            user.set("role_id", roleId);

            if (user.saveIt()) {
                alert("Zapisano użytkownika");
                instance.view.refreshUsersList();
                instance.dispose();
            } else {
                alert("Blad przy zapisywaniu użytkownika");
            }
        }
    };

    /**
     * Handle cancel button click
     */
    protected ActionListener onCancelButtonClick  = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            instance.dispose();
        }
    };

    /**
     * Handle delete button click
     */
    protected ActionListener onDeleteButtonClick  = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            int dialogResult = JOptionPane.showConfirmDialog (
                    null,
                    "Czy na pewno chcesz usunac użytkownika?",
                    "Potwierdz swoja decyzje",
                    JOptionPane.YES_NO_OPTION);
            if(dialogResult == JOptionPane.YES_OPTION) {
                instance.user.delete();
                alert("Użytkownik został usuniety");
                instance.view.refreshUsersList();
                instance.dispose();
            }
        }
    };

    /**
     * ComboBox Model for User Role ComboBox
     */
    private class UserRoleComboBoxModel extends AbstractListModel implements ComboBoxModel
    {
        List<UsersRole> allRoles = UsersRole.findAll();
        String selection = null;

        public Object getElementAt(int index) {
            return allRoles.get(index).get("displayed");
        }
        public UsersRole getSelectedRole(int index)
        {
            return allRoles.get(index);
        }
        public int getSize() {
            return allRoles.size();
        }
        public void setSelectedItem(Object anItem) {
            selection = (String) anItem;
        }
        public Object getSelectedItem() {
            return selection;
        }
    }
}
