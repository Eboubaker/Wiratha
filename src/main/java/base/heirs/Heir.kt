package base.heirs

import base.divider.Divider
import base.divider.Zero

abstract class Heir {

    abstract var id: String
    abstract var gender: String
    abstract val arabicName: String
    open val blockers = listOf<String>()
    open var share: Divider = Zero()
    open var getsRemaining = false
    open var innervationType: IntervationType = IntervationType.WITH_SELF

    open fun isBlocked(heirs: List<Heir>): Boolean
    {
        heirs.forEach {
            if(it !== this && blockers.contains(it.id))
                return true
        }
        return false
    }

    abstract fun calculateShare(heirs: List<Heir>)

    fun getNumericId(): Int
    {
        var id = id.replace('`', '-')
        var add8 = if(id[0] == '#') 8 else 0
        id = id.replace("#", "")
        return id.toInt() + add8
    }

    override fun toString(): String {
        return "$share ${if (getsRemaining) "Moassab" else ""}"
    }
}