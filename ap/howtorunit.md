When in src directory

```shell
rm **/*.class 
javac -source 17 -target 17 com/virtuslab/jd/AllFieldsFinalAnnotationProcessor.java com/virtuslab/jd/GenerateFileAnnotationProcessor.java com/virtuslab/jd/PrintElementsProcessor.java
javac -source 17 -target 17 com/virtuslab/jd/Data.java -processor com.virtuslab.jd.AllFieldsFinalAnnotationProcessor
javac -source 17 -target 17 -XprintProcessorInfo -XprintRounds com/virtuslab/jd/Data.java -processor com.virtuslab.jd.AllFieldsFinalAnnotationProcessor
```


```shell
cleanup () {
  rm **/*.class 
  rm **/KielceJug.java
  rm -r **/KielceJug/
}

compile_processors () {
  javac -source 17 -target 17 com/virtuslab/jd/AllFieldsFinalAnnotationProcessor.java com/virtuslab/jd/GenerateFileAnnotationProcessor.java com/virtuslab/jd/PrintElementsProcessor.java
}

compile_with_processor () {
  compile_processors
  javac -source 17 -target 17 com/virtuslab/jd/Data.java -processor com.virtuslab.jd.PrintElementsProcessor -processor com.virtuslab.jd.GenerateFileAnnotationProcessor -processor com.virtuslab.jd.AllFieldsFinalAnnotationProcessor
}

compile_with_sourcegen () {
  compile_processors
  javac -source 17 -target 17 com/virtuslab/jd/Data.java -processor com.virtuslab.jd.GenerateFileAnnotationProcessor -XprintProcessorInfo -XprintRounds
}

compile_with_check () {
    compile_processors
    javac -source 17 -target 17 com/virtuslab/jd/Data.java -processor com.virtuslab.jd.AllFieldsFinalAnnotationProcessor
}

compile_with_print () {
    compile_processors
    javac -source 17 -target 17 com/virtuslab/jd/Data.java -processor com.virtuslab.jd.PrintElementsProcessor
}
```