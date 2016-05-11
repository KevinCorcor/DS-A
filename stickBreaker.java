package lab1;
/* LAB1
 * Programming Exercise Grab a stick. 
 * Pick a random point on the stick and break it in two.
 * Take the longer piece.
 * Now pick a random point on the longer piece and break it in two, to make three pieces altogether.
 * What is the probability you can form a triangle? 
 */
import java.util.*;

public class stickBreaker 
{
	public static void main(String args[])
	{	
		//INITIALISATION
		double ordarray[] = new double[3];
		Random r = new Random();
		double trials = 1000000;
		double triangles = 0;
		for(int i=0; i<trials; i++)
		{
			double stick0 =1.0;//UNIFORM LENGTH
			double stick1 = r.nextDouble();//BETWEEN 0 AND 1
			//System.out.println(stick1);
			double stick2 = stick0-stick1;//1-THIS GIVES ME THWE OTHER HALF
			double stick3=0.0;
			//FIND BIGGEST OF TWO
			if(stick1>stick2)
			{
				double ratio = r.nextDouble();
			    stick3=ratio*stick1;
			    stick1=stick1-stick3;
			}
			else
			{
				double ratio = r.nextDouble();
				stick3=ratio*stick2;
				stick2=stick2-stick3;
			}
			
			//ORDER MY STICK
			ordarray[0]=stick1;
			ordarray[1]=stick2;
			ordarray[2]=stick3;
			Arrays.sort(ordarray);
			//CHECK
			if(ordarray[0]+ordarray[1]>ordarray[2])
			{
				triangles++;//INCREMENT
			}
		}
		
		System.out.println(triangles/trials);
		
	}
}
