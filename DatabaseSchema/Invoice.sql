CREATE TABLE IF NOT EXISTS `invoices` (
    `id` int(10) unsigned NOT NULL PRIMARY KEY,
    `user_id` int(10) UNSIGNED NOT NULL,
    `customer_id` int(10) UNSIGNED NOT NULL,
    `number` varchar(100) NOT NULL,
    `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
    `place` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
    `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

ALTER TABLE `invoices`
 MODIFY `id` int(10) unsigned NOT NULL AUTO_INCREMENT;

ALTER TABLE `invoices` ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES `users`(id) ON DELETE CASCADE;
-- ALTER TABLE `invoices` ADD CONSTRAINT fk_customer_id FOREIGN KEY (customer_id) REFERENCES `customers`(id) ON DELETE CASCADE;