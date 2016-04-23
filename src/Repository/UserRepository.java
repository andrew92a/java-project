package Repository;

import Models.User;
import java.util.List;

public class UserRepository extends BaseRepository {

    public List<User> all() {
        return User.findAll();
    }

    public List<User> search(String search) {
        search = '%' + search + '%';
        return User.where("name LIKE ?", search).orderBy("name asc");
    }

    public User tryLogin(String login, String pass) {

        // TODO:: ADD password field here and hash them
        List <User> u = User.find("WHERE name = ? || email = ? AND surname = ?", login,login, pass).limit(1);
        return u.iterator().next();
    }
}
