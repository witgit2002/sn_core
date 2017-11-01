package nz.co.application.core.coreapi.coreObject

import nz.co.application.core.coreapi.coreParameter.CoreParameter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository
import java.sql.ResultSet

@Repository
class CoreObjectDAO
{
	@Autowired
	lateinit var coreNamedParamJdbcTemplate: NamedParameterJdbcTemplate

	final val GET_OBJECT_BY_ID = "SELECT * FROM core_objects objects WHERE objects.object_id = :id"
	final val GET_PARAMS_BY_ID = """WITH attrs AS (
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

	fun getObjectById(id: Long): CoreObjectEntity
	{
		try
		{
			return coreNamedParamJdbcTemplate.queryForObject(GET_OBJECT_BY_ID
					, mapOf("id" to id)
					, { rs: ResultSet, rowNum: Int ->
				CoreObjectEntity(id, rs.getLong("parent_id")
						, rs.getLong("object_type_id"), rs.getString("name")
						, rs.getString("description"), loadParameters(id, false))
			})
		}
		catch (ex: EmptyResultDataAccessException)
		{
			throw Exception("No Object Is Found")
		}
	}

	private fun loadParameters(id: Long, visibleOnly: Boolean): HashMap<Long, CoreParameter<Any>>
	{
		val params: HashMap<Long, CoreParameter<Any>> = HashMap()
		coreNamedParamJdbcTemplate.query(GET_PARAMS_BY_ID
				, mapOf("id" to id, "listOnly" to visibleOnly)
				, { rs, rowNum ->
			val coreParameter = when (rs.getInt("attr_type_id"))
			{
				0 -> CoreParameter(rs.getString("str_value"))
				1 -> CoreParameter(rs.getInt("str_value"))
				2 -> CoreParameter(rs.getFloat("str_value"))
				3 -> CoreParameter(rs.getDate("date_value"))
				4 -> CoreParameter(rs.getString("str_value"))
				else -> CoreParameter(rs.getString("str_value"))
			}
			params.put(rs.getLong("attr_id"), coreParameter as CoreParameter<Any>)
		}
		)
		return params
	}


}