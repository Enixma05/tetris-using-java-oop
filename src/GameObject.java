import java.awt.Graphics;

public abstract class GameObject {
    protected int x;
    protected int y;

    public int getX() { return x; }
    public int getY() { return y; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }

    public abstract void update(long deltaMs);
    public abstract void draw(Graphics g, int originX, int originY, int cellW, int cellH);
}
