// gets sum from 5015 sum files, and creates sum of pairs checked
// divides sum by pairs checked to get:
// average score, lcs percentage
    // instant
// also compares the most similar pairs to find the most most similar.
import java.text.DecimalFormat;
import java.io.*;

public class Analyze{
   


    public static void main (String args[]) throws IOException{
        int files = 5015;
        DecimalFormat form = new DecimalFormat("0.00");
    
        int pairs = 0;
        double sumswscore = 0.0;
        String bestsw = "";
        double bestscore = 0.0;
        
        double lcssumpercentage = 0.0;
        String bestlcs = "";
        double bestlcspercentage = 0.0;
        
        
        
        
        for (int i = 1; i <= files; i++){
            System.out.println("File: "+i);
            String name = "sum"+i+".txt";
            BufferedReader br = new BufferedReader(new FileReader(name));
            
            String line = br.readLine(); // line = pairs checked
            int index = line.indexOf(":");
            String pair = line.substring(index+1);
            pair = pair.replaceAll("\\s","");
            
            pairs += Integer.parseInt(pair);
            
            line = br.readLine();       // line = sum sw score
            int index2 = line.indexOf(":");
            String scoret = line.substring(index2+1);
            scoret = scoret.replaceAll("\\s","");
            double scoref = Double.parseDouble(scoret);
            double score = Double.parseDouble(form.format(scoref).toString());
            
            sumswscore += score;
            
            line = br.readLine();       // line = best sw
            int index3 = line.indexOf(":");
            String bestsw2 = line.substring(index3+2);
            
            line = br.readLine();       // line = best sw score
            int index4 = line.indexOf(":");
            String bestscoret = line.substring(index4+2);
            bestscoret = bestscoret.replaceAll("\\s","");
            double bestscore2 = format(bestscoret);
            
            if (bestscore2 > bestscore){bestscore = bestscore2;
                bestsw = bestsw2;}
            
            line = br.readLine();       // line = lcs sum percentage
            int index5 = line.indexOf(":");
            String percentagef = line.substring(index5+1);
            percentagef = percentagef.replaceAll("\\s","");
            double percentage = format(percentagef);
            lcssumpercentage += percentage;
            
            line = br.readLine();       // line = best lcs
            int index6 = line.indexOf(":");
            String bestlcs2 = line.substring(index6 + 2);
            
            line = br.readLine();        // line best lcs percentage
            int index7 = line.indexOf(":");
            String bestpercent = line.substring(index7+1);
            bestpercent = bestpercent.replaceAll("\\s","");
            double bestpercentage = format(bestpercent);
            
            if (bestpercentage > bestlcspercentage){bestlcspercentage = bestpercentage; bestlcs = bestlcs2;}
        }
        
        double averageswscore = sumswscore / pairs;
        String placehold = ""+averageswscore;
        averageswscore = format(placehold);
        double averagelcspercentage = lcssumpercentage / pairs;
        
        PrintWriter print = new PrintWriter("Average.txt","UTF-8");
        print.println("pairs: "+pairs);
        print.println("average sw score: "+averageswscore);
        print.println("best sw: "+bestsw);
        print.println("best sw score: "+bestscore);
        print.println("average lcs: "+averagelcspercentage);
        print.println("best lcs: "+bestlcs);
        print.println("best lcs percentage: "+bestlcspercentage);
        print.close();
        
        
        
    }
    
        public static double format(String doub){
            
            DecimalFormat form = new DecimalFormat("0.00");
            double d = Double.parseDouble(doub);
            double thing = Double.parseDouble(form.format(d).toString());
            
            return thing;
            
        }
    
    
    
}










