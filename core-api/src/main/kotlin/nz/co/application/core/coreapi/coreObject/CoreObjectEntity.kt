package nz.co.application.core.coreapi.coreObject

import nz.co.application.core.coreapi.coreParameter.CoreParameter

class CoreObjectEntity(val id: Long, val name: String, val description: String, var parameters: HashMap<Long, CoreParameter<Any>>)
{

}