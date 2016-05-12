package lab8;
import java.io.File;
public class drawdown
{
	public static void main(String[] args)	
	{
		FileIO io = new FileIO();
		String[] original = io.load("H://Downloads/StockData.txt");
		int numrows=original.length;
		int numcols=original[0].split("\t").length;
		double[][] array = new double[numrows][numcols];
		for(int i=1;i<numrows;i++)
		{ //load in the data
			for(int j=1;j<numcols;j++)
			{
				array[i][j]=Double.parseDouble(original[i].split("\t")[j]);
			}
		}
		double drawdown=0;  //can we find drawdown higher than this?
		String startdate="";
		String finishdate="";
		String company="";
		for(int j=1;j<numcols;j++)
		{ //repeat for all companies
			double current=100; //start current price at 100%
			double peak=100;    //start peak is 100%
			double trough=0;
			String localstartdate="";
			String localfinishdate="";
			String recorddate="";
			for(int i=numrows-1;i>0;i--)
			{
				//go through each day-data is backwards
				current=current+(current*(array[i][j]/100));
				//change the price for today
				if(current>peak)
				{   //if it's a record high update
					peak=current;
					recorddate=original[i].split("\t")[0];
					//keep track of the date
				}
				else if(1-current/peak>trough)
				{
					//otherwise, are we lower than everbefore below the current peak?
					trough=1-current/peak;  
					//keep track of this super low
					localstartdate=recorddate;
					localfinishdate=original[i].split("\t")[0];
				}
			}
			if(trough>drawdown)
			{    
				//now we've found the drawdown for this company-is it bigger than the other companies?
				drawdown=trough;
				startdate=localstartdate;
				finishdate=localfinishdate;
				company=original[0].split("\t")[j]; //remember the company
			}
		}   //print out the overall results
		System.out.println("The company with the highest drawdown was "+company+" which suffered a drawdown of"+String.format("%.1f",drawdown*100)+"% between the dates of "+startdate+" and "+finishdate);
	}
}