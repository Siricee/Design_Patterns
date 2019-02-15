package decorator.Java.original;
public class SwimCar implements Car{

	public void run() {
		System.out.println("swimcar is running");
	}

	public void Swim() {
		System.out.println("swimcar is swimming");
	}
	
	public void show() {
		this.run();
		this.Swim();
	}

}
