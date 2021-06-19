package ocr

object Constants {

    /**
     * TODO: Enabled Events
     * Improve the way EnabledEvents is handled, some kind of configuration file or argument
     *
     * TODO: Level based Event actions
     * Read best option to do (from dumped JSON), for the current level of the player
     */

    //Set Enabled Events
    const val trainCombatEventEnabled : Boolean = true
    const val triviaEventEnabled : Boolean = true
    const val mineEventEnabled : Boolean = false
    const val buryEventEnabled : Boolean = false
    const val chopEventEnabled : Boolean = false
    const val fishEventEnabled : Boolean = false
    const val cookEventEnabled : Boolean = false
    const val thievingEventEnabled : Boolean = false
    const val pickPocketEventEnabled : Boolean = false
    const val smeltEventEnabled : Boolean = false
    const val smithEventEnabled : Boolean = false

    //Used in all Events & OCR.kt
    const val discordName = "kai"

    //Used in TrainCombatEvent.kt, TEMP
    const val combatType = "melee" //NumPad1 = Melee, NumPad2 = Magic, NumPad3 = Ranged
    const val monster = "abyssal demon"

    //Used in MineEvent.kt, TEMP
    const val rock = "coal"

    //Used in BuryEvent.kt, TEMP
    const val bones = "bones"

    //Used in ChopEvent.kt, TEMP
    const val logs = "log"

    //Used in FishEvent.kt & CookEvent.kt, TEMP
    const val fish = "raw swordfish"

    //Used in PickPocketEvent.kt, TEMP
    const val npc = "man"

    //Used in SmeltEvent.kt, TEMP
    const val bar = "bronze bar"

    //Used in SmithEvent.kt, TEMP
    const val smithItem = "bronze dagger"

}