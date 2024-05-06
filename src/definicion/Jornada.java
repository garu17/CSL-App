package definicion;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;


/**
 * La Clase Jornada.
 */
public class Jornada implements Serializable {

	/** La Constante serialVersionUID generada para identificarse. */
	private static final long serialVersionUID = 5312527782867029955L;

	/** El Numero. */
	private Integer Numero;

	/** La Fecha. */
	private Fecha Fecha;

	/** La Temporada. */
	private Temporada Temporada;

	/** La Lista de Partidos. */
	private List<Partido> ListaPartidos;

	/**
	 * Instancia para crear una nueva Jornada por defecto.
	 */
	// Contrustor por defecto
	public Jornada() {
		this.Numero = null;
		this.Fecha = new Fecha();
		this.Temporada = new Temporada();
		this.ListaPartidos = new ArrayList<Partido>();
	}

	/**
	 * Instancia para crear una nueva Jornada copia.
	 *
	 * @param j la Jornada copia
	 */
	// Contructor copia
	public Jornada(Jornada j) {
		this.Numero = j.Numero;
		this.Fecha = j.Fecha;
		this.Temporada = j.Temporada;
		this.ListaPartidos = j.ListaPartidos;
	}

	/**
	 * Instancia para crear una nueva Jornada personalizada.
	 *
	 * @param n  el Numero
	 * @param f  la Fecha
	 * @param t  la Temporada
	 * @param lp la Lista de Partidos
	 */
	// Contructor personalizado
	public Jornada(Integer n, Fecha f, Temporada t, List<Partido> lp) {
		this.Numero = n;
		this.Fecha = f;
		this.Temporada = t;
		this.ListaPartidos = lp;
	}

