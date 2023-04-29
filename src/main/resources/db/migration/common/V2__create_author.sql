CREATE TABLE author
(
    id         BIGINT       NOT NULL,
    first_name VARCHAR(255) NULL,
    last_name  VARCHAR(255) NULL,
    CONSTRAINT pk_author PRIMARY KEY (id)
);

create table author_seq (
    next_val bigint
) engine=InnoDB;
