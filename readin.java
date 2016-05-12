package projectComplete;

public class readin
{
	static FileIO io = new FileIO();
	
	static String[] original = io.load("H://Downloads/stockdata.txt");
static	int numrows=original.length;
static	int numcols=original[0].split("\t").length;
	
	public static  double[][] getData()
	{
		double[][] data = new double[numrows][numcols];
		//System.out.print(numrows+"rows now columns "+numcols);
		for(int i=1;i<numrows;i++)
		{
			for(int j=1;j<numcols;j++)
			{
				//data[i][j]=(double)original[i].split("\t")[j];
				data[i][j]=Double.parseDouble(original[i].split("\t")[j]);
			}
		}
		return data;
	}
	
	public static String[] getDates()
	{
		String[] dates = new String[numrows];
		for(int i=0; i<numrows;i++)
		{
			dates[i]= original[i].split("\t")[0];
			//System.out.println(dates[i]);
		}
		return dates;
	}
	
	public static String[] getNames()
	{
		String names [] =original[0].split("\t");
		return names;
	}
}
