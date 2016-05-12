package lab3;
/*
 * The task is to complete the Huffman algorithm so that it takes in a sentence and outputs Huffman codes. 
 *  */
import java.util.*;

public class huffman
{
    public static void main(String[]args)
    {
       	int[]ASCIIarray = new int[256];

    	Scanner sc = new Scanner(System.in);

    	String word = new String("");

    	System.out.println("Please enter a String");

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
    	System.out.println("");

    	System.out.println("");

    	System.out.println("");

		PriorityQueue < Tree >  PQ = new PriorityQueue < Tree >() ;  //make a priority queue to hold the forest of trees

		int ultcounter=0;
		
		for(int j=0; j<ASCIIarray.length; j++)
		{
			if(ASCIIarray[j]!=0)
			{
				ultcounter++;			
			}

		}
		int position=0;
		int bigg=0;
		int count=0;
		
		
		for(int l=0; l<word.length();l++)
		{	
			bigg=0;
	          
			if(count<ultcounter)
			{
				for(int k = 0; k<ASCIIarray.length; k++)
				{ 
					if(ASCIIarray[k]!=0)
					{
						int currbig=ASCIIarray[k];
							
						if(currbig>bigg)
						{
							bigg=currbig;
							position=k;
							
							
							
						}
					}
				
				}
					System.out.println("'"+(char)position+"' appeared "+bigg+" times");
					Tree myTree= new Tree();

		            myTree.frequency=ASCIIarray[position];

		            myTree.root= new Node();

		            myTree.root.letter = (char)position;

		            PQ.add(myTree);
					ASCIIarray[position]=0;	
					count++;
					
					
			}
			position=0;
	          
		}

        while(PQ.size()>1)
        {
        	Tree firstTree = PQ.poll();

        	Tree secondTree = PQ.poll();

        	Tree comboTree = new Tree();

        	comboTree.frequency = firstTree.frequency + secondTree.frequency;

        	comboTree.root = new Node();

        	comboTree.root.leftChild = firstTree.root;

        	comboTree.root.rightChild = secondTree.root;

        	PQ.add(comboTree);

        }

		System.out.println("");

        Tree HuffmanTree = PQ.poll();   //one tree remains

        int totalLength=0;

        String theCode = new String("");

        for(int i=0; i<word.length();i++)
        {
        	theCode=HuffmanTree.getCode(word.charAt(i));

        	System.out.print(theCode+" ");
        	
        	totalLength+=theCode.length();
        }

        System.out.println("");

        int num1 = (totalLength);

        int num2 = (word.length()*7);

        double compression = (((double)num1/(double)num2)*100);



        System.out.println("");



        System.out.println("Compressed size is: " +  num1 + " bits / " +  num2  + " bits = " + (int)compression  + "%");
        sc.close();
    }
}

class Node
{
   public char letter;            //stores letter

   public Node leftChild;         // this node's left child

   public Node rightChild;        // this node's right child

}  // end class Node
class Tree implements Comparable<Tree>
{
	public Node root;             // first node of tree
 	public int frequency=0;



// -------------------------------------------------------------

	public Tree()
	{
   		root = null;

	}            // no nodes in tree yet

// -------------------------------------------------------------



	//the PriorityQueue needs to be able to somehow rank the objects in it

	//thus, the objects in the PriorityQueue must implement an interface called Comparable
	//the interface requires you to write a compareTo() method so here it is:
	public int compareTo(Tree object)
	{ //

	     if(frequency-object.frequency>0)
	     { //compare the cumulative frequencies of the tree
		         return 1;
		 }
	     else if(frequency-object.frequency<0)
	     {
	    	 return -1;   //return 1 or -1 depending on whether these frequencies are bigger or smaller

		 }
	     else
	     {
	    	 return 0;   //return 0 if they're the same
	     }

	}



// -------------------------------------------------------------



   String path;     //this variable will track the path to the letter we're looking for



	public String getCode(char letter)
	{
		path = new String("");

		inOrder(root, letter, path);

		return path;
	}

    public void inOrder(Node localRoot, char letter, String path)
    {
		if(localRoot != null)
		{
			if(localRoot.letter==letter)
			{
				this.path=path;
			}
			else
			{
				inOrder(localRoot.rightChild, letter, "1"+path);

				inOrder(localRoot.leftChild, letter, "0"+path);
			}

		}

    }
}



