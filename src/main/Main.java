package main;

import java.util.ArrayList;
import java.util.Scanner;
import objects.Cita;
import objects.HiloCreadorCarpetas;
import objects.HiloLectorArchivo;
import objects.Paciente;
import shared.SharedMethods;

public class Main {
	public static Scanner sc = new Scanner(System.in);

	public static ArrayList<Paciente> pacientes = new ArrayList<Paciente>();
	public static ArrayList<Cita> citas = new ArrayList<Cita>();
	
	public static void main(String[] args) {
		int menu;
		SharedMethods metodos = new SharedMethods();
		HiloLectorArchivo hiloLector = new HiloLectorArchivo(metodos);
		HiloCreadorCarpetas hiloCarpetas = new HiloCreadorCarpetas(metodos);
		
		// menu
				do {
					System.out.println(
							"QUE DESEAS HACER:\n0 - Salir  \n1 -INICIAR PROGRAMA");
					menu = sc.nextInt();
					sc.nextLine();

					switch (menu) {

					case 0:
						System.out.println("Programa finalizado");
						break;
					case 1:
						
						hiloLector.start();
						hiloCarpetas.start();
						
						try {
							hiloLector.join();
							hiloCarpetas.join();
						} catch (InterruptedException e) {
						
							e.printStackTrace();
						}
						
						SharedMethods.mostrarPorConsola();
						break;
					

					}
				} while (menu != 0);
		
	

	}

}
