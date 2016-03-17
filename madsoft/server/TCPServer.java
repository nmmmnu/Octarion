package madsoft.server;

import java.io.*;
import java.net.*;
import java.util.Vector;

/**
* Abstract server on Port with TCP protocol.
*
* @see TCPClient
*/
public abstract class TCPServer extends Thread{
   /**
   * Server thread group.
   * 
   * All TCP servers are members of it
   */
   static public ThreadGroup stg = new ThreadGroup("TCPServers");

   /**
   * Client thread group.
   *
   * It is good idea that all TCP clients be members of it. (this depend of YOU:)
   */
   static public ThreadGroup ctg = new ThreadGroup("TCPClients");

   /**
   * Current server threads (clients).
   *
   * This vector is based on idea by Thomas Lea
   * leat@goodnet.com,  http://www.goodnet.com/~leat
   */
   Vector  threads = new Vector(15,5);

   private int  maxHits = 0;
   private int  totalHits = 0;

   /**
   * Is server working?
   */
   public  boolean isWorking;

   private ServerSocket server;

   /**
   * Max server threads
   */
   protected int maxThreads;

   /**
   * TCP Client timeout
   */
   protected int timeout = 0;

   /**
   * TCP Client standard timeout (30 min, may be :)
   */
   public static final int STANDARDTIMEOUT = 1800000;
//==========================================

   /**
   * Construct the server.
   *
   * @param port Server port
   * @param maxBackLog Server socket max back log
   * @param timeout Client timeout
   * @param maxThreads max threads
   */
   public TCPServer(int port, int maxBackLog, int timeout, int maxThreads){
      super(stg, "TCPServer thread");

      this.timeout = (timeout != 0)? timeout : STANDARDTIMEOUT;
      this.maxThreads = maxThreads;

      Log.write(this,Log.NORMAL,"Creating Server Socket");

      try{
         server = new ServerSocket(port, maxBackLog);
      }catch (IOException e){
         Log.write(this,Log.ERROR,"Error creating Server Socket. Fatal error. System halted.");
         System.exit(1);
      }

      Log.write(this,Log.NORMAL,"Server port: " + port);
      Log.write(this,Log.NORMAL,"Max Back Log: " + maxBackLog);

      if (maxThreads > 0)
         Log.write(this,Log.NORMAL,"Max threads: " + maxThreads);
      else
         Log.write(this,Log.NORMAL,"Max threads: unlimited");

      if (timeout > 0)
         Log.write(this,Log.NORMAL,"Timeout: " + timeout);
      else
         Log.write(this,Log.NORMAL,"Timeout: default (" + this.timeout + ")");

      isWorking = true;
   }
//==========================================

   /**
   * Construct the server, without max threads
   *
   * @param port Server port
   * @param maxBackLog Server socket max back log
   * @param timeout Client timeout
   */
   public TCPServer(int port, int maxBackLog, int timeout){
      this(port,maxBackLog,timeout,0);
   }
//==========================================

   /**
   * Construct the server, without max threads and with standard timeout
   *
   * @param port Server port
   * @param maxBackLog Server socket max back log
   */
   public TCPServer(int port, int maxBackLog){
      this(port,maxBackLog,0,0);
   }
//==========================================

   /**
   * Destruct the server
   */
   public void finalize() throws Throwable {
      try{
         server.close();
      }catch (Exception e){
         Log.write(this,Log.ERROR,"Error destroyng Server Socket.");
      }

      Log.write(this,Log.NORMAL,"Server Shutdown");
      super.finalize();
   }
//==========================================

   /**
   * Process the server
   */
   public void run(){ //Tread processing...
      Log.write(this,Log.NORMAL,"Server woking");
      while (true){
         try{
            if (this.canAcceptConnection()){

               Socket clSock = server.accept();
               clSock.setSoTimeout(timeout);

               Thread client = this.getAClient(clSock);

               if (client != null){
                  //client.setDaemon(true);

                  totalHits++;
                  if (totalHits % 100 == 0)
                     Log.write(this,Log.EXTENDED,"Total hits are " + totalHits);

                  threads.addElement(client);

                  client.start();
                  Log.write(this,Log.DEBUG,"Client thread started.");
               }
            }
         }catch (SocketException e){
            //Log.write(this,Log.EXTENDED,"Client Socket error.");
         }catch (IOException e){
            Log.write(this,Log.EXTENDED,"Error creation client Socket.");
         //}catch (Exception e){
           // Log.write(this,Log.ERROR,"WARNING: UNSUPPORTED EXCEPTION !!!");
         }catch (OutOfMemoryError e){
            Log.write(this,Log.ERROR,"WARNING: OUT OF MEMORY !!!");
         }

         clearThreads();

         //Max Hits:
         if (maxHits < threads.size()){
            maxHits = threads.size();
            Log.write(this,Log.EXTENDED,"Max hits are " + maxHits);
         }

         yield(); //Process messages :)
      }
   }
//==========================================

   /**
   * Clear the thread vector.
   *
   * You;ll not need to call this function.
   */
   protected void clearThreads(){
      synchronized (threads){
         //Clear the Vector:
         for (int i = 0; i < threads.size(); ){
            TCPClient cl = (TCPClient) threads.elementAt(i);
            if (cl.isAlive())
               i++;
            else
               threads.removeElementAt(i);
         }
      }
   }
//==========================================

   /**
   * Get the statistic
   * 
   * @return max threads in this moment
   */
   public int getThreadsCount(){
      clearThreads();
      return threads.size();
   }
//==========================================

   /**
   * Get the statistic
   * 
   * @return max threads in the same moment
   */
   public int getMaxHitsCount(){
      return maxHits;
   }
//==========================================

   /**
   * Get the statistic
   * 
   * @return total hits
   */
   public int getTotalHitsCount(){
      return totalHits;
   }
//==========================================

   /**
   * Clear the statistics
   */
   public void ClearHitStat(){
      totalHits = 0;
      maxHits = 0;
   }
//==========================================

   /**
   * Suspend the server
   */
   synchronized public void suspend_(){
      if (!isWorking) return;

      isWorking = false;
      suspend();
      Log.write(this,Log.EXTENDED,"Server stoped");
   }
//==========================================

   /**
   * Resume the server
   */
   synchronized public void resume_(){
      if (isWorking) return;

      isWorking = true;
      resume();
      Log.write(this,Log.EXTENDED,"Server started");
   }
//==========================================

   /**
   * Can accept the incoming connection
   * 
   * @return Yes/No
   */
   protected boolean canAcceptConnection(){
      //Log.write(this,Log.NORMAL,threads.size() + " " + maxThreads);
      return (threads.size() <= maxThreads);
      //@@@@@@@@@@@@@
      //return true;
   }
//==========================================

   /**
   * Get a TCPClient.
   *
   * @return TCPClient TCP Client
   */
   protected  abstract TCPClient getAClient(Socket s);
//==========================================
}
