package decorator.Java.original;
public class FlyCar implements Car {

	public void show() {
		this.run();
		this.fly();
	}

	public void run() {
		System.out.println("flycar is running");
	}
	
	public void fly() {
		System.out.println("flycar is flying");
	}
}
