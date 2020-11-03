package lapr.project.assessment.setup;

import lapr.project.assessment.ProjectUtils;
import lapr.project.assessment.TargetProject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Run this project using mvn -Dtest=*SetupTest,Scenario*Test test.
 */
public class ProjectSetupTest {
    public static void loadEnvironmentVariables() {
        try {
            Properties properties =
                    new Properties(System.getProperties());
            InputStream input = new FileInputStream(
                    "target" + getSeparator() + "test-classes" + getSeparator()
                            + "application.properties");
            properties.load(input);
            input.close();
            System.setProperties(properties);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @BeforeAll
    public static void setup() {
        ProjectUtils.loadEnvironmentVariables();

        String command =
                "mvn -f " + ProjectUtils.getApplicationFolder()
                        + getSeparator() + ".." + getSeparator() + TargetProject
                        .getTargetProjectName() + getSeparator() +
                        "pom.xml package";

        if ("win".equalsIgnoreCase(System.getProperty("os.name").substring(0,
                3))) {
            command = "cmd /c" + command;
        }

        String output = ProjectUtils.executeCommand(command);

        //System.out.println(output);
    }

    private static String getSeparator() {
        return File.separator;
    }

    @Test
    public void test() {
        System.out.println("Project " + TargetProject.getTargetProjectName()
                + " has been packaged.");
    }
}
