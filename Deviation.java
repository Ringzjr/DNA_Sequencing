// goes through 5015 results files, summing up the variance^2
// and will put the most
// 5016^2 operations
// Will need to cluster this.
// Each computer will take the averages, and sum up the variance
// will create 5016 variance files
import java.io.*;
import java.text.DecimalFormat;

public class Deviation{
    
    public static void main(String args[]) throws IOException{
        int id = Integer.parseInt(args[0]);
        System.out.println(id);
        boolean sw = true;
        String name = "results"+id+".txt";
        BufferedReader br = new BufferedReader(new FileReader("Average.txt"));
        
        String line = br.readLine();        // line = average score
        int index = line.indexOf(":");
        String avs = line.substring(index+1);
        avs = avs.replaceAll("\\s","");
        double averagescore = Double.parseDouble(avs);
        
        br.readLine(); // skip best sw
        br.readLine(); // skip best score
        
        line = br.readLine();        // line = average lcs percentage
        int index2 = line.indexOf(":");
        String avp = line.substring(index2+1);
        avp = avp.replaceAll("\\s","");
        double averagepercentage = Double.parseDouble(avp);
        
        br.close();
        
        double swvariation = 0.0;
        double lcsvariation = 0.0;
        BufferedReader results = new BufferedReader(new FileReader(name));
        
        String r = results.readLine();
        //need score and need percentage
        // will subtract score and average, then square it, add it variation
        int counter = 0;
        while (r != null && r.length() > 0){
            while((r != null) && (r.indexOf("score") < 0) && (r.indexOf("percentage") < 0)){
                r = results.readLine();
            }
            if(r == null)break;
            int index3 = r.indexOf(":");
            String ph = r.substring(index3+1);
            ph = ph.replaceAll("\\s","");
            double score = format(ph);
            
            double average;
            if (sw)
                average = averagescore;
            else
                average = averagepercentage;
            
            double var = Math.pow((score - average),2);
            
            if(sw)
                swvariation += var;
            else{
                lcsvariation += var;
                System.out.println("ID: "+id+"; "+ ++counter);}
            
            sw = !sw;
            r = results.readLine();
        }
        
        String name2 = "variance"+id+".txt";
        PrintWriter v = new PrintWriter(name2,"UTF-8");
        v.println("SW variance: "+swvariation);
        v.println("LCS variation: "+lcsvariation);
        v.close();
        
        
    }
    
    public static double format(String doub){
        
        DecimalFormat form = new DecimalFormat("0.00");
        double d = Double.parseDouble(doub);
        double thing = Double.parseDouble(form.format(d).toString());
        
        return thing;
        
    }

    
    
    
    
    
}
