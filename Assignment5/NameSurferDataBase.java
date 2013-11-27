import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import acm.util.*;
import java.util.*;

/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 * 
 * Received help: https://github.com/NatashaTheRobot/Stanford-CS-106A/blob/master/Assignment6/NameSurferDataBase.java
 */

public class NameSurferDataBase implements NameSurferConstants { 
	
/* Constructor: NameSurferDataBase(filename) */
/**
 * Creates a new NameSurferDataBase and initializes it using the
 * data in the specified file.  The constructor throws an error
 * exception if the requested file does not exist or if an error
 * occurs as the file is being read.
 */
	public NameSurferDataBase(String filename) {
		getNameData(filename);
	}
	
	private HashMap <String, NameSurferEntry> namesHistory = new HashMap <String, NameSurferEntry>();
	private void getNameData(String filename) {
		try {
			BufferedReader getName = new BufferedReader(new FileReader(filename));
			while(true) {
				String singleName = getName.readLine();
				if(singleName == null) break;
				NameSurferEntry enterName = new NameSurferEntry(singleName);
				namesHistory.put(enterName.getName(), enterName);
			}
			getName.close();
		}
		catch(IOException ex) {
			throw new ErrorException(ex);
		}
	}
	
/* Method: findEntry(name) */
/**
 * Returns the NameSurferEntry associated with this name, if one
 * exists.  If the name does not appear in the database, this
 * method returns null.
 */
	public NameSurferEntry findEntry(String name) {
		/* 
		 * translates user input name to format of names in database:
		 * all lowercase except capital first letter
		 */
		char firstLetter = name.charAt(0);
		if (Character.isLowerCase(firstLetter) == true) {
			firstLetter = Character.toUpperCase(firstLetter);
		}
		String allLetters = name.substring(1);
		allLetters = allLetters.toLowerCase();
		name = firstLetter + allLetters;
		/*
		 * search database for matching name
		 */
		if (namesHistory.containsKey(name)) {
			return namesHistory.get(name); //name matched
		}
		else {
			return null; //name not found
		}
	}
	
}

