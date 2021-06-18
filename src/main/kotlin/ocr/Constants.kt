package ocr

object Constants {

    //Set Enabled Events
    const val trainCombatEventEnabled : Boolean = true
    const val triviaEventEnabled : Boolean = true
    const val mineEventEnabled : Boolean = false

    //Used in all Events & OCR.kt
    const val discordName = "kai"

    //Used for TrainCombatEvent.kt
    const val combatType = "melee" //NumPad1 = Melee, NumPad2 = Magic, NumPad3 = Ranged
    const val monster = "rock crab"

    //Used for MineEvent.kt
    const val rock = "coal"

}