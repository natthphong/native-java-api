DROP TABLE IF EXISTS customer_info;
CREATE TABLE customer_info (
  id bigint NOT NULL AUTO_INCREMENT,
  age int DEFAULT NULL,
  email varchar(255) DEFAULT NULL,
  password varchar(255) DEFAULT NULL,
  username varchar(255) DEFAULT NULL,
  is_delete varchar(1) DEFAULT 'N',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=latin1;
