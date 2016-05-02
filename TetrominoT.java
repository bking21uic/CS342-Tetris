import java.awt.Color;


public class TetrominoT extends Tetromino {

	public TetrominoT(Grid g, int x, int y) {
		super(g, x, y);
		Color c = Tetromino.rndColor();
		this.grid = g;
		this.piece = new Block[4];
		this.piece[0] = new Block(g, x, y, c);
		this.piece[1] = new Block(g, x+1, y, c);
		this.piece[2] = new Block(g, x-1, y, c);
		this.piece[3] = new Block(g, x, y+1, c);
		this.rotateX = x;
		this.rotateY = y;
	}
	
}
