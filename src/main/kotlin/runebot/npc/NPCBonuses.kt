package runebot.npc

import kotlinx.serialization.Serializable

@Serializable
class NPCBonuses(val id: Int, val bonuses: ShortArray)
