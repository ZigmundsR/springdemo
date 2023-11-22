USE `spring_demo_zigmunds`;

SET foreign_key_checks = 0;
DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `role`;
SET FOREIGN_KEY_CHECKS = 0;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` char(80) NOT NULL,
  `enabled` tinyint NOT NULL,
  `employee_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_EMPLOYEE_idx` (`employee_id`),
  CONSTRAINT `FK_EMPLOYEE` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--
-- NOTE: The passwords are encrypted using BCrypt
--

INSERT INTO `user` (`username`,`password`,`enabled`,`employee_id`)
VALUES
('admin','$2a$12$jkandXlxFcqFtJdb8tjIKOZsKxhu7WYUGzoXCW1ziB0v8Py6mstYa',1, 1);


--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `roles`
--

INSERT INTO `role` (name)
VALUES
('ROLE_EMPLOYEE'),('ROLE_MANAGER'),('ROLE_ADMIN');

SET FOREIGN_KEY_CHECKS = 0;

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;

CREATE TABLE `users_roles` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,

  PRIMARY KEY (`user_id`,`role_id`),

  KEY `FK_ROLE_idx` (`role_id`),

  CONSTRAINT `FK_USER_05` FOREIGN KEY (`user_id`)
  REFERENCES `user` (`id`)
  ON DELETE NO ACTION ON UPDATE NO ACTION,

  CONSTRAINT `FK_ROLE` FOREIGN KEY (`role_id`)
  REFERENCES `role` (`id`)
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;

--
-- Dumping data for table `users_roles`
--

INSERT INTO `users_roles` (user_id,role_id)
VALUES
(1, 1),
(1, 2),
(1, 3)
