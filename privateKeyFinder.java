package lab6;
/*
 * Alice’s public key is (24852977, 2744, 8414508). 
 * You eavesdrop on the line and observe Bob sends her the cipher (15268076, 743675). 
 * Extract the message by any means.
 * */

import java.math.*;

public class privateKeyFinder 
{
	public static void main(String args[])
	{
		BigInteger a= new BigInteger("2744");//g
		BigInteger x= new BigInteger("2");
		//BigInteger y= new BigInteger("150001");
		BigInteger ad1= new BigInteger("1");
		BigInteger ad2= new BigInteger("-1");
		BigInteger p= new BigInteger("24852977");//mod
		BigInteger ans= new BigInteger("8414508");//result
		//int q=0;
		//int s=0;
		boolean found=false;
		
		//BigInteger bb[][] = new BigInteger[2][p];
		
		DoublyLinkedList blist = new DoublyLinkedList();
		DoublyLinkedList bblist = new DoublyLinkedList();
		
		for(BigInteger k= new BigInteger("0"), l= p;!found&&!k.equals(l);k=k.add(ad1) ,l=l.subtract(ad1))
		{	
			BigInteger temp= a.modPow(k,p);
			blist.insertLast(temp);
			//System.out.println(temp);
			
			BigInteger temp2= (ans.multiply(a.modPow((k.multiply(ad2)), p)).mod(p)) ;//modMult(ans,a.modPow((k.multiply(ad2)), p),p);/*(ans.multiply(a.modPow((k.multiply(ad2)), p)).mod(p)) ;*/ 
			bblist.insertLast(temp2);
		//	System.out.println(k+"|"+temp2);
			
				if(blist.findKey(temp2))
				{
					BigInteger i =blist.foundKey(temp2);
				//	BigInteger k =blist.foundKey(temp);
					System.out.println(temp+"with "+a+"^"+k+"mod"+p+"*************"+temp2+"with ("+ans+"*"+a+"^-"+i+"mod"+p+")mod"+p);
					found = true;
					BigInteger xx = i.add(k);
					System.out.println("so our private key seems to be "+(xx));
					
					BigInteger c1=new BigInteger("15268076");
					BigInteger c2=new BigInteger("743675");
					BigInteger exp = (p.subtract(ad1)).subtract(xx);
					BigInteger m = (c1.modPow(exp, p).multiply(c2)).mod(p);
					
					System.out.println("so our message seems to be "+m);
					
					break;
				}
				else if(bblist.findKey(temp))
				{
					BigInteger i =bblist.foundKey(temp);
					System.out.println(temp+"with "+a+"^"+k+"mod"+p+"*************"+temp2+"with ("+ans+"*"+a+"^-"+i+"mod"+p+")mod"+p);
					found = true;
					BigInteger xx = i.add(k);
					System.out.println("so our private key seems to be "+(xx));
					
					BigInteger c1=new BigInteger("15268076");
					BigInteger c2=new BigInteger("743675");
					BigInteger exp = (p.subtract(ad1)).subtract(xx);
					BigInteger m = (c1.modPow(exp, p).multiply(c2.mod(p)));
					System.out.println("so our message seems to be "+m);
					
					break;
				}
				/*System.out.println("other end");
				BigInteger templ= a.modPow(l,p);
				blist.insertLast(templ);
				System.out.println(templ);
				
				BigInteger temp2l= (ans.multiply(a.modPow((l.multiply(ad2)), p)).mod(p)) ;//modMult(ans,a.modPow((k.multiply(ad2)), p),p);/*(ans.multiply(a.modPow((k.multiply(ad2)), p)).mod(p)) ;*/ 
				/*bblist.insertLast(temp2l);
				System.out.println(l+"|"+temp2l);
				
					if(blist.findKey(temp2l))
					{
						BigInteger i =blist.foundKey(temp2l);
					//	BigInteger k =blist.foundKey(temp);
						System.out.println(templ+"with "+a+"^"+l+"mod"+p+"*************"+temp2l+"with ("+ans+"*"+a+"^-"+i+"mod"+p+")mod"+p);
						found = true;
						
						System.out.println("so our private key seem to be "+(i.add(l)));
						break;
					}
					else if(bblist.findKey(templ))
					{
						BigInteger i =bblist.foundKey(templ);
						System.out.println(templ+"with "+a+"^"+l+"mod"+p+"*************"+temp2l+"with ("+ans+"*"+a+"^-"+i+"mod"+p+")mod"+p);
						found = true;
						
						System.out.println("so our private key seem to be "+(i.add(l)));
						break;
					}
					*/
					
				
			
		}
		
		
				
		/*for(BigInteger t= new BigInteger("1539359");!found;t=t.add(ad1))
		{
			//for(BigInteger tt= new BigInteger("0");(tt.intValue()<p.intValue())&&!found;tt=tt.add(ad1))
			//{
			System.out.println(t+"| "+a.modPow(t,p));
				if(a.modPow(t,p).equals(ans))
				{
					found=true;
					
					x=t;
					System.out.println(x+"hiiiiii");
				}
			//}
				System.out.println(x);
		}*/
		
		
	}
	
	/*public static BigInteger modMult(BigInteger first, BigInteger second, BigInteger modulus)
	{
		//multiplies the first number by the second number with the given modulus
		//a  long  can  have  a  maximum  of  19  digits.  Therefore,  if  you're  multiplying 	two ten digits numbers the usual way, things will go wrong
		//you need to multiply numbers in such a way that the result is consistently moduloed to keep it in the range
		//however  you  want  the  algorithm  to  work  quickly 	-	having  an  addition  loop 	would result in an O(n) algorithm!
		//the trick is to use recursion	-keep breaking down the multiplication into smaller pieces and	mod each of the pieces individually
		
		BigInteger k= new BigInteger("2");
		BigInteger a= new BigInteger("0");
		if(second.equals(a))
		{
			BigInteger j= new BigInteger("0");
			return j;
		}
			
		else if ((second.mod(k)).equals("0"))
		{
			BigInteger half=modMult(first, second.divide(k), modulus);
			BigInteger half1 = half.add(half);
			return (half1).mod(modulus);
		}
		else
		{
			BigInteger half=modMult(first, second.divide(k), modulus);
			BigInteger half1 = half.add(half);
			return (half1.add(first)).mod(modulus);
		}
	}
	
	public static BigInteger modPow(BigInteger number, BigInteger power, BigInteger modulus)
	{
			//raises a number to a power with the given modulus
			//when raising a number to a power, the number quickly becomes too large to handle
			//you need to multiply numbers in such a way that the result is consistently moduloe to keep it in the range
			//however  you  want  the  algorithm  to  work  quickly -aving  a  multiplication loop would result in an O(n) algorithm!
			//the trick is to use recursion -keep breaking the problem down into smaller pieces and use the modMult method to 			join them back together
			
		BigInteger k= new BigInteger("2");
		if(power.equals("0"))
		{
			BigInteger j= new BigInteger("1");
			return j;
		}
		else if ((power.mod(k)).equals("0")) 
		{
			BigInteger halfpower=modPow(number, power.divide(k), modulus);
			return modMult(halfpower,halfpower,modulus);
		}
		else
		{
			BigInteger halfpower=modPow(number, power.divide(k), modulus);
			BigInteger firstbit = modMult(halfpower,halfpower,modulus);
			return modMult(firstbit,number,modulus);
		}
	}*/
}
