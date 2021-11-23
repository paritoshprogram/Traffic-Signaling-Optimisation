import java.awt.*;  
import javax.swing.JFrame;  
  
public class temp extends Canvas{  
      
    public void paint(Graphics g) {  
       // g.drawString("Hello",40,40);  
        setBackground(Color.WHITE);  
       // g.fillRect(130, 30,100, 80);  
        g.fillOval(150,150,50, 50); 
        g.fillOval(150,250,50, 50);  
        g.fillOval(250,150,50, 50);  
        g.fillOval(50,150,50, 50);  
        g.fillOval(150,50,50, 50); 
        g.drawLine(175, 175, 175, 75);  
        g.drawLine(175, 75, 275, 175);  
        g.drawLine(275, 175, 175, 275);  
        g.drawLine(175, 275, 75, 175);  
        g.drawLine(75, 175, 175, 75);  
        g.drawLine(275, 175, 175, 175);  
        setForeground(Color.RED);  
       // g.fillOval(130,130,50, 60);  
     //   g.drawArc(30, 200, 40,50,90,60);  
        //g.fillArc(30, 130, 40,50,180,40);  
          
    }  
        public static void main(String[] args) {  
        temp m=new temp();  
        JFrame f=new JFrame();  
        f.add(m);  
        f.setSize(400,400);  
        //f.setLayout(null);  
        f.setVisible(true);  
    }  
  
}  