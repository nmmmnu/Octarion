package madsoft.exporter;

import java.io.*;

/**
* This class provide export to Text/HTML format
* <p>Text/HTML format is ASCII text with some HTML tags
*/
public class TextHTMLExporter extends TextExporter{
    String title = null;
    String back = null;
//=========================================================
    /**
    * Construct an exporter
    * @param stream Canal stream
    */
    public TextHTMLExporter(OutputStream stream){
       super(stream);
    }
//=========================================================

    /**
    * Construct an exporter
    * @param stream Canal stream
    * @param title HTML title
    */
    public TextHTMLExporter(OutputStream stream, String title){
       this(stream);
       this.title = title;
    }
//=========================================================

    /**
    * Construct an exporter
    * @param stream Canal stream
    * @param title HTML title
    * @param back HTML background image
    */
    public TextHTMLExporter(OutputStream stream, String title, String back){
       this(stream, title);
       this.back = back;
    }
//=========================================================

    /**
    * Open exporter
    */
    public void open(){
       super.open();

       outputLn("<HTML>");
       outputLn("<!--- This is automatic generated file --->");
       outputLn("<!------------- Do Not Edit -------------->");

       if (title != null)
          outputLn("<HEAD><TITLE>" + title + "</TITLE></HEAD>");

       if (back != null)
          output("<BODY Background = \"" + back + "\">");
       else
          output("<BODY>");

       outputLn("<P><TT><PRE>"); // <PRE> is HTML 3.0 tag !!!
    }
//=========================================================

    /**
    * Close exporter
    */
    public void close(){
       outputLn("</PRE></TT></P></BODY></HTML>");
       super.close();
    }
//=========================================================
}
