package modelo;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Clase encargada de mostrar todas las materias que se extraigan de las consultas a la base de datos
 *
 */
public class Oferta {

	//Array<clave,valor> similares a los de PHP
	private Hashtable<String, Asignatura> eleccion = new Hashtable<>();
	
	public Oferta() {
		this.eleccion = new Hashtable<>();
	}
	
	/**
	 * Intenta a�adir una asignatura al horario.<br/>
	 * @param a - Asginatura a a�adir
	 * @return true si ha tenido �xito y false si ya hab�a sido escogida.
	 */
	public boolean addAsignatura(Asignatura a){
		boolean exito = true;
		
		if(eleccion.contains(a.getNombre()))
			return false;
		
		this.eleccion.put(a.getNombre(), a);
		
		return exito;
	}
	
	/**
	 * Intenta a�adir un listado de asignaturas al horario.<br/>
	 * @param listado - Asginaturas a a�adir
	 * @return N�mero de inserciones con �xito
	 */
	public int addAsignaturas(ArrayList<Asignatura> listado){
		int exitos = 0;
		
		for(Asignatura a : listado)
		{
			if(!this.eleccion.containsKey(a.getNombre()))
			{
				this.eleccion.put(a.getNombre(), a);
				exitos++;
			}
		}
		
		return exitos;
	}
	
	
	
	/**
	 * Elimina todas las asignaturas
	 */
	public void clear(){
		this.eleccion.clear();
	}
}
