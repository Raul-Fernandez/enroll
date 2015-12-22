package modelo;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * Clase encargada de mostrar todas las materias que se extraigan de las consultas a la base de datos
 *
 */
public class Oferta {

	//Array<clave,valor> similares a los de PHP
	//String -> Nombre + Hora
	private Hashtable<String, Asignatura> listado = new Hashtable<>();
	
	public Oferta() {
		this.listado = new Hashtable<>();
	}
	
	/**
	 * Intenta a�adir una asignatura al horario.<br/>
	 * @param a - Asginatura a a�adir
	 * @return true si ha tenido �xito y false si ya hab�a sido escogida.
	 */
	public boolean addAsignatura(Asignatura a){
		boolean exito = true;
		
		if(listado.contains(a.getNombre()))
			return false;
		
		this.listado.put(a.getNombre()+a.getHora(), a);
		
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
			if(!this.listado.containsKey(a.getNombre()+a.getHora()))
			{
				this.listado.put(a.getNombre()+a.getHora(), a);
				exitos++;
			}
		}
		return exitos;
	}
	
	/**
	 * Elimina una asignatura de la lista de ofertadas
	 * @param a
	 * @return true si se pudo eliminar la materia del listado.
	 */
	public boolean quitaAsignatura(Asignatura a){
		boolean eliminado = false;
		
		Enumeration<String> e = this.listado.keys();
		while (e.hasMoreElements()) {
			String clave = e.nextElement();
			if(this.listado.get(clave).getNombre().equalsIgnoreCase(a.getNombre())){
				this.listado.remove(clave);
				eliminado = true;
			}
		}
		return eliminado;
	}
	
	/**
	 * Elimina todas las asignaturas
	 */
	public void clear(){
		this.listado.clear();
	}
}
