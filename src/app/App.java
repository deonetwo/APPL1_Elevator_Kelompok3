package app;

import controller.ElevatorController;

public class App {
    public static void main(String[] args) throws Exception {
        ElevatorController ec = new ElevatorController();
        ec.run();
    }
}