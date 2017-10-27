CREATE TABLE attr_group_types
(
    attr_group_type_id BIGINT PRIMARY KEY NOT NULL,
    name VARCHAR(255),
    description VARCHAR(512)
);
CREATE TABLE attr_groups
(
    attr_group_id BIGINT PRIMARY KEY NOT NULL,
    name VARCHAR(255),
    description VARCHAR(512),
    attr_group_type_id BIGINT NOT NULL
);
CREATE TABLE attr_object_types
(
    attr_object_type_id BIGINT PRIMARY KEY NOT NULL,
    attr_id BIGINT,
    object_type_id BIGINT
);
CREATE TABLE attribute_types
(
    attr_type_id BIGINT PRIMARY KEY NOT NULL,
    name VARCHAR(255),
    description VARCHAR(512),
    reference_to_object BIGINT,
    reference_to_attr BIGINT
);
CREATE TABLE attributes
(
    attr_id BIGINT PRIMARY KEY NOT NULL,
    name VARCHAR(255),
    description VARCHAR(512),
    options INTEGER,
    attr_type_id BIGINT,
    attr_group_id BIGINT NOT NULL
);
CREATE TABLE core_objects
(
    object_id BIGINT PRIMARY KEY NOT NULL,
    name VARCHAR(255),
    description VARCHAR(512),
    object_type_id BIGINT NOT NULL,
    version INTEGER DEFAULT 1 NOT NULL,
    parent_id BIGINT NOT NULL,
    parent_ids VARCHAR(1024)
);
CREATE TABLE core_params
(
    attr_id BIGINT,
    object_id BIGINT,
    value VARCHAR(1024)
);
CREATE TABLE list_values
(
    list_value_id BIGINT PRIMARY KEY NOT NULL,
    attr_type_id BIGINT NOT NULL,
    name VARCHAR(100),
    description VARCHAR(512)
);
CREATE TABLE object_types
(
    object_type_id BIGINT PRIMARY KEY NOT NULL,
    name VARCHAR(255),
    description VARCHAR(512),
    parent_id BIGINT NOT NULL,
    parent_ids VARCHAR(1024) NOT NULL
);
CREATE TABLE param_date_values
(
    object_id BIGINT NOT NULL,
    attr_id BIGINT NOT NULL,
    value DATE,
    options INTEGER
);
CREATE TABLE param_reference_values
(
    object_id BIGINT NOT NULL,
    attr_id BIGINT NOT NULL,
    value BIGINT,
    options INTEGER
);
ALTER TABLE attr_groups ADD FOREIGN KEY (attr_group_type_id) REFERENCES attr_group_types (attr_group_type_id);
ALTER TABLE attr_object_types ADD FOREIGN KEY (attr_id) REFERENCES attributes (attr_id);
ALTER TABLE attr_object_types ADD FOREIGN KEY (object_type_id) REFERENCES object_types (object_type_id);
ALTER TABLE attributes ADD FOREIGN KEY (attr_type_id) REFERENCES attribute_types (attr_type_id);
ALTER TABLE attributes ADD FOREIGN KEY (attr_group_id) REFERENCES attr_groups (attr_group_id);
ALTER TABLE core_objects ADD FOREIGN KEY (object_type_id) REFERENCES object_types (object_type_id);
ALTER TABLE core_params ADD FOREIGN KEY (attr_id) REFERENCES attributes (attr_id);
ALTER TABLE core_params ADD FOREIGN KEY (object_id) REFERENCES core_objects (object_id);
ALTER TABLE list_values ADD FOREIGN KEY (attr_type_id) REFERENCES attribute_types (attr_type_id);
ALTER TABLE param_date_values ADD FOREIGN KEY (object_id) REFERENCES core_objects (object_id);
ALTER TABLE param_date_values ADD FOREIGN KEY (attr_id) REFERENCES attributes (attr_id);
ALTER TABLE param_reference_values ADD FOREIGN KEY (object_id) REFERENCES core_objects (object_id);
ALTER TABLE param_reference_values ADD FOREIGN KEY (attr_id) REFERENCES attributes (attr_id);
ALTER TABLE param_reference_values ADD FOREIGN KEY (value) REFERENCES core_objects (object_id);