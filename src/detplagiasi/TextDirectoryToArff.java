package detplagiasi;

/*
 *    TextDirectoryToArff.java
 *    Copyright (C) 2002 Richard Kirkby
 *
 *    This program is free software; you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation; either version 2 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program; if not, write to the Free Software
 *    Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */

import java.io.*;
import weka.core.*;
import java.util.ArrayList;

/**
 * Builds an arff dataset from the documents in a given directory.
 * Assumes that the file names for the documents end with ".txt".
 *
 * Usage:<p>
 *
 * TextDirectoryToArff <directory path><p>
 *
 * @author Richard Kirkby (rkirkby at cs.waikato.ac.nz)
 * @version 1.0
 */
public class TextDirectoryToArff {
    String[] fileName = new String[999999];

  public Instances createDataset(String directoryPath) throws Exception {
     
    FastVector atts = new FastVector(2);
    atts.addElement(new Attribute("filename", (FastVector) null));
    atts.addElement(new Attribute("contents", (FastVector) null));
    
    /*
    ArrayList atts = new ArrayList(2);
    atts.addElement(new Attribute("filename", (ArrayList) null));
    atts.addElement(new Attribute("contents", (ArrayList) null));
     */
    Instances data = new Instances("text_files_in_" + directoryPath, atts, 0);
    File dir = new File(directoryPath);
    String[] files = dir.list();
    //create file a untuk menampung name file dari instance yang terkait
    //FileWriter fstream = new FileWriter(directoryPath+"\\cluster detail.txt");
    BufferedWriter out = null;
    out = new BufferedWriter(new FileWriter(directoryPath+"\\cluster detail.txt"));
    
    
    for (int i = 0; i < files.length; i++) {
      if (files[i].endsWith(".txt")) {
          out.write("file ke "+(i+1)+": "+files[i]);
          System.out.println("processed files:"+files[i]);
          fileName[i] = files[i];
          out.write("file ke "+(i+1)+": "+files[i]);
	try {
            double[] newInst = new double[2];
            newInst[0] = (double)data.attribute(0).addStringValue(files[i]);
            File txt = new File(directoryPath + File.separator + files[i]);  
          
            System.out.println("TDTARFF: " + txt.getCanonicalPath());

            InputStreamReader is;
            is = new InputStreamReader(new FileInputStream(txt));
            StringBuffer txtStr = new StringBuffer();
            int c;
            while ((c = is.read()) != -1) {
                txtStr.append((char)c);
            }
            newInst[1] = (double)data.attribute(1).addStringValue(txtStr.toString());
            try{
                out.write("file ke "+(i+1)+": "+files[i]);
                System.out.println("success");
            }catch(Exception d){
                System.err.println(d.getLocalizedMessage());
            }
            //input pada file a nama file dari instance
            //data.add(new Instance(1.0, newInst));
            data.add(new Instance(1.0, newInst));
            //data.renameAttributeValue(data.attribute("att_name_in_data2"),"att_value_in_data2","att_value_in_data1");
            } 
        catch (Exception e) {
            System.err.println("failed to convert file: " + directoryPath + File.separator + files[i]);
	}
      }
    }
    return data;
  }
  
  /*public static void main(String[] args) {
    
    if (args.length == 1) {
      TextDirectoryToArff tdta = new TextDirectoryToArff();
      try {
	Instances dataset = tdta.createDataset(args[0]);
	System.out.println(dataset);
      } catch (Exception e) {
	System.err.println(e.getMessage());
	e.printStackTrace();
      }
    } else {
      System.out.println("Usage: java TextDirectoryToArff <directory name>");
    }
  }
  * */
}

