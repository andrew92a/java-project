package App.Auth;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import Models.User;
import Repository.UserRepository;

public class Auth {

    /**
     * Handle logged user instance
     */
    private static User logged;

    /**
     * Hashes password for storage in database
     * @param pass
     * Password to be hashed.
     *
     * @return String hash
     */
    public static String getPasswordHash(char[] pass)
    {
        String passwordToHash = String.valueOf(pass);
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(passwordToHash.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    /**
     * Try to login user, if user exists and credentials is ok,
     * assign user instance to logged property.
     *
     * @param login
     * User login
     * @param password
     * User password
     * @return Boolean
     */
    public static Boolean tryLogin(String login, char[] password)
    {
        String hash = getPasswordHash(password);

        User logged =  UserRepository.tryLogin(login, hash);

        if (logged != null) {
            Auth.logged = logged;
            return true;
        }

        return false;
    }

    /**
     * Destroy login session instance, logout logged user
     */
    public static void logout()
    {
        Auth.logged = null;
    }

    /**
     * Return logged user instance, if no user is logged returns null.
     *
     * @return User | null
     */
    public static User user() {

        if (Auth.logged != null) {
            return Auth.logged;
        }

        return null;
    }
}
