package lab5;

public class getDiff 
{
	public static String main(double freq[])
	{
		double[]Finnish = {12.22, 10.82, 8.83, 8.75, 7.97, 7.86, 5.76, 5.61, 5.01, 4.97};//top ten letter frequencies
    	double []English = {12.10, 8.94, 8.55, 7.47, 7.33, 7.17, 6.73, 6.33, 4.96, 4.21};
    	double[]German = {15.99, 9.59, 7.71, 7.60, 6.43, 6.41, 6.34, 4.92, 4.11, 3.76};
    	double[]Swedish = {10.15, 9.38, 8.54, 8.43, 7.69, 6.59, 5.82, 5.28, 4.70, 4.48};
    	double[]Russian = {10.61, 8.21, 8.04, 7.98, 6.72, 5.83, 5.71, 5.38, 4.75, 4.32};
    	double[]French = {14.47, 7.98, 7.60, 7.32, 7.21, 7.11, 6.86, 5.86, 5.55, 5.39};
       	double[]Danish = {15.45, 8.96, 7.24, 6.86, 6.03, 6.00, 5.86, 5.81, 5.23, 4.64};
    	double[]Icelandic = {10.11, 8.58, 7.71, 7.58, 6.42, 5.63, 4.95, 4.56, 4.53, 4.39};
    	double[]Polish = {9.02, 8.09, 7.90, 7.51, 5.81, 5.17, 5.06, 4.78, 4.46, 3.96};
    	
    	String []lang = {"Finnish","English","German","Swedish","Russian","French","Danish","Icelandic","Polish"};//corresponding array of string languages
    	
    	double diff[]=new double[9];//record differences 
    	//measure the value of all differences between our freq array and above arrays 
    	for(int i=0;i<10;i++)
    	{
    		diff[0]+=Math.abs(Finnish[i]-freq[i/*+1*/]);
    	}
    	System.out.println("the difference between the language and Finnish is "+diff[0]+"%");
    	
    	for(int i=0;i<10;i++)
    	{
    		diff[1]+=Math.abs(English[i]-freq[i/*+1*/]);
    	}
    	System.out.println("the difference between the language and English is "+diff[1]+"%");
    	
    	for(int i=0;i<10;i++)
    	{
    		diff[2]+=Math.abs(German[i]-freq[i/*+1*/]);
    	}
    	System.out.println("the difference between the language and German is "+diff[2]+"%");
    	
    	for(int i=0;i<10;i++)
    	{
    		diff[3]+=Math.abs(Swedish[i]-freq[i/*+1*/]);
    	}
    	System.out.println("the difference between the language and Swedish is "+diff[3]+"%");
    	
    	for(int i=0;i<10;i++)
    	{
    		diff[4]+=Math.abs(Russian[i]-freq[i/*+1*/]);
    	}
    	System.out.println("the difference between the language and Russian is "+diff[4]+"%");
    	
    	for(int i=0;i<10;i++)
    	{
    		diff[5]+=Math.abs(French[i]-freq[i/*+1*/]);
    		
    	}
    	System.out.println("the difference between the language and French is "+diff[5]+"%");
    	
    	for(int i=0;i<10;i++)
    	{
    		diff[6]+=Math.abs(Danish[i]-freq[i/*+1*/]);
    		
    	}
    	
    	System.out.println("the difference between the language and Danish is "+diff[6]+"%");
    	
    	for(int i=0;i<10;i++)
    	{
    		diff[7]+=Math.abs(Icelandic[i]-freq[i/*+1*/]);
    		
    	}
    	System.out.println("the difference between the language and Icelandic is "+diff[7]+"%");
    	
    	for(int i=0;i<10;i++)
    	{
    		diff[8]+=Math.abs(Polish[i]-freq[i/*+1*/]);
    		
    	}
    	System.out.println("the difference between the language and Polish is "+diff[8]+"%");
    	
    	String ans = new String("");
    	//figure out the smallest diff and output corresponding language
    	double currdiff=0.0;
    	int i=0;
    	currdiff=diff[0];
		ans = lang[0];
    	
		for(i = 1; i<diff.length;i++)
    	{
    		if(currdiff>diff[i])
    		{
    			currdiff=diff[i];
	    		ans = lang[i];
	    	}
    	}
    	return ans;    	//return language
	}
}
