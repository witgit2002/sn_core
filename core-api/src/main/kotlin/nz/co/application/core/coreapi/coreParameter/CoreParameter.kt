package nz.co.application.core.coreapi.coreParameter


class CoreParameter<T>(val value: T)
{
    override fun toString(): String {
        return value.toString()
    }
}
