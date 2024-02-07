USE `CRUDAllXone`;

-- DROP TABLES

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS
    coin
;

CREATE TABLE coin
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    created_at     datetime              NULL,
    updated_at     datetime              NULL,
    user_id        VARCHAR(255)          NULL,
    name           VARCHAR(255)          NULL,
    symbol         VARCHAR(255)          NULL,
    coin_market_id VARCHAR(255)          NULL,
    quantity       BIGINT                NULL,
    CONSTRAINT pk_address PRIMARY KEY (id)
);
;

SET FOREIGN_KEY_CHECKS = 1;
