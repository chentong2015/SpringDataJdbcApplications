create table ${table-domain}SAMPLE_CONFIG
(
    id                 INT  identity(1,1) primary key,
    name               varchar(64)   not null,
    description        varchar(255)   not null,
);
GO
