package delight.npmpackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import de.mxro.file.Jre.FilesJre;
import de.mxro.process.Spawn;

public class NpmPackage {

    public static void perform(final String packageJson, final File target, final String[] npmDependencies) {

        try {
            // final File workDir = File.createTempFile("temp-file-name",
            // ".tmp");

            final File workDir = Files.createTempDirectory("npmwork").toFile();

            FilesJre.wrap(workDir).assertFile("package.json").setText(packageJson);

            for (final String dependency : npmDependencies) {
                System.out.println(Spawn.sh("npm install " + dependency + " --save"));
            }

        } catch (final IOException e) {
            throw new RuntimeException(e);
        }

    }

    private final static String getPackageJson(final ZipFile zipFile) throws IOException {
        final Enumeration<? extends ZipEntry> entries = zipFile.entries();

        while (entries.hasMoreElements()) {
            final ZipEntry ze = entries.nextElement();
            if (ze.getName() == "package.json") {

                final BufferedReader br = new BufferedReader(new InputStreamReader(zipFile.getInputStream(ze)));

                String lines = "";
                String line;
                while ((line = br.readLine()) != null) {
                    lines += line;
                }

                br.close();
                return lines;
            }
        }

        throw new IllegalArgumentException("The passed zip file must contain a package.json file.");

    }

    private final static String setPackageJson(final ZipFile zipFile) throws IOException {
        final Enumeration<? extends ZipEntry> entries = zipFile.entries();

        while (entries.hasMoreElements()) {
            final ZipEntry ze = entries.nextElement();
            if (ze.getName() == "package.json") {

                return lines;
            }
        }

        throw new IllegalArgumentException("The passed zip file must contain a package.json file.");

    }

}
