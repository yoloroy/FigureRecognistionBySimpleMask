# FigureRecognistionBySimpleMask
Recognizes the shapes in the picture by the mask :art: -> :symbols:

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

## :rocket:How to launch
* Download this project and launch in IDE, kotlin jvm

## :white_check_mark:TODO
* move algorithm to separate file
