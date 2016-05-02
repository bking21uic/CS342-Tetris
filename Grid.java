import java.awt.*;


public class Grid {
	
	int width;
	int height;
	Block[][] block;
	Tetromino piece = null;
	int nextPieceID;
	int nextOrientation;
	boolean gameOver = false;
	
	int score = 0;
	int lines = 0;
	int level = 1;
	
	
	public static final int USERKEY_L = 0;
	public static final int USERKEY_R = 1;
	public static final int USERKEY_D = 2;
	public static final int USERKEY_U = 3;
	public static final int USERKEY_DX = 4;
	
	public Grid(int width, int height) {
		this.width = width;
		this.height = height;
		this.block = new Block[height][width];
		nextPieceID = (int)(7*Math.random());
	}
	
	public void move(int userMove) {
		if (gameOver) return;

		if (this.piece==null) {
			
			int clearCount = 0;
			for (int i=0; i<this.height; i++)
				if (this.rowFull(i)==true) {
					removeRow(i);
					clearCount++;
				}
			lines+=clearCount;
			if (clearCount==1) score+=40*level;
			else if (clearCount==2) score+=100*level;
			else if (clearCount==3) score+=300*level;
			else if (clearCount==4) score+=1200*level;
			
			level = 1 + lines / 10;
			
			this.piece = Tetromino.newTetromino(this, 2, this.width/2, this.nextPieceID, this.nextOrientation);
			this.nextPieceID = (int) (Math.random()*7);
			this.nextOrientation = (int)(Math.random()*4);
			
			if (!this.piece.canPlace()) {
				this.gameOver = true;
				return;
			} else
				this.piece.add();
			
		} else {
			this.piece.remove();
			
			Tetromino d = this.piece;//.move(0, 0);
			
			if (userMove == USERKEY_L)
				d = this.piece.move(0, -1);
			else if (userMove == USERKEY_R)
				d = this.piece.move(0, 1);
			
			if (!d.canPlace() || userMove == USERKEY_D || userMove == USERKEY_DX)
				 d = this.piece.move(1, 0);
			
			if (userMove == USERKEY_U)
				d = this.piece.rotate();
			
			if (d==null) {
				//Nothing
			} else if (d.canPlace()) {
				this.piece = d;
				this.piece.add();
			} else {
				this.piece.add();
				if (userMove != USERKEY_U)
					this.piece = null;
			}
			
		}
		
		if (userMove == USERKEY_DX && this.piece!=null)
			move (USERKEY_D);
		
		
		
		
	}
	
	public Color getBlockColor(int x, int y) {
		return (this.block[x][y]==null) ? Color.BLACK : this.block[x][y].getColor();
	}
	
	public Color getPreviewColor(int x, int y) {
		Grid g = new Grid(5, 5);
		Tetromino t = Tetromino.newTetromino(g, 2, 2, this.nextPieceID, nextOrientation );
		t.add();
		return (g.block[x][y]==null) ? Color.BLACK : Color.RED;
	}
	
	public boolean blockIsFree(int i, int j) {
		if (i<0 || i>=this.block.length || j<0 || j>=this.block[i].length )
			return false;
		if (this.block[i][j]==null) return true;
		return false;
	}
	
	public void addBlock(Block b) {
		int x = b.getX();
		int y = b.getY();
		this.block[x][y] = b;
	}
	
	public void removeBlock(Block b) {
		int x = b.getX();
		int y = b.getY();
		this.block[x][y] = null;
	}
	
	public boolean rowFull(int rowNum) {
		for (int i=0; i<this.width; i++) {
			if (this.block[rowNum][i]==null) return false;
		}
		return true;
	}
	
	public void removeRow(int rowNum) {
		for (int j=rowNum; j>0; j--)
			for (int i=0; i<this.width; i++)
				this.block[j][i] = this.block[j-1][i];
		for (int i=0; i<this.width; i++)
			this.block[0][i] = null;
	}

	public int getLines() {
		return this.lines;
	}

	public int getScore() {
		return this.score;
	}

	public int getLevel() {
		return this.level;
	}

	public String getTime() {
		return "???";
	}
	
	
	
	
	

	
	
}