/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package detplagiasi;

import static detplagiasi.Clustering.getArffFile;
import static detplagiasi.Clustering.getDataTraining;
import static detplagiasi.Clustering.getDataUji;
import static detplagiasi.Clustering.noClusterUji;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import weka.clusterers.ClusterEvaluation;
import weka.clusterers.DensityBasedClusterer;
import weka.clusterers.EM;
import weka.core.Instances;

/**
 *
 * @author vebry3
 */
public class EMClustering extends Clustering {

    EMClustering(){
        addd = ct.getAddress();

        try{
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
                totalCluster = cl.numberOfClusters();
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
            Logger.getLogger(EMClustering.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("errorrrr null em");
        }
    }
            
}
