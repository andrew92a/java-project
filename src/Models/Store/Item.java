package Models.Store;

import Models.BaseModel;
import org.javalite.activejdbc.annotations.BelongsTo;
import org.javalite.activejdbc.annotations.Table;

@BelongsTo(parent = ItemsCategory.class, foreignKeyName = "category_id")
@Table("store_items")

public class Item extends BaseModel {


}
