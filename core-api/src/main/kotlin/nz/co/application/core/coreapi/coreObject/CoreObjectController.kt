package nz.co.application.core.coreapi.coreObject

import nz.co.application.core.coreapi.coreParameter.CoreParameter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import javax.sql.DataSource

@RestController
class CoreObjectController
{
    @Autowired
    lateinit var coreDataSource: DataSource

    @GetMapping("/coreObjectTest")
    fun test2(): CoreObjectEntity
    {
        val params = HashMap<Long, CoreParameter<Any>>();
        params.put(10L, CoreParameter( "hey hey hey"));
        params.put(11L, CoreParameter( 100));
        val coreObject = CoreObjectEntity(1L, 2L, 3L, "test Object", "test description", params);
        return coreObject;
    }

    @GetMapping("/coreObjectDB")
    fun coreObjectTestDB(): String
    {
        val jdbcTemplate = JdbcTemplate(coreDataSource)
        val changesInLog = jdbcTemplate.queryForObject("SELECT count(1) FROM databasechangelog", java.lang.Long::class.java)
        return changesInLog.toString()
    }

}