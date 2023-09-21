package com.virtuslab.jd;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementFilter;
import java.io.CharArrayWriter;
import java.util.Set;

import static javax.tools.Diagnostic.Kind.ERROR;
import static javax.tools.Diagnostic.Kind.NOTE;

@SupportedAnnotationTypes("com.virtuslab.jd.*")
@SupportedSourceVersion(SourceVersion.RELEASE_21)
public class PrintElementsProcessor extends AbstractProcessor {
    private int roundCounter = 1;

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        messager()
                .printMessage(NOTE, "Running processor: %s. Round number: %s. Error raised: %s".formatted(
                        this.getClass().getSimpleName(),
                        roundCounter,
                        roundEnv.errorRaised())
                );

        annotations.forEach(annotation ->
                messager().printMessage(NOTE, "Annotation passed to processor %s".formatted(annotation)));

        roundEnv.getRootElements().forEach(rootElement ->
                messager().printMessage(NOTE, "Root element %s".formatted(rootElement), rootElement));

        var annotateTypedElements = ElementFilter.typesIn(roundEnv.getElementsAnnotatedWith(AllFieldsFinal.class));
        annotateTypedElements.forEach(this::printElements);

        if (!roundEnv.errorRaised()) {
            messager().printMessage(ERROR, "Artificial error to prove the point");
        }

        roundCounter++;
        return true;
    }

    private Messager messager() {
        return processingEnv.getMessager();
    }

    private void printElements(TypeElement element) {
        CharArrayWriter writer = new CharArrayWriter();
        processingEnv.getElementUtils().printElements(writer, element.getEnclosedElements().toArray(new Element[0]));

        messager().printMessage(
                NOTE,
                "Elements declared in file\n" + writer,
                element
        );
    }
}
