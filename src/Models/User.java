package Models;

import org.javalite.activejdbc.annotations.BelongsTo;

@BelongsTo(parent = UsersRole.class, foreignKeyName = "role_id")

public class User extends BaseModel {

}
