package projectComplete;
import java.util.*;
public class Stats 
{
	static final double maxinvest=200000.0;
	static double[][] data;
	static int rows;
	static int cols;
//COULDN'T GET THIS CONSTRUCTOR TO WORK SO JUST IGNORE IT INCLUDING SOME OF THE STATIC DECALARATIONS ABOVE
    public Stats(double[][] data) 
    {
       data = data;
       rows = data.length;
       cols =data[0].length;
    }   

    /**
     * takes in all the data and outputs an array containing the mean change% 
     * of every company with corresponding indexes
     * @param  double [][]data of change rates
     * @return     double[] means
   
     */
    public static double[] getMeans(double [][] data)
    {
    	int rows = data.length;
    	int cols =data[0].length;
        
    	double means[] = new double[cols];
        double sums[]= new double[cols];
        for(int c =1;c<cols;c++)
        {
        	for(int r= 1 ; r<rows;r++)
        	{
        		sums[c]+= data[r][c];
        	}
        	means[c]=((sums[c])/(rows-1));
        	
        }
        return means;
    }
    
    /**
     *takes in all our change % per company and
     * calculates the variance of each company
     * storing it in a corresponding vars[]
     *
     *
     * @param double[][]data, double[] means 
     * @return double[]vars     
   
     */
    public static double[] getVariance(double[][] data,double[] means)
    {
    	int rows = data.length;
    	int cols =data[0].length;
        double temp[] = new double[cols];
        double vars[] = new double[cols];
        
        for(int c=1;c<cols;c++)
        {
        	for(int r=1;r<rows;r++)
        	{
        		temp[c] += (means[c]-data[r][c])*(means[c]-data[r][c]);
        	}
        	vars[c]=((temp[c])/(rows-1));
        }
        
        return vars;
    }
    /**
    *calculates the variance for each company %change   *
    * @param  double [][]data,double[]vars
    * @return sds[]
  
    */
    public static double[] getStdDev(double[][] data, double[] vars)
    {
    	int rows = data.length;
    	int cols =data[0].length;
    	double sds[] = new double[cols];
    	
        for(int c=1;c<cols;c++)
        {
        	sds[c] = Math.sqrt(vars[c]);
        }
        return sds;
    }

    /**
    *calculates the x[] we need to later calculate our volatility
    * @param  double[][] data,int [] invests,double[]euros
    * @return x[] 
  
    */
    public static double[] getSumProd(double[][] data,int [] invests,double[]euros)
    {
    	int rows = data.length;
    	int cols =data[0].length;
    	
    	double x[] =new double[rows];
    		double totalinvest=0;
    		
    	for(int r = 1; r<rows;r++)
    	{	double temp=0;
    		totalinvest=0;
    		for(int c=1; c<cols;c++)
   			{
   				if(invests[c]!=0)
   				{
   					temp+=invests[c]*euros[c]*data[r][c];
   					totalinvest+=invests[c]*euros[c];    			
   				}
    		}
			x[r]=temp/totalinvest;
		}
    
    	return x;
    }
    
    
    /**
     *calculate the mean of x
     * @param x[]
     * @return mean an double
     */
    public static double getmean(double[]x)
    {
    	double sum=0;
    	double mean=0;
    	for(int r= 1 ; r<rows;r++)
    	{
    		sum+= x[r];
    	}
    	mean=((sum)/(rows-1));
    	return mean;
    }
    /**
     *calculates the variance of x[]
     * @param  double[] x
     * @return var a double 
     */
    public static double getvar(double[]x)
    {
    	int rows = x.length;
    	double temp=0;
    	double var=0;
    	double mean=getmean(x);
    	for(int r=1;r<rows;r++)
    	{
    		temp += (mean-x[r])*(mean-x[r]);
    	}
    	var=((temp)/(rows-1));
    	return var;
    	
    }
    /**
     *calculates the volatility of x[]
     * @param  double x[]
     * @return vol
     */
    public static double getvol(double[] x)
    {
    	double vol=0;
    	vol=Math.sqrt(getvar(x));
    	return vol;
    }
    /**
     *calculates how much we have invested
     * @param  double[] euro, double[] invests
     * @return double investtotal
     */
    public static double getcurrinvest(double[] euro,int[] invests)
    {
    	int cols = invests.length;
    	double currinvest=0;
    	for(int c = 1;c<cols;c++)
    	{
    		if(invests[c]!=0)
    		{
    			currinvest+=euro[c]*invests[c];
    		}		
    	}
    	return currinvest;
    }
    
    
    
    
    
    
}    

   /* public double median() 
    {
       Arrays.sort(data);

       if (data.length % 2 == 0) 
       {
          return (data[(data.length / 2) - 1] + data[data.length / 2]) / 2.0;
       } 
       else 
       {
          return data[data.length / 2];
       }
    }*/

