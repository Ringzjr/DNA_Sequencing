
// Sum up the 5015 variance files and find the sqrt.
// To get Standard deviation sw score and lcs lcs percentage
import java.io.*;



public class Deviation2{

    public static void main (String args[]) throws IOException{
        
        int files = 5015;
        
        double swvariance = 0.0;
        double lcsvariance = 0.0;
        
        for(int i=1; i<= 5015; i++){
            String name = "variance"+i+".txt";
            
            BufferedReader br = new BufferedReader(new FileReader(name));
            String line = br.readLine();
            int index = line.indexOf(":");
            line = line.substring(index+1);
            line = line.replaceAll("\\s","");
            
            swvariance += Double.parseDouble(line);
            
            
            line = br.readLine();
            int index2 = line.indexOf(":");
            line = line.substring(index2+1);
            line = line.replaceAll("\\s","");
            
            lcsvariance += Double.parseDouble(line);
            br.close();
        }
        
        
       
        
        BufferedReader b = new BufferedReader(new FileReader("Average.txt"));
        String line0 = b.readLine(); //total pairs checked 5015!
        int index3 = line0.indexOf(":");
        line0 = line0.substring(index3+1);
        line0 = line0.replaceAll("\\s","");
        int pairs = Integer.parseInt(line0);
        double swstd = Math.sqrt(swvariance/(double)pairs);
        double lcsstd = Math.sqrt(lcsvariance/(double)pairs);
        
        String line1 = b.readLine(); // average sw score
        String line2 = "SW Standard Deviation: "+swstd;
        String line3 = b.readLine(); // best SW
        String line4 = b.readLine(); // best score
        String line5 = b.readLine(); // average lcs percentage
        String line6 = "LCS Percentage Standard Deviation: "+lcsstd;
        String line7 = b.readLine(); // best LCS
        String line8 = b.readLine(); // best LCS percentage
        b.close();
        PrintWriter transfer = new PrintWriter("Average2.txt","UTF-8");
        transfer.println(line1);
        transfer.println(line2);
        transfer.println(line3);
        transfer.println(line4);
        transfer.println(line5);
        transfer.println(line6);
        transfer.println(line7);
        transfer.println(line8);
        transfer.close();
        
    }









}