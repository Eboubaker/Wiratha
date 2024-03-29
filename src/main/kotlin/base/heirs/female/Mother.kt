package base.heirs.female

import base.divider.Sixth
import base.divider.Third
import base.heirs.Heir
import base.heirs.containsBranch
import base.heirs.male.Brother
import base.heirs.male.BrotherFromFather
import base.heirs.male.BrotherFromMother

class Mother : Heir() {
    companion object {
        var ID = "`4"
    }

    override var id = ID
    override var gender = "F"
    override val arabicName = "الأم"

    override fun calculateShare(heirs: List<Heir>) {
        share = if (heirs.containsBranch() ||
            heirs.any { it is Brother || it is BrotherFromFather || it is BrotherFromMother }
        )
            Sixth()
        else
            Third()
    }
}
