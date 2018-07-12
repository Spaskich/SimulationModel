import helpers.RandomNumberGenerator;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;

public class AgentsQueue {
    private int arrivalRate;
    private int timeToNextEvent;

    private int maxLength;
    private int allTimeLength;

    private Queue<Agent> agents;
    private int resignedAgents;

    private AgentsQueue() {
        this.maxLength = -1;
        this.allTimeLength = 0;

        this.agents = new ArrayDeque<>();
        this.resignedAgents = 0;
    }

    public AgentsQueue(int arrivalRate) {
        this();

        this.arrivalRate = arrivalRate;
    }

    public AgentsQueue(int arrivalRate, int maxLength) {
        this();

        this.arrivalRate = arrivalRate;
        this.maxLength = maxLength;
    }

    public int getAllTimeLength() {
        return allTimeLength;
    }

    public int getResignedAgents() {
        return resignedAgents;
    }

    public Queue<Agent> getAgents() {
        return agents;
    }

    public void handleAgents() {
        Iterator<Agent> iter = this.agents.iterator();

        while (iter.hasNext()) {
            Agent agent = iter.next();
            agent.waitInLine();

            if (agent.getCurrWaitTime() > agent.getMaxWaitTime()) {
                iter.remove();
                this.resignedAgents++;
            }
        }
    }

    public void scheduleNextEvent() {
        RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();
        this.timeToNextEvent = randomNumberGenerator.gerRandomNumberBasedOnAverage(this.arrivalRate);
//        System.out.println("new event in: " + this.timeToNextEvent);
    }

    public void handleEvents() {
        this.timeToNextEvent--;

        if (this.timeToNextEvent == 0) {
            this.scheduleNextEvent();

            if (this.hasFreeSpace()) {
                Agent agent = new Agent();
                this.addAgent(agent);
            }

        }

    }

    private void addAgent(Agent agent) throws UnsupportedOperationException {
        if (this.agents.size() < this.maxLength || this.maxLength == -1) {
            this.agents.add(agent);
        } else {
            throw new UnsupportedOperationException();
        }
    }

    public Agent removeAgent() {
        this.allTimeLength++;

        return this.agents.poll();
    }

    private void resignAgent(Agent agent) {
        this.agents.remove(agent);
        System.out.println("resigned");

        this.resignedAgents++;
    }

    public boolean hasFreeSpace() {
        return this.maxLength == -1 || this.maxLength > this.agents.size();
    }

    public boolean isEmpty() {
        return this.agents.size() == 0;
    }

    @Override
    public String toString() {
        return "AgentsQueue{" +
                "arrivalRate=" + arrivalRate +
                ", maxLength=" + maxLength +
                ", allTimeLength=" + allTimeLength +
                ", agents=" + agents +
                '}';
    }
}
