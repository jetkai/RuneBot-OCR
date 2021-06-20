package runebot.ocr.misc

import org.json.JSONObject
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.net.URL
import java.net.URLEncoder
import javax.net.ssl.HttpsURLConnection

class API {

    companion object {
        /**
         * Converts the Image to Text using the OCR API on the web
         * @param base64
         * @return
         * @throws Exception
         */
        @Throws(Exception::class)
        fun getOCRResults(base64: String): String {
            val endpoint = URL("https://api.ocr.space/parse/image") // runebot.OCR API Endpoint
            val con = endpoint.openConnection() as HttpsURLConnection
            con.requestMethod = "POST"
            con.doOutput = true
            con.setRequestProperty("User-Agent", "Mozilla/5.0")
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5")

            val postData = JSONObject()
            postData.put("apikey", "a41e8e316b88957")
            postData.put("isOverlayRequired", false)
            postData.put("base64Image", base64)
            postData.put("language", "eng")
            postData.put("OCREngine", 2)

            val outStream = DataOutputStream(con.outputStream)
            outStream.writeBytes(getPostDataString(postData))
            outStream.flush()
            outStream.close()

            val inStream = BufferedReader(InputStreamReader(con.inputStream))
            var inputLine : String?
            val response = StringBuilder()

            while (inStream.readLine().also { inputLine = it } != null)
                response.append(inputLine)
            inStream.close()
            val jsonObject = JSONObject(response.toString())
            //Grabs the output I want from the API
            println("OCR Response: $response")
            val parsedText = jsonObject.getJSONArray("ParsedResults").getJSONObject(0).getString("ParsedText");
            println("OCR Parsed Text: $parsedText")
            return parsedText
        }

        /**
         * Returns the post data, returned from the API
         * @param params
         * @return
         * @throws Exception
         */
        @Throws(Exception::class)
        private fun getPostDataString(params: JSONObject): String {
            val result = StringBuilder()
            var first = true
            val itr: Iterator<String> = params.keys()
            while (itr.hasNext()) {
                val key = itr.next()
                val value: Any = params.get(key)
                if (first) first = false else result.append("&")
                result.append(URLEncoder.encode(key, "UTF-8"))
                result.append("=")
                result.append(URLEncoder.encode(value.toString(), "UTF-8"))
            }
            return result.toString()
        }
    }
}