package base.heirs

import base.divider.Divider

interface GetsRemaining {
    fun calculateRemaining(remaining: Divider, heirs: List<Heir>)
}