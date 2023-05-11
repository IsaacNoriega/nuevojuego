import javax.swing.*;
import java.security.Principal;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Window extends JFrame {
    private Game game1;
    protected static int width, height; //Variables para la anchira y altura de la ventana
    private int x, y; //Variables para la posicion de la ventana
    protected static String gameName, background, player, enemy, jump, lose;

    //Este constructor define la ventana del juego con valores por defecto.
    public Window() {
        //Titulo default del juego
        super("Juego estilo dinosaurio");
        JOptionPane.showMessageDialog(null, "READY?");
        //Setters de valores defaults
        setWidth(Constants.WINDOW_WIDTH);
        setHeight(Constants.WINDOW_HEIGHT);
        setX(Constants.WINDOW_POSITION_X);
        setY(Constants.WINDOW_POSITION_Y);
        setPlayer(Constants.PLAYER);
        setEnemy(Constants.ENEMY);
        setBackGround(Constants.BACKGROUND);
        setJump(Constants.JUMP_SOUND);
        setLose(Constants.LOSE_SOUND);
        game1 = new Game();
        //Propiedades de la ventana
        add(game1);
        setSize(width, height);
        setLocation(x, y);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //Constructor que permite personalizar valores de la ventana del juego, como el nombre, tamaño, posición y sonidos.
    public Window(String name, int width, int height, int x, int y, String player,
                  String enemy, String background, String jumpSound, String loseSound) {
        super(name);
        JOptionPane.showMessageDialog(null, "READY?");
        setGameName(name);
        setWidth(width);
        setHeight(height);
        setX(x);
        setY(y);
        setPlayer(player);
        setEnemy(enemy);
        setBackGround(background);
        setJump(jumpSound);
        setLose(loseSound);
        game1 = new Game();
        add(game1);
        //Propiedades de la ventana
        setSize(Window.width, Window.height);
        setLocation(this.x, this.y);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    //Setters
    public void setGameName(String gameName) {
        gameName = gameName;
    }
    public void setWidth(int width) {
        Window.width = width;
    }
    public void setHeight(int height) {
        Window.height = height;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setBackGround(String url) {
        background = url;
    }
    public void setEnemy(String url) {
        enemy = url;
    }
    public void setPlayer(String url) {
        player = url;
    }
    public void setJump(String url) {
        jump = url;
    }
    public void setLose(String url) {
        lose = url;
    }

    //Metodo run , es lo que inicializara y manejara la logica de todo el juego
    public void run() {
        while (true) {
            if (Game.gameOver) {
                Game.restartGame = JOptionPane.showConfirmDialog(null, "You lose, RESTART?", "You lose", JOptionPane.YES_NO_OPTION);
                if (Game.restartGame == 0) {
                    Game.restartValues();
                } else if (Game.restartGame == 1) {
                    System.exit(Game.restartGame);
                }
            } else {
                game1.repaint();
                try {
                    Thread.sleep(12);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (Game.loseLives == true) {
                    JOptionPane.showMessageDialog(null, "Carefull!!!");
                    Game.loseLives = false;
                    Game.lives--;
                    Player.y_init = 310;
                    Player.jumping = false;
                    Enemy.x_init = width;
                }
            }
        }
    }
}