package gameOfLife_Ofri;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Board extends JFrame{
	
	private static ArrayList<JButton> cellGrid = new ArrayList<JButton>();
	private static JLabel gen = new JLabel();
	private static boolean check = false;
	
	private static Timer tm = new Timer(1000, new ActionListener(){
		public void actionPerformed(ActionEvent evt){
			Life.setCells(Life.next_gen(Life.getCells()));
			cellsToBoard();
			setText();
		}
	});

	public Board()
	{
		setResizable(false);
		setTitle("Game Of Life");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel grid = new JPanel();
		grid.setPreferredSize(new Dimension(400,400));
		grid.setLayout(new GridLayout(15,15));
		
		int i = 0;
		
		while(i < 225)
		{
			cellGrid.add(new JButton());
			cellGrid.get(i).setBackground(Color.gray);
			cellGrid.get(i).addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					if(((JComponent)e.getSource()).getBackground().equals(Color.yellow))
						((JComponent)e.getSource()).setBackground(Color.gray);
					else
						((JComponent)e.getSource()).setBackground(Color.yellow);
					boardToCells();
					}
				});
						
			cellGrid.get(i).addMouseListener(new MouseMove());
			grid.add(cellGrid.get(i));
			i++;
		}
		
		grid.getInputMap().put(KeyStroke.getKeyStroke("pressed SPACE"), "spacePressed");
		grid.getActionMap().put("spacePressed", new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				check = true;
			}
		});
		
		grid.getInputMap().put(KeyStroke.getKeyStroke("released SPACE"), "spaceReleased");
		grid.getActionMap().put("spaceReleased", new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				System.out.println("s");
				for(JButton b:cellGrid)
				check = false;
			}
		});
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1,2));
		JButton next = new JButton("next");
		JButton clear = new JButton("clear");
		JButton start = new JButton("start");
		
		
		
		next.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Life.setCells(Life.next_gen(Life.getCells()));
				cellsToBoard();
				setText();
				}
			});
		
		clear.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				clearBoard();
				Life.setGenNum(1);
				setText();
				}
			});
		
		start.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(tm.isRunning())
					tm.stop();
				else
					tm.start();
				}
			});
		
		buttonPanel.add(start);
		buttonPanel.add(next);
		buttonPanel.add(clear);
		
		JPanel textPanel = new JPanel(); 
		gen.setFont(new Font("Arial", Font.PLAIN, 20));
		textPanel.add(gen);
		
		add(grid, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
		add(textPanel, BorderLayout.NORTH);
		pack();
		setVisible(true);
	}
	
	public static void cellsToBoard()
	{	
		for(int i=1; i<16; i++)
		{
			for(int k=1; k<16; k++)
			{	
				cellGrid.get((i-1)*15 + k-1).setBackground(Color.gray);
				if(Life.getCells()[i][k] == "1")
				{
					cellGrid.get((i-1)*15 + k-1).setBackground(Color.yellow);
				}
			}
		}
	}
	
	public static void boardToCells()
	{
		String[][] newCells = Life.getCells();
		
		for(int i=1; i<16; i++)
		{
			for(int k=1; k<16; k++)
			{	
				if(cellGrid.get((i-1)*15 + k-1).getBackground().equals(Color.yellow))
					newCells[i][k] = "1";
				else
					newCells[i][k] = "0";
			}
		}
		
		Life.setCells(newCells);
	}
	
	public static void clearBoard()
	{
		for(int i=1; i<16; i++)
		{
			for(int k=1; k<16; k++)
			{
				cellGrid.get((i-1)*15 + k-1).setBackground(Color.gray);
			}
		}
		boardToCells();
	}
	
	public static void setText()
	{
		gen.setText("generation: " + Life.getGenNum());
	}
	
	static class MouseMove implements MouseListener{

		@Override
		public void mouseEntered(MouseEvent e) {
			if(check)
				((JComponent) e.getSource()).setBackground(Color.yellow);
			
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			
		}

		
	}
}
