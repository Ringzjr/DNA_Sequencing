import java.util.ArrayList;
import java.io.*;

public class Sequence{



    public static void main(String[] args) throws IOException{
        
        int total = 1;
        BufferedReader br = new BufferedReader(new FileReader("sequence.fasta"));
        String line2;
        while((line2 = br.readLine()) != null)
        {if(line2.length() == 0)total++;}
        System.out.println("total entries: "+ total);
        br.close();
        
        int p = Integer.parseInt(args[0])+1;
        //int q = Integer.parseInt(args[1]);
        String one = read(p);
        int onesize = one.length();
        /*System.out.println();
        System.out.println("a size: "+onesize);
        String two = read(q);
        System.out.println("b size: "+two.length());
        System.out.println();
        String shortest;
        if (Math.min(one.length(), two.length()) == one.length())
            shortest = one;
        else
            shortest = two;

        long start = System.nanoTime();
        String lcs = LCS (one, two);
        long done = (System.nanoTime() - start) / 1000000;
        
        System.out.println("LCS: "+lcs);
        System.out.println();
        System.out.println("size: "+lcs.length());
        System.out.println(("percentage: "+lcs.length()/(double)shortest.length()*100) + " %" );
        System.out.println("LCS takes : "+done+" miliseconds.");
        System.out.println();
        
        long start2 = System.nanoTime();
        String[] sw = SW (one, two);
        long done2 = (System.nanoTime() - start2) / 1000000;
        
        System.out.println("SW: "+sw[0]+'\n');
        
        System.out.println("size: "+sw[0].length());
        System.out.println("score: "+sw[1]);
        System.out.println("SW takes : "+ done+" miliseconds.");
        */
        //long start3 = System.nanoTime();
        int swsumlength = 0;
        double swsumscore = 0.0;
        int lcssumlength = 0;
        double lcssumpercentage = 0.0;
        int pairschecked = total - p;
        
        double bestswscore = 0.0;
        int bestswID = 0;
        
        double bestpercentage = 0.0;
        int bestlcsID = 0;
        
        String name2 = "results"+p+".txt";
        PrintWriter print = new PrintWriter(name2, "UTF-8");
        
        for (int i = p; i < total; i++){
            String three = read(i);
            int threesize = three.length();
            String shortest2;
            if (Math.min(onesize,threesize) == onesize)
                shortest2 = one;
            else
                shortest2 = three;
            
            String longest = LCS(one,three);
            String[] sw2 = SW(one,three);
            String swalignment = sw2[0];
            double swscore = Double.parseDouble(sw2[1]);
            
            if (bestswscore < swscore){bestswscore = swscore; bestswID = i;}
            
            //System.out.println(p+" x "+i);
            print.println(p+" x "+i+":");
            
            print.println("SW score: "+swscore);
            swsumscore += swscore;
            
            print.println("SW size: "+swalignment.length());
            swsumlength += swalignment.length();
            print.println("SW alignment "+'\n'+sw2[0]);
            
            double lcssize = (double)longest.length();
            double percentage = lcssize/(double)shortest2.length();
            
            if (bestpercentage < percentage){ bestpercentage = percentage; bestlcsID = i;}
            
            print.println();
            print.println("LCS size: "+ lcssize);
            lcssumlength += longest.length();
            print.println("LCS percentage: "+percentage);
            lcssumpercentage += percentage;
            print.println("LCS alignment "+'\n'+longest);
            print.println();
            
        }
        print.close();
        String name = "sum" + p + ".txt";
        PrintWriter printer = new PrintWriter(name, "UTF-8");
        printer.print("pairs checked: "+pairschecked+'\n'+
                      "sum sw score: "+swsumscore+'\n'+
                      "best sw: "+p+" x "+bestswID+'\n'+
                      "best score: "+bestswscore+'\n'+
                      "lcs sum percentage: "+lcssumpercentage+'\n'+
                      "best lcs: "+p+" x "+bestlcsID+'\n'+
                      "best lcs percentage: "+bestpercentage+'\n');
        printer.flush();
        printer.close();
        
        //long done3 = (System.nanoTime() - start3) / 1000000;
        //System.out.println("5015 comparisons took: "+done3+" miliseconds.");
    }


    
    
    
    
    
    public static String read(int index) throws IOException{
        int counter = 1;
        BufferedReader br = new BufferedReader(new FileReader("sequence.fasta"));
        
        while(counter != index){
            br.readLine();
            String line = br.readLine();
            while(line.length() > 0){
                line = br.readLine();
            }
            counter++;
        }
        
        br.readLine();
        String line = br.readLine();
        String string = "";
        while(line != null && line.length() > 0){
            string = string + line;
            line = br.readLine();
        }
    
        
        return string;
        
    }



