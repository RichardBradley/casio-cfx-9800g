package org.bradders.casiocfx9800g;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * A class that can resolve simple filenames with respect to a base dir
 * from a URL.
 */
public class UrlResolverWithBaseDir
{
   private String baseDir;
   
   public URL resolveFilename(String filename) throws MalformedURLException
   {
      return new URL(baseDir + filename);
   }

   /**
    * Gets the simple filename for a URL
    */
   public String getFilename(URL url)
   {
      String urlS = url.toString();
      int lastSlash = urlS.lastIndexOf('/');
      return urlS.substring(lastSlash + 1, urlS.length());
   }

   /**
    * Specifies the given URL as the new base dir for resolveFilename
    */
   public void setBaseDir(URL url)
   {
      String urlS = url.toString();
      int lastSlash = urlS.lastIndexOf('/');
      baseDir = urlS.substring(0, lastSlash + 1);
   }
}
