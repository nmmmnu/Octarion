import java.io.*;

public class canonic{
   public static void main(String[] args){
      System.out.println("Canon helper ver 1.0");
      System.out.println("====================");
      System.out.println();

      if (args.length == 0){
         System.out.println("Usage: Java canonic [filename]");
         return;
      }

      try{
         File f = new File(args[0]);
         String path = f.getCanonicalPath();

         System.out.println("canonical path is: " + path);
      }catch (Exception e){
         System.out.println("I/O Error");
      }
   }
}