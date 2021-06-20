package runebot.npc

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.io.IOException
import java.io.RandomAccessFile
import java.nio.ByteBuffer
import java.nio.channels.FileChannel

fun main() {

    val npcDataFile = main()::class.java.classLoader.getResource("data/npc-data.json")?.readText()
    val npcBonuses = main()::class.java.classLoader.getResource("data/npc_bonuses_685_cache.json")?.readText()
    val npcAttackSpeedFile = main()::class.java.classLoader.getResource("data/npc_attack_speeds_685_cache.json")?.readText()

    val npcDefinitions = Json {
        this.prettyPrint = true
        this.encodeDefaults = true
    }

    val npcBonusArray = npcDefinitions.decodeFromString<Array<NPCBonuses>>(npcBonuses!!)
    val npcDataArray = npcDefinitions.decodeFromString<Array<NPCData>>(npcDataFile!!)
    val npcAttackArray = npcDefinitions.decodeFromString<Array<NPCAttackSpeed>>(npcAttackSpeedFile!!)

    val npcBonusesMap = npcBonusArray.associateBy { it.id }
    val npcDataMap = npcDataArray.associateBy { it.id }
    val npcAttackMap = npcAttackArray.associateBy { it.id }

    val npcs = init().toMutableMap()

    for (i in 0 until 13657) {
        var npc = npcs[i]
        val npcBonus = npcBonusesMap[i]
        val npcData = npcDataMap[i]
        val npcAttackSpeed = npcAttackMap[i]
        if(npc == null) {
            npc = NPCDefinition(i)
            npcs[i] = npc
        }
        if(npcAttackSpeed != null)
            npc.attackSpeed = npcAttackSpeed.attackSpeed
        if (npcBonus != null)
            npc.bonuses = npcBonus.bonuses
        if(npcData != null) {
            npc.examine = npcData.examine
            npc.lifepoints = ((npcData.hpLevel * 10).toShort())
            npc.attackLevel = npcData.attackLevel
            npc.defenceLevel = npcData.defenseLevel
            npc.rangeLevel = npcData.rangeLevel
            npc.magicLevel = npcData.magicLevel
            npc.aggressiveRadius = npcData.aggressiveRadius
            npc.attackSound = npcData.attackSound
            npc.defenceSound = npcData.defenseSound
            npc.deathSound = npcData.deathSound
            if(npc.attackAnimation.toInt() == -1)
                npc.attackAnimation = npcData.attackAnim
            if(npc.defenceAnimation.toInt() == -1)
                npc.defenceAnimation = npcData.defenseAnim
            if(npc.deathAnimation.toInt() == -1)
                npc.deathAnimation = npcData.deathAnim
            if(!npc.usingRange && !npc.usingMagic)
                npc.usingMelee = true
            //TODO Remainder of these
            /* if(runebot.npc.attackAnimation != npcData.attackAnim) {
             println("${runebot.npc.id}:${runebot.npc.attackAnimation}:${npcData.attackAnim}")
            }
            var startGraphics: Short = 0,
            var projectileId: Short = 0,
            var endGraphics: Short = 0, */
        }
    }
    val encodedString = npcDefinitions.encodeToString(npcs.values.toTypedArray())
    File("local/final.json").writeText(encodedString)
}

/**
 * Initializes the NPC definitions.
 *
 * @throws IOException When an I/O error occurs.
 */
@Throws(IOException::class)

fun init() : Map<Int, NPCDefinition> {
    //NPCDefinitionPacker.pack();
    //System.out.println(CacheManager525.getAmountOfNpcs());
    val totalNpcs = 13657
    val definitions = mutableMapOf<Int, NPCDefinition>()
    println("Loading runebot.npc definitions...")
    val channel = RandomAccessFile("local/NPCDefinitions.bin", "r").channel
    val buffer: ByteBuffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size())
    for (i in 0 until totalNpcs) {
        val id = buffer.short.toInt()
        val def = NPCDefinition(i)
        if (id == -1) {
            definitions[i] = def
            continue
        }
        /*def.combatLevel =*/ buffer.short //CombatLevel
        def.examine = readRS2String(buffer)
        def.bonuses = ShortArray(18)
        for (x in 0..13) {
            def.bonuses[x] = buffer.short
        }
        def.lifepoints = buffer.short
        def.respawn = buffer.get()
        def.attackAnimation = buffer.short
        def.defenceAnimation = buffer.short
        def.deathAnimation = buffer.short
        def.strengthLevel = buffer.short
        def.attackLevel = buffer.short
        def.defenceLevel = buffer.short
        def.rangeLevel = buffer.short
        def.magicLevel = buffer.short
        def.attackSpeed = buffer.get()
        def.startGraphics = buffer.short
        def.projectileId = buffer.short
        def.endGraphics = buffer.short
        def.usingMelee = buffer.get().toInt() == 1
        def.usingRange = buffer.get().toInt() == 1
        def.usingMagic = buffer.get().toInt() == 1
        def.aggressive = buffer.get().toInt() == 1
        def.poisonImmune = buffer.get().toInt() == 1
        definitions[i] = def
        println(def)
    }
    channel.close()
    println("Loaded " + definitions.size + " runebot.npc definitions.")
    return definitions
}

fun readRS2String(buffer: ByteBuffer): String {
    val sb = StringBuilder()
    var b: Byte = 0
    while (buffer.remaining() > 0 && buffer.get().also { b = it }.toInt() != 0)
        sb.append(b.toInt().toChar())
    return sb.toString()
}

