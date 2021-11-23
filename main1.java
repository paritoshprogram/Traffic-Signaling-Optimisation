import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
//import javafx.util.Pair;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import java.lang.Exception;
class main1{
    
    public static void main(String[] main) throws FileNotFoundException, InterruptedException{
        File file = new File("./file.txt");
        Scanner inputScanner = new Scanner(file);
        TakesInputFromFile dataset1 = new TakesInputFromFile(inputScanner);
        while(inputScanner.hasNext()){
            dataset1.firstline(); 
            dataset1.nextSlines();
            dataset1.nextVlines();
            dataset1.getvals();
       }
       inputScanner.close();

      
      
       return;
    }
}

class P {

    int val1;
    int val2;

    public P(int x,int y)
    {
        val1=x;  val2 = y;
    }
}


class Pair{

    int x;
   ArrayList<String> carP;
  
    
public Pair(int x, ArrayList<String> str)
    {
        this.x = x;
        carP=str;
       
    }

}

class Street{

    public int intStart;
    public int intEnd;
    public String StreetId;
    public int StreetLen;

public Street(int start, int end, String name,int time)
{
    intStart= start;
    intEnd = end;
    StreetId = name;

    StreetLen= time;

}



}

class Defined{

    Scanner inputScanner;
    int NoOfStreets, NoOfCars, DurationOfSimulation, NoOfIntersections, BonusPoints;
    int CarPathTime; 
    int index;
    ArrayList<Integer> IntersectionStart = new ArrayList<Integer>();
    ArrayList<Integer> IntersectionEnd = new ArrayList<Integer>();
    ArrayList<String> StreetName = new ArrayList<String>();
    ArrayList<Integer> StreetTime = new ArrayList<Integer>();
    ArrayList<Integer> NoOfStreetsForCar = new ArrayList<Integer>();
    ArrayList<ArrayList<String>> StreetNamesForCar= new ArrayList<ArrayList<String>>();
    ArrayList<String> duplicate = new ArrayList<String>();
    HashMap<Integer,Integer> DiffSimulationCarPath = new HashMap<Integer, Integer>();

    Defined(){
      
    }

    
}



class TakesInputFromFile extends Defined{
    Scanner inputScanner;
    int NoOfStreets, NoOfCars;
    ArrayList<Street> streets = new ArrayList<>();
   public HashMap< Integer, ArrayList<String>> map = new HashMap<>();
   public HashMap<Integer,ArrayList<Street>> map2 = new HashMap<>();
    TakesInputFromFile(Scanner inputReader){
        this.inputScanner = inputReader;
       
    }

    TakesInputFromFile()
    {
        int Priority=0;
    }

    void firstline(){
         DurationOfSimulation = inputScanner.nextInt();
         NoOfIntersections = inputScanner.nextInt();
        NoOfStreets = inputScanner.nextInt();
        NoOfCars = inputScanner.nextInt();
        BonusPoints = inputScanner.nextInt();
        System.out.println(DurationOfSimulation + " " + NoOfIntersections + ":" + NoOfStreets + ":" + NoOfCars + ":" + BonusPoints);
          
    }

    void nextSlines(){
         for(int i=1; i<=this.NoOfStreets; i++){
            int IntersectionStart = inputScanner.nextInt();
            int IntersectionEnd = inputScanner.nextInt();
            String StreetName = inputScanner.next().toLowerCase();
            int StreetTime = inputScanner.nextInt();
             
            Street newstreet = new Street(IntersectionStart,IntersectionEnd,StreetName,StreetTime);

        
            streets.add(newstreet);

          //  System.out.println(IntersectionStart + ":" + IntersectionEnd + ":" + StreetName + ":" + StreetTime);
            System.out.println(newstreet.intStart + " to " + newstreet.intEnd + " : " + newstreet.StreetId + " : " + newstreet.StreetLen);

           
         }

         //System.out.println(streets);
    }

    void nextVlines(){

       
        for(int i=1; i<=this.NoOfCars; i++){
            ArrayList<String> paths = new ArrayList<>();

            ArrayList<Street> paths1 = new ArrayList<Street>();

            int NoOfStreetsForCar = inputScanner.nextInt();
           
            String s1;
            for(int j=1; j<=NoOfStreetsForCar; j++){
                s1=inputScanner.next().toLowerCase();
                paths.add(s1); 
                
                for(Street st2: streets)
                {
                    if(s1.equals(st2.StreetId))
                    {
                        paths1.add(st2);
                    }
                }
                
            }
            System.out.println(NoOfStreetsForCar);
           
            map.put(i,paths);
            map2.put(i,paths1);

            System.out.println("Car "+i+ " " +map.get(i));

            s1=" ";
           
         }

        
    }

