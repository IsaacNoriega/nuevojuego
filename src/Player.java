import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Area;

public class Player {
    Game game;

    // Variables para el estado del jugador
    static boolean jumping = false; // Variable que indica si el jugador está saltando o no
    boolean up = false, down = false; // Variables que indican si el jugador está subiendo o bajando durante un salto

    // Variables para la Hitbox del jugador
    Area leftleg, rightLeg, torso, head, player; // Variables que representan las diferentes partes del cuerpo del jugador para la colisión
    // Variables para las dimensiones del Jugador
    int widthPlayer = 100; // Ancho del jugador
    int heightPlayer = 68; // Alto del jugador
    // Coordenadas iniciales del personaje
    static int x_init = 50, y_init = 310; // Coordenadas iniciales del jugador
    // Coordenadas para manipular al personaje
    int x_aux = 0, y_aux = 0; // Variables que se usan para mover al jugador en el eje X y Y

    public Player(Game game) {
        this.game = game;
    }

    public void toMove() { // Método que se usa para mover al jugador en el eje X y Y
        if (x_init + x_aux > 0 && x_init + x_aux < game.getWidth() - widthPlayer) { // Si el jugador no se sale del área de juego
            x_init += x_aux; // Mueve el jugador en el eje X
        }
        if (jumping) { // Si el jugador está saltando
            if (y_init == 310) { // Si el jugador está en el suelo
                up = true; // El jugador está subiendo
                y_aux = -2; // Mueve al jugador hacia arriba en el eje Y
                down = false;
            }
            if (y_init == 200) { // Si el jugador ha alcanzado el límite de salto
                down = true; // El jugador está bajando
                y_aux = 2; // Mueve al jugador hacia abajo en el eje Y
                up = false;
            }
            if (up) { // Si el jugador está subiendo
                y_init += y_aux; // Mueve al jugador hacia arriba en el eje Y
            }
            if (down) { // Si el jugador está bajando
                y_init += y_aux; // Mueve al jugador hacia abajo en el eje Y
                if (y_init == 310) { // Si el jugador ha aterrizado en el suelo después de saltar
                    jumping = false; // El jugador ya no está saltando
                }
            }
        } else {
            x_init += x_aux; // Si el jugador no está saltando, solo se mueve en el eje X
        }
    }
    public void paint(Graphics2D g2) {
        ImageIcon player = new ImageIcon(getClass().getResource(Window.player));
        g2.drawImage(player.getImage(), x_init, y_init, widthPlayer, heightPlayer, null);
    }

    public void KeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            jumping = true; // Si se presiona la tecla de espacio, el jugador comienza a saltar
        }
    }

    public Area getBounds() {
        Rectangle forma1 = new Rectangle(x_init, y_init, widthPlayer, heightPlayer);
        player = new Area(forma1); // Se crea un objeto Area que representa los límites de la Hitbox del jugador
        return player;
    }
}