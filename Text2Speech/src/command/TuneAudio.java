package command;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import model.Document;

public class TuneAudio implements ActionListener{
	
	Document curDocument;
	JSlider volumeSlider;
	JSlider rateSlider;
	JSpinner pitchSpinner;
	
	int volumeSliderRep;
	int rateSliderRep;
	int pitchSpinnerRep;
	
	private ArrayList<ActionListener> commandList = new ArrayList<ActionListener>();
	private ArrayList<Boolean> replayBool = new ArrayList<Boolean>();
	
	//bool that check if command is a copy of another
	boolean isReplayed = false;
	
	//constructor
	public TuneAudio(Document curDocument, JSlider volumeSlider, JSlider rateSlider, JSpinner pitchSpinner,ArrayList<ActionListener> commandList,ArrayList<Boolean> replayBool) {
		this.curDocument = curDocument;
		this.volumeSlider = volumeSlider;
		this.rateSlider = rateSlider;
		this.pitchSpinner = pitchSpinner;
		this.commandList = commandList;
		this.replayBool = replayBool;
	}
	
	//replay constructor
	public TuneAudio(Document curDocument, int volumeSlider, int rateSlider, int pitchSpinner,ArrayList<ActionListener> commandList) {
		this.curDocument = curDocument;
		this.volumeSliderRep = volumeSlider;
		this.rateSliderRep = rateSlider;
		this.pitchSpinnerRep = pitchSpinner;
		isReplayed = true;
		this.commandList = commandList;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(isReplayed == false) {
			curDocument.tuneAudioSettings(volumeSlider.getValue(), rateSlider.getValue(),(int)pitchSpinner.getValue());
		}
		else{curDocument.tuneAudioSettings(volumeSliderRep,rateSliderRep,pitchSpinnerRep);}
		
		//check if we are recording commands
		if(!isReplayed) {
			if(replayBool.get(0) == true) {
				TuneAudio copy = new TuneAudio(curDocument, volumeSlider.getValue(), rateSlider.getValue(), (int)pitchSpinner.getValue(), commandList);
				commandList.add(copy);
			}
		}
	}
}