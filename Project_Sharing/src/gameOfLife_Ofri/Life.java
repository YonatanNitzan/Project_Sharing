package gameOfLife_Ofri;

public class Life {

	private static String[][] cells = new String[17][17];
	private static int genNum = 1;
	
	public static String[][] fill_arr(String [][] arr, String shape)
	{
		for(int i=0; i<17; i++)
		{
			for(int k=0; k<17; k++)
				arr[i][k] = "0";
		}
		
		return arr;
	}
	
	public static void print_arr(String[][] arr, int gen)
	{
		System.out.println("gen " + gen);
		
		for(int i=1; i<16; i++)
		{
			for(int k=1; k<16; k++)
			{
				if(k==15)
					if(arr[i][k] == "1")
						System.out.println("* ");
					else
						System.out.println("- ");
				else
					if(arr[i][k] == "1")
						System.out.print("* ");
					else
						System.out.print("- ");
			}
			if(i==15)
				System.out.println(" ");
		}
	}
	
	public static String[][] next_gen(String[][] old_arr)
	{
		genNum++;
		int count = 0;
		String[][] new_arr = new String[17][17];
		
		for(int i=0; i<17; i++)
			for(int k=0; k<17; k++)
				new_arr[i][k] = old_arr[i][k];
		
		for(int i=1; i<16; i++)
		{
			for(int k=1; k<16; k++)
			{
				count = 0;
				if(old_arr[i-1][k-1] == "1")
					count++;
				if(old_arr[i-1][k] == "1")
					count++;
				if(old_arr[i-1][k+1] == "1")
					count++;
				if(old_arr[i][k-1] == "1")
					count++;
				if(old_arr[i][k+1] == "1")
					count++;
				if(old_arr[i+1][k-1] == "1")
					count++;
				if(old_arr[i+1][k] == "1")
					count++;
				if(old_arr[i+1][k+1] == "1")
					count++;
				
				if(old_arr[i][k] == "1")
				{
					
					if(count > 3)
						new_arr[i][k] = "0";
					else if(count < 2)
						new_arr[i][k] = "0";
					
				}
				else if(count == 3)
					new_arr[i][k] = "1";
				
			}
		}
		
		return new_arr;
	}
	
	public static String[][] getCells()
	{
		return cells;
	}
	
	public static void setCells(String[][] newCells)
	{
		cells = newCells;
	}
	
	public static int getGenNum()
	{
		return genNum;
	}
	
	public static void setGenNum(int newGenNum)
	{
		genNum = newGenNum;
	}
	
	public static void main(String[] args) {
		
		cells = fill_arr(cells, "X");
		new Board();
		Board.cellsToBoard();
		Board.setText();
	}
}
