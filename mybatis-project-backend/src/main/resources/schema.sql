USE CRUDAllXone;

-- DROP TABLES

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS
    coin,
    user,
    token,
    role,
    user_role,
    tax_information,
    employee,
    payroll,
    time_tracking,
    department
;

CREATE TABLE coin
(
    id             BIGINT NOT NULL,
    created_at     datetime              NULL,
    updated_at     datetime              NULL,
    user_id        BIGINT                NULL,
    name           VARCHAR(255)          NULL,
    symbol         VARCHAR(255)          NULL,
    coin_market_id VARCHAR(255)          NULL,
    quantity       BIGINT                NULL
);

CREATE TABLE user
(
    id           BIGINT NOT NULL,
    name         VARCHAR(255)          NULL,
    email        VARCHAR(255)          NULL,
    password     VARCHAR(255)          NULL,
    is_active    BOOLEAN               NULL,
    gender       BOOLEAN               NULL,
    address      VARCHAR(255)          NULL,
    age          INT                   NULL,
    phone_number VARCHAR(255)          NULL,
    created_at   datetime              NULL,
    updated_at   datetime              NULL
);

CREATE TABLE token
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    token      VARCHAR(255)          NULL,
    token_type VARCHAR(255)          NULL,
    revoked    TINYINT(1)            NULL,
    expired    TINYINT(1)            NULL,
    user_id    BIGINT                NULL,
    CONSTRAINT pk_token PRIMARY KEY (id)
)
;

CREATE TABLE role
(
    id   INT NOT NULL,
    name VARCHAR(255)
);

CREATE TABLE user_role
(
    user_id BIGINT,
    role_id INT
);

CREATE TABLE department
(
    id   int         NOT NULL,
    name varchar(50) NOT NULL
);

CREATE TABLE employee
(
    id               bigint      NOT NULL,
    user_id          bigint      NOT NULL,
    first_name       varchar(50) NOT NULL,
    last_name        varchar(50) NOT NULL,
    birthday         date             DEFAULT NULL,
    gender           varchar(10)      DEFAULT NULL,
    contact_number   varchar(15)      DEFAULT NULL,
    email            varchar(100)     DEFAULT NULL,
    department_id    int         NOT NULL,
    position         varchar(50)      DEFAULT NULL,
    hire_date        date             DEFAULT NULL,
    termination_date date             DEFAULT NULL,
    created_at       timestamp   NULL DEFAULT NULL,
    updated_at       timestamp   NULL DEFAULT NULL
);

CREATE TABLE payroll
(
    id           bigint         NOT NULL,
    employee_id  bigint         NOT NULL,
    salary       decimal(10, 2) NOT NULL,
    bonus        decimal(10, 2)      DEFAULT NULL,
    deductions   decimal(10, 2)      DEFAULT NULL,
    net_salary   decimal(10, 2)      DEFAULT NULL,
    period_start date                DEFAULT NULL,
    period_end   date                DEFAULT NULL,
    created_at   timestamp      NULL DEFAULT NULL,
    updated_at   timestamp      NULL DEFAULT NULL
);

CREATE TABLE tax_information
(
    id            int           NOT NULL,
    employee_id   bigint        NOT NULL,
    tax_rate      decimal(5, 2) NOT NULL,
    tax_exemption bit(1)             DEFAULT NULL,
    created_at    timestamp     NULL DEFAULT NULL,
    updated_at    timestamp     NULL DEFAULT NULL
);

CREATE TABLE time_tracking
(
    id          bigint    NOT NULL,
    employee_id bigint    NOT NULL,
    date_track  date      NOT NULL,
    clock_in    time      NOT NULL,
    clock_out   time      NOT NULL,
    total_hours decimal(5, 2)  DEFAULT NULL,
    created_at  timestamp NULL DEFAULT NULL,
    updated_at  timestamp NULL DEFAULT NULL
);

ALTER TABLE coin
    ADD PRIMARY KEY (id),
    ADD KEY FK_userCoin (user_id);

ALTER TABLE department
    ADD PRIMARY KEY (id);

ALTER TABLE employee
    ADD PRIMARY KEY (id),
    ADD KEY department_id (department_id),
    ADD KEY user_id (user_id);

ALTER TABLE payroll
    ADD PRIMARY KEY (id),
    ADD KEY employee_id (employee_id);

ALTER TABLE role
    ADD PRIMARY KEY (id);

ALTER TABLE tax_information
    ADD PRIMARY KEY (id),
    ADD KEY employee_id (employee_id);

ALTER TABLE time_tracking
    ADD PRIMARY KEY (id),
    ADD KEY employee_id (employee_id);

ALTER TABLE user
    ADD PRIMARY KEY (id);

ALTER TABLE user_role
    ADD KEY FK_user (user_id),
    ADD KEY FK_role (role_id);

ALTER TABLE coin
    MODIFY id bigint NOT NULL AUTO_INCREMENT;

ALTER TABLE department
    MODIFY id int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

ALTER TABLE employee
    MODIFY id bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

ALTER TABLE payroll
    MODIFY id bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

ALTER TABLE role
    MODIFY id int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

ALTER TABLE tax_information
    MODIFY id int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

ALTER TABLE time_tracking
    MODIFY id bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

ALTER TABLE user
    MODIFY id bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

ALTER TABLE coin
    ADD CONSTRAINT FK_userCoin FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE employee
    ADD CONSTRAINT employees_ibfk_1 FOREIGN KEY (department_id) REFERENCES department (id) ON DELETE RESTRICT ON UPDATE RESTRICT,
    ADD CONSTRAINT employees_ibfk_2 FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE payroll
    ADD CONSTRAINT payroll_ibfk_1 FOREIGN KEY (employee_id) REFERENCES employee (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE tax_information
    ADD CONSTRAINT tax_information_ibfk_1 FOREIGN KEY (employee_id) REFERENCES employee (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE time_tracking
    ADD CONSTRAINT time_tracking_ibfk_1 FOREIGN KEY (employee_id) REFERENCES employee (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE user_role
    ADD CONSTRAINT FK_role FOREIGN KEY (role_id) REFERENCES role (id) ON DELETE RESTRICT ON UPDATE RESTRICT,
    ADD CONSTRAINT FK_user FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE RESTRICT ON UPDATE RESTRICT;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
