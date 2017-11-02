package nz.co.application.core.coreapi.objectType

import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ObjectTypeController
{
	@ApiOperation(value = "get object type by id", notes = "get object type by id", tags = arrayOf("Object Type Management"))
	@GetMapping("/objectType")
	fun getObjectTypeById(id: Long): ObjectTypeEntity
	{
		return ObjectTypeEntity(1, "test name", "test description")
	}
}