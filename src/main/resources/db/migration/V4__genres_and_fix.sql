ALTER TABLE "books"
    ADD COLUMN release_date DATE;

ALTER TABLE "books_authors"
    ALTER COLUMN book_id TYPE BIGINT,
    ALTER COLUMN author_id TYPE BIGINT;

CREATE TABLE "genres"(
    id SERIAL PRIMARY KEY,
    name TEXT
);

CREATE TABLE "books_genres"(
    book_id BIGINT REFERENCES books(id),
    genre_id BIGINT REFERENCES genres(id),
    CONSTRAINT books_genres_pk PRIMARY KEY(book_id, genre_id)
);