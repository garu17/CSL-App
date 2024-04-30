package aplicacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicComboBoxUI;

import definicion.Equipo;
import definicion.EquipoSeleccion;
import definicion.Estadisticas;
import definicion.Fecha;
import definicion.Jornada;
import definicion.Jugador;
import definicion.Logger;
import definicion.Participante;
import definicion.Partido;
import definicion.Seleccion;
import definicion.Temporada;

import javax.swing.JTextField;
import javax.swing.JComponent;
import javax.swing.ScrollPaneConstants;
import javax.swing.ListSelectionModel;

/**
 * La Clase AñadirTemporada.
 */
public class AñadirTemporada extends JFrame implements ActionListener, ListSelectionListener {

	/** La Constante serialVersionUID generada para identificarse. */
	private static final long serialVersionUID = 1L;

	/** El Content Pane principal. */
	private JPanel contentPane;

	/** El Boton de Volver. */
	private JButton btnVolver;

	/** El Label del Numero de Temporada. */
	private JLabel lblNumeroTemporada;

	/** El Label de la Lista de Equipos. */
	private JLabel lblListaEquipos;

	/** La Lista de Equipos. */
	private JList<Equipo> lstEquipos;

	/** El Modelo de la Lista de Equipos de la Temporda. */
	private DefaultListModel<Equipo> elm;

	/** El Modelo de la Lista de Equipos Registrados. */
	private DefaultListModel<Equipo> elm2;

	/** El Boton de Añadir Equipo de la Lista de Equipos Registrados. */
	private JButton btnListaEquiposAñadir;

	/** El Boton de Editar Equipo de la Lista de Equipos Registrados. */
	private JButton btnListaEquiposEditar;

	/** El Boton de Eliminar Equipo de la Lista de Equipos Registrados. */
	private JButton btnListaEquiposEliminar;

	/** El TextField de Numero de la Temporada. */
	private JTextField textNumeroTemporada;

	/** El Label del Emoticono de la Flecha. */
	private JLabel lblFlecha;

	/** El Label de la Fecha de Inicio. */
	private JLabel lblFecha;

	/** El Panel de la Fecha de Inicio. */
	private JPanel panelFecha;

	/** El Boton de Guardar. */
	private JButton btnGuardar;

	/** La ComboBox del Dia de Inicio de la Temporada. */
	private JComboBox<Integer> comboBoxFechaDia;

	/** La ComboBox del Año de Inicio de la Temporada. */
	private JComboBox<Integer> comboBoxFechaAño;

	/** La ComboBox del Mes de Inicio de la Temporada. */
	private JComboBox<Integer> comboBoxFechaMes;

	/** El Label de la Primera Barra de la Fecha de Inicio. */
	private JLabel lblFechaBarra1;

	/** El Label de la Segunda Barra de la Fecha de Inicio. */
	private JLabel lblFechaBarra2;

	/** El Label del Dia de la Fecha de Inicio. */
	private JLabel lblFechaDia;

	/** El Label del Mes de la Fecha de Inicio. */
	private JLabel lblFechaMes;

	/** El Label del Año de la Fecha de Inicio. */
	private JLabel lblFechaAño;

	/** El Panel de la Lista de Equipos de la Temporada. */
	private JPanel panelListaEquipos;

	/** El Panel de la Lista de Equipos Registrados. */
	private JPanel panelListaEquiposRegistrados;

	/** El Scroll Pane de la Lista de Equipos de la Temporada. */
	private JScrollPane scrollPanelst;

	/** El Scroll Pane de la Lista de Equipos Registrados. */
	private JScrollPane scrollPanelst2;

	/** El Label de los Equipos Registrados. */
	private JLabel lblEquiposRegistrados;

	/** El Boton de Crear Equipo de la Lista de Equipos Registrados. */
	private JButton btnListaEquiposCrear;

	/** La Fecha Actual del Sistema. */
	private Calendar fechaActual;

	/** La Lista de Equipos Registrados. */
	private JList<Equipo> lstEquiposRegistrados;

	/** El Modelo de la ComboBox del Año de Inicio. */
	private DefaultComboBoxModel<Integer> modeloAño;

	/** La Lista de Temporadas Totales que hay Registradas. */
	private ArrayList<Temporada> ListaTemporadas;

	/** La Lista de Jornadas Generadas. */
	private ArrayList<Jornada> ListaJornadas;

	/** La Lista de Equipos de la Temporada. */
	private List<Equipo> ListaEquipos;

	/** La Lista de Equipos Totales que hay Registrados. */
	private ArrayList<Equipo> ListaEquiposRegistrados;

	/** La Lista de Movimientos Totales que hay Registrados. */
	private ArrayList<Logger> ListaMovimientos;

	/** La Clase Inicio. */
	Inicio Inicio;

	/** Si algo en la Lista de Equipos ha sido modificado */
	Boolean modificado = false;

