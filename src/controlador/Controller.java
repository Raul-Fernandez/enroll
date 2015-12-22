package controlador;

import java.util.ArrayList;

import modelo.Asignatura;
import modelo.Consultor;
import modelo.Conflicto;
import modelo.Estudiante;
import modelo.Oferta;
import modelo.db.Conector;

public class Controller {

	private Estudiante estudiante;
	private Oferta oferta;
	private Conector conector;
	private Consultor consultor;
	
	public Controller() {
		this.estudiante = new Estudiante();
		this.oferta = new Oferta();
		this.conector = new Conector();
		this.consultor = new Consultor(this.conector.getConexion());
	}
	
	/**
	 * Metodo encargado de iniciar la aplicación
	 */
	public void run(){
		
	}
	
//////////////////////
////    Modelo    ////
//////////////////////
	
	//Conector
	public void openConnection(){
		this.conector.openConnection();
	}
	
	public void closeConnection(){
		this.conector.closeConnection();
	}
	
	//Estudiante
	public boolean addAsignaturaEstudiante(Asignatura a){
		return this.estudiante.addAsignatura(a);
	}
	
	public ArrayList<Asignatura> getAsignaturas(){
		return this.estudiante.getEleccion();
	}
	
	public int totalEscogidas(){
		return this.estudiante.cuantasEscogidas();
	}
	
	public ArrayList<Conflicto> buscaConflictos(){
		return this.estudiante.buscaConflictos();
	}
	
	public void vaciaEscogidas(){
		this.estudiante.clear();
	}
	
	//Oferta
	public boolean addAsignaturaOferta(Asignatura a){
		return this.oferta.addAsignatura(a);
	}
	
	public int addAsignaturasOferta(ArrayList<Asignatura> listado){
		return this.oferta.addAsignaturas(listado);
	}
	
	public boolean quitaAsignaturaOferta(Asignatura a){
		return this.oferta.quitaAsignatura(a);
	}
	
	public void vaciaOferta(){
		this.oferta.clear();
	}
	
	//AsignaturaMapper
	
	
/////////////////////
////    Vista    ////
/////////////////////
	
	
	
	
}
