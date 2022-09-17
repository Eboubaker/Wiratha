import base.divider.sum
import base.heirs.calculate
import base.heirs.female.Mother
import base.heirs.female.Sister
import base.heirs.female.Wife
import base.heirs.male.Brother
import base.heirs.male.BrotherFromMother
import base.heirs.male.Father
import base.heirs.male.Husband
import base.heirs.printSharesTable
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class HeirsTest {
    @Test
    fun testSum() {
        val heirs = mutableListOf(Husband(), Sister(), Sister())
        heirs.calculate()
        val div = heirs.map {
            it.share
        }.sum()
        heirs.printSharesTable()
        assertEquals(div.divider, div.dividend)
    }

    @Test
    fun hamir() {
        val heirs = mutableListOf(Husband(), Mother(), Brother(), Brother(), BrotherFromMother(), BrotherFromMother())
        heirs.calculate()
        val div = heirs.map {
            it.share
        }.sum()
        heirs.printSharesTable()
        assertEquals(div.divider, div.dividend)
    }

    @Test
    fun father() {
        val father = Father();
        val heirs = mutableListOf(Wife(), Mother(), Sister(), Sister(), father)
        heirs.calculate()
        assertNotEquals(1, father.share.dividend)
    }
}
