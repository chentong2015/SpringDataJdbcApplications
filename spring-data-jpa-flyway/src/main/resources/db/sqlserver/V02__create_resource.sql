create table ${table-domain}PRIVATE_RESOURCE_FILE_CONFIG
(
    id                 INT  identity(1,1) primary key,
    public_id          varchar(64)   not null,
    name               varchar(255)   not null,
    created_on         DATETIME2 default ('3000-01-01 00:00:00'),
    created_by         varchar(255)  not null,
    updated_on         DATETIME2 default ('3000-01-01 00:00:00'),
    updated_by         varchar(255)  not null
);
GO
