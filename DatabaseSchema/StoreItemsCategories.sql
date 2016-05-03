
CREATE TABLE IF NOT EXISTS `store_items_categories` (
  `id` int(10) UNSIGNED NOT NULL,
  `parent_id` int(10) UNSIGNED DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


ALTER TABLE `store_items_categories`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `store_items_categories`
 MODIFY `id` int(10) unsigned NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;

ALTER TABLE `store_items_categories` ADD CONSTRAINT fk_parent_id FOREIGN KEY (parent_id) REFERENCES `store_items_categories`(id);