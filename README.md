# FigureRecognistionBySimpleMask
Recognizes the shapes in the picture by the mask :art: -> :symbols:

## :rocket:How to launch
* Download this project and launch in IDE, kotlin jvm
* Configure files in [recognistionFigure.local.adapters package](https://github.com/yoloroy/FigureRecognistionBySimpleMask/tree/master/src/main/kotlin/recognazingFigure/local/adapters) for your platform, Java/Android/Native/Korge/...
* Configure file in [recognistionFigure.local.figures package](https://github.com/yoloroy/FigureRecognistionBySimpleMask/tree/master/src/main/kotlin/recognazingFigure/local/figures) for your resources

## :iphone: Image
<img src="https://github.com/yoloroy/FigureRecognistionBySimpleMask/blob/master/readmeRes/usageImage.gif" width="480" height="560">

## :hammer: Code
```kotlin
fun main() {
    println("Enter input file path:")
    val inputPath = readLine()!!

    val pic = ImageIO.read(File(inputPath))

    // .figure starts recognizing and returns enum object
    println("This is ${pic.figure.name}")
}
```

* Samples: https://github.com/yoloroy/FigureRecognistionBySimpleMask/tree/master/src/main/resources/samples
* Masks: https://github.com/yoloroy/FigureRecognistionBySimpleMask/tree/master/src/main/resources/figures

## :white_check_mark:TODO
* unite compression and fitting for better work
* move Color to core
* add adapters for different platforms in platform-specific branches
