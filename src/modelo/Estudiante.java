package modelo;

import java.util.Hashtable;

/**
 * Clase encargada de guardar todas las materias que vaya escogiendo el usuario
 *
 */

public class Estudiante {

	//Array<clave,valor> similares a los de PHP
	private Hashtable<String, Asignatura> eleccion = new Hashtable<>();
	
	/**
	 * Constructor.
	 * Inicializa el Hashtable
	 */
	public Estudiante() {
		this.eleccion = new Hashtable<>();
	}
	
	/**
	 * Intenta a�adir una asignatura a la elecci�n del estudiante.<br/>
	 * Devuelve true si ha tenido �xito y false si ya hab�a sido escogida. 
	 * @param a - Asginatura a a�adir
	 * @return
	 */
	public boolean addAsignatura(Asignatura a){
		boolean exito = true;
		
		if(eleccion.contains(a.getNombre()))
			return false;
		
		this.eleccion.put(a.getNombre(), a);
		
		return exito;
	}
	
	
}
