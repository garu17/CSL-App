package definicion;


import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

/**
 * La Clase Temporada.
 */
public class Temporada implements Comparable<Temporada>, Serializable {

	/** La Constante serialVersionUID generada para identificarse. */
	private static final long serialVersionUID = -7680466212868208184L;

	/** El Numero. */
	private Integer Numero;

	/** La Fecha de Inicio. */
	private Fecha FechaInicio;

	/** El Estado. */
	private String Estado;

	/** La Lista de Equipos. */
	private List<Equipo> ListaEquipos;

	/** La Lista de Jornadas. */
	private List<Jornada> ListaJornadas;

	/**
	 * Instancia para crear una nueva Temporada por defecto.
	 */
	// Contrustor por defecto
	public Temporada() {
		this.Numero = null;
		this.FechaInicio = new Fecha();
		this.Estado = "PROXIMAMENTE";
		this.ListaEquipos = new ArrayList<Equipo>();
		this.ListaJornadas = new ArrayList<Jornada>();
	}

	/**
	 * Instancia para crear una nueva Temporada copia.
	 *
	 * @param t la Temporada copia
	 */
	// Contructor copia
	public Temporada(Temporada t) {
		this.Numero = t.Numero;
		this.FechaInicio = t.FechaInicio;
		this.Estado = t.Estado;
		this.ListaEquipos = t.ListaEquipos;
		this.ListaJornadas = t.ListaJornadas;
	}

	/**
	 * Instancia para crear una nueva Temporada personalizada.
	 *
	 * @param n  el Numero
	 * @param fi la Fecha de Inicio
	 * @param e  el Estado
	 * @param le la Lista de Equipos
	 */
	// Contructor personalizado
	public Temporada(Integer n, Fecha fi, String e, List<Equipo> le) {
		this.Numero = n;
		this.FechaInicio = fi;
		this.Estado = e;
		this.ListaEquipos = le;
		this.ListaJornadas = null;
	}
	
	/**
	 * Instancia para crear una nueva Temporada personalizada.
	 *
	 * @param n  el Numero
	 * @param fi la Fecha de Inicio
	 * @param e  el Estado
	 * @param le la Lista de Equipos
	 * @param lj la Lista de Jornadas
	 * 
	 */
	// Contructor personalizado
	public Temporada(Integer n, Fecha fi, String e, List<Equipo> le, List<Jornada> lj) {
		this.Numero = n;
		this.FechaInicio = fi;
		this.Estado = e;
		this.ListaEquipos = le;
		this.ListaJornadas = lj;
	}

	/**
	 * Funcion To string.
	 *
	 * @return Informacion en formato String
	 */
	public String toString() {
		return ("Temporada " + Numero + " [Fecha Inicio:" + FechaInicio + "] Estado: " + Estado);
	}

	/**
	 * Obtener el Numero.
	 *
	 * @return el Numero
	 */
	public Integer getNumero() {
		return Numero;
	}

	/**
	 * Asignar el Numero.
	 *
	 * @param numero el nuevo Numero
	 */
	public void setNumero(Integer numero) {
		Numero = numero;
	}

	/**
	 * Obtener el Estado.
	 *
	 * @return el Estado
	 */
	public String getEstado() {
		return Estado;
	}

	/**
	 * Asignar el Estado.
	 *
	 * @param estado el nuevo Estado
	 */
	public void setEstado(String estado) {
		Estado = estado;
	}

	/**
	 * Obtener la Lista de Equipos.
	 *
	 * @return la Lista de Equipos
	 */
	public List<Equipo> getListaEquipos() {
		return ListaEquipos;
	}

	/**
	 * Asignar la Lista de Equipos.
	 *
	 * @param listaEquipos la nueva Lista de Equipos
	 */
	public void setListaEquipos(List<Equipo> listaEquipos) {
		ListaEquipos = listaEquipos;
	}

	/**
	 * Obtener la Lista de Jornadas.
	 *
	 * @return la Lista de Jornadas
	 */
	public List<Jornada> getListaJornadas() {
		return ListaJornadas;
	}

	/**
	 * Asignar la Lista de Jornadas.
	 *
	 * @param listaJornadas la nueva Lista de Jornadas
	 */
	public void setListaJornadas(List<Jornada> listaJornadas) {
		ListaJornadas = listaJornadas;
	}

