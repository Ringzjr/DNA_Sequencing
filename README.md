# DNA_Sequencing
Find the longest common subsequence between 5000 DNA sequences.

This is my attempt at finding the longest common subsequence using both the common LCS algorithm and the smith-watermen algorithm on one computer with one thread. 

The amount of files this program creates is due to the fact that the original program was meant for multiple computers, who all had to coordinate their outputs so that a final program could run through and process the solution.

The difference in speed between computer this much data with one computer vs with many computers is astronomical. Finding the solution without the edits to use multiple computers takes around 6 hours to complete.  

To run the code, you'll need to first run Sequence, then Deviation, then Deviation2, then Analyze, which will then output the average LCS length, best LCS length, and standard deviation. 
Again, the reason so many programs are written, is that this program was originally intended to be used by Amherst College's cluster of computers. This version is just an edit to test the run time using only one computer. 

- To run it on one computer, you'd first run Sequence, which performs the two algorithms on 5000^2 pairs of sequences and fills the folder with results files, each file containing the longest common subsequence between 1 of the 5000 sequences and all the others. Thus there will be 5000 results files, each containing 5000 entries. Sequence will also fill that folder with 5000 sum files, each containing the sum length of the longest sequences found
- Next, you'll run Deviation, which will sum up the sum files' values and divide by the total to find the average lcs length. Deviation will accumulate the amount each LCS varied from the average also create 5000 variance files 
- Deviation2 will go through those variance files and find the SQRT, giving us the standard deviation. 
- Finally, Analyze will use those sum files to find the average. This final processessing will be output to Average.txt
