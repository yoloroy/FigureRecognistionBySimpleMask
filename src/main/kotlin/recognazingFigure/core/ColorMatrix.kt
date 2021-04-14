package recognazingFigure.core

import recognazingFigure.local.adapters.Color
import recognazingFigure.local.adapters.color

typealias ColorMatrix = Array<Array<Color>>

fun ColorMatrix(width: Int, height: Int): ColorMatrix = Array(height) { Array(width) { Color(0) } }

val ColorMatrix.height get() = size
val ColorMatrix.width get() = first().size

fun ColorMatrix.foreachIndexed(callback: Color.(x: Int, y: Int) -> Unit) {
    for (x in 0 until width)
        for (y in 0 until height)
            getRGB(x, y).color.callback(x, y)
}

fun ColorMatrix.getRGB(x: Int, y: Int) = get(x, y).rgb

operator fun ColorMatrix.get(x: Int, y: Int) = get(y)[x]

fun ColorMatrix.setRGB(x: Int, y: Int, rgb: Int) = set(Point(x, y), rgb.color)

operator fun <T> Array<Array<T>>.set(point: Point, value: T) {
    this[point.y][point.x] = value
}
