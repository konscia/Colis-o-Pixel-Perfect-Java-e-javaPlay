/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jogo;

import pixelPerfect.GameObjectImagePixelPerfect;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaPlay.GameEngine;
import javaPlay.GameObject;
import javaPlay.GameStateController;
import javaPlay.Keyboard;
import javaPlayExtras.Imagem;
import pixelPerfect.ImagemPixelPerfect;
import javaPlayExtras.Keys;

/**
 *
 * @author KALEU
 */
public class Fase implements GameStateController {

    GameObjectImagePixelPerfect vegeta;
    GameObjectImagePixelPerfect ryu;
    Explosao explosao;
    

    public void load() {        
        this.vegeta = new GameObjectImagePixelPerfect("resources/vegeta.png");
        this.ryu = new GameObjectImagePixelPerfect("resources/ryu.png");
        this.ryu.setX(200);
        this.ryu.setY(200);
    }

    public void unload() {}

    public void start() {  }

    public void step(long timeElapsed) {
        Keyboard k = GameEngine.getInstance().getKeyboard();
        if(k.keyDown(Keys.BAIXO)){
            this.vegeta.setY( this.vegeta.getY() + 5);
        }

        if(k.keyDown(Keys.CIMA)){
            this.vegeta.setY( this.vegeta.getY() - 5);
        }

        if(k.keyDown(Keys.DIREITA)){
            this.vegeta.setX( this.vegeta.getX() + 5);
        }

        if(k.keyDown(Keys.ESQUERDA)){
            this.vegeta.setX( this.vegeta.getX() - 5);
        }

        Point colisao = this.vegeta.temColisao( this.ryu );
        if(colisao != null){

            if(this.explosao != null && this.explosao.isActive()){
                //Já tem uma explosao ativa, não faz nada
            } else {
                //Nenhuma explosao ativa, cria uma nova.
                this.explosao = new ExplosaoFraca(colisao.x, colisao.y);
            }
            
        }

        if(this.explosao != null){
            this.explosao.step(timeElapsed);
        }
    }

    public void draw(Graphics g) {
        g.fillRect(0,0,800,600);
        this.vegeta.draw(g);
        this.ryu.draw(g);
        
        if(this.explosao != null){
            this.explosao.draw(g);
        }
    }

    public void stop() {
        
    }

}