	/**
	 * Funcion To string.
	 *
	 * @return Informacion en formato String
	 */
	public String toString() {
		return ("Numero:" + Numero + ", Fecha:" + Fecha + ", Lista de Partidos:" + ListaPartidos);
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
	 * Obtener la Lista de Partidos.
	 *
	 * @return la Lista de Partidos
	 */
	public List<Partido> getListaPartidos() {
		return ListaPartidos;
	}

	/**
	 * Asignar la Lista de Partidos.
	 *
	 * @param listaPartidos la Lista de Partidos
	 */
	public void setListaPartidos(List<Partido> listaPartidos) {
		ListaPartidos = listaPartidos;
	}

	/**
	 * Funcion Hash code.
	 *
	 * @return el hashCode de los Objetos a Comparar
	 */
	@Override
	public int hashCode() {
		return Objects.hash(Numero);
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
		Jornada other = (Jornada) obj;
		// Si tienen el mismo contenido devuelvo true
		return Objects.equals(this.Numero, other.Numero);
	}

	/**
	 * Funcion Compare to.
	 *
	 * @param other Otra Jornada
	 * @return un int, que indica cual es mayor que el otro o si son iguales
	 */
	public int compareTo(Jornada other) {
		// Si el valor de Dni en null se le asigna 0
		if (other.Numero.equals(null)) {
			other.setNumero(0);
		} else if (this.Numero.equals(null)) {
			this.setNumero(0);
		}

		return this.Numero.compareTo(other.Numero);
	}

	/**
	 * Funcion para Generar jornadas.
	 *
	 * @param equipos     la Lista de Equipos que hay
	 * @param fechaInicio la Fecha de Inicio de la Temporada
	 * @param temporada   la Temporada en donde se generan
	 * @return el ArrayList de las Jornadas
	 */
	public static ArrayList<Jornada> generarJornadas(List<Equipo> equipos, Fecha fechaInicio, Temporada temporada) {
		ArrayList<Jornada> jornadas = new ArrayList<>();

		// Agregar un equipo para descansar si el número de equipos es impar
		if (equipos.size() % 2 != 0) {
			equipos.add(new Equipo("Equipo para Descansar"));
		}

		int numEquipos = equipos.size();
		int numJornadas = numEquipos - 1;

		// Listas para almacenar los índices de los equipos en las jornadas de ida y
		// vuelta
		List<Integer> indicesIda = new ArrayList<>();
		List<Integer> indicesVuelta = new ArrayList<>();

		// Inicializar las listas con los índices de los equipos
		for (int i = 0; i < numEquipos; i++) {
			indicesIda.add(i);
			indicesVuelta.add(i);
		}

		// Generar jornadas de ida
		for (int i = 0; i < numJornadas; i++) {
			Jornada jornadaIda = new Jornada();
			jornadaIda.setNumero(i + 1);
			jornadaIda.setFecha(sacarFechaNueva(fechaInicio, i));
			jornadaIda.setTemporada(temporada);

			// Crear partidos para la jornada de ida
			for (int j = 0; j < numEquipos / 2; j++) {
				int index1 = indicesIda.get(j);
				int index2 = indicesIda.get(numEquipos - 1 - j);

				Equipo equipo1 = equipos.get(index1);
				Equipo equipo2 = equipos.get(index2);

				Partido partido = new Partido(equipo1, equipo2, -1, -1);
				jornadaIda.getListaPartidos().add(partido);
			}

			jornadas.add(jornadaIda);

			// Rotar los equipos para la próxima jornada de ida
			indicesIda.add(1, indicesIda.remove(indicesIda.size() - 1));
		}

		// Generar jornadas de vuelta
		for (int i = 0; i < numJornadas; i++) {
			Jornada jornadaVuelta = new Jornada();
			int numeroJornadaVuelta = numJornadas + i + 1;
			jornadaVuelta.setNumero(numeroJornadaVuelta);
			jornadaVuelta.setFecha(sacarFechaNueva(fechaInicio, i + numJornadas + 1));
			jornadaVuelta.setTemporada(temporada);

			// Crear partidos para la jornada de vuelta
			for (int j = 0; j < numEquipos / 2; j++) {
				int index1 = indicesVuelta.get(j);
				int index2 = indicesVuelta.get(numEquipos - 1 - j);

				// Se invierten los equipos en las jorandas de vuelta
				Equipo equipo1 = equipos.get(index2);
				Equipo equipo2 = equipos.get(index1);

				Partido partido = new Partido(equipo1, equipo2, -1, -1);
				jornadaVuelta.getListaPartidos().add(partido);
			}

			jornadas.add(jornadaVuelta);

			// Rotar los equipos para la próxima jornada de vuelta
			indicesVuelta.add(1, indicesVuelta.remove(indicesVuelta.size() - 1));
		}

		return jornadas;
	}

	/**
	 * Funcion para Sacar la Fecha en cada Jornada.
	 *
	 * @param fechaInicio la Fecha de Inicio de la Temporada
	 * @param semanas     el numero de semanas que se suman a fechaInicio
	 * @return la Fecha de la Jornada
	 */
	public static Fecha sacarFechaNueva(Fecha fechaInicio, int semanas) {
		Calendar calendar = fechaInicio.getCalendar();
		// Añadir las semanas a la fecha de fin
		calendar.add(Calendar.WEEK_OF_YEAR, semanas);
		return new Fecha(calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH) + 1,
				calendar.get(Calendar.YEAR));
	}
	
