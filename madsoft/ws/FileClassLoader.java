package madsoft.ws;

import java.io.*;
import java.util.Hashtable;

class FileClassLoader extends ClassLoader {
   static Hashtable classes = new Hashtable();
//=====================================
   synchronized protected byte[] loadClassBytes(String className) {

      byte result[];
      String fileName = className;
      try {
         FileInputStream inStream = new FileInputStream(fileName);

         result = new byte[inStream.available()];
         inStream.read(result);
         inStream.close();
         return result;

      } catch (Exception e) {
         Log.write(this,Log.ERROR,"Class " + className + " not found");
         return null;
      }
   }
//=====================================
   public Class loadClass(String className) throws ClassNotFoundException {
      return ( loadClass(className, true) );
   }
//=====================================
   public Class loadClass(String className, boolean resolveIt) throws ClassNotFoundException {
      //System.out.println("==> " + className);

      Class   result;
      byte[]  classBytes;

      //----- Check with the primordial class loader
      try {
         result = super.findSystemClass(className);
         return result;
      } catch (ClassNotFoundException e) {}

      //----- Check our local cache of classes
      result = (Class)classes.get(className.toUpperCase());
      if (result != null)
         return result;

      //----- Try to load it from preferred source
      classBytes = loadClassBytes(className);
      if (classBytes == null)
         throw new ClassNotFoundException();

      //----- Define it (parse the class file)
      result = defineClass(classBytes, 0, classBytes.length);
      if (result == null)
         throw new ClassFormatError();

      //----- Resolve if necessary
      if (resolveIt)
         resolveClass(result);

      //*******************************************
      //*******************************************
      //*******************************************
      System.out.println("Web server proceses - full classes dump");
      System.out.println("=======================================");

      for (java.util.Enumeration e = FileClassLoader.classes.keys(); e.hasMoreElements();)
         System.out.println(e.nextElement());
      //*******************************************
      //*******************************************
      //*******************************************

      // Done
      classes.put(className.toUpperCase(), result);
      Log.write(this,Log.NORMAL,"Class " + className + " was implemented in server");

      return result;
   }
}
