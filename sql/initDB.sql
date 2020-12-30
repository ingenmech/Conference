INSERT INTO user(ROLE, LOGIN, PASSWORD) VALUES
('ADMIN', 'amaks', SHA1('maks')),
('USER', 'umaks', SHA1('maks')),
('USER', 'uanna', SHA1('maks'));

INSERT INTO conference(NAME, DATE) VALUES
('Conference', '2021-01-20 15:00:00'),
('Web Conference', '2021-03-25 15:00:00'),
('Online Conference', '2021-01-28 15:00:00'),
('WebDev Conference', '2021-04-26 10:00:00'),
('GameDev Conference', '2021-01-26 15:00:00'),
('Financial Conference', '2021-07-21 10:00:00'),
('Legal Conference', '2021-10-15 10:00:00'),
('Engineering Conference', '2021-11-15 10:00:00'),
('Design Conference', '2021-12-17 10:00:00'),
('Flower Conference', '2021-05-19 10:00:00'),
('WebSecurity Conference', '2021-03-21 10:00:00'),
('Developer Conference', '2021-04-20 10:00:00'),
('Auto Conference', '2021-02-02 10:00:00'),
('Conference', '2021-01-03 10:00:00'),
('WebFinancial Conference', '2021-06-08 10:00:00'),
('WebOnline Conference', '2021-07-23 10:00:00'),
('WebOnlineSecurity Conference', '2021-08-28 10:00:00');

INSERT INTO section(CONFERENCE_ID, NAME) VALUES
('1', 'Section 1'),
('1', 'Section 2'),
('1', 'Section 3'),
 ('2', 'Web section 1'),
 ('2', 'Web section 2'),
 ('2', 'Web section 3'),
 ('3', 'Online section 1'),
 ('3', 'Online section 2'),
 ('3', 'Online section 3'),
 ('4', 'WebDev section 1'),
 ('4', 'WebDev section 2'),
 ('4', 'WebDev section 3'),
 ('5', 'GameDev section 1'),
 ('5', 'GameDev section 2'),
 ('5', 'GameDev section 3'),
 ('6', 'Financial section 1'),
 ('6', 'Financial section 2'),
 ('6', 'Financial section 3'),
 ('7', 'Legal section 1'),
 ('7', 'Legal section 2'),
 ('7', 'Legal section 3'),
 ('7', 'Legal section 4'),
 ('7', 'Legal section 5'),
 ('8', 'Engineering section 1'),
 ('8', 'Engineering section 2'),
 ('8', 'Engineering section 3'),
 ('8', 'Engineering section 4'),
 ('9', 'Design section 1'),
 ('9', 'Design section 2'),
 ('9', 'Design section 3'),
 ('10', 'Flower section 1'),
 ('10', 'Flower section 2'),
 ('10', 'Flower section 3'),
 ('11', 'WebSecurity section 1'),
 ('11', 'WebSecurity section 2'),
 ('11', 'WebSecurity section 3'),
 ('12', 'Developer section 1'),
 ('12', 'Developer section 2'),
 ('13', 'Auto section 1'),
 ('13', 'Auto section 2'),
 ('13', 'Auto section 3'),
 ('13', 'Auto section 4'),
 ('14', 'Conference section 1'),
 ('14', 'Conference section 2'),
 ('15', 'WebFinancial section 1'),
 ('15', 'WebFinancial section 2'),
 ('15', 'WebFinancial section 3'),
 ('16', 'WebOnline section 1'),
 ('16', 'WebOnline section 2'),
 ('16', 'WebOnline section 3'),
 ('17', 'WebOnlineSecurity section 1'),
 ('17', 'WebOnlineSecurity section 2'),
 ('17', 'WebOnlineSecurity section 3'),
 ('17', 'WebOnlineSecurity section 4');

INSERT INTO request(USER_ID, SECTION_ID, TOPIC, STATUS) VALUES
('2', '11', 'User topic 1', 'CONSIDERING'),
('3', '6', 'User topic 2', 'CONSIDERING'),
('2', '10', 'User topic 3', 'CONSIDERING'),
('3', '3', 'User topic 4', 'CONSIDERING'),
('2', '14', 'User topic 5', 'CONSIDERING');
