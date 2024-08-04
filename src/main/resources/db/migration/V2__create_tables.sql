-- qms_question.mcq definition

-- Drop table

-- DROP TABLE IF EXISTS qms_question.mcq;

CREATE TABLE qms_question.mcq (
	id SERIAL NOT NULL,
	status varchar(255) NULL,
	stem varchar(255) NULL,
    "options" varchar NULL,
    closed_on timestamp NULL,
    closed_by varchar(255) NULL,
    published_on timestamp NULL,
    published_by varchar(255) NULL,
    created_by varchar(255) NULL,
    updated_ts timestamp NULL,
    created_ts timestamp NULL,
	CONSTRAINT mcq_pkey PRIMARY KEY (id)
);

-- Create sequence for mcq.id
--CREATE SEQUENCE qms_question.mcq_id_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;


-- qms_question.topic definition

-- Drop table

-- DROP TABLE IF EXISTS qms_question.topic;

CREATE TABLE qms_question.topic (
	id SERIAL NOT NULL,
	"name" varchar(255) NULL,
	status varchar(20) NULL,
	CONSTRAINT topic_pkey PRIMARY KEY (id)
);

-- Create sequence for topic.id
--CREATE SEQUENCE qms_question.topic_id_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;


-- qms_question.skill definition

-- Drop table

-- DROP TABLE IF EXISTS qms_question.skill;

CREATE TABLE qms_question.skill (
	id SERIAL NOT NULL,
	topic_id int4 NULL,
	"name" varchar(255) NULL,
    status varchar(20) NULL,
	CONSTRAINT skill_pkey PRIMARY KEY (id),
	CONSTRAINT fk_topic FOREIGN KEY (topic_id) REFERENCES qms_question.topic (id)
);

-- Create sequence for skill.id
--CREATE SEQUENCE qms_question.skill_id_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;


-- qms_question.mcq_skills definition

-- Drop table

-- DROP TABLE IF EXISTS qms_question.mcq_skills;

CREATE TABLE qms_question.mcq_skills (
	mcq_id int8 NOT NULL,
	skill_id int8 NOT NULL,
	CONSTRAINT mcq_skills_fk_skill FOREIGN KEY (skill_id) REFERENCES qms_question.skill(id),
	CONSTRAINT mcq_skills_fk_mcq FOREIGN KEY (mcq_id) REFERENCES qms_question.mcq(id) ON DELETE CASCADE
);


-- qms_question.mcq_topics definition

-- Drop table

-- DROP TABLE IF EXISTS qms_question.mcq_topics;

CREATE TABLE qms_question.mcq_topics (
	mcq_id int8 NOT NULL,
	topic_id int8 NOT NULL,
	CONSTRAINT mcq_topics_fk_mcq FOREIGN KEY (mcq_id) REFERENCES qms_question.mcq(id),
	CONSTRAINT mcq_topics_fk_topic FOREIGN KEY (topic_id) REFERENCES qms_question.topic(id) ON DELETE CASCADE
);