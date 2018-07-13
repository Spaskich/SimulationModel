import helpers.Constants;
import helpers.RandomNumberGenerator;

public class Agent  {


    private int currWaitTime;
    private int maxWaitTime;

    public Agent() {
        RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();

        this.currWaitTime = 0;
        this.maxWaitTime = randomNumberGenerator.getRandomNumberInRange(10, 60);
    }

    public int getMaxWaitTime() {
        return maxWaitTime;
    }

    public int getCurrWaitTime() {
        return currWaitTime;
    }

    public void waitInLine() {
        this.currWaitTime++;
    }
}