    public static String LCS (String inputa, String inputb){
        char[] a = inputa.toCharArray();
        char[] b = inputb.toCharArray();
        int[][] H = new int[a.length+1][b.length+1];
        String[][] map = new String[a.length+1][b.length+1];
        int[] best = new int[2];
        
        for (int i = 0; i < H.length; i++){
            H[i][0] = 0;
            map[i][0] = "none";}
        for (int j = 0; j < H[0].length; j++){
            H[0][j] = 0;
            map[0][j] = "none";
        }

        
        for (int i = 1; i < H.length; i++)
            for (int j = 1; j < H[0].length; j++){
                int diagnol;
                if(a[i-1] == b[j-1])
                    diagnol = H[i-1][j-1] + 1;
                else
                    diagnol = Integer.MIN_VALUE;
                int up = H[i-1][j];
                int left = H[i][j-1];
                
                
                
                
                int choice = Math.max(diagnol, Math.max(up,left));
                H[i][j] = choice;
                
                if(choice > H[best[0]][best[1]])
                {
                    best[0] = i; best[1] = j;
                    //System.out.println("best is: "+H[i][j]);
                }
                
                if(choice == diagnol)
                    map[i][j] = "diagnol";
                else if (choice == up)
                    map[i][j] = "up";
                else if (choice == left)
                    map[i][j] = "left";
                else
                    map[i][j] = "whoh";
                
                
            }
        
        int n = best[0];
        int m = best[1];
        String ref = map[n][m];
        ArrayList<Character> alignment_backwards = new ArrayList<Character>();
        String alignment = "";
        
        /*for (int i = 0; i < H.length; i++){
            System.out.println();
            for (int j = 0; j < H[0].length; j++)
                System.out.print("| H["+i+"]["+j+"]: "+H[i][j]);
        }*/
        
        while (!ref.equals("none")){
            if (ref.equals("diagnol")){
                //System.out.println("n : "+n);
                //System.out.println("m : "+m);
                alignment_backwards.add(a[(--n)]);
                ref = map[n][--m];
            }
            else if (ref.equals("up")){
                ref = map[--n][m];
            }
            else if (ref.equals("left")){
                ref = map[n][--m];
            }
            else{
                System.out.println("H["+n+"]["+m+"] is: "+H[n][m]);
                ref = "none";
            }
            
        }
        
        for (int i = alignment_backwards.size()-1; i >= 0; i--)
            alignment = alignment + alignment_backwards.get(i);
        
        
        return alignment;
        
    }





    public static String[] SW(String inputa, String inputb){
        char[] a = inputa.toCharArray();
        char[] b = inputb.toCharArray();
        double[][] H = new double[a.length+1][b.length+1];
        String[][] map = new String[a.length+1][b.length+1];
        double bestscore = 0.0;
        int[] bestindex = {0,0};

        
        for (int i = 0; i < H.length; i++){
            H[i][0] = 0.0;
            map[i][0] = "none";}
        for (int j = 0; j < H[0].length; j++){
            H[0][j] = 0.0;
            map[0][j] = "none";
        }
        
        for (int i = 1; i < H.length;i++)
            for (int l = 1; l < H[0].length;l++){
                double s = -.33;
                double w = 1.33;
                if(a[i-1]==b[l-1])
                    s = 1.0;
        
                // Max of Diagnol + 1/ Diagnol - .33, Up - 1.33, Left - 1.33,0
                double diagnol = (H[i-1][l-1] + s);
                double up = H[i-1][l] - w;
                double left = H[i][l-1] - w;
                
                double holder = Math.max(diagnol,up);
                double score = Math.max(holder,left);
                H[i][l] = Math.max(score,0.0);
                if (H[i][l] > bestscore){
                    bestscore = H[i][l];
                    bestindex[0] = i; bestindex[1] = l;
                }
                
                if(H[i][l] == 0)
                    map[i][l] = "none";
                else if (H[i][l] == diagnol)
                    map[i][l] = "diagnol";
                else if (H[i][l] == up)
                    map[i][l] = "up";
                else if (H[i][l] == left)
                    map[i][l] = "left";
                else
                    map[i][l] = "huh?";
                
                
            }
        
        // traceback, will go to file later
        int n = bestindex[0]; int m = bestindex[1];
        String score = "" + H[n][m];
        String ref = map[n][m];
        ArrayList<Character> alignment_backwards = new ArrayList<Character>();
        String alignment = "";
        
        while (!ref.equals("none")){
            if (ref.equals("diagnol")){
                alignment_backwards.add(a[n-1]);
                ref = map[--n][--m];
            }
            else if (ref.equals("up")){
                ref = map[--n][m];
            }
            else if (ref.equals("left")){
                ref = map[n][--m];
            }
            else{
                System.out.println("H["+n+"]["+m+"] is: "+H[n][m]);
                ref = "none";
            }
            
        }
        
        for (int i = alignment_backwards.size()-1; i >= 0; i--)
            alignment = alignment + alignment_backwards.get(i);
        
        
        return new String[]{alignment,score};
    }



}