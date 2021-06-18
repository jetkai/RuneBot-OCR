package npc

import kotlinx.serialization.Serializable

@Serializable
class NPCData(
    val id : Int,
    val hpLevel : Short,
    val attackLevel : Short,
    val defenseLevel : Short,
    val magicLevel : Short,
    val rangeLevel : Short,
    val equipmentBonuses : ShortArray,
    val slayerXpBonus : Double,
    val meleeAttackStyle : Short,
    val deathDelay : Short,
    val respawnDelay : Short,
    val aggressive_type : Short,
    val aggression_range : Short,
    val poisonImmune : Boolean,
    val examine : String? = null,
    val attackAnim : Short,
    val deathAnim : Short,
    val defenseAnim : Short,
    val defenseSound : Short = -1,
    val attackSound : Short = -1,
    val deathSound : Short = -1,
    val aggressiveRadius : Short,
    val maxHit : Short,


)