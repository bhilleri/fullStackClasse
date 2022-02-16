INSERT INTO products (productname, description, cost) VALUES
  ('iphone', 'smartphone apple', 1000),
  ('ipad', 'tablette apple', 500),
  ('imac', 'ordinateur apple', 2000);
INSERT INTO categories (categoryname) VALUES
  ('smartphone'),
  ('tablette'),
  ('ordinateur'),
  ('apple');
INSERT INTO category_product (category_id, product_id) VALUES
    (1,1),
    (2,2),
    (3,3),
    (4,1),
    (4,2),
    (4,3);