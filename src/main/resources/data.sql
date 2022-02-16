DROP TABLE IF EXISTS products;

CREATE TABLE products (
  id BIGINT AUTO_INCREMENT  PRIMARY KEY,
  productname VARCHAR(250) NOT NULL,
  description VARCHAR(250) NOT NULL,
  cost INT(250) NOT NULL
);

INSERT INTO products (productname, description, cost) VALUES
  ('iphone', 'smartphone apple', 1000),
  ('ipad', 'tablette apple', 500),
  ('imac', 'ordinateur apple', 2000)