package delight.npmpackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.zip.ZipInputStream;
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

    private final static String getPackageJson(final File zipFile) {

        final byte[] buffer = new byte[2048];

        // open the zip file stream
        final InputStream fis = new FileInputStream(zipFile);
        final ZipInputStream zis = new ZipInputStream(fis);

    }

}
