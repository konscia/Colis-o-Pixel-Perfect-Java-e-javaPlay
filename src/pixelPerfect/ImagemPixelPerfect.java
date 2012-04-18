package pixelPerfect;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import javaPlay.GameObject;
import javaPlayExtras.Imagem;
import javax.imageio.ImageIO;

public class ImagemPixelPerfect extends Imagem {

    GameObject go;
    int count = 0;

    public ImagemPixelPerfect(String filename, GameObject obj) throws Exception {
        super(filename);
        this.go = obj;
    }

    // returns a HashSet of strings that list all the pixels in an image that aren't transparent
    // the pixels contained in the HashSet follow the guideline of:
    // x,y where x is the absolute x position of the pixel and y is the absolute y position of the pixel
    public HashSet<String> getMask() {

        HashSet<String> mask = new HashSet<String>();
        BufferedImage image = null;

        try {
            image = ImageIO.read(new File ( this.filename ));
         } catch (IOException e) {
            System.out.println("error");
         }
       
        int pixel, a;

        for (int i = 0; i < image.getWidth(); i++) { // for every (x,y) component in the given box,
            for (int j = 0; j < image.getHeight(); j++) {
                pixel = image.getRGB(i, j); // get the RGB value of the pixel
                a = (pixel >> 24) & 0xff;
                if (a != 0) { // if the alpha is not 0, it must be something other than transparent
                    mask.add((go.getX() + i) + "," + (go.getY() + j)); // add the absolute x and absolute y coordinates to our set
                }
            }
        }
        return mask; //return our set
    }
/*
    public ArrayList<Point> getMaskList() {

        ArrayList<Point> mask = new ArrayList<Point>();
        BufferedImage image = null;

        try {
            image = ImageIO.read(new File ( this.filename ));
         } catch (IOException e) {
            System.out.println("error");
         }

        int pixel, a;

        for (int i = 0; i < image.getWidth(); i++) { // for every (x,y) component in the given box,
            for (int j = 0; j < image.getHeight(); j++) {
                pixel = image.getRGB(i, j); // get the RGB value of the pixel
                a = (pixel >> 24) & 0xff;
                if (a != 0) { // if the alpha is not 0, it must be something other than transparent
                    mask.add( new Point(go.getX() + i, go.getY() + j) ); // add the absolute x and absolute y coordinates to our set
                }
            }
        }
        return mask; //return our set
    }

    public void drawDebug(Graphics g){
         ArrayList<Point> maskPlayer1 = this.getMaskList();
         for(Point p : maskPlayer1){
             g.drawOval(p.x, p.y, 1, 1);
         }
    }*/

    // Returns true if there is a collision between object a and object b
    public Point checkCollision(ImagemPixelPerfect img) {

        int ax1 = this.go.getX();
        int ay1 = this.go.getY();

        int ax2 = ax1 + this.go.getLargura();
        int ay2 = ay1 + this.go.getAltura();

        int bx1 = img.go.getX();
        int by1 = img.go.getY();

        int bx2 = img.go.getX()+img.go.getLargura();
        int by2 = img.go.getY()+img.go.getAltura();

        if (by2 < ay1 || ay2 < by1 || bx2 < ax1 || ax2 < bx1) {
            return null; // Collision is impossible.
        } else // Collision is possible.
        { // get the masks for both images

            HashSet<String> maskPlayer1 = this.getMask();
            
            HashSet<String> maskPlayer2 = img.getMask();

            maskPlayer1.retainAll(maskPlayer2); // Check to see if any pixels in maskPlayer2 are the same as those in maskPlayer1

            if (maskPlayer1.size() > 0) { // if so, than there exists at least one pixel that is the same in both images, thus                                
                for(String s : maskPlayer1){
                    String[] itens = s.split(",");
                    Point p = new Point(Integer.parseInt(itens[0]), Integer.parseInt(itens[1]));                    
                    return p;
                }

            }
        }
        return null;
    }
}
