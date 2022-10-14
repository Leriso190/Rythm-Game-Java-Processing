package MainClasses;
import processing.core.PApplet;
import processing.core.PFont;
import java.util.stream.IntStream;

public class GUI extends PApplet {
    Playable gameEngine=new GameEngine();
    private float length=gameEngine.getLengthPlayGround();
    private float width=gameEngine.getWidthPlayGround();
    private float circleDiameter =55;
    private float strokeWeightVariable =3;
    private float smallCircleDiameter=circleDiameter- strokeWeightVariable *2;
    private float xPos1=width*1/8;
    private float xPos2=width*3/8;
    private float xPos3=width*5/8;
    private float xPos4=width*7/8;
    private float yPosButtons=length*11/14;
    private float lengthUpperInGameMenu=length/14;
    private float circleDiameterGameOverMenu = (float) (circleDiameter*1.3);
    private float xPos1GameOverMenu=width*1/3;
    private float xPos2GameOverMenu=width*2/3;
    private float yPosGameOverMenu=length*9/16;
    private float radius=smallCircleDiameter/2;
    private int yOffset50= (int) (1.5*radius);
    private boolean dKeyHit=false;
    private boolean fKeyHit=false;
    private boolean jKeyHit=false;
    private boolean kKeyHit=false;
    private int checkColorRed=color(218, 80, 76);
    private int checkColorOrange=color(255, 182, 62);
    private int checkColorBlue=color(60, 191, 254);
    private int checkColorGreen=color(66, 178, 166);
    private int buttonColorRed=color(188, 48, 47);
    private int buttonColorOrange=color(229, 152, 22);
    private int buttonColorBlue=color(20, 151, 214);
    private int buttonColorGreen=color(26, 138, 126);
    private int textColor=color(255);
    private int buttonColorGreenWhenHovered=color(26, 98, 126);
    private int buttonColorRedWhenHovered=color(146, 48, 47);
    private int lifeBarGreen=color(137, 192, 74);
    private int lifeBarRed=color(227, 59, 55);
    private boolean isBlurred =false;
    private int hitColorGreen = color(184, 242, 196,150);
    private int hitColorRed=color(237, 79, 17,150);
    private int closingTimeGeneralDelta=200;
    private int closingTimeD;
    private int closingTimeF;
    private int closingTimeJ;
    private int closingTimeK;

    @Override
    public void settings() {
        size((int)width,(int)length);
    }

    @Override
    public void draw() {
        if(!gameEngine.isGameStarted()){
            generateStartMenu();
        }else{
            if(gameEngine.isGameOver()){
                generateGameOverMenu();
            }else{
                createBackground();
                createButtons();
                printBalls();
                moveBallsGUI();
                refreshUpperInGameMenu();
                showKeyPressedEffects();
            }
        }
    }

    public void moveBallsGUI(){
        IntStream.range(0,gameEngine.getVelocity()).forEach(i->gameEngine.moveBalls());
    }

    @Override
    public void setup() {
        surface.setTitle("Hit the Ball!");
        createBackground();
        textAlign(CENTER,CENTER);
        textSize(30);
    }

    public void printBalls(){
        noStroke();
        for(int i=0;i<gameEngine.getColumnList().get(0).size();i++){
            if(gameEngine.getColumnList().get(0).get(i).isExisting()){
                fill(checkColorRed);
                ellipse(xPos1,gameEngine.getColumnList().get(0).get(i).getBallPostion(),smallCircleDiameter,smallCircleDiameter);
            }if(gameEngine.getColumnList().get(1).get(i).isExisting()){
                fill(checkColorOrange);
                ellipse(xPos2,gameEngine.getColumnList().get(1).get(i).getBallPostion(),smallCircleDiameter,smallCircleDiameter);
            }if(gameEngine.getColumnList().get(2).get(i).isExisting()){
                fill(checkColorBlue);
                ellipse(xPos3,gameEngine.getColumnList().get(2).get(i).getBallPostion(),smallCircleDiameter,smallCircleDiameter);
            }if(gameEngine.getColumnList().get(3).get(i).isExisting()){
                fill(checkColorGreen);
                ellipse(xPos4,gameEngine.getColumnList().get(3).get(i).getBallPostion(),smallCircleDiameter,smallCircleDiameter);
            }
        }
    }

    public void createBackground(){
        background(color(0, 0, 0));
        strokeWeight(strokeWeightVariable);
        stroke(255);
    }