	/**
	 * Funcion para Comprobar si la Temporada esta Activa y se permite su edicion.
	 *
	 * @param edicion, si se permite la edicion de los partidos en el caso en el que la temporada este activa
	 * @return true, if successful
	 */
	private static boolean permitirEdicion(Boolean edicion) {
		if (Seleccion.getTemporadaSeleccionada().getEstado().equals("ACTIVA") && edicion) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Funcion para Crear los Paneles de las Jornadas.
	 *
	 * @param jornadas la lista de jornadas
	 * @param tabbedPaneJornadas el tabbedpane al que aplicar la configuracion
	 * @param edicion, si se permite la edicion de los partidos en el caso en el que la temporada este activa
	 */
	public static void crearPanelesJornadas(List<Jornada> jornadas, JTabbedPane tabbedPaneJornadas, Boolean edicion) {
		// Iterar sobre la lista de jornadas
		for (Jornada jornada : jornadas) {
			// Crear un nuevo panel para la jornada
			JPanel panelJornadaX = new JPanel();
			// Agregar una pestaña al contenedor con el número de jornada como título y el
			// panel recién creado
			tabbedPaneJornadas.addTab("Jornada " + jornada.getNumero(), null, panelJornadaX, null);
			// Configurar el diseño del panel con un GridBagLayout
			GridBagLayout gbl_panelJornadaX = new GridBagLayout();
			gbl_panelJornadaX.columnWidths = new int[] { 5, 15, 10, 10, 10, 15, 5, 0 }; // Ajusta las anchuras de las
																													// columnas
			gbl_panelJornadaX.rowHeights = new int[] { 70, 70, 0 };
			gbl_panelJornadaX.columnWeights = new double[] { 0.0, 0.15, 0.1, 0.1, 0.1, 0.15, 0.0, Double.MIN_VALUE };
			gbl_panelJornadaX.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
			panelJornadaX.setLayout(gbl_panelJornadaX);

			JLabel lblJornada = new JLabel("Jornada " + jornada.getNumero());
			lblJornada.setFont(new Font("Tahoma", Font.BOLD, 25));
			lblJornada.setHorizontalAlignment(SwingConstants.CENTER);
			lblJornada.setHorizontalTextPosition(SwingConstants.LEADING);
			GridBagConstraints gbc_lblJornada = new GridBagConstraints();
			gbc_lblJornada.insets = new Insets(0, 0, 5, 5);
			gbc_lblJornada.gridwidth = 5;
			gbc_lblJornada.gridx = 1;
			gbc_lblJornada.gridy = 0;
			panelJornadaX.add(lblJornada, gbc_lblJornada);

			JLabel lblFecha = new JLabel("(" + jornada.getFecha() + ")");
			lblFecha.setHorizontalTextPosition(SwingConstants.LEADING);
			lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
			lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 15));
			GridBagConstraints gbc_lblFecha = new GridBagConstraints();
			gbc_lblFecha.anchor = GridBagConstraints.SOUTH;
			gbc_lblFecha.fill = GridBagConstraints.HORIZONTAL;
			gbc_lblFecha.insets = new Insets(0, 0, 5, 5);
			gbc_lblFecha.gridwidth = 5;
			gbc_lblFecha.gridx = 1;
			gbc_lblFecha.gridy = 0;
			panelJornadaX.add(lblFecha, gbc_lblFecha);

			List<Partido> partidos = jornada.getListaPartidos();
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridwidth = 1;
			int yPosition = 1; // Iniciar desde la segunda fila para los partidos

			// Iterar sobre la lista de partidos
			for (Partido partido : partidos) {
				gbc.gridy = yPosition;

				// Verificar si el equipo local es un equipo de descanso
				if (partido.getEquipoLocal().getNombre().equals("Equipo para Descansar")) {
					JLabel lblDescansa = new JLabel("Descansa");
					lblDescansa.setHorizontalTextPosition(SwingConstants.LEADING);
					lblDescansa.setHorizontalAlignment(SwingConstants.CENTER);
					lblDescansa.setFont(new Font("Tahoma", Font.BOLD, 25));
					GridBagConstraints gbc_lblDescansa = new GridBagConstraints();
					gbc_lblDescansa.fill = GridBagConstraints.HORIZONTAL;
					gbc_lblDescansa.insets = new Insets(0, 0, 0, 5);
					gbc_lblDescansa.gridx = 3;
					gbc_lblDescansa.gridy = yPosition;
					panelJornadaX.add(lblDescansa, gbc_lblDescansa);

					// JLabel para el escudo visitante
					JLabel lblEscudoVisitante = new JLabel("");
					lblEscudoVisitante.setBorder(new LineBorder(new Color(0, 0, 0)));
					lblEscudoVisitante.setHorizontalAlignment(SwingConstants.CENTER);

					lblEscudoVisitante.setPreferredSize(new Dimension(80, 74));

					// Ajustar la imagen del escudo visitante al tamaño especificado
					String escudoVisitantePath = partido.getEquipoVisitante().getEscudo();
					if (escudoVisitantePath != null && !escudoVisitantePath.isEmpty()) {
						ImageIcon escudoVisitanteIcon = new ImageIcon(escudoVisitantePath);
						Image escudoVisitanteImage = escudoVisitanteIcon.getImage().getScaledInstance(80, 74,
								Image.SCALE_SMOOTH);
						lblEscudoVisitante.setIcon(new ImageIcon(escudoVisitanteImage));
					} else {
						lblEscudoVisitante.setIcon(null);
					}

					GridBagConstraints gbc_lblEscudoVisitante = new GridBagConstraints();
					gbc_lblEscudoVisitante.fill = GridBagConstraints.BOTH;
					gbc_lblEscudoVisitante.insets = new Insets(0, 0, 0, 5);
					gbc_lblEscudoVisitante.gridx = 5;
					gbc_lblEscudoVisitante.gridy = yPosition;
					panelJornadaX.add(lblEscudoVisitante, gbc_lblEscudoVisitante);

					JLabel lblEquipoVisitante = new JLabel(partido.getEquipoVisitante().getNombre());
					lblEquipoVisitante.setHorizontalTextPosition(SwingConstants.RIGHT);
					lblEquipoVisitante.setHorizontalAlignment(SwingConstants.CENTER);
					lblEquipoVisitante.setFont(new Font("Tahoma", Font.BOLD, 10));
					GridBagConstraints gbc_lblEquipoVisitante = new GridBagConstraints();
					gbc_lblEquipoVisitante.fill = GridBagConstraints.HORIZONTAL;
					gbc_lblEquipoVisitante.gridx = 6;
					gbc_lblEquipoVisitante.gridy = yPosition;
					panelJornadaX.add(lblEquipoVisitante, gbc_lblEquipoVisitante);

					yPosition += 2;

					continue;
				}

				// Verificar si el equipo visitante es un equipo de descanso
				if (partido.getEquipoVisitante().getNombre().equals("Equipo para Descansar")) {
					JLabel lblDescansa = new JLabel("Descansa");
					lblDescansa.setHorizontalTextPosition(SwingConstants.LEADING);
					lblDescansa.setHorizontalAlignment(SwingConstants.CENTER);
					lblDescansa.setFont(new Font("Tahoma", Font.BOLD, 25));
					GridBagConstraints gbc_lblDescansa = new GridBagConstraints();
					gbc_lblDescansa.fill = GridBagConstraints.HORIZONTAL;
					gbc_lblDescansa.insets = new Insets(0, 0, 0, 5);
					gbc_lblDescansa.gridx = 3;
					gbc_lblDescansa.gridy = yPosition;
					panelJornadaX.add(lblDescansa, gbc_lblDescansa);

					JLabel lblEquipoLocal = new JLabel(partido.getEquipoLocal().getNombre());
					lblEquipoLocal.setHorizontalTextPosition(SwingConstants.RIGHT);
					lblEquipoLocal.setHorizontalAlignment(SwingConstants.CENTER);
					lblEquipoLocal.setFont(new Font("Tahoma", Font.BOLD, 10));
					GridBagConstraints gbc_lblEquipoLocal = new GridBagConstraints();
					gbc_lblEquipoLocal.fill = GridBagConstraints.HORIZONTAL;
					gbc_lblEquipoLocal.insets = new Insets(0, 0, 0, 5);
					gbc_lblEquipoLocal.gridx = 0;
					gbc_lblEquipoLocal.gridy = yPosition;
					panelJornadaX.add(lblEquipoLocal, gbc_lblEquipoLocal);

					// JLabel para el escudo local
					JLabel lblEscudoLocal = new JLabel("");
					lblEscudoLocal.setBorder(new LineBorder(new Color(0, 0, 0)));
					lblEscudoLocal.setHorizontalAlignment(SwingConstants.CENTER);
					lblEscudoLocal.setPreferredSize(new Dimension(80, 74));

					// Ajustar la imagen del escudo local al tamaño especificado
					String escudoLocalPath = partido.getEquipoLocal().getEscudo();
					if (escudoLocalPath != null && !escudoLocalPath.isEmpty()) {
						ImageIcon escudoLocalIcon = new ImageIcon(escudoLocalPath);
						Image escudoLocalImage = escudoLocalIcon.getImage().getScaledInstance(80, 74, Image.SCALE_SMOOTH);
						lblEscudoLocal.setIcon(new ImageIcon(escudoLocalImage));
					} else {
						lblEscudoLocal.setIcon(null);
					}

					GridBagConstraints gbc_lblEscudoLocal = new GridBagConstraints();
					gbc_lblEscudoLocal.fill = GridBagConstraints.BOTH;
					gbc_lblEscudoLocal.insets = new Insets(0, 0, 0, 5);
					gbc_lblEscudoLocal.gridx = 1;
					gbc_lblEscudoLocal.gridy = yPosition;
					panelJornadaX.add(lblEscudoLocal, gbc_lblEscudoLocal);

					yPosition += 2;

					continue;
				}

				JLabel lblEquipoLocal = new JLabel(partido.getEquipoLocal().getNombre());
				lblEquipoLocal.setHorizontalTextPosition(SwingConstants.RIGHT);
				lblEquipoLocal.setHorizontalAlignment(SwingConstants.CENTER);
				lblEquipoLocal.setFont(new Font("Tahoma", Font.BOLD, 10));
				GridBagConstraints gbc_lblEquipoLocal = new GridBagConstraints();
				gbc_lblEquipoLocal.fill = GridBagConstraints.HORIZONTAL;
				gbc_lblEquipoLocal.insets = new Insets(0, 0, 0, 5);
				gbc_lblEquipoLocal.gridx = 0;
				gbc_lblEquipoLocal.gridy = yPosition;
				panelJornadaX.add(lblEquipoLocal, gbc_lblEquipoLocal);

				// JLabel para el escudo local
				JLabel lblEscudoLocal = new JLabel("");
				lblEscudoLocal.setBorder(new LineBorder(new Color(0, 0, 0)));
				lblEscudoLocal.setHorizontalAlignment(SwingConstants.CENTER);
				lblEscudoLocal.setPreferredSize(new Dimension(80, 74));

				// Ajustar la imagen del escudo local al tamaño especificado
				String escudoLocalPath = partido.getEquipoLocal().getEscudo();
				if (escudoLocalPath != null && !escudoLocalPath.isEmpty()) {
					ImageIcon escudoLocalIcon = new ImageIcon(escudoLocalPath);
					Image escudoLocalImage = escudoLocalIcon.getImage().getScaledInstance(80, 74, Image.SCALE_SMOOTH);
					lblEscudoLocal.setIcon(new ImageIcon(escudoLocalImage));
				} else {
					lblEscudoLocal.setIcon(null);
				}

				GridBagConstraints gbc_lblEscudoLocal = new GridBagConstraints();
				gbc_lblEscudoLocal.fill = GridBagConstraints.BOTH;
				gbc_lblEscudoLocal.insets = new Insets(0, 0, 0, 5);
				gbc_lblEscudoLocal.gridx = 1;
				gbc_lblEscudoLocal.gridy = yPosition;
				panelJornadaX.add(lblEscudoLocal, gbc_lblEscudoLocal);

				if (permitirEdicion(edicion)) {
					// Crear JTextField para puntos local
					JTextField textFieldPuntosLocal = new JTextField();
					if (String.valueOf(partido.getPuntosLocal()).equals("-1") || partido.getJugado().equals(false)) {
						textFieldPuntosLocal.setText("");
					} else {
						textFieldPuntosLocal.setText(String.valueOf(partido.getPuntosLocal()));
					}
					textFieldPuntosLocal.putClientProperty("partidoIndex", jornada.getListaPartidos().indexOf(partido));
					textFieldPuntosLocal.putClientProperty("equipo", "local");
					textFieldPuntosLocal.setHorizontalAlignment(JTextField.CENTER);
					textFieldPuntosLocal.setFont(new Font("Tahoma", Font.BOLD, 25));
					textFieldPuntosLocal.setColumns(3); // Set the number of columns to accommodate three digits
					GridBagConstraints gbc_textFieldPuntosLocal = new GridBagConstraints();
					gbc_textFieldPuntosLocal.fill = GridBagConstraints.HORIZONTAL;
					gbc_textFieldPuntosLocal.insets = new Insets(0, 0, 0, 5);
					gbc_textFieldPuntosLocal.gridx = 2;
					gbc_textFieldPuntosLocal.gridy = yPosition;
					panelJornadaX.add(textFieldPuntosLocal, gbc_textFieldPuntosLocal);

					// Crear JTextField para puntos
					JTextField textFieldPuntosVisitante = new JTextField();

					if (String.valueOf(partido.getPuntosVisitante()).equals("-1") || partido.getJugado().equals(false)) {
						textFieldPuntosVisitante.setText("");
					} else {
						textFieldPuntosVisitante.setText(String.valueOf(partido.getPuntosVisitante()));
					}
					textFieldPuntosVisitante.putClientProperty("partidoIndex", jornada.getListaPartidos().indexOf(partido));
					textFieldPuntosVisitante.putClientProperty("equipo", "visitante");
					textFieldPuntosVisitante.setHorizontalAlignment(JTextField.CENTER);
					textFieldPuntosVisitante.setFont(new Font("Tahoma", Font.BOLD, 25));
					textFieldPuntosVisitante.setColumns(3); // Set the number of columns to accommodate three digits
					GridBagConstraints gbc_textFieldPuntosVisitante = new GridBagConstraints();
					gbc_textFieldPuntosVisitante.fill = GridBagConstraints.HORIZONTAL;
					gbc_textFieldPuntosVisitante.insets = new Insets(0, 0, 0, 5);
					gbc_textFieldPuntosVisitante.gridx = 4;
					gbc_textFieldPuntosVisitante.gridy = yPosition;
					panelJornadaX.add(textFieldPuntosVisitante, gbc_textFieldPuntosVisitante);

				} else {
					JLabel lblPuntosLocal = new JLabel();
					if (String.valueOf(partido.getPuntosLocal()).equals("-1")) {
						lblPuntosLocal.setText("0");
					} else {
						lblPuntosLocal.setText(String.valueOf(partido.getPuntosLocal()));
					}
					lblPuntosLocal.setHorizontalTextPosition(SwingConstants.LEADING);
					lblPuntosLocal.setHorizontalAlignment(SwingConstants.CENTER);
					lblPuntosLocal.setFont(new Font("Tahoma", Font.BOLD, 25));
					GridBagConstraints gbc_lblPuntosLocal = new GridBagConstraints();
					gbc_lblPuntosLocal.fill = GridBagConstraints.HORIZONTAL;
					gbc_lblPuntosLocal.insets = new Insets(0, 0, 0, 5);
					gbc_lblPuntosLocal.gridx = 2;
					gbc_lblPuntosLocal.gridy = yPosition;
					panelJornadaX.add(lblPuntosLocal, gbc_lblPuntosLocal);

					JLabel lblPuntosVisitante = new JLabel();
					if (String.valueOf(partido.getPuntosVisitante()).equals("-1")) {
						lblPuntosVisitante.setText("0");
					} else {
						lblPuntosVisitante.setText(String.valueOf(partido.getPuntosVisitante()));
					}
					lblPuntosVisitante.setHorizontalTextPosition(SwingConstants.LEADING);
					lblPuntosVisitante.setHorizontalAlignment(SwingConstants.CENTER);
					lblPuntosVisitante.setFont(new Font("Tahoma", Font.BOLD, 25));
					GridBagConstraints gbc_lblPuntosVisitante = new GridBagConstraints();
					gbc_lblPuntosVisitante.fill = GridBagConstraints.HORIZONTAL;
					gbc_lblPuntosVisitante.insets = new Insets(0, 0, 0, 5);
					gbc_lblPuntosVisitante.gridx = 4;
					gbc_lblPuntosVisitante.gridy = yPosition;
					panelJornadaX.add(lblPuntosVisitante, gbc_lblPuntosVisitante);

				}

				JLabel lblLinea = new JLabel("-");
				lblLinea.setHorizontalTextPosition(SwingConstants.LEADING);
				lblLinea.setHorizontalAlignment(SwingConstants.CENTER);
				lblLinea.setFont(new Font("Tahoma", Font.BOLD, 25));
				GridBagConstraints gbc_lblLinea = new GridBagConstraints();
				gbc_lblLinea.fill = GridBagConstraints.HORIZONTAL;
				gbc_lblLinea.insets = new Insets(0, 0, 0, 5);
				gbc_lblLinea.gridx = 3;
				gbc_lblLinea.gridy = yPosition;
				panelJornadaX.add(lblLinea, gbc_lblLinea);

				// JLabel para el escudo visitante
				JLabel lblEscudoVisitante = new JLabel("");
				lblEscudoVisitante.setBorder(new LineBorder(new Color(0, 0, 0)));
				lblEscudoVisitante.setHorizontalAlignment(SwingConstants.CENTER);
				lblEscudoVisitante.setPreferredSize(new Dimension(80, 74));

				// Ajustar la imagen del escudo visitante al tamaño especificado
				String escudoVisitantePath = partido.getEquipoVisitante().getEscudo();
				if (escudoVisitantePath != null && !escudoVisitantePath.isEmpty()) {
					ImageIcon escudoVisitanteIcon = new ImageIcon(escudoVisitantePath);
					Image escudoVisitanteImage = escudoVisitanteIcon.getImage().getScaledInstance(80, 74,
							Image.SCALE_SMOOTH);
					lblEscudoVisitante.setIcon(new ImageIcon(escudoVisitanteImage));
				} else {
					lblEscudoVisitante.setIcon(null);
				}

				GridBagConstraints gbc_lblEscudoVisitante = new GridBagConstraints();
				gbc_lblEscudoVisitante.fill = GridBagConstraints.BOTH;
				gbc_lblEscudoVisitante.insets = new Insets(0, 0, 0, 5);
				gbc_lblEscudoVisitante.gridx = 5;
				gbc_lblEscudoVisitante.gridy = yPosition;
				panelJornadaX.add(lblEscudoVisitante, gbc_lblEscudoVisitante);

				JLabel lblEquipoVisitante = new JLabel(partido.getEquipoVisitante().getNombre());
				lblEquipoVisitante.setHorizontalTextPosition(SwingConstants.RIGHT);
				lblEquipoVisitante.setHorizontalAlignment(SwingConstants.CENTER);
				lblEquipoVisitante.setFont(new Font("Tahoma", Font.BOLD, 10));
				GridBagConstraints gbc_lblEquipoVisitante = new GridBagConstraints();
				gbc_lblEquipoVisitante.fill = GridBagConstraints.HORIZONTAL;
				gbc_lblEquipoVisitante.gridx = 6;
				gbc_lblEquipoVisitante.gridy = yPosition;
				panelJornadaX.add(lblEquipoVisitante, gbc_lblEquipoVisitante);

				yPosition += 2;
			}
			// Crear un JScrollPane y agregarlo como una nueva pestaña al contenedor
			JScrollPane scrollPane = new JScrollPane(panelJornadaX);
			scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			scrollPane.setBounds(tabbedPaneJornadas.getBounds());
			tabbedPaneJornadas.addTab("Jornada " + jornada.getNumero(), null, scrollPane, null);
		}
	}

}
