package Models.Store;

import Models.BaseModel;
import org.javalite.activejdbc.annotations.BelongsTo;
import org.javalite.activejdbc.annotations.Table;

@BelongsTo(parent = ItemsCategory.class, foreignKeyName = "parent_id")
@Table("store_items_categories")
public class ItemsCategory extends BaseModel {

}
