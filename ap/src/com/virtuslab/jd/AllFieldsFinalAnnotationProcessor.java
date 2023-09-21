package com.virtuslab.jd;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementFilter;
import java.util.Set;

import static javax.lang.model.element.Modifier.FINAL;
import static javax.tools.Diagnostic.Kind.ERROR;
import static javax.tools.Diagnostic.Kind.NOTE;

@SupportedAnnotationTypes("com.virtuslab.jd.AllFieldsFinal")
@SupportedSourceVersion(SourceVersion.RELEASE_21)
public class AllFieldsFinalAnnotationProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        var annotateTypedElements = ElementFilter.typesIn(roundEnv.getElementsAnnotatedWith(AllFieldsFinal.class));
        annotateTypedElements.forEach(this::checkType);
        return true;
    }

    private void checkType(TypeElement element) {
        var nonFinalFields = ElementFilter.fieldsIn(element.getEnclosedElements())
                .stream()
                .filter(childrenElem -> !childrenElem.getModifiers().contains(FINAL))
                .toList();

        for (var nonFinal : nonFinalFields) {
            processingEnv.getMessager().printMessage(ERROR, "Failed for given element.", nonFinal);
        }
    }
}
