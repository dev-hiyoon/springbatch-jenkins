








```sql

-- DROP
DROP DATABASE batch;
DROP TABLE IF EXISTS batch.people;
DROP TABLE IF EXISTS batch.payment;
DROP TABLE IF EXISTS batch.statistic;

-- CREATE
CREATE database batch;

-- CREATE
CREATE TABLE batch.people
(
    person_id  BIGINT NOT NULL,
    first_name varchar(100) NULL,
    last_name  varchar(100) NULL,
    CONSTRAINT people_PK PRIMARY KEY (person_id)
) ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;

CREATE TABLE batch.payment
(
    `date`         varchar(100) NOT NULL,
    person_id      BIGINT       NOT NULL,
    payment_amount BIGINT       NOT NULL,
    reg_date       DATETIME     NOT NULL
) ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;

CREATE TABLE batch.statistic
(
    `date`               varchar(100) NOT NULL,
    person_id            BIGINT       NOT NULL,
    total_payment_amount BIGINT       NOT NULL,
    total_payment_count  BIGINT       NOT NULL
) ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;

INSERT INTO batch.people(person_id, first_name, last_name)VALUES(0, 'Hongil', 'Yoon');
INSERT INTO batch.people(person_id, first_name, last_name)VALUES(1, 'Jill', 'Doe');
INSERT INTO batch.people(person_id, first_name, last_name)VALUES(2, 'Justin', 'Jack');
INSERT INTO batch.people(person_id, first_name, last_name)VALUES(3, 'Hedson', 'Tom');
INSERT INTO batch.people(person_id, first_name, last_name)VALUES(4, 'Chanhee', 'Choi');

INSERT INTO batch.payment(`date`, person_id, payment_amount, reg_date)VALUES('20211130', 0, 1000, now());
INSERT INTO batch.payment(`date`, person_id, payment_amount, reg_date)VALUES('20211130', 0, 1500, now());
INSERT INTO batch.payment(`date`, person_id, payment_amount, reg_date)VALUES('20211130', 1, 500, now());

```