package data;

public class Score {
    private static final Score ourInstance = new Score();
    private int score =0;

    public static Score getInstance(){
        return ourInstance;
    }

    public Score() {
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
