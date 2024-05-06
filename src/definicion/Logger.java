package definicion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

/**
 * La Clase Logger.
 */
public class Logger implements Serializable {

	/** La Constante serialVersionUID generada para identificarse. */
	private static final long serialVersionUID = -7021149973598236767L;

	/** La Constante FILENAME que indica la ubicacion del fichero. */
	private static final String FILENAME = "ficheros/logs.txt";

	/** El Usuario. */
	private Usuario Usuario;

	/** El Tipo de Movimiento. */
	private String TipoMovimiento;

	/** La Fecha. */
	private Fecha Fecha;

	/** El Horario. */
	private Horario Horario;

	/**
	 * Instancia para crear un nuevo Log por defecto.
	 */
	// Contrustor por defecto
	public Logger() {
		this.Usuario = new Usuario();
		this.TipoMovimiento = "";
		this.Fecha = new Fecha();
		this.Horario = new Horario();
	}

	/**
	 * Instancia para crear un nuevo Log copia.
	 *
	 * @param l el Log copia
	 */
	// Contructor copia
	public Logger(Logger l) {
		this.Usuario = l.Usuario;
		this.TipoMovimiento = l.TipoMovimiento;
		this.Fecha = l.Fecha;
		this.Horario = l.Horario;
	}

	/**
	 * Instancia para crear un nuevo Log personalizado.
	 *
	 * @param u  el Usuario
	 * @param tm el Tipo de Movimiento
	 * @param f  la Fecha
	 * @param h  el Horario
	 */
	// Contructor personalizado
	public Logger(Usuario u, String tm, Fecha f, Horario h) {
		this.Usuario = u;
		this.TipoMovimiento = tm;
		this.Fecha = f;
		this.Horario = h;
	}

	/**
	 * Funcion To string.
	 *
	 * @return Informacion en formato String
	 */
	public String toString() {
		return (Usuario.getNombre() + ": " + TipoMovimiento + " | " + Fecha + "  | " + Horario);
	}

	/**
	 * Obtener el Usuario.
	 *
	 * @return el Usuario
	 */
	public Usuario getUsuario() {
		return Usuario;
	}

	/**
	 * Asignar el Usuario.
	 *
	 * @param usuario el nuevo Usuario
	 */
	public void setUsuario(Usuario usuario) {
		Usuario = usuario;
	}

	/**
	 * Obtener el Tipo de Movimiento.
	 *
	 * @return el Tipo de Movimiento
	 */
	public String getTipoMovimiento() {
		return TipoMovimiento;
	}

	/**
	 * Asignar el Tipo de Movimiento.
	 *
	 * @param tipoMovimiento el nuevo Tipo de Movimiento
	 */
	public void setTipoMovimiento(String tipoMovimiento) {
		TipoMovimiento = tipoMovimiento;
	}

	/**
	 * Obtener la Fecha.
	 *
	 * @return la Fecha
	 */
	public Fecha getFecha() {
		return Fecha;
	}

	/**
	 * Asignar la Fecha.
	 *
	 * @param fecha la nueva Fecha
	 */
	public void setFecha(Fecha fecha) {
		Fecha = fecha;
	}

	/**
	 * Obtener el Horario.
	 *
	 * @return el Horario
	 */
	public Horario getHorario() {
		return Horario;
	}

	/**
	 * Asignar el Horario.
	 *
	 * @param horario el nuevo Horario
	 */
	public void setHorario(Horario horario) {
		Horario = horario;
	}

	/**
	 * Funcion Hash code.
	 *
	 * @return el hashCode de los Objetos a Comparar
	 */
	@Override
	public int hashCode() {
		return Objects.hash(Usuario, TipoMovimiento, Fecha);
	}

