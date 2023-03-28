CREATE TABLE IF NOT EXISTS client (
  name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  PRIMARY KEY (email)
);

CREATE TABLE IF NOT EXISTS position (
  id BIGINT NOT NULL AUTO_INCREMENT,
  title VARCHAR(255) NOT NULL,
  location VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);

INSERT INTO position (title, location)
VALUES ('Java Developer', 'Budapest'),
       ('Backend Java Developer', 'New York'),
       ('Frontend Developer', 'London');