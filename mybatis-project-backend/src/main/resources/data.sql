-- insert user
INSERT INTO CRUDAllXone.user (id, name, email, password, is_active, gender, address, age, phone_number,
                              created_at, updated_at)
VALUES (1, 'bui quang', 'kyntps24779@fpt.edu.vn', '$2a$10$hYpOHzJwBl9kN1NoweoUE.yJPM0hPTY2CrEOVtjN9wMTX3EZZH19K', 1, 1,
        'address', 23, '0123456789', '2024-02-15 08:37:40', '2024-02-15 08:37:40');

INSERT INTO CRUDAllXone.user (id, name, email, password, is_active, gender, address, age, phone_number,
                              created_at, updated_at)
VALUES (2, 'bui quang', 'nguyentriky0604@gmail.com', '$2a$10$hYpOHzJwBl9kN1NoweoUE.yJPM0hPTY2CrEOVtjN9wMTX3EZZH19K', 1,
        1, 'address', 23, '0123456789', '2024-02-15 08:37:40', '2024-02-15 08:37:40');

-- insert coin
INSERT INTO CRUDAllXone.coin (id, created_at, updated_at, user_id, name, symbol, coin_market_id, quantity)
VALUES (1, '2024-02-15 09:13:30', '2024-02-15 09:13:30', 1, 'Bitcoin', 'BTC', '5615165asada165', 1);

-- insert role
INSERT INTO CRUDAllXone.role (id, name)
VALUES (1, "ADMIN");
INSERT INTO CRUDAllXone.role (id, name)
VALUES (2, "USER");

-- insert user_role
INSERT INTO CRUDAllXone.user_role (user_id, role_id)
VALUES (1, 1);
INSERT INTO CRUDAllXone.user_role (user_id, role_id)
VALUES (2, 2);
-- department
INSERT INTO department (id, name)
VALUES (1, 'Customer Services'),
       (2, 'Sales'),
       (3, 'Marketing');
--employee
INSERT INTO employee (id, user_id, first_name, last_name, birthday, gender, contact_number, email,
                         department_id, position, hire_date, termination_date, leave_paid_days,
                         insurance_ids, created_at, updated_at)
VALUES (1, 1, 'A', 'Nguyen', '1970-01-01', 'Male', '1234567890', 'a@company.com', 1, 'Call person', '2024-01-01', NULL,
        0, 'null', '2024-02-21 07:40:08', '2024-02-28 02:29:11'),
       (2, 1, 'B', 'Nguyen', '1970-01-01', 'FeMale', '1335604979', 'b@company.com', 2, 'Negotiator', '2024-02-01', NULL,
        0, 'null', '2024-02-21 07:41:49', '2024-02-28 02:28:50'),
       (3, 1, 'C', 'Nguyen', '2020-06-09', 'Male', '3564313500', 'c@company.com', 2, 'check c', '2024-02-28', NULL, 0,
        '[1,2,3]', '2024-02-28 02:28:17', NULL);

-- holidays

INSERT INTO holiday (id, holiday_name, holiday_description, duration_days)
VALUES (1, 'Tet Holidays',
        'The Vietnamese New Year, known as Tet holiday, stands as the most significant festival in Vietnam among other Vietnam holidays, a nationwide celebration marking the dawn of the new year in the traditional lunar calendar.',
        7),
       (2, 'New Year', 'First day of the year.', 1),
       (3, 'Independence Day', 'A day celebrating the anniversary of national independence.', 1);
--insurance_type
INSERT INTO insurance_type (id, insurance_name, insurance_description, insurance_rate)
VALUES (1, 'Unemployment Insurance',
        'A type of insurance policy (= contract) that people pay into, so that they can receive money if they lose their job',
        1.00),
       (2, 'Social Insurance',
        'As with Vietnamese employees, the mandatory social insurance scheme for foreign employees covers sickness, maternity, occupational diseases, accidents, retirement, and death.',
        8.00),
       (3, 'Health Insurance', 'Insurance for healthcare', 4.50);


-- tax-information
INSERT INTO tax_information (id, employee_id, tax_rate, tax_exemption, created_at, updated_at)
VALUES (1, 1, 0.00, b'1', '2024-02-21 07:47:28', '2024-02-21 07:47:28');
INSERT INTO tax_information (id, employee_id, tax_rate, tax_exemption, created_at, updated_at)
VALUES (2, 2, 0.00, b'1', '2024-02-21 07:47:28', '2024-02-21 07:47:28');
--payroll
INSERT INTO payroll (id, employee_id, salary, bonus, deductions, net_salary, leave_paid_days, holiday_ids, period_start,
                     period_end, created_at, updated_at)
VALUES (1, 1, 15.00, NULL, NULL, 450.00, 0, '[1, 2]', '2024-01-01', '2024-01-31', '2024-02-21 07:44:40',
        '2024-02-21 07:44:40');
INSERT INTO payroll (id, employee_id, salary, bonus, deductions, net_salary, leave_paid_days, holiday_ids, period_start,
                     period_end, created_at, updated_at)
VALUES (2, 2, 15.00, NULL, NULL, 0, 0, null, '2024-01-01', '2024-01-31', '2024-02-21 07:44:40',
        '2024-02-21 07:44:40');
--time_tracking
INSERT INTO time_tracking (id, employee_id, date_track, clock_in, clock_out, total_hours, created_at, updated_at)
VALUES (1, 1, '2024-01-03', '09:00:00', '17:00:00', 8.00, '2024-02-21 07:46:05', '2024-02-21 07:46:05'),
       (2, 1, '2024-01-05', '09:00:00', '17:00:00', 8.00, '2024-02-21 07:46:16', '2024-02-21 07:46:16'),
       (3, 1, '2024-01-08', '09:00:00', '17:00:00', 8.00, '2024-02-21 07:46:27', '2024-02-21 07:46:27'),
       (4, 1, '2024-01-10', '09:00:00', '17:00:00', 8.00, '2024-02-21 07:46:34', '2024-02-21 07:46:34'),
       (5, 1, '2024-01-11', '09:00:00', '17:00:00', 8.00, '2024-02-21 07:46:41', '2024-02-21 07:46:41');



