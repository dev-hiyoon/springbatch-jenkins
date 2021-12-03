```sql

-- DROP
DROP
DATABASE batch;
DROP TABLE IF EXISTS batch.people;
DROP TABLE IF EXISTS batch.payment;
DROP TABLE IF EXISTS batch.statistic;

-- CREATE
CREATE
database batch;

-- CREATE
CREATE TABLE `people`
(
    `person_id`  bigint(20) NOT NULL,
    `first_name` varchar(255) DEFAULT NULL,
    `last_name`  varchar(255) DEFAULT NULL,
    PRIMARY KEY (`person_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `payment`
(
    `date`           varchar(255) DEFAULT NULL,
    `payment_amount` bigint(20) DEFAULT NULL,
    `person_id`      bigint(20) DEFAULT NULL,
    `reg_date`       datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `statistic`
(
    `date`                 varchar(255) NOT NULL,
    `person_id`            bigint(20) DEFAULT NULL,
    `total_payment_amount` bigint(20) DEFAULT NULL,
    `total_payment_count`  bigint(20) DEFAULT NULL,
    PRIMARY KEY (`date`,`person_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


INSERT INTO people(person_id, first_name, last_name)
VALUES (0, 'Hongil', 'Yoon');
INSERT INTO people(person_id, first_name, last_name)
VALUES (1, 'Jill', 'Doe');
INSERT INTO people(person_id, first_name, last_name)
VALUES (2, 'Justin', 'Jack');
INSERT INTO people(person_id, first_name, last_name)
VALUES (3, 'Hedson', 'Tom');
INSERT INTO people(person_id, first_name, last_name)
VALUES (4, 'Chanhee', 'Choi');

INSERT INTO payment(`date`, person_id, payment_amount, reg_date)
VALUES ('20211130', 0, 1000, now());
INSERT INTO payment(`date`, person_id, payment_amount, reg_date)
VALUES ('20211130', 0, 1500, now());
INSERT INTO payment(`date`, person_id, payment_amount, reg_date)
VALUES ('20211130', 1, 500, now());

```

```shell
java -jar .\springbatch-jenkins-0.0.1-SNAPSHOT.jar date=20211203 --job.name=statisticInitBatchJob version=0.4
java -jar .\springbatch-jenkins-0.0.1-SNAPSHOT.jar date=20211203 --job.name=statisticJob version=0.4

```