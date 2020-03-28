
INSERT INTO PROFILE(description) VALUES('ADMIN');

INSERT INTO FUNCTION(description, url, method) VALUES('create.user', '/user', 'POST');
INSERT INTO FUNCTION(description, url, method) VALUES('search.user', '/user', 'GET');
INSERT INTO FUNCTION(description, url, method) VALUES('update.user', '/user', 'UPDATE');
INSERT INTO FUNCTION(description, url, method) VALUES('delete.user', '/user', 'DELETE');

INSERT INTO PROFILE_FUNCTIONS(profile_id,functions_id) VALUES(1,1);
INSERT INTO PROFILE_FUNCTIONS(profile_id,functions_id) VALUES(1,2);
INSERT INTO PROFILE_FUNCTIONS(profile_id,functions_id) VALUES(1,3);
INSERT INTO PROFILE_FUNCTIONS(profile_id,functions_id) VALUES(1,4);

INSERT INTO USER(username, password) VALUES('admin', '$2a$10$X.HvTUErov5QQPmIPMITv.o/IPBlrGm5b8pysWnQ.gvrKdEDHu0dO');

INSERT INTO USER_PROFILE(user_id,profile_id) VALUES(1,1);