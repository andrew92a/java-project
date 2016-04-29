package Forms;

import Models.User;
import Views.User.UserTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class AddUserForm extends JFrame{
    private JPanel panel1;
    private JTextField surname;
    private JTextField name;
    private JButton resetButton;
    private JButton dodajButton;

    AddUserForm instance;

    public AddUserForm() {

        instance = this;
        setContentPane(panel1);
        pack();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dodajButton.addActionListener(afterUserFormSubmit);
        setTitle("Tworzenie nowego użytkownika");


        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        setVisible(true);
    }



    /**
     * Handle Login Button Click
     */
    protected ActionListener afterUserFormSubmit = new ActionListener() {
        public void actionPerformed(ActionEvent e) {

            Boolean errors = false;
            String userName = "";
            String userSurname = "";

            if (isValid(name)) {
                userName = name.getText();
            } else {
                alert("Niepoprawne imie");
                errors = true;
                return;
            }

            if (isValid(surname)) {
                userSurname = surname.getText();
            } else {
                alert("Niepoprawne nazwisko");
                errors = true;
            }

            if (! errors) {
                User newUser = new User();
                newUser.set("name", userName);
                newUser.set("surname", userSurname);
                newUser.set("login", userName.toLowerCase());

                if (newUser.saveIt()) {
                    alert("Dodano użytkownika");
                    instance.dispose();
                } else {
                    alert("Blad przy dodaniu użytkownika");
                }
            }
        }
    };

    private void alert(String message) {
        JOptionPane.showMessageDialog(AddUserForm.this, message);
    }

    private Boolean isValid(JTextField filed) {
        return filed.getText().trim().length() > 0;
    }

}
