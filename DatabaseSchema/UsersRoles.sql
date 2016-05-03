
CREATE TABLE IF NOT EXISTS `users_roles` (
  `id` int(10) UNSIGNED NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `displayed` varchar(255) COLLATE utf8_unicode_ci
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO `users_roles` (`id`, `name`) VALUES
(1, 'user', 'Użytkownik'),
(2, 'employee', 'Pracownik'),
(3, 'admin', 'Administrator');

ALTER TABLE `users_roles`
  ADD PRIMARY KEY (`id`);