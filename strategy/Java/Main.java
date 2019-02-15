package strategy.Java;

public class Main {
    public static void main(String[] args) {
        BusinessMan man = new BusinessMan(new TransportationAirplane());
        man.transport();

        man.setStrategy(new TransportationTrain());
        man.transport();

        man.setStrategy(new TransportationVehicle());
        man.transport();
    }

}
