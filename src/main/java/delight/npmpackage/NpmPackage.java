package delight.npmpackage;

import java.io.File;
import java.nio.file.Files;

import org.zeroturnaround.zip.ZipUtil;

import de.mxro.file.Jre.FilesJre;
import de.mxro.process.Spawn;

public class NpmPackage {

    public static void perform(final NpmPackageParameters params) {

        try {

            final File workDir = Files.createTempDirectory("npmwork").toFile();

            FilesJre.wrap(workDir).assertFile("index.js").setText(params.indexJs);
            FilesJre.wrap(workDir).assertFile("lib.js").setText(params.libJs);
            FilesJre.wrap(workDir).assertFile("package.json").setText(params.packageJson);

            for (final String dependency : params.npmDependencies) {
                Spawn.sh(workDir, "npm install " + dependency + " --save");
            }

            ZipUtil.pack(workDir, params.target, false);

        } catch (final Exception e) {
            throw new RuntimeException(e);
        }

    }

}
