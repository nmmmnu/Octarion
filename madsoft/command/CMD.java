package madsoft.command;

import java.io.*;

/**
* Command Interface.
* All commands must implement this interface.
* @author Nikolay Mijaylov
*/
public interface CMD{

   /**
   * return command name
   *
   *  @return command name
   */
   public String name();

   /**
   * return command description
   *
   *  @return command description (for help screens)
   */
   public String description();

   /**
   * Execute the command
   *
   * @param is stdin
   * @param ps stdout
   * @exception IOException throw in command execution
   */
   public void cmd(DataInputStream is, PrintStream ps) throws IOException;
}
