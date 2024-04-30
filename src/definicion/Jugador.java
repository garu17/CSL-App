package definicion;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.ImageIcon;

/**
 * La Clase Jugador.
 */
public class Jugador extends Participante {

	/** La Constante serialVersionUID generada para identificarse. */
	private static final long serialVersionUID = -1812403768776440701L;

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
	 * @param fn la Fecha de Nacimiento
	 */
	// Contructor personalizado
	public Jugador(Participante p, String foto , String rol, Fecha fn) {
		super(p);
		this.Foto = foto;
		this.Rol = rol;
		this.FechaNacimiento = fn;
	}
	
	/**
	 * Instancia para crear un nuevo Jugador personalizado.
	 *
	 * @param d El DNI
	 * @param fn la Fecha de Nacimiento
	 */
	// Contructor personalizado
	public Jugador(String d, Fecha fn) {
		super(new Participante(d));
		this.Foto = "";
		this.Rol = "";
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
	 * Función para cargar jugadores desde la base de datos.
	 *
	 * @return ArrayList de Jugadores cargados desde la base de datos.
	 */
	public static ArrayList<Jugador> cargarJugadores() {
	    ArrayList<Jugador> jugadores = new ArrayList<>();

	    try (Connection conn = DriverManager.getConnection("jdbc:mysql://195.35.24.130/CSLeague", "gael", "123");
	         Statement stmt = conn.createStatement()) {

	        // Consulta SQL para seleccionar todos los jugadores
	        String sql = "SELECT DNI, FechaNacimiento FROM Jugador";
	        ResultSet rs = stmt.executeQuery(sql);

	        // Iterar sobre los resultados y crear objetos Jugador
	        while (rs.next()) {
	            String dni = rs.getString("DNI");
	            Fecha fechaNacimiento = convertirAFecha(rs.getDate("FechaNacimiento"));

	            // Crear un nuevo objeto Jugador y agregarlo a la lista
	            Jugador jugador = new Jugador(dni, fechaNacimiento);
	            jugadores.add(jugador);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Manejar errores de conexión o consulta SQL
	    }

	    return jugadores;
	}
	
	  private static Fecha convertirAFecha(Date fechaSql) {
	      // Convierte la fecha de SQL a un objeto Fecha
	      Calendar calendar = Calendar.getInstance();
	      calendar.setTime(fechaSql);
	      int año = calendar.get(Calendar.YEAR);
	      int mes = calendar.get(Calendar.MONTH) + 1; // Sumamos 1 porque en Calendar los meses van de 0 a 11
	      int dia = calendar.get(Calendar.DAY_OF_MONTH);
	      return new Fecha(dia, mes, año);
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
