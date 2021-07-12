package base.divider

import com.google.common.math.IntMath.gcd

open class Divider (open var dividened: Int = 1, open var divider: Int = 1) {
    fun getValue(): String
    {
        val perc = (dividened * 1.0 / divider * 100).toString()
        val dotpos = perc.indexOf('.')

        val intpart = perc.substring(0, dotpos)
        val decpart = perc.substring(dotpos+1).take(2)
        if(decpart != "00" && decpart != "0")
            return "%s.%s%%".format(intpart, decpart)
        else
            return "%s%%".format(intpart)
    }

    override fun toString(): String {
        return "$dividened/$divider"
    }

    fun reduced(): Divider
    {
        var d = gcd(dividened, divider)
        return Divider(dividened/d, divider/d)
    }

    fun remaining(): Divider
    {
        return Divider(divider-dividened, divider)
    }

    fun mult(other: Divider): Divider
    {
        return Divider(dividened*other.dividened, other.divider * divider)
    }
}