import java.awt.Color
import java.awt.Point
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

val classLoader: ClassLoader get() = Thread.currentThread().contextClassLoader!!

const val MASK_SIZES = 16

enum class Figures {
    Triangle {
        override val path = "figures/triangle_mask.png"
    },
    Circle {
        override val path = "figures/circle_mask.png"
    },
    Quad {
        override val path = "figures/quad_mask.png"
    };

    abstract val path: String

    companion object {
        fun asList() = listOf(Triangle, Circle, Quad)
    }
}

fun main() {
    println("Enter input file path:")
    val inputPath = readLine()!!

    val pic = ImageIO.read(File(inputPath))

    println("This is ${pic.figure.name}")
}

val BufferedImage.figure: Figures get() {
    val compressedPic = BufferedImage(MASK_SIZES, MASK_SIZES, BufferedImage.TYPE_INT_ARGB).apply {
        loadFrom(this@figure)

        fitToTheEdges()
    }

    val points = buildList<Point> {
        compressedPic.foreachIndexed { x, y ->
            if (equals(Color.BLACK))
                add(Point(x, y))
        }
    }

    return Figures.asList().maxByOrNull { figure ->
        val figurePath = classLoader.getResource(figure.path)!!.toURI()
        val figurePic = ImageIO.read(File(figurePath))

        getSimilarityValue(points, figurePic)
    }!!
}

private fun getSimilarityValue(points: List<Point>, pic: BufferedImage): Double {
    var counter = 0
    points.forEach {
        if (pic.getRGB(it.x, it.y).color.rgb == Color.BLACK.rgb)
            counter++
    }

    return counter.toDouble() / points.size
}

private fun BufferedImage.loadFrom(pic: BufferedImage) {
    val sizeRatio = Point(pic.width / MASK_SIZES, pic.height / MASK_SIZES)

    pic.foreachIndexed { xi, yi ->
        val x = (xi.toDouble() / sizeRatio.x).toInt().let {
            if (it >= MASK_SIZES)
                MASK_SIZES - 1
            else
                it
        }
        val y = (yi.toDouble() / sizeRatio.y).toInt().let {
            if (it >= MASK_SIZES)
                MASK_SIZES - 1
            else
                it
        }

        setRGB(x, y, Color.WHITE.rgb)
        if (red < 128 && green < 128 && blue < 128)
            setRGB(x, y, Color.BLACK.rgb)
    }
}

fun BufferedImage.fitToTheEdges() {
    val (topLeft, bottomRight) = findBounds()
    val boundsSize = bottomRight - topLeft + Point(1, 1)
    val boundsRatioX = MASK_SIZES / (boundsSize.getX())
    val boundsRatioY = MASK_SIZES / (boundsSize.getY())

    val matrix = Array(MASK_SIZES) { Array(MASK_SIZES) { 0xffffff } }

    foreachIndexed { x, y ->
        var point = Point(x, y)

        if (point in topLeft to bottomRight && rgb == Color.BLACK.rgb) {
            point -= topLeft
            point.x = (point.x * boundsRatioX).toInt().let {
                if (it >= MASK_SIZES)
                    MASK_SIZES - 1
                else
                    it
            }
            point.y = (point.y * boundsRatioY).toInt().let {
                if (it >= MASK_SIZES)
                    MASK_SIZES - 1
                else
                    it
            }

            matrix[point] = Color.BLACK.rgb
        }
    }

    foreachIndexed { x, y ->
        setRGB(x, y, matrix[y][x])
    }
}

fun BufferedImage.findBounds(): Pair<Point, Point> {
    var topBound = height
    var bottomBound = 0
    var leftBound = width
    var rightBound = 0

    foreachIndexed { x, y ->
        if (rgb == Color.BLACK.rgb) {
            if (topBound > y) topBound = y
            if (bottomBound < y) bottomBound = y
            if (leftBound > x) leftBound = x
            if (rightBound < x) rightBound = x
        }
    }

    return Point(leftBound, topBound) to Point(rightBound, bottomBound)
}

fun BufferedImage.foreachIndexed(callback: Color.(x: Int, y: Int) -> Unit) {
    for (x in 0 until width)
        for (y in 0 until height)
            getRGB(x, y).color.callback(x, y)
}

val Int.color: Color get() = Color(this shr 16 and 0xFF, this shr 8 and 0xFF, this shr 0 and 0xFF)

private operator fun Point.plus(other: Point): Point = Point(this.x + other.x, this.y + other.y)

private operator fun Point.minus(other: Point): Point = Point(this.x - other.x, this.y - other.y)

private operator fun Point.div(i: Int): Point = Point(x / i, y / i)

operator fun <T> Array<Array<T>>.set(point: Point, value: T) {
    this[point.y][point.x] = value
}

private operator fun Pair<Point, Point>.contains(point: Point): Boolean =
    point.x in first.x..second.x && point.y in first.y..second.y

inline fun <E> buildList(block: MutableList<E>.() -> Unit): MutableList<E> = mutableListOf<E>().apply(block)
