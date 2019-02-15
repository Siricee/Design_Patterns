package decorator.Java.decorator;

public class RunCar implements Car {

	public void run() {
		System.out.println("runcar is running");
	}

	public void show() {
		this.run();
	}

}
