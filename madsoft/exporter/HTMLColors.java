package madsoft.exporter;

public class HTMLColors{
   String text = null;
   String back = null;

   String link = null;
   String visited = null;
   String active = null;

   String stylesheet = null;
//=========================================================
   public HTMLColors(){
      super();
   }
//=========================================================
   public HTMLColors(String text, String back){
      this();

      this.text = text;
      this.back = back;
   }
//=========================================================
   public HTMLColors(String text, String back,
              String link, String visited, String active){
      this(text,back);

      this.link = link;
      this.active = active;
      this.visited = visited;
   }
//=========================================================
   public HTMLColors(String stylesheet){
      this();

      this.stylesheet = stylesheet;
   }
//=========================================================
   String tag(){
      String s = "";

      if (text != null)     s = s + " Text=\"" + text + "\"";
      if (back != null)     s = s + " BgColor=\"" + back + "\"";
      if (link != null)     s = s + " Link=\"" + link + "\"";
      if (visited != null)  s = s + " VLink=\"" + visited + "\"";
      if (active != null)   s = s + " ALink=\"" + active + "\"";

      if (stylesheet != null)        s = s + " StyleScr=\"" + stylesheet + "\"";

      return s;
   }
//=========================================================
}
