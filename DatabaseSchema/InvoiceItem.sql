CREATE TABLE IF NOT EXISTS `invoices_items` (
    `id` int(10) unsigned NOT NULL,
    `invoice_id` int(10) UNSIGNED NOT NULL,
    `item_id` int(10) UNSIGNED NOT NULL,
    `price` float(20,2) NOT NULL,
    `vat` int(3) COLLATE utf8_unicode_ci NOT NULL,
    `quantity` int(10) COLLATE utf8_unicode_ci NOT NULL DEFAULT 1,
    `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

ALTER TABLE `invoices_items`
 MODIFY `id` int(10) unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY;

ALTER TABLE `invoices_items` ADD CONSTRAINT fk_invoice_id FOREIGN KEY (invoice_id) REFERENCES `invoices`(id) ON DELETE CASCADE;
-- ALTER TABLE `invoices_items` ADD CONSTRAINT fk_item_id FOREIGN KEY (item_id) REFERENCES `store_items`(id) ON DELETE CASCADE;