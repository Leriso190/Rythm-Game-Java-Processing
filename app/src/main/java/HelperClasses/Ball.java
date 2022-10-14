package HelperClasses;

public class Ball {
    private float ballPostion;
    private boolean existing;

    public Ball(int pos, float probability) {
        this.ballPostion = pos;
        this.existing = generateRandomBoolean(probability);
    }

    public Ball() {
        this.ballPostion = 0;
        this.existing=false;
    }

    public void setBallPostion(float ballPostion) {
        this.ballPostion = ballPostion;
    }

    public float getBallPostion() {
        return ballPostion;
    }

    public boolean isExisting() {
        return existing;
    }

    public void setExisting(boolean existing) {
        this.existing = existing;
    }

    public boolean generateRandomBoolean(float probability){
        return  Math.random()<probability;
    }

    @Override
    public String toString() {
        return
                "(" + ballPostion +
                        "," + existing+
                        ")";
    }
}