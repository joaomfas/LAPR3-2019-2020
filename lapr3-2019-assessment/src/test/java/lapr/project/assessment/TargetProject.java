package lapr.project.assessment;

import java.io.File;

public class TargetProject {
    public static String getTargetProjectName() {
        return System.getProperty("target.project.name");
    }

    public static String getTargetProjectVersion() {
        return System.getProperty("target.project.version");
    }

    public static String getTargetGroupId() {
        return System.getProperty("target.project.groupId");
    }

    public static String getTargetArtifactId() {
        return System.getProperty("target.project.artifactId");
    }

    public static String getTargetRelativePath() {
        return System.getProperty("target.project.relative.path").replace("/"
                , File.separator);
    }

    public static Serviceable getServiceableObject() {
        String folder = ProjectUtils.getApplicationFolder()
                + getTargetRelativePath() + getTargetProjectName() +
                File.separator + "target" + File.separator
                + getTargetArtifactId() + "-"
                + getTargetProjectVersion() + ".jar";
        Serviceable facadeInstance = (Serviceable) ProjectUtils
                .getClass(folder, "lapr.project.assessment.Facade");
        return facadeInstance;
    }
}
