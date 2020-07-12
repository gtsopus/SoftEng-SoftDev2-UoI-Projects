package encodingstrategies;

public abstract class TemplateEncoding implements EncodingStrategy {
	
	public TemplateEncoding() {
		
	}
	
	//general encode method
	public String encode(String s) {
		//split the string to characters
		char[] charArray = s.toCharArray();
		for(int i=0; i<charArray.length; i++) {
			//mapCharacter is defined in child class accordingly
			charArray[i] = this.mapCharacter(charArray[i]);
		}
		//make string out of encoded characters
		String encodedS = new String(charArray);
		return encodedS;
	}
	public abstract char mapCharacter(char c);
}
