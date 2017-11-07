package nz.co.application.core.coreapi.coreObject

import io.swagger.annotations.ApiOperation
import nz.co.application.core.coreapi.coreParameter.CoreParameter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class CoreObjectController
{
	@Autowired
	lateinit var coreJdbcTemplate: JdbcTemplate

	@Autowired
	lateinit var coreObjectDAO: CoreObjectDAO

	@ApiOperation(value = "Test if DB is up", notes = "test DB", tags = arrayOf("Object Management"))
	@GetMapping("/testDB")
	fun coreObjectTestDB(): String
	{
		val changesInLog = coreJdbcTemplate.queryForObject("SELECT count(1) FROM databasechangelog", java.lang.Long::class.java)
		return changesInLog.toString()
	}

	@ApiOperation(value = "get object by id", notes = "get object by id", tags = arrayOf("Object Management"))
	@GetMapping("/object")
	fun getObjectById(@RequestParam(name = "id") id: Long): CoreObjectEntity
	{
		return coreObjectDAO.getObjectById(id);
	}

}