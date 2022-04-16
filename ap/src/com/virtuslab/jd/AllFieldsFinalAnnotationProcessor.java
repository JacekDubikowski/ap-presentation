package com.virtuslab.jd;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import static javax.lang.model.element.Modifier.FINAL;
import static javax.tools.Diagnostic.Kind.ERROR;
import static javax.tools.Diagnostic.Kind.NOTE;

@SupportedAnnotationTypes("com.virtuslab.*")
@SupportedSourceVersion(SourceVersion.RELEASE_17)
public class AllFieldsFinalAnnotationProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        var annotatedElements = roundEnv.getElementsAnnotatedWith(AllFieldsFinal.class);
        annotatedElements.forEach(this::checkType);
        return true;
    }

    private void checkType(Element element) {
        printElements(element);
        handleNotFinals(element);
    }

    private void printElements(Element element) {
        CharArrayWriter writer = new CharArrayWriter();
        processingEnv.getElementUtils().printElements(writer, element.getEnclosedElements().toArray(new Element[0]));
        messager().printMessage(NOTE, writer.toString(), element);
    }

    private void handleNotFinals(Element element) {
        var nonFinalFields = fields(element).stream()
                .filter(this::isNotFinal)
                .toList();
        if (!nonFinalFields.isEmpty()) {
            raiseError(element, nonFinalFields);
        }
    }

    private List<? extends Element> fields(Element element) {
        return element.getEnclosedElements().stream().filter(innerElement -> innerElement.getKind().isField()).toList();
    }

    private boolean isNotFinal(Element element) {
        return !element.getModifiers().contains(FINAL);
    }

    private void raiseError(Element element, List<? extends Element> nonFinalFields) {
        var message = "Checking final fields failed on element %s, below field(s) are/is not final '%s'".formatted(element, nonFinalFields);
        messager().printMessage(ERROR, message, element);
    }

    private Messager messager() {
        return processingEnv.getMessager();
    }
}
