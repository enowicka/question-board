DROP DATABASE IF EXISTS qboard;
DROP USER IF EXISTS buser;

USE mysql;
CREATE DATABASE qboard DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
CREATE USER 'buser'@'%' IDENTIFIED BY 'pass';
GRANT ALL ON qboard.* TO 'buser'@'%';
FLUSH PRIVILEGES;