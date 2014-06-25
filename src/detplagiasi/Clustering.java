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
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import weka.clusterers.ClusterEvaluation;
import weka.clusterers.DensityBasedClusterer;
import weka.clusterers.EM;
import weka.clusterers.SimpleKMeans;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.tokenizers.WordTokenizer;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;

/**
 *
 * @author vebry3
 */
public class Clustering {

    static Instances ujiArff, dataUji, dataTraining;
    private static File arffName;
    int a, method;
    static int noClusterUji;
    String addd;
    TextDirectoryToArff td = new TextDirectoryToArff();
    static String[] array1;
    static int[] array2;
    
    //public DefaultTableModel resultModel;
    Object[] hasil = {"No","File Name","# of Cluster"};

    public static Instances getUjiArff() {
        return ujiArff;
    }
    public static void setUjiArff(Instances ujiArff1) {
        ujiArff = ujiArff1;
    }
    public static void setArffFile(File arffName2){
        arffName = arffName2;
    }
    public static File getArffFile(){
        return arffName;
    }
    public static void setDataTraining(Instances dataTraining2){
        dataTraining = dataTraining2;
    }
    public static Instances getDataTraining(){
        return dataTraining;
    }
    public static void setDataUji(Instances dataUji2){
        dataUji = dataUji2;
    }
    public static Instances getDataUji(){
        return dataUji;
    }

    public void startCluster(File[] file) throws Exception {

        addd = Container.getAddress();
        
        if (addd != null) {
            try {
                Instances dataset = td.createDataset(addd);
                ArffSaver saver = new ArffSaver(); //create arff file
                ArffSaver saver2 = new ArffSaver();
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
                        System.out.println("instances datafiltered");
                        
                        Instances dataTraining = new Instances(dataFiltered);
                        System.out.println("instances dataTraining");
                        
                        dataUji = dataFiltered;
                        setDataUji(dataUji);
                        System.out.println("Instances dataUji");
                        
                        int x = dataFiltered.numInstances()-2;
                        for(int z=0; z<=x;z++){
                            dataUji.delete(0); //delete instances selain instances doc uji
                        }
                        
                        System.out.println("data uji= "+dataUji.numInstances());
                        dataTraining.delete(dataFiltered.numInstances()-1);
                        System.out.println("data training= " +dataTraining.numInstances());
                        setDataTraining(dataTraining);
                        int sss = dataTraining.numInstances();
                        array1 = new String[sss];
                        array2 = new int[sss];

                        saver.setInstances(dataTraining);
                        saver2.setInstances(dataUji);
                        File he = new File(addd + "\\clusterTraining.arff");
                        File hee = new File(addd + "\\clusterUji.arff");
                        saver.setFile(he);
                        saver.writeBatch();
                        
                        System.out.println("saver pertama");
                        
                        saver2.setFile(hee);
                        saver2.writeBatch();
                        
                        System.out.println("Saver kedua");
                        
                        setArffFile(he);
                        if(method==1){
                            EMClustering();
                        }else if(method==2){
                            KMeansClustering();
                        }
 
                    }
                catch(Exception d){
                    System.err.println(d.getLocalizedMessage());
                    System.out.println("error1");
                }
                setUjiArff(dataset);
                //System.out.println(dataset);

            } catch (Exception e) {
                System.err.println(e.getMessage());
                System.out.println("error2");
            }

        } else {
            System.out.println("NULLLLLLL");
        }
    }
    
    public void EMClustering(){
        addd = Container.getAddress();
        try {
            ClusterEvaluation eval;
            Instances data;
            String[] options;
            DensityBasedClusterer cl;
            
            File he = getArffFile();
            data = new Instances(new BufferedReader(new FileReader(he)));
            System.out.println("-----EM Clustering-----");
            // normal
           try(BufferedWriter out = new BufferedWriter(new FileWriter(addd + "\\output.txt",true))){
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
                getDataUji();
                getDataTraining();
                System.out.println("jumlah kluster = "+cl.numberOfClusters());
                noClusterUji = cl.clusterInstance(dataUji.instance(0));
                System.out.println("kluster = "+cl.clusterInstance(dataUji.instance(0)));
                for (int b=0; b<dataTraining.numInstances();b++){
                    System.out.print("file "+td.fileName[b]+" termasuk cluster ke ");
                    array1[b] = td.fileName[b];
                    array2[b] = cl.clusterInstance(dataTraining.instance(b));
                    System.out.println(cl.clusterInstance(dataTraining.instance(b)));
                    //simpan nilai instance ke dalam sebuah array int buat dikirim ke detplaggui
                }

                out.write("\r\n");

                eval = new ClusterEvaluation();
                eval.setClusterer(cl);
                eval.evaluateClusterer(new Instances(data));
                out.write("\r\n\n# of clusters: " + eval.getNumClusters());
               
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                    System.out.println("error2 em cluster");
                }
            
        } catch (IOException ex) {
            Logger.getLogger(Clustering.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("errorrrr null em");
        }

    }
    
    public void KMeansClustering(){
        addd = Container.getAddress();
        try {
            ClusterEvaluation eval;
            Instances data;
            String[] options;
            SimpleKMeans cl;
            
            File he = getArffFile();
            data = new Instances(new BufferedReader(new FileReader(he)));
            System.out.println("-----KMeans Clustering-----");
            // normal
            try(BufferedWriter out = new BufferedWriter(new FileWriter(addd + "\\output.txt",true))){
                out.write("\r\n--> normal\r\n");
                options    = new String[2];
                options[0] = "-t";
                options[1] = he.getAbsolutePath();
                out.write("\r\n"+ClusterEvaluation.evaluateClusterer(new SimpleKMeans(), options)+"\r\n");
                out.write("\r\n");

                // manual call
                out.write("\n--> manual\r\n");
                cl   = new SimpleKMeans();
                out.write("\r\n");
                cl.buildClusterer(data);
                getDataUji();
                System.out.println("jumlah kluster = "+cl.numberOfClusters());
                System.out.println("kluster = "+cl.clusterInstance(dataUji.instance(0)));
                for (int b=0; b<dataTraining.numInstances();b++){
                    System.out.print("file "+td.fileName[b]+" termasuk cluster ke ");
                    System.out.println(cl.clusterInstance(dataTraining.instance(b)));
                    array1[b] = td.fileName[b];
                    array2[b] = cl.clusterInstance(dataTraining.instance(b));
                    //simpan nilai instance ke dalam sebuah array int buat dikirim ke detplaggui
                }

                out.write("\r\n");

                eval = new ClusterEvaluation();
                eval.setClusterer(cl);
                eval.evaluateClusterer(new Instances(data));
                out.write("\r\n\n# of clusters: " + eval.getNumClusters());
               
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                    System.out.println("error2 kmeans cluster");
                }
            
        } catch (IOException ex) {
            Logger.getLogger(Clustering.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("errorrrr null kmeans");
        }
    }
}
