package MainClasses;
import processing.core.PApplet;

public class Main {
    public static void main(String[] args) {
        String[] appArgs = {"New Game"};
        MainClasses.GUI gui = new MainClasses.GUI();
        PApplet.runSketch(appArgs, gui);
    }
}