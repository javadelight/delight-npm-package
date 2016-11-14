package delight.npmpackage;

import delight.strings.SanitizeStrings;

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
            if (params.libJs != null) {
                FilesJre.wrap(workDir).assertFile("lib.js").setText(params.libJs);
            }
            FilesJre.wrap(workDir).assertFile("package.json").setText(params.packageJson);

            for (final String dependency : params.npmDependencies) {
                if (!dependency.equals(sanitize(dependency))) {
                    throw new RuntimeException("Invalid npm dependency declaration: " + dependency);
                }
                Spawn.sh(workDir, "npm install " + sanitize(dependency) + " --save");
            }

            ZipUtil.pack(workDir, params.target, false);

            FilesJre.wrap(workDir).getParent().deleteFolder(FilesJre.wrap(workDir).getName());

        } catch (final Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static final String sanitize(final String dependency) {
        String res = "";

        for (int i = 0; i < dependency.length(); i++) {
            final char c = dependency.charAt(i);

            if (SanitizeStrings.isUrlPathCharacter(c) || c == '@' || c == '/' || c == '.' || c == ':') {
                res += c;
            }

        }

        return res;

    }

}
