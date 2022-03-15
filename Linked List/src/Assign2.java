import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

/*
 * Author: Usman Ibrahim 30054920
 
 */


public class Assign2 {

	/*
	 * Insertion sort implementation for strings
	 */
	public static class SortFucntion{
		
		
//		public static void main(String[] args) {
//			// TODO Auto-generated method stub
//
//			String str = "123efewgreg";
//			char [] characters = new char[str.length()];
//			
//			for (int i =0; i < str.length(); i++) {
//				characters[i]=str.charAt(i);
//			}
//			
//			System.out.println(charSort(characters));
//		}
//		Char sort works
		
		
		/*a function used to sort the character array
		 * takes char [] input as an argument
		 * 
		 * Source: https://www.geeksforgeeks.org/sort-a-string-in-java-2-different-ways/
		 * 		:	Stack Overflow, GeeksforGeeks
		 * 
		 */
		
		public static void charSort(char [] input) {
			for (int i = 1,j; i < input.length; i++) {
				char temp = input[i];
				
				for(j=i; (j>0)&&(temp<input[j-1]); j--)
					input[j] = input[j-1];
				input[j] = temp;
			}
		}
		
		/* By Converting this method into a nested while loop it is deduced that the Big-O
		 * notation is O(k^2)
		 */
		
		
		/*
		 * Quick Sort method Implementation from Class from class and tutorial
		 * Stack Overflow and Discussion with other students
		 * https://www.geeksforgeeks.org/quicksort-on-singly-linked-list/
		 * 
		 * Passed in the list as an argument to be sorted
		 * Made use of some linked list attrubutes as shown in tutorial
		 */
		
		public static void quickSort(int lo, int hi, AnagramList[] input) {
			int first = lo, last = hi;
			String pivot = input[(lo+hi)/2].getHead().data;
			
			while(first<=last){
				
				while((input[first].getHead().data).compareTo(pivot) < 0){
					first++;
				}
				while((input[last].getHead().data).compareTo(pivot) > 0){
					last--;
				}
				// swap
				if(first<=last){
					
					swap(input, first,last);
					first++;
					last--;
				}
			}
			// recursive method call
			if(lo < last)
				quickSort(lo, last, input);
			if(first < hi)
				quickSort(first, hi, input);
		}
		
		/*
		 * 
		 */
		
		private static void swap (AnagramList[] input, int index1, int index2){
	        
	        AnagramList temp = input[index1];
	        input[index1] = input[index2];
	        input[index2] = temp;
	    }
		
		
		
		/*
		 * Implemented from Tutorial and Lecture notes(Textbook)
		 * Other source: https://www.geeksforgeeks.org/insertion-sort-for-singly-linked-list/
		 * 
		 *
		 */
		
		public static void insertionSort(AnagramList input) {
			for(int i=1, j; i<input.size(); i++){
				String temp = input.getData(i);
				for(j=i; (j>0)&&((temp.compareTo(input.getData(j-1)))<0); j--){
					String tempo = input.getData(j-1);
					input.set(tempo,j);
				}
				input.set(temp,j);
			}
		}
		
	}
	
	/*
	 * Implemented from Tutorial and Class
	 * Other source: https://www.geeksforgeeks.org/quicksort-on-singly-linked-list/ 
	 */
	
	public static class AnagramList {

		
		public class Node{
			String data;
			Node next = null;
		}
		
		private Node head;
		
		private int size;
		
		public AnagramList() {
			head = null;
			size = 0;
		}
		public AnagramList(String text) {
			addFront(text);
		}
		
		public int size() {return size;}
		
		public Node getHead() {return head;}
		
		public void addFront(String text) {
			Node temp = new Node();
			temp.data = text;
			temp.next = head;
			head = temp;
			size++;
		}
		
		
		/*
		 * Implemented for Tutorial 
		 * 
		 * Methods created to get the item in the list
		 * and to set items in the list
		 * 
		 * getData method
		 * getting data checks if the list is empty and if it is empty, the program ends
		 * But after that, it progress loop through the list and get data that is in temp
		 * 
		 * 
		 * set Method
		 * Takes two argument the string and the size of the linkList
		 * First checks to see the size of the link list
		 * -starts by making a temporary node which is pointing to the head 
		 * -loops through the list and for each
		 */
		
		
		