	/**
	 * Obtener la Fecha de Inicio.
	 *
	 * @return la Fecha de Inicio
	 */
	public Fecha getFechaInicio() {
		return FechaInicio;
	}

	/**
	 * Asignar la Fecha de Inicio.
	 *
	 * @param fechaInicio la nueva Fecha de Inicio
	 */
	public void setFechaInicio(Fecha fechaInicio) {
		FechaInicio = fechaInicio;
	}

	/**
	 * Funcion Hash code.
	 *
	 * @return el hashCode de los Objetos a Comparar
	 */
	@Override
	public int hashCode() {
		return Objects.hash(Numero, FechaInicio);
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
		Temporada other = (Temporada) obj;
		// Si tienen el mismo contenido devuelvo true
		return Objects.equals(this.Numero, other.Numero);
	}

	/**
	 * Funcion Compare to.
	 *
	 * @param other Otra Temporada
	 * @return un int, que indica cual es mayor que el otro o si son iguales
	 */
	public int compareTo(Temporada other) {
		// Si el valor de Dni en null se le asigna 0
		if (other.Numero.equals(null)) {
			other.setNumero(0);
		} else if (this.Numero.equals(null)) {
			this.setNumero(0);
		}
		return this.Numero.compareTo(other.Numero);
	}

	/**
	 * Carga la lista de temporadas disponibles desde la base de datos.
	 *
	 * @return Una lista de objetos Temporada que representan las temporadas disponibles.
	 */
	public static ArrayList<Temporada> cargarTemporadas() {
      ArrayList<Temporada> temporadas = new ArrayList<>();

      Connection conexion = null;
      PreparedStatement psTemporadas = null;
      ResultSet rsTemporadas = null;

      try {
          conexion = DriverManager.getConnection("jdbc:mysql://localhost/CSLeague", "root", "");

          // Consulta para obtener todas las temporadas excepto la temporada 0
          String queryTemporadas = "SELECT t.Numero, t.FechaInicio, t.Estado FROM Temporada t WHERE t.Numero != 0";
          psTemporadas = conexion.prepareStatement(queryTemporadas);
          rsTemporadas = psTemporadas.executeQuery();

          while (rsTemporadas.next()) {
              int numeroTemporada = rsTemporadas.getInt("Numero");
              Date fechaInicioSql = rsTemporadas.getDate("FechaInicio");
              String estado = rsTemporadas.getString("Estado");

              // Convertir la fecha de SQL a objeto Fecha
              Fecha fechaInicio = convertirAFecha(fechaInicioSql);

              // Obtener equipos y sus datos relacionados
              List<Equipo> equiposTemporada = cargarEquiposPorTemporada(conexion, numeroTemporada);
              Temporada temporadaParaJornadas = new Temporada(numeroTemporada, fechaInicio, estado, equiposTemporada);
              List<Jornada> jornadasTemporada = cargarJornadasPorTemporada(conexion, temporadaParaJornadas);

              // Crear objeto Temporada y agregarlo a la lista
              Temporada temporada = new Temporada(numeroTemporada, fechaInicio, estado, equiposTemporada, jornadasTemporada);
              temporadas.add(temporada);
          }
      } catch (SQLException e) {
          e.printStackTrace();
      } finally {
          try {
              if (rsTemporadas != null) rsTemporadas.close();
              if (psTemporadas != null) psTemporadas.close();
              if (conexion != null) conexion.close();
          } catch (SQLException e) {
              e.printStackTrace();
          }
      }

      return temporadas;
  }

