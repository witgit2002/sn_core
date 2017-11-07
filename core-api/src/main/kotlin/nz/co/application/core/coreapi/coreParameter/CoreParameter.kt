package nz.co.application.core.coreapi.coreParameter


data class CoreParameter<T>(val value: T)

data class ObjectReference(val id: Long, val name: String)
data class ReferenceList(val id: Long, val objects: List<ObjectReference>)
