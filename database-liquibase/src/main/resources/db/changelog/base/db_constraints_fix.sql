ALTER TABLE core_objects ADD CONSTRAINT core_objects_parent_id_fky FOREIGN KEY (parent_id) REFERENCES core_objects (object_id);
ALTER TABLE object_types ADD CONSTRAINT object_types_parent_id_fky FOREIGN KEY (parent_id) REFERENCES object_types (object_type_id);

ALTER TABLE attributes ADD COLUMN is_required BOOLEAN NOT NULL DEFAULT FALSE;
ALTER TABLE attributes ADD COLUMN list_view BOOLEAN NOT NULL DEFAULT TRUE;
ALTER TABLE attr_object_types ADD COLUMN order_number INTEGER NOT NULL DEFAULT 0;
