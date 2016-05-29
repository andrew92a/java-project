package Forms;

import javax.swing.*;

abstract public class BaseForm extends JFrame {

    protected void alert(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    protected Boolean isValid(JTextField filed) {
        return filed.getText().trim().length() > 0;
    }

}
