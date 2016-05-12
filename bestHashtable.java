package lab7;

import java.io.*;
import java.util.*;
import java.math.*;

public class bestHashtable 
{
	static int size =240607;//this is the size of the hash table - a prime number is best
	static String[] hashTable = new String[size];//create the hash table
	static String[] array = new String[216555]; //make sure your String array is big enough to hold all the data

	public static void main (String[] args) throws IOException 
	{
		File testFile = new File("H://MU/Computer Science/cs211 workspace/current lab trials/dictionary.txt");     //this is where the file to be sorted is loaded from
		

		//fill the hash table so that every slot contains a space
		getContents(testFile);
		//loads up the file
		System.out.println("Which type of open addressing would you like to use?");
		System.out.println("1) Linear Probing");
		System.out.println("2) Quadratic Probing");
		System.out.println("3) Double Hashing");
		Scanner in = new Scanner(System.in);
		int strategy = in.nextInt();	//the user enters a number for the hashing strategy they want to use
	
		switch(strategy){
		case 1: 
			fillLinearProbing();
			break;
		case 2: 
			fillQuadraticProbing();
			break;
		case 3: 
			fillDoubleHash();
			break;
		}     
		in.nextLine();
		System.out.print("\nEnter a word to find: ");
		String word = in.nextLine();
		while(!word.equals("quit")){
			find(word, strategy);
			System.out.print("\nEnter a word to find: ");
			word = in.nextLine();
			//the user is asked to enter words to search for until they enter the word 'quit'
		}
	}

	public static void find(String word, int strategy)//this method takes in a word to look for and the strategy by which it has been placed in the hash table
	{		
		int probes = 1;
		int index = getHashKey(word);
		//calculate the hash key for the word
		System.out.println();
		while(hashTable[index]!=null&&!hashTable[index].equals(word))
		{
			System.out.println("Checking slot "+index+"...collision with "+hashTable[index]);
			//as long as you do not stumble across either the word or a blank keep searching
			if(strategy==1)
			{
				//depending on the strategy go up in linear jumps, quadratic jumps or the double hash jump
				index++;
				probes++;
				index=index%size;
				//always mod the index size so it doesn't go out of bounds
			}else if(strategy==2)
			{
				index=index+(probes*probes);
				probes++;
				index=index%size;
			}else if(strategy==3)
			{
				index=index+getDoubleHashKey(word);
				probes++;
				index=index%size;
			}
		}
		if(hashTable[index]==null)
		{
			System.out.println("NOT IN HASHTABLE");//if you've found a space then the word cannot be in the hash table
			
		}else
		{
			System.out.println("The word "+word+" was found in slot "+index+" of the hashtable");
		}     
		System.out.println("Number of hash table probes: "+probes);//print out the total number of attempts to find the correct slot
		
	}
	static int primes [] ={2,   3,  5,  7, 11, 13, 17, 19, 23,  29, 31,37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83,  89, 97,101};
	static char alpha [] ={'e','s','i','a','r','n','t','o','l','c','d','u','p','g','m','h','b','y','f','k','v','w','z','x','j','q'}; 


	public static int getHashKey(String word)
	{
		//this is the primary hash key function - it should return a number which is a slot in the hash table
		//for words, a good strategy is to raise each character to successive powers of the alphabet size
		//assume that the alphabet is ASCII characters - a total of 256 possibilities
		//each successive character value should be raised to successive powers of 256
		//the whole thing is added together and then moduloed to create a hash table index

		/*
    	 int hashkey = 0;
 		 int j = 0;
         for(char chr : word.toCharArray()) //cycle through a char array
         {
        	 int asc = (int) chr;
        	 hashkey += asc * modPow(127, j, size);//83623word.length()-1-
        	 j++;
         }

        hashkey = hashkey % size;
         return hashkey;
     }*/
		int hashkey = 1;
		for(int i =0; i<word.length();i++)
		{
			for(int j = 0;j<26;j++)
			{
				if((word.charAt(i)==alpha[j]))
				{

					//hashkey= hashkey+primes[j]*(int)Math.pow((double)2,(double)word.length()-i);
					hashkey += primes[j]*modPow(107,i,size);//83036//0.9 337974hashkey += primes[j]*modPow(101,i,size);
					//hashkey= hashkey*primes[j];//((int) Math.pow((double) primes[j], (double) i));
					break;
				}
			}

		}
		//

		return modPow(hashkey,107,size);//338324

	}
	public static int getDoubleHashKey(String word){
		//this method should be different to the primary hash function
		//it should return a different number for words which generated the same primary hash key value
		//for example, you could just add up all of the letters in the word


		int i = 0;
		for(char c : word.toCharArray()) 
		{
			int asc = (int) c;
			i =i+ asc;
		}
		return i % size;
	}

