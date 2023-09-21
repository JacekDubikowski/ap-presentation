When in src directory

```shell
rm **/*.class 
javac -source 21 -target 21 com/virtuslab/jd/AllFieldsFinalAnnotationProcessor.java com/virtuslab/jd/GenerateFileAnnotationProcessor.java com/virtuslab/jd/PrintElementsProcessor.java
javac -source 21 -target 21 com/virtuslab/jd/Data.java -processor com.virtuslab.jd.AllFieldsFinalAnnotationProcessor
javac -source 21 -target 21 -XprintProcessorInfo -XprintRounds com/virtuslab/jd/Data.java -processor com.virtuslab.jd.AllFieldsFinalAnnotationProcessor
```


```shell
cleanup () {
  rm **/*.class 
  rm **/SourceFourDevelopers.java
  rm -r **/FourDevelopers/
}

compile_processors () {
  javac -source 21 -target 21 com/virtuslab/jd/AllFieldsFinalAnnotationProcessor.java com/virtuslab/jd/GenerateFileAnnotationProcessor.java com/virtuslab/jd/PrintElementsProcessor.java
}

compile_with_processor () {
  compile_processors
  javac -source 21 -target 21 com/virtuslab/jd/Data.java -processor com.virtuslab.jd.PrintElementsProcessor -processor com.virtuslab.jd.GenerateFileAnnotationProcessor -processor com.virtuslab.jd.AllFieldsFinalAnnotationProcessor
}

compile_with_sourcegen () {
  compile_processors
  javac -source 21 -target 21  com/virtuslab/jd/NotAnnotatedData.java com/virtuslab/jd/Data.java com/virtuslab/jd/OtherData.java -processor com.virtuslab.jd.GenerateFileAnnotationProcessor -XprintProcessorInfo -XprintRounds
}

compile_with_check () {
    compile_processors
    javac -source 21 -target 21  com/virtuslab/jd/NotAnnotatedData.java com/virtuslab/jd/Data.java com/virtuslab/jd/OtherData.java -processor com.virtuslab.jd.AllFieldsFinalAnnotationProcessor
}

compile_with_print () {
    compile_processors
    javac -source 21 -target 21 com/virtuslab/jd/NotAnnotatedData.java com/virtuslab/jd/Data.java com/virtuslab/jd/OtherData.java -processor com.virtuslab.jd.PrintElementsProcessor
}
```