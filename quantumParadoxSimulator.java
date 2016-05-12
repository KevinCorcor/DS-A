package lab9;

public class quantumParadoxSimulator 
{
	public static void main (String args[])
	{
		int previous=0;
		int count=0;
		int  []rollstaken= new int[900000];
		//roll a dice
		for(int i=0;i<900000;i++)
		{
			int dice=roll();
			//System.out.println(dice);
			if(dice==6)
			{
				rollstaken[count]=i-previous;
				previous=i;
				count++;
			}
		}
		 previous=0;
		int count2=0;
		int  []rollstaken2= new int[900000];
		//roll a dice
		for(int i=0;i<900000;i++)
		{
			int dice=rollsnap();
			//System.out.println(dice);
			if(dice==7)
			{
				rollstaken2[count2]=i-previous;
				previous=i;
				count2++;
			}
		}
		 previous=0;
			int count3=0;
			int  []rollstaken3= new int[900000];
			//roll a dice
			for(int i=0;i<900000;i++)
			{
				int dice=rollsnapsnee();
				//System.out.println(dice);
				if(dice==8)
				{
					rollstaken3[count3]=i-previous;
					previous=i;
					count3++;
				}
			}
		double result1= getmean(rollstaken,count);
		double result2= getmean(rollstaken2,count2);
		double result3= getmean(rollstaken3,count3);
		System.out.println("  "+result1+" vs "+result2+" vs "+result3+" a difference of: ");
				
	}
	public static double getmean(int[]x,int count)
    {
		int rows=count;
    	double sum=0;
    	double mean=0;
    	for(int r= 1 ; r<count;r++)
    	{
    		sum+= x[r];
    		
    	}
    	mean=((sum)/(count));
    	return mean;
    }
	public static int roll()
	{
		
		int res = (int)(Math.random()*6) + 1;
		return res;
	}
	public static int rollsnap()
	{
		int res = (int)(Math.random()*3) + 1;
		int res2 = (int)(Math.random()*4) + 1;
		
		return (res+res2);
	}
	public static int rollsnapsnee()
	{
		int res = (int)(Math.random()*3) + 1;
		int res2 = (int)(Math.random()*3) + 1;
		int res3 = (int)(Math.random()*2) + 1;
		
		return (res+res2+res3);
	}
	
}