    public void getvals() throws InterruptedException{

        OutPutFile o1 = new OutPutFile();
       o1.noIntersections(map,streets);

       Priority p1 = new Priority();
       p1.sorting(map,DurationOfSimulation,streets);

      ArrayList<P> incstreet = o1.scheduling(streets,map,map2);

       HashMap<Street,ArrayList<Integer>> mp = o1.simulation(streets,map2,incstreet,DurationOfSimulation);

       o1.graphing(incstreet,streets,mp,DurationOfSimulation);

       

    }



}

class OrderNDurationGreenLight
{
    public String StreetName1 ;
    public int GreenTime ;

    public OrderNDurationGreenLight(String s1, int gt)
    {
        StreetName1=s1;
        GreenTime=gt;
    }
}

class IntersectionSchedule
{
    public int IntersectionNo;
    public int NumberOfIncomingOfStreets ;
    public ArrayList<OrderNDurationGreenLight> orderNDurationGreenLights;  

   public IntersectionSchedule(int intno, int nois)
   {
       IntersectionNo=intno;
       NumberOfIncomingOfStreets=nois;
       orderNDurationGreenLights= new ArrayList<OrderNDurationGreenLight>();

   }
}




class OutPutFile
{
    

    public int UniqueIntersections;
     
    ArrayList<Integer> Uniques = new ArrayList<Integer>();

    public ArrayList<IntersectionSchedule> intersectionSchedules = new ArrayList<IntersectionSchedule>();

    public HashMap<Integer,ArrayList<Street>> pathsvtime = new HashMap<Integer,ArrayList<Street>>();
  

    public void noIntersections(HashMap< Integer, ArrayList<String>> map1, ArrayList<Street> st)
    {
        HashMap<Integer,Integer> k = new HashMap<>();
       for(int i: map1.keySet())
       {
           for(int j=0; j<map1.get(i).size()-1;j++)
           {
               String s2 = map1.get(i).get(j);
           
                for(Street s3: st)
                {
                    if(s3.StreetId.contains(s2))
                    {
                       // System.out.println(s2);
                        // int j = st.indexOf(s2.getClass());

                         Street t = s3;

                       

                         if(k.containsKey(t.intEnd))
                         {
                         k.put(t.intEnd,k.get(t.intEnd)+1); }

                         else {
                         
                            k.put(t.intEnd,1);

                         }
                    }
                }

           }

       }

            int l=0; 

            for(int i : k.keySet())
            {
                if(k.get(i)>0)
                {
                    l++;
                    Uniques.add(i);
                }
            }

            UniqueIntersections= l;

            

            System.out.println("\n\nNo of Unique Intersections :- " + l+ "   List- "+Uniques);
            

    }

    class SortbyInc implements Comparator<P>{

        public int compare( P p1, P p2)
        {
            return p1.val2-p2.val2;
        }

    }

    class SortbyInc1 implements Comparator<P>{

        public int compare( P p1, P p2)
        {
            if(p1.val2==p2.val2)
            return p1.val1-p2.val1;

            else return 0;
        }

    }

    public boolean containscar(int intersection, ArrayList<Street> streets,HashMap<Integer,ArrayList<Street>>map)
    {
          String nameofSt="";
          for(Street st : streets)
          {
                if(st.intEnd==intersection)
                {
                   // System.out.println("true");
                    nameofSt= st.StreetId;

                    for(int i : map.keySet())
                    {
                         //for(int j=0;j<map.get(i).size();j++)
                        // {
                             if(map.get(i).get(0).StreetId.equals(nameofSt))
                             {
                                 return true;
                             }
                        // }
                    }
                }
          }

         

          return false;

    }


