package decorator.Java.decorator;

public class SwimCarDecorator extends CarDecorator {

	public SwimCarDecorator(Car car) {
		super(car);
	}

	public void show() {
		this.getCar().show();
		this.swim();
	}
	
	public void swim() {
		System.out.println("swimcar is swimming");
	}

	public void run() {
		
	}

}
