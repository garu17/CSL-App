package aplicacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.basic.BasicComboBoxUI;

import definicion.Equipo;
import definicion.EquipoSeleccion;
import definicion.Fecha;
import definicion.Jugador;
import definicion.Logger;
import definicion.Participante;

import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.ScrollPaneConstants;

/**
 * La Clase EditarEquipo.
 */
public class EditarEquipo extends JFrame implements ActionListener, ListSelectionListener, WindowListener {

	/** La Constante serialVersionUID generada para identificarse. */
	private static final long serialVersionUID = 1L;

	/** El Content Pane principal. */
	private JPanel contentPane;

	/** El Boton de Volver. */
	private JButton btnVolver;

	/** El Label del Nombre de Equipo. */
	private JLabel lblNombreEquipo;

	/** El Label de la Lista de Jugadores. */
	private JLabel lblListaJugadores;

	/** La Lista de Jugadores. */
	private JList<Jugador> lstJugadores;

	/** El Modelo de la Lista de Jugadores. */
	private DefaultListModel<Jugador> jlm;

	/** El TextField de los Nombres de los Jugadores. */
	private JTextField textListaJugadoresNombre;

	/** El Boton de Añadir Jugadores de la Lista. */
	private JButton btnListaJugadoresAñadir;

	/** El Boton de Eliminar Jugadores de la Lista. */
	private JButton btnListaJugadoresEliminar;

	/** El TextField de los Apellidos de los Jugadores. */
	private JTextField textListaJugadoresApellido;

	/** El Label del Nombre de los Jugadores de la Lista. */
	private JLabel lblListajugadoresNombre;

	/** El Label del Apellido de los Jugadores de la Lista. */
	private JLabel lblListaJugadoresApellido;

	/** El Label que Muestra la Foto del Jugador. */
	private JLabel lblFotoJugador;

	/** El Panel de la Fecha de Creación. */
	private JPanel panelCreacion;

	/** El Label que Muestra la Fecha de Creación. */
	private JLabel lblFecha;

	/** El Boton para Subir la Foto del Jugador. */
	private JButton btnListaJugadoresSubir;

	/** El Boton de Añadir Escudo. */
	private JButton btnAñadirEscudo;

	/** El TextField del Nombre del Equipo. */
	private JTextField textNombreEquipo;

	/** El Label de la Posicion de los Jugadores de la Lista. */
	private JLabel lblListaJugadoresRol;

	/** El Label de la Nacionalidad de los Jugadores de la Lista. */
	private JLabel lblListajugadoresNacionalidad;

	/** El Label de la Primera Barra de la Fecha de Creación */
	private JLabel lblCreacionBarra1;

	/** El Label de la Segunda Barra de la Fecha de Creación */
	private JLabel lblCreacionBarra2;

	/** El Label del Año de la Fecha de Creación. */
	private JLabel lblCreacionAño;

	/** El Label del Mes de la Fecha de Creación. */
	private JLabel lblCreacionMes;

	/** El Label del Dia de la Fecha de Creación. */
	private JLabel lblCreacionDia;

	/** La ComboBox de la Nacionalidad de los Jugadores de la Lista. */
	private JComboBox<String> comboBoxListaJugadoresNacionalidad;

	/** El Lable de Entrenador. */
	private JLabel lblEntrenador;

	/** El Panel de Entrenador. */
	private JPanel panelEntrenador;

	/** El TextField del Nombre del Entrenador. */
	private JTextField textEntrenadorNombre;

	/** El TextField del Apellido del Entrenador. */
	private JTextField textEntrenadorApellido;

	/** El Label de Subir la Foto de los Jugadores de la Lista. */
	private JLabel lblListaJugadoresSubir;

	/** El Label del Nombre del Entrenador. */
	private JLabel lblEntrenadorNombre;

	/** El Label del Apellido del Entrenador. */
	private JLabel lblEntrenadorApellido;

	/** El Label de la Nacionalidad del Entrenador. */
	private JLabel lblEntrenadorNacionalidad;

	/** La ComboBox la Nacionalidad del Entrenador. */
	private JComboBox<String> comboBoxEntrenadorNacionalidad;

	/** El Label de la Fecha de Alta del Entrenador. */
	private JLabel lblEntrenadorFechaAlta;

	/** El Boton de Guardar. */
	private JButton btnGuardar;

	/** La ComboBox del Dia de Alta del Entrenador. */
	private JComboBox<Integer> comboBoxEntrenadorDia;

	/** La ComboBox del Año de Alta del Entrenador. */
	private JComboBox<Integer> comboBoxEntrenadorAño;

	/** La ComboBox del Mes de Alta del Entrenador. */
	private JComboBox<Integer> comboBoxEntrenadorMes;

	/** La ComboBox del Dia de Creacion del Equipo. */
	private JComboBox<Integer> comboBoxCreacionDia;

	/** La ComboBox del Año de Creacion del Equipo. */
	private JComboBox<Integer> comboBoxCreacionAño;

	/** La ComboBox del Mes de Creacion del Equipo. */
	private JComboBox<Integer> comboBoxCreacionMes;

	/** El Label de la Primera Barra de la Fecha de Alta del Entrenador. */
	private JLabel lblListajugadoresBarra1;

	/** El Label de la Segunda Barra de la Fecha de Alta del Entrenador. */
	private JLabel lblListajugadoresBarra2;

	/** El Label del Dia de la Fecha de Alta del Entrenador. */
	private JLabel lblEntrenadorDia;

	/** El Label del Mes de la Fecha de Alta del Entrenador. */
	private JLabel lblEntrenadorMes;

	/** El Label del Año de la Fecha de Alta del Entrenador. */
	private JLabel lblEntrenadorAño;

	/** El Panel de la Lista de Jugadores. */
	private JPanel panelListaJugadores;

	/** La ComboBox de la Posicion de los Jugadores de la Lista. */
	private JComboBox<String> comboBoxListaJugadoresPosicion;

	/** El Label de Añadir Escudo. */
	private JLabel lblEditarEscudo;

	/** El Label de la Descripcion. */
	private JLabel lblDescripcion;

	/** El TextField de la Descripcion. */
	private JTextArea textDescripcion;

	/** El Scroll Pane de la Descripcion. */
	private JScrollPane scrollPane;

	/** El Scroll Pane de la Lista de Jugadores. */
	private JScrollPane scrollPanelst;

	/** El TextField del DNI de los Jugadores de la Lista. */
	private JTextField textListaJugadoresDNI;

	/** El Label del DNI de los Jugadores de la Lista. */
	private JLabel lblListajugadoresDNI;

	/** El TextField del DNI del Entrenador. */
	private JTextField textEntrenadorDNI;

	/** El Label del DNI del Entrenador. */
	private JLabel lblEntrenadorDNI;

	/** El Label del Escudo. */
	private JLabel lblEscudo;

	/** La Lista de Jugadores Totales que hay Registrados. */
	private List<Jugador> ListaJugadoresRegistrados;

	/** La Lista de Equipos Totales que hay Registrados. */
	private ArrayList<Equipo> ListaEquipos;

	/** La Lista de Movimientos Totales que hay Registrados. */
	private ArrayList<Logger> ListaMovimientos;

	/** Los Datos del Equipo Original. */
	private Equipo DatosEquipo;

	/** Los Datos de los Jugadores Originales. */
	private ArrayList<Jugador> DatosJugadores;

	/** La Clase EditarTemporada */
	EditarTemp ET;

	/** La Clase AñadirTemporada */
	AñadirTemporada AT;

	/** La Clase Panel */
	Panel P;

