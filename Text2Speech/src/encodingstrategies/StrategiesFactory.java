package encodingstrategies;

public class StrategiesFactory {
	
	private EncodingStrategy dummyEncodingStrategy;

	public StrategiesFactory() {
		
	}
	
	//instantiates and returns an encoding strategy depending on a string
	public EncodingStrategy createStrategy(String s) {
		if(s == "rot13" || s == "Rot13") {
			dummyEncodingStrategy = new Rot13Encoding();
		}
		else if(s == "atbash" || s == "atBash"|| s == "Atbash"|| s == "AtBash") {
			dummyEncodingStrategy = new AtBashEncoding();
		}
		return dummyEncodingStrategy;
	}
}
