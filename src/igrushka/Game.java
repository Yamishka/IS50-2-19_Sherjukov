/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package igrushka;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.JPanel;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Graphics;
/**
 *
 * @author mikha
 */
public class Game extends JFrame {
    public static Game game_window;
    public static Image background;
    public static Image game_over;
    public static Image drop;
    public static long last_frame_time;
    public static float drop_left = 200;
    public static float drop_top = 100;
    public static float drop_v = 200;
    public static int score = 0;
    //sherjukov
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        background = ImageIO.read(Game.class.getResourceAsStream("background.jpg"));
        game_over = ImageIO.read(Game.class.getResourceAsStream("end.png"));
        drop = ImageIO.read(Game.class.getResourceAsStream("object.png"));
        game_window = new Game();
        game_window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        game_window.setLocation(200, 50);
        game_window.setSize(900, 600);
        game_window.setResizable(false);
        last_frame_time = System.nanoTime();
        //sherjukov
        GameField game_field = new GameField();
        game_field.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            float drop_right = drop_left + drop.getWidth(null);
            float drop_bottom = drop_top + drop.getHeight(null);
            boolean is_drop = x >= drop_left && x <= drop_right && y >= drop_top && y <= drop_bottom;  
             if (is_drop) {
                drop_top = (game_field.getHeight() + drop.getHeight(null)); 
                //drop_left = (int) (Math.random() * (game_field.getWidth() - drop.getWidth(null))); 
                //sherjukov
                drop_v = drop_v + 10; 
                score++; 
                game_window.setTitle("Score: " + score);
                }
                }
                });
        game_window.add(game_field); 
        game_window.setVisible(true);
    }
  public static void onRepaint(Graphics g) {
        long current_time = System.nanoTime();
        last_frame_time = current_time;
        float delt_time = (current_time - last_frame_time) * 0.000000001f;
        drop_top = drop_top + drop_v * delt_time;
        g.drawImage(background, 0, 0, null); 
        g.drawImage(drop, (int) drop_left, (int) drop_top, null); 
        if(drop_top > game_window.getHeight()) {
         g.drawImage(game_over, 210, 150, null);} 
        else{
        
        }
        //sherjukov
    }
    private static class GameField extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            onRepaint(g);
            repaint();
            //sherjukov
        }
    }
}