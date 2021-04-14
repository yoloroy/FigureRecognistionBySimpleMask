import org.junit.Test
import recognazingFigure.local.adapters.readImage
import recognazingFigure.core.*
import recognazingFigure.core.ColorMatrix
import recognazingFigure.local.figures.Figures
import recognazingFigure.local.figures.MASK_SIZES
import kotlin.test.assertEquals

class RecognizeFigureTest {
    @Test
    fun testTriangle() {
        val inputPath = "src/test/resources/samples/triangle.jpg"
        val pic = readImage(inputPath)

        assertEquals(Figures.Triangle, pic.figure)
    }

    @Test
    fun testSquare() {
        val inputPath = "src/test/resources/samples/square.jpg"
        val pic = readImage(inputPath)

        assertEquals(Figures.Square, pic.figure)
    }

    @Test
    fun testCircle() {
        val inputPath = "src/test/resources/samples/circle.png"
        val pic = readImage(inputPath)

        assertEquals(Figures.Circle, pic.figure)
    }

    @Test
    fun testFitting() {
        val inputPath = "src/test/resources/samples/triangle.jpg"
        val pic = readImage(inputPath)
        val compressedPic = ColorMatrix(MASK_SIZES, MASK_SIZES).apply {
            loadFrom(pic)

            fitToTheEdges()
        }

        val (tl, br) = compressedPic.findBounds()

        assertEquals(Point(0, 0), tl)
        assertEquals(Point(compressedPic.width - 1, compressedPic.height - 1), br)
    }
}
