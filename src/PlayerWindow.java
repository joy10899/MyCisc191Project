import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class PlayerWindow extends JFrame {

    private JPanel PanelNorth;
    private JPanel PanelCenter;
    private JPanel PanelSouth;
    private JLabel player1Label;
    private JLabel player2Label;
    private JLabel instructions;
    private JTextArea textArea;
    private JLabel ReadyLabel;
    private JButton ReadyButton;
    public static Player player1;
    public static Player player2;
    public static ArrayList<Player> players;

    public PlayerWindow(Player player1, Player player2) {
        // Call the superclass constructor and set the window title
        super("Player Window");

        // Define the window size
//        final int WINDOW_WIDTH = 400;
//        final int WINDOW_HEIGHT = 200;

        // Set the size and default close operation
//        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the main panel and components
        try
		{
			createPanel(player1, player2);
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
       

        // Make the window visible
        setVisible(true);
        
        // Add an ActionListener to the "Ready" button
        ReadyButton.addActionListener(new ActionListener() {
        	

            public void actionPerformed(ActionEvent e) {
            	 dispose();
            	 List<Player> players = new ArrayList<>();
                 players.add(player1);
                 players.add(player2);

                 // Create and display the new GameWindow
                 JFrame GameWindow = new GameWindow(players);
                GameWindow.setVisible(true);
          
     
            }
        });
    }
    
    private void createPanel(Player player1, Player player2) throws IOException {
        //customFont
        Font customFont = new Font("Serif", Font.PLAIN, 20);
        
        //Create PanelNorth
        PanelNorth = new JPanel();
        // Create player1 & player2 Label
        player1Label = new JLabel("Player 1  "   +   player1.getName() );
        player1Label.setFont(customFont);
        player2Label = new JLabel("Player 2  "   +   player2.getName() );
        player2Label.setFont(customFont);
        
        //Create PanelCenter
        PanelCenter = new JPanel();
        instructions = new JLabel("Instructions");
        textArea = new JTextArea("",50,100);
        //Create PanelSouth
        PanelSouth = new JPanel();
        // Create Ready Label
        ReadyLabel = new JLabel("Are you ready?");
        ReadyLabel.setFont(customFont);
        //Create Ready Button
        ReadyButton = new JButton("Ready");
        ReadyButton.setFont(customFont);
        
        // Add labels to panels
        PanelNorth.add(player1Label);
        PanelNorth.add(player2Label);
        PanelCenter.setLayout(new BorderLayout());
        PanelCenter.add(instructions,BorderLayout.NORTH);
        PanelCenter.add(textArea,BorderLayout.CENTER);
        PanelCenter.add(new JScrollPane(textArea));
        try {
        	//Obtain and store information of File name
            File inFile = new File ("gameInstruction.txt");
            //create scanner object connect to the input file
            Scanner scanner = new Scanner(inFile);
            StringBuffer str = new StringBuffer("");
            //check if input File has next line
            while (scanner.hasNextLine())
            {
                //store the current line to String line
                String line = scanner.nextLine();
                //append line to this character sequence
                str.append(line+System.lineSeparator());
            }
            //set text to str
            textArea.setText(str.toString());
        }
        catch(IOException e)
        {
        	throw e;
        }
//        finally
//        {
//        	scanner.close();
//        }
//        
        
        
        
        
        
        PanelSouth.add(ReadyLabel);
        PanelSouth.add(ReadyButton);

     // Create a location for the JFrame
        Container contentPane = getContentPane();

        // Use BorderLayout for the content pane
        contentPane.setLayout(new BorderLayout());

        // Add the PanelNorth and PanelSouth to the north and south of the content pane
        contentPane.add(PanelNorth, BorderLayout.NORTH);
        contentPane.add(PanelCenter,BorderLayout.CENTER);
        contentPane.add(PanelSouth, BorderLayout.SOUTH);
        
    }


    public static void main(String[] args) {
        // Example usage: Create an instance of PlayerWindow with a player name

    	new PlayerWindow(player1, player2);
    	List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
    }



	

}
