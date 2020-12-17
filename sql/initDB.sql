INSERT INTO user(ROLE, LOGIN, PASSWORD) VALUES
('ADMIN', 'amaks', SHA1('maks')),
('USER', 'umaks', SHA1('maks')),
('USER', 'uanna', SHA1('maks'));

INSERT INTO conference(NAME, DATE) VALUES
('Some Conference 1', '2021-01-20 15:00:00'),
('Some Web Conference 2', '2021-03-25 15:00:00'),
('Some Online Conference 2', '2021-01-28 15:00:00'),
('Some WebDev Conference 2', '2021-04-26 10:00:00'),
('Some GameDev Conference 2', '2021-01-26 15:00:00');

INSERT INTO section(CONFERENCE_ID, NAME) VALUES
('1', 'Some section 1'),
('1', 'Some section 2'),
('1', 'Some section 3'),
 ('2', 'Some web section 1'),
 ('2', 'Some web section 2'),
 ('2', 'Some web section 3'),
 ('3', 'Some online section 1'),
 ('3', 'Some online section 2'),
 ('3', 'Some online section 3'),
 ('4', 'Some WebDev section 1'),
 ('4', 'Some WebDev section 2'),
 ('4', 'Some WebDev section 3'),
 ('5', 'Some GameDev section 1'),
 ('5', 'Some GameDev section 2'),
 ('5', 'Some GameDev section 3');

INSERT INTO request(USER_ID, SECTION_ID, TOPIC, STATUS) VALUES
('2', '11', 'Some user topic 1', 'CONSIDERING'),
('3', '6', 'Some user topic 2', 'CONSIDERING'),
('2', '10', 'Some user topic 3', 'CONSIDERING'),
('3', '3', 'Some user topic 4', 'CONSIDERING'),
('2', '14', 'Some user topic 5', 'CONSIDERING');
