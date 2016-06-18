package delight.npmpackage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.zip.ZipOutputStream;

public class NpmPackage {

    public static void perform(final ZipOutputStream target, final String[] npmDependencies) {

        try {
            // final File workDir = File.createTempFile("temp-file-name",
            // ".tmp");

            final File workDir = Files.createTempDirectory("npmwork", null).toFile();

        } catch (final IOException e) {
            throw new RuntimeException(e);
        }

    }

}
