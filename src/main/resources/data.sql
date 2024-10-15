INSERT INTO company (name) VALUES
('TechCorp'),
('InnovateInc'),
('GlobalSolutions'),
('InnovateCo');

INSERT INTO department (name, company_id) VALUES
('Engineering', 1),
('Marketing', 1),
('Sales', 1),
('Research', 2),
('Operations', 2),
('Human Resources', 3);

INSERT INTO team (name, department_id) VALUES
('Frontend Dev', 1),
('Backend Dev', 1),
('Digital Marketing', 2),
('Sales Team A', 3),
('AI Research', 4),
('Logistics', 5),
('Recruitment', 6);

INSERT INTO project (name, team_id) VALUES
('Website Redesign', 1),
('API Development', 2),
('Social Media Campaign', 3),
('Q4 Sales Push', 4),
('Machine Learning Model', 5),
('Supply Chain Optimization', 6),
('Talent Acquisition', 7);

INSERT INTO manager (contact_information, project_id) VALUES
('john.doe@techcorp.com', 1),
('jane.smith@techcorp.com', 2),
('mike.johnson@innovateinc.com', 3),
('sarah.brown@innovateinc.com', 4),
('david.wilson@globalsolutions.com', 5),
('emily.davis@globalsolutions.com', 6),
('chris.taylor@globalsolutions.com', 7);