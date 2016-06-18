package delight.npmpackage.tests;

import delight.npmpackage.NpmPackage;
import delight.npmpackage.utils.DefaultPackageJson;

import java.io.File;
import java.io.IOException;

public class ManualTest {

    public static void main(final String[] args) throws IOException {

        final File target = new File("/home/adminuser/Desktop/dest/service.zip");// Files.createTempDirectory("npmwork").toFile();

        NpmPackage.perform("var async = require('async');", DefaultPackageJson.get(), target, new String[] { "async" });

    }

}
