//import org.graphstream.graph.*;
//import org.graphstream.graph.implementations.*;
//import org.graphstream.stream.file.FileSourceGraphML.GraphMLConstants.NodeAttribute;

import javax.swing.*;
//import javax.swing.border.Border;

//import com.lowagie.text.Image;

import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

//import java.awt.BorderLayout;
//import java.awt.Container;
import java.awt.Graphics;
import javax.swing.GrayFilter;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
//import javax.swing.JScrollPane;
import javax.swing.JTextArea;



class Output{

    HashMap<Integer,ArrayList<Street>> finalLight = new HashMap<Integer,ArrayList<Street>>();
    HashMap<Street,ArrayList<Integer>> streetsvstime = new  HashMap<Street,ArrayList<Integer>>(); 
     ArrayList<IntersectionSchedule> intersectionSchedules1 = new ArrayList<IntersectionSchedule>();

     Output()
     {

     }
   
    Output(  HashMap<Integer,ArrayList<Street>> finalLight , HashMap<Street,ArrayList<Integer>> streetsvstime,ArrayList<IntersectionSchedule> intersectionSchedules1){
        this.finalLight = finalLight;
        this.streetsvstime=streetsvstime;
        this.intersectionSchedules1=intersectionSchedules1;
    }

    void output(){
       // Download download = new Download(finalLight,streetsvstime,intersectionSchedules1);
        //download.f1();
    }
    
}


 class Download extends JFrame{
            
    HashMap<Integer,ArrayList<Street>> finalLight = new HashMap<Integer,ArrayList<Street>>();
    HashMap<Street,ArrayList<Integer>> streetsvstime= new HashMap<Street,ArrayList<Integer>>();
    ArrayList<IntersectionSchedule> intersectionSchedules1 = new ArrayList<IntersectionSchedule>();
    JPanel panel1;
    public String str;

           /* void f1(){
                new Download(finalLight, streetsvstime,intersectionSchedules1);
            }*/
            public JButton button1;

            void downloadbut( HashMap<Integer,ArrayList<Street>> finalLight , HashMap<Street,ArrayList<Integer>> streetsvstime,ArrayList<IntersectionSchedule> intersectionSchedules1)
            {
                button1.addActionListener(e-> {

                    this.finalLight= finalLight;
                    this.streetsvstime = streetsvstime;
                    this.intersectionSchedules1= intersectionSchedules1;

                    File f = new File("file1.txt");
                    try{
                        PrintWriter printWriter = new PrintWriter(f);
                   // printWriter.print(finalLight);

                   
                  


            
        


                   printWriter.println("\nThe Green Light Schedules are :- \n\n");

for(IntersectionSchedule it : intersectionSchedules1)
{

printWriter.println("Intersection No. - "+ it.IntersectionNo+"\n"+"No. of Incoming Streets :- "+it.NumberOfIncomingOfStreets+"\n\n"+"Streets are :- \n");

for(OrderNDurationGreenLight o1 : it.orderNDurationGreenLights )
{
    printWriter.println(o1.StreetName1+ " "+o1.GreenTime);
}

printWriter.println("\n\n");
}


                   printWriter.println("STREETS VS TIME\n\n");
                   printWriter.println("1 for Green and 0 for Red \n\n");
                   
               
                   for(Street key1:streetsvstime.keySet())
                   {
                       printWriter.print(key1.StreetId+" - "+streetsvstime.get(key1)+"\n");
                   }
               
                   printWriter.println("\n\n");
               
                  printWriter.println("CAR-PATH VS TIME\n");


                   for(int key : finalLight.keySet())
{

    printWriter.print("CAR "+key+" :-\n\n");
    for(int z=0;z<finalLight.get(key).size();z++)
 { 
     
     printWriter.print(z+" - "+finalLight.get(key).get(z).StreetId+"   "); 
    

    }

 printWriter.println("\n\n");

}
                    printWriter.close();
        
                    }
                    catch(FileNotFoundException ex){
                        System.out.println("file not found");
                    }


                });
            }

            String inp()
            {
                return str;
            }
            
            Download()
            {
                this.setSize(500,250);
                this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                this.setTitle("Traffic Signalling");
                        
                
                        
                panel1 = new JPanel();
                final ImageIcon imageIcon = new ImageIcon("/Users/paritoshdutta/Desktop/Screenshot 2021-11-22 at 1.41.44 PM.png");

                JTextArea textfield= new JTextArea(20,40){

                java.awt.Image image = imageIcon.getImage();

      java.awt.Image grayImage = GrayFilter.createDisabledImage(image);
      {
        setOpaque(false);
      }

     public void paint(Graphics g) {
        g.drawImage(grayImage, 0, 0, this);
        super.paint(g);
      } 


                };

                textfield.setBounds(5, 5, 280, 50);
                panel1.add(textfield);


            

                JButton tc1= new JButton("Test Case 1");
           JButton tc2= new JButton("Test Case 2");
           JButton tc3= new JButton("Test Case 3");

          
          

           panel1.add(tc1);
           panel1.add(tc2);
           panel1.add(tc3);
          

        // Path fileName ; //Path.of("file.txt");

           tc1.addActionListener(e -> {

          Path fileName = Path.of("file.txt");
         // String content  = "";
          // Files.writeString(fileName, content);
   
           try {
               String actual = Files.readString(fileName);
               textfield.setText("Input File Preview \n\n\n"+ actual);
           } catch (IOException e1) {
               // TODO Auto-generated catch block
               e1.printStackTrace();
           }

           
          str=fileName.toString();
          inp();
          

           });


           tc2.addActionListener(e -> {

            Path fileName = Path.of("file2.txt");
            //String content  = "";
            // Files.writeString(fileName, content);
     
             try {
                 String actual = Files.readString(fileName);
                 textfield.setText("Input File Preview \n\n\n"+ actual);
             } catch (IOException e2) {
                 // TODO Auto-generated catch block
                 e2.printStackTrace();
             }

            
            str=fileName.toString();
            inp();

           });

           tc3.addActionListener(e -> {

            Path fileName = Path.of("file3.txt");
          //  String content  = "";
            // Files.writeString(fileName, content);
     
             try {
                 String actual = Files.readString(fileName);
                 textfield.setText("Input File Preview \n\n\n"+ actual);
             } catch (IOException e2) {
                 // TODO Auto-generated catch block
                 e2.printStackTrace();
             }

             str=fileName.toString();
             inp();

           });

           



           //Clicklistener click= new Clicklistener();

           button1= new JButton ("Download");
           // panel1.add(new JLabel(new ImageIcon("/Users/paritoshdutta/Desktop/Screenshot 2021-11-22 at 1.41.44 PM.png")));
           // button1.addActionListener(click);
            panel1.add(button1);
       
      
          
            this.add(panel1);
            this.setVisible(true);

            }
                
        /*    Download( HashMap<Integer,ArrayList<Street>> finalLight, HashMap<Street,ArrayList<Integer>> streetsvstime,  ArrayList<IntersectionSchedule> intersectionSchedules1) 
            {
                this.finalLight = finalLight;
                this.streetsvstime=streetsvstime;
                this.intersectionSchedules1=intersectionSchedules1;
               

                
 
            
           
            //textfield.
           
            }  */
            
            class Clicklistener implements ActionListener 
            {
            
            public void actionPerformed(ActionEvent e)
            {
            if (e.getSource() == button1) //checking if the button has been clicked
            {
                File f = new File("file1.txt");
                        try{
                            PrintWriter printWriter = new PrintWriter(f);
                       // printWriter.print(finalLight);

                       
                      


                
            


                       printWriter.println("\nThe Green Light Schedules are :- \n\n");

for(IntersectionSchedule it : intersectionSchedules1)
{

    printWriter.println("Intersection No. - "+ it.IntersectionNo+"\n"+"No. of Incoming Streets :- "+it.NumberOfIncomingOfStreets+"\n\n"+"Streets are :- \n");
    
    for(OrderNDurationGreenLight o1 : it.orderNDurationGreenLights )
    {
        printWriter.println(o1.StreetName1+ " "+o1.GreenTime);
    }

    printWriter.println("\n\n");
}


                       printWriter.println("STREETS VS TIME\n\n");
                       printWriter.println("1 for Green and 0 for Red \n\n");
                       
                   
                       for(Street key1:streetsvstime.keySet())
                       {
                           printWriter.print(key1.StreetId+" - "+streetsvstime.get(key1)+"\n");
                       }
                   
                       printWriter.println("\n\n");
                   
                      printWriter.println("CAR-PATH VS TIME\n");


                       for(int key : finalLight.keySet())
    {

        printWriter.print("CAR "+key+" :-\n\n");
        for(int z=0;z<finalLight.get(key).size();z++)
     { 
         
         printWriter.print(z+" - "+finalLight.get(key).get(z).StreetId+"   "); 
        

        }

     printWriter.println("\n\n");

   }
                        printWriter.close();
            
                        }
                        catch(FileNotFoundException ex){
                            System.out.println("file not found");
                        } 
            }

           



        }
    }
}
   