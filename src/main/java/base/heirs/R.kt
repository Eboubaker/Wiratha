package base.heirs

import base.divider.Divider
import base.divider.Half
import base.divider.Quarter
import base.heirs.*
import base.heirs.female.*
import base.heirs.male.*

abstract class R: Heir(), GetsRemaining {
    override var gender = "M"
    override val blockers = listOf(BrotherFromFather.ID, Brother.ID, GrandFather.ID, Father.ID, SonOfSon.ID, Son.ID)

    override fun isBlocked(heirs: List<Heir>): Boolean {
        return super.isBlocked(heirs)
                || heirs.any { it.id.startsWith('#') && it.id < this.id}
    }
    override fun calculateShare(heirs: List<Heir>) {
        getsRemaining = true
    }

    override fun calculateRemaining(remaining: Divider, heirs: List<Heir>) {
        if(getsRemaining && remaining.dividened <= remaining.divider)
        {
            var count = heirs.count { it::class == this::class }
            share = Divider(remaining.dividened, remaining.divider*count).reduced()
        }
    }

}