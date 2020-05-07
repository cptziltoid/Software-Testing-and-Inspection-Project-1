import static org.junit.Assert.*;

import org.junit.Test;

public class TestGameOfLife {

	@Test
	public void testAnalyzeTable() {
		int rows = 6;
		int columns = 6;
		int[][] test1 ={{1, 0, 0, 0, 0, 0},    
				 		{0, 0, 0, 0, 0, 0},    
				 		{0, 0, 0, 0, 0, 0},    
				 		{0, 0, 0, 0, 0, 0},    
				 		{0, 0, 0, 0, 0, 0},    
				 		{0, 0, 0, 0, 0, 0}};
		
		int[][] expected1 ={{0, 0, 0, 0, 0, 0},    
				 			{0, 0, 0, 0, 0, 0},    
				 			{0, 0, 0, 0, 0, 0},    
				 			{0, 0, 0, 0, 0, 0},    
				 			{0, 0, 0, 0, 0, 0},    
				 			{0, 0, 0, 0, 0, 0}}; 
		
		int[][] test2 ={{1, 0, 0, 0, 0, 1},    
				 		{1, 0, 0, 0, 0, 1},    
				 		{0, 0, 1, 0, 0, 1},    
				 		{0, 0, 0, 0, 0, 1},    
				 		{1, 1, 1, 1, 1, 1},    
				 		{0, 1, 0, 1, 1, 1}};

		int[][] expected2 ={{0, 0, 0, 0, 0, 0},    
				 			{0, 1, 0, 0, 1, 1},    
				 			{0, 0, 0, 0, 1, 1},    
				 			{0, 0, 0, 0, 0, 1},    
				 			{1, 1, 0, 0, 0, 0},    
				 			{1, 1, 0, 0, 0, 1}};
		
		GameOfLife game = new GameOfLife(test1, rows, columns);
		int[][] test1Output = game.analyzeTable();
		assertArrayEquals(test1Output, expected1);
	}

}
