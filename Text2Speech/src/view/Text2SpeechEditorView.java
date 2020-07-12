package view;

import java.awt.EventQueue;
import java.util.Hashtable;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JToolBar;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import command.CommandFactory;
import model.Document;

import java.awt.BorderLayout;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextArea;

public class Text2SpeechEditorView {

	//Swing components
	private JFrame frame;
	JTextArea textArea = new JTextArea();

	//command listener factory
	private static CommandFactory commandFactory;
	
	//Constructor
	public Text2SpeechEditorView() {
		initialize();
	}
	
	//Document
	private Document curDocument;
	
	//Basic window function
	private void initialize() {
		curDocument = new Document();
		
		frame = new JFrame("Text2Speech Editor");
		frame.setBounds(100, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//initialize sliders
		JSlider rateSlider = new JSlider(0,1000,150);
		JSlider soundSlider = new JSlider(0,100,100);
		//pitch spinner
		JLabel spinnerText = new JLabel("<html>Pitch <br/>Level </html>", SwingConstants.CENTER);
		spinnerText.setSize(10, 10);
		SpinnerModel model = new SpinnerNumberModel(100, 0, 500, 1);     
		JSpinner spinner = new JSpinner(model);
		
	    //initialize command factory
	    commandFactory = new CommandFactory(textArea, frame, curDocument,soundSlider,rateSlider,spinner);
	    
		//initialize toolbars
		JToolBar toolBar = new JToolBar();
		JToolBar soundToolBar = new JToolBar();

		frame.getContentPane().add(toolBar, BorderLayout.NORTH);
		frame.getContentPane().add(soundToolBar, BorderLayout.SOUTH);

		//initialize menubars
		JMenuBar menuBar = new JMenuBar();
		toolBar.add(menuBar);

		//add sliders
		soundSlider.setMajorTickSpacing(25);
		soundSlider.setPaintTicks(true);
		
		// Set the labels to be painted on the slider
		soundSlider.setPaintLabels(true);
		Hashtable<Integer, JLabel> position = new Hashtable<Integer, JLabel>();
		position.put(0, new JLabel("0"));
		position.put(50, new JLabel("Sound Level"));
		position.put(100, new JLabel("100"));
		         
		// Set the label to be drawn
		soundSlider.setLabelTable(position);
		
		rateSlider.setMajorTickSpacing(100);
		rateSlider.setPaintTicks(true);
		rateSlider.setPaintLabels(true);
		         
		Hashtable<Integer, JLabel> position2 = new Hashtable<Integer, JLabel>();
		position2.put(0, new JLabel("0"));
		position2.put(500, new JLabel("Rate level"));;
		position2.put(1000, new JLabel("1000"));
		         
		rateSlider.setLabelTable(position2);
		
		//sound save button
		JButton saveSound = new JButton(new ImageIcon("img/soundsave.png"));
		saveSound.setToolTipText("Save sound settings.");

		//add soundToolbar components
		soundToolBar.add(soundSlider);
		soundToolBar.add(rateSlider);
		soundToolBar.add(spinnerText);
		soundToolBar.add(spinner);
		
		//send sound settings to command
		saveSound.addActionListener(commandFactory.createCommand("TuneAudio"));
		soundToolBar.add(saveSound);
		
		//Add menus
		JMenu mnNewMenu = new JMenu("File");
		JMenu mnPlayMenu = new JMenu("Play");
		JMenu mnEncodeMenu = new JMenu("Encode");
		JMenu mnReplayMenu = new JMenu("Replay");
		
		menuBar.add(mnNewMenu);
		menuBar.add(mnPlayMenu);
		menuBar.add(mnEncodeMenu);
		menuBar.add(mnReplayMenu);

		//Add menu items and actionListeners linking to commands
		JMenuItem mntmNewMenuItem = new JMenuItem("New");
		mnNewMenu.add(mntmNewMenuItem);
		mntmNewMenuItem.addActionListener(commandFactory.createCommand("New"));
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Open...");
		mnNewMenu.add(mntmNewMenuItem_2);
		mntmNewMenuItem_2.addActionListener(commandFactory.createCommand("Open..."));

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Save");
		mnNewMenu.add(mntmNewMenuItem_1);
		mntmNewMenuItem_1.addActionListener(commandFactory.createCommand("Save"));
		
		JMenuItem mntmNewMenuItem_0 = new JMenuItem("Edit");
		mnNewMenu.add(mntmNewMenuItem_0);
		mntmNewMenuItem_0.addActionListener(commandFactory.createCommand("Edit"));
			
		//audio related items
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Play document");
		mnPlayMenu.add(mntmNewMenuItem_3);
		mntmNewMenuItem_3.addActionListener(commandFactory.createCommand("Play document"));
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Play line");
		mnPlayMenu.add(mntmNewMenuItem_4);
		mntmNewMenuItem_4.addActionListener(commandFactory.createCommand("Play line"));
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Play reverse document");
		mnPlayMenu.add(mntmNewMenuItem_5);
		mntmNewMenuItem_5.addActionListener(commandFactory.createCommand("Play reverse document"));
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Play reverse line");
		mnPlayMenu.add(mntmNewMenuItem_6);
		mntmNewMenuItem_6.addActionListener(commandFactory.createCommand("Play reverse line"));
		
		JMenuItem mntmNewMenuItem_9 = new JMenuItem("Play encoded document");
		mnPlayMenu.add(mntmNewMenuItem_9);
		mntmNewMenuItem_9.addActionListener(commandFactory.createCommand("Play encoded document"));
		
		JMenuItem mntmNewMenuItem_10 = new JMenuItem("Play encoded line");
		mnPlayMenu.add(mntmNewMenuItem_10);
		mntmNewMenuItem_10.addActionListener(commandFactory.createCommand("Play encoded line"));
		
		//encoding related commands
		JRadioButtonMenuItem mntmNewMenuItem_7 = new JRadioButtonMenuItem("Rot13", false);
		mnEncodeMenu.add(mntmNewMenuItem_7);
		mntmNewMenuItem_7.addActionListener(commandFactory.createCommand("Rot13"));
		
		JRadioButtonMenuItem mntmNewMenuItem_8 = new JRadioButtonMenuItem("AtBash", false);
		mnEncodeMenu.add(mntmNewMenuItem_8);
		mntmNewMenuItem_8.addActionListener(commandFactory.createCommand("AtBash"));
		
		//replay related commands
		JRadioButtonMenuItem mntmNewMenuItem_11 = new JRadioButtonMenuItem("Start recording commands");
		mnReplayMenu.add(mntmNewMenuItem_11);
		mntmNewMenuItem_11.addActionListener(commandFactory.createCommand("Start recording commands"));
		
		JRadioButtonMenuItem mntmNewMenuItem_12 = new JRadioButtonMenuItem("Stop recording commands");
		mnReplayMenu.add(mntmNewMenuItem_12);
		mntmNewMenuItem_12.addActionListener(commandFactory.createCommand("Stop recording commands"));
		
		JMenuItem mntmNewMenuItem_13 = new JMenuItem("Play recorded commands");
		mnReplayMenu.add(mntmNewMenuItem_13);
		mntmNewMenuItem_13.addActionListener(commandFactory.createCommand("Play recorded commands"));
		
		//add buttonmenuitems to group in order for only one to be true at all times
		ButtonGroup group = new ButtonGroup();
		group.add(mntmNewMenuItem_8);
		group.add(mntmNewMenuItem_7);
		
		ButtonGroup replayGroup = new ButtonGroup();
		replayGroup.add(mntmNewMenuItem_11);
		replayGroup.add(mntmNewMenuItem_12);
		replayGroup.add(mntmNewMenuItem_13);
		
		//Add scroll pane to the frame
		JScrollPane scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		
		//add text area to scroll pane
		scrollPane.setViewportView(textArea);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);	
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Text2SpeechEditorView window = new Text2SpeechEditorView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
