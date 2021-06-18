package ocr.trivia

import kotlinx.serialization.Serializable

@Serializable
class TriviaData(val question : String, val answer : String, var matchAmount : Double = 0.0)