	/**
	 * Carga la lista de equipos participantes en una temporada específica.
	 *
	 * @param conexion        La conexión a la base de datos.
	 * @param numeroTemporada El número de la temporada para la cual se cargan los equipos.
	 * @return Una lista de objetos Equipo que participan en la temporada especificada.
	 * @throws SQLException Si ocurre algún error al ejecutar la consulta SQL.
	 */
	private static List<Equipo> cargarEquiposPorTemporada(Connection conexion, int numeroTemporada) throws SQLException {
	    List<Equipo> equipos = new ArrayList<>();
	    // Consulta SQL para obtener los datos de los equipos en la temporada específica
	    String queryEquipos = "SELECT DISTINCT e.Nombre, e.FechaCreacion, tp.Escudo, tp.Descripcion, " +
	            "ec.Entrenador, ec.Nombre AS NombreEntrenador, ec.Apellido, ec.Nacionalidad, en.FechaAlta " +
	            "FROM Equipo e " +
	            "JOIN EntrenadorContratado ec ON e.Nombre = ec.Equipo " +
	            "JOIN Entrenador en ON ec.Entrenador = en.DNI " +
	            "JOIN TemporadaParticipada tp ON e.Nombre = tp.Equipo " +
	            "WHERE tp.Temporada = ?";

	    PreparedStatement psEquipos = conexion.prepareStatement(queryEquipos);
	    psEquipos.setInt(1, numeroTemporada);
	    ResultSet rsEquipos = psEquipos.executeQuery();

	    while (rsEquipos.next()) {
	        // Obtener datos de cada equipo
	        String nombreEquipo = rsEquipos.getString("Nombre");
	        String escudo = rsEquipos.getString("Escudo");
	        String descripcion = rsEquipos.getString("Descripcion");
	        Date fechaCreacionSql = rsEquipos.getDate("FechaCreacion");
	        Fecha fechaCreacion = convertirAFecha(fechaCreacionSql);

	        String dniEntrenador = rsEquipos.getString("Entrenador");
	        String nombreEntrenador = rsEquipos.getString("NombreEntrenador");
	        String apellidoEntrenador = rsEquipos.getString("Apellido");
	        String nacionalidadEntrenador = rsEquipos.getString("Nacionalidad");
	        Date fechaAltaEntrenadorSql = rsEquipos.getDate("FechaAlta");
	        Fecha fechaAltaEntrenador = convertirAFecha(fechaAltaEntrenadorSql);

	        // Cargar jugadores para este equipo
	        List<Jugador> jugadores = cargarJugadoresPorEquipo(conexion, nombreEquipo, numeroTemporada);

	        // Crear objeto Entrenador
	        Participante participante = new Participante(dniEntrenador, nombreEntrenador, apellidoEntrenador, nacionalidadEntrenador);
	        Entrenador entrenador = new Entrenador(participante, fechaAltaEntrenador);

	        // Crear objeto Equipo y agregarlo a la lista
	        Equipo equipo = new Equipo(nombreEquipo, escudo, descripcion, fechaCreacion, entrenador, jugadores);
	        equipos.add(equipo);
	    }

	    // Cerrar ResultSet y PreparedStatement
	    rsEquipos.close();
	    psEquipos.close();

	    return equipos;
	}

