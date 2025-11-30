import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public abstract class Tetromino extends GameObject implements Rotatable {
    protected Point[] blocks = new Point[4];
    protected Color color;

    public Tetromino() {
        for (int i = 0; i < 4; i++) {
            blocks[i] = new Point(0, 0);
        }
        this.color = Color.BLACK;
    }

    public Point[] getBlocks() {
        Point[] copy = new Point[4];
        for (int i = 0; i < 4; i++) {
            copy[i] = new Point(blocks[i]);
        }
        return copy;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public void update(long deltaMs) {
        // movement diatur Board
    }

    @Override
    public void draw(Graphics g, int originX, int originY, int cellW, int cellH) {
        g.setColor(color);
        for (Point p : blocks) {
            int drawX = originX + (x + p.x) * cellW;
            int drawY = originY + (y - p.y) * cellH;

            g.fillRect(drawX + 1, drawY + 1, cellW - 2, cellH - 2);

            g.setColor(color.brighter());
            g.drawLine(drawX, drawY + cellH - 1, drawX, drawY);
            g.drawLine(drawX, drawY, drawX + cellW - 1, drawY);

            g.setColor(color.darker());
            g.drawLine(drawX + 1, drawY + cellH - 1,
                       drawX + cellW - 1, drawY + cellH - 1);
            g.drawLine(drawX + cellW - 1, drawY + cellH - 1,
                       drawX + cellW - 1, drawY + 1);

            g.setColor(color);
        }
    }

    public abstract Tetromino clonePiece();
}
