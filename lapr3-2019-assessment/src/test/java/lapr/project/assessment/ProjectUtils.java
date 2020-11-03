package lapr.project.assessment;

import lapr.project.assessment.scenario003.Scenario003Test;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProjectUtils {
    private static final Logger LOGGER = Logger.getLogger("ProjectUtils");

    public static String executeCommand(String command) {

        StringBuffer output = new StringBuffer();

        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader reader =
                    new BufferedReader(
                            new InputStreamReader(p.getInputStream()));

            String line = "";
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return output.toString();

    }

    /**
     * https://coderanch.com/t/614007/java/Dynamic-loading-class-external-jar
     *
     * @param jarPath
     * @param className
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Object getClass(String jarPath, String className) {
        File jarFile = new File(jarPath);
        Object instance = null;
        try {
            URL url = jarFile.toURI().toURL();
            String jarUrl = "jar:" + url + "!/";
            URL[] urls = {new URL(jarUrl)};
            URLClassLoader child = new URLClassLoader(urls);
            System.out.println(urls);

            System.out.println(className);
            Class<?> classToLoad =
                    Class.forName(className, true, child);
            instance = classToLoad.newInstance();
        } catch (MalformedURLException | ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
            LOGGER.log(Level.SEVERE,
                    String.valueOf(Scenario003Test.class.getName()), ex);
        }
        return instance;
    }

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

    /**
     * Get this application folder.
     *
     * @return This application folder.
     */
    public static String getApplicationFolder() {
        return System.getProperty("user.dir");
    }

    private static String getSeparator() {
        return File.separator;
    }
}
