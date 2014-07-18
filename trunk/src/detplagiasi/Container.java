/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package detplagiasi;

/**
 *
 * @author vebry3
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import org.apache.tika.Tika;

public class Container {
    static String address;
    static String inFName;
    static File outFName = null;
    static String fName;
    
  public static void setAddress(String address2){
      address = address2; 
  }
  public static String getAddress() {
      return address;
  }
  public static String getFName(){
      return inFName;
  }
  public static void setFName(String fName2){
      inFName = fName2;
  }
  public static File getoutFName(){
      return outFName;
  }
  public static void setoutFName(File outfName2){
      outFName = outfName2;
  }
  public static File docUji(File file) throws Exception{
      String absolutePath = file.getAbsolutePath();
      //String filePath = absolutePath.substring(0,absolutePath.lastIndexOf(File.separator));
      setAddress(address);
      setFName(absolutePath);
      rename(file);
      parser(file);
      return outFName;
  }
  public static void rename(File file) throws Exception{
    inFName = getFName();
    fName = file.getName().toLowerCase();

    if (inFName.endsWith(".docx")) {
        outFName = new File(address + "\\" + fName.replace(".docx", ".txt"));
    }
    else if(inFName.endsWith(".pdf")){                    
        outFName = new File(address + "\\" + fName.replace(".pdf",".txt"));
    }
    else if (inFName.endsWith(".doc")) {
        outFName = new File(address + "\\" + fName.replace(".doc", ".txt"));
    }
    else if (inFName.endsWith(".pptx")) {
        outFName = new File(address + "\\" + fName.replace(".pptx", ".txt"));
    }
    else if (inFName.endsWith(".ppt")) {
        outFName = new File(address + "\\" + fName.replace(".ppt", ".txt"));
    }
    else if (inFName.endsWith(".odf")) {
        outFName = new File(address + "\\" + fName.replace(".odf", ".txt"));
    }
    else if (inFName.endsWith(".odt")) {
        outFName = new File(address + "\\" + fName.replace(".odt", ".txt"));
    }
    else if (inFName.endsWith(".rtf")) {
        outFName = new File(address + "\\" + fName.replace(".rtf", ".txt"));
    }
    else if (inFName.endsWith(".xls")) {
        outFName = new File(address + "\\" + fName.replace(".xls", ".txt"));
    }
    else if (inFName.endsWith(".xlsx")) {
        outFName = new File(address + "\\" + fName.replace(".xlsx", ".txt"));
    }
    else if(inFName.endsWith (".txt")){
        outFName = new File(address + "\\" + fName);
    }
    //System.out.println("test outfname : "+outFName);
    setoutFName(outFName);
    //System.out.println("test outfname output : "+outFName);
  }
  public static void parser(File file) throws Exception{
      Tika tika = new Tika();
      BufferedReader inputStream = null;
      PrintWriter outputStream = null;
      outFName = getoutFName();
      try {
        inputStream = new BufferedReader(tika.parse(new File(inFName)));
        outputStream = new PrintWriter(new FileWriter(outFName));

        String line;
        int lineCtr = 0;
        int lenStr = 0;
        while ((line = inputStream.readLine()) != null) {
          outputStream.println(line);
          lenStr += line.length();
          lineCtr++;
          outputStream.flush();
      }
      System.out.println("\t>> DEBUG: " + outFName + ": " + lenStr);
      System.out.println("\t>> DEBUG: " + lineCtr + " lines were saved.");
      System.out.println("\t>> DEBUG: " + outFName);
      
    }
    
    finally {
      if (inputStream != null) {
          inputStream.close(); 
          
      }
    }
  }
  public static void simpanFile(File file) throws Exception {
    inFName = file.getCanonicalPath();
    fName = file.getName().toLowerCase();
    setoutFName(outFName);
    rename(file);
    parser(file);
        

    }
}