	/**
	 * Funcion Equals.
	 *
	 * @param obj el Objeto a comparar
	 * @return true, si son Iguales
	 */
	// Compara los objetos de una clase
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			// Si son el mismo objeto
			return true;
		if (obj == null)
			// si el objeto no esta creado
			return false;
		if (getClass() != obj.getClass())
			// si son de distinta clase
			return false;
		// Comparo las propiedades
		Logger other = (Logger) obj;
		// Si tienen el mismo contenido devuelvo true
		return Objects.equals(this.Usuario, other.Usuario) && Objects.equals(this.TipoMovimiento, other.TipoMovimiento)
				&& Objects.equals(this.Fecha, other.Fecha);
	}

	/**
	 * Registra un nuevo movimiento en el registro de actividades.
	 * Si el usuario actual está disponible, crea un nuevo objeto {@code Logger} con el usuario actual,
	 * el mensaje dado, y la fecha y hora actuales. Si no hay usuario actual, crea un objeto {@code Logger}
	 * con un usuario vacío, el mensaje dado, y la fecha y hora actuales.
	 * Después de agregar el nuevo movimiento a la lista de movimientos existente, guarda la lista actualizada
	 * en el archivo de registro.
	 *
	 * @param mensaje el mensaje que describe el movimiento registrado.
	 */
	public static void nuevoMovimiento(String mensaje) {
	    // Cargar la lista de movimientos existente
	    ArrayList<Logger> movimientos = cargarMovimientos();

	    Calendar calendar = Calendar.getInstance();
	    int año = calendar.get(Calendar.YEAR);
	    int mes = calendar.get(Calendar.MONTH) + 1; // Nota: los meses comienzan desde 0
	    int dia = calendar.get(Calendar.DAY_OF_MONTH);
	    int hora = calendar.get(Calendar.HOUR_OF_DAY);
	    int minuto = calendar.get(Calendar.MINUTE);

	    // Crear el formato de fecha y hora
	    Fecha fechaActual = new Fecha(dia, mes, año);
	    Horario horaActual = new Horario(hora, minuto);

	    Usuario usuario = Sesion.getUsuarioActual();

	    Logger movimiento;
	    if (usuario != null) {
	        movimiento = new Logger(usuario, mensaje, fechaActual, horaActual);
	    } else {
	        Usuario usuarioVacio = new Usuario();
	        movimiento = new Logger(usuarioVacio, mensaje, fechaActual, horaActual);
	    }

	    // Agregar el nuevo movimiento a la lista
	    movimientos.add(movimiento);

	    // Guardar la lista actualizada de movimientos
	    guardarMovimientos(movimientos);
	}

	/**
	 * Carga la lista de movimientos desde el archivo de registro.
	 *
	 * @return la lista de movimientos cargada desde el archivo.
	 */
	public static ArrayList<Logger> cargarMovimientos() {
	    ArrayList<Logger> movimientos = new ArrayList<>();
	    try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
	        String line;
	        while ((line = reader.readLine()) != null) {
	            Logger movimiento = parsearMovimiento(line);
	            if (movimiento != null) {
	                movimientos.add(movimiento);
	            }
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return movimientos;
	}

	/**
	 * Parsea una línea de texto del archivo de registro en un objeto {@code Logger}.
	 *
	 * @param line la línea de texto del archivo de registro a ser parseada.
	 * @return un objeto {@code Logger} creado a partir de la línea parseada, o {@code null} si ocurre un error.
	 */
	private static Logger parsearMovimiento(String line) {
	    try {
	        // Parsear la línea para reconstruir un objeto Logger
	        String[] parts = line.split(" \\| ");

	        // Extraer los componentes del movimiento
	        String usuarioYTipo = parts[0].trim(); // Ejemplo: "admin: Ha iniciado sesión."
	        String[] usuarioTipoParts = usuarioYTipo.split(":");
	        String nombreUsuario = usuarioTipoParts[0].trim();
	        String tipoMovimiento = usuarioTipoParts[1].trim();

	        String fechaPart = parts[1].trim(); // Ejemplo: "2/5/2024"
	        String horarioPart = parts[2].trim(); // Ejemplo: "13:46"

	        String[] fechaParts = fechaPart.split("/");
	        int dia = Integer.parseInt(fechaParts[0].trim());
	        int mes = Integer.parseInt(fechaParts[1].trim());
	        int año = Integer.parseInt(fechaParts[2].trim());

	        String[] horarioParts = horarioPart.split(":");
	        int hora = Integer.parseInt(horarioParts[0].trim());
	        int minuto = Integer.parseInt(horarioParts[1].trim());

	        Fecha fecha = new Fecha(dia, mes, año);
	        Horario horario = new Horario(hora, minuto);

	        Usuario usuario = new Usuario(nombreUsuario);
	        return new Logger(usuario, tipoMovimiento, fecha, horario);

	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}

	/**
	 * Guarda la lista de movimientos en el archivo de registro.
	 *
	 * @param movimientos la lista de movimientos a ser guardada en el archivo.
	 */
	public static void guardarMovimientos(ArrayList<Logger> movimientos) {
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME))) {
	        for (Logger movimiento : movimientos) {
	            String logEntry = movimiento.toString();
	            writer.write(logEntry);
	            writer.newLine();
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
}
