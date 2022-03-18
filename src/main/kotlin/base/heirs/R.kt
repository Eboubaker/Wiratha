package base.heirs

import base.divider.Divider
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
        if(getsRemaining && remaining.dividend <= remaining.divider)
        {
            val count = heirs.count { it::class == this::class }
            share = Divider(remaining.dividend, remaining.divider*count).reduced()
        }
    }

}
