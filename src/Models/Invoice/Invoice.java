package Models.Invoice;

import Models.Orders.Client;
import Models.User;
import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.BelongsTo;
import org.javalite.activejdbc.annotations.BelongsToParents;
import org.javalite.activejdbc.annotations.Table;

@BelongsToParents({
    @BelongsTo(parent = User.class, foreignKeyName = "user_id"),
    @BelongsTo(parent = Client.class, foreignKeyName = "customer_id")
})

@Table("invoices")
public class Invoice extends Model {

}
