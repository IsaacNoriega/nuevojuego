import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URL;

public class Game extends JPanel {

    //Sonido del juego
    URL directionSoundJump,directionSoundLose;
    //AudioClip soundJump,soundLose;

    //Enemigo,jugadore y fondo
    Player player = new Player(this);
    Enemy enemy = new Enemy(this);
    BackGround backGround = new BackGround(this);

    //Variables del juego
   public static boolean gameOver= false;
    public static boolean loseLives=false;
    public static int lives=3;
     public static int score=0;
    static int level=1;

    public Game()  {


        //Thread.sleep(clip.getMicrosecondLength() / 1000);
        directionSoundLose = getClass().getResource("/Multimedia/muerte.wav");
        //soundLose=Applet.newAudioClip(directionSoundLose);

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyPressed(KeyEvent e) {
                //El salto se activa al darle espacio
            if(e.getKeyCode()==KeyEvent.VK_SPACE){
                directionSoundJump = getClass().getResource("/Multimedia/salto.wav");
                //soundJump=Applet.newAudioClip(directionSoundJump);
                AudioInputStream audioInputStream = null;
                try {
                    audioInputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("/Multimedia/salto.wav"));
                } catch (UnsupportedAudioFileException s) {
                    throw new RuntimeException(s);
                } catch (IOException s) {
                    throw new RuntimeException(s);
                }
                Clip clip = null;
                try {
                    clip = AudioSystem.getClip();
                } catch (LineUnavailableException s) {
                    throw new RuntimeException(s);
                }
                try {
                    clip.open(audioInputStream);
                } catch (LineUnavailableException s) {
                    throw new RuntimeException(s);
                } catch (IOException s) {
                    throw new RuntimeException(s);
                }
                clip.start();
                player.KeyPressed(e);
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        setFocusable(true);
    }

    public void toMove(){
        enemy.toMove();
        player.toMove();
        backGround.toMOve();
    }

    @Override
    public void paintComponent(Graphics g){
       super.paintComponent(g);
       Graphics2D g2=(Graphics2D) g;
       draw(g2);
       drawScore(g2);
    }

    public void draw(Graphics2D g2){
        backGround.paint(g2);
        player.paint(g2);
        enemy.paint(g2);
        toMove();
    }

    public void drawScore(Graphics2D g2){
     Graphics2D g3=g2,g4=g2;
     Font scores= new Font("Arial",Font.BOLD,30);
     g2.setFont(scores);
     g2.setColor(Color.WHITE);
     g3.drawString("Score:"+ score,1120,30);
     g3.drawString("Level:"+ level,570,30);
     g3.drawString("Lives:"+ lives,20,30);

     if(gameOver){
         g4.setColor(Color.red);
         g4.drawString("GAME OVER",((float)getBounds().getCenterY()/2),100);
     }
    }
    public void GameOver(){
        directionSoundJump = getClass().getResource("/Multimedia/muerte.wav");
        //soundJump=Applet.newAudioClip(directionSoundJump);
        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("/Multimedia/muerte.wav"));
        } catch (UnsupportedAudioFileException s) {
            throw new RuntimeException(s);
        } catch (IOException s) {
            throw new RuntimeException(s);
        }
        Clip clip = null;
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException s) {
            throw new RuntimeException(s);
        }
        try {
            clip.open(audioInputStream);
        } catch (LineUnavailableException s) {
            throw new RuntimeException(s);
        } catch (IOException s) {
            throw new RuntimeException(s);
        }
        clip.start();
        gameOver=true;

    }

    public void LoseLives(){
        directionSoundJump = getClass().getResource("/Multimedia/muerte.wav");
        //soundJump=Applet.newAudioClip(directionSoundJump);
        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("/Multimedia/muerte.wav"));
        } catch (UnsupportedAudioFileException s) {
            throw new RuntimeException(s);
        } catch (IOException s) {
            throw new RuntimeException(s);
        }
        Clip clip = null;
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException s) {
            throw new RuntimeException(s);
        }
        try {
            clip.open(audioInputStream);
        } catch (LineUnavailableException s) {
            throw new RuntimeException(s);
        } catch (IOException s) {
            throw new RuntimeException(s);
        }
        clip.start();
        loseLives=true;
    }

}
