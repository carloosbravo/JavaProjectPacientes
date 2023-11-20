package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import objects.Cita;
import objects.Paciente;

public class Main {
	public static Scanner sc = new Scanner(System.in);
	public static String path = "pacientes.txt";
	public static ArrayList<Paciente> pacientes = new ArrayList<Paciente>();
	public static ArrayList<Cita> citas = new ArrayList<Cita>();

	public static void main(String[] args) {
		int menu;

		// menu
		do {
			System.out.println("QUE DESEAS HACER: ");
			menu = sc.nextInt();
			sc.nextLine();

			switch (menu) {

			case 0:
				System.out.println("Programa finalizado");
				break;
			case 1:
				cargarArchivo(path);
				break;
			case 2:
				mostrarPorConsola();
				break;
			}
		} while (menu != 0);

	}

	public static void cargarArchivo(String path) {

		System.out.println("cargando archivos...");
		try {
			FileReader fr = new FileReader(path);
			BufferedReader br = new BufferedReader(fr);
			String linea;
			boolean pacienteLeido = false;
			while ((linea = br.readLine()) != null) {

				// añade el paciente y luego añadira las citas

				String[] camposPaciente = linea.split(";");
				Paciente p = new Paciente(camposPaciente[0], camposPaciente[1], camposPaciente[2], camposPaciente[3],
						camposPaciente[4], camposPaciente[5]);
				pacientes.add(p);
				Cita c = new Cita(p);
				while ((linea = br.readLine()) != "\n") {
					String[] camposCita = linea.split(";");
					c = new Cita(camposCita[0], camposCita[1], camposCita[2], camposCita[3]);
					citas.add(c);
				}
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void mostrarPorConsola() {

	}

}
