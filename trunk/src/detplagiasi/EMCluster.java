/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package detplagiasi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import weka.clusterers.ClusterEvaluation;
import weka.clusterers.DensityBasedClusterer;
import weka.clusterers.EM;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.tokenizers.WordTokenizer;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.RemoveType;
import weka.filters.unsupervised.attribute.StringToNominal;
import weka.filters.unsupervised.attribute.StringToWordVector;

/**
 *
 * @author vebry3
 */
public class EMCluster {

    static Instances ujiArff;
    int a;

    public static Instances getUjiArff() {
        return ujiArff;
    }

    public static void setUjiArff(Instances ujiArff1) {
        ujiArff = ujiArff1;
    }

    public static void docujiarff(File file) throws Exception {
        String hehe = file.getCanonicalPath();
        /*
         DocUjiToArff sample = new DocUjiToArff();
         ujiArff = sample.createDataset(hehe, file);
        
         System.out.println("DEBUG: ujiArff");
         System.out.println(ujiArff);
        
         * 
         */
        String filePath = hehe.substring(0, hehe.lastIndexOf(File.separator));

        TextDirectoryToArff td = new TextDirectoryToArff();
        if (filePath != null) {
            try {
                Instances dataset = td.createDataset(filePath);
                ArffSaver saver = new ArffSaver(); //create arff file
                try {

                        StringToWordVector filter = new StringToWordVector();
                        WordTokenizer wt = new WordTokenizer();
                        String delimiters = " \r\t\n.,;:\'\"()?!-><#$\\%&*+/@^_=[]{}|`";
                        wt.setDelimiters(delimiters);
                        filter.setTokenizer(wt);
                        filter.setLowerCaseTokens(true);
                        System.out.println(wt.getDelimiters());
                        filter.setInputFormat(dataset);
                        Instances dataFiltered = Filter.useFilter(dataset, filter);

                        saver.setInstances(dataFiltered);
                        File he = new File(filePath + "\\cluster.arff");
                        saver.setFile(he);
                        saver.writeBatch();
                        
                    }
                catch(Exception d){
                    System.err.println(d.getLocalizedMessage());
                }
                //setUjiArff(dataFiltered1);
                setUjiArff(dataset);
                
                System.out.println(dataset);

            } catch (Exception e) {
                System.err.println(e.getMessage());
            }

        } else {
            System.out.println("No such file on the directory. ");
        }
    }

