-- insert user
INSERT INTO CRUDAllXone.user (id, firstname, lastname, email, password, is_active, gender, address, age, role, phone_number, created_at, updated_at)
VALUES (1, 'huu', 'bui quang', 'kyntps24779@fpt.edu.vn', '$2a$10$hYpOHzJwBl9kN1NoweoUE.yJPM0hPTY2CrEOVtjN9wMTX3EZZH19K', 1, 1, 'address', 23, 'ADMIN', '0123456789', '2024-02-15 08:37:40', '2024-02-15 08:37:40');

-- insert coin
INSERT INTO CRUDAllXone.coin (id, created_at, updated_at, user_id, name, symbol, coin_market_id, quantity) VALUES (1, '2024-02-15 09:13:30', '2024-02-15 09:13:30', 1, 'Bitcoin', 'BTC', '5615165asada165', 1);
