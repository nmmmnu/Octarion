package madsoft.ws;

import java.net.Socket;

public interface RestrictManager {
   public boolean restrict(Socket client);
}