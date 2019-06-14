CREATE TABLE contact
(
    id SERIAL PRIMARY KEY,
    first_name VARCHAR,
    last_name VARCHAR,
    email VARCHAR
);

insert INTO contact (first_name, last_name, email) VALUES ('Bent', 'Solheim', 'BentAndre.Solheim@skatteetaten.no');