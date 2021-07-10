package base.divider

import base.heirs.Heir
import com.google.common.math.IntMath.gcd

fun List<Divider>.lcm(): Int
{
    var ans = this[0].divider

    for (i in 1 until this.size)
    ans = (((this[i].divider * ans)) /
            (gcd(this[i].divider, ans)));

    return ans;
}
fun List<Divider>.toLcm(lcm: Int = lcm())
{
    forEach {
        if(lcm > it.divider) {
            val multiplier = lcm / it.divider
            it.dividened *= multiplier
            it.divider *= multiplier
        }
    }
}
fun List<Divider>.sum(): Divider
{
    var sum = 0
    var lcm = lcm()
    forEach {
        sum += if(lcm > it.divider) {
            it.dividened * (lcm / it.divider)
        }else
            it.dividened
    }
    return Divider(sum, lcm)
}