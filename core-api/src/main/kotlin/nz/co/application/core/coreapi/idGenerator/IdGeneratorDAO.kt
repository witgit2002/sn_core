package nz.co.application.core.coreapi.idGenerator

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class IdGeneratorDAO
{
	val logger: Logger = LoggerFactory.getLogger(IdGeneratorDAO::class.qualifiedName)

	@Autowired
	lateinit var coreNamedParamJdbcTemplate: NamedParameterJdbcTemplate

	fun loadNewRange(name: String = "common_id", rangeSize: Int = 100): Long
	{
		logger.error("loadNewRange START")

		var result: Long = 0
		coreNamedParamJdbcTemplate.queryForObject("SELECT get_next_id(:range_of_ids) as value",
				mapOf("range_of_ids" to rangeSize),
				{rs, rowNum -> result = rs.getLong(1) })
		return result
	}


}