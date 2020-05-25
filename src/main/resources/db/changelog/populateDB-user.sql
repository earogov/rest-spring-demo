INSERT INTO users VALUES (
'1',
'admin',
'{bcrypt}$2a$10$T8y5atzjwrUNfTwvw4.ugOsIqIb3UZncogi8vp5c5RcgQm/8EOB4O',
'',
'',
'admin@server.com',
'ROLE_ADMIN,ROLE_GUEST');

INSERT INTO users VALUES (
'2',
'guest',
'{bcrypt}$2a$10$DioCIo6RqkrAc6FAAnJ27eOQwDQk2a2GPL4RFECtFSPgCT/hP4jd2',
'',
'',
'guest@server.com',
'ROLE_GUEST');