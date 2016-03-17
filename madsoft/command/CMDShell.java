package madsoft.command;

import java.io.*;
import java.util.Enumeration;

/**
* CMDShell provide shell for executing a commands
* it is some alike UNIX shell !!!
* <p>User can write any commands
* <p>There are some internal commands:
* <pre>
*    help - display the commands
*    exit - close the telnet sesion
* </pre>
* All another commands are get from CMDExecutor object.
*
* @see CMDExecutor
*/
public class CMDShell{
   /**
   * Command prompt (like '$' or 'C:\>')
   */
   public String prompt = "]";

   /**
   * OK prompt (like 'ok' or 'CMD Accepted')
   */
   public String okprompt = "Cmd acepted";

   /**
   * Coresponding CMDExecutor object
   */
   public CMDExecutor cmd;

   /**
   * Std In
   */
   protected DataInputStream is;

   /**
   * Std Out
   */
   protected PrintStream     ps;

   /**
   * Construct a shell with CMDExecutor object
   *
   * @param is stdin
   * @param ps stdout
   * @param com CMDExecutor object
   */
   public CMDShell(DataInputStream is, PrintStream ps,
                   CMDExecutor cmd,
                   String prompt, String okprompt){
      this(is,ps,cmd);

      if (prompt != null)
         this.prompt = prompt;

      if (okprompt != null)
         this.okprompt = okprompt;
   }

   /**
   * Construct a shell with CMDExecutor object
   *
   * @param is stdin
   * @param ps stdout
   * @param com CMDExecutor object
   */
   public CMDShell(DataInputStream is, PrintStream ps, CMDExecutor cmd){
      this.cmd = cmd;
      this.is = is;
      this.ps = ps;
   }

   /**
   * Construct a shell
   *
   * @param is stdin
   * @param ps stdout
   */
   public CMDShell(DataInputStream is, PrintStream ps){
      this(is, ps, new CMDExecutor(is,ps));
   }

   /**
   * List the commands
   */
   public void listCMD(){
      ps.println("Avail commands:");
      ps.println("===============");
      ps.println("Internal");
      ps.println("--------");
      ps.println("help\tthis help screen");
      ps.println("exit\tclose connection");
      ps.println();
      ps.println("Implemented");
      ps.println("-----------");

      for (Enumeration e = cmd.com.elements(); e.hasMoreElements();){
         CMD x = (CMD) e.nextElement();
         ps.println(x.name() + "\t" + x.description());
      }
   }

   /**
   * Process the shell
   *
   * @param is stdin
   * @param ps stdout
   */
   public void process() throws IOException{
      ps.println();
      ps.println("Welcome Master...");
      ps.println("Use 'help' for help...");
      ps.println();

      String s = new String();

      while (s.startsWith("exit",0) == false){
         ps.print(prompt);
         ps.flush();

         s = is.readLine();
         ps.println();

         if (cmd.com.containsKey(s)){
            cmd.execCMD(s);
         }else if (s.equals("help")){
            listCMD();
         }else if (s.equals("hello")){
            ps.println("Hello my master, nice to see You!");
         }else if (!s.equals("exit"))
            ps.println("Bad command");

         ps.println();
         ps.println(okprompt);

         Thread.currentThread().yield(); //Process messages :)
      }
   }
}
