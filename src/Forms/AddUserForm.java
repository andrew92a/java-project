package Forms;

import Models.User;
import Models.UsersRole;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import App.Auth.Auth;
import Repository.UserRepository;

import java.util.List;

public class AddUserForm extends JFrame{
    private JPanel panel1;
    private JTextField surname;
    private JTextField name;
    private JTextField login;
    private JPasswordField password;
    private JTextField email;

    private JButton resetButton;
    private JButton addButton;
    private JComboBox role;

    AddUserForm instance;

    public AddUserForm() {

        instance = this;
        setContentPane(panel1);
        pack();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        addButton.addActionListener(afterUserFormSubmit);
        resetButton.addActionListener(onResetButtonClick);
        setTitle("Tworzenie nowego użytkownika");

        role.setModel(new UserRoleComboBoxModel());
        role.setEditable(false);
        role.setSelectedIndex(1);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        setVisible(true);
    }



    /**
     * Handle Login Button Click
     */
    protected ActionListener afterUserFormSubmit = new ActionListener() {
        public void actionPerformed(ActionEvent e) {

            if (! isValid(name)) {
                alert("Niepoprawne imie");
                return;
            }

            if (! isValid(surname)) {
                alert("Niepoprawne nazwisko");
                return;
            }

            if (! isValid(login)) {
                alert("Niepoprawny login");
                return;
            }

            if (! isValid(password)) {
                alert("Niepoprawne hasło");
                return;
            }

            if (! isValid(email)) {
                alert("Niepoprawny email");
                return;
            }

            String roleId = (new UserRoleComboBoxModel()).getSelectedRole(role.getSelectedIndex()).getId().toString();

            if (UserRepository.isEmailExists(email.getText())) {
                alert("Do tego adresu email jest juz przypisane konto.");
                return;
            }

            User newUser = new User();
            newUser.set("name", name.getText());
            newUser.set("surname", surname.getText());
            newUser.set("login",  login.getText().toLowerCase());
            newUser.set("password", Auth.getPasswordHash(password.getPassword()));
            newUser.set("email",  email.getText());
            newUser.set("role_id", roleId);

            if (newUser.saveIt()) {
                alert("Dodano użytkownika");
                instance.dispose();
            } else {
                alert("Blad przy dodaniu użytkownika");
            }
        }
    };

    protected ActionListener onResetButtonClick = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            name.setText("");
            surname.setText("");
            login.setText("");
            password.setText("");
            email.setText("");
        }
    };

    private void alert(String message) {
        JOptionPane.showMessageDialog(AddUserForm.this, message);
    }

    private Boolean isValid(JTextField filed) {
        return filed.getText().trim().length() > 0;
    }

    private class UserRoleComboBoxModel extends AbstractListModel implements ComboBoxModel
    {
        List <UsersRole> allRoles = UsersRole.findAll();
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

