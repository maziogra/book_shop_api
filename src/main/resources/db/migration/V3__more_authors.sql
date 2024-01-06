ALTER TABLE "books"
    DROP CONSTRAINT fk_author,
    DROP COLUMN author_id;

CREATE TABLE "books_authors"(
    book_id BIGINT REFERENCES books(id),
    author_id BIGINT REFERENCES authors(id),
    CONSTRAINT books_authors_pk PRIMARY KEY(book_id, author_id)
);