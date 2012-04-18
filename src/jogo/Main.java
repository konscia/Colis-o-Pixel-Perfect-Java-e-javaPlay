
package jogo;

import javaPlay.GameEngine;

public class Main {

    public static void main(String[] args) {
        Fase umaFase = new Fase();       
        GameEngine.getInstance().addGameStateController( 1 , umaFase);
        
        GameEngine.getInstance().setStartingGameStateController( 1 );
        
        GameEngine.getInstance().setFramesPerSecond( 30 );
        GameEngine.getInstance().run();
    }
}
