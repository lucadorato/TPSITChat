/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.LineBorder;


/**
 *
 * @author lucad
 */
public class LoggedIndex extends javax.swing.JFrame {

     private ArrayList<String> Contacts;
     private ArrayList<JLabel> LContacts;
     private String LocalUser;
     private ArrayList<ArrayList<Message>> Messages;
     private int NScreenMessage = 0;
    /**
     * Creates new form LoggedIndex
     */
    public LoggedIndex() {
        initComponents();
        
        InitArrayList();
    }
    
    private void InitArrayList()
    {
        LocalUser = "LocalUser";
        Contacts = new ArrayList<String>();
        LContacts = new ArrayList<JLabel>();
        for(int i = 0; i<5;i++)
        {
            Contacts.add("Contact"+i);
        }
        
        Messages = new ArrayList<ArrayList<Message>>();
        for(int i = 0; i<5;i++)
        {
            ArrayList<Message> ContMessages = new ArrayList<Message>();
            for(int j = 0; j<5;j++)
            {
                if(j%2 == 0)
                    ContMessages.add(new Message("Message"+(j*(i+1)),LocalUser));
                else
                    ContMessages.add(new Message("Message"+(j*(i+1)),"RemoteUser"));
            }
            Messages.add(ContMessages);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 415, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 317, Short.MAX_VALUE)
        );

        jScrollPane2.setViewportView(jPanel2);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane3.setViewportView(jTextArea1);

        jButton1.setText("Send");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        // TODO add your handling code here:
        // TODO add your handling code here:
        JPanel p2 = new JPanel();
        // p2.setLayout(new BoxLayout(p2, BoxLayout.Y_AXIS));
        //p2.setLayout(new GridBagLayout());
        p2.setLayout(null);
        p2.setBackground(Color.LIGHT_GRAY);
        p2.setPreferredSize(new Dimension(70, 600));
        p2.setAutoscrolls(true);
        jScrollPane1.setViewportView(p2);
        //JScrollPane scrollPane = new JScrollPane(p2);
//        scrollPane.setHorizontalScrollBarPolicy(
//                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        //jScrollPane1.setBounds(0, 0, 70, 800);
        for(int i = 0; i<Contacts.size(); i++)
        {
            //LContacts.add(new JLabel(Contacts.get(i)));
            //LContacts.add(new JLabel(Contacts.get(i)));
            JPanel jp = new JPanel();
            jp.setLayout(null);
            jp.setBackground(Color.WHITE);
            //jp.setPreferredSize(new Dimension(70, 30));
            jp.setBounds(0,i*50,170,50);
            //LContacts.get(i).setBounds(0, 50*i, 100, 50);
            //LContacts.get(i).setMinimumSize(new Dimension(180,50));
            //LContacts.get(i).setMaximumSize(new Dimension(170, 50));
            jp.setBorder(new LineBorder(Color.RED));
            //JButton b = new JButton(Contacts.get(i));
            LContacts.add(new JLabel(Contacts.get(i)));
            LContacts.get(i).setName(String.valueOf(i));
            //b.setPreferredSize(new Dimension(70, 30));
            LContacts.get(i).setBounds(0,0,170,50);
            //ChatIdx = i;
            LContacts.get(i).addMouseListener(new java.awt.event.MouseAdapter() {
                
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    
                    jLableIMouseClicked(evt);
                }
        });
            // b.setMinimumSize(new Dimension(100,50));
            //b.setBounds(0,50*i,100,50);
            jp.add(LContacts.get(i));
            //sp1.setLayout(new FlowLayout());
            
            p2.add(jp);
        }
        
    }//GEN-LAST:event_formWindowOpened

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
        String sMessage = jTextArea1.getText();
        
        JLabel MessLabel = new JLabel(sMessage);
        MessLabel.setBackground(Color.GREEN);
        MessLabel.setOpaque(true);
        MessLabel.setMinimumSize(new Dimension(70,40));
        MessLabel.setBounds(0, NScreenMessage*50, MessLabel.getPreferredSize().width, MessLabel.getPreferredSize().height);
        jPanel2.add(MessLabel);
        jPanel2.revalidate();
        jPanel2.repaint();
        NScreenMessage++; 
        
        jTextArea1.selectAll();
        jTextArea1.replaceSelection("");
        
        
    }//GEN-LAST:event_jButton1MouseClicked

    private void jLableIMouseClicked(java.awt.event.MouseEvent evt) {                                      
        // TODO add your handling code here:
        jPanel2.removeAll();
        jPanel2.revalidate();
        jPanel2.repaint();
        jPanel2.setLayout(null);
        jPanel2.setBackground(Color.LIGHT_GRAY);
        jPanel2.setPreferredSize(new Dimension(319, 600));
        jPanel2.setAutoscrolls(true);
        JLabel name = (JLabel)evt.getSource();
        int JLIdx = Integer.parseInt(name.getName());
        for(int i = 0; i<Messages.get(JLIdx).size();i++)
        {
            JLabel MessLabel = new JLabel(Messages.get(JLIdx).get(i).getMsg());
            MessLabel.setBackground(Color.GREEN);
            MessLabel.setOpaque(true);
            MessLabel.setMinimumSize(new Dimension(70,40));
            if(Messages.get(JLIdx).get(i).getSender().equals(LocalUser))
                MessLabel.setBounds(0, i*50, MessLabel.getPreferredSize().width, MessLabel.getPreferredSize().height);
            else
                MessLabel.setBounds(200, i*50, MessLabel.getPreferredSize().width, MessLabel.getPreferredSize().height);
            jPanel2.add(MessLabel);
            NScreenMessage++;
        }
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