    public HashMap<Street,ArrayList<Integer>> simulation(ArrayList<Street> streets, HashMap< Integer, ArrayList<Street>> map2, ArrayList<P> incstreet,int DurationOfSimulation)
    { 

        int ncars;
       HashMap<Street,ArrayList<Integer>> streetvtime = new HashMap<Street,ArrayList<Integer>>();

      //  HashMap<Integer,ArrayList<Street>> pathsvtime = new HashMap<Integer,ArrayList<Street>>();

        for(Street s1: streets)
        {

            ArrayList<Integer> ar = new ArrayList<Integer>();

            for(int i=0;i<=DurationOfSimulation;i++)
            {
                ar.add(0);
            }
               
             streetvtime.put(s1,ar);
               

        }

      ncars = map2.size();

  /*    ArrayList<Street> arr = new ArrayList<Street>();

      arr.add(map2.get(1).get(0));
      arr.add(map2.get(2).get(0));

      int c=1; int t=1;

for(int k=1;k<=6;k++)
{
    
      for(int j=1;j<=ncars;j++)
      {
          Street cur= map2.get(j).get(c);
        
        if(k<t+cur.StreetLen)
        {
          arr.add(cur); }
          
          else {
              c++;
              t=k;
          }
     


      }


    }*/

   

    for(int j=1;j<=ncars;j++)
    {
         ArrayList<Street> arr = new ArrayList<Street>() ;
         arr.add(map2.get(j).get(0));

          int k;   int c=1; int t=1;
         for( k=1;k<=DurationOfSimulation;k++)
         {

            if(c>=map2.get(j).size() || t>DurationOfSimulation)
            {
                 break;
            }
    
            
               Street cur= map2.get(j).get(c);  
             //  System.out.println("\n"+c);
        
          if(k<t+cur.StreetLen )
          {
            arr.add(cur); 
      //  System.out.print(cur.StreetId+"  ");
        }
            
            else {

// check for delay   
                
              /*  for(int temp: pathsvtime.keySet())
                {
                    if(pathsvtime.get(temp)!=null && pathsvtime.get(j)!=null && temp<j)
                    {
                        if(pathsvtime.get(temp).get(k-1).intEnd==pathsvtime.get(j).get(k-1).intEnd)
                        {
                            if(c<map2.get(j).size() && t<=DurationOfSimulation)
                            {
                            cur= map2.get(j).get(c);
                            arr.add(cur); 
                            System.out.println("["+cur.StreetId+"]");
                        
                        }

                        }

                        

                       
                    }
                }
*/

                    c++;
                    t=k;
                    if(c<map2.get(j).size() && t<=DurationOfSimulation)
                    {
                    cur= map2.get(j).get(c);
                    arr.add(cur);  
                
                 //   System.out.print(" -> " + cur.StreetId);
                }

              
            }
       

         }
        // System.out.println("\n\n");

        pathsvtime.put(j,arr);


    }


    for(int val1: pathsvtime.keySet())
    {
        int flag=0;

        for(int val2: pathsvtime.keySet())
        {
            if(val1!=val2)
            {
                int size1 = pathsvtime.get(val1).size()<=pathsvtime.get(val2).size()?pathsvtime.get(val1).size():pathsvtime.get(val2).size();
                for(int j=1;j<size1;j++)
                {
                    if(pathsvtime.get(val1).get(j-1)!=pathsvtime.get(val2).get(j-1) && pathsvtime.get(val1).get(j-1).intEnd==pathsvtime.get(val2).get(j-1).intEnd )
                    {
                      //  System.out.println(pathsvtime.get(val1).get(j-1).StreetId);
                        pathsvtime.get(val2).add(j,pathsvtime.get(val2).get(j-1) );
                        flag=-1;
                        break;
                    }
                }
            }
        }

        if(flag==-1)
        {
            break;
        }




    }


    for(int i=0;i<=DurationOfSimulation;i++)
    {
        for(Street s4: streets)
        {
            for(P inter: incstreet)
            {
                if(inter.val1==s4.intEnd)
                {
                    if(inter.val2==1)
                    {
                        streetvtime.get(s4).set(i, 1);
                    }

                    else if(inter.val2>1)
                    {

                        for(Street s5: streets)
                        {
                            for(int y: pathsvtime.keySet())
                            {
                                if(i<pathsvtime.get(y).size())
                                {
                                  if(pathsvtime.get(y).get(i).StreetId.equals(s5.StreetId)) 
                                  {
                                    streetvtime.get(s5).set(i, 1);
                                    break;
                                  }

                                }
                            }

                        }

                    }
                }
            }
        }
    }


    System.out.println("STREETS VS TIME\n\n");
    System.out.println("1 for Green and 0 for Red \n\n");
    

    for(Street key1:streetvtime.keySet())
    {
        System.out.print(key1.StreetId+" - "+streetvtime.get(key1)+"\n");
    }

    System.out.println("\n\n");

    System.out.println("CAR-PATH VS TIME\n");
    
   for(int key : pathsvtime.keySet())
    {

        System.out.print("CAR "+key+" :-\n\n");
        for(int z=0;z<pathsvtime.get(key).size();z++)
     { 
         
         System.out.print(z+" - "+pathsvtime.get(key).get(z).StreetId+"   "); 
        

        }

     System.out.println("\n\n");

   }

   
  
   

  // System.out.println(pathsvtime);
     

return streetvtime;


    }


