CREATE TABLE "authors"(
    id SERIAL PRIMARY KEY,
    name TEXT,
    age INT
);
CREATE TABLE "books"(
    id SERIAL PRIMARY KEY,
    title TEXT,
    author_id INT,
    CONSTRAINT fk_author FOREIGN KEY(author_id) REFERENCES authors(id)
);