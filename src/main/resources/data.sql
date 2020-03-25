
INSERT INTO PROFILE(description) VALUES('admin');
INSERT INTO PROFILE(description) VALUES('user');

INSERT INTO FUNCTION(description, url, method) VALUES('create.user', '/user', 'POST');
INSERT INTO FUNCTION(description, url, method) VALUES('search.user', '/user', 'GET');
INSERT INTO FUNCTION(description, url, method) VALUES('update.user', '/user', 'UPDATE');
INSERT INTO FUNCTION(description, url, method) VALUES('delete.user', '/user', 'DELETE');

INSERT INTO PROFILE_FUNCTIONS(profile_id, functions_id) VALUES(1,1);
INSERT INTO PROFILE_FUNCTIONS(profile_id, functions_id) VALUES(1,2);
INSERT INTO PROFILE_FUNCTIONS(profile_id, functions_id) VALUES(1,3);
INSERT INTO PROFILE_FUNCTIONS(profile_id, functions_id) VALUES(1,4);
INSERT INTO PROFILE_FUNCTIONS(profile_id, functions_id) VALUES(2,1);
INSERT INTO PROFILE_FUNCTIONS(profile_id, functions_id) VALUES(2,2);
INSERT INTO PROFILE_FUNCTIONS(profile_id, functions_id) VALUES(2,3);

INSERT INTO USER(username, password, profile_id) VALUES('user.admin', '123456', 1);
INSERT INTO USER(username, password, profile_id) VALUES('robson.grillo', '123456', 2);

