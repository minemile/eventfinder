
CREATE TABLE place (
  id INT AUTO_INCREMENT NOT NULL PRIMARY KEY ,
  name VARCHAR(200) NOT NULL ,
  address VARCHAR(200) NOT NULL ,
  description VARCHAR(5000)
);

CREATE TABLE tusovka (
  id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  name VARCHAR(200) ,
  description VARCHAR(5000) ,
  date DATETIME ,
  price INT NOT NULL ,
  place_id INT ,
  INDEX place_ind (place_id) ,
  FOREIGN KEY (place_id)
  REFERENCES place (id)
);

ALTER TABLE tusovka CONVERT TO CHARACTER SET utf8 COLLATE utf8_unicode_ci;
ALTER TABLE place CONVERT TO CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- examples of usage

INSERT INTO place (name, address, description) VALUES ('Стёпик', 'Москва, ул. Пушкина, д. 2', 'Просто хорошее место для отдыха.');
INSERT INTO place (name, address, description) VALUES ('Powerhouse', 'Москва, около Таганской', 'Здесь играет музыка.');

insert INTO tusovka (name, description, date, price, place_id) VALUES ('asd', 'adad', '2005-08-09T18:31:42', 100, (SELECT id FROM place WHERE place.name = 'Powerhouse'));

SELECT * from place;
SELECT * FROM tusovka;