
DROP TABLE IF EXISTS manager;
DROP TABLE IF EXISTS project;
DROP TABLE IF EXISTS team;
DROP TABLE IF EXISTS department;
DROP TABLE IF EXISTS company;

CREATE TABLE company (
    id SERIAL PRIMARY KEY,
    name VARCHAR(80) NOT NULL UNIQUE
);

CREATE TABLE department (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    company_id INT NOT NULL,
    FOREIGN KEY (company_id) REFERENCES company(id),
    UNIQUE (company_id, name)
);

CREATE TABLE team (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    department_id INT NOT NULL,
    FOREIGN KEY (department_id) REFERENCES department(id),
    UNIQUE (department_id, name)
);

CREATE TABLE project (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    team_id INT UNIQUE NOT NULL,
    FOREIGN KEY (team_id) REFERENCES team(id)
);

CREATE TABLE manager (
    id SERIAL PRIMARY KEY,
    contact_information VARCHAR(255) UNIQUE NOT NULL,
    project_id INT UNIQUE NOT NULL,
    FOREIGN KEY (project_id) REFERENCES project(id)
);