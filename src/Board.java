import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.*;

public class Board extends JPanel {

    private static final long serialVersionUID = 1L;

    private final int BoardWidth = 10;
    private final int BoardHeight = 22;

    private Color[][] grid;
    private Tetromino currentPiece;
    private int curX, curY;

    private boolean isStarted = false;
    private boolean isPaused = false;
    private boolean isGameOver = false;

    private int score = 0;
    private int dropIntervalMs = 600;

    private Thread gameThread;
    private volatile boolean running = false;

    private final Random random = new Random();
    private final String username;
    private final TetrisFrame frame;

    public Board(String username, TetrisFrame frame) {
        this.username = username;
        this.frame = frame;

        setFocusable(true);
        grid = new Color[BoardHeight][BoardWidth];
        clearBoard();
        addKeyListener(new TAdapter());
    }

    public void start() {
        if (isStarted) return;

        requestFocusInWindow();

        isStarted = true;
        isGameOver = false;
        score = 0;
        dropIntervalMs = 600;
        clearBoard();
        spawnNewPiece();
        startGameLoop();
    }

    private void startGameLoop() {
        running = true;
        gameThread = new Thread(() -> {
            long last = System.currentTimeMillis();
            long acc = 0;

            while (running) {
                long now = System.currentTimeMillis();
                long delta = now - last;
                last = now;
                acc += delta;

                if (!isPaused && !isGameOver && acc >= dropIntervalMs) {
                    acc = 0;
                    oneLineDown();
                    repaint();
                }

                try { Thread.sleep(15); }
                catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            }
        }, "GameLoopThread");

        gameThread.start();
    }

    private void clearBoard() {
        for (int r = 0; r < BoardHeight; r++)
            for (int c = 0; c < BoardWidth; c++)
                grid[r][c] = null;
    }

    private Tetromino randomPiece() {
        switch (random.nextInt(7)) {
            case 0: return new TetrominoPieces.LinePiece();
            case 1: return new TetrominoPieces.SquarePiece();
            case 2: return new TetrominoPieces.ZPiece();
            case 3: return new TetrominoPieces.SPiece();
            case 4: return new TetrominoPieces.LPiece();
            case 5: return new TetrominoPieces.JPiece();
            default: return new TetrominoPieces.TPiece();
        }
    }

    private void spawnNewPiece() {
        currentPiece = randomPiece();
        curX = BoardWidth / 2;
        curY = 1;
        currentPiece.setX(curX);
        currentPiece.setY(curY);

        if (!canPlace(currentPiece, curX, curY))
            gameOver();
    }

    private boolean canPlace(Tetromino piece, int px, int py) {
        for (Point p : piece.getBlocks()) {
            int tileX = px + p.x;
            int tileY = py + p.y;

            if (tileX < 0 || tileX >= BoardWidth) return false;
            if (tileY < 0 || tileY >= BoardHeight) return false;
            if (grid[tileY][tileX] != null) return false;
        }
        return true;
    }

    private boolean tryMove(Tetromino piece, int newX, int newY) {
        if (!canPlace(piece, newX, newY)) return false;
        piece.setX(newX);
        piece.setY(newY);
        curX = newX;
        curY = newY;
        return true;
    }

    private boolean tryRotate(boolean right) {
        Tetromino temp = currentPiece.clonePiece();

        if (right) temp.rotateRight();
        else temp.rotateLeft();

        if (!canPlace(temp, currentPiece.getX(), currentPiece.getY())) return false;

        if (right) currentPiece.rotateRight();
        else currentPiece.rotateLeft();
        return true;
    }

    private void oneLineDown() {
        if (!tryMove(currentPiece, curX, curY + 1))
            placePiece();
    }

    private void dropDown() {
        while (tryMove(currentPiece, curX, curY + 1)) {}
        placePiece();
    }

    private void placePiece() {
        for (Point p : currentPiece.getBlocks()) {
            int gx = curX + p.x;
            int gy = curY + p.y;
            if (gy >= 0 && gy < BoardHeight && gx >= 0 && gx < BoardWidth)
                grid[gy][gx] = currentPiece.getColor();
        }
        clearFullLines();
        spawnNewPiece();
    }

