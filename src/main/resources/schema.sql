CREATE TABLE IF NOT EXISTS question (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    question TEXT NOT NULL
    );

-- Create the table for TeamSurvey
CREATE TABLE if not exists team_survey (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                             validity_days INT NOT NULL,
                             title VARCHAR(255) NOT NULL,
                             completed BOOLEAN DEFAULT FALSE
);

-- Create the table for Survey with a foreign key to TeamSurvey
CREATE TABLE if not exists survey (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        team_survey_id BIGINT, -- Foreign key to team_survey
                        link VARCHAR(255) NOT NULL,
                        completed BOOLEAN DEFAULT FALSE,
                        FOREIGN KEY (team_survey_id) REFERENCES team_survey(id) ON DELETE CASCADE
);

-- Create the table for RatedQuestion
CREATE TABLE if not exists rated_question (
                                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                question_id BIGINT NOT NULL, -- Foreign key to Question
                                survey_id BIGINT NOT NULL,   -- Foreign key to Survey
                                rate INT NOT NULL,           -- Rating for the question
                                FOREIGN KEY (question_id) REFERENCES question(id) ON DELETE CASCADE,
                                FOREIGN KEY (survey_id) REFERENCES survey(id) ON DELETE CASCADE
);