CREATE TABLE file_metadata_entity (
  id varchar(255) NOT NULL,
  date_uploaded timestamp(6) NULL,
  http_content_type varchar(255) NULL,
  original_name varchar(255) NULL,
  "size" int8 NOT NULL,
  CONSTRAINT file_metadata_entity_pkey PRIMARY KEY (id)
);