	public static void fillLinearProbing()
	{
		int totalcollisions=0;
		//this variable stores the total number of collisions that have occurred for every word
		for(int i=0; i<array.length;i++)
		{
			//go through all words
			int collisions=0;
			int index = getHashKey(array[i]);
			//generate a hash key
			while(hashTable[index]!=null)
			{
				//if that slot is already filled move onto the next slot and increment the collisions
				collisions++;
				index++;
				index=index%size;
				//make sure you don't go off the edge of the hash table
			}
			hashTable[index]=array[i];
			if(i%100==0)
			{
				System.out.println(array[i] + " was placed in slot "+index+" of the hash table after "+collisions+" collisions");
				System.out.println("the load factor is "+216555.0/(double)size);
			}
			totalcollisions+=collisions;
			//print out the information for the last 1,000 words only, otherwise it takes quite long and gets annoying
		}
		System.out.println("The total number of collisions was "+ totalcollisions);
		System.out.println("the load factor is "+216555/size);
	}

	public static void fillQuadraticProbing()
	{
		int totalcollisions=0;
		for(int i=0; i<array.length;i++)
		{
			int collisions=0;
			int index = getHashKey(array[i]);
			int queries=1;
			while(hashTable[index]!=null)
			{
				collisions++;
				index=index+(queries*queries);
				index=index%size;
				queries++;
			}
			hashTable[index]=array[i];
			if(i%100==0)
			{
				System.out.println(array[i] + " was placed in slot "+index+" of the hash table after "+collisions+" collisions");
			} 
			totalcollisions+=collisions;
		}
		System.out.println("The total number of collisions was "+ totalcollisions);
		System.out.println("the load factor is "+216555.0/(double)size);
	}

	public static void fillDoubleHash()
	{
		int totalcollisions=0;
		for(int i=0; i<array.length;i++)
		{
			int collisions=0;
			int index = getHashKey(array[i]);
			int doubleHash = getDoubleHashKey(array[i]);
			while(hashTable[index]!=null)
			{
				collisions++;
				index=index+doubleHash;
				index=index%size;
			}
			hashTable[index]=array[i];
			// if(i%100==0){
			System.out.println(array[i] + " was placed in slot "+index+" of the hash table after "+collisions+" collisions");
			//  }
			totalcollisions+=collisions;
		}
		System.out.println("The total number of collisions was "+ totalcollisions);
		System.out.println("the load factor is "+216555.0/(double)size);
	}

	public static int modPow(int number, int power, int modulus)
	{
		//raises a number to a power with the given modulus
		//when raising a number to a power, the number quickly becomes too large to handle
		//you need to multiply numbers in such a way that the result is consistently moduloed to keep it in the range
		//however you want the algorithm to work quickly - having a multiplication loop would result in an O(n) algorithm!
		//the trick is to use recursion - keep breaking the problem down into smaller pieces and use the modMult method to join them back together         
		if(power==0)
			return 1;
		else if (power%2==0)
		{
			int halfpower=modPow(number, power/2, modulus);
			return modMult(halfpower,halfpower,modulus);
		}else
		{
			int halfpower=modPow(number, power/2, modulus);
			int firstbit = modMult(halfpower,halfpower,modulus);
			return modMult(firstbit,number,modulus);
		}
	}

