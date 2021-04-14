import recognazingFigure.core.figure
import recognazingFigure.local.adapters.readImage

fun main() {
    println("Enter input file path:")
    val inputPath = readLine()!!

    val pic = readImage(inputPath)

    println("This is ${pic.figure.name}")
}
