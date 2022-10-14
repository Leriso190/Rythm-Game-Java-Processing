package MainClasses;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.stream.IntStream;
import static org.junit.jupiter.api.Assertions.*;

class GameEngineTest {
    GameEngine gameEngine = new GameEngine();

    @Test
    void initialiseAllBallColumns() {

        gameEngine.getColumnList().stream().flatMap(l->l.stream()).forEach(e->assertFalse(e.isExisting()));
        System.out.println("Jede Reihe auf dem Spielfeld zeigt keine Baelle an");
        gameEngine.getColumnList().stream().flatMap(l->l.stream()).forEach(e->assertEquals(e.getBallPostion(),0));
        System.out.println("Auf keiner Position auf dem Spielfeld befindet sich ein Ball");
        gameEngine.getColumnList().stream().forEach(l->assertEquals(l.size(),8));
        System.out.println("Jede Reihe kann 8 Baelle enthalten und anzeigen");
        System.out.println(gameEngine.toString());
    }

    //Enthält die Tests für alle Methoden in moveBalls(), wie addUpPosition(), shiftPositions(), ()
    @Test
    void moveBalls() {
        //addUpPosition()-Test
        gameEngine.setGameStarted(true);
        gameEngine.moveBalls();
        assertEquals(gameEngine.getPos()[0], 1);
        System.out.println("Die erste Position im Positionsraster wird um 1 (Pixel) erhoeht");
        System.out.println(Arrays.toString(gameEngine.getPos()));
        assertEquals(gameEngine.getPos()[1], 1 + gameEngine.getLengthPlayGround() * 1 / 7);
        System.out.println("Die zweite Position im Positionsraster wird um 1 (Pixel) erhoeht");

        //shiftPositions()-Test
        IntStream.range(0,120).forEach(i->gameEngine.moveBalls());
        System.out.println(gameEngine.toString());
        boolean temp1 = gameEngine.getColumnList().get(0).get(0).isExisting();
        assertEquals(gameEngine.getColumnList().get(0).get(1).getBallPostion(), 121);
        System.out.println("Der Ball wird um 120 Pixel nach unten verschoben und in die naechste Position des Positionsrasters verschoben");
        IntStream.range(0,130).forEach(i->gameEngine.moveBalls());
        boolean temp2 = gameEngine.getColumnList().get(0).get(1).isExisting();
        System.out.println(gameEngine.toString());
        assertEquals(gameEngine.getColumnList().get(0).get(2).getBallPostion(), 251);
        System.out.println("Der Ball wird um 130 Pixel nach unten verschoben und in die naechste Position des Positionsrasters verschoben");
        assertEquals(temp1, temp2);
        System.out.println("Der Ball hat sich durch das Erhoehen um 130 um eine Position verschoben");

        //deleteMissedBallsAndDecreaseLifePoints()-Test
        IntStream.range(0,1000).forEach(i->gameEngine.moveBalls());
        assertFalse(gameEngine.columnList.get(0).get(gameEngine.columnList.get(0).size()-1).isExisting());
        System.out.println("1. Auf der letzten Position sind alle Baelle geloescht, egal ob getroffen oder nicht");
        IntStream.range(0,100).forEach(i->gameEngine.moveBalls());
        assertFalse(gameEngine.columnList.get(0).get(gameEngine.columnList.get(0).size()-1).isExisting());
        System.out.println("2. Auf der letzten Position sind alle Baelle geloescht, egal ob getroffen oder nicht");
        System.out.println(gameEngine.toString());

    }

    @Test
    void getColumnList() {
        assertEquals(gameEngine.getPos()[0],gameEngine.getColumnList().get(0).get(0).getBallPostion());
        System.out.println("Die Position im pos-array entspricht der des jeweiligen Ball-Objektes in der columnList");
    }

    @Test
    void setLifePoints() {
        gameEngine.setLifePoints(150);
        assertTrue(gameEngine.getLifePoints()<101);
        System.out.println("LifePoints sind immer unter 100 Punkten");
        assertEquals(gameEngine.getLifePoints(),100);
        System.out.println("LifePoints sind bei gesetzten Werten ueber 100 Punkten genau auf 100 Punkte gesetzt");
        IntStream.range(0,10000).forEach(i->gameEngine.moveBalls());
        assertTrue(gameEngine.getLifePoints()>-1);
        System.out.println("LifePoints koennen nicht unter 0 Punkte werden");
    }

    @Test
    void resetGame() {
        gameEngine.resetGame();
        assertFalse(gameEngine.getColumnList().get(0).get(0).isExisting());
        System.out.println("Kein Ball wird mehr angezeigt");
        assertArrayEquals(gameEngine.getColumnList().get(0).toArray(),gameEngine.initialisationList.toArray());
        System.out.println("Alle Positionen in der columnList sind auf 0 gesetzt");
        assertEquals(gameEngine.getScore(),0);
        System.out.println("Der Score ist auf 0 gesetzt");
        assertEquals(gameEngine.getLifePoints(),100);
        System.out.println("Die LifePoints sind wieder vollkommen hergestellt");
    }

    @Test
    void setUpDifficulty() {
        gameEngine.setScore(400);
        gameEngine.setUpDifficulty();
        int temp1=gameEngine.getVelocity();
        gameEngine.setScore(1000);
        gameEngine.setUpDifficulty();
        int temp2=gameEngine.getVelocity();
        assertFalse(temp1==temp2);
        System.out.println("Die Geschwindigkeit erhoeht sich bei einer Punkteschwelle von 1000");
        assertEquals(temp2,temp1+1);
        System.out.println("Die Geschwindigkeit pro 1000er Punkteschwelle erhöht sich um den Wert 1");
    }

    @Test
    void checkStreakCounter() {
        gameEngine.setStreakCounter(25);
        gameEngine.moveBalls();
        assertTrue(20==gameEngine.getScoreIncreaserBigHit());
        System.out.println("Genaue Treffer geben 20 Punkte bei einer Serie von 25 Hits");
        assertTrue(10==gameEngine.getScoreIncreaserSmallHit());
        System.out.println("Ungenaue Treffer geben 10 Punkte bei einer Serie von 25 Hits");

        gameEngine.setStreakCounter(24);
        gameEngine.moveBalls();
        assertTrue(10==gameEngine.getScoreIncreaserBigHit());
        System.out.println("Genaue Treffer geben 10 Punkte bei einer Serie von <25 Hits");
        assertTrue(5==gameEngine.getScoreIncreaserSmallHit());
        System.out.println("Ungenaue Treffer geben 5 Punkte bei einer Serie von <25 Hits");
    }

    @Test
    void checkIfGameOver(){
        gameEngine.setGameStarted(true);
        assertFalse(gameEngine.isGameOver());
        System.out.println("Das Spiel ist im Gange");
        IntStream.range(0,10000).forEach(i->gameEngine.moveBalls());;
        assertTrue(gameEngine.isGameOver());
        System.out.println("Das Spiel ist vorbei");
        assertTrue(gameEngine.getLifePoints()==0);
        System.out.println("Lebenspunkte sind auf 0");
        int temp1=gameEngine.getScore();
        IntStream.range(0,10000).forEach(i->gameEngine.moveBalls());
        assertEquals(temp1,gameEngine.getScore());
        System.out.println("Punktezahl aendert sich nicht mehr");

    }
}