	/** Los Dias del mes para las ComboBox de las Fechas. */
	private Integer[] dia = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25,
			26, 27, 28, 29, 30, 31 };

	/** Los Meses del año para las ComboBox de las Fechas. */
	private Integer[] mes = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };

	/** Los Años para las ComboBox de las Fechas. */
	private Integer[] año;

	/** El Dia de la Fecha de Inicio. */
	Integer FechaDia = 0;

	/** El Mes de la Fecha de Inicio. */
	Integer FechaMes = 0;

	/** El Año de la Fecha de Inicio. */
	Integer FechaAño = 0;

	/**
	 * Ejecuta la aplicacion.
	 *
	 * @param args los Argumentos
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AñadirTemporada frame = new AñadirTemporada();
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
	public AñadirTemporada() {
		// Imagen de la aplicación
		setIconImage(new ImageIcon(getClass().getResource("/Imagenes/CSL.png")).getImage());
		setResizable(false);
		setTitle("Añadir Temporada");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 550, 650);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(this);
		btnVolver.setForeground(new Color(255, 255, 255));
		btnVolver.setBackground(new Color(29, 29, 27));
		btnVolver.setFont(new Font("Dialog", Font.BOLD, 13));
		btnVolver.setFocusable(false);
		btnVolver.setBounds(335, 523, 85, 50);
		contentPane.add(btnVolver);

		lblNumeroTemporada = new JLabel("Numero de Temporada:");
		lblNumeroTemporada.setFont(new Font("Dialog", Font.BOLD, 20));
		lblNumeroTemporada.setBounds(42, 39, 234, 33);
		contentPane.add(lblNumeroTemporada);

		lblListaEquipos = new JLabel("Equipos:");
		lblListaEquipos.setFont(new Font("Dialog", Font.BOLD, 20));
		lblListaEquipos.setBounds(42, 240, 91, 33);
		contentPane.add(lblListaEquipos);

		elm = new DefaultListModel<Equipo>();

		lstEquipos = new JList<Equipo>();
		lstEquipos.setFont(new Font("Dialog", Font.BOLD, 12));
		lstEquipos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lstEquipos.setBorder(new LineBorder(new Color(0, 0, 0)));
		lstEquipos.setBounds(44, 0, 122, 75);
		// asocio el DefaultListModel a la lista
		lstEquipos.setModel(elm);
		contentPane.add(lstEquipos);

		panelListaEquipos = new JPanel();
		panelListaEquipos.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelListaEquipos.setBackground(new Color(255, 255, 255));
		panelListaEquipos.setBounds(42, 283, 122, 62);
		contentPane.add(panelListaEquipos);
		panelListaEquipos.setLayout(null);

		btnListaEquiposEliminar = new JButton("Eliminar");
		btnListaEquiposEliminar.setFocusable(false);
		btnListaEquiposEliminar.setFont(new Font("Dialog", Font.BOLD, 12));
		btnListaEquiposEliminar.addActionListener(this);
		btnListaEquiposEliminar.setBounds(18, 10, 86, 40);
		btnListaEquiposEliminar.setBackground(new Color(230, 230, 230));
		panelListaEquipos.add(btnListaEquiposEliminar);

		textNumeroTemporada = new JTextField();
		textNumeroTemporada.setBorder(new LineBorder(new Color(171, 173, 179)));
		textNumeroTemporada.setFont(new Font("Dialog", Font.PLAIN, 22));
		textNumeroTemporada.setBounds(42, 82, 437, 33);
		contentPane.add(textNumeroTemporada);
		textNumeroTemporada.setColumns(10);

		lblFecha = new JLabel("Fecha de Inicio:");
		lblFecha.setFont(new Font("Dialog", Font.BOLD, 20));
		lblFecha.setBounds(42, 125, 168, 33);
		contentPane.add(lblFecha);

		panelFecha = new JPanel();
		panelFecha.setLayout(null);
		panelFecha.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelFecha.setBackground(Color.WHITE);
		panelFecha.setBounds(42, 168, 200, 62);
		contentPane.add(panelFecha);

		comboBoxFechaDia = new JComboBox<Integer>(dia);
		comboBoxFechaDia.setFont(new Font("Dialog", Font.PLAIN, 12));
		comboBoxFechaDia.addActionListener(this);
		comboBoxFechaDia.setUI(new BasicComboBoxUI() {
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
		comboBoxFechaDia.setBounds(10, 21, 38, 19);
		panelFecha.add(comboBoxFechaDia);

		inicializarAño();
		modeloAño = new DefaultComboBoxModel<>(año);
		comboBoxFechaAño = new JComboBox<>(modeloAño);
		comboBoxFechaAño.setFont(new Font("Dialog", Font.PLAIN, 12));
		comboBoxFechaAño.addActionListener(this);
		comboBoxFechaAño.setUI(new BasicComboBoxUI() {
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
		comboBoxFechaAño.setBounds(131, 21, 59, 19);
		panelFecha.add(comboBoxFechaAño);

		comboBoxFechaMes = new JComboBox<Integer>(mes);
		comboBoxFechaMes.setFont(new Font("Dialog", Font.PLAIN, 12));
		comboBoxFechaMes.addActionListener(this);
		comboBoxFechaMes.setUI(new BasicComboBoxUI() {
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
		comboBoxFechaMes.setBounds(72, 21, 37, 19);
		panelFecha.add(comboBoxFechaMes);

		lblFechaBarra1 = new JLabel("/");
		lblFechaBarra1.setBounds(58, 21, 12, 19);
		panelFecha.add(lblFechaBarra1);
		lblFechaBarra1.setFont(new Font("Tahoma", Font.BOLD, 10));

		lblFechaBarra2 = new JLabel("/");
		lblFechaBarra2.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblFechaBarra2.setBounds(119, 21, 12, 19);
		panelFecha.add(lblFechaBarra2);

		lblFechaDia = new JLabel("Dia");
		lblFechaDia.setForeground(Color.GRAY);
		lblFechaDia.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblFechaDia.setBounds(11, 39, 21, 13);
		panelFecha.add(lblFechaDia);

		lblFechaMes = new JLabel("Mes");
		lblFechaMes.setForeground(Color.GRAY);
		lblFechaMes.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblFechaMes.setBounds(72, 39, 37, 13);
		panelFecha.add(lblFechaMes);

		lblFechaAño = new JLabel("Año");
		lblFechaAño.setForeground(Color.GRAY);
		lblFechaAño.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblFechaAño.setBounds(131, 39, 21, 13);
		panelFecha.add(lblFechaAño);

		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(this);
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.setFont(new Font("Dialog", Font.BOLD, 13));
		btnGuardar.setFocusable(false);
		btnGuardar.setBackground(new Color(29, 29, 27));
		btnGuardar.setBounds(128, 523, 85, 50);
		contentPane.add(btnGuardar);

		// Crea un JScrollPane y envuelve el lstJugadores con él
		scrollPanelst = new JScrollPane(lstEquipos);
		scrollPanelst.setBorder(null);
		scrollPanelst.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPanelst.setBounds(42, 345, 122, 125);
		// Añade el JScrollPane al contenido en lugar del JTextArea directamente
		contentPane.add(scrollPanelst);

		fechaActual = Calendar.getInstance();
		fechaActual.setTime(new Date());
		comboBoxFechaDia.setSelectedItem(fechaActual.get(Calendar.DAY_OF_MONTH));
		comboBoxFechaMes.setSelectedItem(fechaActual.get(Calendar.MONTH) + 1); // Sumar 1 porque los meses van de 0 a 11
		comboBoxFechaAño.setSelectedItem(fechaActual.get(Calendar.YEAR));

		panelListaEquiposRegistrados = new JPanel();
		panelListaEquiposRegistrados.setLayout(null);
		panelListaEquiposRegistrados.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelListaEquiposRegistrados.setBackground(Color.WHITE);
		panelListaEquiposRegistrados.setBounds(197, 283, 323, 62);
		contentPane.add(panelListaEquiposRegistrados);

		btnListaEquiposAñadir = new JButton("Añadir");
		btnListaEquiposAñadir.setFocusable(false);
		btnListaEquiposAñadir.setFont(new Font("Dialog", Font.BOLD, 12));
		btnListaEquiposAñadir.setBounds(16, 10, 86, 40);
		btnListaEquiposAñadir.setBackground(new Color(230, 230, 230));
		panelListaEquiposRegistrados.add(btnListaEquiposAñadir);

		btnListaEquiposCrear = new JButton("Crear");
		btnListaEquiposCrear.setFocusable(false);
		btnListaEquiposCrear.setFont(new Font("Dialog", Font.BOLD, 12));
		btnListaEquiposCrear.addActionListener(this);
		btnListaEquiposCrear.setBounds(220, 10, 86, 40);
		btnListaEquiposCrear.setBackground(new Color(230, 230, 230));
		panelListaEquiposRegistrados.add(btnListaEquiposCrear);

		btnListaEquiposEditar = new JButton("Editar");
		btnListaEquiposEditar.setFocusable(false);
		btnListaEquiposEditar.setFont(new Font("Dialog", Font.BOLD, 12));
		btnListaEquiposEditar.setBounds(118, 10, 86, 40);
		btnListaEquiposEditar.setBackground(new Color(230, 230, 230));
		panelListaEquiposRegistrados.add(btnListaEquiposEditar);
		btnListaEquiposEditar.addActionListener(this);

		elm2 = new DefaultListModel<Equipo>();

		lstEquiposRegistrados = new JList<Equipo>();
		lstEquiposRegistrados.setFont(new Font("Dialog", Font.BOLD, 12));
		lstEquiposRegistrados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lstEquiposRegistrados.setBorder(new LineBorder(new Color(0, 0, 0)));
		lstEquiposRegistrados.setBounds(197, 345, 323, 125);
		lstEquiposRegistrados.addListSelectionListener(this);

		// asocio el DefaultListModel a la lista
		lstEquiposRegistrados.setModel(elm2);
		contentPane.add(lstEquiposRegistrados);
		btnListaEquiposAñadir.addActionListener(this);

		// Crea un JScrollPane y envuelve el lstJugadores con él
		scrollPanelst2 = new JScrollPane(lstEquiposRegistrados);
		scrollPanelst2.setBorder(null);
		scrollPanelst2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPanelst2.setBounds(197, 345, 323, 125);
		// Añade el JScrollPane al contenido en lugar del JTextArea directamente
		contentPane.add(scrollPanelst2);

		lblEquiposRegistrados = new JLabel("Equipos Registrados:");
		lblEquiposRegistrados.setFont(new Font("Dialog", Font.BOLD, 20));
		lblEquiposRegistrados.setBounds(197, 240, 212, 33);
		contentPane.add(lblEquiposRegistrados);

		lblFlecha = new JLabel("🢀");
		lblFlecha.setFont(new Font("Dialog", Font.BOLD, 17));
		lblFlecha.setBounds(170, 380, 24, 36);
		contentPane.add(lblFlecha);

		ListaTemporadas = Temporada.cargarTemporadas();
		ListaEquipos = new ArrayList<>(); // Agrega esta línea para inicializar ListaEquipos
		ListaJornadas = new ArrayList<>(); // Agrega esta línea para inicializar ListaEquipos

		ListaEquiposRegistrados = Equipo.cargarEquipos();

		for (Equipo equipo : ListaEquiposRegistrados) {
			if (!equipo.getNombre().equalsIgnoreCase("Equipo para Descansar")) {
				elm2.addElement(equipo);
			}
		}

		EquipoSeleccion.setGuardado(false);

		ListaMovimientos = Logger.cargarMovimientos();
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
		} else if (e.getSource() == btnListaEquiposAñadir) {
			Añadir();
		} else if (e.getSource() == btnListaEquiposCrear) {
			Crear();
		} else if (e.getSource() == btnListaEquiposEditar) {
			Editar();
		} else if (e.getSource() == btnListaEquiposEliminar) {
			Eliminar();
		} else if (e.getSource() == comboBoxFechaDia) {
			FechaDia = (Integer) comboBoxFechaDia.getSelectedItem();
			Fecha();
		} else if (e.getSource() == comboBoxFechaMes) {
			FechaMes = (Integer) comboBoxFechaMes.getSelectedItem();
			Fecha();
		} else if (e.getSource() == comboBoxFechaAño) {
			FechaAño = (Integer) comboBoxFechaAño.getSelectedItem();
			Fecha();
		}
	}

	/**
	 * Funcion para seleccionar la Fecha.
	 */
	private void Fecha() {
		if (comboBoxFechaDia != null && comboBoxFechaMes != null && comboBoxFechaAño != null) {
			int selectedDay = (Integer) comboBoxFechaDia.getSelectedItem();
			int selectedMonth = (Integer) comboBoxFechaMes.getSelectedItem();
			int selectedYear = (Integer) comboBoxFechaAño.getSelectedItem();

			// Crear un objeto Calendar con la fecha seleccionada
			Calendar fechaSeleccionada = Calendar.getInstance();
			// Establecer la fecha seleccionada
			fechaSeleccionada.set(selectedYear, selectedMonth - 1, selectedDay); // Meses en Calendar van de 0 a 11

			// Verificar si el año seleccionado es bisiesto
			boolean esBisiesto = (selectedYear % 4 == 0 && (selectedYear % 100 != 0 || selectedYear % 400 == 0));

			// Crear un nuevo array con la longitud adecuada según el mes seleccionado y si
			// es bisiesto
			Integer[] newArray;
			if (selectedMonth == 2) {
				int daysInFebruary = esBisiesto ? 29 : 28;
				newArray = new Integer[daysInFebruary];
			} else if (selectedMonth == 4 || selectedMonth == 6 || selectedMonth == 9 || selectedMonth == 11) {
				newArray = new Integer[30];
			} else {
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
			comboBoxFechaDia.setModel(model);

			// Verificar si el día previamente seleccionado está dentro del rango de días
			// válidos
			if (selectedDay >= 1 && selectedDay <= dia.length) {
				// Mantener el día seleccionado
				comboBoxFechaDia.setSelectedItem(selectedDay);
			} else {
				// Seleccionar el primer día disponible
				comboBoxFechaDia.setSelectedItem(dia[0]);
			}
		}
	}

	/**
	 * Funcion para Volver.
	 */
	private void Volver() {
		if (modificado) {
			int opcion = JOptionPane.showConfirmDialog(this, "¿Estas seguro de que quieres salir sin guardar?",
					"Cierre sin guardar", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			switch (opcion) {
			case 1:
				return;
			case JOptionPane.CLOSED_OPTION:
				return;
			}
		}
		dispose();
	}

	/**
	 * Funcion para Guardar.
	 */
	private void Guardar() {
		// Obtener valores de los campos
		String NumeroTemporada = textNumeroTemporada.getText();

		// Cambiar el color de fondo a blanco para todos los campos
		resetearColoresCampos();

		// Validar campos
		if (esCampoInvalido(NumeroTemporada) || NumeroTemporada.length() > 10 || !esEntero(NumeroTemporada)
				|| Integer.parseInt(NumeroTemporada) <= 0) {
			resaltarCampo(textNumeroTemporada);
			mostrarError(
					"Por favor, introduzca un numero entero positivo mayor que 0 para la temporada, que contenga 10 digitos o menos");
			return;
		}

		if (elm.getSize() < 6) {
			resaltarCampo(lstEquipos);
			mostrarError("El número mínimo de equipos permitidos es de 6");
			return;
		}
		// Se hace una busqueda en la lista de las temporadas para ver si el numero esta
		// en uso
		for (Temporada temporada : ListaTemporadas) {
			if (("" + temporada.getNumero()).equals(NumeroTemporada)) {
				resaltarCampo(textNumeroTemporada);
				mostrarError("El numero de la temporada ya está en uso");
				return;
			}
		}
		// Creas los nuevos datos ingresados
		// FechaInicio
		Fecha fi = new Fecha(FechaDia, FechaMes, FechaAño);

		ListaEquipos.clear();

		for (int i = 0; i < elm.getSize(); i++) {
			Equipo equipo = (Equipo) elm.getElementAt(i);
			ListaEquipos.add(equipo);
		}

		// Creas una nueva Temporada con los datos ingresados
		Temporada nuevaTemporada = new Temporada(Integer.parseInt(NumeroTemporada), fi, "PROXIMAMENTE", ListaEquipos);

		ListaJornadas = Jornada.generarJornadas(ListaEquipos, fi, nuevaTemporada);

		// Asignar estadísticas a cada equipo en ListaEquipos
		for (Equipo equipo : ListaEquipos) {
			Estadisticas stats = new Estadisticas(nuevaTemporada);
			equipo.getEstadisticasPorTemporada().add(stats);
		}

		nuevaTemporada.setListaJornadas(ListaJornadas);

		JOptionPane.showMessageDialog(this, "Se ha creado tu Temporada con éxito", "Temporada Registrado",
				JOptionPane.INFORMATION_MESSAGE);

		Inicio.añadirTemporada(nuevaTemporada);

		Logger.nuevoMovimiento(ListaMovimientos, "Ha creado la Temporada " + nuevaTemporada.getNumero() + ".");

		File EscudosTemporada = new File("ficheros/Escudos/");
		File JugadoresTemporada = new File("ficheros/Jugadores/");

		// Crear una carpeta dentro de Escudos para los escudos de la temporada
		File escudosTemporadaFolder = new File(EscudosTemporada, "Temporada" + nuevaTemporada.getNumero());
		if (!escudosTemporadaFolder.exists()) {
			escudosTemporadaFolder.mkdirs();
		}

		// Crear una carpeta dentro de Jugadores para los jugadores de la temporada
		File jugadoresTemporadaFolder = new File(JugadoresTemporada, "Temporada" + nuevaTemporada.getNumero());
		if (!jugadoresTemporadaFolder.exists()) {
			jugadoresTemporadaFolder.mkdirs();
		}

		// Copiar escudos y fotos de jugadores
		copiarEscudos(nuevaTemporada, escudosTemporadaFolder);
		copiarFotosJugadores(nuevaTemporada, jugadoresTemporadaFolder);

		// Crear una nueva lista de equipos para la temporada actualizada
		List<Equipo> nuevaListaEquipos = crearNuevaListaEquipos(nuevaTemporada, escudosTemporadaFolder,
				jugadoresTemporadaFolder);

		// Establecer la nueva lista de equipos como la lista de equipos de la temporada
		// seleccionada
		nuevaTemporada.setListaEquipos(nuevaListaEquipos);
		
		try {
			// Crear la conexión a la base de datos
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/CSLeague", "root", "");
			conn.setAutoCommit(false); // Desactivar el modo de autocommit

			// Crear la consulta de inserción para el equipo
			String queryTemporada = "INSERT INTO Temporada (Numero, FechaInicio, Estado) VALUES (?, ?, ?)";
			PreparedStatement psTemporada = conn.prepareStatement(queryTemporada);
			psTemporada.setInt(1, nuevaTemporada.getNumero());
			psTemporada.setString(2, nuevaTemporada.getFechaInicio().getAño() + "-"
					+ nuevaTemporada.getFechaInicio().getMes() + "-" + nuevaTemporada.getFechaInicio().getDia());
			psTemporada.setString(3, "PROXIMAMENTE");

			psTemporada.executeUpdate();

			for (Equipo equipo : nuevaTemporada.getListaEquipos()) {
				if (equipo.getNombre().equals("Equipo para Descansar")) {
					continue;
				}

				// Crear la consulta de inserción para la relación entre equipo y entrenador
				String queryTemporadaParticipada = "INSERT INTO TemporadaParticipada (Temporada, Equipo, Escudo, Descripcion) VALUES (?, ?, ?, ?)";
				PreparedStatement psTemporadaParticipada = conn.prepareStatement(queryTemporadaParticipada);
				// Asignar los valores a los parámetros
				psTemporadaParticipada.setInt(1, nuevaTemporada.getNumero());
				psTemporadaParticipada.setString(2, equipo.getNombre());
				psTemporadaParticipada.setString(3, equipo.getEscudo());
				psTemporadaParticipada.setString(4, equipo.getDescripcion());
				psTemporadaParticipada.executeUpdate();

				// Crear la consulta de inserción para la relación entre equipo y entrenador
				String queryEquipoEntrenador = "INSERT INTO EntrenadorContratado (Temporada, Equipo, Entrenador, Nombre, Apellido, Nacionalidad) VALUES (?, ?, ?, ?, ?, ?)";
				PreparedStatement psEquipoEntrenador = conn.prepareStatement(queryEquipoEntrenador);
				// Asignar los valores a los parámetros
				psEquipoEntrenador.setInt(1, nuevaTemporada.getNumero());
				psEquipoEntrenador.setString(2, equipo.getNombre());
				psEquipoEntrenador.setString(3, equipo.getEntrenador().getDNI());
				psEquipoEntrenador.setString(4, equipo.getEntrenador().getNombre());
				psEquipoEntrenador.setString(5, equipo.getEntrenador().getApellido());
				psEquipoEntrenador.setString(6, equipo.getEntrenador().getNacionalidad());
				psEquipoEntrenador.executeUpdate();
				
				for (Estadisticas estadisticas : equipo.getEstadisticasPorTemporada()) {
				    // Crear la consulta de inserción para la relación entre equipo y estadisticas
				    String queryEstadisticas = "INSERT INTO Estadisticas (Temporada, Equipo, PuntosTotales, PartidasJugadas, PartidasGanadas, PartidasPerdidas, RondasDiferencia) VALUES (?, ?, ?, ?, ?, ?, ?)";
				    PreparedStatement psEstadisticas = conn.prepareStatement(queryEstadisticas);
				    
				    psEstadisticas.setInt(1, estadisticas.getTemporada().getNumero()); // Usar el número de la temporada desde el objeto Temporada asociado a las estadísticas
				    psEstadisticas.setString(2, equipo.getNombre()); // Obtener el nombre del equipo
				    psEstadisticas.setInt(3, estadisticas.getPuntosTotales());
				    psEstadisticas.setInt(4, estadisticas.getPartidosJugados());
				    psEstadisticas.setInt(5, estadisticas.getPartidosGanados());
				    psEstadisticas.setInt(6, estadisticas.getPartidosPerdidos());
				    psEstadisticas.setInt(7, estadisticas.getRondasDiferencia());
				    
				    psEstadisticas.executeUpdate(); // Ejecutar la consulta de inserción
				}

            // Conjunto para eliminar DNIs duplicados
            Set<String> dnisSelectedos = new HashSet<>();

            // Filtrar y eliminar DNIs duplicados de la lista de jugadores
            Iterator<Jugador> iter = equipo.getListaJugadores().iterator();
            while (iter.hasNext()) {
                Jugador jugador = iter.next();
                String dni = jugador.getDNI();
                if (dnisSelectedos.contains(dni)) {
                    // DNI duplicado encontrado, eliminar jugador de la lista
                    iter.remove();
                } else {
                    // Agregar DNI al conjunto de DNIs vistos
                    dnisSelectedos.add(dni);
                }
            }

				for (Jugador jugador : equipo.getListaJugadores()) {
					// Crear la consulta de inserción para los jugadores contratados
					String queryJugadorContratado = "INSERT INTO JugadorContratado (Temporada, Equipo, Jugador, Nombre, Apellido, Nacionalidad, Foto, Rol) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
					PreparedStatement psJugadorContratado = conn.prepareStatement(queryJugadorContratado);
					// Asignar los valores a los parámetros
					psJugadorContratado.setInt(1, nuevaTemporada.getNumero());
					psJugadorContratado.setString(2, equipo.getNombre());
					psJugadorContratado.setString(3, jugador.getDNI());
					psJugadorContratado.setString(4, jugador.getNombre());
					psJugadorContratado.setString(5, jugador.getApellido());
					psJugadorContratado.setString(6, jugador.getNacionalidad());
					psJugadorContratado.setString(7, jugador.getFoto());
					psJugadorContratado.setString(8, jugador.getPosicion());

					// Ejecutar el lote de inserciones para los jugadores contratados
					psJugadorContratado.executeUpdate();
				}
			}

			for (Jornada jornada : nuevaTemporada.getListaJornadas()) {
				// Crear la consulta de inserción para la relación entre equipo y entrenador
				String queryJornada = "INSERT INTO Jornada (Temporada, Numero, Fecha) VALUES (?, ?, ?)";
				PreparedStatement psJornada = conn.prepareStatement(queryJornada);
				// Asignar los valores a los parámetros
				psJornada.setInt(1, nuevaTemporada.getNumero());
				psJornada.setInt(2, jornada.getNumero());
				psJornada.setString(3,
						jornada.getFecha().getAño() + "-" + jornada.getFecha().getMes() + "-" + jornada.getFecha().getDia());
				psJornada.executeUpdate();

				for (Partido partido : jornada.getListaPartidos()) {
				    if (partido.getEquipoLocal().getNombre().equals("Equipo para Descansar") ||
				            partido.getEquipoVisitante().getNombre().equals("Equipo para Descansar")) {
				        continue;
				    }

				    // Conjunto para eliminar DNIs duplicados en equipos locales y visitantes
				    Set<String> dnisSelectedosPartido = new HashSet<>();

				    // Filtrar y eliminar DNIs duplicados de la lista de jugadores del equipo local
				    Iterator<Jugador> iterLocal = partido.getEquipoLocal().getListaJugadores().iterator();
				    while (iterLocal.hasNext()) {
				        Jugador jugadorLocal = iterLocal.next();
				        String dniLocal = jugadorLocal.getDNI();
				        if (dnisSelectedosPartido.contains(dniLocal)) {
				            // DNI duplicado encontrado, eliminar jugador de la lista del equipo local
				            iterLocal.remove();
				        } else {
				            // Agregar DNI al conjunto de DNIs vistos del equipo local
				            dnisSelectedosPartido.add(dniLocal);
				        }
				    }

				    // Filtrar y eliminar DNIs duplicados de la lista de jugadores del equipo visitante
				    Iterator<Jugador> iterVisitante = partido.getEquipoVisitante().getListaJugadores().iterator();
				    while (iterVisitante.hasNext()) {
				        Jugador jugadorVisitante = iterVisitante.next();
				        String dniVisitante = jugadorVisitante.getDNI();
				        if (dnisSelectedosPartido.contains(dniVisitante)) {
				            // DNI duplicado encontrado, eliminar jugador de la lista del equipo visitante
				            iterVisitante.remove();
				        } else {
				            // Agregar DNI al conjunto de DNIs vistos del equipo visitante
				            dnisSelectedosPartido.add(dniVisitante);
				        }
				    }

				    // Crear la consulta de inserción para el partido
				    String queryPartido = "INSERT INTO Partido (Temporada, Jornada, EquipoLocal, EquipoVisitante, PuntosLocal, PuntosVisitante, Jugado) VALUES (?, ?, ?, ?, ?, ?, ?)";
				    PreparedStatement psPartido = conn.prepareStatement(queryPartido);
				    psPartido.setInt(1, nuevaTemporada.getNumero());
				    psPartido.setInt(2, jornada.getNumero());
				    psPartido.setString(3, partido.getEquipoLocal().getNombre());
				    psPartido.setString(4, partido.getEquipoVisitante().getNombre());
				    psPartido.setInt(5, (partido.getPuntosLocal() == -1) ? 0 : partido.getPuntosLocal());
				    psPartido.setInt(6, (partido.getPuntosVisitante() == -1) ? 0 : partido.getPuntosVisitante());
				    psPartido.setBoolean(7, partido.getJugado());
				    psPartido.executeUpdate();

				    // Insertar datos de los jugadores del equipo local en el partido
				    for (Jugador jugadorLocal : partido.getEquipoLocal().getListaJugadores()) {
				        // Insertar datos para el equipo local
				        insertarPartidoJugado(conn, jugadorLocal, "Local", partido, nuevaTemporada, jornada.getNumero());
				    }

				    // Insertar datos de los jugadores del equipo visitante en el partido
				    for (Jugador jugadorVisitante : partido.getEquipoVisitante().getListaJugadores()) {
				        // Insertar datos para el equipo visitante
				        insertarPartidoJugado(conn, jugadorVisitante, "Visitante", partido, nuevaTemporada, jornada.getNumero());
				    }
				}

			}

			// Confirmar la transacción
			conn.commit();

			// Cerrar la conexión
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error al guardar el equipo en la base de datos.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		// Establecer la posición de la temporada seleccionada en 0
		Seleccion.setTemporadaPosicion(0);
		Seleccion.setTemporadaSeleccionada(nuevaTemporada);

		// Cerrar la ventana actual
		dispose();

		return;
	}
	
	private void insertarPartidoJugado(Connection conn, Jugador jugador, String tipo, Partido partido, Temporada temporada, Integer jornada) throws SQLException {
	    // Crear la consulta de inserción para el registro del partido jugado
	    String queryPartidoJugado = "INSERT INTO PartidoJugado (Jugador, EquipoJugador, EquipoRival, Jornada, Temporada) VALUES (?, ?, ?, ?, ?)";
	    PreparedStatement psPartidoJugado = conn.prepareStatement(queryPartidoJugado);
	    // Asignar los valores a los parámetros
	    psPartidoJugado.setString(1, jugador.getDNI());
	    if (tipo.equals("Local")) {
		    psPartidoJugado.setString(2, partido.getEquipoLocal().getNombre()); // Nombre del equipo del jugador
		    psPartidoJugado.setString(3, partido.getEquipoVisitante().getNombre()); // Nombre del equipo rival
	    } else {
		    psPartidoJugado.setString(2, partido.getEquipoVisitante().getNombre()); // Nombre del equipo del jugador
		    psPartidoJugado.setString(3, partido.getEquipoLocal().getNombre()); // Nombre del equipo rival
	    }
	    psPartidoJugado.setInt(4, jornada); // Número de la jornada
	    psPartidoJugado.setInt(5, temporada.getNumero()); // Número de la temporada

	    // Ejecutar la consulta de inserción
	    psPartidoJugado.executeUpdate();
	    // Cerrar el PreparedStatement
	    psPartidoJugado.close();
	}

	private void copiarEscudos(Temporada temporada, File destinoFolder) {
		for (Equipo equipo : temporada.getListaEquipos()) {
			if (equipo.getNombre().equals("Equipo para Descansar")) {
				continue;
			}
			String extension = obtenerExtension(equipo.getEscudo());
			String nombreEscudo = equipo.getNombre() + extension;
			File destinoArchivo = new File(destinoFolder, nombreEscudo);

			if (!destinoArchivo.exists()) {
				File escudoAnterior = new File("ficheros/Escudos/", nombreEscudo);
				if (escudoAnterior.exists()) {
					try {
						Files.copy(escudoAnterior.toPath(), destinoArchivo.toPath(), StandardCopyOption.REPLACE_EXISTING);
						equipo.setEscudo("ficheros/Escudos/Temporada" + temporada.getNumero() + "/" + equipo.getNombre()
								+ obtenerExtension(equipo.getEscudo()));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	private void copiarFotosJugadores(Temporada temporada, File destinoFolder) {
		for (Equipo equipo : temporada.getListaEquipos()) {
			if (equipo.getNombre().equals("Equipo para Descansar")) {
				continue;
			}
			for (Jugador jugador : equipo.getListaJugadores()) {
				String extensionFoto = obtenerExtension(jugador.getFoto());
				String nombreFoto = equipo.getNombre() + "-" + jugador.getDNI() + extensionFoto;
				File destinoArchivoFoto = new File(destinoFolder, nombreFoto);

				if (!destinoArchivoFoto.exists()) {
					File fotoAnterior = new File("ficheros/Jugadores/", nombreFoto);
					if (fotoAnterior.exists()) {
						try {
							Files.copy(fotoAnterior.toPath(), destinoArchivoFoto.toPath(),
									StandardCopyOption.REPLACE_EXISTING);
							jugador.setFoto("ficheros/Jugadores/Temporada" + temporada.getNumero() + "/" + equipo.getNombre()
									+ "-" + jugador.getDNI() + obtenerExtension(jugador.getFoto()));
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}

	private List<Equipo> crearNuevaListaEquipos(Temporada temporada, File escudosFolder, File jugadoresFolder) {
		List<Equipo> nuevaListaEquipos = new ArrayList<>();

		for (Equipo equipo : temporada.getListaEquipos()) {
			String extension = obtenerExtension(equipo.getEscudo());
			String nombreEscudo = equipo.getNombre() + extension;
			String nuevaRutaEscudo = "ficheros/Escudos/Temporada" + temporada.getNumero() + "/" + nombreEscudo;

			List<Jugador> nuevosJugadores = new ArrayList<>();
			for (Jugador jugador : equipo.getListaJugadores()) {
				String extensionFoto = obtenerExtension(jugador.getFoto());
				String nombreFoto = equipo.getNombre() + "-" + jugador.getDNI() + extensionFoto;
				String nuevaRutaFoto = "ficheros/Jugadores/Temporada" + temporada.getNumero() + "/" + nombreFoto;
				Jugador nuevoJugador = new Jugador(new Participante(jugador.getDNI(), jugador.getNombre(),
						jugador.getApellido(), jugador.getNacionalidad()), nuevaRutaFoto, jugador.getPosicion(),
						jugador.getFechaNacimiento());
				nuevosJugadores.add(nuevoJugador);
			}

			Equipo nuevoEquipo = new Equipo(equipo.getNombre(), nuevaRutaEscudo, equipo.getDescripcion(),
					equipo.getFechaCreacion(), equipo.getEntrenador(), nuevosJugadores,
					equipo.getEstadisticasPorTemporada());

			nuevaListaEquipos.add(nuevoEquipo);
		}

		return nuevaListaEquipos;
	}

	/**
	 * Método para obtener la extensión del archivo de imagen del escudo
	 *
	 * @param fileName el Escudo
	 */
	private String obtenerExtension(String fileName) {
		int dotIndex = fileName.lastIndexOf('.');
		if (dotIndex >= 0 && dotIndex < fileName.length() - 1) {
			return fileName.substring(dotIndex);
		}
		return ""; // Si no se encuentra extensión o no está presente en el nombre del archivo
	}

	/**
	 * Funcion para Añadir un Equipo a la Lista de Equipos de la Temporada.
	 */
	private void Añadir() {
		// Obtengo las posiciones de los elementos seleccionados
		int[] indicesSeleccionados = lstEquiposRegistrados.getSelectedIndices();

		// Compruebo si hay algún elemento seleccionado
		if (indicesSeleccionados.length != 0) {
			// Verificar duplicados en los equipos seleccionados
			for (int indice : indicesSeleccionados) {
				Equipo equipoEnLista = elm2.getElementAt(indice);

				// Verificar si el equipo ya está en la lista visual elm
				if (yaEstaEnLaLista(elm, equipoEnLista)) {
					mostrarError("El equipo '" + equipoEnLista.getNombre() + "' ya está en la lista.");
					return;
				}

				// Verificar duplicados entre los participantes de los equipos en elm y elm2
				Equipo equipoDuplicado = equipoConParticipantesDuplicados(elm, equipoEnLista);
				if (equipoDuplicado != null) {
					mostrarError("El equipo '" + equipoEnLista.getNombre() + "' contiene DNI's duplicados con el equipo '"
							+ equipoDuplicado.getNombre() + "'.");
					return;
				}

				elm.addElement(equipoEnLista);
				ListaEquipos.add(equipoEnLista);
			}

			modificado = true;
		} else {
			// No hay ningún elemento seleccionado
			mostrarError("Error, ningún elemento seleccionado en la lista");
		}
	}

	/**
	 * Funcion para verificar que no se añade un Equipo con Participantes
	 * Duplicados.
	 *
	 * @param model  el Modelo de la Lista de tipo Equipo
	 * @param equipo el Equipo
	 * @return el Equipo, si tiene Participantes Duplicados
	 */
	private Equipo equipoConParticipantesDuplicados(DefaultListModel<Equipo> model, Equipo equipo) {
		List<Participante> participantesEquipo = new ArrayList<>(equipo.getListaJugadores());
		participantesEquipo.add(equipo.getEntrenador());

		// Recorrer la lista visual y verificar duplicados con el equipo actual
		for (int i = 0; i < model.getSize(); i++) {
			Equipo otroEquipo = model.getElementAt(i);

			if (otroEquipo != equipo) { // Evitar comparar el equipo consigo mismo
				for (Participante participante : otroEquipo.getListaJugadores()) {
					String dni = participante.getDNI();
					if (participantesEquipo.stream().anyMatch(p -> p.getDNI().equals(dni))) {
						return otroEquipo; // Devolver el equipo con participantes duplicados
					}
				}

				// Verificar duplicados con el entrenador del otro equipo
				String dniEntrenadorOtroEquipo = otroEquipo.getEntrenador().getDNI();
				if (participantesEquipo.stream().anyMatch(p -> p.getDNI().equals(dniEntrenadorOtroEquipo))) {
					return otroEquipo; // Devolver el equipo con participantes duplicados con el entrenador
				}
			}
		}

		return null; // No se encontraron DNIs duplicados entre equipos
	}

	/**
	 * Funcion para comprobar si un Equipo Ya esta en la Lista.
	 *
	 * @param model  el Modelo de la Lista de tipo Equipo
	 * @param equipo el Equipo
	 * @return true, si ya esta en la lista
	 */
	private boolean yaEstaEnLaLista(DefaultListModel<Equipo> model, Equipo equipo) {
		for (int i = 0; i < model.getSize(); i++) {
			Equipo otroEquipo = model.getElementAt(i);
			if (otroEquipo.equals(equipo)) {
				return true; // El equipo ya está en la lista
			}
		}
		return false; // El equipo no está en la lista
	}

	/**
	 * Funcion para Crear un nuevo Equipo.
	 */
	private void Crear() {
		// Creo las variables
		AñadirEquipo AE = new AñadirEquipo();
		AE.setVentanaTemporada(this);

		AE.setAbiertoTemporada();
		setEnabled(false);

		AE.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				if (AE != null) {
					AE.dispose();
				}
				// Habilitar la ventana de inicio
				setEnabled(true);

				// Establece que la ventana se quede en primer plano
				requestFocus();

				ListaMovimientos = Logger.cargarMovimientos();
			}
		});
		// Muestro la ventana Registro
		AE.setVisible(true);
		// Centrar la ventana en el centro de la pantalla
		AE.setLocationRelativeTo(null);

		modificado = true;
	}

	/**
	 * Funcion para Editar un Equipo.
	 */
	private void Editar() {

		// Obtengo las posiciones de los elementos seleccionados
		int indiceSeleccionado = lstEquiposRegistrados.getSelectedIndex();

		// Compruebo si hay algún elemento seleccionado
		if (indiceSeleccionado != -1) {
			// Creo las variables
			EquipoSeleccion.setEquipoSeleccionado(elm2.elementAt(indiceSeleccionado));

			if (elm.contains(EquipoSeleccion.getEquipoSeleccionado())) {
				JOptionPane.showMessageDialog(this,
						"Si editas un equipo añadido en la temporada tendras que volver a añadirlo", "Aviso Edicion",
						JOptionPane.INFORMATION_MESSAGE);
			}

			ListaEquiposRegistrados.remove(EquipoSeleccion.getEquipoSeleccionado());

			EditarEquipo EE = new EditarEquipo();

			setEnabled(false);

			EE.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosed(WindowEvent e) {
					if (EE != null) {
						EE.dispose();
					}
					// Habilitar la ventana de inicio
					setEnabled(true);

					// Establece que la ventana se quede en primer plano
					requestFocus();

					elm2.add(EquipoSeleccion.getEquipoPosicion(), EquipoSeleccion.getEquipoSeleccionado());
					if (elm.contains(EquipoSeleccion.getEquipoSeleccionado()) && EquipoSeleccion.getGuardado()) {
						elm.removeElement(EquipoSeleccion.getEquipoSeleccionado());
						EquipoSeleccion.setGuardado(false);
					}
					lstEquiposRegistrados.clearSelection();
					lstEquipos.clearSelection();

					ListaMovimientos = Logger.cargarMovimientos();
				}
			});
			// Muestro la ventana Registro
			EE.setVisible(true);
			// Centrar la ventana en el centro de la pantalla
			EE.setLocationRelativeTo(null);

			modificado = true;

		} else {
			// No hay ningún elemento seleccionado
			mostrarError("Error, ningún elemento seleccionado en la lista");
		}
	}

	/**
	 * Funcion para Eliminar un equipo de la Lista de Equipos de la Temporada.
	 */
	private void Eliminar() {
		// Obtengo las posiciones de los elementos seleccionados
		int indiceSeleccionado = lstEquipos.getSelectedIndex();

		// Compruebo si hay algún elemento seleccionado
		if (indiceSeleccionado != -1) {
			// Elimino elemento
			elm.remove(indiceSeleccionado);
			ListaEquipos.remove(indiceSeleccionado);

			if (elm.getSize() == 0) {
				modificado = false;
			}
		} else {
			// No hay ningún elemento seleccionado
			mostrarError("Error, ningún elemento seleccionado en la lista");
		}
	}

	/**
	 * Funcion para verificar si un String Es Entero.
	 *
	 * @param texto el Texto que se busca verificar
	 * @return true, si es Entero
	 */
	private boolean esEntero(String texto) {
		try {
			Integer.parseInt(texto);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * Funcion para Resetear los colores de los campos por defecto.
	 */
	private void resetearColoresCampos() {
		textNumeroTemporada.setBackground(Color.WHITE);
		comboBoxFechaDia.setBackground(Color.WHITE);
		comboBoxFechaMes.setBackground(Color.WHITE);
		comboBoxFechaAño.setBackground(Color.WHITE);
		lstEquipos.setBackground(Color.WHITE);
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
		} else if (componente instanceof JList) {
			((JList<?>) componente).setBackground(new Color(255, 192, 183));
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
	 * Funcion par Inicializar el año de las Fechas.
	 */
	private void inicializarAño() {
		año = new Integer[201]; // 2124 - 1924 + 1

		for (int i = 2124; i >= 1924; i--) {
			año[2124 - i] = i;
		}
	}

	/**
	 * Funcion para Añadir un Equipo a la Lista de Equipos Registrados.
	 *
	 * @param nuevoEquipo el Nuevo Equipo
	 */
	public void añadirEquipo(Equipo nuevoEquipo) {
		// TODO Auto-generated method stub
		elm2.addElement(nuevoEquipo);
		ListaEquipos.add(nuevoEquipo);
	}

	/**
	 * Funcion para identificar a traves de que Ventana Inicio se ha abierto esta
	 * Clase.
	 *
	 * @param inicio la Ventan Inicio que se busca identificar
	 */
	public void setVentanaInicio(Inicio inicio) {
		this.Inicio = inicio;
	}

	/**
	 * Funcion que Guarda los cambios de Seleccion de las Listas de Equipos.
	 *
	 * @param e el Evento de Seleccion de la Lista
	 */
	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		int selectedIndex = lstEquiposRegistrados.getSelectedIndex();

		if (selectedIndex != -1) {

			EquipoSeleccion.setEquipoPosicion(selectedIndex);
		}
	}
}
