INSERT INTO products (id, name, price) VALUES
  (RANDOM_UUID(), 'Sample Item A', 1000),
  (RANDOM_UUID(), 'Sample Item B', 2500);
INSERT INTO users (id, username, password) VALUES
  (RANDOM_UUID(), 'admin', '$2a$10$bMaksc3v64duZ9TCodJ5UeTm4mm60RSRjHgNWFSGxqBZNjDzPfc0m');