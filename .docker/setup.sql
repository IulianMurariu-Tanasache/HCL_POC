CREATE DATABASE IF NOT EXISTS productdb;
CREATE DATABASE IF NOT EXISTS reviewdb;
CREATE DATABASE IF NOT EXISTS recommendationdb;

grant all privileges on productdb.* to poc@'%';
grant all privileges on reviewdb.* to poc@'%';
grant all privileges on recommendationdb.* to poc@'%';

