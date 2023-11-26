package objects;




import shared.SharedMethods;

public class HiloLectorArchivo extends Thread {
	private SharedMethods metodos;
	
	public HiloLectorArchivo(SharedMethods metodos) {
		
		this.metodos = metodos;
	}


	@Override
	public void run() {
		System.out.println("Cargando archivos...");
		metodos.cargarArchivo();
	}
}
