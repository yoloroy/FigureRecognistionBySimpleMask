import recognazingFigure.figure
import java.io.File
import javax.imageio.ImageIO

fun main() {
    println("Enter input file path:")
    val inputPath = readLine()!!

    val pic = ImageIO.read(File(inputPath))

    println("This is ${pic.figure.name}")
}
