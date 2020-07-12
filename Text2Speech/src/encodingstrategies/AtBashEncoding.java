package encodingstrategies;

public class AtBashEncoding extends TemplateEncoding{
	
	public AtBashEncoding() {
		
	}
	
	@Override
	public char mapCharacter(char c) {
		//if char is a letter we subtract from the last letter the ascii number of c and add the first letter to return to the original range
		if(Character.isLetter(c)) {
			if(Character.isUpperCase(c)) {
				int ascii = (int) c ;
				int rotInt = 'Z' - ascii + 'A';
				char rotChar = (char) rotInt;
				return rotChar;
			}
			else if(Character.isLowerCase(c)) {
				int ascii = (int) c ;
				int rotInt = 'z' - ascii + 'a';
				char rotChar = (char) rotInt;
				return rotChar;
			}
		}
		//if c is not a letter then return it
		else {
			return c;
		}
		System.out.println("Something's wrong");
		return 0;
	}
}
