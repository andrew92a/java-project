package Models;

import App.Auth.Auth;
import Models.Orders.Repair;
import org.javalite.activejdbc.annotations.BelongsTo;

import java.util.List;

@BelongsTo(parent = UsersRole.class, foreignKeyName = "role_id")
public class User extends BaseModel {

    /**
     * Check if current user is Admin
     *
     * @return Boolean
     */
    public Boolean isAdmin()
    {
        try {
            UsersRole role = this.parent(UsersRole.class);
            return role.get("name").toString().equals(Auth.ADMIN_ROLE_NAME);

        } catch (NullPointerException e) {
            return false;
        }
    }

    public List<Repair> getRepairs()
    {
        return this.get(Repair.class, "Status IS NULL");
    }

}
