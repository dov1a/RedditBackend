INSERT INTO users (username, password, email, avatar, registration_date, description, display_name, user_type)
VALUES ('dovla', 'dovla123', 'dovla123@gmail.com', NULL, '2022-05-18', 'Ja sam Vladimir', 'Vladimir Djurdjevic', 'admin');

INSERT INTO users (username, password, email, avatar, registration_date, description, display_name, user_type)
VALUES ('petrex', 'petrex123', 'petrex123@gmail.com', NULL, '2022-05-18', 'Ja sam Petar', 'Petar Petrovic', 'user');

INSERT INTO users (username, password, email, avatar, registration_date, description, display_name, user_type)
VALUES ('ogi', 'ogi123', 'ogi123@gmail.com', NULL, '2022-05-18', 'Ja sam Ognjen', 'Ognjen Lazic', 'moderator');

INSERT INTO post (title, text, creation_date, image_path, community_id, user_id, flair_id)
            VALUES('Naslov objave 1', 'Text objave 1', '2022-05-18', NULL, 1, 2, Null);

INSERT INTO rule (description, community_id)
            VALUES('Rule number 1', 1);

INSERT INTO community (name, description, creation_date, is_suspend, suspended_reason, user_id)
                VALUES('Community1', 'Description of community1', '2022-05-18', false, null, 2);


