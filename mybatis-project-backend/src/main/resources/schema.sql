USE `CRUDAllXone`;

-- DROP TABLES

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS
    coin,
    user,
    token
;

CREATE TABLE coin
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    created_at     datetime              NULL,
    updated_at     datetime              NULL,
    user_id        BIGINT                NULL,
    name           VARCHAR(255)          NULL,
    symbol         VARCHAR(255)          NULL,
    coin_market_id VARCHAR(255)          NULL,
    quantity       BIGINT                NULL,
    CONSTRAINT pk_coin PRIMARY KEY (id)
);

CREATE TABLE user
(
    id           BIGINT AUTO_INCREMENT NOT NULL,
    name     VARCHAR(255)          NULL,
    email        VARCHAR(255)          NULL,
    password     VARCHAR(255)          NULL,
    is_active    BOOLEAN               NULL,
    gender       BOOLEAN               NULL,
    address      VARCHAR(255)          NULL,
    age          INT                   NULL,
    role         VARCHAR(255)          NULL,
    phone_number VARCHAR(255)          NULL,
    created_at   datetime              NULL,
    updated_at   datetime              NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
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

SET FOREIGN_KEY_CHECKS = 1;
