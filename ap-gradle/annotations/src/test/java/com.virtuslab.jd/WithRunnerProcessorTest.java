package com.virtuslab.jd;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class WithRunnerProcessorTest {
    @Test
    void thereShouldBeTestClassRunnerClassPresent() {
        assertDoesNotThrow(() -> Class.forName(TestClass.class.getCanonicalName()+ "Runner"));
    }
}
