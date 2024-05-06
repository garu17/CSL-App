package definicion;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

/**
 * La Clase Equipo.
 */
public class Equipo implements Serializable {

	/** La Constante serialVersionUID generada para identificarse. */
	private static final long serialVersionUID = -1650289960010952509L;

	/** El Nombre. */
	private String Nombre;

	/** El Escudo. */
	private String Escudo;

	/** La Descripcion. */
	private String Descripcion;

	/** La Fecha de Creacion del Equipo. */
	private Fecha FechaCreacion;

	/** El Entrenador. */
	private Entrenador Entrenador;

	/** La Lista de Jugadores. */
	private List<Jugador> ListaJugadores;

	/** La Lista de estadisticas por temporada. */
	private List<Estadisticas> estadisticasPorTemporada;

	/**
	 * Instancia para crear un nuevo Equipo por defecto.
	 */
	// Constructor por defecto
	public Equipo() {
		this.Nombre = "";
		this.Escudo = "";
		this.Descripcion = "";
		this.FechaCreacion = new Fecha();
		this.Entrenador = new Entrenador();
		this.ListaJugadores = new ArrayList<Jugador>();
		this.estadisticasPorTemporada = new ArrayList<Estadisticas>();
	}

	/**
	 * Instancia para crear un nuevo Equipo copia.
	 *
	 * @param e el Equipo copia
	 */
	// Constructor copia
	public Equipo(Equipo e) {
		this.Nombre = e.Nombre;
		this.Escudo = e.Escudo;
		this.Descripcion = e.Descripcion;
		this.FechaCreacion = e.FechaCreacion;
		this.Entrenador = e.Entrenador;
		this.ListaJugadores = e.ListaJugadores;
		this.estadisticasPorTemporada = e.estadisticasPorTemporada;
	}

	/**
	 * Instancia para crear un nuevo Equipo personalizado.
	 *
	 * @param n   el Nombre
	 * @param esc el Escudo
	 * @param d   la Descripcion
	 * @param f   la Fecha de Creacion
	 * @param e   el Entrenador
	 * @param lj  la Lista de Jugadores
	 * @param es  la Lista de Estadisticas por Temporada
	 */
	// Constructor personalizado
	public Equipo(String n, String esc, String d, Fecha f, Entrenador e, List<Jugador> lj, List<Estadisticas> es) {
		this.Nombre = n;
		this.Escudo = esc;
		this.Descripcion = d;
		this.FechaCreacion = f;
		this.Entrenador = e;
		this.ListaJugadores = lj;
		this.estadisticasPorTemporada = es;
	}
	
	/**
	 * Instancia un objeto Equipo con atributos personalizados.
	 *
	 * @param n  el Nombre del equipo.
	 * @param esc el Escudo del equipo.
	 * @param d  la Descripción del equipo.
	 * @param f  la Fecha de creación del equipo.
	 * @param e  el Entrenador del equipo.
	 * @param lj la Lista de Jugadores del equipo.
	 */
	// Constructor personalizado
	public Equipo(String n, String esc, String d, Fecha f, Entrenador e, List<Jugador> lj) {
	    this.Nombre = n;
	    this.Escudo = esc;
	    this.Descripcion = d;
	    this.FechaCreacion = f;
	    this.Entrenador = e;
	    this.ListaJugadores = lj;
	    this.estadisticasPorTemporada = new ArrayList<>();
	}

	/**
	 * Instancia un objeto Equipo con un nombre específico.
	 *
	 * @param n el Nombre del equipo.
	 */
	// Constructor personalizado
	public Equipo(String n) {
	    this.Nombre = n;
	    this.Escudo = "";
	    this.Descripcion = "";
	    this.FechaCreacion = new Fecha();
	    this.Entrenador = new Entrenador();
	    this.ListaJugadores = new ArrayList<>();
	    this.estadisticasPorTemporada = new ArrayList<>();
	}

