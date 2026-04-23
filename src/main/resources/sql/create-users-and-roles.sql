USE `clinic_db`;

SET foreign_key_checks = 0;
DROP TABLE IF EXISTS `users_roles`;
DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `role`;
DROP TABLE IF EXISTS `patient`;
SET foreign_key_checks = 1;

--
-- Table structure for table `user`
--
CREATE TABLE `user` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `username` varchar(50) NOT NULL,
                        `password` char(80) NOT NULL,
                        `enabled` tinyint NOT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

-- password: test123
INSERT INTO `user` (`username`,`password`,`enabled`)
VALUES
    ('john', '$2a$10$ZyW33UAIxK3JOv1.ZorF6OZj0/.YCCGWfw422I0E09CeQ5uABCvqi', 1),
    ('mary', '$2a$10$ZyW33UAIxK3JOv1.ZorF6OZj0/.YCCGWfw422I0E09CeQ5uABCvqi', 1),
    ('susan', '$2a$10$ZyW33UAIxK3JOv1.ZorF6OZj0/.YCCGWfw422I0E09CeQ5uABCvqi', 1);

--
-- Table structure for table `role`
--
CREATE TABLE `role` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `name` varchar(50) DEFAULT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `roles`
--
INSERT INTO `role` (name)
VALUES
    ('ROLE_NURSE'),('ROLE_DOCTOR'),('ROLE_ADMIN');

--
-- Table structure for table `users_roles`
--
CREATE TABLE `users_roles` (
                               `user_id` int NOT NULL,
                               `role_id` int NOT NULL,

                               PRIMARY KEY (`user_id`,`role_id`),
                               KEY `FK_ROLE_idx` (`role_id`),

                               CONSTRAINT `FK_USER_05` FOREIGN KEY (`user_id`)
                                   REFERENCES `user` (`id`)
                                   ON DELETE NO ACTION ON UPDATE NO ACTION,

                               CONSTRAINT `FK_ROLE` FOREIGN KEY (`role_id`)
                                   REFERENCES `role` (`id`)
                                   ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users_roles`
--
INSERT INTO `users_roles` (user_id,role_id)
VALUES
    (1, 1),
    (2, 1),
    (2, 2),
    (3, 1),
    (3, 2),
    (3, 3);

--
-- Table structure for table `patient`
--
CREATE TABLE `patient` (
                           `id` int NOT NULL AUTO_INCREMENT,
                           `first_name` varchar(50) DEFAULT NULL,
                           `last_name` varchar(50) DEFAULT NULL,
                           `disease` varchar(100) DEFAULT NULL,
                           `room` int DEFAULT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `patient`
--
INSERT INTO `patient` (first_name, last_name, disease, room)
VALUES
    ('Mario', 'Large', 'headache', 2),
    ('Anna', 'Kowalski', 'flu', 5),
    ('John', 'Smith', 'cold', 1),
    ('Olena', 'Petrenko', 'fever', 3),
    ('Carlos', 'Mendez', 'migraine', 4),
    ('Sophie', 'Dubois', 'allergy', 6),
    ('Liam', 'O.Connor', 'back pain', 7),
    ('Marta', 'Nowak', 'asthma', 8),
    ('Yuki', 'Tanaka', 'stomach ache', 9),
    ('David', 'Brown', 'diabetes', 10);