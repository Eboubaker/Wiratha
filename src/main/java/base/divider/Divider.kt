package base.divider

import com.google.common.math.IntMath.gcd

open class Divider (open var dividened: Int = 1, open var divider: Int = 1) {
    fun getValue(): String
    {
        val perc = (dividened * 1.0 / divider * 100)
        val intpart = perc.toInt()
        val decpart = ((perc - intpart)*100).toInt()
        if(decpart > 0)
            return "%s.%s%%".format(intpart, decpart)
        else
            return "%d%%".format(intpart)
    }

    override fun toString(): String {
        return "$dividened/$divider"
    }

    fun reduced(): Divider
    {
        var d = gcd(dividened, divider)
        return Divider(dividened/d, divider/d)
    }
}