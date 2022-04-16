When in src directory

```shell
$ rm **/*.class
$ javac -source 17 -target 17 -XprintProcessorInfo -XprintRounds com/virtuslab/jd/AllFieldsFinalAnnotationProcessor.java 
$ javac -source 17 -target 17 -XprintProcessorInfo -XprintRounds com/virtuslab/jd/Data.java -processor com.virtuslab.jd.AllFieldsFinalAnnotationProcessor
```
