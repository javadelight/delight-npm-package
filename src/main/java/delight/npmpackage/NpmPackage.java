package delight.npmpackage;

import java.io.File;
import java.io.IOException;
import java.util.zip.ZipOutputStream;

public class NpmPackage {

    public static void perform(final ZipOutputStream target, final String[] npmDependencies) {

        try {
            final File workDir = File.createTempFile("temp-file-name", ".tmp");
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }

    }

}
