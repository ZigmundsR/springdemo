-- Drop user first if they exist
DROP USER if exists 'springdemozigmunds'@'%' ;

-- Now create user with prop privileges
CREATE USER 'springdemozigmunds'@'%' IDENTIFIED BY 'springdemozigmunds';

GRANT ALL PRIVILEGES ON * . * TO 'springdemozigmunds'@'%';