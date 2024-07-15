-- qms_question.mcq definition

-- Drop table

-- DROP TABLE qms_question.mcq;

CREATE TABLE qms_question.mcq (
	id SERIAL NOT NULL,
	published_on timestamp(6) NULL,
	closed_by varchar(255) NULL,
	created_by varchar(255) NULL,
	published_by varchar(255) NULL,
	status varchar(255) NULL,
	stem varchar(255) NULL,
    "options" varchar NULL,
    closed_on timestamp(6) NULL,
    created_on timestamp(6) NULL,
	CONSTRAINT mcq_pkey PRIMARY KEY (id)
);


-- qms_question.skill definition

-- Drop table

-- DROP TABLE qms_question.skill;

CREATE TABLE qms_question.skill (
	id SERIAL NOT NULL,
	topic_id int4 NULL,
	"name" varchar(255) NULL,
	CONSTRAINT skill_pkey PRIMARY KEY (id)
);


-- qms_question.topic definition

-- Drop table

-- DROP TABLE qms_question.topic;

CREATE TABLE qms_question.topic (
	id SERIAL NOT NULL,
	"name" varchar(255) NULL,
	CONSTRAINT topic_pkey PRIMARY KEY (id)
);


-- qms_question.mcq_options definition

-- Drop table

-- DROP TABLE qms_question.mcq_options;

--CREATE TABLE qms_question.mcq_options (
--	mcq_id int8 NOT NULL,
--	"options" varchar NULL,
--	CONSTRAINT mcq_options_fk FOREIGN KEY (mcq_id) REFERENCES qms_question.mcq(id)
--);


-- qms_question.mcq_skills definition

-- Drop table

-- DROP TABLE qms_question.mcq_skills;

CREATE TABLE qms_question.mcq_skills (
	mcq_id int8 NOT NULL,
	skill_id int8 NOT NULL,
	CONSTRAINT mcq_skills_fk_skill FOREIGN KEY (skill_id) REFERENCES qms_question.skill(id),
	CONSTRAINT mcq_skills_fk_mcq FOREIGN KEY (mcq_id) REFERENCES qms_question.mcq(id)
);


-- qms_question.mcq_topics definition

-- Drop table

-- DROP TABLE qms_question.mcq_topics;

CREATE TABLE qms_question.mcq_topics (
	mcq_id int8 NOT NULL,
	topic_id int8 NOT NULL,
	CONSTRAINT  mcq_topics_fk_mcq  FOREIGN KEY (mcq_id) REFERENCES qms_question.mcq(id),
	CONSTRAINT  mcq_topics_fk_topic FOREIGN KEY (topic_id) REFERENCES qms_question.topic(id)
);