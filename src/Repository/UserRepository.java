package Repository;

import Models.User;
import java.util.List;
import java.util.NoSuchElementException;

public class UserRepository extends BaseRepository {

    public List<User> all() {
        return User.findAll();
    }

    public List<User> search(String search) {
        search = '%' + search + '%';
        return User.where("name LIKE ?", search).orderBy("name asc");
    }

    public static User tryLogin(String login, String pass) {
        try {
            List <User> u = User.where("(login = ? || email = ?) AND password = ?", login,login, pass).limit(1);
            return u.iterator().next();
        } catch (NoSuchElementException ex) {
            return null;
        }
    }

    public static Boolean isEmailExists(String email) {

       return User.count("email = ?", email) > 0;
    }
}
