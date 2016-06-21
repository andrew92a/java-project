package Models.Orders;


import Models.BaseModel;
import Models.User;
import org.javalite.activejdbc.annotations.BelongsTo;

@BelongsTo(parent = User.class, foreignKeyName = "TechnicanId")
public class Repair extends BaseModel
{

}
