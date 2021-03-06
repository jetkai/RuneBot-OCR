package runebot.ocr.misc

import sun.misc.BASE64Encoder
import java.awt.Rectangle
import java.awt.Robot
import java.awt.Toolkit
import java.awt.geom.AffineTransform
import java.awt.image.AffineTransformOp
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import javax.imageio.ImageIO

class CaptureScreen {

    companion object {

        @Throws(IOException::class)
        private fun captureScreenRegion(): BufferedImage {
            val robot = Robot()
            val screenSize = Toolkit.getDefaultToolkit().screenSize
            return robot.createScreenCapture(Rectangle(0, 0, screenSize.width, screenSize.height))
        }

        /**
         * Increases the size of the image, so the OCR can read it much better
         */
        @Throws(IOException::class)
        private fun scaleImage(image: BufferedImage): BufferedImage {
            val width = image.width
            val height = image.height
            var scaledImage = BufferedImage(width * 1, height * 1, BufferedImage.TYPE_INT_ARGB)
            val at = AffineTransform.getScaleInstance(1.0, 1.0)
            val ato = AffineTransformOp(at, AffineTransformOp.TYPE_BICUBIC)
            scaledImage = ato.filter(image, scaledImage)
            ImageIO.write(scaledImage, "PNG", File("finish.png")) //Outputs a preview to see if it looks okay
            return scaledImage
        }

        /**
         * Encodes Image to Base64 (direct upload to the API instead of using boring links)
         * @param image
         * @param type
         * @return
         */
        private fun encodeToString(image: BufferedImage?, type: String?): String? {
            val imageString: String?
            val bos = ByteArrayOutputStream()
            ImageIO.write(image, type, bos)
            val imageBytes = bos.toByteArray()
            val encoder = BASE64Encoder()
            imageString = encoder.encode(imageBytes)
            bos.close()
            return imageString
        }

        fun getBase64Encoded(): String {
            val extension = "png"
            val image = scaleImage(captureScreenRegion()) // Increases size by 3x && Turns image into black & white
            return "data:image/" + extension + ";base64," + encodeToString(image, extension)
        }
    }

}