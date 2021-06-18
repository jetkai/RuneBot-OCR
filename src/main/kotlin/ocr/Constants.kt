package ocr

object Constants {

    //Set Enabled Events TODO - Improve this
    const val trainCombatEventEnabled : Boolean = false
    const val triviaEventEnabled : Boolean = true
    const val mineEventEnabled : Boolean = false
    const val buryEventEnabled : Boolean = false
    const val chopEventEnabled : Boolean = false
    const val fishEventEnabled : Boolean = false
    const val cookEventEnabled : Boolean = false
    const val thievingEventEnabled : Boolean = true

    //Used in all Events & OCR.kt
    const val discordName = "kai"

    //Used in TrainCombatEvent.kt
    const val combatType = "melee" //NumPad1 = Melee, NumPad2 = Magic, NumPad3 = Ranged
    const val monster = "rock crab"

    //Used in MineEvent.kt
    const val rock = "coal"

    //Used in BuryEvent.kt
    const val bones = "bones"

    //Used in ChopEvent.kt
    const val logs = "log"

    //Used in FishEvent.kt & CookEvent.kt
    const val fish = "raw swordfish"

}