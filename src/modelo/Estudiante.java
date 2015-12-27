package modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * Clase encargada de guardar todas las materias que vaya escogiendo el usuario
 * De esta clase se saca la cuadrícula con el horario en la interfaz gráfica
 */

public class Estudiante {

	//Array<clave,valor> similares a los de PHP
	private Hashtable<Integer, Asignatura> eleccion;
	
	//Cuatrimestre-Dia-Hora (Ej. 1-X-19)
	private ArrayList<String> horasOcupadas;
	
	// La clave será Cuatrimestre-Dia-Hora (Ej. 1-X-19)
	private Hashtable<String, Conflicto> conflictos;
	
	/**
	 * Constructor.
	 * Inicializa el Hashtable
	 */
	public Estudiante() {
		this.eleccion = new Hashtable<>();
		this.horasOcupadas = new ArrayList<>();
		this.conflictos = new Hashtable<>();
	}
	
	/**
	 * Intenta añadir una asignatura a la elección del estudiante.<br/>
	 * @param a - Asginatura a añadir
	 * @return true si ha tenido éxito y false si ya había sido escogida.
	 */
	public boolean addAsignatura(Asignatura a){
		boolean exito = true;
		
		if(eleccion.containsKey(a.getId()))
			return false;

		String hora = a.getCuatrimestre()+"-"+a.getDia()+"-"+a.getHora();
		
		//Comprueba si alguna materia está ocupando esa hora
		if(horaOcupada(hora)){
			//Conflicto detectado -> se busca la clase 
			Conflicto con = new Conflicto(a, buscaConflito(hora), hora);
			
			//Si ya había conflicto, se añade la asignatura al conflicto. Si no, se crea
			if(this.conflictos.containsKey(hora))
				this.conflictos.get(hora).addConflicto(a);
			else
				this.conflictos.put(hora, con);
		}
		else
			this.horasOcupadas.add(hora);
		
		this.eleccion.put(a.getId(), a);
		
		return exito;
	}
	
	/**
	 * Comprueba si una hora ya está siendo ocupada
	 * @param hora - Hora a comprobar en formato <i>Cuatrimestre-Dia-Hora</i>
	 * @return true si alguna materia elegida tiene clase a esa hora
	 */
	private boolean horaOcupada(String hora){
		for(String s : this.horasOcupadas){
			if(s.equalsIgnoreCase(hora))
				return true;
		}
		return false;
	}
	
	/**
	 * Busca la materia con la que está en conflicto
	 * @param hora - Hora a comprobar en formato <i>Cuatrimestre-Dia-Hora</i>
	 * @return La asignatura con la que se está en conflicto o <b>null</b> si no hay conflicto
	 */
	private Asignatura buscaConflito(String hora){
		Asignatura a = null;
		String aux = "";
		
		Enumeration<Integer> e = this.eleccion.keys();
		while (e.hasMoreElements()) {
			a = this.eleccion.get(e.nextElement());
			aux = a.getCuatrimestre() + "-" + a.getDia() + "-" + a.getHora();
			if(aux.equalsIgnoreCase(hora))
				return a;
		}
		return null;
	}
	
	/**
	 * Comprueba si una asignatura ya ha sido elegida por el usuario.<br/>
	 * @param a - Asignatura a comprobar
	 * @return true si ya ha sido elegida.
	 */
	public boolean estaElegida(Asignatura a){
		Enumeration<Integer> e = this.eleccion.keys(); 
		while(e.hasMoreElements()){
			if(eleccion.get(e.nextElement()).getId() == a.getId())
				return true;
		}
		return false;
	}
	
	/**
	 * Devuelve un ArrayList<Asignatura> con todas las asignaturas elegidas hasta el momento
	 * @return Asignaturas elegidas
	 */
	public ArrayList<Asignatura> getEleccion(){
		ArrayList<Asignatura> asignaturas = new ArrayList<>();
		
		//Iteramos por toda la tabla
		Enumeration<Integer> e = this.eleccion.keys(); 
		while(e.hasMoreElements()){
			asignaturas.add(this.eleccion.get(e.nextElement()));
		}
		
		return asignaturas;
	}
	
