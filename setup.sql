DROP DATABASE IF EXISTS tenant_ghi;
DROP DATABASE IF EXISTS tenant_def;
DROP DATABASE IF EXISTS tenant_abc;
DROP DATABASE IF EXISTS tenants_db;

create database tenants_db;
create database tenant_abc;
create database tenant_def;
create database tenant_ghi;

use tenants_db;

create table tenant (
	id char(3) not null,
	url varchar(255) not null,
	username varchar(255) not null,
	pwd varchar(255),
	constraint PK_tenant primary key (id)
);

use tenant_abc;

create table location (
	name varchar(255) not null
);

use tenant_def;

create table location (
	name varchar(255) not null
);

use tenant_ghi;

create table location (
	name varchar(255) not null
);

