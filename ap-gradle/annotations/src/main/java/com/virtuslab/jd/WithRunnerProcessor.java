package com.virtuslab.jd;

import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.Name;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import static javax.tools.Diagnostic.Kind.ERROR;
import static javax.tools.Diagnostic.Kind.NOTE;

@SupportedAnnotationTypes("com.virtuslab.*")
@SupportedSourceVersion(SourceVersion.RELEASE_17)
public class WithRunnerProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        messager().printMessage(NOTE, "Starting work");
        var annotatedElements = roundEnv.getElementsAnnotatedWith(WithRunner.class);
        annotatedElements.forEach(this::generateRunner);
        return false;
    }

    private void generateRunner(Element element) {
        var type = element.getAnnotationsByType(WithRunner.class)[0];
        var methodName = type.method();
        validateMethodIsPresent(element, methodName);
        var noArgConstructor = getPublicConstructors(element).stream()
                .filter(e -> e.getParameters().size() == 0)
                .findFirst();
        noArgConstructor.ifPresentOrElse(
                __ -> generateRunner(element, methodName),
                () -> messager().printMessage(ERROR, "There is no no-arg constructor", element)
        );
    }

    private void generateRunner(Element element, String methodName) {
        try {
            MethodSpec main = MethodSpec.methodBuilder("main")
                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                    .returns(void.class)
                    .addParameter(String[].class, "args")
                    .addStatement("var instance = new $T()", element)
                    .addStatement("instance.$L()", methodName)
                    .build();

            TypeSpec helloWorld = TypeSpec.classBuilder(element.getSimpleName().toString() + "Runner")
                    .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                    .addMethod(main)
                    .build();

            JavaFile javaFile = JavaFile.builder(packageName(element).toString(), helloWorld)
                    .build();
            javaFile.writeTo(processingEnv.getFiler());
        } catch (IOException exception) {
            messager().printMessage(ERROR, "Sth went badly.", element);
        }
    }

    private Name packageName(Element element) {
        while(element.getEnclosingElement().getKind() != ElementKind.PACKAGE) {
            element = element.getEnclosingElement();
        }
        var packageElement = (PackageElement) element.getEnclosingElement();
        return packageElement.getQualifiedName();
    }

    private List<ExecutableElement> getPublicConstructors(Element element) {
        return  element.getEnclosedElements().stream()
                .filter(it -> it.getKind() == ElementKind.CONSTRUCTOR && it.getModifiers().contains(Modifier.PUBLIC))
                .map(it -> (ExecutableElement) it)
                .toList();
    }

    private void validateMethodIsPresent(Element annotatedElement, String method) {
        if (method.equals("")) {
            messager().printMessage(ERROR, "Method to run not specified", annotatedElement);
        }
    }

    private Messager messager() {
        return processingEnv.getMessager();
    }
}
