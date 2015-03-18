package com.codergb.jsontilemapeditor.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import com.codergb.jsontilemapeditor.editor.Editor;
import com.codergb.jsontilemapeditor.json.JSONParser;
import com.codergb.jsontilemapeditor.ui.UIButton;
import com.codergb.jsontilemapeditor.ui.UILabel;
import com.codergb.jsontilemapeditor.ui.UITextField;

/**
 * This class sets up the user interface for the program
 * as well as the main editor for the tilemap.
 * 
 * @author Brendan Goodenough
 */

public class Program implements ActionListener {
	
	// Variables
	private int tileSize, rows, cols;
	
	// Constants
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	public static final String PROGRAM_TITLE = "JSON TileMap Editor";
	
	// Components
	private Editor editor;
	private JPanel container;
	private UITextField tfCols, tfRows, tfTileSize;
	private UIButton btnSaveFile, btnResize;
	
	private JSONParser jsonParser;
	
	/**
	 * Constructor for the program
	 */
	@SuppressWarnings("serial")
	public Program() {
		// Variables
		tileSize = 32;
		rows = 16;
		cols = 24;
		
		jsonParser = new JSONParser();
		
		// Text fields
		tfCols = new UITextField(cols + "");
		tfRows = new UITextField(rows + "");
		tfTileSize = new UITextField(tileSize + "");
		
		// Buttons
		btnSaveFile = new UIButton("Save");
		btnResize = new UIButton("Resize");
		
		btnSaveFile.setActionCommand("saveFile");
		btnResize.setActionCommand("resize");
		
		btnSaveFile.addActionListener(this);
		btnResize.addActionListener(this);
		
		// Setup the editor
		editor = new Editor(rows, cols, tileSize);
		
		// Setup sub panels
		JPanel subSaveLoadPanel1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		JPanel subResizePanelTitle = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JPanel subResizePanel1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JPanel subResizePanel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JPanel subResizePanel3 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		JPanel tileLayerContainer = new JPanel();
		tileLayerContainer.setLayout(new BoxLayout(tileLayerContainer, BoxLayout.Y_AXIS));
		
		subSaveLoadPanel1.setBackground(Color.WHITE);
		subResizePanelTitle.setBackground(Color.WHITE);
		subResizePanel1.setBackground(Color.WHITE);
		subResizePanel2.setBackground(Color.WHITE);
		subResizePanel3.setBackground(Color.WHITE);
		
		subSaveLoadPanel1.add(btnSaveFile);
		
		subResizePanelTitle.add(new UILabel("Properties"));
		
		subResizePanel1.add(new UILabel("Map Size:"));
		subResizePanel1.add(tfCols);
		subResizePanel1.add(new UILabel("x"));
		subResizePanel1.add(tfRows);
		
		subResizePanel2.add(new UILabel("Tile Size:"));
		subResizePanel2.add(tfTileSize);
		
		subResizePanel3.add(btnResize);
		
		// Setup the toolPanel
		JPanel toolPanel = new JPanel();
		toolPanel.setLayout(new BoxLayout(toolPanel, BoxLayout.Y_AXIS));
		toolPanel.add(subSaveLoadPanel1);
		toolPanel.add(subResizePanelTitle);
		toolPanel.add(subResizePanel1);
		toolPanel.add(subResizePanel2);
		toolPanel.add(subResizePanel3);
		toolPanel.add(tileLayerContainer);
		
		JPanel toolPanelContainer = new JPanel(new FlowLayout(FlowLayout.RIGHT)) {
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(new Color(220, 220, 220));
				g.drawLine(0, 0, 0, getHeight());
			}
		};
		toolPanelContainer.setBackground(Color.WHITE);
		toolPanelContainer.setBorder(new EmptyBorder(5, 5, 5, 5));
		toolPanelContainer.add(toolPanel);
		
		// Setup the container
		container = new JPanel(new BorderLayout());
		container.add(toolPanelContainer, BorderLayout.EAST);
		container.add(editor);
		container.setBackground(Color.WHITE);
		
		// Setup the JFrame
		JFrame frame = new JFrame(PROGRAM_TITLE);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT);
		frame.add(container);
		frame.setLocationRelativeTo(null);
		
		// Show JFrame
		frame.setVisible(true);
	}
	
	private String getFilePath() {
		JFileChooser chooser = new JFileChooser();
		int returnVal = chooser.showSaveDialog(container);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			return chooser.getSelectedFile().getAbsolutePath();
		}
		return null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		switch (cmd) {
		case "saveFile":
			jsonParser.saveJSON(getFilePath(), editor.getTiles());
			break;
		case "resize":
			try {
				cols = Integer.parseInt(tfCols.getText());
				rows = Integer.parseInt(tfRows.getText());
				tileSize = Integer.parseInt(tfTileSize.getText());
			}
			catch(Exception ex) {
				tfCols.setText(cols + "");
				tfRows.setText(rows + "");
				tfTileSize.setText(tileSize + "");
			}
			editor.setSize(rows, cols, tileSize);
			break;
		}
	}
	
	/* Getters */
	public Editor getEditor() { return editor; }
	
	/**
	 * Starts the program
	 */
	public static void main(String[] args) {
		new Program();
	}
	
}
