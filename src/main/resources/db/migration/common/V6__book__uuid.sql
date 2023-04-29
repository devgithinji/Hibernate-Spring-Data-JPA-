CREATE TABLE book_uuid
(
    id        BINARY(16) NOT NULL,
    isbn      VARCHAR(255),
    publisher VARCHAR(255),
    title     VARCHAR(255),
    PRIMARY KEY (id)
) engine = InnoDB;