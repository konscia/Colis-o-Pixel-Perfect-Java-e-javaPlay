
package pixelPerfect;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaPlay.GameObject;

public class GameObjectImagePixelPerfect extends GameObject {

    private ImagemPixelPerfect img;

    public GameObjectImagePixelPerfect(String filename){
        try {
            this.img = new ImagemPixelPerfect(filename, this);
            this.largura = this.img.pegaLargura();
            this.altura = this.img.pegaAltura();
        } catch (Exception ex) {
            Logger.getLogger(GameObjectImagePixelPerfect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Point temColisao(GameObjectImagePixelPerfect outro){
        return this.img.checkCollision( outro.img );
    }

    @Override
    public void step(long timeElapsed) {}

    @Override
    public void draw(Graphics g) {
        this.img.draw(g, this.x, this.y);        
    }

    public void drawFlipped(Graphics g) {
        this.img.drawFlipped(g, this.x, this.y);        
    }

}
