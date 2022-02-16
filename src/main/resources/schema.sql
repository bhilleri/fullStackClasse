DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS category_product;
CREATE TABLE products (
  id BIGINT AUTO_INCREMENT  PRIMARY KEY,
  productname VARCHAR(250) NOT NULL,
  description VARCHAR(250) NOT NULL,
  cost INT(250) NOT NULL
);
CREATE TABLE categories (
  category_id BIGINT AUTO_INCREMENT  PRIMARY KEY,
  categoryname VARCHAR(250) NOT NULL
);
CREATE TABLE category_product (
    category_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL
);