    public ArrayList<P> scheduling(ArrayList<Street> streets,HashMap<Integer,ArrayList<String>> map1,HashMap<Integer,ArrayList<Street>> map2)
    {

        
       
        HashMap<Integer,Integer> incomingStreets = new HashMap<Integer,Integer>();

     ArrayList<P> incStreets = new ArrayList<P>();

        for(Street s : streets)
        {
            //if(Uniques.contains(s.intEnd))
           // {
           if(incomingStreets.containsKey(s.intEnd))
           {
            incomingStreets.put(s.intEnd,incomingStreets.get(s.intEnd)+1); }

            else {
                incomingStreets.put(s.intEnd,1);
            }
        //}

        }

        for(int i : incomingStreets.keySet())
        {
            P vals = new P(i,incomingStreets.get(i));
          
           if(containscar(i,streets,map2)==true)
            {
            incStreets.add(vals);  }

        }

        Collections.sort(incStreets,new SortbyInc());
        Collections.reverse(incStreets);


        for(int i : incomingStreets.keySet())
        {
            P vals = new P(i,incomingStreets.get(i));
          
            if(containscar(i,streets,map2)==false)
            {
            incStreets.add(vals);  }

        }

        
       // Collections.sort(incStreets,new SortbyInc1());


       System.out.println("Intersection Priority Order :- \n");

        for(P j: incStreets)
{
    
    System.out.println("\n\nIntersection No. :- " +j.val1+"    "+"No. of Incoming Streets :- "+j.val2);
}


for(P k: incStreets)
{
    IntersectionSchedule is1 = new IntersectionSchedule(k.val1,k.val2);

    for(Street it: streets)
    {
        if(it.intEnd==k.val1)
        {
            OrderNDurationGreenLight g1 = new OrderNDurationGreenLight(it.StreetId,0);
            is1.orderNDurationGreenLights.add(g1);

        }
    }

    intersectionSchedules.add(is1);
      
}

System.out.println("\nThe Green Light Schedules are :- \n\n");

for(IntersectionSchedule it : intersectionSchedules)
{

    System.out.println("Intersection No. - "+ it.IntersectionNo+"\n"+"No. of Incoming Streets :- "+it.NumberOfIncomingOfStreets+"\n\n"+"Streets are :- \n");
    
    for(OrderNDurationGreenLight o1 : it.orderNDurationGreenLights )
    {
        System.out.println(o1.StreetName1+ " "+o1.GreenTime);
    }

    System.out.println("\n\n");
}

Output output = new Output(pathsvtime);
       output.output();
 
return incStreets;

    }