    public void startCluster(File[] file) throws Exception {
        TextDirectoryToArff td = new TextDirectoryToArff();
        int klaster;

        String addd = Container.getAddress();
        if (addd != null) {
            
            try {
                Instances dataset = td.createDataset(addd);
                ArffSaver saver = new ArffSaver(); //create arff file
                try (BufferedWriter out = new BufferedWriter(new FileWriter(addd + "\\output.txt"))){
                        out.write("Statistik:\n\n");
                        out.write("\r\n"+dataset.numInstances()+"\r\n");
                        out.write("\r\n"+dataset.numAttributes()+"\r\n");
                        out.write("\r\n"+dataset.toSummaryString()+"\r\n");
                        a = dataset.numAttributes();
                        System.out.println("jlh atribut =" +a);
                        
                        StringToWordVector filter = new StringToWordVector();
                        WordTokenizer wt = new WordTokenizer();
                        String delimiters = " \r\t\n.,;:\'\"()?!-><#$\\%&*+/@^_=[]{}|`";
                        wt.setDelimiters(delimiters);
                        filter.setTokenizer(wt);
                        filter.setLowerCaseTokens(true);
                        System.out.println(wt.getDelimiters());
                        filter.setInputFormat(dataset);
                        Instances dataFiltered = Filter.useFilter(dataset, filter);

                        saver.setInstances(dataFiltered);
                        File he = new File(addd + "\\cluster.arff");
                        saver.setFile(he);
                        saver.writeBatch();
                        
                        ClusterEvaluation eval;
                        Instances data;
                        String[] options;
                        DensityBasedClusterer cl;
                        
                        data = new Instances(new BufferedReader(new FileReader(he)));
                        
                        /*StringToNominal filter2 = new StringToNominal();
                        filter2.setAttributeRange("first");
                        filter2.setInputFormat(data);
                        data = StringToNominal.useFilter(data, filter);
                        * */
                        
                        // normal
                        out.write("\r\n--> normal\r\n");
                        options    = new String[2];
                        options[0] = "-t";
                        options[1] = he.getAbsolutePath();
                        out.write("\r\n"+ClusterEvaluation.evaluateClusterer(new EM(), options)+"\r\n");
                        out.write("\r\n");

                        // manual call
                        out.write("\n--> manual\r\n");
                        cl   = new EM();
                        out.write("\r\n");
                        cl.buildClusterer(data);
                        out.write("\r\n");
                        
                        eval = new ClusterEvaluation();
                        eval.setClusterer(cl);
                        eval.evaluateClusterer(new Instances(data));
                        out.write("\r\n\n# of clusters: " + eval.getNumClusters());

                    }
                catch(Exception d){
                    System.err.println(d.getLocalizedMessage());
                }
                //setUjiArff(dataFiltered1);
                setUjiArff(dataset);
                
                System.out.println(dataset);

            } catch (Exception e) {
                System.err.println(e.getMessage());
            }

            
            /*
             * try {
                Instances dataset = td.createDataset(addd);
                ArffSaver saver = new ArffSaver(); //create arff file
                try (BufferedWriter out = new BufferedWriter(new FileWriter(addd + "\\output.txt"))) {
                    out.write("Statistik:\n\n");
                    out.write("\r\n" + dataset.numInstances() + "\r\n");
                    out.write("\r\n" + dataset.numAttributes() + "\r\n");
                    out.write("\r\n" + dataset.toSummaryString() + "\r\n");
                    //RemoveType a = new RemoveType(String);
                    
                    StringToWordVector filter = new StringToWordVector();

                    WordTokenizer wt = new WordTokenizer();
                    String delimiters = " \r\t\n.,;:\'\"()?!-><#$\\%&*+/@^_=[]{}|`";
                    wt.setDelimiters(delimiters);
                    filter.setTokenizer(wt);
                    filter.setLowerCaseTokens(true);
                    System.out.println(wt.getDelimiters());
                    filter.setInputFormat(dataset);
                    Instances dataFiltered = Filter.useFilter(dataset, filter);

                    saver.setInstances(dataFiltered);
                    saver.setInstances(dataset);
                    File he = new File(addd + "\\cluster.arff");
                    saver.setFile(he);
                    //saver.setDestination(new File(addd + "\\test.arff"));   // **not** necessary in 3.5.4 and later
                    saver.writeBatch();

                    ClusterEvaluation eval;
                    Instances data;
                    String[] options;
                    DensityBasedClusterer cl;

                    data = new Instances(new BufferedReader(new FileReader(he)));
                    StringToNominal filter2 = new StringToNominal();
                    filter2.setAttributeRange("first");
                    filter2.setInputFormat(data);
                    data = StringToNominal.useFilter(data, filter);
                         
                    
                    // normal
                    out.write("\r\n--> normal\r\n");
                    options = new String[2];
                    options[0] = "-t";
                    options[1] = he.getAbsolutePath();
                    out.write("\r\n" + ClusterEvaluation.evaluateClusterer(new EM(), options) + "\r\n");
                    out.write("\r\n");

                    // manual call
                    out.write("\n--> manual\r\n");
                    cl = new EM();
                    //cl.
                    out.write("\r\n");
                    cl.buildClusterer(data);
                    //klaster = cl.clusterInstance(getUjiArff().firstInstance());
                    //System.out.println(getUjiArff().instance(0).numAttributes());
                    klaster = cl.clusterInstance(getUjiArff().instance(0));

                    //System.out.println("Dokumen uji ada di cluster: " + klaster);

                        //Instance dokumenUji = null;
                    //int kelompok;
                    //kelompok = cl.clusterInstance(dokumenUji);
                    out.write("\r\n");

                       // cl.
                    eval = new ClusterEvaluation();
                    eval.setClusterer(cl);
                    eval.evaluateClusterer(new Instances(data));
                    
                    out.write("\r\n\n# of clusters: " + eval.getNumClusters());

                    /*
                     right after you get the plot data:
                     PlotData2D predData = ClustererPanel.setUpVisualizableInstances(train,eval);
                     String name = (new SimpleDateFormat("HH:mm:ss - ")).format(new Date());
                     String cname = clusterer.getClass().getName();
                     if (cname.startsWith("weka.clusterers."))
                     name += cname.substring("weka.clusterers.".length());
                     else
                     name += cname;
                     * */
            /*
                }

                System.out.println(dataset);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
            * */

        } else {
            System.out.println("NULLLLLLL");
        }
    }
    public void test(File[] file) throws Exception{
        TextDirectoryToArff haha = new TextDirectoryToArff();
        //create instances dengan looping
        //get all attributes ke dalam suatu array, attribute filename dan contents
        //set up attribute
            // >atts = new FastVector();
            // >atts.addELement(new Attribute("att2", (FastVector) null));
            //create instances object
            // >data = new Instances("MyRelation", atts, 0));
            //fill with data
            // first instance
            // >vals = new double[data.numAttributes()];
            // >vals[2] = data.attribute(2).addStringValue("This is a string!");
            // second instance
            // >vals[2] = data.attribute(2).addStringValue("And another one!");
        for (int dyo = 0; dyo<a; dyo++){
            
        }
    }
}
