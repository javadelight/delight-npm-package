package delight.npmpackage.tests;

import delight.npmpackage.NpmPackage;

import org.junit.Test;

import junit.framework.Assert;

public class TestSanitize {

    @Test
    public void test_valid() {

        Assert.assertEquals("async", NpmPackage.sanitize("async"));
        Assert.assertEquals("async@0.0.1", NpmPackage.sanitize("async@0.0.1"));

        Assert.assertEquals("async@0.0.1", NpmPackage.sanitize("async@0.0.1"));

        Assert.assertEquals("@myorg/privatepackage", NpmPackage.sanitize("@myorg/privatepackage"));

        Assert.assertEquals("https://github.com/indexzero/forever/tarball/v0.5.6",
                NpmPackage.sanitize("https://github.com/indexzero/forever/tarball/v0.5.6"));

    }

    @Test
    public void test_invalid() {

        Assert.assertNotSame("sudo wget https://google.com", NpmPackage.sanitize("sudo wget https://google.com"));

    }

}