    public void graphing(ArrayList<P> incstreet,ArrayList<Street> streets,HashMap<Street,ArrayList<Integer>>mp, int dur) throws InterruptedException
    {
       
       Graph graph = new SingleGraph("Tutorial 1");

       System.setProperty("org.graphstream.ui", "swing");

      // graph { fill-mode: image-scaled-ratio-max; fill-image: url('data/Smiley_128.png'); }

    // graph.setAttribute("ui.stylesheet","graph{fill-mode: image-scaled-ratio-min; fill-image: url('/Users/paritoshdutta/Desktop/Screenshot 2021-11-22 at 1.41.44 PM.png');}"); 
    
    
       graph.display();

        for(P i: incstreet)
        {
            String s = Integer.toString(i.val1);
            graph.addNode(s);
 
        }
        for(Street st:streets)
        {
            String s1 = Integer.toString(st.intStart);
            String s2 = Integer.toString(st.intEnd);
            graph.addEdge(s1+s2,s1,s2);
            Edge e = graph.getEdge(s1+s2);
            e.setAttribute("ui.label", st.StreetId);
            e.setAttribute("ui.style", "text-size: 23px;  shape: cubic-curve; shadow-mode: plain; shadow-width: 2px; shadow-color: #999; shadow-offset: 0.5px, -0.5px; ");
        }
        
        for (Node node : graph) {
            node.setAttribute("ui.label", node.getId());
            Node n = graph.getNode(node.getId());
            n.setAttribute("ui.style", "size: 40px; fill-color: pink; text-size: 30px; shadow-mode: plain; shadow-width: 0px; shadow-color: #999; shadow-offset: 4px, -4px; shape: box;");
           
        }

        for(int i=0;i<=dur;i++) 
        {
            System.out.println("Time -  "+ i+" Secs"+"\n");

        for(Street sr : mp.keySet())
        {
            for(Street st1: streets)
{

    if(sr==st1)
    {
        String s1 = Integer.toString(st1.intStart);
        String s2 = Integer.toString(st1.intEnd);

        Edge e1 = graph.getEdge(s1+s2);


     /*   for(int k: pathsvtime.keySet())
        {
            if(pathsvtime.get(k).size()>i)
            {
            if(pathsvtime.get(k).get(i)==st1)
            {

                e1.setAttribute("ui.label", st1.StreetId+" "+"(Car "+k+" passing)");
               }

               else {
                e1.setAttribute("ui.label", st1.StreetId);
               }
           

            }
        }
        
    */
        
       

            if(mp.get(sr).get(i)==1)
            {
                e1.setAttribute("ui.style", "fill-color: green ; text-size: 23px;   shape: cubic-curve;   size: 1.5px; shadow-mode: plain; shadow-width: 2px; shadow-color: #999; shadow-offset: 0.5px, -0.5px;");
                System.out.println(st1.StreetId+"  GREEN");
            }
            else if(mp.get(sr).get(i)==0)
            {
                e1.setAttribute("ui.style", "fill-color: red; text-size: 23px;   shape: cubic-curve;   size: 3.2px; shadow-mode: plain; shadow-width: 2px; shadow-color: #999; shadow-offset: 0.5px, -0.5px; ");
                System.out.println(st1.StreetId+"  RED");
            }

        }

        

    }


}

try{Thread.sleep(3000);}
           catch(Exception e)
           {

           }

           System.out.println("\n\n");
           
        }
        
 /*graph.addEdge("DA","D","A");
 graph.addEdge("AB", "A", "B");
 graph.addEdge("BC", "B", "C");
 graph.addEdge("CA", "C", "A");
 
 //graph.setAttribute("ui.stylesheet", "graph { fill-color: red; }");
 Edge a1 = graph.getEdge("AB");
 a1.setAttribute("ui.style", "size: 5px; fill-color: red;");*/
 //graph.setAttribute("ui.stylesheet", "edge{size:30px;}");


 

}

}

class Priority extends TakesInputFromFile{

    class sortbydur implements Comparator<Pair>{

        public int compare( Pair p1, Pair p2)
        {
            return p1.x-p2.x;
        }

    }

public Priority()
{
    super();
}

/*static void compare(ArrayList<Pair> arr, int n)
{
    
    Collections.sort(arr, new Comparator<Pair>() {
        @Override public int compare(Pair p1, Pair p2)
        {
            return p1.x - p2.x;
        }
    });

    for (int i = 0; i < n; i++) {
        System.out.print(arr.get(i).x + " " + arr.get(i).carP+" ");
    }
    System.out.println();
}
*/





public void sorting(HashMap< Integer, ArrayList<String>> map , int DurationOfSimulation, ArrayList<Street> streets)
{
    int finalTime;  int sum;
    ArrayList<Pair> priori = new ArrayList<Pair>();

    for(int i:map.keySet())
    {
     sum=0; int j=0;
       for(String temp: map.get(i))
       {
        for(Street s3: streets)
        {
            if(s3.StreetId.contains(temp))
            {
                 Street t = s3;
                 if(j>0)
                 {
                  sum+=t.StreetLen;
                 }
                 j++;
 
            }
        }

       }
      // System.out.println(sum);

       finalTime = DurationOfSimulation-sum;

       priori.add(new Pair(finalTime,map.get(i)));

       


    }

   // Object[] arr = new Pair[priori.size()];
   // arr=  priori.toArray();

    //compare(priori,priori.size());

    Collections.sort(priori,new sortbydur());
    System.out.println("\nCar Priority Order\n");

  for(Pair i : priori)
  {
      
      System.out.println("Difference - " + i.x+ "  Car - " +i.carP);
  }
  System.out.println("\n");
    
}

}