	/**
	 * Obtener el Nombre.
	 *
	 * @return el Nombre
	 */
	public String getNombre() {
		return Nombre;
	}

	/**
	 * Asignar el Nombre.
	 *
	 * @param nombre el nuevo Nombre
	 */
	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	/**
	 * Obtener el Escudo.
	 *
	 * @return el Escudo
	 */
	public String getEscudo() {
		return Escudo;
	}

	/**
	 * Asignar el Escudo.
	 *
	 * @param escudo el nuevo Escudo
	 */
	public void setEscudo(String escudo) {
		Escudo = escudo;
	}

	/**
	 * Obtener la Descripcion.
	 *
	 * @return la Descripcion
	 */
	public String getDescripcion() {
		return Descripcion;
	}

	/**
	 * Asignar la Descripcion.
	 *
	 * @param descripcion la nueva Descripcion
	 */
	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}

	/**
	 * Obtener el Entrenador.
	 *
	 * @return el Entrenador
	 */
	public Entrenador getEntrenador() {
		return Entrenador;
	}

	/**
	 * Asignar el Entrenador.
	 *
	 * @param entrenador el nuevo Entrenador
	 */
	public void setEntrenador(Entrenador entrenador) {
		Entrenador = entrenador;
	}

	/**
	 * Obtener la Lista de Jugadores.
	 *
	 * @return la Lista de Jugadores
	 */
	public List<Jugador> getListaJugadores() {
		return ListaJugadores;
	}

	/**
	 * Asignar la Lista de Jugadores.
	 *
	 * @param listaJugadores la nueva Lista de Jugadores
	 */
	public void setListaJugadores(List<Jugador> listaJugadores) {
		ListaJugadores = listaJugadores;
	}

	/**
	 * Obtener la Lista de Estadisticas por Temporada.
	 *
	 * @return la Lista de Estadisticas por Temporada
	 */
	public List<Estadisticas> getEstadisticasPorTemporada() {
		return estadisticasPorTemporada;
	}

	/**
	 * Asignar la Lista de Estadisticas por Temporada.
	 *
	 * @param estadisticasPorTemporada la nueva Lista de Estadisticas por Temporada
	 */
	public void setEstadisticasPorTemporada(List<Estadisticas> estadisticasPorTemporada) {
		this.estadisticasPorTemporada = estadisticasPorTemporada;
	}

	/**
	 * Obtener la Fecha de Creacion.
	 *
	 * @return la Fecha de Creacion
	 */
	public Fecha getFechaCreacion() {
		return FechaCreacion;
	}

	/**
	 * Asignar la Fecha de Creacion.
	 *
	 * @param fechaCreacion la fecha de Creacion
	 */
	public void setFechaCreacion(Fecha fechaCreacion) {
		FechaCreacion = fechaCreacion;
	}

	/**
	 * Funcion To string.
	 *
	 * @return Informacion en formato String
	 */
	public String toString() {
		return (Nombre + " ");
	}

	/**
	 * Funcion Hash code.
	 *
	 * @return el hashCode de los Objetos a Comparar
	 */
	@Override
	public int hashCode() {
		return Objects.hash(Nombre);
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
		Equipo other = (Equipo) obj;
		// Si tienen el mismo contenido devuelvo true
		return Objects.equals(this.Nombre, other.Nombre);
	}

	/**
	 * Funcion para Cargar equipos desde base de datos.
	 *
	 * @return el ArrayList de Equipos
	 */
	// Carga la lista de equipos desde un archivo
	public static ArrayList<Equipo> cargarEquipos(Integer temporada) {
		ArrayList<Equipo> equipos = new ArrayList<>();

		Connection conexion = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			conexion = DriverManager.getConnection("jdbc:mysql://195.35.24.130/CSLeague", "gael", "123");
			st = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = st.executeQuery("SELECT DISTINCT e.Nombre, e.FechaCreacion, tp.Escudo, tp.Descripcion, "
					+ "ec.Entrenador, ec.Nombre AS NombreEntrenador, ec.Apellido, ec.Nacionalidad, en.FechaAlta "
					+ "FROM Equipo e " + "JOIN EntrenadorContratado ec ON e.Nombre = ec.Equipo "
					+ "JOIN Entrenador en ON ec.Entrenador = en.DNI "
					+ "JOIN TemporadaParticipada tp ON e.Nombre = tp.Equipo " + "WHERE tp.Temporada = "+ temporada +" AND ec.Temporada = "+ temporada);

			while (rs.next()) {
				String nombreEquipo = rs.getString("Nombre");
				String escudo = rs.getString("Escudo");
				String descripcion = rs.getString("Descripcion");
				Date fechaCreacionSql = rs.getDate("FechaCreacion");
				Fecha fechaCreacion = convertirAFecha(fechaCreacionSql);

				String dniEntrenador = rs.getString("Entrenador");
				String nombreEntrenador = rs.getString("NombreEntrenador");
				String apellidoEntrenador = rs.getString("Apellido");
				String nacionalidadEntrenador = rs.getString("Nacionalidad");
				Date fechaAltaEntrenadorSql = rs.getDate("FechaAlta");
				Fecha fechaAltaEntrenador = convertirAFecha(fechaAltaEntrenadorSql);

				// Crear objeto Entrenador
				Participante participante = new Participante(dniEntrenador, nombreEntrenador, apellidoEntrenador,
						nacionalidadEntrenador);
				Entrenador entrenador = new Entrenador(participante, fechaAltaEntrenador);

				// Ahora cargamos los jugadores del equipo
				ArrayList<Jugador> jugadoresEquipo = new ArrayList<>();

				// Crear un nuevo Statement para la consulta de jugadores
				Statement stJugadores = conexion.createStatement();
				ResultSet rsJugadores = stJugadores.executeQuery(
						"SELECT j.DNI, jc.Nombre, jc.Apellido, jc.Nacionalidad, jc.Foto, jc.Rol, j.FechaNacimiento "
								+ "FROM JugadorContratado jc " + "JOIN Jugador j ON jc.Jugador = j.DNI " + "WHERE jc.Equipo = '"
								+ nombreEquipo + "' AND jc.Temporada = "+temporada);

				while (rsJugadores.next()) {
					String dniJugador = rsJugadores.getString("DNI");
					String nombreJugador = rsJugadores.getString("Nombre");
					String apellidoJugador = rsJugadores.getString("Apellido");
					String nacionalidadJugador = rsJugadores.getString("Nacionalidad");
					String fotoJugador = rsJugadores.getString("Foto");
					String posicionJugador = rsJugadores.getString("Rol");
					Date fechaNacimientoJugadorSql = rsJugadores.getDate("FechaNacimiento");
					Fecha fechaNacimientoJugador = convertirAFecha(fechaNacimientoJugadorSql);

					Participante participanteJugador = new Participante(dniJugador, nombreJugador, apellidoJugador,
							nacionalidadJugador);
					Jugador jugador = new Jugador(participanteJugador, fotoJugador, posicionJugador, fechaNacimientoJugador);

					jugadoresEquipo.add(jugador);
				}

				// Cerrar ResultSet y Statement del segundo query
				rsJugadores.close();
				stJugadores.close();

				// Crear objeto Equipo
				Equipo equipo = new Equipo(nombreEquipo, escudo, descripcion, fechaCreacion, entrenador, jugadoresEquipo);

				// Agregar el equipo a la lista de equipos
				equipos.add(equipo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
				if (conexion != null)
					conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return equipos;
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
	 * Funcion para Actualizar estadisticas por temporada.
	 *
	 * @param partido      el partido
	 * @param temporada    la temporada
	 * @param nombreEquipo el nombre del equipo
	 */
	public void actualizarEstadisticas(Partido partido, Temporada temporada, String nombreEquipo) {

		// Obtener las estadísticas actuales del equipo para la temporada específica
		Estadisticas estadisticasActuales = obtenerEstadisticasPorTemporada(temporada, nombreEquipo);

		// Verificar si el equipo tiene 0 puntos en el partido
		Integer puntosLocal = partido.getPuntosLocal();
		Integer puntosVisitante = partido.getPuntosVisitante();

		int puntosEquipo = (nombreEquipo.equals(partido.getEquipoLocal().getNombre()))
				? (puntosLocal != null ? puntosLocal : 0)
				: (puntosVisitante != null ? puntosVisitante : 0);

		if (puntosEquipo != -1) {
			// Marcar el partido como jugado
			partido.setJugado(true);

			// Incrementar el número de partidos jugados
			estadisticasActuales.setPartidosJugados(estadisticasActuales.getPartidosJugados() + 1);

			// Determinar el resultado del partido y actualizar las estadísticas
			// correspondientes
			if (nombreEquipo.equals(partido.getEquipoLocal().getNombre())) {
				// El equipo local está involucrado en el partido
				if (Partido.determinarGanador(partido).equals("Local")) {
					// El equipo local ha ganado
					estadisticasActuales.setPartidosGanados(estadisticasActuales.getPartidosGanados() + 1);
					// Incrementar los puntos totales en 2 (por ganar)
					estadisticasActuales.setPuntosTotales(estadisticasActuales.getPuntosTotales() + 2);
				} else if (Partido.determinarGanador(partido).equals("Visitante")) {
					// El equipo local ha perdido
					estadisticasActuales.setPartidosPerdidos(estadisticasActuales.getPartidosPerdidos() + 1);
				}

				// Actualizar las Rondas de Diferencia
				estadisticasActuales.setRondasDiferencia(estadisticasActuales.getRondasDiferencia()
						+ (partido.getPuntosLocal() - partido.getPuntosVisitante()));
			} else if (nombreEquipo.equals(partido.getEquipoVisitante().getNombre())) {
				// El equipo visitante está involucrado en el partido
				if (Partido.determinarGanador(partido).equals("Visitante")) {
					// El equipo visitante ha ganado
					estadisticasActuales.setPartidosGanados(estadisticasActuales.getPartidosGanados() + 1);
					// Incrementar los puntos totales en 2 (por ganar)
					estadisticasActuales.setPuntosTotales(estadisticasActuales.getPuntosTotales() + 2);
				} else if (Partido.determinarGanador(partido).equals("Local")) {
					// El equipo visitante ha perdido
					estadisticasActuales.setPartidosPerdidos(estadisticasActuales.getPartidosPerdidos() + 1);
				}

				// Actualizar las Rondas de Diferencia
				estadisticasActuales.setRondasDiferencia(estadisticasActuales.getRondasDiferencia()
						+ (partido.getPuntosVisitante() - partido.getPuntosLocal()));
			}
		}

		try (Connection conn = DriverManager.getConnection("jdbc:mysql://195.35.24.130/CSLeague", "gael", "123")) {
			conn.setAutoCommit(false); // Desactivar el modo de autocommit

			// Crear la consulta SQL para actualizar las estadísticas
			String updateEstadisticasQuery = "UPDATE Estadisticas SET PuntosTotales = ?, PartidasJugadas = ?, PartidasGanadas = ?, PartidasPerdidas = ?, RondasDiferencia = ? WHERE Temporada = ? AND Equipo = ?";
			PreparedStatement psUpdateEstadisticas = conn.prepareStatement(updateEstadisticasQuery);

			// Asignar los valores a los parámetros de la consulta
			psUpdateEstadisticas.setInt(1, estadisticasActuales.getPuntosTotales());
			psUpdateEstadisticas.setInt(2, estadisticasActuales.getPartidosJugados());
			psUpdateEstadisticas.setInt(3, estadisticasActuales.getPartidosGanados());
			psUpdateEstadisticas.setInt(4, estadisticasActuales.getPartidosPerdidos());
			psUpdateEstadisticas.setInt(5, estadisticasActuales.getRondasDiferencia());
			psUpdateEstadisticas.setInt(6, Seleccion.getTemporadaNumero());
			psUpdateEstadisticas.setString(7, nombreEquipo);

			// Ejecutar la consulta de actualización
			psUpdateEstadisticas.executeUpdate();

			// Confirmar la transacción
			conn.commit();

			// Cerrar el PreparedStatement
			psUpdateEstadisticas.close();
		} catch (SQLException e) {
			e.printStackTrace();
			// Manejar el error apropiadamente
		}
	}

	/**
	 * Funcion para Obtener estadisticas por temporada.
	 *
	 * @param temporada la temporada
	 * @return las Estadisticas
	 */
	private Estadisticas obtenerEstadisticasPorTemporada(Temporada temporada, String equipo) {
		Estadisticas estadisticas = null;

		try (Connection conn = DriverManager.getConnection("jdbc:mysql://195.35.24.130/CSLeague", "gael", "123")) {
			String query = "SELECT * FROM Estadisticas WHERE Temporada = ? AND Equipo = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, temporada.getNumero());
			ps.setString(2, equipo); // Obtener el nombre del equipo actual

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				// Si se encontraron estadísticas en la base de datos
				int puntosTotales = rs.getInt("PuntosTotales");
				int partidosJugados = rs.getInt("PartidasJugadas");
				int partidosGanados = rs.getInt("PartidasGanadas");
				int partidosPerdidos = rs.getInt("PartidasPerdidas");
				int rondasDiferencia = rs.getInt("RondasDiferencia");

				estadisticas = new Estadisticas(temporada);
				estadisticas.setPuntosTotales(puntosTotales);
				estadisticas.setPartidosJugados(partidosJugados);
				estadisticas.setPartidosGanados(partidosGanados);
				estadisticas.setPartidosPerdidos(partidosPerdidos);
				estadisticas.setRondasDiferencia(rondasDiferencia);
			}

			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (estadisticas == null) {
			// Si no se encontraron estadísticas en la base de datos, crear nuevas
			estadisticas = new Estadisticas(temporada);
			estadisticasPorTemporada.add(estadisticas);
		}

		return estadisticas;
	}

	/**
	 * Limpia las estadísticas de un equipo para una temporada específica en la base de datos.
	 *
	 * @param temporada    La temporada a la cual pertenecen las estadísticas a limpiar.
	 * @param nombreEquipo  El nombre del equipo cuyas estadísticas se van a limpiar.
	 */
	public static void limpiarEstadisticasEquipo(Temporada temporada, String nombreEquipo) {
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://195.35.24.130/CSLeague", "gael", "123")) {
			conn.setAutoCommit(false); // Desactivar el modo de autocommit

			// Crear la consulta SQL para limpiar las estadísticas del equipo para la
			// temporada
			String limpiarEstadisticasQuery = "UPDATE Estadisticas SET PuntosTotales = 0, PartidasJugadas = 0, PartidasGanadas = 0, PartidasPerdidas = 0, RondasDiferencia = 0 WHERE Temporada = ? AND Equipo = ?";
			PreparedStatement psLimpiarEstadisticas = conn.prepareStatement(limpiarEstadisticasQuery);

			// Asignar los valores a los parámetros de la consulta
			psLimpiarEstadisticas.setInt(1, temporada.getNumero());
			psLimpiarEstadisticas.setString(2, nombreEquipo);

			// Ejecutar la consulta para limpiar las estadísticas del equipo
			psLimpiarEstadisticas.executeUpdate();

			// Confirmar la transacción
			conn.commit();

			// Cerrar el PreparedStatement
			psLimpiarEstadisticas.close();
		} catch (SQLException e) {
			e.printStackTrace();
			// Manejar el error apropiadamente
		}
	}

}
