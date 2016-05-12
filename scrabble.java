package lab4;
/*
 * Make  yourself  unbeatable  at  Scrabble  by  writing  a  computer  program that  uses  your  letters  to  make 
 * the  longest  words possible  and gives  you the top 10 suggestions. 
 * You will need to load and use the file dictionary.txt which contains all of the words in the English language
 * */

import java.util.*;

public class scrabble 
{
	public static void main(String args[])
	 {
	    FileIO reader = new FileIO();
	    Scanner sc = new Scanner(System.in);
	    
	    System.out.println("please enter your letters");
	    String ltrs= sc.nextLine();

	    String[] dict = reader.load("H://MU//Computer Science//cs211 workspace//labs finished/src/lab4/dictionary.txt");  //file to String array
	    String res[]= new String[10000];
	   
	    int ii=0;
	    for(int i=0;i<dict.length;i++)
	    {
	    	boolean match=Word(dict[i],ltrs);
	    	if(match)
	    	{
	    		
	    		res[ii]=dict[i];//assign to valid arra
	    		
	    		ii++;//
	    	}
	    }
	    System.out.println("fingers crossed!");
     
	    //sorts out words by word length
	    for(int j=0; j<res.length;j++)
	    {
	    	for (int i=j+1 ; i<res.length; i++)
           {
               if(res[i]==null)
               {
               	//do nothing
               }
	            else if(res[i].length()>res[j].length())
	            {
	                 String temp= res[j];
	                 res[j]= res[i];
	                 res[i]=temp;
	            }
	       }
	   }
	   for(int i=0;i<10;i++)
	   {
	       if(res[i]!=null)//use null not"null"
	       {
	           System.out.println(res[i]);
	       }
	   }
	        

	       sc.close();
	 }
	 public static boolean Word(String in, String let)
	 {
		
		 char []inputArray=in.toCharArray();
		 char [] letterArray=let.toCharArray();
		 
		 for(int i=0;i<inputArray.length;i++)//cycle through dict
		 {
			 for(int x=0;x<letterArray.length;x++)//cycle through letters
			 {
				 if(inputArray[i]==letterArray[x])
				 {
					 inputArray[i]='\0';
					 letterArray[x]='\0';//don't nee to look for it anymore
				 }

			 }
		 }
		 int corr=1;
		 for(int i=0;i<inputArray.length;i++)//cycle though dict word has the same lenght as 0s in
		 {
			if(inputArray[i]==0)
			{
				corr++;	//increment coreectness	
			}
			
			if(corr==inputArray.length)//full word match
			{
				return true;
			}
		 }

		return false;
	 }
	
}


