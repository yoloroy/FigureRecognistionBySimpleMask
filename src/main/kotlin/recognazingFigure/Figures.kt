package recognazingFigure

const val MASK_SIZES = 16

enum class Figures {
    Triangle {
        override val path = "figures/triangle_mask.png"
    },
    Circle {
        override val path = "figures/circle_mask.png"
    },
    Square {
        override val path = "figures/square_mask.png"
    };

    abstract val path: String
    

    companion object {
        fun asList() = listOf(Triangle, Circle, Square)
    }
}