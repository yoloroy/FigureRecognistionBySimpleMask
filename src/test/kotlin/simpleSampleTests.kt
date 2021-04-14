import org.junit.Test
import recognazingFigure.Figures
import recognazingFigure.figure
import java.io.File
import javax.imageio.ImageIO
import kotlin.test.assertEquals

class RecognizeFigureTest {
    @Test
    fun testAll() {
        testTriangle()
        testSquare()
        testCircle()
    }
}

fun testTriangle() {
    val inputPath = "src/test/resources/samples/triangle.jpg"
    val pic = ImageIO.read(File(inputPath))

    assertEquals(pic.figure, Figures.Triangle)
}

fun testSquare() {
    val inputPath = "src/test/resources/samples/square.jpg"
    val pic = ImageIO.read(File(inputPath))

    assertEquals(pic.figure, Figures.Square)
}

fun testCircle() {
    val inputPath = "src/test/resources/samples/circle.png"
    val pic = ImageIO.read(File(inputPath))

    assertEquals(pic.figure, Figures.Circle)
}
