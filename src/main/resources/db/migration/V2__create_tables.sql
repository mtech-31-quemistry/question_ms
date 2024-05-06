CREATE TABLE IF NOT EXISTS sjmsuser.user_profile
(
    id serial PRIMARY KEY,
    account_uuid VARCHAR(255) UNIQUE,
    email VARCHAR(255),
    name character varying(255) NOT NULL,
    seeking bool NOT NULL DEFAULT TRUE,
    job_title character varying(255) NOT NULL,
    image character varying(255) NOT NULL,
    about character varying(512) NOT NULL
);

CREATE TABLE IF NOT EXISTS sjmsuser.work_experience
(
    id serial PRIMARY KEY,
    profile_id bigint NOT NULL,
    company character varying(255) NOT NULL,
    year_start character varying(255) NOT NULL,
    year_end character varying(255) NOT NULL,
    job_title character varying(255) NOT NULL,
    logo character varying(255) NOT NULL,
    experience character varying(512) NOT NULL
);

CREATE TABLE IF NOT EXISTS sjmsuser.education
(
    id serial PRIMARY KEY,
    profile_id bigint NOT NULL,
    school character varying(255) NOT NULL,
    year_start character varying(255) NOT NULL,
    year_end character varying(255) NOT NULL,
    logo character varying(255) NOT NULL,
    description character varying(512) NOT NULL
);

