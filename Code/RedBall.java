import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;





public class RedBall extends JComponent implements ActionListener, MouseMotionListener, KeyListener {

    private int ballx = 150;
    private int bally = 30;
    private int ballx1 = 100;
    private int bally1 = 10;
    private int paddlex = 0;
    private int ballySpeed = 7;
    private int ballxSpeed = 5;
    private int bally1Speed = 14;
    private int ballx1Speed = 10;
    public int score = 0;
    public int score1 = 0;
    private int scorefinal;
    public int bestscore;
    public int bestscore1;
    public boolean gameOver, started;
   
    public static int columns = 25;
    public static int rows = 15;

    public static String[][] stringArray = new String[columns][rows];

    public ImageIcon ii = new ImageIcon("src/smalltree.jpg"); 
    public ImageIcon squirreljpg = new ImageIcon("src/squirrel.jpg");

    public static int xstart = 15;
    public static int ystart = 7;

    public static void main(String[] args) {

        //set walls!!
        for (int i = 0; i < rows; i++){
            stringArray[0][i] = Object.TREE;
        }
        for (int i = 0; i < rows; i++){
            stringArray[columns-1][i] = Object.TREE;
        }
        for (int i = 0; i < columns; i++){
            stringArray[i][0] = Object.TREE;
        }
        for (int i = 0; i < columns; i++){
            stringArray[i][rows-1] = Object.TREE;
        }
        
        //starting position
        stringArray[xstart][ystart] = "Squirrel";



        

        //left wall

        //right wall

        //bottom wall

        JFrame wind = new JFrame("RedBall/GamePinfo");
        RedBall g = new RedBall();
        wind.add(g);
        wind.pack();
        wind.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        wind.setLocationRelativeTo(null);
        wind.setVisible(true);
        wind.addMouseMotionListener(g);

        Timer tt = new Timer(17, g);
        tt.start();

    }

    public void newball(int ballx, int bally, int ballxspeed, int ballyspeed) {

        ballx = 150;
        bally = 30;
        ballxspeed = 5;
        ballyspeed = 7;
        JOptionPane.showMessageDialog(null, "new ball !");

        return;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1500, 900);
    }

    @Override
    protected void paintComponent(Graphics g) {

        //draw the sky

        for (int x = 0; x < columns; x ++){
            for (int y = 0; y < rows; y++){
                if (stringArray[x][y] == Object.TREE){
                    g.drawImage(ii.getImage(), x*60, y*60, this);
                }
                if (stringArray[x][y] == "Squirrel"){
                    g.drawImage(squirreljpg.getImage(), x*60, y*60, this);
                }
            }
        }
    
        //Toolkit.getDefaultToolkit().sync();

        //draw the ball_1
        if (score >= 5) {
            g.setColor(Color.BLACK);
            g.fillOval(ballx1, bally1, 20, 20);

        }
        //score 
        if (score >= 5) {
            g.setColor(Color.red);
            g.setFont(new Font("Arial", 8, 50));
            g.drawString(String.valueOf(score + score1), 30 / 1 - 15, 80);
        } else {
            g.setColor(Color.white);
            g.setFont(new Font("Arial", 8, 50));
            g.drawString(String.valueOf(score), 30 / 1 - 15, 80);
        }
        // start && gameOver
        g.setColor(Color.white);
        g.setFont(new Font("Arial", 8, 50));

        if (gameOver) {
            g.drawString(String.valueOf(" Best Score :" + scorefinal), 50 / 1 - 15, 200);
        }
    }


    //repeatedly called by the Timer tt
    @Override
    public void actionPerformed(ActionEvent e) {

        //implement enenemy movement
        //System.out.printf("%s\n", e)

        //if (e.getKeyChar() == 'w'){
        //stringArray[xstart][ystart++] = "Squirrel";
        //}
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

        paddlex = e.getX() - 50;
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.printf("%s\n", e.getKeyChar());

        if (e.getKeyChar() == 'w'){
            stringArray[xstart][ystart++] = "Squirrel";
        }
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //check movement
        System.out.printf("%s\n", e.getKeyChar());
        
        if (e.getKeyCode() == e.VK_W){
            stringArray[xstart][ystart++] = "Squirrel";
        }
        repaint();

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
