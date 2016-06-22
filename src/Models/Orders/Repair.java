package Models.Orders;


import Models.BaseModel;
import Models.User;
import org.javalite.activejdbc.annotations.BelongsTo;
import org.javalite.activejdbc.annotations.BelongsToParents;

@BelongsToParents({
    @BelongsTo(parent = User.class, foreignKeyName = "TechnicanId"),
    @BelongsTo(parent = Client.class, foreignKeyName = "ClientId")
})
public class Repair extends BaseModel
{

}
