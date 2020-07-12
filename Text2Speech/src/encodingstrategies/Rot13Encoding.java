package encodingstrategies;

public class Rot13Encoding extends TemplateEncoding{
	
	public Rot13Encoding() {
		
	}	

	@Override
	public char mapCharacter(char c) {
		if(Character.isLetter(c)) {
			if(Character.isUpperCase(c)) {
				if(c < 'M') {
					c += 13;
					return c;
				}
				else {
					//if c+13 > 'Z' then we subtract the difference from 'A' plus 12 digits for Rot encoding
					int rotInt =  'A' + 12 - ('Z' - c);
					char rotChar = (char) rotInt;
					return rotChar;
				}
			}
			else if(Character.isLowerCase(c)) {
				if(c < 'm') {
					c += 13;
					return c;
				}
				else {
					//if c+13 > 'z' then we subtract the difference from 'a' plus 12 digits for Rot encoding
					int rotInt =  'a' + 12 - ('z' - c);
					char rotChar = (char) rotInt;
					return rotChar;
				}
			}
		}
		else {
			//if c is not a letter then return it
			return c;
		}
		System.out.println("Something's wrong");
		return 0;
	}
}
