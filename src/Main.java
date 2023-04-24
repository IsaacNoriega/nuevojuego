import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.IOException;
import java.security.Principal;
import java.util.logging.Level;
import java.util.logging.Logger;



public class Main {
    public static int restartGame=-1;
    public static void main(String[] args)  {
       //JOptionPane.showMessageDialog(null, "READY?");

        JFrame window = new JFrame("Juego estilo dinosaurio");
        Game game1 = new Game();
        //Propiedades de la ventana
        window.add(game1);
        window.setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        window.setLocation(Constants.WINDOW_POSITION_X, Constants.WINDOW_POSITION_Y);
        window.setVisible(true);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        while (true) {
            if(Game.gameOver){
                restartGame=JOptionPane.showConfirmDialog(null,"You lose , RESTART?","You lose",JOptionPane.YES_NO_OPTION);
                if (restartGame==0){
                    restartValues();
                }else if(restartGame==1){
                    System.exit(restartGame);
                }
            }else {
                game1.repaint();
                try {
                    Thread.sleep(12);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (Game.loseLives==true){
                    JOptionPane.showMessageDialog(null,"Carefull!!!");
                    Game.loseLives=false;
                    Game.lives--;
                    Player.y_init=310;
                    Player.jumping=false;
                    Enemy.x_init=1300;

                }
            }
        }
    }

    public static void restartValues(){
        Game.gameOver=false;
        Enemy.x_aux=-4;
        Game.score=0;
        Game.lives=3;
        Game.level=1;
        restartGame=-1;
        Enemy.x_init=1300;

    }
}