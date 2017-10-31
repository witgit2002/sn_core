INSERT INTO object_types(object_type_id, name, description, parent_id, parent_ids)
VALUES (0, 'Base Type', 'The very base object type', 0, '0')
;
INSERT INTO object_types(object_type_id, name, description, parent_id, parent_ids)
VALUES (1, 'User Base Type', 'The base object type for users', 0, '0|1')
;
INSERT INTO object_types(object_type_id, name, description, parent_id, parent_ids)
VALUES (2, 'User Group Type', 'User groups type', 1, '0|1|2')
;
INSERT INTO object_types(object_type_id, name, description, parent_id, parent_ids)
VALUES (3, 'User Type', 'User type', 1, '0|1|3')
;
INSERT INTO attribute_types(attr_type_id, name, description, reference_to_object, reference_to_attr)
VALUES (0, 'String', 'varchar value format', null, null)
;
INSERT INTO attribute_types(attr_type_id, name, description, reference_to_object, reference_to_attr)
VALUES (1, 'Integer', 'integer value format', null, null)
;
INSERT INTO attribute_types(attr_type_id, name, description, reference_to_object, reference_to_attr)
VALUES (2, 'Float', 'float value format', null, null)
;
INSERT INTO attribute_types(attr_type_id, name, description, reference_to_object, reference_to_attr)
VALUES (3, 'Date', 'date value format', null, null)
;
INSERT INTO attribute_types(attr_type_id, name, description, reference_to_object, reference_to_attr)
VALUES (4, 'Password', 'secured value format', null, null)
;
INSERT INTO attr_group_types(attr_group_type_id, name, description)
VALUES (0, 'Parameters Type', 'Group type for parameters tab')
;
INSERT INTO attr_group_types(attr_group_type_id, name, description)
VALUES (1, 'Parent-Child Type', 'Group type for parent/child relationship')
;
INSERT INTO attr_group_types(attr_group_type_id, name, description)
VALUES (2, 'Reference Type', 'Group type for reference relationship')
;
INSERT INTO attr_group_types(attr_group_type_id, name, description)
VALUES (3, 'Custom Type', 'Group type for custom type')
;
INSERT INTO attr_groups(attr_group_id, name, description, attr_group_type_id)
VALUES (1, 'Parameters', 'Parameters is the default tab on object', 0)
;

INSERT INTO attributes(attr_id, name, description, options, attr_type_id, attr_group_id)
VALUES (1, 'First Name', '', 0, 0, 1);
;
INSERT INTO attributes(attr_id, name, description, options, attr_type_id, attr_group_id)
VALUES (2, 'Last Name', '', 0, 0, 1);
;
INSERT INTO attributes(attr_id, name, description, options, attr_type_id, attr_group_id)
VALUES (3, 'Email', '', 0, 0, 1);
;
INSERT INTO attributes(attr_id, name, description, options, attr_type_id, attr_group_id)
VALUES (4, 'Password', '', 0, 4, 1);
;

INSERT INTO core_objects(object_id, name, description, object_type_id, parent_id, parent_ids)
VALUES (0, 'Root Object', 'Virtual root object', 0, 0, '0')
;
INSERT INTO core_objects(object_id, name, description, object_type_id, parent_id, parent_ids)
VALUES (1, 'Users Object', 'Users root object', 2, 0, '0|1')
;
INSERT INTO core_objects(object_id, name, description, object_type_id, parent_id, parent_ids)
VALUES (2, 'TestUser', '', 3, 1, '0|1|2')
;
INSERT INTO core_params (attr_id, object_id, value) VALUES (1, 2, 'FirstName');
INSERT INTO core_params (attr_id, object_id, value) VALUES (2, 2, 'SecondName');
INSERT INTO core_params (attr_id, object_id, value) VALUES (3, 2, 'test@test.com');

INSERT INTO core_objects(object_id, name, description, object_type_id, parent_id, parent_ids)
VALUES (3, 'TestGroup', '', 2, 1, '0|1|3')
;
