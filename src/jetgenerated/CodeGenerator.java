package jetgenerated;

import java.util.*;
import at.fhooe.mc.shape.*;

public class CodeGenerator
{
  protected static String nl;
  public static synchronized CodeGenerator create(String lineSeparator)
  {
    nl = lineSeparator;
    CodeGenerator result = new CodeGenerator();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + NL + "public class Statemachine {" + NL + "" + NL + "String currState = rectangleList.get(0);" + NL + "" + NL + "void handleEvent(String _evt) {" + NL + "\tswitch (currState) {\t\t\t";
  protected final String TEXT_3 = NL + "\t\tcase ";
  protected final String TEXT_4 = " :";
  protected final String TEXT_5 = NL + "\t\t\tif(_evt = ";
  protected final String TEXT_6 = " ) {" + NL + "\t\t\t\tcurrState = ";
  protected final String TEXT_7 = NL + "\t\t\t}\t";
  protected final String TEXT_8 = "\t" + NL + "\t}" + NL + "}";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
     Object[] arguments = (Object[])argument; 
     ArrayList<RectangleObject> rectangleList = (ArrayList<RectangleObject>) arguments[0]; 
     ArrayList<Connection> connectionList = (ArrayList<Connection>) arguments[1]; 
    stringBuffer.append(TEXT_2);
     for(RectangleObject rect : rectangleList ) { 
     for (Connection conn : connectionList ) { 
    stringBuffer.append(TEXT_3);
    stringBuffer.append( rect.getText() );
    stringBuffer.append(TEXT_4);
     if(conn.getStartRect().equals(rect)) { 
    stringBuffer.append(TEXT_5);
    stringBuffer.append( conn.getText() );
    stringBuffer.append(TEXT_6);
    stringBuffer.append( conn.getEndRect().getText() );
    stringBuffer.append(TEXT_7);
     } 
     } 
     }
    stringBuffer.append(TEXT_8);
    return stringBuffer.toString();
  }
}
