package nz.co.application.core.coreapi.coreObject

import nz.co.application.core.coreapi.coreParameter.CoreParameter

data class CoreObjectEntity(val id: Long
                       , val parentID: Long
                       , val objectTypeID: Long
                       , val name: String
                       , val description: String
                       , val parameters: HashMap<Long, CoreParameter<Any>>)