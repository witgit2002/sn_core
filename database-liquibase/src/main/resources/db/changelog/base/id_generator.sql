CREATE TABLE id_generator(
    name VARCHAR(255)
  , id BIGINT
);

INSERT INTO id_generator(name, id) VALUES ('common_id', 0);

DROP FUNCTION IF EXISTS get_next_id( INTEGER );
CREATE FUNCTION get_next_id(range_of_ids INTEGER)
  RETURNS BIGINT
AS $$
DECLARE
  seq_number BIGINT;
  dt_number  BIGINT;
BEGIN
  SELECT id
  INTO seq_number
  FROM id_generator
  WHERE name = 'common_id'
  FOR UPDATE;

  IF ((seq_number + range_of_ids) > 99999)
  THEN
    seq_number := 0;
  END IF;

  UPDATE id_generator
  SET id = seq_number + range_of_ids
  WHERE name = 'common_id';

  dt_number = to_number(to_char(current_timestamp, 'YYMMDDHH24MISSMS') || '00000', '9999999999999999999');

  --   RAISE NOTICE '%', (dt_number);
  --   RAISE NOTICE '%', (seq_number);
  RETURN dt_number + seq_number;
END;
$$
LANGUAGE plpgsql;
