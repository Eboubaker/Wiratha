package base

import base.divider.Divider
import base.divider.sum
import base.heirs.*
import base.heirs.female.Wife
import base.heirs.male.Husband
import dnl.utils.text.table.TextTable
import java.lang.Exception

fun main()
{
    while(true)
    {
        try{
            var heirs = mutableListOf<Heir>()
            print("family: ")
            """[`#]?\d\+*""".toRegex()
            .findAll(readLine()!!)
            .map { it.groupValues.first() }
            .forEach {
                var count = 1 + it.count { it2 -> it2 == '+' }
                var id = it.replace("+", "")
                while(count-- > 0)
                    heirs.add(idToHeir(id))
            }
            heirs.calculate()
            TextTable(arrayOf("heir", "share", "share%"), heirs.map { arrayOf(it::class.java.simpleName, it.share, it.share.getValue()) }.toTypedArray()).printTable()
        }catch (e: Exception)
        {
            println("Error: " + e.message)
        }
    }
}