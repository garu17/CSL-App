package definicion;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.ImageIcon;

/**
 * La Clase Jugador.
 */
public class Jugador extends Participante {

	/** La Constante serialVersionUID generada para identificarse. */
	private static final long serialVersionUID = -1812403768776440701L;

	/** La Constante FILENAME que indica la ubicacion del fichero. */
	private static final String FILENAME = "ficheros/jugadores.ser";

	/** La Foto del Jugador. */
	private String Foto;
	
	/** La Posicion de Juego. */
	private String Rol;
	
	/** La Fecha de Nacimiento. */
	private Fecha FechaNacimiento;

	/**
	 * Instancia para crear un nuevo Jugador por defecto.
	 */
	// Contrustor por defecto
	public Jugador() {
		super();
		this.Foto = "";
		this.Rol = "";
		this.FechaNacimiento = new Fecha();
	}

	/**
	 * Instancia para crear un nuevo Jugador copia.
	 *
	 * @param j el Jugador copia
	 */
	// Contructor copia
	public Jugador(Jugador j) {
		super(j);
		this.Foto = j.Foto;
		this.Rol = j.Rol;
		this.FechaNacimiento = j.FechaNacimiento;
	}

	/**
	 * Instancia para crear un nuevo Jugador personalizado.
	 *
	 * @param p   el Participante
	 * @param foto la Foto
	 * @param rol el Rol
	 */
	// Contructor personalizado
	public Jugador(Participante p, String foto , String rol, Fecha fn) {
		super(p);
		this.Foto = foto;
		this.Rol = rol;
		this.FechaNacimiento = fn;
	}

	/**
	 * Funcion To string.
	 *
	 * @return Informacion en formato String
	 */
	public String toString() {
		return (super.toString() + " | " + Rol + " | " + FechaNacimiento);
	}

	/**
	 * Obtener la Posicion.
	 *
	 * @return la Posicion
	 */
	public String getPosicion() {
		return Rol;
	}

	/**
	 * Asignar la Posicion.
	 *
	 * @param posicion la nueva Posicion
	 */
	public void setPosicion(String posicion) {
		Rol = posicion;
	}
	
	/**
	 * Obtener la Foto.
	 *
	 * @return la Foto
	 */
	public String getFoto() {
		return Foto;
	}

	/**
	 * Asignar la Foto.
	 *
	 * @param foto la nueva Foto
	 */
	public void setFoto(String foto) {
		Foto = foto;
	}
	
	/**
	 * Obtener la Fecha de Nacimiento.
	 *
	 * @return la Fecha de Nacimiento
	 */
	public Fecha getFechaNacimiento() {
		return FechaNacimiento;
	}

	/**
	 * Asignar la Fecha de Nacimiento.
	 *
	 * @param fechaNacimiento la Fecha de Nacimiento
	 */
	public void setFechaNacimiento(Fecha fechaNacimiento) {
		FechaNacimiento = fechaNacimiento;
	}

	/**
	 * Obtener el serialversionuid.
	 *
	 * @return el serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * Funcion para Guardar jugadores en el fichero.
	 *
	 * @param lstJugadores la Lista de Jugadores
	 */
	// Guarda la lista de jugadores en un archivo
	public static void guardarJugadores(ArrayList<Jugador> lstJugadores) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
			oos.writeObject(lstJugadores);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Funcion para Cargar jugadores desde fichero.
	 *
	 * @return el ArrayList de Jugadores
	 */
	// Carga la lista de jugadores desde un archivo
	@SuppressWarnings("unchecked")
	public static ArrayList<Jugador> cargarJugadores() {
		ArrayList<Jugador> jugadores = new ArrayList<>();
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME))) {
			jugadores = (ArrayList<Jugador>) ois.readObject();
		} catch (FileNotFoundException e) {
			// El archivo no existe, se creará uno nuevo cuando sea necesario
		} catch (IOException | ClassNotFoundException e) {
			if (!jugadores.isEmpty()) {
				e.printStackTrace();
			}
		}
		return jugadores;
	}

	/**
	 * Obtiene la foto como un objeto ImageIcon.
	 *
	 * @return un objeto ImageIcon que representa la foto asociada a este objeto.
	 */
   public ImageIcon obtenerFotoComoIcono() {
      return new ImageIcon(Foto); // Crea un ImageIcon a partir de la ruta de la foto
  }
}
