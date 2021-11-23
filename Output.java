import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.graphstream.stream.file.FileSourceGraphML.GraphMLConstants.NodeAttribute;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

class Output{

    HashMap<Integer,ArrayList<Street>> finalLight = new HashMap<Integer,ArrayList<Street>>();

   
    Output(  HashMap<Integer,ArrayList<Street>> finalLight ){
        this.finalLight = finalLight;
    }

    void output(){
        Download download = new Download(finalLight);
        download.f1();
    }
}


 class Download extends JFrame{
            
    HashMap<Integer,ArrayList<Street>> finalLight = new HashMap<Integer,ArrayList<Street>>();

            void f1(){
                new Download(finalLight);
            }
            private JButton button1;
                
             Download( HashMap<Integer,ArrayList<Street>> finalLight)
            {
                this.finalLight = finalLight;

            this.setSize(300,150);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setTitle("Traffic Signalling");
                    
            Clicklistener click= new Clicklistener();
                    
            JPanel panel1= new JPanel();
            button1= new JButton ("Download");
            button1.addActionListener(click);
            panel1.add(button1);
            this.add(panel1);
            this.setVisible(true);
            }
            
            class Clicklistener implements ActionListener
            {
            public void actionPerformed(ActionEvent e)
            {
            if (e.getSource() == button1) //checking if the button has been clicked
            {
                File f = new File("file1.txt");
                        try{
                            PrintWriter printWriter = new PrintWriter(f);
                        printWriter.print(finalLight);
                        printWriter.close();
            
                        }
                        catch(FileNotFoundException ex){
                            System.out.println("file not found");
                        }
            }
        }
    }
}
   