INSERT INTO users (username, password, email, avatar, registration_date, description, display_name, roles)
VALUES ('dovla', '$2a$10$2mcfr5xM9EcXBIHPqGnTPuGFI4t/jf9Ek9Or5i5EB55vCDxlCi0OG', 'dovla123@gmail.com', NULL, '2022-05-18', 'Ja sam Vladimir', 'Vladimir Djurdjevic', 'ADMIN');

INSERT INTO users (username, password, email, avatar, registration_date, description, display_name, roles)
VALUES ('petrex', '$2a$10$qwsmCybN3dnYPHs/ximqLO1rGCRfxEx6jdfDhEAENz7n32TEaNqpe', 'petrex123@gmail.com', NULL, '2022-05-18', 'Ja sam Petar', 'Petar Petrovic', 'USER');

INSERT INTO users (username, password, email, avatar, registration_date, description, display_name, roles)
VALUES ('ogi', '$2a$10$M49QzM7qj5wXlALOJgk9c.RHdZ3J8q7C/RuNgkxleSwVzSMWEQDQq', 'ogi123@gmail.com', NULL, '2022-05-18', 'Ja sam Ognjen', 'Ognjen Lazic', 'MODERATOR');

INSERT INTO post (title, text, creation_date, image_path, community_id, user_id, flair_id)
            VALUES('Naslov objave 1', 'Text objave 1', '2022-05-18', NULL, 1, 2, Null);

INSERT INTO rule (description, community_id)
            VALUES('Rule number 1', 1);

INSERT INTO community (name, description, creation_date, is_suspend, suspended_reason, user_id)
                VALUES('Community1', 'Description of community1', '2022-05-18', false, null, 2);


