package lab5;

import java.util.*;

public class huffmanAdapt
{
	public static double[] main(String arg)//string parameter
	{
		int[]ASCIIarray = new int[256];//ascii array
	
	  	Scanner sc = new Scanner(arg);//reading in string
	
	  	String word = new String("");
	
	  	word = sc.nextLine();
	
	  	for(int i=0; i<word.length(); i++) //loops from 0 to the length of the String
	  	{
	  		int numWord = (int)word.charAt(i); //Sets numWord equal to the decimal value of the ASCII character
	
	  		ASCIIarray[numWord]++; //Increments the slot of the corresponding ASCII character
	
	  		int bin = Integer.parseInt(Integer.toBinaryString(numWord)); //Converts the decimal value of the ASCII character into binary
	
	  		String binary = new String(""+bin); //converts binary number to String
	
	  		if(binary.length()==6)
	  		{
	  			System.out.print("0" + binary + " "); //Prints the binary number to the screen
	  		}
	  		else
	  		{
	  			System.out.print(binary + " "); //Prints the binary number to the screen
	  		}
	  	
	  	}
	  	//beginning of huffman
	  	System.out.println("");
	
		double [] freq = new double[256];//
		int ultcounter=0;//total amount of individual characters
		int ultbig=0;//the ultimate count of all characters
			
		for(int j=0; j<ASCIIarray.length; j++)//traverse through string characters
		{
			if(ASCIIarray[j]!=0)
			{
				ultcounter++;//increment 1 ... a new individual character	
				ultbig+=ASCIIarray[j];//amount of characters
			}
		}
		
		int position=0;//current position
		int bigg=0;//current biggest value
		int count=0;//the amount of outputs
	
		int j=0;
		for(int l=0; l<word.length();l++)//cycle through array
		{	
			bigg=0;//biggest found is now 0
	          
			if(count<ultcounter)//will only output the amount of individual characters actually in the text
			{
				for(int k = 0; k<ASCIIarray.length; k++)//cycle through the characters and their frequencies
				{ 
					if(ASCIIarray[k]!=0)//ignore characters that weren't in the text
					{
						int currbig=ASCIIarray[k];//begin with assigning a found character to current big
								
						if(currbig>bigg)//if this is bigger than the actuall biggest found
						{
							bigg=currbig;//let it equal bigg
							position=k;//record the position				
						}
					}
				}
				
				double fbigg = (double) bigg;//convert to double
//****************************************************************************************************************************************************
				double fultbig = (double) ultbig-233;//convert to double, -233 because i'm ignoring spaces in this text.
//******************************************************************************************************************************************************************
				double r=(double) (fbigg/fultbig);//record the ratio of the character then convert it to double
				System.out.print("'"+(char)position+"' appeared "+bigg+" times   ");//output occurences
				System.out.println(r*100);//output frequencies
					
				freq[j]=r*100;//convert to % and store away
				j++;//increment freq array
				
			    ASCIIarray[position]=0;//set the character ascii to 0n so we don't find it again
				count++;//executed once so increment
			}
			position=0;//reset the position(possibly not even needed)
		}
		sc.close();//close scanner
		return freq;//return the prequency array associated with this text     
	}
}
