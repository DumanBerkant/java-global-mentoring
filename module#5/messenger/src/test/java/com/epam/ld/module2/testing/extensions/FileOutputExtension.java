package com.epam.ld.module2.testing.extensions;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestIdentifier;
import org.junit.platform.launcher.TestPlan;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class FileOutputExtension implements TestExecutionListener {
    private PrintWriter writer;

    @Override
    public void testPlanExecutionStarted(TestPlan testPlan) {
        try {
            File outputFile = new File(
                    "src/test/resources/test-results/"
                    + new Date().toString()
                    + ".txt" );
            writer = new PrintWriter(outputFile);
        } catch (IOException e) {

        }
    }

    @Override
    public void testPlanExecutionFinished(TestPlan testPlan) {
        if (writer != null) {
            writer.close();
        }
    }

    @Override
    public void executionFinished(TestIdentifier testIdentifier, TestExecutionResult result) {
        writer.println(testIdentifier.getDisplayName() + ": " + result.getStatus());
    }

}