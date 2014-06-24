/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package detplagiasi;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import static java.nio.file.StandardCopyOption.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author vebry3
 */
public class DetPlagGUI extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */

    File dataset, doc, output;
    String datasetPath, docPath, outPath;
    Container container = new Container();
    Clustering clusterer = new Clustering();
    private JTable jTable1;
    private JFrame jFrame1;
    private static final Object[][] rowData = {};
    private static final Object[] columnNames = {"No", "File Name","# Cluster"};

    public DetPlagGUI() {
        initComponents();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        AlgoGrp = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        datasetLoc = new javax.swing.JTextField();
        browseDataset = new javax.swing.JButton();
        docLoc = new javax.swing.JTextField();
        browseDoc = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        em = new javax.swing.JRadioButton();
        kmeans = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        outputLoc = new javax.swing.JTextField();
        browseOutput = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        processB = new javax.swing.JButton();
        resetB = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("PLAGIARISM DETECTION");

        browseDataset.setText("Browse");
        browseDataset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseDatasetActionPerformed(evt);
            }
        });

        browseDoc.setText("Browse");
        browseDoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseDocActionPerformed(evt);
            }
        });

        jLabel2.setText("Select your training dataset");

        jLabel3.setText("Select the document to be tested");

        jLabel4.setText("Choose Algorithm");

        AlgoGrp.add(em);
        em.setText("EM Algorithm");

        AlgoGrp.add(kmeans);
        kmeans.setText("K-Means Algorithm");

        jLabel5.setText("Select your output location");

        browseOutput.setText("Browse");
        browseOutput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseOutputActionPerformed(evt);
            }
        });

        jLabel6.setText("Description: Checking a document of an unknown author");

        processB.setText("Process");
        processB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                processBActionPerformed(evt);
            }
        });

        resetB.setText("Reset");
        resetB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetBActionPerformed(evt);
            }
        });

        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(datasetLoc)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(browseDataset))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(outputLoc, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(docLoc, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(browseOutput))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(browseDoc))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(em)
                                        .addGap(36, 36, 36)
                                        .addComponent(kmeans))
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel6)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(74, 74, 74)
                                        .addComponent(processB)
                                        .addGap(65, 65, 65)
                                        .addComponent(resetB, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(69, 69, 69)
                                        .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 75, Short.MAX_VALUE)))
                        .addGap(50, 50, 50))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addGap(57, 57, 57)
                .addComponent(jLabel2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(datasetLoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(browseDataset))
                .addGap(30, 30, 30)
                .addComponent(jLabel3)
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(docLoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(browseDoc))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(em)
                    .addComponent(kmeans))
                .addGap(10, 10, 10)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(outputLoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(browseOutput))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(processB)
                    .addComponent(resetB)
                    .addComponent(btnExit))
                .addContainerGap(74, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void browseDatasetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseDatasetActionPerformed
        JFileChooser datasetCh = new JFileChooser();
        datasetCh.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int nilai = datasetCh.showOpenDialog(this);
        if(nilai==JFileChooser.APPROVE_OPTION){
            dataset = datasetCh.getSelectedFile();
            datasetLoc.setText(dataset.getAbsolutePath());
            datasetPath = (dataset.getAbsolutePath());

        }else{
            datasetLoc.setText("");
        }
    }//GEN-LAST:event_browseDatasetActionPerformed

    private void browseDocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseDocActionPerformed
        JFileChooser docCh = new JFileChooser();
        docCh.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int nilai = docCh.showOpenDialog(this);
        if(nilai==JFileChooser.APPROVE_OPTION){
            doc = docCh.getSelectedFile();
            docLoc.setText(doc.getAbsolutePath());
            docPath = (doc.getAbsolutePath());

        }else{
            docLoc.setText("");
        }
    }//GEN-LAST:event_browseDocActionPerformed

    private void browseOutputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseOutputActionPerformed
        JFileChooser outputCh = new JFileChooser();
        outputCh.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int nilai = outputCh.showOpenDialog(this);
        if(nilai==JFileChooser.APPROVE_OPTION){
            output = outputCh.getSelectedFile();
            outputLoc.setText(output.getAbsolutePath());
            outPath = (output.getAbsolutePath());

        }else{
            outputLoc.setText("");
        }
    }//GEN-LAST:event_browseOutputActionPerformed

    private void processBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_processBActionPerformed
        if(!"".equals(datasetLoc.getText()) && (!"".equals(docLoc.getText())) &&(!"".equals(outputLoc.getText()))){
            try {
                File folder, ax;
                folder = new File(datasetLoc.getText());
                Container.setAddress(outPath);
                File[] file = folder.listFiles();
                File[] file2 = null;
                System.out.println(file.length);
                System.out.println(datasetPath);
                System.out.println(docPath);
                String a = Container.docUji(doc);
                System.out.println(a);
                if(file.length!=0){
                    for (int j=0; j<file.length; j++) {
                        if(file[j].isFile()){
                            Container.simpanFile(file[j]);
                        }
                        else if(file[j].isDirectory()){
                            ax= new File(file[j].getAbsolutePath());
                            System.out.println("folder name : "+ax);
                            file2 = file[j].listFiles();
                            for(int l=0; l<file2.length;l++){
                                System.out.println("file ke "+(l+1)+":"+ file2[l]);
                                Container.simpanFile(file2[l]);
                            }
                        }
                    }
                    File[] fileC = new File[file.length + file2.length];
                    System.arraycopy(file, 0, fileC, 0, file.length);
                    System.arraycopy(file2, 0, fileC, file.length, file2.length);
                    for(int ss=0;ss<file.length;ss++){
                        System.out.println(fileC[ss]);
                    }
                    if(em.isSelected()){
                        clusterer.method=1;
                        clusterer.startCluster(fileC);
                        //a =clusterer.td.fileName[1];
                    }else if(kmeans.isSelected()){
                        clusterer.method=2;
                        clusterer.startCluster(fileC);
                    }else{
                        JOptionPane.showMessageDialog(null, "Please choose your algorithm", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Folder is empty", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (Exception ex) {
                //System.out.println(files.length);
                Logger.getLogger(DetPlagGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            JOptionPane.showMessageDialog(null,  "Fill out first", "Input Kosong", JOptionPane.ERROR_MESSAGE);
        }
        /*DefaultTableModel model;
        model = new DefaultTableModel(rowData, columnNames);
        int huee = Clustering.array1.length;
        for(int hue=0;hue<huee;hue++){
            int num = hue+1;
            String nomer = String.valueOf(num);
            String fileName = Clustering.array1[hue];
            String clusterNum = String.valueOf(Clustering.array2[hue]);
            model.addRow(new Object[]{nomer, fileName, clusterNum});
        }
        
        */
        jFrame1 = new JFrame("Result");
        jFrame1.setSize(600,600);
        jFrame1.setVisible(true);
         
         //columnNames = {"No","File Name","# Cluster"};
        Object[][] rowData;
        int huee = Clustering.array1.length;
        rowData = new Object [huee][3];
         
            for(int hue=0;hue<huee;hue++){
            int num = hue+1;
            String nomer = String.valueOf(num);
            String fileName = Clustering.array1[hue];
            String clusterNum = String.valueOf(Clustering.array2[hue]);
            //rowData.addRow(new Object[]{nomer, fileName, clusterNum});
            rowData[hue][0] = nomer;
            rowData[hue][1] = fileName;
            rowData[hue][2] = clusterNum;
        }
        JTable jTable2 = new JTable (rowData, columnNames);
        jTable2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jTable2.getColumnModel().getColumn(0).setPreferredWidth(40);
        jTable2.getColumnModel().getColumn(1).setPreferredWidth(400);
        jTable2.getColumnModel().getColumn(2).setPreferredWidth(70);
        jTable2.setCellEditor(null);
        JScrollPane scrollPane = new JScrollPane(jTable2);
        jTable2.setFillsViewportHeight(true); 
        jFrame1.add(scrollPane);
        jFrame1.pack();
         
        
        /*jTable1 = new JTable(model);
        //JLabel jLabel7 = new JLabel("No");
        //JLabel jLabel8 = new JLabel("Nama File");
        //JLabel jLabel9 = new JLabel("# Cluster");
        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(40);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(400);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(50);
        jTable1.setCellEditor(null);
        jTable1.setBounds(30, 250, 490, 500);
        //jLabel7.setBounds(30, 60, 90, 50);
        jTable1.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(jTable1);
	
        jFrame1.add(scrollPane);
        jFrame1.setTitle("Result");
        //jFrame1.add(jLabel7);
        jFrame1.setVisible(true);
        jFrame1.pack();
        */
        

        //} catch (IOException ex) {
        //    Logger.getLogger(DetPlagGUI.class.getName()).log(Level.SEVERE, null, ex);
        //}
    }//GEN-LAST:event_processBActionPerformed


    private void resetBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetBActionPerformed
        datasetLoc.setText("");
        docLoc.setText("");
        outputLoc.setText("");
    }//GEN-LAST:event_resetBActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnExitActionPerformed
       /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DetPlagGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DetPlagGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DetPlagGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DetPlagGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DetPlagGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup AlgoGrp;
    private javax.swing.JButton browseDataset;
    private javax.swing.JButton browseDoc;
    private javax.swing.JButton browseOutput;
    private javax.swing.JButton btnExit;
    private javax.swing.JTextField datasetLoc;
    private javax.swing.JTextField docLoc;
    private javax.swing.JRadioButton em;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JRadioButton kmeans;
    private javax.swing.JTextField outputLoc;
    private javax.swing.JButton processB;
    private javax.swing.JButton resetB;
    // End of variables declaration//GEN-END:variables
}
