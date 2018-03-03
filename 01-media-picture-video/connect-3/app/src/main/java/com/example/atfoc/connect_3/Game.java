package com.example.atfoc.connect_3;

import java.io.Serializable;

/**
 * Created by atfoc on 2/24/18.
 */

public class Game implements Serializable
{
	private int[][] board;
	private int currPlayer;
	private boolean end;
	private int game_winner;
	private int fieldLeft;
	private static int numRowCol = 3;

	public Game()
	{
		board = new int[numRowCol][numRowCol];
		this.reset();
		currPlayer = 0;
		end = false;
		game_winner = -1;
		fieldLeft = 9;
	}

	public int set(int x, int y)
	{
		if(x < 0 || x > numRowCol-1 || y < 0 || y > numRowCol-1)
		{
			return -1;
		}

		if(-1 == board[x][y])
		{
			int tmp = currPlayer;
			board[x][y] = currPlayer;
			currPlayer = (0 == currPlayer) ? 1 : 0;
			--fieldLeft;
			return tmp;
		}
		return -1;
	}

	public boolean isEnd()
	{
		if(-1 != check_for_win())
		{
			end = true;
		}
		return end || fieldLeft == 0;
	}

	public void reset()
	{
		for(int[] i : board)
		{
			for(int j = 0 ; j < numRowCol; ++j)
			{
				i[j] = -1;
			}
		}
		end = false;
		currPlayer = 0;
		game_winner = -1;
		fieldLeft = 9;
	}

	public int winner()
	{
		return game_winner;
	}

	public int playerTurn()
	{
		return  currPlayer;
	}

	private int check_horizontal(int row)
	{
		int tmp = board[row][0];
		if(-1 == tmp)
		{
			return -1;
		}

		for(int i = 1; i < numRowCol; ++i)
		{
			if(tmp != board[row][i])
			{
				return -1;
			}
		}
		return tmp;
	}

	private int check_vertical(int col)
	{
		int tmp = board[0][col];
		if(-1 == tmp)
		{
			return -1;
		}

		for(int i = 1; i < numRowCol; ++i)
		{
			if(tmp != board[i][col])
			{
				return -1;
			}
		}

		return tmp;
	}

	private int check_diagonal1()
	{
		int tmp = board[0][0];
		if(-1 == tmp)
		{
			return -1;
		}

		for(int i = 1; i < numRowCol; ++i)
		{
			if(tmp != board[i][i])
			{
				return -1;
			}
		}

		return tmp;
	}

	private int check_diagonal2()
	{
		int tmp = board[numRowCol-1][0];

		if(-1 == tmp)
		{
			return -1;
		}

		for(int i = 1; i < numRowCol; ++i)
		{
			if(tmp != board[numRowCol - i - 1][i])
			{
				return -1;
			}
		}

		return tmp;
	}

	private int check_for_win()
	{
		int w = -1;
		//check rows
		for(int i = 0; i < numRowCol; ++i)
		{
			w = check_horizontal(i);
			if(-1 != w)
			{
				game_winner = w;
				return w;
			}
		}

		//check col
		for(int i = 0; i < numRowCol; ++i)
		{
			w = check_vertical(i);
			if(-1 != w)
			{
				game_winner = w;
				return w;
			}
		}

		//check diagonal1
		w = check_diagonal1();
		if(-1 != w)
		{
			game_winner = w;
			return w;
		}

		w = check_diagonal2();
		if(-1 != w)
		{
			game_winner = w;
			return w;
		}
		return -1;
	}

	int getPlayerAt(int x, int y)
	{
		if(x < 0 || x > numRowCol-1 || y < 0 || y > numRowCol-1)
		{
			return -1;
		}
		return board[x][y];
	}

	int getNextPlayer()
	{
		return currPlayer;
	}

}
