package base.divider

import com.google.common.math.IntMath.gcd

open class Divider (open var dividend: Int = 1, open var divider: Int = 1) {
    fun getValue(): String
    {
        val percentage = (dividend * 1.0 / divider * 100).toString()
        val dotPos = percentage.indexOf('.')

        val intPart = percentage.substring(0, dotPos)
        val decPart = percentage.substring(dotPos+1).take(2)
        return if(decPart != "00" && decPart != "0")
            "%s.%s%%".format(intPart, decPart)
        else
            "%s%%".format(intPart)
    }

    override fun toString(): String {
        return "$dividend/$divider"
    }

    fun reduced(): Divider
    {
        val d = gcd(dividend, divider)
        return Divider(dividend/d, divider/d)
    }

    fun remaining(): Divider
    {
        return Divider(divider-dividend, divider)
    }

    fun multiply(other: Divider): Divider
    {
        return Divider(dividend*other.dividend, other.divider * divider)
    }
}
