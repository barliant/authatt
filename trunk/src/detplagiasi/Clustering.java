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
    static int noClusterUji, totalCluster;
    public String addd;
    Container ct = new Container();
    static TextDirectoryToArff td = new TextDirectoryToArff();
    static String[] array1;
    static int[] array2;
    static String[] test = new String[999999];
    
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
                            //EMClustering();
                            EMClustering emClustering = new EMClustering();
                        }else if(method==2){
                            KMeansClustering kmeans = new KMeansClustering();
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
}
