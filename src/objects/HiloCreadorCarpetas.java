package objects;
import shared.SharedMethods;

public class HiloCreadorCarpetas extends Thread{
	private SharedMethods metodos;
	
	
	public HiloCreadorCarpetas(SharedMethods metodos) {
		
		this.metodos = metodos;
	}

	@Override
	public void run() {
		System.out.println("Creando carpetas");
			metodos.creacionDeXML();
	}
}
