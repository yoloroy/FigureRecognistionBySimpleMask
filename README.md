# FigureRecognistionBySimpleMask
:eyes: Recognizes the shapes in the picture by the mask

## :iphone: Image
<img src="https://github.com/yoloroy/FigureRecognistionBySimpleMask/blob/master/readmeRes/usageImage.gif" width="480" height="560">

## :hammer: Code
```kotlin
fun main() {
    println("Enter input file path:")
    val inputPath = readLine()!!

    val pic = ImageIO.read(File(inputPath))

    println("This is ${pic.figure.name}") // .figure starts recognizing
}
```

## :rocket:How to launch
* Download this project and launch in IDE, kotlin jvm

## :white_check_mark:TODO
* move algorithm to separate file
