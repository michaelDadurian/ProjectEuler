import java.util.*;

class ProjectEuler{
	private static final long NUMBER = 600851475143L;
	
	
	public static void main (String[] args){
		long largestPrime = largestPrimeFactor(NUMBER);
		System.out.println("Largest prime factor of 600851475143: " + largestPrime);
		
		System.out.println("Largest palindrome: " + largestPalindrome());
		
		System.out.println("Smallest Multiple: " + smallestMultiple());
		
		System.out.println("Sum square difference: " + sumSquareDifference());
		
		System.out.println("10001 prime: " + countPrimes(10001));
		
		System.out.println("Product in series: " + productInSeries());
		
		System.out.println("Pythagorean triplet: " + pythagoreanTriplet());
	}
	
	
	
	
	/*
	Problem 3:
	The prime factors of 13195 are 5, 7, 13 and 29.

	What is the largest prime factor of the number 600851475143 ?
	*/
	public static long largestPrimeFactor(long n){
		long max = -1;
		for (int i = 2; i * i <= n; i++){
			if (n == 1){
				break;
			}
			if (n % i != 0){
				continue;
			}
			max = i;
			while (n % i == 0){
				n /= i;
			}
		}
		
		return n == 1 ? max : n;
	}
	
	/*
	Problem 4:
	A palindromic number reads the same both ways. 
	The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 × 99.

	Find the largest palindrome made from the product of two 3-digit numbers.
	*/
	public static long largestPalindrome(){
		int largest = -1;
		for (int i = 1; i <= 999; i++){
			for (int j = 1; j <= 999; j++){
				int product = j * i;
				if (checkPalindrome(product)){
					if (product > largest)
						largest = product;
				}
			}
		}
		
		return largest;
	}
	
	public static boolean checkPalindrome(int n){
		String intStr = String.valueOf(n);
		return intStr.equals(new StringBuilder(intStr).reverse().toString());
	}
	
	/*
	Problem 5:
	2520 is the smallest number that can be divided by 
	each of the numbers from 1 to 10 without any remainder.

	What is the smallest positive number that is evenly divisible by 
	all of the numbers from 1 to 20?
	
	DOESNT WORK
	
	*/
	
	public static int smallestMultiple(){
		int smallestMultiple = 20;
		int fullMatch = 20;
		
		while (fullMatch != 0){
			fullMatch = 20;
			smallestMultiple *= 2;
			
			for(int i = 20; i > 0; i--){
				if (smallestMultiple % i != 0)
					break;
				fullMatch--;
			}
		}
		
		
		
		
		return smallestMultiple;
	}
	
	/*
	Problem 6:
	Find the difference between the sum of the squares of the first one hundred natural numbers and the square of the sum.
	
	*/
	
	public static double sumSquareDifference(){
		double sumOfSquares = 0;
		double squareOfSum = 0;
		
		//1^2 + 2^2 + ... + 10^2 = 385
		//(1 + 2 + ... + 10)2 = 552 = 3025
		for (int i = 1; i <= 100; i++){
			squareOfSum += i;
			sumOfSquares += Math.pow(i, 2);
		}
		
		squareOfSum = Math.pow(squareOfSum, 2);
		
		return squareOfSum - sumOfSquares;
		
		
	}
	
	/*
	Problem 7:
	By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that the 6th prime is 13.

	What is the 10 001st prime number?
	
	*/
	public static boolean isPrime(int num){
		
		//if number is even or not 2, not prime
		if (num % 2 == 0 && num != 2){
			return false;
		}
		
		//skip even numbers
		for (int i = 3; i <= (num^(1/2)+1); i++){
			if (num % i == 0)
				return false;
		}
		
		return true;
	}
	
	public static int countPrimes(int num){
		int numPrimes = 0;
		int i = 2;
		
		while (numPrimes != num){
			if (isPrime(i)){
				numPrimes++;
				i++;
			}else{
				i++;
			}
		}
		
		return i - 1;
	}
	
	/*
	Problem 8:
	Find the thirteen adjacent digits in the 1000-digit number that have the greatest product. What is the value of this product?
	
	*/
	
	public static long productInSeries(){
		String number = "73167176531330624919225119674426574742355349194934"
						+ "96983520312774506326239578318016984801869478851843"
						+ "85861560789112949495459501737958331952853208805511"
						+ "12540698747158523863050715693290963295227443043557"
						+ "66896648950445244523161731856403098711121722383113"
						+ "62229893423380308135336276614282806444486645238749"
						+ "30358907296290491560440772390713810515859307960866"
						+ "70172427121883998797908792274921901699720888093776"
						+ "65727333001053367881220235421809751254540594752243"
						+ "52584907711670556013604839586446706324415722155397"
						+ "53697817977846174064955149290862569321978468622482"
						+ "83972241375657056057490261407972968652414535100474"
						+ "82166370484403199890008895243450658541227588666881"
						+ "16427171479924442928230863465674813919123162824586"
						+ "17866458359124566529476545682848912883142607690042"
						+ "24219022671055626321111109370544217506941658960408"
						+ "07198403850962455444362981230987879927244284909188"
						+ "84580156166097919133875499200524063689912560717606"
						+ "05886116467109405077541002256983155200055935729725"
						+ "71636269561882670428252483600823257530420752963450";
		int current = 0;
		int counter = 1;
		long product = 1;
		long largestProduct = -1;
		ArrayList<Integer> adjacentDigits = new ArrayList<>();
						
		for (int i = 0; i < number.length(); i++){
			String digit = "" + number.charAt(i);
			current = Integer.parseInt(digit);
			adjacentDigits.add(current);
			counter++;
			if (counter == 13){
				for (int j : adjacentDigits){
					product = j * product;
				}
				if (product > largestProduct)
					largestProduct = product;
				
				adjacentDigits.remove(0);
				product = 1;
			}
			counter = adjacentDigits.size();
		}
		
		return largestProduct;
										

	}
	
	/*
	Problem 9:
	A Pythagorean triplet is a set of three natural numbers, a < b < c, for which,

	a^2 + b^2 = c^2
	For example, 3^2 + 4^2 = 9 + 16 = 25 = 5^2.

	There exists exactly one Pythagorean triplet for which a + b + c = 1000.
	Find the product abc.
	
	*/
	
	public static int pythagoreanTriplet(){
		int sum = 1000;
		int largest = 998;
		int x = 0;
		
		for (int i = 1; i <= largest; i++){
			for (int j = i + 1; j <= (sum - i) / 2; j++){
				x = sum - i - j;
				if(check(i,j,x))
					return i * j * x;
			}
		}
		
		return 0;
		
		
	}
	
	public static boolean check(int a, int b, int c){
		if ((a < b) && (b < c) && (a*a + b*b == c*c))
			return true;
		return false;
	}
	
	
	
	
}