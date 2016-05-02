import java.awt.Color;


public class Tetromino {
	Grid grid;
	Block[] piece;
	int rotateX;
	int rotateY;
	
	protected Tetromino(Grid g, int x, int y) {
		this.grid = g;
		this.piece = new Block[0];
		this.rotateX = x;
		this.rotateY = y;
	}
	
	public boolean canPlace() {
		for (int i=0; i<piece.length; i++)
			if (!piece[i].canPlace()) return false;
		return true;
	}
	
	public void remove() {
		for (int i=0; i<piece.length; i++)
			grid.removeBlock(piece[i]);
	}
	
	public void add() {
		for (int i=0; i<piece.length; i++)
			grid.addBlock(piece[i]);
	}
	
	public Tetromino move(int deltaX, int deltaY) {
		Color c = this.piece[0].getColor();
		Tetromino t = new Tetromino(this.grid, this.rotateX, this.rotateY);
		t.piece = new Block[this.piece.length];
		for (int i=0; i<this.piece.length; i++) {
			int x = this.piece[i].getX();
			int y = this.piece[i].getY();
			t.piece[i] = new Block(this.grid, x+deltaX, y+deltaY, c);
		}
		t.rotateX = this.rotateX + deltaX;
		t.rotateY = this.rotateY + deltaY;
		return t;
	}
	
	
	
	
	
	public Tetromino rotate() {
		Color c = this.piece[0].getColor();
		Tetromino t = new Tetromino(this.grid, this.rotateX, this.rotateY);
		t.piece = new Block[this.piece.length];
		for (int i=0; i<this.piece.length; i++) {
			int x = this.piece[i].getX();
			int y = this.piece[i].getY();
			int x0 = this.rotateX;
			int y0 = this.rotateY;
			t.piece[i] = new Block(this.grid, -1*(y-y0) + x0, (x-x0) + y0, c);
		}
		return t;
	} 
	
	
	
	
	
	
	
	public static Tetromino newTetromino(Grid g, int x, int y, int type, int orientation) {
		Tetromino t;
		if (type==0) t = new TetrominoI(g, x, y);
		else if (type==1) t = new TetrominoT(g, x, y);
		else if (type==2) t = new TetrominoO(g, x, y);
		else if (type==3) t = new TetrominoL(g, x, y);
		else if (type==4) t = new TetrominoJ(g, x, y);
		else if (type==5) t = new TetrominoS(g, x, y);
		else if (type==6) t = new TetrominoZ(g, x, y);
		else t = new Tetromino(g, x, y);
		
		for (int i=0; i<orientation; i++)
			t = t.rotate();
		
		return t;
	}
	
	public static Color rndColor() {
		int r = (int)(8*Math.random());
		if (r==0) return Color.BLUE;
		else if (r==1) return Color.CYAN;
		else if (r==2) return Color.GREEN;
		else if (r==3) return Color.MAGENTA;
		else if (r==4) return Color.ORANGE;
		else if (r==5) return Color.PINK;
		else if (r==6) return Color.RED;
		return Color.YELLOW;
	}

	
}
