package nz.co.application.core.coreapi.coreObject

import nz.co.application.core.coreapi.coreParameter.CoreParameter
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CoreObjectController
{
    @GetMapping("/test")
    fun test(): String
    {
        return "Hello world";
    }

    @GetMapping("/test2")
    fun test2(): CoreObjectEntity
    {
        val params = HashMap<Long, CoreParameter<Any>>();
        params.put(10L, CoreParameter( "hey hey hey" as Any));
        params.put(11L, CoreParameter( 100 as Any));
        val coreObject = CoreObjectEntity(1L, "test Object", "test description", params);
        return coreObject;
    }

}