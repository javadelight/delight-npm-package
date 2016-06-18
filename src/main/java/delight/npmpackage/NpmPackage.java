package delight.npmpackage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.zip.ZipOutputStream;

import de.mxro.process.Spawn;

public class NpmPackage {

    public static void perform(final ZipOutputStream target, final String[] npmDependencies) {

        try {
            // final File workDir = File.createTempFile("temp-file-name",
            // ".tmp");

            final File workDir = Files.createTempDirectory("npmwork").toFile();

            for (final String dependency : npmDependencies) {
                System.out.println(Spawn.sh("npm install " + dependency + " --save"));
            }

        } catch (final IOException e) {
            throw new RuntimeException(e);
        }

    }

}
