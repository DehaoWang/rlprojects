package model;

/**
 * Created by wangdehao on 18/1/18.
 */
public class Config {
    private int numOfGames = 100;
    private int sleepTimeForSingleGame = 2000;
    public static final int SLEEP_TIME = 0;
    private boolean printInfo = true;

    public Config(int numOfGames, int sleepTimeForSingleGame, boolean printInfo) {
        this.numOfGames = numOfGames;
        this.sleepTimeForSingleGame = sleepTimeForSingleGame;
        this.printInfo = printInfo;
    }

}
