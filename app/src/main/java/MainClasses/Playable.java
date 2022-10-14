package MainClasses;

import HelperClasses.Ball;

import java.util.List;

public interface Playable {
    float getWidthPlayGround();
    float getLengthPlayGround();
    List<List<Ball>> getColumnList();
    void moveBalls();
    int getVelocity();
    void setScore(int score);
    int getScore();
    int getLifePoints();
    void setLifePoints(int lifePoints);
    int getLifePointsDecreaser();
    boolean isGameOver();
    void resetGame();
    void setGameStarted(boolean gameStarted);
    boolean isGameStarted();
    int getScoreIncreaserSmallHit();
    int getScoreIncreaserBigHit();
    int getStreakCounter();
    void setStreakCounter(int streakCounter);
}


