import java.awt.Color;
import java.awt.Point;

public class TetrominoPieces {

    private static void rotatePointsRight(Point[] pts) {
        for (Point p : pts) {
            int oldX = p.x;
            p.x = -p.y;
            p.y = oldX;
        }
    }

    private static void rotatePointsLeft(Point[] pts) {
        for (Point p : pts) {
            int oldX = p.x;
            p.x = p.y;
            p.y = -oldX;
        }
    }

    public static class LinePiece extends Tetromino {
        public LinePiece() {
            color = new Color(102, 204, 204);
            blocks[0] = new Point(0, -1);
            blocks[1] = new Point(0, 0);
            blocks[2] = new Point(0, 1);
            blocks[3] = new Point(0, 2);
        }
        @Override public void rotateLeft() { rotatePointsLeft(blocks); }
        @Override public void rotateRight() { rotatePointsRight(blocks); }
        @Override public Tetromino clonePiece() {
            LinePiece c = new LinePiece();
            for (int i = 0; i < 4; i++) c.blocks[i] = new Point(this.blocks[i]);
            return c;
        }
    }

    public static class SquarePiece extends Tetromino {
        public SquarePiece() {
            color = new Color(204, 102, 102);
            blocks[0] = new Point(0, 0);
            blocks[1] = new Point(1, 0);
            blocks[2] = new Point(0, 1);
            blocks[3] = new Point(1, 1);
        }
        @Override public void rotateLeft() { }
        @Override public void rotateRight() { }
        @Override public Tetromino clonePiece() {
            SquarePiece c = new SquarePiece();
            for (int i = 0; i < 4; i++) c.blocks[i] = new Point(this.blocks[i]);
            return c;
        }
    }

    public static class ZPiece extends Tetromino {
        public ZPiece() {
            color = new Color(102, 204, 102);
            blocks[0] = new Point(-1, 0);
            blocks[1] = new Point(0, 0);
            blocks[2] = new Point(0, 1);
            blocks[3] = new Point(1, 1);
        }
        @Override public void rotateLeft() { rotatePointsLeft(blocks); }
        @Override public void rotateRight() { rotatePointsRight(blocks); }
        @Override public Tetromino clonePiece() {
            ZPiece c = new ZPiece();
            for (int i = 0; i < 4; i++) c.blocks[i] = new Point(this.blocks[i]);
            return c;
        }
    }

    public static class SPiece extends Tetromino {
        public SPiece() {
            color = new Color(204, 204, 102);
            blocks[0] = new Point(1, 0);
            blocks[1] = new Point(0, 0);
            blocks[2] = new Point(0, 1);
            blocks[3] = new Point(-1, 1);
        }
        @Override public void rotateLeft() { rotatePointsLeft(blocks); }
        @Override public void rotateRight() { rotatePointsRight(blocks); }
        @Override public Tetromino clonePiece() {
            SPiece c = new SPiece();
            for (int i = 0; i < 4; i++) c.blocks[i] = new Point(this.blocks[i]);
            return c;
        }
    }

    public static class LPiece extends Tetromino {
        public LPiece() {
            color = new Color(204, 102, 204);
            blocks[0] = new Point(0, -1);
            blocks[1] = new Point(0, 0);
            blocks[2] = new Point(0, 1);
            blocks[3] = new Point(1, 1);
        }
        @Override public void rotateLeft() { rotatePointsLeft(blocks); }
        @Override public void rotateRight() { rotatePointsRight(blocks); }
        @Override public Tetromino clonePiece() {
            LPiece c = new LPiece();
            for (int i = 0; i < 4; i++) c.blocks[i] = new Point(this.blocks[i]);
            return c;
        }
    }

    public static class JPiece extends Tetromino {
        public JPiece() {
            color = new Color(218, 170, 0);
            blocks[0] = new Point(0, -1);
            blocks[1] = new Point(0, 0);
            blocks[2] = new Point(0, 1);
            blocks[3] = new Point(-1, 1);
        }
        @Override public void rotateLeft() { rotatePointsLeft(blocks); }
        @Override public void rotateRight() { rotatePointsRight(blocks); }
        @Override public Tetromino clonePiece() {
            JPiece c = new JPiece();
            for (int i = 0; i < 4; i++) c.blocks[i] = new Point(this.blocks[i]);
            return c;
        }
    }

    public static class TPiece extends Tetromino {
        public TPiece() {
            color = new Color(102, 102, 204);
            blocks[0] = new Point(-1, 0);
            blocks[1] = new Point(0, 0);
            blocks[2] = new Point(1, 0);
            blocks[3] = new Point(0, 1);
        }
        @Override public void rotateLeft() { rotatePointsLeft(blocks); }
        @Override public void rotateRight() { rotatePointsRight(blocks); }
        @Override public Tetromino clonePiece() {
            TPiece c = new TPiece();
            for (int i = 0; i < 4; i++) c.blocks[i] = new Point(this.blocks[i]);
            return c;
        }
    }
}
