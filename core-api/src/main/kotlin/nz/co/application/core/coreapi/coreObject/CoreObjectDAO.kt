package nz.co.application.core.coreapi.coreObject

import nz.co.application.core.coreapi.coreParameter.CoreParameter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository
import java.sql.ResultSet

@Repository
class CoreObjectDAO: CoreObjectDAOConstants
{
	@Autowired
	lateinit var coreNamedParamJdbcTemplate: NamedParameterJdbcTemplate

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