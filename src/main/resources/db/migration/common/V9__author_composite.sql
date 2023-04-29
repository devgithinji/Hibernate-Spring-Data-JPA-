CREATE TABLE author_composite
(
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    country VARCHAR(255),
    PRIMARY KEY (first_name, last_name)
) engine = InnoDB;