	/**
	 * Devuelve el número de asignaturas que lleva escogidas el alumno
	 * @return Número de asignaturas escogidas
	 */
	public int cuantasEscogidas(){
		return this.eleccion.size();
	}
	
	/**
	 * Comprueba si hay conflictos y, si los hay, devuelve un arrayList.<br/>
	 * Si no hay conflictos, devuelve null
	 * @return Un ArrayList con todos los conflictos en el horario del alumno
	 */
	public ArrayList<Conflicto> getConflictos(){
		ArrayList<Conflicto> listado = new ArrayList<>();
		
		//Iteramos por toda la tabla
		Enumeration<String> e = this.conflictos.keys(); 
		while(e.hasMoreElements()){
			listado.add(this.conflictos.get(e.nextElement()));
		}
		
		return listado;
	}
	
	public String[][] toTabla(ArrayList<Asignatura> listado){
		String tabla[][] = new String[6][13];
		
		//Llena todas las casillas con ""
		Arrays.fill(tabla, "");
		
		//Inicializamos los días
		tabla[1][0] = "lunes";
		tabla[2][0] = "martes";
		tabla[3][0] = "miércoles";
		tabla[4][0] = "jueves";
		tabla[5][0] = "viernes";

		//Inicialuizamos las horas
		tabla[0][1] = "9";
		tabla[0][2] = "10";
		tabla[0][3] = "11";
		tabla[0][4] = "12";
		tabla[0][5] = "13";
		tabla[0][6] = "14";
		tabla[0][7] = "15";
		tabla[0][8] = "16";
		tabla[0][9] = "17";
		tabla[0][10] = "18";
		tabla[0][11] = "19";
		tabla[0][12] = "20";
		
		int[] coord;
		String aux;
		for(Asignatura a : listado){
			coord = getCoordenadasTabla(a);
			aux = tabla[coord[0]][coord[1]];
			if(aux.length() == 0)
				tabla[coord[0]][coord[1]] += a.getNombre();
			else
				tabla[coord[0]][coord[1]] += " - " + a.getNombre();
		}
		return tabla;
	}
	
	/**
	 * Devuelve la casilla de la tabla en la que tiene que estar la asignatura
	 * @param a - La asignatura
	 * @return coord[0] es el día, coord[1] es la hora
	 */
	private int[] getCoordenadasTabla(Asignatura a){
		int coord[] = new int[2];
		
		String dia = ""+ a.getDia();
		int hora = a.getHora();
		
		switch (dia.toLowerCase()) {
			case "l": coord[0] = 1; break;
			case "m": coord[0] = 2; break;
			case "x": coord[0] = 3; break;
			case "j": coord[0] = 4; break;
			case "v": coord[0] = 5; break;
			default : break;
		}		
		
		switch (hora) {
			case 9: coord[1] = 1; break;
			case 10: coord[1] = 2; break;
			case 11: coord[1] = 3; break;
			case 12: coord[1] = 4; break;
			case 13: coord[1] = 5; break;
			case 14: coord[1] = 6; break;
			case 15: coord[1] = 7; break;
			case 16: coord[1] = 8; break;
			case 17: coord[1] = 9; break;
			case 18: coord[1] = 10; break;
			case 19: coord[1] = 11; break;
			case 20: coord[1] = 12; break;
			default : break;
		}
		
		return coord;
	}
	
	/**
	 * Elimina todas las elecciones del estudiante, con ello los conflictos y las horas ocupadas
	 */
	public void vaciaElecciones(){
		this.eleccion.clear();
		this.horasOcupadas.clear();
		this.conflictos.clear();
	}
}
