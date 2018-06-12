import java.util.ArrayList;

public class Word implements Comparable<Word>{
	
	private String word;
	private ArrayList<String> file_names = new ArrayList<String>();
	
	/*
	 * This is the constructor that takes in a string and creates a word object by setting this.word to that string.
	 */
	public Word(String str) {
		this.word = str;	
	}
	
	/*
	 * This takes in a string and adds it to the file_names array list.
	 */
	public void addFile(String name) {
		this.file_names.add(name);
	}
	
	/*
	 * This a setter method that takes in a string and sets this.word to that string.
	 */
	public void setWord(String str) {
		this.word = str;
	}
	
	/*
	 * This is a getter method that takes nothing and returns this.word.
	 */
	public String getWord() {
		return this.word;
	}
	
	/*
	 * This is a setter method that takes in an array list of strings and sets this.file_names equal to that.
	 */
	public void setFileNames(ArrayList<String> files) {
		this.file_names = files;
	}
	
	/*
	 * This is a getter method that takes in nothing and returns this.file_names.
	 */
	public ArrayList<String> getFileNames() {
		return this.file_names;
	}
	
	/*
	 * This is the to string method that takes in nothing and sorts through the file_names array list to create a 
	 * string of the files that are in the word. It then returns "Files containing " as well as this.word as well 
	 * as the string of files.
	 */
	public String toString() {
		String text_files = "";
		for(int index = 0; index<this.file_names.size(); index++) {
			String file = file_names.get(index);
			String[] files_directories = file.split("/");
			text_files += files_directories[files_directories.length-1] + " ";
		}
		return "Files containing " + this.word + ": " + text_files;
	}
	
	/*
	 * This is a compare to method that takes in a word object, otherWord and returns this.word compared to the otherWord
	 * and the comparison is done through calling the string class compare to method.
	 */
	public int compareTo(Word otherWord) {
		return this.word.compareTo(otherWord.getWord());
	}

}
