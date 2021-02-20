package command;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ReplayCommand implements ActionListener{
	private static ArrayList<ActionListener> replayCommands;
	private int saveIndex;
	
	private ArrayList<Boolean> replayBool = new ArrayList<Boolean>();
	
	public ReplayCommand(int saveIndex, ArrayList<ActionListener> commandList, ArrayList<Boolean> replayBool){
		ReplayCommand.replayCommands = commandList;
		this.saveIndex = saveIndex;
		this.replayBool = replayBool;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(saveIndex == 1){
			saveRecording();
		}
		else if(saveIndex == 2){
			replay(replayCommands);	
		}			
		else if(saveIndex == 3){
			startRecording();	
		}			
	}
	
	//read log file and remplay commands
	public void replay(ArrayList<ActionListener> replayCommands){
		for(int i=0; i<replayCommands.size(); i++) {
			replayCommands.get(i).actionPerformed(null);;
		}
	}
	
	//set start index in CommandFactory true 
	public void startRecording(){
		replayCommands.clear();
		replayBool.set(0, true);
	}
	
	//read log file for header and close it with end header
	public void saveRecording(){
		replayBool.set(0, false);
	}
	
	//add command to arraylist
	public static void addCommandToArraylist(ActionListener command) {
		replayCommands.add(command);
		
	}
}
