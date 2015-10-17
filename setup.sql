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

insert into tenant values ('abc', 'jdbc:mysql://localhost:3306/tenant_abc', 'root', NULL);
insert into tenant values ('def', 'jdbc:mysql://localhost:3306/tenant_def', 'root', NULL);

use tenant_abc;

create table location (
	id varchar(3) not null,
	name varchar(255) not null,
	constraint PK_location primary key (id)
);

insert into location values ('123', 'Location ABC-123');

use tenant_def;

create table location (
	id varchar(3) not null,
	name varchar(255) not null,
	constraint PK_location primary key (id)
);

insert into location values ('345', 'Location DEF-345');

use tenant_ghi;

create table location (
	id varchar(3) not null,
	name varchar(255) not null,
	constraint PK_location primary key (id)
);

insert into location values ('678', 'Location GHI-678');
