package nz.co.application.core.coreapi.objectType

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class ObjectTypeDAO
{
	@Autowired
	lateinit var coreNamedParamJdbcTemplate: NamedParameterJdbcTemplate

	fun getObjectTypeById(id: Long)
	{

	}
}