import java.awt.Color;


public class Block {
	
	private Grid grid;
	private int x;
	private int y;
	private Color c;
	
	public Block(Grid myGrid, int x, int y, Color c) {
		this.grid = myGrid;
		this.x = x;
		this.y = y;
		this.c = c;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public Color getColor() {
		return this.c;
	}
	
	public boolean canPlace() {
		return grid.blockIsFree(this.x, this.y);
	}
	
}
