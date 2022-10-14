package MainClasses;
import HelperClasses.Ball;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class GameEngine implements MainClasses.Playable {
    private float widthPlayGround=350;
    private float lengthPlayGround=700;
    private float yPos0=0;
    private float yPos1=lengthPlayGround*1/7;
    private float yPos2=lengthPlayGround*2/7;
    private float yPos3=lengthPlayGround*3/7;
    private float yPos4=lengthPlayGround*4/7;
    private float yPos5=lengthPlayGround*5/7;
    private float yPos6=lengthPlayGround*6/7;
    private float yPos7=lengthPlayGround;
    private float[] pos= {yPos0,yPos1,yPos2,yPos3,yPos4,yPos5,yPos6,yPos7};
    private int numberColumns=4;
    List<Ball> initialisationList = Arrays.asList(new Ball(),new Ball(),new Ball(),new Ball(),new Ball(),new Ball(),new Ball(),new Ball());
    List<List<Ball>> columnList=new ArrayList<List<Ball>>();
    private boolean gameStarted=false;
    private boolean gameOver=false;
    private float probability=0.25F;
    private int velocity=2;
    private int lifePoints=100;
    private int score=0;
    private int lifePointsDecreaser=5;
    private int scoreIncreaserSmallHit=5;
    private int scoreIncreaserBigHit=10;
    private boolean scoreThreshold1Released=true;
    private boolean scoreThreshold2Released=true;
    private int scoreThreshold1=500;
    private int scoreThreshold2=scoreThreshold1*2;
    private int spacerForThresholds=scoreThreshold2;
    private int streakCounter=0;

    public GameEngine() {
        initialiseAllBallColumns();
    }

    public void initialiseAllBallColumns(){
        IntStream.range(0,numberColumns).forEach(i->columnList.add(initialisationList));
    }

    public float[] getPos() {
        return pos;
    }

    public List<List<Ball>> getColumnList() {
        return columnList;
    }

    public float getWidthPlayGround() {
        return widthPlayGround;
    }

    public float getLengthPlayGround() {
        return lengthPlayGround;
    }

    public int getVelocity() {
        return velocity;
    }

    public int getLifePoints() {
        return lifePoints;
    }

    public int getLifePointsDecreaser() {
        return lifePointsDecreaser;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameStarted(boolean gameStarted) {
        this.gameStarted = gameStarted;
    }

    public boolean isGameStarted() {
        return gameStarted;
    }

    public void setProbability(float probability) {
        this.probability = probability;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public int getScoreIncreaserSmallHit() {
        return scoreIncreaserSmallHit;
    }

    public int getScoreIncreaserBigHit() {
        return scoreIncreaserBigHit;
    }

    public int getStreakCounter() {
        return streakCounter;
    }

    public void setStreakCounter(int streakCounter) {
        this.streakCounter = streakCounter;
    }
    public int getScore() {
        return score;
    }

    public void setLifePoints(int lifePoints) {
        //Invariante fuer die Klasse
        if(lifePoints<101&&lifePoints>-1){
            this.lifePoints = lifePoints;
        }else if (lifePoints>100){
            this.lifePoints=100;
        }else if(lifePoints<0){
            this.lifePoints=0;
        }
    }

    public void setScore(int score) {
        this.score = score;
        System.out.println("Streak: "+streakCounter);
    }

    public void moveBalls() {
        if (gameOver) {
            //
        } else if(gameStarted) {
            if (pos[0] < yPos1) {
                addUpPosition();
            } else {
                shiftPositions();
            }
            checkIfGameOver();
        }
        checkStreakCounter();
    }

    public void addUpPosition(){
        for(int k=0;k<columnList.get(0).size();k++){
            pos[k]++;
            for(int j=0;j<columnList.size();j++){
                columnList.get(j).get(k).setBallPostion(pos[k]);
            }
        }
        deleteMissedBallsAndDecreaseLifePoints();
        setUpDifficulty();
    }

    public void deleteMissedBallsAndDecreaseLifePoints(){
        IntStream.range(0,columnList.size()).filter(i->columnList.get(i).get(columnList.get(i).size()-1).isExisting()==true).forEach(i->{
            setLifePoints(getLifePoints()-lifePointsDecreaser);
            setStreakCounter(0);
            columnList.get(i).get(columnList.get(i).size()-1).setExisting(false);
        });
    }

    public void checkIfGameOver(){
        gameOver=lifePoints<=0?true:false;
    }

    public void shiftPositions(){
        float[] tempPos=new float[pos.length];
        List<List<Ball>> tempColumnList=new ArrayList<List<Ball>>();
        IntStream.range(0,columnList.size()).forEach(i->{
            tempColumnList.add(new ArrayList<>());
            tempColumnList.get(i).add(new Ball(0,probability));
        });

        for(int k=0;k<columnList.size();k++){
            tempPos[0]=0;
            for(int l=0;l<pos.length-1;l++){
                tempPos[l+1]=pos[l];
                tempColumnList.get(k).add(columnList.get(k).get(l));
            }
        }
        IntStream.range(0,columnList.size()).forEach(i->columnList.set(i,tempColumnList.get(i)));

        pos=tempPos;
        addUpPosition();
    }

    public void setUpDifficulty(){
        if(getScore()!=0){
            if(getScore()<=scoreThreshold1+getScoreIncreaserBigHit()&&getScore()>=scoreThreshold1-getScoreIncreaserBigHit()&&scoreThreshold1Released){
                setProbability(0.4F);
                scoreThreshold1+=spacerForThresholds;
                scoreThreshold1Released=false;
            }else{
                scoreThreshold1Released=true;
            }
            if(getScore()<=scoreThreshold2+getScoreIncreaserBigHit()&&getScore()>=scoreThreshold2-getScoreIncreaserBigHit()&&scoreThreshold2Released){
                setProbability(0.25F);
                setVelocity(getVelocity()+1);
                setLifePoints(getLifePoints()+40);
                scoreThreshold2+=spacerForThresholds;
                scoreThreshold2Released=false;
            }else{
                scoreThreshold2Released=true;
            }
        }
        if(getScore()>3000+getScoreIncreaserBigHit()){
            setProbability(0.2F);
        }
    }

    public void checkStreakCounter(){
        if(streakCounter>=25){
            scoreIncreaserBigHit=20;
            scoreIncreaserSmallHit=10;
        }else{
            scoreIncreaserBigHit=10;
            scoreIncreaserSmallHit=5;
        }
    }

    public void resetGame(){
        gameOver=false;
        setLifePoints(100);
        setScore(0);
        scoreThreshold1=spacerForThresholds/2;
        scoreThreshold2=spacerForThresholds;
        scoreThreshold2Released=true;
        scoreThreshold1Released=true;
        probability=0.25F;
        velocity=2;
        streakCounter=0;
        scoreIncreaserBigHit=10;
        scoreIncreaserSmallHit=5;
        columnList.clear();
        initialiseAllBallColumns();
    }

    @Override
    public String toString() {
        return
                " [ballsCol1=" + Arrays.toString(columnList.get(0).toArray()) +
                        "\n, ballsCol2=" + Arrays.toString(columnList.get(1).toArray()) +
                        "\n, ballsCol3=" + Arrays.toString(columnList.get(2).toArray()) +
                        "\n, ballsCol4=" + Arrays.toString(columnList.get(3).toArray())+"\n"
                ;
    }
}