    private void clearFullLines() {
        int lines = 0;

        for (int r = BoardHeight - 1; r >= 0; r--) {
            boolean full = true;
            for (int c = 0; c < BoardWidth; c++)
                if (grid[r][c] == null) { full = false; break; }

            if (full) {
                lines++;
                for (int rr = r; rr > 0; rr--)
                    System.arraycopy(grid[rr - 1], 0, grid[rr], 0, BoardWidth);
                for (int cc = 0; cc < BoardWidth; cc++)
                    grid[0][cc] = null;
                r++;
            }
        }

        if (lines > 0) {
            score += lines * 100;
            dropIntervalMs = Math.max(100, dropIntervalMs - lines * 10);
        }
    }

    private void gameOver() {
        isGameOver = true;
        running = false;
        KoneksiDatabase.saveScore(username, score);

        JOptionPane.showMessageDialog(this,
                "Game Over!\nPlayer: " + username + "\nScore: " + score,
                "Game Over", JOptionPane.INFORMATION_MESSAGE);

        frame.backToMenu();
    }

    @Override
        public void paint(Graphics g) {
        super.paint(g);
        Dimension size = getSize();

        int hudSpace = 50;
        int availableHeight = size.height - hudSpace;

        int cellSize = availableHeight / BoardHeight;
        int cellW = cellSize;
        int cellH = cellSize;

        int boardPixelWidth = cellW * BoardWidth;

        int offsetX = (size.width - boardPixelWidth) / 2;
        // offsetX = Math.max(offsetX, 5); // kurangi gap kanan kiri agar tidak terlalu besar
        int offsetY = hudSpace;

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, size.width, size.height);

        // Draw stored grid
        for (int r = 0; r < BoardHeight; r++) {
            for (int c = 0; c < BoardWidth; c++) {
                Color col = grid[r][c];
                if (col != null) {
                    int drawX = offsetX + c * cellW;
                    int drawY = offsetY + r * cellH;

                    g.setColor(col);
                    g.fillRect(drawX + 1, drawY + 1, cellW - 2, cellH - 2);

                    g.setColor(col.brighter());
                    g.drawLine(drawX, drawY + cellH - 1, drawX, drawY);
                    g.drawLine(drawX, drawY, drawX + cellW - 1, drawY);

                    g.setColor(col.darker());
                    g.drawLine(drawX + 1, drawY + cellH - 1,
                               drawX + cellW - 1, drawY + cellH - 1);
                    g.drawLine(drawX + cellW - 1, drawY + cellH - 1,
                               drawX + cellW - 1, drawY + 1);
                }
            }
        }

        if (currentPiece != null && !isGameOver)
            currentPiece.draw(g, offsetX, offsetY, cellW, cellH);

        // ---- HUD TEXT ----
        g.setColor(Color.WHITE);
        g.setFont(new Font("Segoe UI", Font.BOLD, 16));
        g.drawString("Player: " + username, 10, 20);
        g.drawString("Score: " + score, 10, 40);
        if (isPaused)
            g.drawString("PAUSED", getWidth() - 90, 20);
    }

    private class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (!isStarted || isGameOver) return;

            int key = e.getKeyCode();

            if (key == KeyEvent.VK_P) {
                isPaused = !isPaused;
                repaint();
                return;
            }

            if (isPaused) return;

            switch (key) {
                case KeyEvent.VK_LEFT: tryMove(currentPiece, curX - 1, curY); break;
                case KeyEvent.VK_RIGHT: tryMove(currentPiece, curX + 1, curY); break;
                case KeyEvent.VK_UP: tryRotate(false); break;
                case KeyEvent.VK_DOWN: tryRotate(true); break;
                case KeyEvent.VK_SPACE: dropDown(); break;
                case KeyEvent.VK_D: oneLineDown(); break;
            }
            repaint();
        }
    }
}
