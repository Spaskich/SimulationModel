import helpers.RandomNumberGenerator;

public class ServiceDevice {
    private int avgServiceTime;
    private int currServiceTime;

    private int busyTime;
    private boolean isBusy;

    private int currAgentTime;

    private int agentsServed;

    private int totalWaitTime;
    private int maxWaitTime;

    private ServiceDevice() {
        this.currServiceTime = 0;
        this.busyTime = 0;
        this.isBusy = false;
        this.currAgentTime = 0;

        this.totalWaitTime = 0;
        this.maxWaitTime = 0;

        this.agentsServed = 0;
    }

    public ServiceDevice(int avgServiceTime) {
        this();

        this.avgServiceTime = avgServiceTime;
    }



    public void nextAgent(AgentsQueue agentsQueue) {
        if (agentsQueue.hasFreeSpace()) {
            Agent nextAgent = agentsQueue.removeAgent();

            this.setCurrServiceTime();
            this.currAgentTime = 0;
            this.isBusy = true;

            this.totalWaitTime += nextAgent.getCurrWaitTime();
            if (nextAgent.getCurrWaitTime() > this.maxWaitTime) {
                this.maxWaitTime = nextAgent.getCurrWaitTime();
            }
        }
    }

    public void serveAgent() throws UnsupportedOperationException {
        if (isBusy) {
            this.currAgentTime++;
            this.busyTime++;

            if (this.currAgentTime == this.currServiceTime) {
                this.isBusy = false;
                agentsServed++;
            }
        } else {
            throw new UnsupportedOperationException();
        }
    }

    public int getAvgServiceTime() {
        return avgServiceTime;
    }

    public int getCurrServiceTime() {
        return currServiceTime;
    }

    private void setCurrServiceTime() {
        RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();
        this.currServiceTime = randomNumberGenerator.gerRandomNumberBasedOnAverage(avgServiceTime);
//        System.out.println("service time: " + this.currServiceTime);
    }

    public int getBusyTime() {
        return busyTime;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }

    public int getCurrAgentTime() {
        return currAgentTime;
    }

    public void setCurrAgentTime(int currAgentTime) {
        this.currAgentTime = currAgentTime;
    }

    public int getTotalWaitTime() {
        return totalWaitTime;
    }

    public int getMaxWaitTime() {
        return maxWaitTime;
    }

    public int getAgentsServed() {
        return agentsServed;
    }
}