	public static int modMult(int first, int second, int modulus)
	{
		//multiplies the first number by the second number with the given modulus
		//a long can have a maximum of 19 digits. Therefore, if you're multiplying two ten digits numbers the usual way, things will go wrong
		//you need to multiply numbers in such a way that the result is consistently moduloed to keep it in the range
		//however you want the algorithm to work quickly - having an addition loop would result in an O(n) algorithm!
		//the trick is to use recursion - keep breaking down the multiplication into smaller pieces and mod each of the pieces individually  
		if(second==0)
			return 0;
		else if (second%2==0) 
		{
			int half=modMult(first, second/2, modulus);
			return (half+half)%modulus;
		}else
		{
			int half=modMult(first, second/2, modulus);
			return (half+half+first)%modulus;
		}
	}


	/**
	 * Fetch the entire contents of a text file, and return it in a String.
	 * This style of implementation does not throw Exceptions to the caller.
	 *
	 * @param aFile is a file which already exists and can be read.
	 */
	public static String getContents(File aFile)
	{
		//...checks on aFile are elided
		StringBuffer contents = new StringBuffer();

		//declared here only to make visible to finally clause
		BufferedReader input = null;
		try {
			//use buffering, reading one line at a time
			//FileReader always assumes default encoding is OK!
			input = new BufferedReader( new FileReader(aFile) );
			String line = null; //not declared within while loop
			/*
			 * readLine is a bit quirky :
			 * it returns the content of a line MINUS the newline.
			 * it returns null only for the END of the stream.
			 * it returns an empty String if two newlines appear in a row.
			 */
			int i = 0;
			while (( line = input.readLine()) != null)
			{
				array[i]=line;
				i++;
				contents.append(System.getProperty("line.separator"));
			}
		}
		catch (FileNotFoundException ex)
		{
			ex.printStackTrace();
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
		finally {
			try {
				if (input!= null)
				{
					//flush and close both "input" and its underlying FileReader
					input.close();
				}
			}
			catch (IOException ex)
			{
				ex.printStackTrace();
			}
		}
		return contents.toString();
	}


	public static void setContents(File aFile)
			throws FileNotFoundException, IOException 
	{

		//declared here only to make visible to finally clause; generic reference
		Writer output = null;
		try {
			//use buffering
			//FileWriter always assumes default encoding is OK!
			output = new BufferedWriter( new FileWriter(aFile) );
			int i=0;
			while(array[i]!=null)
			{
				output.write( array[i] );
				output.write(System.getProperty("line.separator"));
				i++;
			}
		}
		finally 
		{
			//flush and close both "output" and its underlying FileWriter
			if (output != null) output.close();
		}
	}


	private static final long FNV_64_INIT = 0xcbf29ce484222325L;
	private static final long FNV_64_PRIME = 0x100000001b3L;

	private static final int FNV_32_INIT = 0x811c9dc5;
	private static final int FNV_32_PRIME = 0x01000193;

	//  public FNVHash() {}

	public static int hash32(final byte[] k) 
	{
		int rv = FNV_32_INIT;
		final int len = k.length;
		for(int i = 0; i < len; i++) 
		{
			rv ^= k[i];
			rv *= FNV_32_PRIME;
		}
		return rv;
	}

	public static long hash64(final byte[] k) 
	{
		long rv = FNV_64_INIT;
		final int len = k.length;
		for(int i = 0; i < len; i++) 
		{
			rv ^= k[i];
			rv *= FNV_64_PRIME;
		}
		return rv;
	}

	public static int hash32(String k) 
	{
		int rv = FNV_32_INIT;
		final int len = k.length();
		for(int i = 0; i < len; i++) 
		{
			rv ^= k.charAt(i);
			rv *= FNV_32_PRIME;
		}
		return rv;
	}

	public static long hash64(final String k) 
	{
		long rv = FNV_64_INIT;
		final int len = k.length();
		for(int i = 0; i < len; i++) 
		{
			rv ^= k.charAt(i);
			rv *= FNV_64_PRIME;
		}
		return rv;
	}
}