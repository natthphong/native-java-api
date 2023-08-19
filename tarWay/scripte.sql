DROP TABLE IF EXISTS customer_info;
CREATE TABLE customer_info (
  id bigint NOT NULL AUTO_INCREMENT,
  age int DEFAULT NULL,
  email varchar(255) DEFAULT NULL,
  password varchar(255) DEFAULT NULL,
  username varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=latin1;
INSERT INTO db.customer_info
(id, age, email, password, username)
VALUES
  (1, 1, 'taza1a@hotmail.com', '$2a$10$Dl95Uvy7mWa6u653.Bu7NO8WBvg8ssRB7FqgshXAkwzBUrK8BTaSy', 'Test Tar : 1'),
  (2, 2, 'taza2a@hotmail.com', '$2a$10$rwH1KHTUoqpuHNNU33m6..j1Pr4bjvfQvlMewQD4diUHOdY1HECkW', 'Test Tar : 2'),
  (3, 3, 'taza3a@hotmail.com', '$2a$10$WhMTThw3RgrOX.kSXKHZquilYdgV6eiqXTabOBDIkOKbNyidzaCH6', 'Test Tar : 3'),
  (4, 4, 'taza4a@hotmail.com', '$2a$10$L0kYGxQWPdlXTsRVWWgFLeME.PYo13MNH1wv15PfwhubyDJAJ76.S', 'Test Tar : 4'),
  (5, 5, 'taza5a@hotmail.com', '$2a$10$DqFVEPj4HjeekX1X1T9TyugdCstmPH2hGffjPgPw26GGJPA6gX3qG', 'Test Tar : 5'),
  (6, 6, 'taza6a@hotmail.com', '$2a$10$GYwFDCrLA8NqQIE0pDMruO2W6eN6egMlFuZJocbxX58G2iGkjJHGm', 'Test Tar : 6'),
  (7, 7, 'taza7a@hotmail.com', '$2a$10$./yhHzSxs4cu5HBuxdVbnuSs3Ch0iHTTEh6JXwLiZuw6/fhWIq90i', 'Test Tar : 7'),
  (8, 8, 'taza8a@hotmail.com', '$2a$10$9rrVX3lMd1o109cqa8oSI.rAe1uo6fVbaZhPhsWvaTDSfrSU7Xgh.', 'Test Tar : 8'),
  (9, 9, 'taza9a@hotmail.com', '$2a$10$1ik/MlkCrNdTY2eT/D7PkeOJaV.zMZHxMjv6r5VFaxxMN.dtGFP56', 'Test Tar : 9'),
  (10, 10, 'taza10a@hotmail.com', '$2a$10$.XTEcMUMe6q2F42SKjKjO.eBQrJATshsuVs2qRt3LK2B1DPudeqyO', 'Test Tar : 10'),
  (11, 11, 'taza11a@hotmail.com', '$2a$10$yxL.UwgZTgQ8pA/rD/Ea3OoYOvhdSiUVaUU/SlD.CC/lH7.G6SHci', 'Test Tar : 11'),
  (12, 12, 'taza12a@hotmail.com', '$2a$10$tcgK2gpASvf0eQ9AceCab.jIFC0hIRnb8m5APP0ZAFkYVnLTgQQN.', 'Test Tar : 12'),
  (13, 13, 'taza13a@hotmail.com', '$2a$10$J3nTcqsqqqOwphXBMtoKcO6LbaTGFnRhE/tUTy8fYcjxYvCZJ3bXC', 'Test Tar : 13'),
  (14, 14, 'taza14a@hotmail.com', '$2a$10$BnKm9mJN4okophoTh4Nr/eTg6ft5Cr0Po.G3GWwFszlqaGHD0iP7u', 'Test Tar : 14'),
  (15, 15, 'taza15a@hotmail.com', '$2a$10$/WcdSsWGJ5XwTbuBqgBX2uH/KLUPshGjSGNjKiA0kNFbcJYM6Jldu', 'Test Tar : 15'),
  (16, 16, 'taza16a@hotmail.com', '$2a$10$r94aDKC9kLseyjszHgo7z.aauQpNue7.LSlbLyZdkkrOW8oqNLeWa', 'Test Tar : 16'),
  (17, 17, 'taza17a@hotmail.com', '$2a$10$ZNQavL6nd2Z6mEGyJAHPJOEGeI9l2LzvYOL.P.DlOg/mOhx56OYxO', 'Test Tar : 17'),
  (18, 18, 'taza18a@hotmail.com', '$2a$10$43JQQyxGJ45b/981uhkf9OPLk6s6d/Zg1lGNF9zaEdAM/f/w/7udS', 'Test Tar : 18'),
  (19, 19, 'taza19a@hotmail.com', '$2a$10$AkZcFE4KdZvAFt/jhYZ5Ru7lgobKHAdRQezDObQFFq.omDIOyMqCu', 'Test Tar : 19'),
  (20, 20, 'taza20a@hotmail.com', '$2a$10$p4rlqroa.8ui.21dU4es2OGIZlAiz.F/FPbmDRy/k5HToXoHTW9p6', 'Test Tar : 20'),
  (101, 0, '', '', '');
