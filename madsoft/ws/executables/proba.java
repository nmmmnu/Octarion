import java.io.*;

public class proba{
   public static void main(String[] args){
      madsoft.util.MemoryManager.runFinalizersOnExit(true);

      proba x = new proba();
      x.exec();
      x = null;
      madsoft.util.MemoryManager.fullGC();
      System.out.println("====================");
      while (true);
   }

   public void exec(){
      System.out.println("exec");
      //while (true);
   }

   public void finalize() throws Throwable{
      for (int i = 0; i < 20; i++)
         System.out.println("HELLO!!!!!!!!!!");
   }

   public static void classFinalize() throws Throwable{
      for (int i = 0; i < 20; i++)
         System.out.println("HELLO!!!!!!!!!!");
   }
}