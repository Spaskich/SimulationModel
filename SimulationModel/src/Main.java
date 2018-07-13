import helpers.Constants;
import helpers.RandomNumberGenerator;

public class Main {
    public static void main(String[] args) {

        AgentsQueue agentsQueue1 = new AgentsQueue(20);
        AgentsQueue agentsQueue2 = new AgentsQueue(15, 10);

        int workHours = Constants.SIMULATION_TIME / 60;
        int lengthsQ1 = 0;
        int lengthsQ2 = 0;

        ServiceDevice serviceDevice1 = new ServiceDevice(10);
        ServiceDevice serviceDevice2 = new ServiceDevice(12);

        agentsQueue1.scheduleNextEvent();
        agentsQueue2.scheduleNextEvent();

//        System.out.println(agentsQueue1);
//        System.out.println(agentsQueue2);

        for (int i = Constants.WORK_DAY_START; i <= Constants.WORK_DAY_END; i++) {


            lengthsQ1 += agentsQueue1.getAgents().size();
            lengthsQ2 += agentsQueue2.getAgents().size();


            agentsQueue1.handleEvents();
            agentsQueue2.handleEvents();

            agentsQueue1.handleAgents();
            agentsQueue2.handleAgents();

            if (serviceDevice1.isBusy()) {
                serviceDevice1.serveAgent();
            } else if (!agentsQueue1.isEmpty()) {
                serviceDevice1.nextAgent(agentsQueue1);
            } else if (!agentsQueue2.isEmpty()) {
                serviceDevice1.nextAgent(agentsQueue2);
            }

            if (serviceDevice2.isBusy()) {
                serviceDevice2.serveAgent();
            } else if (!agentsQueue2.isEmpty()) {
                serviceDevice2.nextAgent(agentsQueue2);
            }
        }



//        System.out.println(agentsQueue1.getAllTimeLength());
        System.out.printf("Average Q1 Length: %.2f people/hour.%n", (double)lengthsQ1 / (Constants.SIMULATION_TIME / 60));
        System.out.printf("Average Q2 Length: %.2f people/hour.%n", (double)lengthsQ2 / (Constants.SIMULATION_TIME / 60));

        System.out.printf("Average Wait Time: %.2f minutes.%n",
                (double)(serviceDevice1.getTotalWaitTime() + serviceDevice2.getTotalWaitTime())
                        / (serviceDevice1.getAgentsServed() + serviceDevice2.getAgentsServed()));
        System.out.printf("Maximum Wait Time: %d minutes.%n",
                Math.max(serviceDevice1.getMaxWaitTime(), serviceDevice2.getMaxWaitTime()));
        System.out.printf("Service Device 1 business factor: %d%%.%n", (int)((double)serviceDevice1.getBusyTime() / Constants.SIMULATION_TIME * 100));
        System.out.printf("Service Device 2 business factor: %d%%.%n", (int)((double)serviceDevice2.getBusyTime() / Constants.SIMULATION_TIME * 100));
        System.out.printf("Total resignations: %d.%n", agentsQueue1.getResignedAgents() + agentsQueue2.getResignedAgents());
    }
}
