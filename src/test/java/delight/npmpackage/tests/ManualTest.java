package delight.npmpackage.tests;

import delight.npmpackage.utils.DefaultPackageJson;

import java.io.File;
import java.io.IOException;

import de.mxro.file.Jre.FilesJre;
import de.mxro.process.Spawn;

public class ManualTest {

    public static void main(final String[] args) throws IOException {

        final File target = new File("/home/adminuser/Desktop/tmp/service.zip");// Files.createTempDirectory("npmwork").toFile();

        FilesJre.wrap(workDir).assertFile("package.json").setText(DefaultPackageJson.get());

        System.out.println(Spawn.sh(workDir, "npm install async --save"));

    }

}
