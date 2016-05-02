package Views;

import App.Application;
import Forms.LoginForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartWindow extends JFrame {

    private JPanel panel1;

    private JButton SignInButton;

    protected LoginForm loginForm = null;

    public StartWindow() {
        setContentPane(panel1);
        pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        SignInButton.addActionListener(onSignInButtonClick);

        setVisible(true);
    }

    /**
     * Handle Sign In Button Click
     */
    protected ActionListener onSignInButtonClick = new ActionListener() {
        public void actionPerformed(ActionEvent e) {

        if (null == loginForm) {
            loginForm = new LoginForm();
            loginForm.setSuccessLoginCallback(
                new Application()
            );

            setVisible(false);
        }
        }
    };

}
