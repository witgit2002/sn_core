package nz.co.application.core.coreapi.coreObject

interface CoreObjectDAOConstants
{
	val GET_OBJECT_BY_ID: String
		get() = "SELECT * FROM core_objects objects WHERE objects.object_id = :id"
	val GET_PARAMS_BY_ID: String
		get() = """WITH attrs AS (
	    SELECT
	      attributes.attr_id,
	      attributes.attr_type_id
	    FROM core_objects objects
	      JOIN attr_object_types aot ON (objects.object_type_id = aot.object_type_id)
	      JOIN attributes ON (attributes.attr_id = aot.attr_id)
	                         AND (CASE WHEN :listOnly = TRUE
	        THEN attributes.list_view = TRUE
	                              ELSE TRUE END)
	    WHERE objects.object_id = :id
	)
	SELECT
	  params.object_id,
	  params.attr_id,
	  attrs.attr_type_id,
	  params.value            str_value,
	  NULL                    date_value,
	  cast(NULL AS BIGINT) AS reference_value
	FROM core_params params
	  JOIN attrs ON (params.attr_id = attrs.attr_id)
	WHERE params.object_id = :id
	UNION ALL
	SELECT
	  params.object_id,
	  params.attr_id,
	  attrs.attr_type_id,
	  NULL         str_value,
	  params.value date_value,
	  NULL         reference_value
	FROM param_date_values params
	  JOIN attrs ON (params.attr_id = attrs.attr_id)
	WHERE params.object_id = :id
	UNION ALL
	SELECT
	  params.object_id,
	  params.attr_id,
	  attrs.attr_type_id,
	  NULL         str_value,
	  NULL         date_value,
	  params.value reference_value
	FROM param_reference_values params
	  JOIN attrs ON (params.attr_id = attrs.attr_id)
	WHERE params.object_id = :id
	"""
}