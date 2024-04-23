package definicion;

import java.io.Serializable;
import java.util.Objects;

/**
 * La Clase Estadisticas.
 */
public class Estadisticas implements Serializable {

	/** La Constante serialVersionUID generada para identificarse. */
	private static final long serialVersionUID = -6139057543933527268L;

	/** La Temporada. */
	private Temporada Temporada;

	/** Los Puntos Totales. */
	private Integer PuntosTotales;

	/** Los Partidos Jugados. */
	private Integer PartidasJugadas;

	/** Los Partidos Ganados. */
	private Integer PartidasGanadas;

	/** Los Partidos Perdidos. */
	private Integer PartidasPerdidas;

	/** Las Rondas de Diferencia. */
	private Integer RondasDiferencia;

	/**
	 * Instancia para crear unas nuevas Estadisticas por defecto.
	 */
	// Contrustor por defecto
	public Estadisticas() {
		this.Temporada = new Temporada();
		this.PuntosTotales = 0;
		this.PartidasJugadas = 0;
		this.PartidasGanadas = 0;
		this.PartidasPerdidas = 0;
		this.RondasDiferencia = 0;
	}

	/**
	 * Instancia para crear unas nuevas Estadisticas copia.
	 *
	 * @param e las Estadisticas copia
	 */
	// Contructor copia
	public Estadisticas(Estadisticas e) {
		this.Temporada = e.Temporada;
		this.PuntosTotales = e.PuntosTotales;
		this.PartidasJugadas = e.PartidasJugadas;
		this.PartidasGanadas = e.PartidasGanadas;
		this.PartidasPerdidas = e.PartidasPerdidas;
		this.RondasDiferencia = e.RondasDiferencia;
	}

	/**
	 * Instancia para crear unas nuevas Estadisticas personalizadas.
	 *
	 * @param t  la Temporada
	 * @param pt los Puntos Totales
	 * @param pj los Partidos Jugados
	 * @param pg los Partidos Ganados
	 * @param pp Los Partidos Perdidos
	 * @param rd Las Rondas de Diferencia
	 */
	// Contructor personalizado
	public Estadisticas(Temporada t, Integer pt, Integer pj, Integer pg, Integer pp,
			Integer rd) {
		this.Temporada = t;
		this.PuntosTotales = pt;
		this.PartidasJugadas = pj;
		this.PartidasGanadas = pg;
		this.PartidasPerdidas = pp;
		this.RondasDiferencia = rd;
	}

	/**
	 * Instancia para crear unas nuevas Estadisticas personalizadas.
	 *
	 * @param t la Temporada
	 */
	// Contructor personalizado
	public Estadisticas(Temporada t) {
		this.Temporada = t;
		this.PuntosTotales = 0;
		this.PartidasJugadas = 0;
		this.PartidasGanadas = 0;
		this.PartidasPerdidas = 0;
		this.RondasDiferencia = 0;
	}

	/**
	 * Obtener los Puntos Totales.
	 *
	 * @return los Puntos Totales
	 */
	public Integer getPuntosTotales() {
		return PuntosTotales;
	}

	/**
	 * Asignar los Puntos Totales.
	 *
	 * @param puntosTotales los nuevos Puntos Totales
	 */
	public void setPuntosTotales(Integer puntosTotales) {
		PuntosTotales = puntosTotales;
	}

	/**
	 * Obtener los Partidos Jugados.
	 *
	 * @return los Partidos Jugados
	 */
	public Integer getPartidosJugados() {
		return PartidasJugadas;
	}

	/**
	 * Asignar los Partidos Jugados.
	 *
	 * @param partidosJugados los nuevos Partidos Jugados
	 */
	public void setPartidosJugados(Integer partidosJugados) {
		PartidasJugadas = partidosJugados;
	}

	/**
	 * Obtener los Partidos Ganados.
	 *
	 * @return los Partidos Ganados
	 */
	public Integer getPartidosGanados() {
		return PartidasGanadas;
	}

	/**
	 * Asignar los Partidos Ganados.
	 *
	 * @param partidosGanados los nuevos Partidos Ganados
	 */
	public void setPartidosGanados(Integer partidosGanados) {
		PartidasGanadas = partidosGanados;
	}


	/**
	 * Obtener los Partidos Perdidos.
	 *
	 * @return los Partidos Perdidos
	 */
	public Integer getPartidosPerdidos() {
		return PartidasPerdidas;
	}

	/**
	 * Asignar los Partidos Perdidos.
	 *
	 * @param partidosPerdidos los nuevos Partidos Perdidos
	 */
	public void setPartidosPerdidos(Integer partidosPerdidos) {
		PartidasPerdidas = partidosPerdidos;
	}

	
	/**
	 * Obtener las Rondas de Diferencia.
	 *
	 * @return RondasDiferencia las Rondas de Diferencia
	 */
	public Integer getRondasDiferencia() {
		return RondasDiferencia;
	}

	/**
	 * Asignar las Rondas de Diferencia.
	 *
	 * @param rondasDiferencia las nuevas Rondas de Diferencia
	 */
	public void setRondasDiferencia(Integer rondasDiferencia) {
		RondasDiferencia = rondasDiferencia;
	}

	/**
	 * Obtener la Temporada.
	 *
	 * @return la Temporada
	 */
	public Temporada getTemporada() {
		return Temporada;
	}

	/**
	 * Asignar la Temporada.
	 *
	 * @param temporada la nueva Temporada
	 */
	public void setTemporada(Temporada temporada) {
		Temporada = temporada;
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
	 * Funcion To String.
	 *
	 * @return Informacion en formato String
	 */
	public String toString() {
		return (PuntosTotales + " " + PartidasJugadas + " " + PartidasGanadas + " "
				+ PartidasPerdidas + " " + RondasDiferencia);
	}

	/**
	 * Funcion Hash code.
	 *
	 * @return el hashCode de los Objetos a Comparar
	 */
	@Override
	public int hashCode() {
		return Objects.hash(PuntosTotales);
	}

	/**
	 * Funcion Compare to.
	 *
	 * @param estadisticas1 las Estadisticas de el Equipo 1
	 * @param estadisticas2 las Estadisticas de el Equipo 2
	 * @return un int, que indica cual es mayor que el otro o si son iguales
	 */
	public int compareTo(Estadisticas estadisticas1, Estadisticas estadisticas2) {
		// Comparar por puntos totales (orden descendente)
		int comparacion = Integer.compare(estadisticas2.getPuntosTotales(), estadisticas1.getPuntosTotales());
		if (comparacion != 0) {
			return comparacion;
		}

		// Comparar por partidos ganados (orden descendente)
		comparacion = Integer.compare(estadisticas2.getPartidosGanados(), estadisticas1.getPartidosGanados());
		if (comparacion != 0) {
			return comparacion;
		}

		// Comparar por partidos perdidos (orden descendente)
		comparacion = Integer.compare(estadisticas2.getPartidosPerdidos(), estadisticas1.getPartidosPerdidos());
		if (comparacion != 0) {
			return comparacion;
		}

		// Comparar las Rondas de Diferencia (orden descendente)
		return Integer.compare(estadisticas2.getRondasDiferencia(), estadisticas1.getRondasDiferencia());
	}

}
