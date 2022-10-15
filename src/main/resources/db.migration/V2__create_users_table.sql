CREATE TABLE users (
  id BIGINT AUTO_INCREMENT NOT NULL,
   name VARCHAR(100) NULL,
   document VARCHAR(14) NULL,
   document_type VARCHAR(255) NOT NULL,
   email VARCHAR(50) NOT NULL,
   phone VARCHAR(30) NULL,
   gender VARCHAR(255) NOT NULL,
   gender_identity VARCHAR(255) NOT NULL,
   social_name VARCHAR(100) NULL,
   digest VARCHAR(255) NOT NULL,
   salt VARCHAR(255) NOT NULL,
   authpass VARCHAR(255) NULL,
   CONSTRAINT pk_users PRIMARY KEY (id)
);