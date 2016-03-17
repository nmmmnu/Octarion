package madsoft.exporter;

public class FontStyle{
   public boolean   bold = false;
   public boolean   italic = false;
   public boolean   underline = false;
   public boolean   teletype = false;
//=========================================================
   public FontStyle(){
      super();
   }
//=========================================================
   public FontStyle(boolean bold, boolean italic, boolean underline, boolean teletype){
      this();

      this.bold = bold;
      this.italic = italic;
      this.underline = underline;
      this.teletype = teletype;
   }
//=========================================================
   String tag(boolean b){
      String s = "";

      if (b){
         if (bold)      s = s + "<Strong>";
         if (italic)    s = s + "<Em>";
         if (underline) s = s + "<U>";
         if (teletype)  s = s + "<TT>";
      }else{
         if (bold)      s = s + "</Strong>";
         if (italic)    s = s + "</Em>";
         if (underline) s = s + "</U>";
         if (teletype)  s = s + "</TT>";
      }

      return s;
   }
//=========================================================
}
