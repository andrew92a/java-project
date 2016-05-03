package Forms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import App.Auth.Auth;

public class LoginForm extends JFrame {

    /**
     * Login form title
     */
    private static final String TITLE = "Welcome, login to get started...";

    /**
     * Instance of LoginForm
     */
    private LoginForm instance;

    /**
     * Panel wrapper
     */
    private JPanel panelWrapper;

    /**
     * Login Button
     */
    private JButton loginButton;

    /**
     * Exit Button
     */
    private JButton exitButton;

    /**
     * Login Input Text Field
     */
    private JTextField loginInput;

    /**
     * Password Input Field
     */
    private JPasswordField passwordInput;

    /**
     * Success Login Callback
     */
    private Runnable onSuccessLogin;

    /**
     * Handle Login Button Click
     */
    protected ActionListener LoginButtonActionListener = new ActionListener() {

        public void actionPerformed(ActionEvent e) {

            String login = loginInput.getText();
            char[] pass = passwordInput.getPassword();

            if (Auth.tryLogin(login, pass)) {

                if (onSuccessLogin != null) {
                    onSuccessLogin.run();
                }
                instance.dispose();

            } else {
                JOptionPane.showMessageDialog(LoginForm.this, "Bad credentials");
            }
        }
    };

    /**
     * Handle Exit Button Click
     */
    protected ActionListener ExitButtonActionListener = new ActionListener() {

        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    };

    /**
     * Create Form instance
     */
    public LoginForm() {

        instance = this;
        setContentPane(panelWrapper);
        pack();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        loginButton.addActionListener(LoginButtonActionListener);
        exitButton.addActionListener(ExitButtonActionListener);
        setTitle(LoginForm.TITLE);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        setVisible(true);
    }

    /**
     * Set success login callback
     *
     * @param callback
     * Callback object witch implements Runnable interface
     *
     * @return this
     */
    public LoginForm setSuccessLoginCallback(Runnable callback) {
        this.onSuccessLogin = callback;

        /*
        if (ENVIRONMENT.equals(DEV)) {
            if (onSuccessLogin != null) {
                onSuccessLogin.run();
                instance.dispose();
                return null;
            }
        }
        */

        return this;
    }
}
