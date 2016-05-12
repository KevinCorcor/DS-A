package lab2;
/*lab2
 * This programming exercise is the first part of creating a Huffman encoding program. You will continue it next week. 
 * Write a Java program which takes in a line from the user (using Scanner) and then outputs the characters in the line in order of their frequency. 
 * If two characters have the same frequency then output them in alphabetical order.
 * For example,
 * Input: Forever and ever
 * Output: er vFadno
 * */
import java.util.*;

public class letterFreq
{
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		
		int ar[] =new int[256];
		
		for(int i =0; i<s.length();i++)
		{
			int asc = (int) s.charAt(i);
			ar[asc]++;
			
		}
		int ultcounter=0;
		for(int j=0;j<ar.length;j++)
		{
			if(ar[j]!=0)
			{
				ultcounter++;			
			}

		}
		int position=0;
		int bigg=0;
		int count=0;
		
		sc.close();
		
		for(int l=0; l<s.length();l++)
		{	
			bigg=0;
	          
			if(count<ultcounter)//remember i was originally canceling my search with (ar[l]!=1) deleting not counted occurences
			{
				for(int k = 0; k<ar.length; k++)
				{ 
					if(ar[k]!=0)
					{
						int currbig=ar[k];
							
						if(currbig>bigg)
						{
							bigg=currbig;
							position=k;
					
						}
					}
				
				}
					System.out.println((char)position);
					ar[position]=0;	
					count++;
					
			}
			position=0;
	          
		}
	}
}

	