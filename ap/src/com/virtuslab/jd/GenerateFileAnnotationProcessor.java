package com.virtuslab.jd;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.tools.FileObject;
import java.io.IOException;
import java.util.Set;

import static javax.tools.Diagnostic.Kind.ERROR;
import static javax.tools.Diagnostic.Kind.NOTE;
import static javax.tools.StandardLocation.SOURCE_OUTPUT;

@SupportedAnnotationTypes("com.virtuslab.jd.AllFieldsFinal")
@SupportedSourceVersion(SourceVersion.RELEASE_17)
public class GenerateFileAnnotationProcessor extends AbstractProcessor {
    private boolean processed = false;

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        processingEnv.getMessager()
                .printMessage(NOTE, "Running processor: %s".formatted(this.getClass().getSimpleName()));

        if (!processed) {
            try {
                writeTextFile();
                writeJavaFile();
                writeClassFile();
            } catch (IOException e) {
                processingEnv.getMessager().printMessage(ERROR, "Failed with error '%s'".formatted(e.getMessage()));
                e.printStackTrace();
            }
        }

        processed = true;
        return true;
    }

    private void writeTextFile() throws IOException {
        FileObject resource = processingEnv.getFiler().createResource(SOURCE_OUTPUT, "GDGPoznan", "Hello-World.txt");
        try (var writer = resource.openWriter()) {
            writer.append("Hello from file");
        }
    }

    private void writeJavaFile() throws IOException {
        FileObject resource = processingEnv.getFiler().createSourceFile("com.virtuslab.jd.GDGPoznan");
        try (var writer = resource.openWriter()) {
            writer.append("""
                    package com.virtuslab.jd;
                                        
                    public class GDGPoznan {
                        public static void main(String[] args) {
                            System.out.println("Hello world!");
                        }
                    }
                    """);
        }
    }

    private void writeClassFile() throws IOException {
        FileObject resource = processingEnv.getFiler().createClassFile("com.virtuslab.jd.GDGPoznanClass");
        try (var output = resource.openOutputStream()) {
            for (var hexCode : getClassContent()) {
                output.write(hexCode);
            }
            output.flush();
        }
    }

    private static int[] getClassContent() {
        int[] ints = {
                0xca,
                (int) 0xfe,
                0xba,
                (int) 0xbe,
                (int) 0x00,
                (int) 0x00,
                (int) 0x00,
                (int) '=',
                (int) 0x00,
                (int) 0x1d,
                (int) '\n',
                (int) 0x00,
                (int) 0x02,
                (int) 0x00,
                (int) 0x03,
                (int) 0x07,
                (int) 0x00,
                (int) 0x04,
                (int) 0x0c,
                (int) 0x00,
                (int) 0x05,
                (int) 0x00,
                (int) 0x06,
                (int) 0x01,
                (int) 0x00,
                0x10,
                (int) 'j',
                (int) 'a',
                (int) 'v',
                (int) 'a',
                (int) '/',
                (int) 'l',
                (int) 'a',
                (int) 'n',
                (int) 'g',
                (int) '/',
                (int) 'O',
                (int) 'b',
                (int) 'j',
                (int) 'e',
                (int) 'c',
                (int) 't',
                (int) 0x01,
                (int) 0x00,
                (int) 0x06,
                (int) '<',
                (int) 'i',
                (int) 'n',
                (int) 'i',
                (int) 't',
                (int) '>',
                (int) 0x01,
                (int) 0x00,
                (int) 0x03,
                (int) '(',
                (int) ')',
                (int) 'V',
                (int) '\t',
                (int) 0x00,
                (int) 0x08,
                (int) 0x00,
                (int) '\t',
                (int) 0x07,
                (int) 0x00,
                (int) '\n',
                (int) 0x0c,
                (int) 0x00,
                (int) 0x0b,
                (int) 0x00,
                (int) 0x0c,
                (int) 0x01,
                (int) 0x00,
                (int) 0x10,
                (int) 'j',
                (int) 'a',
                (int) 'v',
                (int) 'a',
                (int) '/',
                (int) 'l',
                (int) 'a',
                (int) 'n',
                (int) 'g',
                (int) '/',
                (int) 'S',
                (int) 'y',
                (int) 's',
                (int) 't',
                (int) 'e',
                (int) 'm',
                (int) 0x01,
                (int) 0x00,
                (int) 0x03,
                (int) 'o',
                (int) 'u',
                (int) 't',
                (int) 0x01,
                (int) 0x00,
                (int) 0x15,
                (int) 'L',
                (int) 'j',
                (int) 'a',
                (int) 'v',
                (int) 'a',
                (int) '/',
                (int) 'i',
                (int) 'o',
                (int) '/',
                (int) 'P',
                (int) 'r',
                (int) 'i',
                (int) 'n',
                (int) 't',
                (int) 'S',
                (int) 't',
                (int) 'r',
                (int) 'e',
                (int) 'a',
                (int) 'm',
                (int) ';',
                (int) 0x08,
                (int) 0x00,
                (int) 0x0e,
                (int) 0x01,
                (int) 0x00,
                (int) 0x1d,
                (int) 'H',
                (int) 'e',
                (int) 'l',
                (int) 'l',
                (int) 'o',
                (int) ' ',
                (int) 'w',
                (int) 'o',
                (int) 'r',
                (int) 'l',
                (int) 'd',
                (int) ' ',
                (int) 'f',
                (int) 'r',
                (int) 'o',
                (int) 'm',
                (int) ' ',
                (int) '.',
                (int) 'c',
                (int) 'l',
                (int) 'a',
                (int) 's',
                (int) 's',
                (int) ' ',
                (int) 'f',
                (int) 'i',
                (int) 'l',
                (int) 'e',
                (int) '!',
                (int) '\n',
                (int) 0x00,
                (int) 0x10,
                (int) 0x00,
                (int) 0x11,
                (int) 0x07,
                (int) 0x00,
                (int) 0x12,
                (int) 0x0c,
                (int) 0x00,
                (int) 0x13,
                (int) 0x00,
                (int) 0x14,
                (int) 0x01,
                (int) 0x00,
                (int) 0x13,
                (int) 'j',
                (int) 'a',
                (int) 'v',
                (int) 'a',
                (int) '/',
                (int) 'i',
                (int) 'o',
                (int) '/',
                (int) 'P',
                (int) 'r',
                (int) 'i',
                (int) 'n',
                (int) 't',
                (int) 'S',
                (int) 't',
                (int) 'r',
                (int) 'e',
                (int) 'a',
                (int) 'm',
                (int) 0x01,
                (int) 0x00,
                (int) 0x07,
                (int) 'p',
                (int) 'r',
                (int) 'i',
                (int) 'n',
                (int) 't',
                (int) 'l',
                (int) 'n',
                (int) 0x01,
                (int) 0x00,
                (int) 0x15,
                (int) '(',
                (int) 'L',
                (int) 'j',
                (int) 'a',
                (int) 'v',
                (int) 'a',
                (int) '/',
                (int) 'l',
                (int) 'a',
                (int) 'n',
                (int) 'g',
                (int) '/',
                (int) 'S',
                (int) 't',
                (int) 'r',
                (int) 'i',
                (int) 'n',
                (int) 'g',
                (int) ';',
                (int) ')',
                (int) 'V',
                (int) 0x07,
                (int) 0x00,
                (int) 0x16,
                (int) 0x01,
                (int) 0x00,
                (int) 0x1f,
                (int) 'c',
                (int) 'o',
                (int) 'm',
                (int) '/',
                (int) 'v',
                (int) 'i',
                (int) 'r',
                (int) 't',
                (int) 'u',
                (int) 's',
                (int) 'l',
                (int) 'a',
                (int) 'b',
                (int) '/',
                (int) 'j',
                (int) 'd',
                (int) '/',
                (int) 'G',
                (int) 'D',
                (int) 'G',
                (int) 'P',
                (int) 'o',
                (int) 'z',
                (int) 'n',
                (int) 'a',
                (int) 'n',
                (int) 'C',
                (int) 'l',
                (int) 'a',
                (int) 's',
                (int) 's',
                (int) 0x01,
                (int) 0x00,
                (int) 0x04,
                (int) 'C',
                (int) 'o',
                (int) 'd',
                (int) 'e',
                (int) 0x01,
                (int) 0x00,
                (int) 0x0f,
                (int) 'L',
                (int) 'i',
                (int) 'n',
                (int) 'e',
                (int) 'N',
                (int) 'u',
                (int) 'm',
                (int) 'b',
                (int) 'e',
                (int) 'r',
                (int) 'T',
                (int) 'a',
                (int) 'b',
                (int) 'l',
                (int) 'e',
                (int) 0x01,
                (int) 0x00,
                (int) 0x04,
                (int) 'm',
                (int) 'a',
                (int) 'i',
                (int) 'n',
                (int) 0x01,
                (int) 0x00,
                (int) 0x16,
                (int) '(',
                (int) '[',
                (int) 'L',
                (int) 'j',
                (int) 'a',
                (int) 'v',
                (int) 'a',
                (int) '/',
                (int) 'l',
                (int) 'a',
                (int) 'n',
                (int) 'g',
                (int) '/',
                (int) 'S',
                (int) 't',
                (int) 'r',
                (int) 'i',
                (int) 'n',
                (int) 'g',
                (int) ';',
                (int) ')',
                (int) 'V',
                (int) 0x01,
                (int) 0x00,
                (int) '\n',
                (int) 'S',
                (int) 'o',
                (int) 'u',
                (int) 'r',
                (int) 'c',
                (int) 'e',
                (int) 'F',
                (int) 'i',
                (int) 'l',
                (int) 'e',
                (int) 0x01,
                (int) 0x00,
                (int) 0x13,
                (int) 'G',
                (int) 'D',
                (int) 'G',
                (int) 'P',
                (int) 'o',
                (int) 'z',
                (int) 'n',
                (int) 'a',
                (int) 'n',
                (int) 'C',
                (int) 'l',
                (int) 'a',
                (int) 's',
                (int) 's',
                (int) '.',
                (int) 'j',
                (int) 'a',
                (int) 'v',
                (int) 'a',
                (int) 0x00,
                (int) '!',
                (int) 0x00,
                (int) 0x15,
                (int) 0x00,
                (int) 0x02,
                (int) 0x00,
                (int) 0x00,
                (int) 0x00,
                (int) 0x00,
                (int) 0x00,
                (int) 0x02,
                (int) 0x00,
                (int) 0x01,
                (int) 0x00,
                (int) 0x05,
                (int) 0x00,
                (int) 0x06,
                (int) 0x00,
                (int) 0x01,
                (int) 0x00,
                (int) 0x17,
                (int) 0x00,
                (int) 0x00,
                (int) 0x00,
                (int) 0x1d,
                (int) 0x00,
                (int) 0x01,
                (int) 0x00,
                (int) 0x01,
                (int) 0x00,
                (int) 0x00,
                (int) 0x00,
                (int) 0x05,
                (int) '*',
                (int) 0xb7,
                (int) 0x00,
                (int) 0x01,
                (int) 0xb1,
                (int) 0x00,
                (int) 0x00,
                (int) 0x00,
                (int) 0x01,
                (int) 0x00,
                (int) 0x18,
                (int) 0x00,
                (int) 0x00,
                (int) 0x00,
                (int) 0x06,
                (int) 0x00,
                (int) 0x01,
                (int) 0x00,
                (int) 0x00,
                (int) 0x00,
                (int) 0x03,
                (int) 0x00,
                (int) '\t',
                (int) 0x00,
                (int) 0x19,
                (int) 0x00,
                (int) 0x1a,
                (int) 0x00,
                (int) 0x01,
                (int) 0x00,
                (int) 0x17,
                (int) 0x00,
                (int) 0x00,
                (int) 0x00,
                (int) '%',
                (int) 0x00,
                (int) 0x02,
                (int) 0x00,
                (int) 0x01,
                (int) 0x00,
                (int) 0x00,
                (int) 0x00,
                (int) '\t',
                (int) 0xb2,
                (int) 0x00,
                (int) 0x07,
                (int) 0x12,
                (int) '\r',
                (int) 0xb6,
                (int) 0x00,
                (int) 0x0f,
                (int) 0xb1,
                (int) 0x00,
                (int) 0x00,
                (int) 0x00,
                (int) 0x01,
                (int) 0x00,
                (int) 0x18,
                (int) 0x00,
                (int) 0x00,
                (int) 0x00,
                (int) '\n',
                (int) 0x00,
                (int) 0x02,
                (int) 0x00,
                (int) 0x00,
                (int) 0x00,
                (int) 0x05,
                (int) 0x00,
                (int) 0x08,
                (int) 0x00,
                (int) 0x06,
                (int) 0x00,
                (int) 0x01,
                (int) 0x00,
                (int) 0x1b,
                (int) 0x00,
                (int) 0x00,
                (int) 0x00,
                (int) 0x02,
                (int) 0x00,
                0x1c
        };
        return ints;
    }
}
