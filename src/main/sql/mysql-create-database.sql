DROP TABLE Tusovka;
DROP TABLE Place;

CREATE TABLE Place (
  id INT AUTO_INCREMENT NOT NULL PRIMARY KEY ,
  name VARCHAR(200) NOT NULL ,
  address VARCHAR(200) NOT NULL ,
  description VARCHAR(5000)
);

CREATE TABLE Tusovka (
  id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  name VARCHAR(200) ,
  description VARCHAR(5000) ,
  date DATETIME ,
  price INT NOT NULL ,
  place_id INT ,
  INDEX place_ind (place_id) ,
  FOREIGN KEY (place_id)
  REFERENCES Place (id)
);

ALTER TABLE Tusovka CONVERT TO CHARACTER SET utf8 COLLATE utf8_unicode_ci;
ALTER TABLE Place CONVERT TO CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- examples of usage

INSERT INTO Place (name, address, description) VALUES ('Стёпик', 'Москва, ул. Пушкина, д. 2', 'Просто хорошее место для отдыха.');
INSERT INTO Place (name, address, description) VALUES ('Powerhouse', 'Москва, около Таганской', 'Здесь играет музыка.');

insert INTO Tusovka (name, description, date, price, place_id) VALUES ('asd', 'adad', '2005-08-09T18:31:42', 100, (SELECT id FROM Place WHERE Place.name = 'Powerhouse'));

SELECT * from Place;
SELECT * FROM Tusovka;