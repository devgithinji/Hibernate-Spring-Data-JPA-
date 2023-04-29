CREATE TABLE book_natural
(
    title     VARCHAR(255) NOT NULL,
    isbn      VARCHAR(255),
    publisher VARCHAR(255),
    primary key (title)
) engine = InnoDB;