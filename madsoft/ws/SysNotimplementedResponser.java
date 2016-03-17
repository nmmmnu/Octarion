package madsoft.ws;

class SysNotimplementedResponser extends SysResponser{
   public SysNotimplementedResponser(HTTP http, boolean header, boolean body){
      super(http,header,HTTPNOTIMPLEMENTED,body,"notimplemented.htm");
   }
}
