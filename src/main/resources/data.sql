insert into question (id, question) values (1, 'Rate your office');
insert into question (id, question) values (2, 'Rate your stuff');
insert into question (id, question) values (3, 'Rate your coffee');
insert into question (id, question) values (4, 'Rate your boss');
insert into question (id, question) values (5, 'Rate your colleagues');

insert into team_survey (id, creation_date, validity_days, title, completed) values (1, CURRENT_TIMESTAMP, 7, 'Team Survey 1', false);

insert into survey (id, team_survey_id, link) values (1, 1, 'link1');
insert into survey (id, team_survey_id, link) values (2, 1, 'link2');
insert into survey (id, team_survey_id, link) values (3, 1, 'link3');


insert into rated_question (id, question_id, survey_id, rate) values (1, 1, 1, 0);
insert into rated_question (id, question_id, survey_id, rate) values (2, 2, 1, 0);
insert into rated_question (id, question_id, survey_id, rate) values (3, 3, 1, 0);
insert into rated_question (id, question_id, survey_id, rate) values (4, 4, 1, 0);
insert into rated_question (id, question_id, survey_id, rate) values (5, 5, 1, 0);

insert into rated_question (id, question_id, survey_id, rate) values (6, 1, 2, 0);
insert into rated_question (id, question_id, survey_id, rate) values (7, 2, 2, 0);
insert into rated_question (id, question_id, survey_id, rate) values (8, 3, 2, 0);
insert into rated_question (id, question_id, survey_id, rate) values (9, 4, 2, 0);
insert into rated_question (id, question_id, survey_id, rate) values (10, 5, 2, 0);

insert into rated_question (id, question_id, survey_id, rate) values (11, 1, 3, 0);
insert into rated_question (id, question_id, survey_id, rate) values (12, 2, 3, 0);
insert into rated_question (id, question_id, survey_id, rate) values (13, 3, 3, 0);
insert into rated_question (id, question_id, survey_id, rate) values (14, 4, 3, 0);
insert into rated_question (id, question_id, survey_id, rate) values (15, 5, 3, 0);





