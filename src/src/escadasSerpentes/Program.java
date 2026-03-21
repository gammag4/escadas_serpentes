package src.escadasSerpentes;

import src.escadasSerpentes.controllers.CreatePlayersScene;
import src.escadasSerpentes.framework.GameRunner;

public class Program {

    public static void main(String[] args) {
        // Tutorial 1: https://www.youtube.com/playlist?list=PL_QPQmz5C6WUF-pOQDsbsKbaBZqXj4qSq
        // Tutorial 2: https://www.youtube.com/watch?v=VE7ezYCTPe4&list=PL8CAB66181A502179
        // Tutorial layout: https://examples.javacodegeeks.com/java-swing-layouts-example/

        GameRunner runner = new GameRunner("Escadas e Serpentes", 800, 600);
        runner.run(new CreatePlayersScene());
    }
}
