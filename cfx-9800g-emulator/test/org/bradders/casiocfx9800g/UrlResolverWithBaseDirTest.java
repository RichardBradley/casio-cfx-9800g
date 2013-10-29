package org.bradders.casiocfx9800g;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.net.URL;

import org.junit.Test;

public class UrlResolverWithBaseDirTest
{
   @Test
   public void testCompilerLoadFile() throws Exception
   {
      UrlResolverWithBaseDir resolver = new UrlResolverWithBaseDir();
      URL firstFile = new URL("jar:file:/C:/Documents%20and%20Settings/rtb/Documents/personal/src/casio-cfx-9800g/cfx-9800g-emulator/cfx-9800g-emulator-applet.jar!/p0-poisson.txt");
      
      assertThat(resolver.getFilename(firstFile), equalTo("p0-poisson.txt"));
      
      resolver.setBaseDir(firstFile);
      
      assertThat(resolver.resolveFilename("foo.txt").toString(),
            equalTo("jar:file:/C:/Documents%20and%20Settings/rtb/Documents/personal/src/casio-cfx-9800g/cfx-9800g-emulator/cfx-9800g-emulator-applet.jar!/foo.txt"));
   }
}
