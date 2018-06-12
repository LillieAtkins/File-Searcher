//I have neither given nor received any unauthorized aid on this assignment. --Lillie Atkins

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FileSearcher {
	public static BinarySearchTree<Word> BiTree = new BinarySearchTree<Word>();
	public static Scanner textreader, scan = new Scanner(System.in);
	public static String stop = "no"; //initialize stop so that it is not equal to q

/*
 * This method reads from command line the one directory input. It then calls scanFiles on that directory. Then it starts
 * a loop for the searching so that when "a" is typed in all the words and the files they are in are printed and when "s"
 * is typed the user can then search a word and get back what files the word is in or if no files contain that word. 
 * Finally, the user can input "q" to stop.
 */
	public static void main(String[] args)throws IOException {
		
		File directory = new File(args[0]);
		scanFiles(directory);
		
		while(!stop.equals("q")) {
			
			System.out.println("Please enter a command (a, s, or q)>>");
			String command = scan.nextLine();
			
			if(command.equals("a")) {
				BiTree.printTree();
			}
			
			else if(command.equals("s")) {
				System.out.println("Word to find>>");
				String look_for = scan.nextLine(); //create a string object in case it is not in the tree so that the print works
				Word look_for_word = new Word(look_for); //create a word object so that it can search the tree for the word
				
				if(BiTree.contains(look_for_word)) { //search with word object to see if the word is in the tree
					System.out.println(((Word) BiTree.find(look_for_word)).toString()); //toString in Word class makes it print properly
				}
				
				else if(!BiTree.contains(look_for_word)) {
					System.out.println(look_for + " is not found."); //print with the string object so that this will print properly
				}
			}
			
			else if(command.equals("q")) { //to stop the loop
				stop = "q";
			}
		} 
	}
	
	/*
	 * This method takes in a file object in this case the directory. It does not return anything, but instead populates
	 * the binary tree (which is a global variable) with word objects that it creates from all of the files in the text 
	 * and adds the file names that these words are in.
	 */
	public static void scanFiles(File f) throws IOException{
		
		File[] FileList = f.listFiles(); //get a list of the objects within any directory
		
		for(int fi = 0; fi<FileList.length; fi++) { //iterate through the list
			
			if(!FileList[fi].isHidden()) {	
				if(FileList[fi].isDirectory()) {
					scanFiles(FileList[fi]); //recursive call
				}
				else {
					textreader = new Scanner(FileList[fi]);
					String text = "";
					while(textreader.hasNextLine()) {
						text += textreader.nextLine();
					}
					
					String temp = "";
					for(int ch = 0; ch < text.length(); ch++) { //iterate to get rid of the punctuation 
						if(Character.isLetter(text.charAt(ch))) { //keep the letters
							temp+=text.charAt(ch);
						}
						else if(Character.isWhitespace(text.charAt(ch))) { //so that we keep the spaces
							temp+=text.charAt(ch);
						}
					}
					text = temp; //so that text is now without punctuation
					
					String[] words = text.split(" "); //array of the words splitting based on the spaces
					for(int w = 0; w<words.length; w++) { //iterate through the array
						Word text_word = new Word(words[w]); //create a word object for every word in the array
						if(BiTree.contains(text_word)) {
							Word returned = (Word) BiTree.find(text_word); //find the word in the tree
							returned.addFile(FileList[fi].toString()); //add the file to the tree
						}
						else {
							text_word.addFile(FileList[fi].toString());
							BiTree.insert(text_word);
						}
					}
				}
			}
		}
	}
}
