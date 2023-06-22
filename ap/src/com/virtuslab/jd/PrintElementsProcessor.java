package com.virtuslab.jd;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementFilter;
import java.io.CharArrayWriter;
import java.util.Set;

import static javax.tools.Diagnostic.Kind.NOTE;

@SupportedAnnotationTypes("com.virtuslab.jd.AllFieldsFinal")
@SupportedSourceVersion(SourceVersion.RELEASE_17)
public class PrintElementsProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        processingEnv.getMessager()
                .printMessage(NOTE, "Running processor: %s".formatted(this.getClass().getSimpleName()));

        var annotateTypedElements = ElementFilter.typesIn(roundEnv.getElementsAnnotatedWith(AllFieldsFinal.class));
        annotateTypedElements.forEach(this::printElements);
        return true;
    }

    private void printElements(TypeElement element) {
        CharArrayWriter writer = new CharArrayWriter();
        processingEnv.getElementUtils().printElements(writer, element.getEnclosedElements().toArray(new Element[0]));

        AnnotationMirror allFieldFinal = element.getAnnotationMirrors().get(0);
        processingEnv.getMessager().printMessage(
                NOTE,
                "Elements declared in file\n" + writer,
                element,
                allFieldFinal
        );
    }
}