    public void createButtons(){
        fill(buttonColorRed);
        ellipse(xPos1,yPosButtons, circleDiameter, circleDiameter);
        fill(buttonColorOrange);
        ellipse(xPos2,yPosButtons, circleDiameter, circleDiameter);
        fill(buttonColorBlue);
        ellipse(xPos3,yPosButtons, circleDiameter, circleDiameter);
        fill(buttonColorGreen);
        ellipse(xPos4,yPosButtons, circleDiameter, circleDiameter);
        fill(textColor);
        text("D",xPos1,yPosButtons);
        text("F",xPos2,yPosButtons);
        text("J",xPos3,yPosButtons);
        text("K",xPos4,yPosButtons);
    }

    public void refreshUpperInGameMenu(){
        noStroke();
        rectMode(CORNER);
        fill(0);
        rect(0,0,width,lengthUpperInGameMenu);
        textSize(18);
        fill(255);
        text("Score: "+gameEngine.getScore(),xPos1+20,length/34);
        fill(lifeBarGreen);
        rect(xPos2,length/60,gameEngine.getLifePoints()*2,length/30);
        fill(lifeBarRed);
        rect(xPos2+200,length/60,(gameEngine.getLifePoints()-100)*2,length/30);
    }

    public void generateGameOverMenu(){
        generateDefaultMenu("Game Over!\nFinal Score:"+gameEngine.getScore()+"\nDo you want to restart the Game?");
        generateOptionButton(buttonColorGreen,"Yes",xPos1GameOverMenu,yPosGameOverMenu);
        generateOptionButton(buttonColorRed,"No",xPos2GameOverMenu,yPosGameOverMenu);
        generateHoverButtonColors("Yes","No");
    }

    public void generateStartMenu(){
        generateDefaultMenu("\nNew Game\n\nPlease start by pressing\n the Enter key");
    }

    public void generateDefaultMenu(String text){
        if(isBlurred ==false){
            filter(BLUR,3);
            isBlurred =true;
        }
        fill(156);
        rectMode(CENTER);
        rect(width/2,length/2,width*15/16,length*1/3,5);
        fill(255);
        PFont font1=createFont("Arial Bold",20);
        textFont(font1);
        text(text,width/2,length*7/16);
    }

    public void generateOptionButton(int colorSelect, String buttonText, float xPos, float yPos){
        strokeWeight(strokeWeightVariable);
        stroke(255);
        fill(colorSelect);
        ellipse(xPos,yPos, circleDiameterGameOverMenu, circleDiameterGameOverMenu);
        fill(textColor);
        text(buttonText,xPos,yPos);

    }

    public void generateHoverButtonColors(String firstButtonName,String secondButtonName){
        if(mouseX<(xPos1GameOverMenu+ circleDiameterGameOverMenu/2)&&mouseX>(xPos1GameOverMenu-circleDiameterGameOverMenu/2)&&mouseY<(yPosGameOverMenu+circleDiameterGameOverMenu/2)&&mouseY>(yPosGameOverMenu-circleDiameterGameOverMenu/2)){
            generateOptionButton(buttonColorGreenWhenHovered,firstButtonName,xPos1GameOverMenu,yPosGameOverMenu);
        }else if(mouseX<(xPos2GameOverMenu+circleDiameterGameOverMenu/2)&&mouseX>(xPos2GameOverMenu-circleDiameterGameOverMenu/2)&&mouseY<(yPosGameOverMenu+circleDiameterGameOverMenu/2)&&mouseY>(yPosGameOverMenu-circleDiameterGameOverMenu/2)){
            generateOptionButton(buttonColorRedWhenHovered,secondButtonName,xPos2GameOverMenu,yPosGameOverMenu);
        }
    }


    @Override
    public void keyPressed() {
        if((key=='d'||key=='D')){
            System.out.println("D "+checkHitBoxPointsHit(xPos1,checkColorRed)+" Points!");
            gameEngine.getColumnList().get(0).get(5).setExisting(false);
            setTimerForKeyPressedEffects('d');
        }
        if((key=='f'||key=='F')){
            System.out.println("F "+checkHitBoxPointsHit(xPos2,checkColorOrange)+" Points!");
            gameEngine.getColumnList().get(1).get(5).setExisting(false);
            setTimerForKeyPressedEffects('f');
        }
        if((key=='j'||key=='J')){
            System.out.println("J "+checkHitBoxPointsHit(xPos3,checkColorBlue)+" Points!");
            gameEngine.getColumnList().get(2).get(5).setExisting(false);
            setTimerForKeyPressedEffects('j');
        }
        if((key=='k'||key=='K')){
            System.out.println("K "+checkHitBoxPointsHit(xPos4,checkColorGreen)+" Points!");
            gameEngine.getColumnList().get(3).get(5).setExisting(false);
            setTimerForKeyPressedEffects('k');
        }
        if(key==ENTER){
            if(!gameEngine.isGameStarted()){
                gameEngine.setGameStarted(true);
                isBlurred=false;
            }else if(gameEngine.isGameOver()){
                resetGame();
            }
        }
    }

