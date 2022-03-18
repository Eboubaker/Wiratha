package base

import base.heirs.Heir
import base.heirs.calculate
import base.heirs.idToHeir
import dnl.utils.text.table.TextTable

fun main() {
    while (true) {
        try {
            val heirs = mutableListOf<Heir>()
            print("family: ")
            """[`#]?\d\+*""".toRegex()
                .findAll(readLine()!!)
                .map { it.groupValues.first() }
                .forEach {
                    var count = 1 + it.count { it2 -> it2 == '+' }
                    val id = it.replace("+", "")
                    while (count-- > 0)
                        heirs.add(idToHeir(id))
                }
            heirs.calculate()
            TextTable(
                arrayOf("heir", "share", "share%"),
                heirs.map { arrayOf(it::class.java.simpleName, it.share, it.share.getValue()) }.toTypedArray()
            ).printTable()
        } catch (e: Exception) {
            println("Error: " + e.message)
        }
    }
}
