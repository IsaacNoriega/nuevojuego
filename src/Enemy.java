import javax.swing.*;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class Enemy {
    Game game;

    // Variables de la Hitbox
    Area head, feets, enemy;
    // Variables para la dimension del personaje
    int widthEnemy = 38, heightEnemy = 38;
    // Coordenadas iniciales del enemigo
    static int x_init = 1300, y_init = 340;
    // Coordenadas para manipular al enemigo
    static int x_aux = -4;

    public Enemy(Game game){
        this.game = game;
    }

    public void toMove(){
        if (x_init <= 100) {
            Game.score++;
            x_init = Constants.WINDOW_WIDTH;
            if (Game.score == 3 | Game.score == 6 | Game.score == 9 | Game.score == 12) {
                x_aux += -2;
                Game.level++;
            }
        } else {
            if (colision()) {
                if (Game.lives == 0) {
                    game.GameOver();
                } else {
                    game.LoseLives();
                }
            } else {
                x_init += x_aux;
            }
        }
    }

    public void paint(Graphics2D g2){
        ImageIcon enemy = new ImageIcon(getClass().getResource("/Multimedia/goomba.gif"));
        g2.drawImage(enemy.getImage(), x_init, y_init, widthEnemy, heightEnemy, null);
    }

    public Area getBounds(){
        Ellipse2D forma1 = new Ellipse2D.Double(x_init, y_init, widthEnemy, heightEnemy);
        enemy = new Area(forma1);
        return enemy;
    }

    public boolean colision(){
        Area areaA = new Area(game.player.getBounds());
        areaA.intersect(getBounds());
        return !areaA.isEmpty();
    }
}