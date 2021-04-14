package recognazingFigure.local.adapters

import recognazingFigure.core.ColorMatrix
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

var readImage: (String) -> ColorMatrix = { ImageIO.read(File(it)).toColorMatrix() }

fun BufferedImage.foreachIndexed(callback: Color.(x: Int, y: Int) -> Unit) {
    for (x in 0 until width)
        for (y in 0 until height)
            getRGB(x, y).color.callback(x, y)
}

fun BufferedImage.toColorMatrix(): ColorMatrix =
    ColorMatrix(height, width).apply {
        this@toColorMatrix.foreachIndexed { x, y ->
            this@apply[y][x] = this
        }
    }
