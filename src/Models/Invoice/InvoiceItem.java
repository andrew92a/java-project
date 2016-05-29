package Models.Invoice;

import Models.Store.Item;
import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.BelongsTo;
import org.javalite.activejdbc.annotations.BelongsToParents;
import org.javalite.activejdbc.annotations.Table;

@BelongsToParents({
    @BelongsTo(parent = Invoice.class, foreignKeyName = "invoice_id"),
    @BelongsTo(parent = Item.class, foreignKeyName = "item_id")
})

@Table("invoices_items")
public class InvoiceItem extends Model {

}
