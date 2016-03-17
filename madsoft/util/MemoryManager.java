package madsoft.util;

/**
* Class for easy memory managment
*/
public class MemoryManager{
   /**
   * Execute Java's gc()
   */
   public static void fullGC(){
      FullGC fgc = new FullGC();
      fgc.setPriority(fgc.MAX_PRIORITY);
      fgc.start();
   }
//=====================================

   /**
   * Execute Java's runFinalizersOnExit
   * 
   * @param value On/Off
   */
   public static  void runFinalizersOnExit(boolean value){
      Runtime rt = Runtime.getRuntime();
      rt.runFinalizersOnExit(value);
   }
//=====================================

   /**
   * Return free memory
   * 
   * @return free memory
   */
   public static long getFreeMem(){
      Runtime rt = Runtime.getRuntime();

      return rt.freeMemory();
   }
//=====================================

   /**
   * Return free memory in %.
   * 
   *<pre>
   *       getFreeMem()
   * 100 * -------------  
   *       getTotalMem()
   *</pre>
   * 
   * @return free memory in % 
   */
   public static int getFreeMemPr(){
      return (int) (100 * getFreeMem() / getTotalMem());
   }
//=====================================

   /**
   * Return total memory
   * 
   * @return total memory
   */
   public static long getTotalMem(){
      Runtime rt = Runtime.getRuntime();

      return rt.totalMemory();
   }
//=====================================
}

class FullGC extends Thread{
   public void run(){
      Runtime rt = Runtime.getRuntime();
      long isFree = rt.freeMemory();
      long wasFree;
      do{
         wasFree = isFree;
         rt.gc();
         isFree = rt.freeMemory();
         yield(); //Process messages :)
      }while (isFree > wasFree);
      rt.runFinalization();
   }
}