	/**
	 * Carga la lista de jugadores de un equipo en una temporada específica.
	 *
	 * @param conexion     La conexión a la base de datos.
	 * @param nombreEquipo El nombre del equipo del cual se cargan los jugadores.
	 * @param numeroTemporada El número de la temporada en la cual se cargan los jugadores.
	 * @return Una lista de objetos Jugador que pertenecen al equipo especificado en la temporada.
	 * @throws SQLException Si ocurre algún error al ejecutar la consulta SQL.
	 */
	private static List<Jugador> cargarJugadoresPorEquipo(Connection conexion, String nombreEquipo, int numeroTemporada) throws SQLException {
	    List<Jugador> jugadores = new ArrayList<>();
	    // Consulta SQL para obtener los datos de los jugadores del equipo en la temporada específica
	    String queryJugadores = "SELECT j.DNI, jc.Nombre, jc.Apellido, jc.Nacionalidad, jc.Foto, jc.Rol, j.FechaNacimiento " +
	            "FROM JugadorContratado jc " +
	            "JOIN Jugador j ON jc.Jugador = j.DNI " +
	            "WHERE jc.Equipo = ? AND jc.Temporada = ?";

	    PreparedStatement psJugadores = conexion.prepareStatement(queryJugadores);
	    psJugadores.setString(1, nombreEquipo);
	    psJugadores.setInt(2, numeroTemporada);
	    ResultSet rsJugadores = psJugadores.executeQuery();

	    while (rsJugadores.next()) {
	        // Obtener datos de cada jugador
	        String dniJugador = rsJugadores.getString("DNI");
	        String nombreJugador = rsJugadores.getString("Nombre");
	        String apellidoJugador = rsJugadores.getString("Apellido");
	        String nacionalidadJugador = rsJugadores.getString("Nacionalidad");
	        String fotoJugador = rsJugadores.getString("Foto");
	        String posicionJugador = rsJugadores.getString("Rol");
	        Date fechaNacimientoSql = rsJugadores.getDate("FechaNacimiento");
	        Fecha fechaNacimiento = convertirAFecha(fechaNacimientoSql);

	        // Crear objeto Jugador y agregarlo a la lista
	        Participante participanteJugador = new Participante(dniJugador, nombreJugador, apellidoJugador, nacionalidadJugador);
	        Jugador jugador = new Jugador(participanteJugador, fotoJugador, posicionJugador, fechaNacimiento);
	        jugadores.add(jugador);
	    }

	    // Cerrar ResultSet y PreparedStatement
	    rsJugadores.close();
	    psJugadores.close();

	    return jugadores;
	}

  
	/**
	 * Carga la lista de jornadas de una temporada específica.
	 *
	 * @param conexion   La conexión a la base de datos.
	 * @param temporada  La temporada de la cual se cargan las jornadas.
	 * @return Una lista de objetos Jornada que pertenecen a la temporada especificada.
	 * @throws SQLException Si ocurre algún error al ejecutar la consulta SQL.
	 */
	private static List<Jornada> cargarJornadasPorTemporada(Connection conexion, Temporada temporada) throws SQLException {
	    List<Jornada> jornadas = new ArrayList<>();
	    // Consulta SQL para obtener las jornadas de la temporada especificada
	    String queryJornadas = "SELECT Temporada, Numero, Fecha FROM Jornada WHERE Temporada = ?";

	    PreparedStatement psJornadas = conexion.prepareStatement(queryJornadas);
	    psJornadas.setInt(1, temporada.getNumero());
	    ResultSet rsJornadas = psJornadas.executeQuery();

	    while (rsJornadas.next()) {
	        int numeroJornada = rsJornadas.getInt("Numero");
	        Date fechaJornadaSql = rsJornadas.getDate("Fecha");

	        // Convertir la fecha de SQL a objeto Fecha
	        Fecha fechaJornada = convertirAFecha(fechaJornadaSql);

	        // Cargar partidos para esta jornada
	        List<Partido> partidos = cargarPartidosPorTemporada(conexion, numeroJornada, temporada);

	        // Crear objeto Jornada y agregarlo a la lista de jornadas
	        Jornada jornada = new Jornada(numeroJornada, fechaJornada, temporada, partidos);
	        jornadas.add(jornada);
	    }

	    // Cerrar ResultSet y PreparedStatement
	    rsJornadas.close();
	    psJornadas.close();

	    return jornadas;
	}

  
	/**
	 * Carga la lista de partidos de una jornada específica de una temporada.
	 *
	 * @param conexion      La conexión a la base de datos.
	 * @param numeroJornada El número de la jornada de la cual se cargan los partidos.
	 * @param temporada     La temporada a la cual pertenece la jornada.
	 * @return Una lista de objetos Partido que pertenecen a la jornada y temporada especificadas.
	 * @throws SQLException Si ocurre algún error al ejecutar la consulta SQL.
	 */
	private static List<Partido> cargarPartidosPorTemporada(Connection conexion, int numeroJornada, Temporada temporada) throws SQLException {
	    List<Partido> partidos = new ArrayList<>();
	    // Consulta SQL para obtener los partidos de la jornada y temporada especificadas
	    String queryPartidos = "SELECT Temporada, Jornada, EquipoLocal, EquipoVisitante, PuntosLocal, PuntosVisitante, Jugado FROM Partido WHERE Temporada = ? AND Jornada = ?";

	    PreparedStatement psPartidos = conexion.prepareStatement(queryPartidos);
	    psPartidos.setInt(1, temporada.getNumero());
	    psPartidos.setInt(2, numeroJornada);
	    ResultSet rsPartidos = psPartidos.executeQuery();

	    while (rsPartidos.next()) {
	        String nombreEquipoLocal = rsPartidos.getString("EquipoLocal");
	        String nombreEquipoVisitante = rsPartidos.getString("EquipoVisitante");
	        Integer puntosLocal = rsPartidos.getInt("PuntosLocal");
	        Integer puntosVisitante = rsPartidos.getInt("PuntosVisitante");
	        Boolean jugado = rsPartidos.getBoolean("Jugado");

	        // Cargar objetos Equipo para el equipo local y visitante
	        Equipo equipoLocal = cargarEquipo(conexion, nombreEquipoLocal, temporada.getNumero());
	        Equipo equipoVisitante = cargarEquipo(conexion, nombreEquipoVisitante, temporada.getNumero());

	        // Crear objeto Partido y agregarlo a la lista de partidos
	        Partido partido = new Partido(equipoLocal, equipoVisitante, puntosLocal, puntosVisitante, jugado);
	        partidos.add(partido);
	    }

	    // Cerrar ResultSet y PreparedStatement
	    rsPartidos.close();
	    psPartidos.close();

	    return partidos;
	}

  
	/**
	 * Carga la información de un equipo específico en una temporada.
	 *
	 * @param conexion     La conexión a la base de datos.
	 * @param nombreEquipo  El nombre del equipo que se desea cargar.
	 * @param numeroTemporada El número de la temporada en la cual se carga el equipo.
	 * @return El objeto Equipo cargado con su información.
	 * @throws SQLException Si ocurre algún error al ejecutar la consulta SQL.
	 */
	private static Equipo cargarEquipo(Connection conexion, String nombreEquipo, int numeroTemporada) throws SQLException {
	    Equipo equipo = null;
	    // Consulta SQL para obtener la información del equipo en la temporada especificada
	    String queryEquipo = "SELECT e.Nombre, e.FechaCreacion, tp.Escudo, tp.Descripcion, " +
	            "ec.Entrenador, ec.Nombre AS NombreEntrenador, ec.Apellido, ec.Nacionalidad, en.FechaAlta " +
	            "FROM Equipo e " +
	            "JOIN EntrenadorContratado ec ON e.Nombre = ec.Equipo " +
	            "JOIN Entrenador en ON ec.Entrenador = en.DNI " +
	            "JOIN TemporadaParticipada tp ON e.Nombre = tp.Equipo " +
	            "WHERE e.Nombre = ? AND tp.Temporada = ?";

	    PreparedStatement psEquipo = conexion.prepareStatement(queryEquipo);
	    psEquipo.setString(1, nombreEquipo);
	    psEquipo.setInt(2, numeroTemporada);
	    ResultSet rsEquipo = psEquipo.executeQuery();

	    if (rsEquipo.next()) {
	        // Obtener datos del equipo
	        String escudo = rsEquipo.getString("Escudo");
	        String descripcion = rsEquipo.getString("Descripcion");
	        Date fechaCreacionSql = rsEquipo.getDate("FechaCreacion");
	        Fecha fechaCreacion = convertirAFecha(fechaCreacionSql);

	        String dniEntrenador = rsEquipo.getString("Entrenador");
	        String nombreEntrenador = rsEquipo.getString("NombreEntrenador");
	        String apellidoEntrenador = rsEquipo.getString("Apellido");
	        String nacionalidadEntrenador = rsEquipo.getString("Nacionalidad");
	        Date fechaAltaEntrenadorSql = rsEquipo.getDate("FechaAlta");
	        Fecha fechaAltaEntrenador = convertirAFecha(fechaAltaEntrenadorSql);

	        // Cargar jugadores para este equipo
	        List<Jugador> jugadores = cargarJugadoresPorEquipo(conexion, nombreEquipo, numeroTemporada);

	        // Crear objeto Entrenador
	        Participante participante = new Participante(dniEntrenador, nombreEntrenador, apellidoEntrenador, nacionalidadEntrenador);
	        Entrenador entrenador = new Entrenador(participante, fechaAltaEntrenador);

	        // Crear objeto Equipo
	        equipo = new Equipo(nombreEquipo, escudo, descripcion, fechaCreacion, entrenador, jugadores);
	    }

	    // Cerrar ResultSet y PreparedStatement
	    rsEquipo.close();
	    psEquipo.close();

	    return equipo;
	}


	/**
	 * Convierte una fecha de tipo java.sql.Date a un objeto de tipo Fecha personalizado.
	 *
	 * @param fechaSql La fecha en formato java.sql.Date a convertir.
	 * @return Un objeto de tipo Fecha con el día, mes y año de la fecha SQL.
	 */
	private static Fecha convertirAFecha(Date fechaSql) {
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(fechaSql);
	    int año = calendar.get(Calendar.YEAR);
	    int mes = calendar.get(Calendar.MONTH) + 1;
	    int dia = calendar.get(Calendar.DAY_OF_MONTH);
	    return new Fecha(dia, mes, año);
	}

}
