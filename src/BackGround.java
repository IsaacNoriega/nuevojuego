import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class BackGround {
    Game game;
    Window window;

    //Coordenadas iniciales para mover el FONDO
    static int x1 = 1300, y1 = 0;
    //Cordenadas para mover otro fondo
    int x2 = 0, y2 = 0;
    public BackGround(Game game) {
        this.game = game;
    }

    public void toMOve() {
        x1 -= 2;
        x2 -= 2;
        if (x1 == 0 && x2 == -1300) {
            x1 = 1300;
            x2 = 0;
        }
    }

    public void paint(Graphics2D g2) {
        ImageIcon backGround = new ImageIcon(Objects.requireNonNull(getClass().getResource(Window.background)));
        g2.drawImage(backGround.getImage(), x1, y1, Window.width, Window.height, null);
        g2.drawImage(backGround.getImage(), x2, y2, Window.width, Window.height, null);

    }


}


