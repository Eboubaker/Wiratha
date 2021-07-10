package base

import base.divider.Divider
import base.divider.sum
import base.heirs.GetsRemaining
import base.heirs.Heir
import base.heirs.idToHeir
import base.heirs.normalizeShares
import dnl.utils.text.table.TextTable
import java.lang.Exception

fun main()
{
    while(true)
    {
        try{
            var heirs = mutableListOf<Heir>()
            print("family: ")
            """`?\d\+*""".toRegex()
            .findAll(readLine()!!)
            .map { it.groupValues.first() }
            .forEach {
                var count = 1 + it.count { it2 -> it2 == '+' }
                var id = it.replace("+", "")
                while(count-- > 0)
                    heirs.add(idToHeir(id))
            }
            heirs.sortBy { it.getNumericId() }
            var nonBlocked = heirs.filter { !it.isBlocked(heirs) }
            nonBlocked.forEach { it.calculateShare(nonBlocked) }
            val sum = nonBlocked.map { it.share }.sum()
            var remaining = Divider(sum.divider - sum.dividened, sum.divider)
            var remainingGetters = nonBlocked.filter {it is GetsRemaining && it.getsRemaining}
            remainingGetters.forEach {
                if(it is GetsRemaining)
                    it.calculateRemaining(remaining, remainingGetters)
            }
            heirs.normalizeShares()
            heirs.sortByDescending { it.share.dividened }
            TextTable(arrayOf("heir", "share", "share%"), heirs.map { arrayOf(it::class.java.simpleName, it.share, it.share.getValue()) }.toTypedArray()).printTable()
        }catch (e: Exception)
        {
            println("Error: " + e.message)
        }
    }
}