		public String getData(int listSize) {
			if((listSize<0)||(listSize>=size)) {
				System.err.println("Program will not execute due to invalid access to the List");
				System.exit(0);
			}
			Node temp = head;
			for(int i = 0; i<listSize; i++)
				temp = temp.next;
			return temp.data;
		}
		 
		public void set(String text, int listSize) {
			if((listSize<0)||(listSize>=size)) {
				System.err.println("Program will not execute due to invalid access to the List ");
				System.exit(0);
			}
			Node temp = head;
			for(int i = 0; i<listSize; i++)
				temp = temp.next;
			temp.data = text;
		}
			
	}

	
	
	public static class Anagram {
		
		// GeeksforGeeks and Stack Overflow, Studend-Jacob Adeyomo 
		
		
		AnagramList [] anagramMat;
		
		String fileIN;
		
		String fileOUT;
		
		PrintWriter cursor;
		
		int sizeofArray, lines;
		
		public void readingFile() throws IOException{
			BufferedReader buffer = new BufferedReader(new FileReader(fileIN));
			String data;
			sizeofArray = 0;
			while((data = buffer.readLine()) != null) {
				if(!isAnagram(data)) {
					anagramMat[sizeofArray] = new AnagramList(data);
					sizeofArray++;
				}
			}
			buffer.close();
		}
		
		/*
		 * Big-O notation after all the operation is O(n) but with the implementation of isAnagram() 
		 * makes the whole program O(k^2n^2)
		 */
		
		public void numberofLines() throws IOException {
			BufferedReader reader = new BufferedReader(new FileReader(fileIN));
			lines = 0;
			while (reader.readLine() != null) {
			    lines++;
			}
			reader.close();
		}
		
		
		/*
		 * Multiple soucre of Textbook, Microsoft website, GeekforGeek, Stack Overflow
		 */
		public boolean isAnagram(String text) {										// 	Number of Operations
			//changes all inputs to character array
			char[] inputChar = text.toCharArray();									// 		2ops
			SortFucntion.charSort(inputChar);										// 		k^2
			for(int i = 0; i<sizeofArray; i++) {									//		2ops
																					//		n
																					//		n-1
				char[] nowChar = anagramMat[i].getHead().data.toCharArray();		//		4 ops
				SortFucntion.charSort(nowChar);										// 		k^2(n-1)
				if(Arrays.equals(inputChar, nowChar)) {								//		n-1
					anagramMat[i].addFront(text);									//		2ops and (n-1)
					return true;													// 		1 op
				}
			}
			return false;															//		1 op
		}
		
		/*The  Big-O notation for this method is O(k^2n)
		 * 
		 */
		
		public void printToFile() {
			try {
				cursor.print("\nSorted List of Anagrams from the input.\n");
				for(int i = 0; anagramMat[i] != null; i++) {
					for(int j = 0; j < anagramMat[i].size(); j++) {
						cursor.print(anagramMat[i].getData(j) + " ");
					}
					cursor.println();
				}
			}
			catch(Exception e) {
				e.printStackTrace();
				System.out.println("File does not exist.");
			}
		}
	}
	
	double startTime, endTime;
	
	public static void main(String[] args) throws IOException {
		
		Anagram anagramTest = new Anagram();
		
		anagramTest.fileIN	= args[0];
		anagramTest.fileOUT= args[1];
		anagramTest.cursor = new PrintWriter(anagramTest.fileOUT);
		
		double startTime, endTime;
		
		startTime = System.nanoTime();
		System.out.println("Executing Program.");
		anagramTest.cursor.println("Executing Program.");
		
		anagramTest.numberofLines();
		anagramTest.anagramMat = new AnagramList[anagramTest.lines];
		anagramTest.readingFile();
		
		for (int i = 0; i < anagramTest.sizeofArray;i++)
			SortFucntion.insertionSort(anagramTest.anagramMat[i]);
		
		SortFucntion.quickSort(0,anagramTest.sizeofArray-1,anagramTest.anagramMat);
	
		anagramTest.printToFile();
		
		endTime = System.nanoTime();		
		anagramTest.cursor.print("Checking if the inputs of "+anagramTest.lines+" lines are anagrams took "+(endTime-startTime)/1000000000.0+" seconds.\n");
		System.out.println("The program has ended.");
		anagramTest.cursor.println("The program has ended.");
		anagramTest.cursor.close();
		
	}
}
