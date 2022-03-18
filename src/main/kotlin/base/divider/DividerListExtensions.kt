package base.divider

import com.google.common.math.IntMath.gcd

fun List<Divider>.lcm(): Int
{
    var ans = this[0].divider

    for (i in 1 until this.size)
    ans = (((this[i].divider * ans)) /
            (gcd(this[i].divider, ans)))

    return ans
}
fun List<Divider>.toLcm(lcm: Int = lcm()): List<Divider>
{
    forEach {
        if(lcm > it.divider) {
            val multiplier = lcm / it.divider
            it.dividend *= multiplier
            it.divider *= multiplier
        }
    }
    return this
}
fun List<Divider>.sum(): Divider
{
    var sum = 0
    val lcm = lcm()
    forEach {
        sum += if(lcm > it.divider) {
            it.dividend * (lcm / it.divider)
        }else
            it.dividend
    }
    return Divider(sum, lcm)
}