	/** Los Dias del mes para las ComboBox de las Fechas. */
	private Integer[] dia = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25,
			26, 27, 28, 29, 30, 31 };

	/** Los Meses del año para las ComboBox de las Fechas. */
	private Integer[] mes = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };

	/** Los Años para las ComboBox de las Fechas. */
	private Integer[] año;

	/** Las Posiciones para la ComboBox de Posicion. */
	private String[] posiciones = { "...", "Leader", "Support", "Lurker", "Fragger", "Awper" };

	/** Las Nacionalidades para la ComboBox de Nacionalidad. */
	private String[] nacionalidades = { "...", "Afganistán", "Albania", "Alemania", "Andorra", "Angola",
			"Antigua y Barbuda", "Arabia Saudita", "Argelia", "Argentina", "Armenia", "Australia", "Austria", "Azerbaiyán",
			"Bahamas", "Bangladés", "Barbados", "Baréin", "Bélgica", "Belice", "Benín", "Bielorrusia", "Birmania",
			"Bolivia", "Bosnia y Herzegovina", "Botsuana", "Brasil", "Brunéi", "Bulgaria", "Burkina Faso", "Burundi",
			"Bután", "Cabo Verde", "Camboya", "Camerún", "Canadá", "Catar", "Chad", "Chile", "China", "Chipre",
			"Ciudad del Vaticano", "Colombia", "Comoras", "Corea del Norte", "Corea del Sur", "Costa de Marfil",
			"Costa Rica", "Croacia", "Cuba", "Dinamarca", "Dominica", "Ecuador", "Egipto", "El Salvador",
			"Emiratos Árabes Unidos", "Eritrea", "Eslovaquia", "Eslovenia", "España", "Estados Unidos", "Estonia",
			"Etiopía", "Filipinas", "Finlandia", "Fiyi", "Francia", "Gabón", "Gambia", "Georgia", "Ghana", "Granada",
			"Grecia", "Guatemala", "Guyana", "Guinea", "Guinea ecuatorial", "Guinea-Bisáu", "Haití", "Honduras", "Hungría",
			"India", "Indonesia", "Irak", "Irán", "Irlanda", "Islandia", "Islas Marshall", "Islas Salomón", "Israel",
			"Italia", "Jamaica", "Japón", "Jordania", "Kazajistán", "Kenia", "Kirguistán", "Kiribati", "Kuwait", "Laos",
			"Lesoto", "Letonia", "Líbano", "Liberia", "Libia", "Liechtenstein", "Lituania", "Luxemburgo",
			"Macedonia del Norte", "Madagascar", "Malasia", "Malaui", "Maldivas", "Malí", "Malta", "Marruecos", "Mauricio",
			"Mauritania", "México", "Micronesia", "Moldavia", "Mónaco", "Mongolia", "Montenegro", "Mozambique", "Namibia",
			"Nauru", "Nepal", "Nicaragua", "Níger", "Nigeria", "Noruega", "Nueva Zelanda", "Omán", "Países Bajos",
			"Pakistán", "Palaos", "Panamá", "Papúa Nueva Guinea", "Paraguay", "Perú", "Polonia", "Portugal", "Reino Unido",
			"República Centroafricana", "República Checa", "República del Congo", "República Democrática del Congo",
			"República Dominicana", "Ruanda", "Rumanía", "Rusia", "Samoa", "San Cristóbal y Nieves", "San Marino",
			"San Vicente y las Granadinas", "Santa Lucía", "Santo Tomé y Príncipe", "Senegal", "Serbia", "Seychelles",
			"Sierra Leona", "Singapur", "Siria", "Somalia", "Sri Lanka", "Suazilandia", "Sudáfrica", "Sudán",
			"Sudán del Sur", "Suecia", "Suiza", "Surinam", "Tailandia", "Tanzania", "Tayikistán", "Timor Oriental", "Togo",
			"Tonga", "Trinidad y Tobago", "Túnez", "Turkmenistán", "Turquía", "Tuvalu", "Ucrania", "Uganda", "Uruguay",
			"Uzbekistán", "Vanuatu", "Venezuela", "Vietnam", "Yemen", "Yibuti", "Zambia", "Zimbabue" };

	/** El Equipo que guarda los ajustes realizados. */
	Equipo EquipoModificado = EquipoSeleccion.getEquipoSeleccionado();

	/** El Escudo del Equipo. */
	String Escudo = "";

	/** La Foto del Jugador. */
	String FotoJugador = "";

	/** Todas las fotos de los Jugadores */
	StringBuilder escudosJugadores = new StringBuilder();

	/** El Dia de la Fecha de Alta del Entrenador. */
	Integer DiaEntrenador = 0;

	/** El Mes de la Fecha de Alta del Entrenador. */
	Integer MesEntrenador = 0;

	/** El Año de la Fecha de Alta del Entrenador. */
	Integer AñoEntrenador = 0;

	/** El Dia de la Fecha de Alta del Entrenador. */
	Integer DiaCreacion = 1;

	/** El Mes de la Fecha de Alta del Entrenador. */
	Integer MesCreacion = 1;

	/** El Año de la Fecha de Alta del Entrenador. */
	Integer AñoCreacion = 2024;

	/** El Dia de la Fecha de Alta del Entrenador. */
	Integer DiaNacimiento = 1;

	/** El Mes de la Fecha de Alta del Entrenador. */
	Integer MesNacimiento = 1;

	/** El Año de la Fecha de Alta del Entrenador. */
	Integer AñoNacimiento = 2024;

	/** La Nacionalidad de el Entrenador por defecto. */
	String NacionalidadEntrenador = "...";

	/** La Nacionalidad de los Jugadores por defecto. */
	String NacionalidadJugador = "...";

	/** La Posicion de los Jugadores por defecto. */
	String PosicionJugador = "...";

	/** La Lista de las Fotos de los Jugadores que hay que eliminar. */
	ArrayList<String> FotoJugadoresEliminados = new ArrayList<String>();

	private JLabel lblEntrenadorAño_1;
	private JLabel lblEntrenadorMes_1;
	private JLabel lblEntrenadorDia_1;
	private JComboBox<Integer> comboBoxJugadorDia;
	private JLabel lblNacimientoBarra1;
	private JComboBox<Integer> comboBoxJugadorMes;
	private JLabel lblNacimientoBarra2;
	private JComboBox<Integer> comboBoxJugadorAño;
	private JLabel lblFechaNacimiento;

	/**
	 * Ejecuta la aplicacion.
	 *
	 * @param args los Argumentos
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditarEquipo frame = new EditarEquipo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Se Crea el Frame
	 */
	public EditarEquipo() {
		addWindowListener(this);
		// Imagen de la aplicación
		setIconImage(new ImageIcon(getClass().getResource("/Imagenes/CSL.png")).getImage());
		setResizable(false);
		setTitle("Editar Equipo");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 550, 750);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(this);
		btnVolver.setForeground(new Color(255, 255, 255));
		btnVolver.setBackground(new Color(74, 127, 214));
		btnVolver.setFont(new Font("Dialog", Font.BOLD, 13));
		btnVolver.setFocusable(false);
		btnVolver.setBounds(329, 663, 85, 40);
		contentPane.add(btnVolver);

		lblNombreEquipo = new JLabel("Cambiar Nombre del Equipo:");
		lblNombreEquipo.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNombreEquipo.setBounds(36, 10, 276, 33);
		contentPane.add(lblNombreEquipo);

		lblListaJugadores = new JLabel("Cambiar Lista de Jugadores:");
		lblListaJugadores.setFont(new Font("Dialog", Font.BOLD, 18));
		lblListaJugadores.setBounds(36, 247, 256, 33);
		contentPane.add(lblListaJugadores);

		jlm = new DefaultListModel<>();

		lstJugadores = new JList<Jugador>();
		lstJugadores.setFont(new Font("Dialog", Font.BOLD, 10));
		lstJugadores.setBorder(new LineBorder(new Color(0, 0, 0)));
		lstJugadores.setBounds(42, 368, 437, 75);
		lstJugadores.setCellRenderer(new JugadorListCellRenderer());
		lstJugadores.addListSelectionListener(this);
		// asocio el DefaultListModel a la lista
		lstJugadores.setModel(jlm);
		contentPane.add(lstJugadores);

		panelListaJugadores = new JPanel();
		panelListaJugadores.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelListaJugadores.setBackground(new Color(255, 255, 255));
		panelListaJugadores.setBounds(36, 278, 463, 90);
		contentPane.add(panelListaJugadores);
		panelListaJugadores.setLayout(null);

		textListaJugadoresNombre = new JTextField();
		textListaJugadoresNombre.setBounds(82, 27, 65, 19);
		panelListaJugadores.add(textListaJugadoresNombre);
		textListaJugadoresNombre.setColumns(10);

		btnListaJugadoresAñadir = new JButton("Añadir");
		btnListaJugadoresAñadir.setFocusable(false);
		btnListaJugadoresAñadir.setFont(new Font("Dialog", Font.BOLD, 12));
		btnListaJugadoresAñadir.addActionListener(this);
		btnListaJugadoresAñadir.setBackground(new Color(230, 230, 230));
		btnListaJugadoresAñadir.setBounds(278, 56, 80, 28);
		panelListaJugadores.add(btnListaJugadoresAñadir);

		btnListaJugadoresEliminar = new JButton("Eliminar");
		btnListaJugadoresEliminar.setFocusable(false);
		btnListaJugadoresEliminar.setFont(new Font("Dialog", Font.BOLD, 12));
		btnListaJugadoresEliminar.addActionListener(this);
		btnListaJugadoresEliminar.setBackground(new Color(230, 230, 230));
		btnListaJugadoresEliminar.setBounds(368, 56, 85, 28);
		panelListaJugadores.add(btnListaJugadoresEliminar);

		textListaJugadoresApellido = new JTextField();
		textListaJugadoresApellido.setColumns(10);
		textListaJugadoresApellido.setBounds(157, 27, 106, 19);
		panelListaJugadores.add(textListaJugadoresApellido);

		lblListajugadoresNombre = new JLabel("Nombre:");
		lblListajugadoresNombre.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblListajugadoresNombre.setBounds(82, 10, 65, 13);
		panelListaJugadores.add(lblListajugadoresNombre);

		lblListaJugadoresApellido = new JLabel("Apellido:");
		lblListaJugadoresApellido.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblListaJugadoresApellido.setBounds(157, 10, 65, 13);
		panelListaJugadores.add(lblListaJugadoresApellido);

		lblListaJugadoresRol = new JLabel("Rol:");
		lblListaJugadoresRol.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblListaJugadoresRol.setBounds(82, 48, 65, 13);
		panelListaJugadores.add(lblListaJugadoresRol);

		lblListajugadoresNacionalidad = new JLabel("Nacionalidad:");
		lblListajugadoresNacionalidad.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblListajugadoresNacionalidad.setBounds(6, 48, 106, 13);
		panelListaJugadores.add(lblListajugadoresNacionalidad);

		comboBoxListaJugadoresPosicion = new JComboBox<>(posiciones);
		comboBoxListaJugadoresPosicion.setBackground(Color.WHITE);
		comboBoxListaJugadoresPosicion.setFocusable(false);
		comboBoxListaJugadoresPosicion.addActionListener(this);
		comboBoxListaJugadoresPosicion.setBounds(81, 65, 65, 19);
		panelListaJugadores.add(comboBoxListaJugadoresPosicion);

		comboBoxListaJugadoresNacionalidad = new JComboBox<>(nacionalidades);
		comboBoxListaJugadoresNacionalidad.setBackground(Color.WHITE);
		comboBoxListaJugadoresNacionalidad.setFocusable(false);
		comboBoxListaJugadoresNacionalidad.addActionListener(this);
		comboBoxListaJugadoresNacionalidad.setBounds(6, 65, 65, 19);
		panelListaJugadores.add(comboBoxListaJugadoresNacionalidad);

		textListaJugadoresDNI = new JTextField();
		textListaJugadoresDNI.setColumns(10);
		textListaJugadoresDNI.setBounds(6, 27, 65, 19);
		panelListaJugadores.add(textListaJugadoresDNI);

		lblListajugadoresDNI = new JLabel("DNI/NIE:");
		lblListajugadoresDNI.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblListajugadoresDNI.setBounds(6, 10, 106, 13);
		panelListaJugadores.add(lblListajugadoresDNI);

		lblFotoJugador = new JLabel("");
		lblFotoJugador.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblFotoJugador.setBounds(227, 48, 36, 36);
		panelListaJugadores.add(lblFotoJugador);

		btnListaJugadoresSubir = new JButton("Subir");
		btnListaJugadoresSubir.addActionListener(this);
		btnListaJugadoresSubir.setFont(new Font("Dialog", Font.BOLD, 12));
		btnListaJugadoresSubir.setFocusable(false);
		btnListaJugadoresSubir.setBackground(new Color(255, 255, 255));
		btnListaJugadoresSubir.setBounds(157, 65, 69, 19);
		panelListaJugadores.add(btnListaJugadoresSubir);

		lblListaJugadoresSubir = new JLabel("Subir Foto:");
		lblListaJugadoresSubir.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblListaJugadoresSubir.setBounds(157, 48, 69, 13);
		panelListaJugadores.add(lblListaJugadoresSubir);

		lblEntrenadorAño_1 = new JLabel("Año");
		lblEntrenadorAño_1.setForeground(Color.GRAY);
		lblEntrenadorAño_1.setFont(new Font("Dialog", Font.BOLD, 10));
		lblEntrenadorAño_1.setBounds(356, 45, 35, 13);
		panelListaJugadores.add(lblEntrenadorAño_1);

		lblEntrenadorMes_1 = new JLabel("Mes");
		lblEntrenadorMes_1.setForeground(Color.GRAY);
		lblEntrenadorMes_1.setFont(new Font("Dialog", Font.BOLD, 10));
		lblEntrenadorMes_1.setBounds(319, 45, 29, 13);
		panelListaJugadores.add(lblEntrenadorMes_1);

		lblEntrenadorDia_1 = new JLabel("Dia");
		lblEntrenadorDia_1.setForeground(Color.GRAY);
		lblEntrenadorDia_1.setFont(new Font("Dialog", Font.BOLD, 10));
		lblEntrenadorDia_1.setBounds(280, 45, 21, 13);
		panelListaJugadores.add(lblEntrenadorDia_1);

		comboBoxJugadorDia = new JComboBox<Integer>(dia);
		comboBoxJugadorDia.setBounds(279, 27, 35, 19);
		comboBoxJugadorDia.addActionListener(this);
		comboBoxJugadorDia.setBackground(Color.LIGHT_GRAY);
		comboBoxJugadorDia.setUI(new BasicComboBoxUI() {
			@Override
			protected JButton createArrowButton() {
				return new JButton() {
					private static final long serialVersionUID = 1L;

					@Override
					public int getWidth() {
						return 0; // Establece el ancho del botón de flecha a cero
					}

					@Override
					public boolean isVisible() {
						return false; // Hace que el botón de flecha no sea visible
					}
				};
			}
		});
		panelListaJugadores.add(comboBoxJugadorDia);

		lblNacimientoBarra1 = new JLabel("/");
		lblNacimientoBarra1.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNacimientoBarra1.setBounds(316, 27, 12, 19);
		panelListaJugadores.add(lblNacimientoBarra1);

		comboBoxJugadorMes = new JComboBox<Integer>(mes);
		comboBoxJugadorMes.setMaximumRowCount(12);
		comboBoxJugadorMes.setBounds(324, 27, 21, 19);
		comboBoxJugadorMes.addActionListener(this);
		comboBoxJugadorMes.setBackground(Color.LIGHT_GRAY);
		comboBoxJugadorMes.setUI(new BasicComboBoxUI() {
			@Override
			protected JButton createArrowButton() {
				return new JButton() {
					private static final long serialVersionUID = 1L;

					@Override
					public int getWidth() {
						return 0; // Establece el ancho del botón de flecha a cero
					}

					@Override
					public boolean isVisible() {
						return false; // Hace que el botón de flecha no sea visible
					}
				};
			}
		});
		panelListaJugadores.add(comboBoxJugadorMes);

		lblNacimientoBarra2 = new JLabel("/");
		lblNacimientoBarra2.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNacimientoBarra2.setBounds(347, 27, 12, 19);
		panelListaJugadores.add(lblNacimientoBarra2);

		inicializarAño();
		comboBoxJugadorAño = new JComboBox<Integer>(año);
		comboBoxJugadorAño.setBounds(353, 27, 61, 19);
		comboBoxJugadorAño.addActionListener(this);
		comboBoxJugadorAño.setBackground(Color.LIGHT_GRAY);
		comboBoxJugadorAño.setUI(new BasicComboBoxUI() {
			@Override
			protected JButton createArrowButton() {
				return new JButton() {
					private static final long serialVersionUID = 1L;

					@Override
					public int getWidth() {
						return 0; // Establece el ancho del botón de flecha a cero
					}

					@Override
					public boolean isVisible() {
						return false; // Hace que el botón de flecha no sea visible
					}
				};
			}
		});
		panelListaJugadores.add(comboBoxJugadorAño);

		lblFechaNacimiento = new JLabel("Fecha de Nacimiento:");
		lblFechaNacimiento.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblFechaNacimiento.setBounds(278, 10, 136, 13);
		panelListaJugadores.add(lblFechaNacimiento);
		lblEditarEscudo = new JLabel("Editar Escudo:");
		lblEditarEscudo.setFont(new Font("Dialog", Font.BOLD, 18));
		lblEditarEscudo.setBounds(36, 81, 200, 33);
		contentPane.add(lblEditarEscudo);

		btnAñadirEscudo = new JButton("Subir Archivo");
		btnAñadirEscudo.setFocusable(false);
		btnAñadirEscudo.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnAñadirEscudo.setBackground(Color.WHITE);
		btnAñadirEscudo.addActionListener(this);
		btnAñadirEscudo.setFont(new Font("Dialog", Font.BOLD, 15));
		btnAñadirEscudo.setBounds(36, 112, 338, 33);
		contentPane.add(btnAñadirEscudo);

		textNombreEquipo = new JTextField();
		textNombreEquipo.setBorder(new LineBorder(new Color(171, 173, 179)));
		textNombreEquipo.setFont(new Font("Dialog", Font.PLAIN, 22));
		textNombreEquipo.setBounds(36, 41, 463, 33);
		contentPane.add(textNombreEquipo);
		textNombreEquipo.setColumns(10);

		lblEntrenador = new JLabel("Editar Entrenador:");
		lblEntrenador.setFont(new Font("Dialog", Font.BOLD, 18));
		lblEntrenador.setBounds(36, 150, 234, 33);
		contentPane.add(lblEntrenador);

		panelEntrenador = new JPanel();
		panelEntrenador.setLayout(null);
		panelEntrenador.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelEntrenador.setBackground(Color.WHITE);
		panelEntrenador.setBounds(36, 181, 463, 62);
		contentPane.add(panelEntrenador);

		textEntrenadorNombre = new JTextField();
		textEntrenadorNombre.setColumns(10);
		textEntrenadorNombre.setBounds(81, 27, 65, 19);
		panelEntrenador.add(textEntrenadorNombre);

		textEntrenadorApellido = new JTextField();
		textEntrenadorApellido.setColumns(10);
		textEntrenadorApellido.setBounds(156, 27, 65, 19);
		panelEntrenador.add(textEntrenadorApellido);

		lblEntrenadorNombre = new JLabel("Nombre:");
		lblEntrenadorNombre.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblEntrenadorNombre.setBounds(81, 10, 65, 13);
		panelEntrenador.add(lblEntrenadorNombre);

		lblEntrenadorApellido = new JLabel("Apellido:");
		lblEntrenadorApellido.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblEntrenadorApellido.setBounds(156, 10, 65, 13);
		panelEntrenador.add(lblEntrenadorApellido);

		lblEntrenadorNacionalidad = new JLabel("Nacionalidad:");
		lblEntrenadorNacionalidad.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblEntrenadorNacionalidad.setBounds(226, 10, 77, 13);
		panelEntrenador.add(lblEntrenadorNacionalidad);

		comboBoxEntrenadorNacionalidad = new JComboBox<>(nacionalidades);
		comboBoxEntrenadorNacionalidad.setBackground(Color.WHITE);
		comboBoxEntrenadorNacionalidad.setFocusable(false);
		comboBoxEntrenadorNacionalidad.addActionListener(this);
		comboBoxEntrenadorNacionalidad.setBounds(226, 27, 86, 19);
		panelEntrenador.add(comboBoxEntrenadorNacionalidad);

		lblEntrenadorFechaAlta = new JLabel("Fecha de Alta:");
		lblEntrenadorFechaAlta.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblEntrenadorFechaAlta.setBounds(317, 10, 74, 13);
		panelEntrenador.add(lblEntrenadorFechaAlta);

		comboBoxEntrenadorDia = new JComboBox<Integer>(dia);
		comboBoxEntrenadorDia.addActionListener(this);
		comboBoxEntrenadorDia.setBackground(Color.LIGHT_GRAY);
		comboBoxEntrenadorDia.setUI(new BasicComboBoxUI() {
			@Override
			protected JButton createArrowButton() {
				return new JButton() {
					private static final long serialVersionUID = 1L;

					@Override
					public int getWidth() {
						return 0; // Oculta la flecha hacia abajo de la comboBox
					}
				};
			}
		});
		comboBoxEntrenadorDia.setBounds(318, 27, 35, 19);
		panelEntrenador.add(comboBoxEntrenadorDia);

		inicializarAño();
		comboBoxEntrenadorAño = new JComboBox<Integer>(año);
		comboBoxEntrenadorAño.addActionListener(this);
		comboBoxEntrenadorAño.setBackground(Color.LIGHT_GRAY);
		comboBoxEntrenadorAño.setUI(new BasicComboBoxUI() {
			@Override
			protected JButton createArrowButton() {
				return new JButton() {
					private static final long serialVersionUID = 1L;

					@Override
					public int getWidth() {
						return 0; // Establece el ancho del botón de flecha a cero
					}

					@Override
					public boolean isVisible() {
						return false; // Hace que el botón de flecha no sea visible
					}
				};
			}
		});
		comboBoxEntrenadorAño.setBounds(392, 27, 61, 19);
		panelEntrenador.add(comboBoxEntrenadorAño);

		comboBoxEntrenadorMes = new JComboBox<Integer>(mes);
		comboBoxEntrenadorMes.addActionListener(this);
		comboBoxEntrenadorMes.setMaximumRowCount(12);
		comboBoxEntrenadorMes.setBackground(Color.LIGHT_GRAY);
		comboBoxEntrenadorMes.setUI(new BasicComboBoxUI() {
			@Override
			protected JButton createArrowButton() {
				return new JButton() {
					private static final long serialVersionUID = 1L;

					@Override
					public int getWidth() {
						return 0; // Establece el ancho del botón de flecha a cero
					}

					@Override
					public boolean isVisible() {
						return false; // Hace que el botón de flecha no sea visible
					}
				};
			}
		});
		comboBoxEntrenadorMes.setBounds(363, 27, 21, 19);
		panelEntrenador.add(comboBoxEntrenadorMes);

		lblListajugadoresBarra1 = new JLabel("/");
		lblListajugadoresBarra1.setBounds(355, 27, 12, 19);
		panelEntrenador.add(lblListajugadoresBarra1);
		lblListajugadoresBarra1.setFont(new Font("Tahoma", Font.BOLD, 10));

		lblListajugadoresBarra2 = new JLabel("/");
		lblListajugadoresBarra2.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblListajugadoresBarra2.setBounds(386, 27, 12, 19);
		panelEntrenador.add(lblListajugadoresBarra2);

		lblEntrenadorDia = new JLabel("Dia");
		lblEntrenadorDia.setForeground(Color.GRAY);
		lblEntrenadorDia.setFont(new Font("Dialog", Font.BOLD, 10));
		lblEntrenadorDia.setBounds(319, 45, 21, 13);
		panelEntrenador.add(lblEntrenadorDia);

		lblEntrenadorMes = new JLabel("Mes");
		lblEntrenadorMes.setForeground(Color.GRAY);
		lblEntrenadorMes.setFont(new Font("Dialog", Font.BOLD, 10));
		lblEntrenadorMes.setBounds(358, 45, 21, 13);
		panelEntrenador.add(lblEntrenadorMes);

		lblEntrenadorAño = new JLabel("Año");
		lblEntrenadorAño.setForeground(Color.GRAY);
		lblEntrenadorAño.setFont(new Font("Dialog", Font.BOLD, 10));
		lblEntrenadorAño.setBounds(395, 45, 21, 13);
		panelEntrenador.add(lblEntrenadorAño);

		textEntrenadorDNI = new JTextField();
		textEntrenadorDNI.setColumns(10);
		textEntrenadorDNI.setBounds(10, 27, 65, 19);
		panelEntrenador.add(textEntrenadorDNI);

		lblEntrenadorDNI = new JLabel("DNI/NIE:");
		lblEntrenadorDNI.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblEntrenadorDNI.setBounds(10, 10, 65, 13);
		panelEntrenador.add(lblEntrenadorDNI);

		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(this);
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.setFont(new Font("Dialog", Font.BOLD, 13));
		btnGuardar.setFocusable(false);
		btnGuardar.setBackground(new Color(74, 127, 214));
		btnGuardar.setBounds(122, 663, 85, 40);
		contentPane.add(btnGuardar);

		lblDescripcion = new JLabel("Cambiar Descripción:");
		lblDescripcion.setFont(new Font("Dialog", Font.BOLD, 18));
		lblDescripcion.setBounds(36, 453, 200, 33);
		contentPane.add(lblDescripcion);

		// Reemplaza la declaración de JTextArea existente con la siguiente
		textDescripcion = new JTextArea();
		textDescripcion.setFont(new Font("Dialog", Font.PLAIN, 13));
		textDescripcion.setBounds(42, 484, 351, 75);
		textDescripcion.setLineWrap(true);
		textDescripcion.setWrapStyleWord(true);

		// Crea un JScrollPane y envuelve el JTextArea con él
		scrollPane = new JScrollPane(textDescripcion);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(36, 484, 464, 75);
		// Añade el JScrollPane al contenido en lugar del JTextArea directamente
		contentPane.add(scrollPane);

		// Crea un JScrollPane y envuelve el lstJugadores con él
		scrollPanelst = new JScrollPane(lstJugadores);
		scrollPanelst.setBorder(null);
		scrollPanelst.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPanelst.setBounds(36, 368, 464, 75);
		// Añade el JScrollPane al contenido en lugar del JTextArea directamente
		contentPane.add(scrollPanelst);

		lblEscudo = new JLabel("");
		lblEscudo.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblEscudo.setBounds(433, 98, 65, 59);
		contentPane.add(lblEscudo);

		lblFecha = new JLabel("Fecha de Creación:");
		lblFecha.setFont(new Font("Dialog", Font.BOLD, 18));
		lblFecha.setBounds(36, 560, 200, 33);
		contentPane.add(lblFecha);

		panelCreacion = new JPanel();
		panelCreacion.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelCreacion.setBackground(Color.WHITE);
		panelCreacion.setBounds(36, 592, 463, 51);
		contentPane.add(panelCreacion);
		panelCreacion.setLayout(null);

		comboBoxCreacionAño = new JComboBox<Integer>(año);
		comboBoxCreacionAño.setBounds(113, 10, 52, 19);
		panelCreacion.add(comboBoxCreacionAño);
		comboBoxCreacionAño.addActionListener(this);
		comboBoxCreacionAño.setBackground(Color.LIGHT_GRAY);
		comboBoxCreacionAño.setUI(new BasicComboBoxUI() {
			@Override
			protected JButton createArrowButton() {
				return new JButton() {
					private static final long serialVersionUID = 1L;

					@Override
					public int getWidth() {
						return 0; // Establece el ancho del botón de flecha a cero
					}

					@Override
					public boolean isVisible() {
						return false; // Hace que el botón de flecha no sea visible
					}
				};
			}
		});

		lblCreacionAño = new JLabel("Año");
		lblCreacionAño.setBounds(113, 28, 35, 13);
		panelCreacion.add(lblCreacionAño);
		lblCreacionAño.setForeground(Color.GRAY);
		lblCreacionAño.setFont(new Font("Dialog", Font.BOLD, 10));

		lblCreacionBarra2 = new JLabel("/");
		lblCreacionBarra2.setBounds(102, 10, 12, 19);
		panelCreacion.add(lblCreacionBarra2);
		lblCreacionBarra2.setFont(new Font("Tahoma", Font.BOLD, 10));

		comboBoxCreacionMes = new JComboBox<Integer>(mes);
		comboBoxCreacionMes.setBounds(62, 10, 35, 19);
		panelCreacion.add(comboBoxCreacionMes);
		comboBoxCreacionMes.addActionListener(this);
		comboBoxCreacionMes.setMaximumRowCount(12);
		comboBoxCreacionMes.setBackground(Color.LIGHT_GRAY);
		comboBoxCreacionMes.setUI(new BasicComboBoxUI() {
			@Override
			protected JButton createArrowButton() {
				return new JButton() {
					private static final long serialVersionUID = 1L;

					@Override
					public int getWidth() {
						return 0; // Establece el ancho del botón de flecha a cero
					}

					@Override
					public boolean isVisible() {
						return false; // Hace que el botón de flecha no sea visible
					}
				};
			}
		});

		lblCreacionBarra1 = new JLabel("/");
		lblCreacionBarra1.setBounds(48, 10, 12, 19);
		panelCreacion.add(lblCreacionBarra1);
		lblCreacionBarra1.setFont(new Font("Tahoma", Font.BOLD, 10));

		comboBoxCreacionDia = new JComboBox<Integer>(dia);
		comboBoxCreacionDia.setBounds(10, 10, 35, 19);
		panelCreacion.add(comboBoxCreacionDia);
		comboBoxCreacionDia.addActionListener(this);
		comboBoxCreacionDia.setBackground(Color.LIGHT_GRAY);
		comboBoxCreacionDia.setUI(new BasicComboBoxUI() {
			@Override
			protected JButton createArrowButton() {
				return new JButton() {
					private static final long serialVersionUID = 1L;

					@Override
					public int getWidth() {
						return 0; // Oculta la flecha hacia abajo de la comboBox
					}
				};
			}
		});

		lblCreacionMes = new JLabel("Mes");
		lblCreacionMes.setBounds(62, 28, 29, 13);
		panelCreacion.add(lblCreacionMes);
		lblCreacionMes.setForeground(Color.GRAY);
		lblCreacionMes.setFont(new Font("Dialog", Font.BOLD, 10));

		lblCreacionDia = new JLabel("Dia");
		lblCreacionDia.setBounds(10, 28, 21, 13);
		panelCreacion.add(lblCreacionDia);
		lblCreacionDia.setForeground(Color.GRAY);
		lblCreacionDia.setFont(new Font("Dialog", Font.BOLD, 10));

		ListaEquipos = Equipo.cargarEquipos();

		ListaJugadoresRegistrados = EquipoModificado.getListaJugadores();
		ListaMovimientos = Logger.cargarMovimientos();

		DatosEquipo = EquipoSeleccion.getEquipoSeleccionado();
		DatosJugadores = new ArrayList<Jugador>();
		DatosEquipo.getEscudo();

		llenarDatosEquipo();

		DiaEntrenador = Integer.parseInt("" + comboBoxJugadorDia.getSelectedItem());
		MesEntrenador = Integer.parseInt("" + comboBoxJugadorMes.getSelectedItem());
		AñoEntrenador = Integer.parseInt("" + comboBoxJugadorAño.getSelectedItem());
	}

	/**
	 * Funcion Action performed.
	 *
	 * @param e el Evento Accionado
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// Cierro la ventana Login
		// Condicion para dirigirnos a la funcion que corresponda
		if (e.getSource() == btnVolver) {
			Volver();
		} else if (e.getSource() == btnGuardar) {
			Guardar();
		} else if (e.getSource() == btnAñadirEscudo) {
			Escudo();
		} else if (e.getSource() == btnListaJugadoresAñadir) {
			Añadir();
		} else if (e.getSource() == btnListaJugadoresEliminar) {
			Eliminar();
		} else if (e.getSource() == btnListaJugadoresSubir) {
			AñadirFotoJugador();
		} else if (e.getSource() == comboBoxEntrenadorDia) {
			DiaEntrenador = (Integer) comboBoxEntrenadorDia.getSelectedItem();
		} else if (e.getSource() == comboBoxEntrenadorMes) {
			MesEntrenador = (Integer) comboBoxEntrenadorMes.getSelectedItem();
			Fecha(1);
		} else if (e.getSource() == comboBoxEntrenadorAño) {
			AñoEntrenador = (Integer) comboBoxEntrenadorAño.getSelectedItem();
			Fecha(1);
		} else if (e.getSource() == comboBoxCreacionDia) {
			DiaCreacion = (Integer) comboBoxCreacionDia.getSelectedItem();
		} else if (e.getSource() == comboBoxCreacionMes) {
			MesCreacion = (Integer) comboBoxCreacionMes.getSelectedItem();
			Fecha(2);
		} else if (e.getSource() == comboBoxCreacionAño) {
			AñoCreacion = (Integer) comboBoxCreacionAño.getSelectedItem();
			Fecha(2);
		} else if (e.getSource() == comboBoxJugadorDia) {
			DiaNacimiento = (Integer) comboBoxJugadorDia.getSelectedItem();
		} else if (e.getSource() == comboBoxJugadorMes) {
			MesNacimiento = (Integer) comboBoxJugadorMes.getSelectedItem();
			Fecha(3);
		} else if (e.getSource() == comboBoxJugadorAño) {
			AñoNacimiento = (Integer) comboBoxJugadorAño.getSelectedItem();
			Fecha(3);
		} else if (e.getSource() == comboBoxEntrenadorNacionalidad) {
			NacionalidadEntrenador = (String) comboBoxEntrenadorNacionalidad.getSelectedItem();
		} else if (e.getSource() == comboBoxListaJugadoresNacionalidad) {
			NacionalidadJugador = (String) comboBoxListaJugadoresNacionalidad.getSelectedItem();
		} else if (e.getSource() == comboBoxListaJugadoresPosicion) {
			PosicionJugador = (String) comboBoxListaJugadoresPosicion.getSelectedItem();
		}
	}

	/**
	 * Funcion para seleccionar el Escudo.
	 */
	private void Escudo() {
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de imagen", "jpg", "jpeg", "png");
		fileChooser.setFileFilter(filter);

		int result = fileChooser.showOpenDialog(this);

		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();

			// Verificar si el archivo tiene una extensión de imagen permitida
			String fileName = selectedFile.getName();
			String extension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
			if (!extension.equals("jpg") && !extension.equals("jpeg") && !extension.equals("png")) {
				JOptionPane.showMessageDialog(this, "Por favor, selecciona un archivo de imagen válido (jpg, jpeg o png).",
						"Error", JOptionPane.ERROR_MESSAGE);
				return;
			}

			// Almacenar la ruta del archivo seleccionado en la variable Escudo
			Escudo = selectedFile.getAbsolutePath();

			try {
				// Cargar la imagen en el JLabel
				ImageIcon imageIcon = new ImageIcon(Escudo);
				Image image = imageIcon.getImage().getScaledInstance(lblEscudo.getWidth(), lblEscudo.getHeight(),
						Image.SCALE_SMOOTH);
				lblEscudo.setIcon(new ImageIcon(image));

			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, "Error al cargar el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * Funcion para Subir la Foto de un Jugador.
	 */
	private void AñadirFotoJugador() {
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de imagen", "jpg", "jpeg", "png");
		fileChooser.setFileFilter(filter);

		int result = fileChooser.showOpenDialog(this);

		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();

			// Verificar si el archivo tiene una extensión de imagen permitida
			String fileName = selectedFile.getName();
			String extension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
			if (!extension.equals("jpg") && !extension.equals("jpeg") && !extension.equals("png")) {
				JOptionPane.showMessageDialog(this, "Por favor, selecciona un archivo de imagen válido (jpg, jpeg o png).",
						"Error", JOptionPane.ERROR_MESSAGE);
				return;
			}

			// Almacenar la ruta del archivo seleccionado en la variable FotoJugador
			FotoJugador = selectedFile.getAbsolutePath();

			try {
				// Cargar la imagen en el JLabel
				ImageIcon imageIcon = new ImageIcon(FotoJugador);
				Image image = imageIcon.getImage().getScaledInstance(lblFotoJugador.getWidth(), lblFotoJugador.getHeight(),
						Image.SCALE_SMOOTH);
				lblFotoJugador.setIcon(new ImageIcon(image));

			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, "Error al cargar el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * Funcion para seleccionar la Fecha.
	 */
	private void Fecha(Integer eleccion) {
		int selectedMonth;

		if (eleccion == 1) {
			selectedMonth = (Integer) comboBoxEntrenadorMes.getSelectedItem();
		} else if (eleccion == 2) {
			selectedMonth = (Integer) comboBoxCreacionMes.getSelectedItem();
		} else {
			selectedMonth = (Integer) comboBoxJugadorMes.getSelectedItem();
		}

		// Crear un nuevo array con la longitud adecuada según el mes seleccionado
		Integer[] newArray;

		if (selectedMonth == 2) {
			// Febrero, verificar si el año es bisiesto
			int selectedYear;
			if (eleccion == 1) {
				selectedYear = (Integer) comboBoxEntrenadorAño.getSelectedItem();
			} else if (eleccion == 2) {
				selectedYear = (Integer) comboBoxCreacionAño.getSelectedItem();
			} else {
				selectedYear = (Integer) comboBoxJugadorAño.getSelectedItem();
			}

			// Si es Bisiesto
			int daysInFebruary = (selectedYear % 4 == 0 && (selectedYear % 100 != 0 || selectedYear % 400 == 0)) ? 29 : 28;
			newArray = new Integer[daysInFebruary];
		} else if (selectedMonth == 4 || selectedMonth == 6 || selectedMonth == 9 || selectedMonth == 11) {
			// Meses con 30 días
			newArray = new Integer[30];
		} else {
			// Meses con 31 días
			newArray = new Integer[31];
		}

		// Llenar el array con días consecutivos
		for (int i = 0; i < newArray.length; i++) {
			newArray[i] = i + 1;
		}

		// Actualizar el array original con el nuevo array
		dia = newArray;

		// Actualizar el modelo del JComboBox con el nuevo array
		DefaultComboBoxModel<Integer> model = new DefaultComboBoxModel<>(dia);
		if (eleccion == 1) {
			comboBoxEntrenadorDia.setModel(model);

			// Actualizar el valor seleccionado en el JComboBox (poner el primer día)
			comboBoxEntrenadorDia.setSelectedItem(dia[0]);
		} else if (eleccion == 2) {
			comboBoxCreacionDia.setModel(model);

			// Actualizar el valor seleccionado en el JComboBox (poner el primer día)
			comboBoxCreacionDia.setSelectedItem(dia[0]);
		} else {
			comboBoxJugadorDia.setModel(model);

			// Actualizar el valor seleccionado en el JComboBox (poner el primer día)
			comboBoxJugadorDia.setSelectedItem(dia[0]);
		}
	}

	/**
	 * Funcion para Volver.
	 */
	private void Volver() {
		int opcion = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que quieres salir sin guardar?",
				"Cierre sin guardar", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

		if (opcion != JOptionPane.YES_OPTION) {
			// Si la opción no es "Sí", es decir, el usuario elige quedarse, entonces
			// simplemente regresamos
			return;
		}

		// Establecer el equipo seleccionado en EquipoSeleccion
		EquipoSeleccion.setEquipoSeleccionado(DatosEquipo);

		// Cerrar la ventana
		dispose();
	}

	/**
	 * Funcion para Guardar.
	 */
	private void Guardar() {
		// Obtener valores de los campos
		String NombreEquipo = textNombreEquipo.getText();
		String DNIEntrenador = textEntrenadorDNI.getText();
		String NombreEntrenador = textEntrenadorNombre.getText();
		String ApellidoEntrenador = textEntrenadorApellido.getText();
		String Descripcion = textDescripcion.getText();

		ListaEquipos.remove(EquipoSeleccion.getEquipoSeleccionado());

		// Cambiar el color de fondo a blanco para todos los campos
		resetearColoresCampos();

		// Comprobar si el nombre del equipo ya está en la lista de equipos registrados
		for (Equipo equipoRegistrado : ListaEquipos) {
			if (equipoRegistrado.getNombre().equals(NombreEquipo)) {
				resaltarCampo(textNombreEquipo);
				mostrarError("El nombre del equipo ya está registrado. Por favor, elige otro nombre.");
				return;
			}
		}

		// Validar campos
		if (esCampoInvalido(NombreEquipo) || NombreEquipo.length() > 10) {
			resaltarCampo(textNombreEquipo);
			mostrarError("Por favor, introduzca el Nombre del Equipo, el maximo de caracteres permitido es 9");
			return;
		}

		if (esCampoInvalido(DNIEntrenador) || DNIEntrenador.length() != 9 || verificarDNIDuplicado(DNIEntrenador)) {
			resaltarCampo(textEntrenadorDNI);
			mostrarError("Por favor, introduzca el DNI del Entrenador, debe contener 9 caracteres y debe ser unico");
			return;
		}

		if (esCampoInvalido(NombreEntrenador)) {
			resaltarCampo(textEntrenadorNombre);
			mostrarError("Por favor, introduzca el Nombre del Entrenador");
			return;
		}

		if (esCampoInvalido(ApellidoEntrenador)) {
			resaltarCampo(textEntrenadorApellido);
			mostrarError("Por favor, introduzca el Apellido del Entrenador");
			return;
		}

		if (esCampoInvalido(NacionalidadEntrenador) || NacionalidadEntrenador.equals("...")) {
			resaltarCampo(comboBoxEntrenadorNacionalidad);
			mostrarError("Por favor, seleccione la Nacionalidad del Entrenador");
			return;
		}

		if (jlm.getSize() < 5) {
			resaltarCampo(lstJugadores);
			mostrarError("El número mínimo de jugadores permitidos es de 5");
			return;
		}

		if (esCampoInvalido(Descripcion)) {
			resaltarCampo(textDescripcion);
			mostrarError("Por favor, introduzca la Descripción");
			return;
		}

		// Si todos los campos están validados correctamente, procedemos a guardar el
		// Escudo en la Carpeta
		try {
			String nombreArchivo = NombreEquipo + "." + obtenerExtension(Escudo);
			// Construir la ruta de destino en la carpeta /ficheros/escudos
			String destinationPath = "ficheros/escudos/" + nombreArchivo; // Suponiendo que la extensión sea ".png"

			// Copiar el archivo a la carpeta de destino
			Files.copy(Paths.get(Escudo), Paths.get(destinationPath), StandardCopyOption.REPLACE_EXISTING);

			Escudo = destinationPath;

		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error al copiar el archivo del escudo.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		// Iterar sobre cada ruta y copiar el archivo de la carpeta original a la
		// carpeta de destino
		for (Jugador jugador : ListaJugadoresRegistrados) {
			try {
				// Dividir la ruta para obtener la foto y el DNI del jugador
				String fotoJugador = jugador.getFoto();
				String dniJugador = jugador.getDNI();

				// Construir la ruta de destino en la carpeta /ficheros/Jugadores/ con el nombre
				// del DNI del jugador
				String ruta = "ficheros/Jugadores/" + NombreEquipo + "-" + dniJugador;

				if (!fotoJugador.startsWith(ruta)) {

					// Obtener solo el nombre del archivo desde la ruta completa
					String nombreArchivo = Paths.get(fotoJugador).getFileName().toString();

					// Construir la ruta de destino en la carpeta /ficheros/Jugadores/ con el nombre
					// del DNI del jugador
					String destino = "ficheros/Jugadores/" + NombreEquipo + "-" + dniJugador + "."
							+ obtenerExtension(nombreArchivo);

					// Copiar el archivo a la carpeta de destino
					Files.copy(Paths.get(fotoJugador), Paths.get(destino), StandardCopyOption.REPLACE_EXISTING);

					jugador.setFoto(destino);
				}
			} catch (IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, "Error al copiar las fotos de los jugadores.", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
		}

		for (String ruta : FotoJugadoresEliminados) {
			// Verificar si el archivo existe antes de intentar eliminarlo
			File archivo = new File(ruta);
			if (archivo.exists()) {
				try {
					// Eliminar foto de los jugadores eliminados
					Files.delete(Paths.get(ruta));
				} catch (IOException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(this, "Error al borrar las fotos de los Jugadores Eliminados", "Error",
							JOptionPane.ERROR_MESSAGE);
					// Dependiendo de tus necesidades, podrías decidir si quieres continuar con la
					// ejecución del método aquí
					return;
				}
			} else {
				// El archivo no existe en la ruta proporcionada
				System.err.println("El archivo no existe en la ruta: " + ruta);
			}
		}

		try {
			// Crear la conexión a la base de datos
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/csleague", "root", "");
			conn.setAutoCommit(false); // Desactivar el modo de autocommit
			
			if (!EquipoModificado.getNombre().equals(NombreEquipo)) {
				
				// Crear la consulta de inserción para el equipo
				String queryEquipo = "INSERT INTO equipo (Nombre, FechaCreacion) VALUES (?, ?)";
				PreparedStatement psEquipo = conn.prepareStatement(queryEquipo);
				psEquipo.setString(1, NombreEquipo);
				psEquipo.setString(2, AñoCreacion + "-" + MesCreacion + "-" + DiaCreacion); 
				
				psEquipo.executeUpdate();
				
				// Crear la consulta de inserción para la relación entre equipo y entrenador
				String queryTemporadaParticipada = "INSERT INTO temporadaparticipada (Temporada, Equipo, Escudo, Descripcion) VALUES (?, ?, ?, ?)";
				PreparedStatement psTemporadaParticipada = conn.prepareStatement(queryTemporadaParticipada);
				// Asignar los valores a los parámetros
				psTemporadaParticipada.setInt(1, 0); // Asumiendo que 'temporada' es un Integer con el número de temporada
				psTemporadaParticipada.setString(2, NombreEquipo);
				psTemporadaParticipada.setString(3, Escudo);
				psTemporadaParticipada.setString(4, Descripcion);
				psTemporadaParticipada.executeUpdate();
				
				// Eliminar registros asociados en entrenadorcontratado
				String deleteEntrenadorContratado = "DELETE FROM entrenadorcontratado WHERE Equipo = ?";
				PreparedStatement psDeleteEntrenadorContratado = conn.prepareStatement(deleteEntrenadorContratado);
				psDeleteEntrenadorContratado.setString(1, EquipoModificado.getNombre());
				psDeleteEntrenadorContratado.executeUpdate();

				// Eliminar registros asociados en entrenadorcontratado
				String deleteJugadorContratado = "DELETE FROM jugadorcontratado WHERE Equipo = ?";
				PreparedStatement psDeleteJugadorContratado = conn.prepareStatement(deleteJugadorContratado);
				psDeleteJugadorContratado.setString(1, EquipoModificado.getNombre());
				psDeleteJugadorContratado.executeUpdate();

				// Eliminar registros asociados en entrenadorcontratado
				String deleteTemporadaParticipada = "DELETE FROM temporadaparticipada WHERE Equipo = ?";
				PreparedStatement psDeleteTemporadaParticipada = conn.prepareStatement(deleteTemporadaParticipada);
				psDeleteTemporadaParticipada.setString(1, EquipoModificado.getNombre());
				psDeleteTemporadaParticipada.executeUpdate();

				// Eliminar el equipo
				String deleteEquipo = "DELETE FROM equipo WHERE Nombre = ?";
				PreparedStatement psDeleteEquipo = conn.prepareStatement(deleteEquipo);
				psDeleteEquipo.setString(1, EquipoModificado.getNombre());
				psDeleteEquipo.executeUpdate();
				
			}
			else {

			// Crear la consulta de actualización para el equipo
			String queryEquipo = "UPDATE equipo SET FechaCreacion = ? WHERE Nombre = ?";
			PreparedStatement psEquipo = conn.prepareStatement(queryEquipo);
			psEquipo.setString(1, AñoCreacion + "-" + MesCreacion + "-" + DiaCreacion);
			psEquipo.setString(2, NombreEquipo);
			psEquipo.executeUpdate();
			psEquipo.close();
			


			// Crear la consulta de actualizacion para la relación entre equipo y temporada
			String queryTemporadaParticipada = "UPDATE temporadaparticipada SET Escudo = ?, Descripcion = ? WHERE Temporada = ? AND Equipo = ?";
			PreparedStatement psTemporadaParticipada = conn.prepareStatement(queryTemporadaParticipada);
			// Asignar los valores a los parámetros
			psTemporadaParticipada.setString(1, Escudo); // Asumiendo que 'temporada' es un Integer con el número de
																		// temporada
			psTemporadaParticipada.setString(2, Descripcion);
			psTemporadaParticipada.setInt(3, 0);
			psTemporadaParticipada.setString(4, NombreEquipo);
			psTemporadaParticipada.executeUpdate();
			
			}

			// Crear la consulta SQL para verificar si ya existe un entrenador con el mismo
			// DNI y la misma fecha de alta
			String queryVerificarEntrenador = "SELECT COUNT(*) FROM entrenador WHERE DNI = ?";
			PreparedStatement psVerificarEntrenador = conn.prepareStatement(queryVerificarEntrenador);
			psVerificarEntrenador.setString(1, DNIEntrenador);
			ResultSet rsVerificarEntrenador = psVerificarEntrenador.executeQuery();
			rsVerificarEntrenador.next();
			int countEntrenador = rsVerificarEntrenador.getInt(1);
			rsVerificarEntrenador.close();
			psVerificarEntrenador.close();

			// Verificar si ya existe un entrenador con el mismo DNI
			if (countEntrenador == 0) {
				// Crear la consulta de inserción para el entrenador
				String queryEntrenador = "INSERT INTO entrenador (DNI, FechaAlta) VALUES (?, ?)";
				PreparedStatement psEntrenador = conn.prepareStatement(queryEntrenador);
				psEntrenador.setString(1, DNIEntrenador);
				psEntrenador.setString(2, AñoEntrenador + "-" + MesEntrenador + "-" + DiaEntrenador); // Asumiendo que // //
																																	// AñoEntrenador son
																																	// // Integer
				psEntrenador.executeUpdate();

				// Crear la consulta de inserción para la relación entre equipo y entrenador
				String queryEquipoEntrenador = "INSERT INTO entrenadorcontratado (Temporada, Equipo, Entrenador, Nombre, Apellido, Nacionalidad) VALUES (?, ?, ?, ?, ?, ?)";
				PreparedStatement psEquipoEntrenador = conn.prepareStatement(queryEquipoEntrenador);
				// Asignar los valores a los parámetros
				psEquipoEntrenador.setInt(1, 0); // Asumiendo que 'temporada' es un Integer con el número de temporada
				psEquipoEntrenador.setString(2, NombreEquipo);
				psEquipoEntrenador.setString(3, DNIEntrenador);
				psEquipoEntrenador.setString(4, NombreEntrenador);
				psEquipoEntrenador.setString(5, ApellidoEntrenador);
				psEquipoEntrenador.setString(6, NacionalidadEntrenador);
				psEquipoEntrenador.executeUpdate();
			} else {
				// Crear la consulta de actualización para el entrenador
				String queryEntrenador = "UPDATE entrenador SET FechaAlta = ? WHERE DNI = ?";
				PreparedStatement psEntrenador = conn.prepareStatement(queryEntrenador);
				psEntrenador.setString(1, AñoEntrenador + "-" + MesEntrenador + "-" + DiaEntrenador); // Asumiendo que
																																	// DiaCreacion,
				psEntrenador.setString(2, DNIEntrenador);
				psEntrenador.executeUpdate();
				psEntrenador.close();
				
			// Crear la consulta SQL para verificar si ya existe un entrenador con el mismo
				// DNI y la misma fecha de alta
				String queryVerificarEntrenadorContratado = "SELECT COUNT(*) FROM entrenadorcontratado WHERE Entrenador = ? AND Equipo = ? AND Temporada = ? ";
				PreparedStatement psVerificarEntrenadorContratado = conn.prepareStatement(queryVerificarEntrenadorContratado);
				psVerificarEntrenadorContratado.setString(1, DNIEntrenador);
				psVerificarEntrenadorContratado.setString(2, NombreEquipo);
				psVerificarEntrenadorContratado.setInt(3, 0);
				ResultSet rsVerificarEntrenadorContratado = psVerificarEntrenadorContratado.executeQuery();
				rsVerificarEntrenadorContratado.next();
				int countEntrenadorContratado = rsVerificarEntrenadorContratado.getInt(1);
				rsVerificarEntrenadorContratado.close();
				rsVerificarEntrenadorContratado.close();

				if (countEntrenadorContratado == 0) {

					// Crear la consulta de inserción para la relación entre equipo y entrenador
					String queryEquipoEntrenador = "INSERT INTO entrenadorcontratado (Temporada, Equipo, Entrenador, Nombre, Apellido, Nacionalidad) VALUES (?, ?, ?, ?, ?, ?)";
					PreparedStatement psEquipoEntrenador = conn.prepareStatement(queryEquipoEntrenador);
					// Asignar los valores a los parámetros
					psEquipoEntrenador.setInt(1, 0); // Asumiendo que 'temporada' es un Integer con el número de temporada
					psEquipoEntrenador.setString(2, NombreEquipo);
					psEquipoEntrenador.setString(3, DNIEntrenador);
					psEquipoEntrenador.setString(4, NombreEntrenador);
					psEquipoEntrenador.setString(5, ApellidoEntrenador);
					psEquipoEntrenador.setString(6, NacionalidadEntrenador);
					psEquipoEntrenador.executeUpdate();

				} else {
					// Crear la consulta de actualización para el entrenador
					String queryEntrenadorContratado = "UPDATE entrenadorcontratado SET Nombre = ?, Apellido = ?, Nacionalidad = ? WHERE Entrenador = ? AND Equipo = ? AND Temporada = ?";
					PreparedStatement psEntrenadorContratado = conn.prepareStatement(queryEntrenadorContratado);
					psEntrenadorContratado.setString(1, NombreEntrenador);
					psEntrenadorContratado.setString(2, ApellidoEntrenador);
					psEntrenadorContratado.setString(3, NacionalidadEntrenador);
					psEntrenadorContratado.setString(4, DNIEntrenador);
					psEntrenadorContratado.setString(5, NombreEquipo);
					psEntrenadorContratado.setInt(6, 0);
					psEntrenadorContratado.executeUpdate();
					psEntrenadorContratado.close();
				}

			}

			for (Jugador jugador : ListaJugadoresRegistrados) {

				// Crear la consulta SQL para verificar si ya existe un entrenador con el mismo
				// DNI y la misma fecha de alta
				String queryVerificarJugador = "SELECT COUNT(*) FROM jugador WHERE DNI = ?";
				PreparedStatement psVerificarJugador = conn.prepareStatement(queryVerificarJugador);
				psVerificarJugador.setString(1, jugador.getDNI());
				ResultSet rsVerificarJugador = psVerificarJugador.executeQuery();
				rsVerificarJugador.next();
				int countJugador = rsVerificarJugador.getInt(1);
				rsVerificarJugador.close();
				rsVerificarJugador.close();

				// Verificar si ya existe un entrenador con el mismo DNI
				if (countJugador == 0) {
					// Crear la consulta de inserción para el entrenador
					String queryJugador = "INSERT INTO jugador (DNI, FechaNacimiento) VALUES (?, ?)";
					PreparedStatement psJugador = conn.prepareStatement(queryJugador);
					psJugador.setString(1, jugador.getDNI());
					psJugador.setString(2, jugador.getFechaNacimiento().getAño() + "-"
							+ jugador.getFechaNacimiento().getMes() + "-" + jugador.getFechaNacimiento().getDia());

					psJugador.executeUpdate();

					// Crear la consulta de inserción para la relación entre equipo y entrenador
					String queryJugadorContratado = "INSERT INTO jugadorcontratado (Temporada, Equipo, Jugador, Nombre, Apellido, Nacionalidad, Foto, Rol) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
					PreparedStatement psJugadorContratado = conn.prepareStatement(queryJugadorContratado);
					// Asignar los valores a los parámetros
					psJugadorContratado.setInt(1, 0); // Asumiendo que 'temporada' es un Integer con el número de temporada
					psJugadorContratado.setString(2, NombreEquipo);
					psJugadorContratado.setString(3, jugador.getDNI());
					psJugadorContratado.setString(4, jugador.getNombre());
					psJugadorContratado.setString(5, jugador.getApellido());
					psJugadorContratado.setString(6, jugador.getNacionalidad());
					psJugadorContratado.setString(7, jugador.getFoto());
					psJugadorContratado.setString(8, jugador.getPosicion());
					psJugadorContratado.executeUpdate();
				} else {
					// Crear la consulta de inserción para el entrenador
					String queryJugador = "UPDATE jugador SET FechaNacimiento = ? WHERE DNI = ? ";
					PreparedStatement psJugador = conn.prepareStatement(queryJugador);
					psJugador.setString(1, jugador.getFechaNacimiento().getAño() + "-"
							+ jugador.getFechaNacimiento().getMes() + "-" + jugador.getFechaNacimiento().getDia());
					psJugador.setString(2, jugador.getDNI());

					psJugador.executeUpdate();

					// Crear la consulta SQL para verificar si ya existe un entrenador con el mismo
					// DNI y la misma fecha de alta
					String queryVerificarJugadorContratado = "SELECT COUNT(*) FROM jugadorcontratado WHERE Jugador = ? AND Equipo = ? AND Temporada = ? ";
					PreparedStatement psVerificarJugadorContratado = conn.prepareStatement(queryVerificarJugadorContratado);
					psVerificarJugadorContratado.setString(1, jugador.getDNI());
					psVerificarJugadorContratado.setString(2, NombreEquipo);
					psVerificarJugadorContratado.setInt(3, 0);
					ResultSet rsVerificarJugadorContratado = psVerificarJugadorContratado.executeQuery();
					rsVerificarJugadorContratado.next();
					int countJugadorContratado = rsVerificarJugadorContratado.getInt(1);
					rsVerificarJugadorContratado.close();
					rsVerificarJugadorContratado.close();

					if (countJugadorContratado == 0) {

						// Crear la consulta de inserción para la relación entre equipo y entrenador
						String queryJugadorContratado = "INSERT INTO jugadorcontratado (Temporada, Equipo, Jugador, Nombre, Apellido, Nacionalidad, Foto, Rol) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
						PreparedStatement psJugadorContratado = conn.prepareStatement(queryJugadorContratado);
						// Asignar los valores a los parámetros
						psJugadorContratado.setInt(1, 0); // Asumiendo que 'temporada' es un Integer con el número de
																		// temporada
						psJugadorContratado.setString(2, NombreEquipo);
						psJugadorContratado.setString(3, jugador.getDNI());
						psJugadorContratado.setString(4, jugador.getNombre());
						psJugadorContratado.setString(5, jugador.getApellido());
						psJugadorContratado.setString(6, jugador.getNacionalidad());
						psJugadorContratado.setString(7, jugador.getFoto());
						psJugadorContratado.setString(8, jugador.getPosicion());
						psJugadorContratado.executeUpdate();

					} else {
						// Crear la consulta de inserción para la relación entre equipo y entrenador
						String queryJugadorContratado = "UPDATE jugadorcontratado SET Nombre = ?, Apellido = ?, Nacionalidad = ?, Foto = ?, Rol = ? WHERE Jugador = ? AND Equipo = ? AND Temporada = ?";
						PreparedStatement psJugadorContratado = conn.prepareStatement(queryJugadorContratado);
						// Asignar los valores a los parámetros
						psJugadorContratado.setString(1, jugador.getNombre());
						psJugadorContratado.setString(2, jugador.getApellido());
						psJugadorContratado.setString(3, jugador.getNacionalidad());
						psJugadorContratado.setString(4, jugador.getFoto());
						psJugadorContratado.setString(5, jugador.getPosicion());
						psJugadorContratado.setString(6, jugador.getDNI()); // Asumiendo que 'temporada' es un Integer con el
																								// número de temporada
						psJugadorContratado.setString(7, NombreEquipo);
						psJugadorContratado.setInt(8, 0);
						psJugadorContratado.executeUpdate();
						psJugadorContratado.close();
					}
				}
			}

			// Confirmar la transacción
			conn.commit();

			conn.close();

			// Guardo los datos
			EquipoModificado.setNombre(NombreEquipo);
			EquipoModificado.setEscudo(Escudo);
			EquipoModificado.getEntrenador().setDNI(DNIEntrenador);
			EquipoModificado.getEntrenador().setNombre(NombreEntrenador);
			EquipoModificado.getEntrenador().setApellido(ApellidoEntrenador);
			EquipoModificado.getEntrenador().setNacionalidad(NacionalidadEntrenador);
			EquipoModificado.getEntrenador().getFechaAlta().setDia(DiaEntrenador);
			EquipoModificado.getEntrenador().getFechaAlta().setMes(MesEntrenador);
			EquipoModificado.getEntrenador().getFechaAlta().setAño(AñoEntrenador);
			EquipoModificado.setListaJugadores(ListaJugadoresRegistrados);
			EquipoModificado.setDescripcion(Descripcion);

			JOptionPane.showMessageDialog(this, "Los datos del equipo han sido actualizados correctamente.",
					"Actualización Exitosa", JOptionPane.INFORMATION_MESSAGE);

			dispose(); // Cerrar la ventana
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error al actualizar los datos del equipo en la base de datos.",
					"Error de Actualización", JOptionPane.ERROR_MESSAGE);
		}

		EquipoSeleccion.setEquipoSeleccionado(EquipoModificado);

		EquipoSeleccion.setGuardado(true);

		Logger.nuevoMovimiento(ListaMovimientos, "Ha editado el Equipo " + EquipoModificado.getNombre() + ".");

		JOptionPane.showMessageDialog(this, "Se ha Editado el Equipo", "Equipo Editado", JOptionPane.INFORMATION_MESSAGE);

		dispose();
	}

	/**
	 * Funcion para Añadir un Jugador a la Lista de Jugadores.
	 */
	private void Añadir() {
		String DNIJugador = textListaJugadoresDNI.getText();
		String DNIEntrenador = textEntrenadorDNI.getText();
		String NombreJugador = textListaJugadoresNombre.getText();
		String ApellidoJugador = textListaJugadoresApellido.getText();

		// Cambio de color del fondo del todo a Blanco
		resetearColoresCampos();

		if (DNIJugador.equals(DNIEntrenador)) {
			// Se encontró un DNI duplicado
			resaltarCampo(textListaJugadoresDNI);
			// Mostramos mensaje de error
			mostrarError("Lo siento, el DNI que intenta introducir lo tiene asignado el Entrenador");
			return;
		}

		if (verificarDNIDuplicado(DNIJugador)) {
			resaltarCampo(textListaJugadoresDNI);
			// Mostramos mensaje de error
			mostrarError("Lo siento, el DNI que intenta introducir se encuentra repetido");
			return;
		}

		if (esCampoInvalido(DNIJugador) || DNIJugador.length() != 9) {
			// Cambio de color del fondo del DNI
			resaltarCampo(textListaJugadoresDNI);
			// Mostramos mensaje de error
			mostrarError("Por favor, introduzca el DNI del Jugador, debe contener 9 caracteres");
			return;
		} else if (esCampoInvalido(NombreJugador)) {
			// Cambio de color del fondo del DNI
			resaltarCampo(textListaJugadoresNombre);
			// Mostramos mensaje de error
			mostrarError("Por favor, introduzca el Nombre del Jugador");
			return;
		} else if (esCampoInvalido(ApellidoJugador)) {
			// Cambio de color del fondo del DNI
			resaltarCampo(textListaJugadoresApellido);
			// Mostramos mensaje de error
			mostrarError("Por favor, introduzca el Apellido del Jugador");
			return;
		} else if (NacionalidadJugador.equals("...")) {
			// Cambio de color del fondo del DNI
			resaltarCampo(comboBoxListaJugadoresNacionalidad);
			// Mostramos mensaje de error
			mostrarError("Por favor, seleccione la Nacionalidad del Jugador");
			return;
		} else if (PosicionJugador.equals("...")) {
			// Cambio de color del fondo del DNI
			resaltarCampo(comboBoxListaJugadoresPosicion);
			// Mostramos mensaje de error
			mostrarError("Por favor, seleccione la Posición del Jugador");
			return;
		} else if (FotoJugador.isEmpty()) {
			resaltarCampo(lblFotoJugador);
			// Mostramos mensaje de errors
			mostrarError("Por favor, seleccione la Foto del Jugador");
			return;
		}

		// Guardar la foto y el DNI del jugador en la variable que guarda todas las
		// fotos y DNIs
		escudosJugadores.append(FotoJugador).append(",").append(DNIJugador).append(";");

		// Crear un nuevo objeto Jugador con la información proporcionada
		Participante nuevoParticipante = new Participante(DNIJugador, NombreJugador, ApellidoJugador,
				NacionalidadJugador);
		// Crear un nuevo objeto Fecha con la información proporcionada
		Fecha FechaN = new Fecha(DiaNacimiento, MesNacimiento, AñoNacimiento);

		Jugador nuevoJugador = new Jugador(nuevoParticipante, FotoJugador, PosicionJugador, FechaN);

		// Agregas el nuevo usuario a la lista
		ListaJugadoresRegistrados.add(nuevoJugador);

		// Añadir el nuevo jugador al DefaultListModel
		jlm.addElement(nuevoJugador);

		// Limpiar los campos después de añadir el jugador
		textListaJugadoresDNI.setText("");
		textListaJugadoresNombre.setText("");
		textListaJugadoresApellido.setText("");
		comboBoxListaJugadoresNacionalidad.setSelectedIndex(0);
		comboBoxListaJugadoresPosicion.setSelectedIndex(0);
		lblFotoJugador.setIcon(null);
		
		EquipoModificado.setListaJugadores(ListaJugadoresRegistrados);

		lstJugadores.clearSelection();

		// Eliminar las rutas del array FotoJugadoresEliminados que coincidan con el DNI
		// del jugador añadido
		String dniNuevoJugador = nuevoJugador.getDNI();
		Iterator<String> iterator = FotoJugadoresEliminados.iterator();
		while (iterator.hasNext()) {
			String ruta = iterator.next();
			if (ruta.contains(dniNuevoJugador)) {
				iterator.remove(); // Eliminar la ruta del jugador del array
			}
		}
	}

	/**
	 * Funcion para Eliminar un Jugador a la Lista de Jugadores.
	 */
	private void Eliminar() {
		// Obtengo las posiciones de los elementos seleccionados
		int[] indicesSeleccionados = lstJugadores.getSelectedIndices();

		// Compruebo si hay algún elemento seleccionado
		if (indicesSeleccionados.length > 0) {
			// Si hay elementos seleccionados, elimino cada uno de ellos en orden inverso
			for (int i = indicesSeleccionados.length - 1; i >= 0; i--) {
				jlm.remove(indicesSeleccionados[i]);

				// Guardo el DNI del Jugador Eliminado
				FotoJugadoresEliminados.add(ListaJugadoresRegistrados.get(indicesSeleccionados[i]).getFoto());

				// Elimino el jugador correspondiente de la lista general
				ListaJugadoresRegistrados.remove(indicesSeleccionados[i]);
				
				EquipoModificado.setListaJugadores(ListaJugadoresRegistrados);

			}

			// Verifico si la lista está vacía después de la eliminación
			if (jlm.getSize() == 0) {
				// Haz algo si la lista está vacía, si es necesario
			}
		} else {
			// No hay ningún elemento seleccionado
			mostrarError("Error, ningún elemento seleccionado en la lista");
		}
	}

	/**
	 * Funcion para Resetear los colores de los campos por defecto.
	 */
	private void resetearColoresCampos() {
		textNombreEquipo.setBackground(Color.WHITE);
		lblEscudo.setBorder(new LineBorder(Color.BLACK));
		textEntrenadorDNI.setBackground(Color.WHITE);
		textEntrenadorNombre.setBackground(Color.WHITE);
		textEntrenadorApellido.setBackground(Color.WHITE);
		comboBoxEntrenadorNacionalidad.setBackground(Color.WHITE);
		comboBoxEntrenadorDia.setBackground(Color.LIGHT_GRAY);
		comboBoxEntrenadorMes.setBackground(Color.LIGHT_GRAY);
		comboBoxEntrenadorAño.setBackground(Color.LIGHT_GRAY);
		comboBoxJugadorDia.setBackground(Color.LIGHT_GRAY);
		comboBoxJugadorMes.setBackground(Color.LIGHT_GRAY);
		comboBoxJugadorAño.setBackground(Color.LIGHT_GRAY);
		lstJugadores.setBackground(Color.WHITE);
		textDescripcion.setBackground(Color.WHITE);

		textListaJugadoresDNI.setBackground(Color.WHITE);
		textListaJugadoresNombre.setBackground(Color.WHITE);
		textListaJugadoresApellido.setBackground(Color.WHITE);
		comboBoxListaJugadoresNacionalidad.setBackground(Color.WHITE);
		comboBoxListaJugadoresPosicion.setBackground(Color.WHITE);
		lblFotoJugador.setBorder(new LineBorder(Color.BLACK));
	}

	/**
	 * Funcion para verificar si Es Campo Invalido.
	 *
	 * @param campo el Campo que se quiere comprobar
	 * @return true, si el contenido es null o esta vacio
	 */
	private boolean esCampoInvalido(String campo) {
		return campo == null || campo.trim().isEmpty();
	}

	/**
	 * Funcion para Resaltar el Campo.
	 *
	 * @param componente el Componente que se quiere resaltar
	 */
	private void resaltarCampo(JComponent componente) {
		if (componente instanceof JTextField) {
			((JTextField) componente).setBackground(new Color(255, 192, 183));
		} else if (componente instanceof JLabel) {
			((JLabel) componente).setBorder(new LineBorder(new Color(255, 192, 183)));
		} else if (componente instanceof JComboBox) {
			((JComboBox<?>) componente).setBackground(new Color(255, 192, 183));
		} else if (componente instanceof JList) {
			((JList<?>) componente).setBackground(new Color(255, 192, 183));
		} else if (componente instanceof JTextArea) {
			((JTextArea) componente).setBackground(new Color(255, 192, 183));
		}
	}

	/**
	 * Funcion para Mostrar un Error.
	 *
	 * @param mensaje el Mensaje de Error
	 */
	private void mostrarError(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje, "Campo Erroneo", JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Funcion para Verificar un DNI Duplicado.
	 *
	 * @param nuevoDNI el Nuevo DNI introducido
	 * @return true, si el DNI esta Duplicado
	 */
	private boolean verificarDNIDuplicado(String nuevoDNI) {
		for (Participante participante : ListaJugadoresRegistrados) {
			if (participante.getDNI().equals(nuevoDNI)) {
				// Se encontró un DNI duplicado
				return true;
			}
		}
		// No se encontraron DNIs duplicados
		return false;
	}

	/**
	 * Funcion par Inicializar el año de las Fechas.
	 */
	private void inicializarAño() {
		año = new Integer[121]; // 2024 - 1904 + 1

		for (int i = 2024; i >= 1904; i--) {
			año[2024 - i] = i;
		}
	}

	/**
	 * Funcion para identificar a traves de que Ventana AñadirTemporada se ha
	 * abierto esta Clase.
	 *
	 * @param ventanaTemporada la Ventan AñadirTemporada que se busca identificar
	 */
	public void setVentanaTemporada(AñadirTemporada ventanaTemporada) {
		this.AT = ventanaTemporada;
	}

	/**
	 * Funcion para identificar a traves de que Ventana EditarTemporada se ha
	 * abierto esta Clase.
	 *
	 * @param EditarTemp la Ventan EditarTemporada que se busca identificar
	 */
	public void setVentanaEditar(EditarTemp EditarTemp) {
		this.ET = EditarTemp;
	}

	/**
	 * Funcion para identificar a traves de que Ventana Panel se ha abierto esta
	 * Clase.
	 *
	 * @param panel la Ventan Panel que se busca identificar
	 */
	public void setVentanaPanelEditar(Panel panel) {
		this.P = panel;
	}

	/**
	 * Funcion para Llenar los Datos del Equipo.
	 */
	private void llenarDatosEquipo() {
		textNombreEquipo.setText(EquipoModificado.getNombre());
		// Cargar la imagen en el JLabel
		ImageIcon imageIcon = new ImageIcon(EquipoModificado.getEscudo());
		Image image = imageIcon.getImage().getScaledInstance(lblEscudo.getWidth(), lblEscudo.getHeight(),
				Image.SCALE_SMOOTH);
		lblEscudo.setIcon(new ImageIcon(image));

		Escudo = EquipoModificado.getEscudo();

		// Icono del Equipo Modificado
		textEntrenadorDNI.setText(EquipoModificado.getEntrenador().getDNI());
		textEntrenadorNombre.setText(EquipoModificado.getEntrenador().getNombre());
		textEntrenadorApellido.setText(EquipoModificado.getEntrenador().getApellido());
		comboBoxEntrenadorNacionalidad.setSelectedItem(EquipoModificado.getEntrenador().getNacionalidad());
		comboBoxEntrenadorDia.setSelectedItem(EquipoModificado.getEntrenador().getFechaAlta().getDia());
		comboBoxEntrenadorMes.setSelectedItem(EquipoModificado.getEntrenador().getFechaAlta().getMes());
		comboBoxEntrenadorAño.setSelectedItem(EquipoModificado.getEntrenador().getFechaAlta().getAño());
		comboBoxCreacionDia.setSelectedItem(EquipoModificado.getFechaCreacion().getDia());
		comboBoxCreacionMes.setSelectedItem(EquipoModificado.getFechaCreacion().getMes());
		comboBoxCreacionAño.setSelectedItem(EquipoModificado.getFechaCreacion().getAño());

		// Añadir los jugadores
		for (Jugador jugador : EquipoModificado.getListaJugadores()) {
			jlm.addElement(jugador);
			DatosJugadores.add(jugador);
		}

		textDescripcion.setText(EquipoModificado.getDescripcion());

	}

	/**
	 * Función para obtener la extensión de un archivo.
	 */
	private String obtenerExtension(String nombreArchivo) {
		int lastIndex = nombreArchivo.lastIndexOf(".");
		if (lastIndex == -1) {
			return ""; // No se encontró una extensión
		}
		return nombreArchivo.substring(lastIndex + 1);
	}

	/**
	 * Funcion que Guarda los cambios de Seleccion de la Lista de Jugadores.
	 *
	 * @param e el Evento de Seleccion de la Lista
	 */
	@Override
	public void valueChanged(ListSelectionEvent e) {
		// Obtener el índice seleccionado en la lista activa
		int selectedIndex = lstJugadores.getSelectedIndex();

		if (selectedIndex != -1) {
			// Obtener el DNI del jugador seleccionado
			String dniJugador = jlm.getElementAt(selectedIndex).getDNI();

			// Obtener el nombre del equipo del jugador seleccionado
			String nombreEquipo = EquipoSeleccion.getEquipoSeleccionado().getNombre();

			// Construir la ruta del escudo del jugador seleccionado
			String rutaEscudoJugador = "ficheros/Jugadores/" + nombreEquipo + "-" + dniJugador + "."
					+ obtenerExtension(jlm.getElementAt(selectedIndex).getFoto());

			// Verificar si la ruta del escudo del jugador no encuentra el archivo
			File escudoJugadorFile = new File(rutaEscudoJugador);
			if (!escudoJugadorFile.exists()) {
				// Ejecutar el código que has proporcionado si la ruta del escudo no encuentra
				// el archivo
				// Obtener la cadena de rutas de las fotos de los jugadores desde la variable
				// escudosJugadores
				String todasLasRutasEscudos = escudosJugadores.toString();

				// Dividir la cadena en un array de rutas individuales
				String[] rutasEscudos = todasLasRutasEscudos.split(";");

				// Iterar sobre cada ruta para encontrar el escudo correspondiente al jugador
				// seleccionado
				for (String rutaEscudo : rutasEscudos) {
					String[] fotoYDNI = rutaEscudo.split(",");
					String dniEscudo = fotoYDNI[1];

					// Verificar si el DNI coincide con el del jugador seleccionado
					if (dniEscudo.equals(dniJugador)) {
						// Obtener la ruta del escudo del jugador seleccionado
						rutaEscudoJugador = fotoYDNI[0];

						// Cargar la imagen y mostrarla en el componente correspondiente
						ImageIcon imagen = new ImageIcon(rutaEscudoJugador);
						Image imagenEscalada = imagen.getImage().getScaledInstance(lblFotoJugador.getWidth(),
								lblFotoJugador.getHeight(), Image.SCALE_SMOOTH);
						lblFotoJugador.setIcon(new ImageIcon(imagenEscalada));

						FotoJugador = rutaEscudoJugador;

						// Salir del bucle una vez que se encuentre el escudo correspondiente
						break;
					}
				}
			} else {
				// Cargar la imagen y mostrarla en el componente correspondiente si la ruta del
				// escudo encuentra el archivo
				ImageIcon imagen = new ImageIcon(rutaEscudoJugador);
				Image imagenEscalada = imagen.getImage().getScaledInstance(lblFotoJugador.getWidth(),
						lblFotoJugador.getHeight(), Image.SCALE_SMOOTH);
				lblFotoJugador.setIcon(new ImageIcon(imagenEscalada));

				FotoJugador = rutaEscudoJugador;
			}

			// Cambiar valores de los txt
			textListaJugadoresDNI.setText(jlm.getElementAt(selectedIndex).getDNI());
			textListaJugadoresNombre.setText(jlm.getElementAt(selectedIndex).getNombre());
			textListaJugadoresApellido.setText(jlm.getElementAt(selectedIndex).getApellido());
			comboBoxListaJugadoresNacionalidad.setSelectedItem(jlm.getElementAt(selectedIndex).getNacionalidad());
			comboBoxListaJugadoresPosicion.setSelectedItem(jlm.getElementAt(selectedIndex).getPosicion());
			comboBoxJugadorAño.setSelectedItem(jlm.getElementAt(selectedIndex).getFechaNacimiento().getAño());
			comboBoxJugadorMes.setSelectedItem(jlm.getElementAt(selectedIndex).getFechaNacimiento().getMes());
			comboBoxJugadorDia.setSelectedItem(jlm.getElementAt(selectedIndex).getFechaNacimiento().getDia());
		}
	}

	/**
	 * Window opened.
	 *
	 * @param e the e
	 */
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * Window closing.
	 *
	 * @param e the e
	 */
	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub

		int opcion = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que quieres salir sin guardar?",
				"Cierre sin guardar", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

		if (opcion != JOptionPane.YES_OPTION) {
			// Si la opción no es "Sí", es decir, el usuario elige quedarse, entonces
			// simplemente regresamos
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			return;
		}

		// Eliminar el equipo de la lista (si existe)
		ListaEquipos.remove(DatosEquipo);

		// Limpiar la lista de jugadores del equipo
		DatosEquipo.getListaJugadores().clear();

		if (DatosJugadores != null) {
			// Agregar los jugadores de DatosJugadores a la lista de jugadores del equipo
			for (Jugador jugador : DatosJugadores) {
				DatosEquipo.getListaJugadores().add(jugador);
			}
		}

		// Agregar el equipo de vuelta a la lista
		ListaEquipos.add(DatosEquipo);

		// Establecer el equipo seleccionado en EquipoSeleccion
		EquipoSeleccion.setEquipoSeleccionado(DatosEquipo);

		// Cerrar la ventana
		dispose();

	}

	/**
	 * Window closed.
	 *
	 * @param e the e
	 */
	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * Window iconified.
	 *
	 * @param e the e
	 */
	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * Window deiconified.
	 *
	 * @param e the e
	 */
	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * Window activated.
	 *
	 * @param e the e
	 */
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * Window deactivated.
	 *
	 * @param e the e
	 */
	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * Clase encargada de poner formato a la JList que muestra los jugadores.
	 */
	public class JugadorListCellRenderer extends JLabel implements ListCellRenderer<Jugador> {

		private static final long serialVersionUID = 2078337753208769960L;

		@Override
		public Component getListCellRendererComponent(JList<? extends Jugador> list, Jugador jugador, int index,
				boolean isSelected, boolean cellHasFocus) {
			setText(jugador.toString());

			String fotoPath = jugador.getFoto();

			// Verificar si la imagen existe en el directorio
			File fotoFile = new File(fotoPath);
			if (fotoFile.exists() && !fotoFile.isDirectory()) {
				// La imagen existe, establecerla como ícono
				ImageIcon icon = new ImageIcon(fotoPath);
				Image image = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
				setIcon(new ImageIcon(image));
			} else {
				// Obtener el DNI del jugador seleccionado
				String dniJugador = jugador.getDNI();

				// Buscar la ruta de la imagen correspondiente al DNI del jugador en
				// escudosJugadores
				String rutaImagen = null;
				String todasLasRutasEscudos = escudosJugadores.toString();
				String[] rutasEscudos = todasLasRutasEscudos.split(";");
				for (String rutaEscudo : rutasEscudos) {
					String[] fotoYDNI = rutaEscudo.split(",");
					String dniEscudo = fotoYDNI[1];
					if (dniEscudo.equals(dniJugador)) {
						rutaImagen = fotoYDNI[0];
						break;
					}
				}

				// Establecer el icono de la celda si se encontró la ruta de la imagen
				// correspondiente
				if (rutaImagen != null) {
					// Escalar la imagen para que se ajuste al tamaño del JLabel
					ImageIcon icon = new ImageIcon(rutaImagen);
					Image image = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
					setIcon(new ImageIcon(image));
				} else {
					// Si no se encuentra la ruta de la imagen, establecer el ícono como nulo
					setIcon(null);
				}
			}

			if (isSelected) {
				setBackground(list.getSelectionBackground());
				setForeground(list.getSelectionForeground());
			} else {
				setBackground(list.getBackground());
				setForeground(list.getForeground());
			}

			setEnabled(true);
			setFont(list.getFont());
			setOpaque(true);

			return this;
		}
	}

}
