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
import weka.core.Instance;

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
public class DocUjiToArff {
    private Instance m_dokumen;
    
    public void DocUjiToArff() {
        
    }
    
    /*
     * 
    public void DocUjiToArff(String filepath, File file) throws Exception {
        m_dokumen = this.createDataset(filepath, file);
    }
     */
    
  public Instance createDataset(String filepath, File file) throws Exception {
     
    FastVector atts = new FastVector(2);
    atts.addElement(new Attribute("filename", (FastVector) null));
    atts.addElement(new Attribute("contents", (FastVector) null));
    /*
    ArrayList atts = new ArrayList(2);
    atts.addElement(new Attribute("filename", (ArrayList) null));
    atts.addElement(new Attribute("contents", (ArrayList) null));
     */
    String a = file.getName();
    Instances data = new Instances(filepath, atts, 0);
    Instance oneDocument = null;
            
    //Instances data = new Instances("text_files_in_" + directoryPath, atts, 0);
    File dir = new File(filepath);
    //String[] files = dir.list();
    if (a.endsWith(".txt")){
        try {
            double[] newInst = new double[2];
            newInst[0] = (double)data.attribute(0).addStringValue(a);
            File txt = new File(filepath + File.separator + a);  
          
            System.out.println("TDTARFF: " + txt.getCanonicalPath());

            InputStreamReader is;
            is = new InputStreamReader(new FileInputStream(txt));
            StringBuffer txtStr = new StringBuffer();
            int c;
            while ((c = is.read()) != -1) {
                txtStr.append((char)c);
            }
            newInst[1] = (double)data.attribute(1).addStringValue(txtStr.toString());
            //data.add(new Instance(1.0, newInst));
            data.add(new Instance(1.0, newInst));
            oneDocument = new Instance(1.0, newInst);
            } 
        catch (Exception e) {
            System.err.println("failed to convert file: " + a );
	}
    }
    
    //return data;
    return oneDocument;
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

