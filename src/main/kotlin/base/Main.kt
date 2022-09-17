package base

import base.heirs.Heir
import base.heirs.calculate
import base.heirs.idToHeir
import base.heirs.printSharesTable
import dnl.utils.text.table.TextTable

fun main() {
    while (true) {
        try {
            val heirs = mutableListOf<Heir>()
            print("family: ")
            """[`#]?\d\+*""".toRegex()
                .findAll(readLine() ?: error("no input"))
                .map { it.groupValues.first() }
                .forEach {
                    var count = 1 + it.count { it2 -> it2 == '+' }
                    val id = it.replace("+", "")
                    while (count-- > 0)
                        heirs.add(idToHeir(id))
                }
            heirs.calculate()
            heirs.printSharesTable()
        } catch (e: Exception) {
            println("Error: " + e.message)
        }
    }
}
