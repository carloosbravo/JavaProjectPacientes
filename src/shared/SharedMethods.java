package shared;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import objects.Cita;
import objects.Paciente;

public class SharedMethods {
	public static ArrayList<Paciente> pacientes = new ArrayList<Paciente>();
	public static ArrayList<Cita> citas = new ArrayList<Cita>();
	
	public synchronized  void cargarArchivo() {

		System.out.println("cargando archivos...");
		try {
			FileReader fr = new FileReader("pacientes.txt");
			BufferedReader br = new BufferedReader(fr);
			String linea = br.readLine();
			while (linea != null) {

				// añade el paciente y luego añadira las citas

				String[] camposPaciente = linea.split(";");
				Paciente p = new Paciente(camposPaciente[0], camposPaciente[1], camposPaciente[2], camposPaciente[3],
						camposPaciente[4], camposPaciente[5]);
				pacientes.add(p);
				linea = br.readLine();

				while (linea != null && linea.charAt(0) == '#') {
					String[] camposCita = linea.split(";");
					Cita c = new Cita(camposCita[0], camposCita[1], camposCita[2], camposCita[3], camposCita[4]);
					citas.add(c);
					p.getCitas().add(c);
					linea = br.readLine();

				}
				
				notifyAll();

			}

			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public synchronized  void creacionDeXML() {

		while(pacientes.size()== 0) {
			
			try {
				wait();
			} catch (InterruptedException e) {
			
				e.printStackTrace();
			}
		}
		for(int x = 0;x< pacientes.size();x++) {
			//creo la ruta donde se creara la carpeta con ese dni
			String path = "Pacientes/" + pacientes.get(x).getId() + "/";
			File carpeta = new File(path);

			// creamos la carpeta con el id del paciente
			carpeta.mkdir();

			try {
				FileWriter fw = new FileWriter(path + "Datos Personales.xml", true);
				PrintWriter pw = new PrintWriter(fw, true);

				pw.write("<Nombre>" + pacientes.get(x).getNombre() + "</Nombre>\n");
				pw.write("<Apellido>" + pacientes.get(x).getApellido1() + "</Apellido>\n");
				pw.write("<Apellido2>" + pacientes.get(x).getApellido2() + "</Apellido2>\n");
				pw.write("<Nacimiento>" + pacientes.get(x).getNacimiento() + "</Nacimiento>\n");
				pw.write("<Localidad>" + pacientes.get(x).getResidencia() + "</Localidad>\n");

				pw.close();
				fw.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				for(int i = 0;i<pacientes.get(x).getCitas().size();i++) {
					
					FileWriter fw = new FileWriter(path + "Citas.xml", true);
					PrintWriter pw = new PrintWriter(fw, true);

					pw.write("<Centro>" + pacientes.get(x).getCitas().get(i).getHospital() + "</centro>\n");
					pw.write("<Especialidad>" + pacientes.get(x).getCitas().get(i).getDepartamento()   + "</Especialidad>\n");
					pw.write("<Doctor>" +  pacientes.get(x).getCitas().get(i).getDoctor()  + "</Doctor>\n");
					pw.write("<Fecha>" +  pacientes.get(x).getCitas().get(i).getFechaCita() + "</Fecha>\n");
					pw.write("<Hora>" +  pacientes.get(x).getCitas().get(i).getHoraCita() + "</Hora>\n");

					pw.close();
					fw.close();
				}
				

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	public static void mostrarPorConsola() {

		for (int x = 0; x < pacientes.size(); x++) {

			// se muestrea el paciente
			System.out.println("ID: " + pacientes.get(x).getId());
			System.out.println("Nombre: " + pacientes.get(x).getNombre() + " " + pacientes.get(x).getApellido1() + " "
					+ pacientes.get(x).getApellido2());
			System.out.println("Nacimiento:" + pacientes.get(x).getNacimiento());
			System.out.println("Residencia: " + pacientes.get(x).getResidencia());
			System.out.println("Citas: ");

			// citas
			for (int i = 0; i < pacientes.get(x).getCitas().size(); i++) {
				System.out.println("> " + pacientes.get(x).getCitas().get(i).getHospital() + ". "
						+ pacientes.get(x).getCitas().get(i).getDoctor() + ". El"
						+ pacientes.get(x).getCitas().get(i).getFechaCita() + " a las "
						+ pacientes.get(x).getCitas().get(i).getHoraCita());
			}
			System.out.println();
		}
	}
}
