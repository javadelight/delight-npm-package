package delight.npmpackage;

import java.io.File;

import org.zeroturnaround.zip.ZipUtil;

import de.mxro.file.Jre.FilesJre;
import de.mxro.process.Spawn;

public class NpmPackage {

    public static void perform(final String indexJs, final String packageJson, final File target,
            final String[] npmDependencies) {

        try {
            // final File workDir = File.createTempFile("temp-file-name",
            // ".tmp");

            final File workDir = new File("/home/adminuser/Desktop/tmp");
            // Files.createTempDirectory("npmwork").toFile();

            FilesJre.wrap(workDir).assertFile("index.js").setText(indexJs);
            FilesJre.wrap(workDir).assertFile("package.json").setText(packageJson);

            for (final String dependency : npmDependencies) {
                System.out.println(Spawn.sh(workDir, "npm install " + dependency + " --save"));
            }

            ZipUtil.pack(workDir, target);

        } catch (final Exception e) {
            throw new RuntimeException(e);
        }

    }

}
