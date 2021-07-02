package runebot.ocr

import kotlin.random.Random

object Constants {

    /**
     * TODO: Enabled Events
     * Improve the way EnabledEvents is handled, some kind of configuration file or argument
     *
     * TODO: Level based Event actions
     * Read best option to do (from dumped JSON), for the current level of the player
     */

    //Set Enabled Events
    const val trainCombatEventEnabled : Boolean = false
    const val triviaEventEnabled : Boolean = true
    const val mineEventEnabled : Boolean = false
    const val buryEventEnabled : Boolean = false
    const val chopEventEnabled : Boolean = false
    const val fishEventEnabled : Boolean = false
    const val cookEventEnabled : Boolean = true
    const val thievingEventEnabled : Boolean = false
    const val pickPocketEventEnabled : Boolean = false
    const val smeltEventEnabled : Boolean = false
    const val smithEventEnabled : Boolean = false
    const val barrowsEventEnabled : Boolean = false
    const val alchEventEnabled : Boolean = false
    const val mEventEnabled : Boolean = false

    //Event Timers (in seconds)
    fun waitTime() = Random.nextInt(1920, 2460)
    fun alchWaitTime() = Random.nextInt(4000, 4715)
    fun longWaitTime() = Random.nextInt(7600, 8200)
    fun triviaWaitTime() = Random.nextInt(22000, 24000)

    //Used in all Events & OCR.kt
    const val discordName = "kai"

    //Used in TrainCombatEvent.kt, TEMP
    const val combatType = "melee" //NumPad1 = Melee, NumPad2 = Magic, NumPad3 = Ranged
    const val monster = "general graardor" //Common: general graardor
    const val time = 120

    //Used in MineEvent.kt, TEMP
    const val rock = "coal"

    //Used in BuryEvent.kt, TEMP
    const val bones = "bones"

    //Used in ChopEvent.kt, TEMP
    const val logs = "log"

    //Used in FishEvent.kt & CookEvent.kt, TEMP
    const val fish = "monkfish"

    //Used in PickPocketEvent.kt, TEMP
    const val npc = "man"

    //Used in SmeltEvent.kt, TEMP
    const val bar = "bronze bar"

    //Used in SmithEvent.kt, TEMP
    const val smithItem = "bronze dagger"

    //Used in AlchEvent.kt, TEMP
    const val alchItem = "slayer gloves"
    const val alchAmount = 800

}