package content.trivia

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.File

class Trivia {

    companion object {

        private lateinit var triviaDataArray : Array<TriviaData>

        fun init() {
            val triviaDataFile = File("./data/questions.json").readText()
            val json = Json {
                this.prettyPrint = true
                this.encodeDefaults = true
            }
            triviaDataArray = json.decodeFromString(triviaDataFile)
        }

        fun getAnswer(question : String): String {
            sortMatch(question)
            //Output for debugging purposes
            println(triviaDataArray.joinToString { triviaData: TriviaData -> triviaData.question + ":" + triviaData.matchAmount + "\n" })
            return triviaDataArray[triviaDataArray.size - 1].answer
        }

        private fun sortMatch(question : String) {
            val questionArray = question.split(" ")
            for(trivia in triviaDataArray) {
                val triviaQuestionArray = trivia.question.split(" ")
                val matchAmount = questionArray.indices.count { it < triviaQuestionArray.size && questionArray.contains(triviaQuestionArray[it]) }
                val percentage = (matchAmount.toDouble() / triviaQuestionArray.size) * 100
                trivia.matchAmount = percentage
            }
            triviaDataArray.sortBy { triviaData: TriviaData -> triviaData.matchAmount }
        }
    }

}