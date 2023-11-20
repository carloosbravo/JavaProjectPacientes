package objects;

public class Cita {
	private Paciente paciente;
	private String hospital;
	private String departamento;
	private String fechaCita;
	private String horaCita;

	public Cita( String hospital, String departamento, String fechaCita, String horaCita) {
		
		this.departamento = departamento;
		this.fechaCita = fechaCita;
		this.horaCita = horaCita;
	}
	public Cita(Paciente paciente) {
		this.paciente = paciente;
		
	}
	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getFechaCita() {
		return fechaCita;
	}

	public void setFechaCita(String fechaCita) {
		this.fechaCita = fechaCita;
	}

	public String getHoraCita() {
		return horaCita;
	}

	public void setHoraCita(String horaCita) {
		this.horaCita = horaCita;
	}

}
