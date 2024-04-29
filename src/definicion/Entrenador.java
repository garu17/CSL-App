package definicion;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * La Clase Entrenador.
 */
public class Entrenador extends Participante {

	/** La Constante serialVersionUID generada para identificarse. */
	private static final long serialVersionUID = 2953596603435362053L;

	/** La Fecha de Alta. */
	private Fecha FechaAlta;

	/**
	 * Instancia para crear un nuevo Entrenador por defecto.
	 */
	// Contrustor por defecto
	public Entrenador() {
		super();
		this.FechaAlta = new Fecha();
	}

	/**
	 * Instancia para crear un nuevo Entrenador copia.
	 *
	 * @param e el Entrenador copia
	 */
	// Contructor copia
	public Entrenador(Entrenador e) {
		super(e);
		this.FechaAlta = e.FechaAlta;
	}

	/**
	 * Instancia para crear un nuevo Entrenador personalizado.
	 *
	 * @param p el Participante
	 * @param f la Fecha
	 */
	// Contructor personalizado
	public Entrenador(Participante p, Fecha f) {
		super(p);
		this.FechaAlta = f;
	}
	
	/**
	 * Instancia para crear un nuevo Jugador personalizado.
	 *
	 * @param d El DNI
	 * @param fa la Fecha de Alta
	 */
	// Contructor personalizado
	public Entrenador(String d, Fecha fa) {
		super(new Participante(d));
		this.FechaAlta = fa;
	}

	/**
	 * Obtener la Fecha de Alta.
	 *
	 * @return la Fecha de Alta
	 */
	public Fecha getFechaAlta() {
		return FechaAlta;
	}

	/**
	 * Asignar la Fecha de Alta.
	 *
	 * @param fechaAlta la nueva Fecha de Alta
	 */
	public void setFechaAlta(Fecha fechaAlta) {
		FechaAlta = fechaAlta;
	}

	/**
	 * Funcion To String.
	 *
	 * @return Informacion en formato String
	 */
	public String toString() {
		return (super.toString() + " | " + FechaAlta);
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
	public static ArrayList<Entrenador> cargarEntrenadores() {
		ArrayList<Entrenador> entrenadores = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/csleague", "root", "");
				Statement stmt = conn.createStatement()) {

			// Consulta SQL para seleccionar todos los jugadores
			String sql = "SELECT DNI, FechaAlta FROM Entrenador";
			ResultSet rs = stmt.executeQuery(sql);

			// Iterar sobre los resultados y crear objetos Jugador
			while (rs.next()) {
				String dni = rs.getString("DNI");
				Fecha fechaAlta = convertirAFecha(rs.getDate("FechaAlta"));

				// Crear un nuevo objeto Jugador y agregarlo a la lista
				Entrenador entrenador = new Entrenador(dni, fechaAlta);
				entrenadores.add(entrenador);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// Manejar errores de conexión o consulta SQL
		}

		return entrenadores;
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
}
