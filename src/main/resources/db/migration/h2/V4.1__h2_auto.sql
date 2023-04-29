DROP TABLE IF EXISTS book;
DROP TABLE IF EXISTS author;

CREATE TABLE book
(
    id        IDENTITY NOT NULL,
    isbn      VARCHAR(255),
    title     VARCHAR(255),
    author_id BIGINT,
    PRIMARY KEY (id)
);

CREATE TABLE author
(
    id         IDENTITY NOT NULL,
    first_name VARCHAR(255),
    last_name  VARCHAR(255),
    PRIMARY KEY (id)
)