package madsoft.ws;

class SysOutofrootResponser extends SysResponser{
   public SysOutofrootResponser(HTTP http, boolean header, boolean body){
      super(http,header,HTTPOUTOFROOT,body,"outofroot.htm");
   }
}