    @Override
    public void mousePressed() {
        if(mousePressed&&mouseX<(xPos1GameOverMenu+ circleDiameterGameOverMenu/2)&&mouseX>(xPos1GameOverMenu-circleDiameterGameOverMenu/2)&&mouseY<(yPosGameOverMenu+circleDiameterGameOverMenu/2)&&mouseY>(yPosGameOverMenu-circleDiameterGameOverMenu/2)){
            resetGame();
        }else if(mousePressed&&mouseX<(xPos2GameOverMenu+ circleDiameterGameOverMenu/2)&&mouseX>(xPos2GameOverMenu-circleDiameterGameOverMenu/2)&&mouseY<(yPosGameOverMenu+circleDiameterGameOverMenu/2)&&mouseY>(yPosGameOverMenu-circleDiameterGameOverMenu/2)){
            exit();
        }
    }

    public int checkHitBoxPointsHit(float xPos, int checkColor){
        if(get((int)xPos,(int)yPosButtons)==checkColor){
            gameEngine.setScore(gameEngine.getScore()+gameEngine.getScoreIncreaserBigHit());
            gameEngine.setStreakCounter(gameEngine.getStreakCounter()+1);
            turnOnKeyHit(xPos,true);
            return gameEngine.getScoreIncreaserBigHit();
        }else if(get((int)xPos,(int)yPosButtons-yOffset50)==checkColor){
            gameEngine.setScore(gameEngine.getScore()+gameEngine.getScoreIncreaserSmallHit());
            gameEngine.setStreakCounter(gameEngine.getStreakCounter()+1);
            turnOnKeyHit(xPos,true);
            return gameEngine.getScoreIncreaserSmallHit();
        }else if(get((int)xPos,(int)yPosButtons+yOffset50)==checkColor){
            gameEngine.setScore(gameEngine.getScore()+gameEngine.getScoreIncreaserSmallHit());
            gameEngine.setStreakCounter(gameEngine.getStreakCounter()+1);
            turnOnKeyHit(xPos,true);
            return gameEngine.getScoreIncreaserSmallHit();
        } else{
            gameEngine.setLifePoints(gameEngine.getLifePoints()- gameEngine.getLifePointsDecreaser());
            gameEngine.setStreakCounter(0);
            turnOnKeyHit(xPos,false);
            return 0;
        }
    }

    public void turnOnKeyHit(float xPos, boolean checker){
        if(xPos == xPos1){
            if(checker==true) dKeyHit=true; else dKeyHit=false;
        }else if(xPos == xPos2){
            if(checker==true) fKeyHit=true; else fKeyHit=false;
        }else if(xPos == xPos3){
            if(checker==true) jKeyHit=true; else jKeyHit=false;
        }else if(xPos == xPos4){
            if(checker==true) kKeyHit=true; else kKeyHit=false;
        }
    }

    public void setTimerForKeyPressedEffects(char c){
        if(c=='d'){
            closingTimeD=millis()+closingTimeGeneralDelta;
        }else if(c=='f'){
            closingTimeF=millis()+closingTimeGeneralDelta;
        }else if(c=='j'){
            closingTimeJ=millis()+closingTimeGeneralDelta;
        }else if(c=='k'){
            closingTimeK=millis()+closingTimeGeneralDelta;
        }
    }

    public void showKeyPressedEffects(){
        float tempDiameter=circleDiameter+strokeWeightVariable;
        noFill();
        strokeWeight(strokeWeightVariable*2);
        if(millis()<closingTimeD){
            if(dKeyHit) stroke(hitColorGreen); else stroke(hitColorRed);
            ellipse(xPos1,yPosButtons,tempDiameter,tempDiameter);
        }else{
            dKeyHit=false;
        }
        if(millis()<closingTimeF){
            if(fKeyHit) stroke(hitColorGreen); else stroke(hitColorRed);
            ellipse(xPos2,yPosButtons,tempDiameter,tempDiameter);
        }else{
            fKeyHit=false;
        }
        if(millis()<closingTimeJ){
            if(jKeyHit) stroke(hitColorGreen); else stroke(hitColorRed);
            ellipse(xPos3,yPosButtons,tempDiameter,tempDiameter);
        }else{
            jKeyHit=false;
        }
        if(millis()<closingTimeK){
            if(kKeyHit) stroke(hitColorGreen); else stroke(hitColorRed);
            ellipse(xPos4,yPosButtons,tempDiameter,tempDiameter);
        }else{
            kKeyHit=false;
        }
    }

    public void resetGame(){
        gameEngine.resetGame();
        isBlurred =false;
    }
}