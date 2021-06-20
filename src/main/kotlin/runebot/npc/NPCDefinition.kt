package runebot.npc

import kotlinx.serialization.Serializable

@Serializable
class NPCDefinition(
    var id : Int,
    var bonuses: ShortArray = ShortArray(18),
    var examine: String? = null,
    var lifepoints: Short = 10,
    var respawn: Byte = 1,
    var attackAnimation: Short = -1,
    var defenceAnimation: Short = -1,
    var deathAnimation: Short = -1,
    var strengthLevel: Short = 1,
    var attackLevel: Short = 1,
    var defenceLevel: Short = 1,
    var rangeLevel: Short = 1,
    var magicLevel: Short = 1,
    var attackSpeed: Byte = 4,
    var startGraphics: Short = -1,
    var projectileId: Short = -1,
    var endGraphics: Short = -1,
    var usingMelee: Boolean = false,
    var usingRange: Boolean = false,
    var usingMagic: Boolean = false,
    var poisonImmune: Boolean = false,
    var aggressive: Boolean = false,
    var aggressiveRadius: Short = 0,
    var attackSound: Short = -1,
    var defenceSound: Short = 513,
    var deathSound: Short = 512
)
