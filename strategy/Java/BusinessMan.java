package strategy.Java;

public class BusinessMan {
    private TransportationStrategy strategy;

    public BusinessMan(TransportationStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(TransportationStrategy strategy) {
        this.strategy = strategy;
    }

    public void transport() {
        this.strategy.go();
    }
}
