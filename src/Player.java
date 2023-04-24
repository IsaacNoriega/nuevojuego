import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Objects;

public class Player {
    Game game;

    //Variables para el estado del jugador
    static boolean jumping=false;
    boolean up=false,down=false;

    //Variables para la Hitbox del jugador
    Area leftleg,rightLeg,torso,head,player;
    //Variables para las dimenciones del Jugador
    int widthPlayer=100;
    int heightPlayer=68;
    //Coordenadas iniciales del personajes
    static int x_init=50,y_init=310;
    //Coordenadas para manipular al personaje
    int x_aux=0,y_aux=0;

    public Player(Game game){
        this.game=game;
    }

    public void toMove(){
        if(x_init+x_aux > 0 && x_init+x_aux < game.getWidth()-widthPlayer ){
            x_init+=x_aux;
        }
        if(jumping){
            if(y_init==310){//Si el auto esta en el suelo
                up=true;
                y_aux=-2;
                down=false;
            }
            if(y_init==200){//Limite de salto -
                down=true;
                y_aux=2;
                up=false;
            }
            if(up){
                y_init+=y_aux;
            }
            if(down){
                y_init+=y_aux;
                if(y_init==310){//Si el auto llego al suelo
                    jumping=false;
                }
            }
        }
        {
            x_init+=x_aux;
        }
    }
    public void paint(Graphics2D g2){
         ImageIcon player=new ImageIcon(getClass().getResource("/Multimedia/marrio.gif"));
        g2.drawImage(player.getImage(),x_init,y_init,widthPlayer,heightPlayer,null);

    }

    public void KeyPressed(KeyEvent e){
        if(e.getKeyCode()==KeyEvent.VK_SPACE){
            jumping=true;
        }
    }

    public Area getBounds(){
        Rectangle forma1 = new Rectangle(x_init,y_init,widthPlayer,heightPlayer);
        player=new Area(forma1);
        return player;

    }


}
