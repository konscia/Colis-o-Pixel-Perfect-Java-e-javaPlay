
package jogo;

import java.awt.Graphics;
import javaPlay.GameObject;
import javaPlay.Sprite;

public class ExplosaoFraca extends Explosao {

    public ExplosaoFraca(int x, int y){
        super(x - 32, y - 20);
        
        try {
            this.sprite = new Sprite("resources/explosao_fraca.png", 16, 64, 41);            
        } catch (Exception ex) {
            System.out.println("Falha ao carregar imagem: "+ex.getMessage());
        }        
    }

}
