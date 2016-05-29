package Models;

import App.Auth.Auth;
import org.javalite.activejdbc.annotations.BelongsTo;


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
}
