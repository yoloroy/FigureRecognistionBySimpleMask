import org.junit.Test
import recognazingFigure.*
import java.awt.Point
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import kotlin.test.assertEquals

class RecognizeFigureTest {
    @Test
    fun testTriangle() {
        val inputPath = "src/test/resources/samples/triangle.jpg"
        val pic = ImageIO.read(File(inputPath))

        assertEquals(pic.figure, Figures.Triangle)
    }

    @Test
    fun testSquare() {
        val inputPath = "src/test/resources/samples/square.jpg"
        val pic = ImageIO.read(File(inputPath))

        assertEquals(pic.figure, Figures.Square)
    }

    @Test
    fun testCircle() {
        val inputPath = "src/test/resources/samples/circle.png"
        val pic = ImageIO.read(File(inputPath))

        assertEquals(pic.figure, Figures.Circle)
    }

    @Test
    fun testFitting() {
        val inputPath = "src/test/resources/samples/triangle.jpg"
        val pic = ImageIO.read(File(inputPath))
        val compressedPic = BufferedImage(MASK_SIZES, MASK_SIZES, BufferedImage.TYPE_INT_ARGB).apply {
            loadFrom(pic)

            fitToTheEdges()
        }

        val (tl, br) = compressedPic.findBounds()

        assertEquals(tl, Point(0, 0))
        assertEquals(br, Point(compressedPic.width - 1, compressedPic.height - 1))
    }
}
