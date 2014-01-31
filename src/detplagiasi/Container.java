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
    static String address1;
    static String inFName;
    static File outFName = null;
    static String fName;
    
  public static void setAddress(String address2){
      address1 = address2; 
  }
  public static String getAddress() {
      return address1;
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
  public static String docUji(File file) throws Exception{
      String absolutePath = file.getAbsolutePath();
      String filePath = absolutePath.substring(0,absolutePath.lastIndexOf(File.separator));
      setAddress(filePath);
      //System.out.println(filePath);
      setFName(absolutePath);
      test(file);
      //System.out.println("hehehe");
      //System.out.println(outFName.getName());
      //System.out.println(outFName.getCanonicalPath()); --correct till here heckity @ 2:10am
      test2(file);
      //System.out.println("test2 success");
      return outFName.getName();
  }
  public static void test(File file) throws Exception{
    inFName = getFName();
    fName = file.getName().toLowerCase();
    //System.out.println("test infname output : "+inFName);//path dokumen asli
    //System.out.println("test fname output : "+fName);//nama dokumen asli
    //System.out.println("test address1 output : "+address1);

    if (inFName.endsWith(".docx")) {
        outFName = new File(address1 + "\\" + fName.replace(".docx", ".txt"));
    }
    else if(inFName.endsWith(".pdf")){                    
        outFName = new File(address1 + "\\" + fName.replace(".pdf",".txt"));
    }
    else if (inFName.endsWith(".doc")) {
        outFName = new File(address1 + "\\" + fName.replace(".doc", ".txt"));
    }
    else if (inFName.endsWith(".pptx")) {
        outFName = new File(address1 + "\\" + fName.replace(".pptx", ".txt"));
    }
    else if (inFName.endsWith(".ppt")) {
        outFName = new File(address1 + "\\" + fName.replace(".ppt", ".txt"));
    }
    else if (inFName.endsWith(".odf")) {
        outFName = new File(address1 + "\\" + fName.replace(".odf", ".txt"));
    }
    else if (inFName.endsWith(".odt")) {
        outFName = new File(address1 + "\\" + fName.replace(".odt", ".txt"));
    }
    else if (inFName.endsWith(".rtf")) {
        outFName = new File(address1 + "\\" + fName.replace(".rtf", ".txt"));
    }
    else if (inFName.endsWith(".xls")) {
        outFName = new File(address1 + "\\" + fName.replace(".xls", ".txt"));
    }
    else if (inFName.endsWith(".xlsx")) {
        outFName = new File(address1 + "\\" + fName.replace(".xlsx", ".txt"));
    }
    else if(inFName.endsWith (".txt")){
        outFName = new File(address1 + "\\" + fName);
    }
    //System.out.println("test outfname : "+outFName);
    setoutFName(outFName);
    //System.out.println("test outfname output : "+outFName);
  }
  public static void test2(File file) throws Exception{
      Tika tika = new Tika();
    //String text = tika.parseToString(new File(file));
      BufferedReader inputStream = null;
      PrintWriter outputStream = null;
      outFName = getoutFName();
      //System.out.println("heeee");
      try {
      //System.out.println("inFname : " +inFName );//inFname : path file yang akan diproses
      //System.out.println("outfname : " +outFName );//outfname : path file yang sudah ganti txt
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
      //String haha ="File "+inFName+ " berhasil diproses.\n";
      
    }
    
    finally {
      if (inputStream != null) {
          inputStream.close(); 
          
      }
    }
  }
  public static void simpanFile(File file) throws Exception {
    inFName = file.getCanonicalPath();
    //System.out.println("ininih" + inFName);
    fName = file.getName().toLowerCase();
    //System.out.println("ini" + fName);
    //System.out.println(outFName +"--");
    setoutFName(outFName);
    test(file);
    //System.out.println("test simpanfile success.");
    //System.out.println(outFName +"--2");
    test2(file);
    //System.out.println("test2 simpanfile success.");
        

    }
}
