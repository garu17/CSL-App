package aplicacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import definicion.Jornada;
import definicion.Jugador;
import definicion.Logger;
import definicion.Participante;
import definicion.Partido;
import definicion.Seleccion;
import definicion.Sesion;
import definicion.Temporada;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JList;
import definicion.Equipo;
import definicion.EquipoSeleccion;
import definicion.Estadisticas;

import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;

/**
 * La Clase EditarTemp.
 */
public class EditarTemp extends JFrame implements ActionListener, WindowListener, ListSelectionListener {

	/** La Constante serialVersionUID generada para identificarse. */
	private static final long serialVersionUID = 1L; 

	/** El Content Pane principal. */
	private JPanel contentPane;

	/** El Content Pane Superior. */
	private JPanel panelSuperior;

	/** El Boton de Cerrar Sesion. */
	private JButton btnCerrarSesion;

	/** El Content Pane Central. */
	private JPanel panelCentral;

	/** El Boton de Atras. */
	private JButton btnAtras;

	/** El Label de Temporada. */
	private JLabel lblTemporada;

	/** El Boton de Guardar. */
	private JButton btnGuardar;

	/** El Tabbed Pane de las Jornadas. */
	private JTabbedPane tabbedPaneJornadas;

	/** La Lista de Temporadas. */
	private ArrayList<Temporada> ListaTemporadas;

	/** La Lista de Jornadas de la Temporada. */
	private List<Jornada> ListaJornadas;

	/** El Boton de Finalizar. */
	private JButton btnFinalizar;

	/** El Modelo de la Lista de Equipos de la Temporda. */
	private DefaultListModel<Equipo> elm;

	/** El Modelo de la Lista de Equipos Registrados. */
	private DefaultListModel<Equipo> elm2;

	/** La Lista de Equipos Registrados. */
	private JList<Equipo> lstEquiposRegistrados;

	/** La Lista de Equipos de la Temporada. */
	private JList<Equipo> lstEquipos;

	/** El Scroll Pane de la Lista de Equipos de la Temporada. */
	private JScrollPane scrollPanelst;

	/** El Scroll Pane de la Lista de Equipos Registrados. */
	private JScrollPane scrollPanelst2;

	/** El Panel de la Lista de Equipos de la Temporada. */
	private JPanel panelListaEquipos;

	/** El Boton de Eliminar Equipo de la Lista de Equipos Registrados. */
	private JButton btnListaEquiposEliminar;

	/** El Boton de Editar Equipo de la Lista de Equipos Registrados. */
	private JButton btnListaEquiposEditar;

	/** El Panel de la Lista de Equipos Registrados. */
	private JPanel panelListaEquiposRegistrados;

	/** El Boton de Añadir Equipo de la Lista de Equipos Registrados. */
	private JButton btnListaEquiposAñadir;

	/** El Boton de Crear Equipo de la Lista de Equipos Registrados. */
	private JButton btnListaEquiposCrear;

	/** El Label de los Equipos de la Temporada. */
	private JLabel lblListaEquipos;

	/** El Label de los Equipos Registrados. */
	private JLabel lblEquiposRegistrados;

	/** La Lista de Equipos de la Temporada. */
	private List<Equipo> ListaEquipos;

	/** La Lista de Equipos Totales que hay Registrados. */
	private ArrayList<Equipo> ListaEquiposRegistrados;

	/** El Modelo de la Tabla de Clasificacion. */
	private DefaultTableModel ctm;

	/** La Tabla de Clasificacion. */
	private JTable tableClasificacion;

	/** Si algo en la Lista de Equipos ha sido modificado */
	Boolean modificadoEquipos = false;

	/**
	 * Si se ha encontrado el Equipo Registrado en la Lista de Equipos de la
	 * Temporada.
	 */
	Boolean encontrado = false;

	/** Si algun equipo se ha eliminado o añadido a la temporada */
	Boolean equiposCambiados = false;

	/** El Boton de que Inicia la Temporada. */
	private JButton btnIniciar;

	/** El Label del Emoticono de la Flecha. */
	private JLabel lblFlecha;

	/**
	 * Ejecuta la aplicacion.
	 *
	 * @param args los Argumentos
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditarTemp frame = new EditarTemp();
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
	public EditarTemp() {
		// Añado acción listener
		this.addWindowListener(this);
		// Imagen de la aplicación
		setIconImage(new ImageIcon(getClass().getResource("/Imagenes/CSL.png")).getImage());
		setResizable(false);
		setTitle("Editar Temporada ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1250, 550);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		panelSuperior = new JPanel();
		panelSuperior.setBackground(new Color(29, 29, 27));
		panelSuperior.setBounds(0, 0, 1236, 90);
		contentPane.add(panelSuperior);
		panelSuperior.setLayout(null);

		btnCerrarSesion = new JButton("Cerrar Sesión");
		btnCerrarSesion.setFont(new Font("Dialog", Font.BOLD, 12));
		btnCerrarSesion.addActionListener(this);
		btnCerrarSesion.setBackground(new Color(230, 230, 230));
		btnCerrarSesion.setFocusable(false);
		btnCerrarSesion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCerrarSesion.setBorder(null);
		btnCerrarSesion.setBounds(22, 22, 119, 45);
		panelSuperior.add(btnCerrarSesion);

		btnAtras = new JButton("Atras");
		btnAtras.setFont(new Font("Dialog", Font.BOLD, 12));
		btnAtras.addActionListener(this);
		btnAtras.setBackground(new Color(230, 230, 230));
		btnAtras.setBorder(null);
		btnAtras.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAtras.setFocusable(false);
		btnAtras.setBounds(1141, 22, 85, 45);
		panelSuperior.add(btnAtras);

		lblTemporada = new JLabel("Temporada X");
		lblTemporada.setForeground(new Color(255, 255, 255));
		lblTemporada.setHorizontalAlignment(SwingConstants.CENTER);
		lblTemporada.setFont(new Font("Dialog", Font.BOLD, 25));
		lblTemporada.setBounds(403, 22, 429, 45);
		panelSuperior.add(lblTemporada);

		panelCentral = new JPanel();
		panelCentral.setFocusable(false);
		panelCentral.setBounds(0, 91, 1236, 432);
		panelCentral.setBackground(new Color(220, 220, 220));
		contentPane.add(panelCentral);
		panelCentral.setLayout(null);

		btnGuardar = new JButton("Guardar");
		btnGuardar.setForeground(new Color(255, 255, 255));
		btnGuardar.setFont(new Font("Dialog", Font.BOLD, 13));
		btnGuardar.addActionListener(this);
		btnGuardar.setFocusable(false);
		btnGuardar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGuardar.setBorder(null);
		btnGuardar.setBackground(new Color(29, 29, 27));
		btnGuardar.setBounds(457, 355, 119, 45);
		panelCentral.add(btnGuardar);

		tabbedPaneJornadas = new JTabbedPane(JTabbedPane.TOP);
		tabbedPaneJornadas.setFocusable(false);
		tabbedPaneJornadas.setBorder(null);
		tabbedPaneJornadas.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPaneJornadas.setFont(new Font("Dialog", Font.PLAIN, 10));
		tabbedPaneJornadas.setBackground(Color.WHITE);
		tabbedPaneJornadas.setBounds(374, 55, 469, 290);
		// asocio el DefaultListModel a la lista
		panelCentral.add(tabbedPaneJornadas);

		btnFinalizar = new JButton("Finalizar");
		btnFinalizar.setForeground(new Color(255, 255, 255));
		btnFinalizar.setFont(new Font("Dialog", Font.BOLD, 13));
		btnFinalizar.setFocusable(false);
		btnFinalizar.addActionListener(this);
		btnFinalizar.setBorder(null);
		btnFinalizar.setBackground(new Color(29, 29, 27));
		btnFinalizar.setBounds(328, 355, 119, 45);
		panelCentral.add(btnFinalizar);
		// Ocultar el boton
		btnFinalizar.setVisible(false);

		elm2 = new DefaultListModel<Equipo>();

		lstEquiposRegistrados = new JList<Equipo>();
		lstEquiposRegistrados.setFont(new Font("Dialog", Font.BOLD, 12));
		lstEquiposRegistrados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lstEquiposRegistrados.setBorder(new LineBorder(new Color(0, 0, 0)));
		lstEquiposRegistrados.setBounds(10, 154, 155, 191);
		lstEquiposRegistrados.addListSelectionListener(this);
		// asocio el DefaultListModel a la lista
		lstEquiposRegistrados.setModel(elm2);
		panelCentral.add(lstEquiposRegistrados);

		// Crea un JScrollPane y envuelve el lstJugadores con él
		scrollPanelst = new JScrollPane(lstEquiposRegistrados);
		scrollPanelst.setBorder(null);
		scrollPanelst.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPanelst.setBounds(10, 154, 155, 191);
		// Añade el JScrollPane al contenido en lugar del JTextArea directamente
		panelCentral.add(scrollPanelst);

		elm = new DefaultListModel<Equipo>();

		lstEquipos = new JList<Equipo>();
		lstEquipos.setFont(new Font("Dialog", Font.BOLD, 12));
		lstEquipos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lstEquipos.setBorder(new LineBorder(new Color(0, 0, 0)));
		lstEquipos.setBounds(209, 154, 155, 191);
		// asocio el DefaultListModel a la lista
		lstEquipos.setModel(elm);
		panelCentral.add(lstEquipos);

		// Crea un JScrollPane y envuelve el lstJugadores con él
		scrollPanelst2 = new JScrollPane(lstEquipos);
		scrollPanelst2.setBorder(null);
		scrollPanelst2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPanelst2.setBounds(209, 154, 155, 191);
		// Añade el JScrollPane al contenido en lugar del JTextArea directamente
		panelCentral.add(scrollPanelst2);

		panelListaEquipos = new JPanel();
		panelListaEquipos.setLayout(null);
		panelListaEquipos.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelListaEquipos.setBackground(Color.WHITE);
		panelListaEquipos.setBounds(209, 53, 155, 101);
		panelCentral.add(panelListaEquipos);

		btnListaEquiposEliminar = new JButton("Eliminar");
		btnListaEquiposEliminar.setFocusable(false);
		btnListaEquiposEliminar.setFont(new Font("Dialog", Font.BOLD, 10));
		btnListaEquiposEliminar.setBackground(new Color(230, 230, 230));
		btnListaEquiposEliminar.addActionListener(this);
		btnListaEquiposEliminar.setBounds(34, 20, 86, 60);
		panelListaEquipos.add(btnListaEquiposEliminar);

		panelListaEquiposRegistrados = new JPanel();
		panelListaEquiposRegistrados.setLayout(null);
		panelListaEquiposRegistrados.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelListaEquiposRegistrados.setBackground(Color.WHITE);
		panelListaEquiposRegistrados.setBounds(10, 53, 155, 101);
		panelCentral.add(panelListaEquiposRegistrados);

		btnListaEquiposAñadir = new JButton("Añadir");
		btnListaEquiposAñadir.setFocusable(false);
		btnListaEquiposAñadir.setBackground(new Color(230, 230, 230));
		btnListaEquiposAñadir.setFont(new Font("Dialog", Font.BOLD, 10));
		btnListaEquiposAñadir.addActionListener(this);
		btnListaEquiposAñadir.setBounds(35, 41, 86, 21);
		panelListaEquiposRegistrados.add(btnListaEquiposAñadir);

		btnListaEquiposCrear = new JButton("Crear");
		btnListaEquiposCrear.setFont(new Font("Dialog", Font.BOLD, 10));
		btnListaEquiposCrear.setFocusable(false);
		btnListaEquiposCrear.setBackground(new Color(230, 230, 230));
		btnListaEquiposCrear.addActionListener(this);
		btnListaEquiposCrear.setBounds(35, 10, 86, 21);
		panelListaEquiposRegistrados.add(btnListaEquiposCrear);

		btnListaEquiposEditar = new JButton("Editar");
		btnListaEquiposEditar.setFocusable(false);
		btnListaEquiposEditar.setFont(new Font("Dialog", Font.BOLD, 10));
		btnListaEquiposEditar.setBackground(new Color(230, 230, 230));
		btnListaEquiposEditar.setBounds(35, 70, 86, 21);
		panelListaEquiposRegistrados.add(btnListaEquiposEditar);
		btnListaEquiposEditar.addActionListener(this);

		lblListaEquipos = new JLabel("Equipos de la Temporada:");
		lblListaEquipos.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblListaEquipos.setBounds(209, 10, 175, 33);
		panelCentral.add(lblListaEquipos);

		lblEquiposRegistrados = new JLabel("Equipos Registrados:");
		lblEquiposRegistrados.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEquiposRegistrados.setBounds(10, 10, 147, 33);
		panelCentral.add(lblEquiposRegistrados);

		ctm = new DefaultTableModel();
		ctm.addColumn("Posición");
		ctm.addColumn("Equipo");
		ctm.addColumn("Puntos Totales");
		ctm.addColumn("Partidas Jugadas");
		ctm.addColumn("Partidas Ganadas");
		ctm.addColumn("Partidas Perdidas");
		ctm.addColumn("Rondas Diferencia");

		tableClasificacion = new JTable();
		tableClasificacion.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		tableClasificacion.setEnabled(false);
		tableClasificacion.setBounds(853, 55, 373, 286);
		// Asignar el modelo de tabla a la JTable
		tableClasificacion.setModel(ctm);
		panelCentral.add(tableClasificacion);

		btnIniciar = new JButton("Iniciar");
		// Añade accion de tipo listener al boton
		btnIniciar.addActionListener(this);
		btnIniciar.setForeground(Color.WHITE);
		btnIniciar.setFont(new Font("Dialog", Font.BOLD, 13));
		btnIniciar.setFocusable(false);
		btnIniciar.setBorder(null);
		btnIniciar.setBackground(new Color(29, 29, 27));
		btnIniciar.setBounds(763, 355, 119, 45);
		panelCentral.add(btnIniciar);

		lblFlecha = new JLabel("🢂");
		lblFlecha.setFont(new Font("Dialog", Font.BOLD, 17));
		lblFlecha.setBounds(179, 200, 24, 36);
		panelCentral.add(lblFlecha);

		lblTemporada.setText("Editar Temporada " + Seleccion.getTemporadaNumero());

		ListaJornadas = Seleccion.getTemporadaSeleccionada().getListaJornadas();

		ListaTemporadas = Temporada.cargarTemporadas();

		ListaEquipos = Equipo.cargarEquipos(Seleccion.getTemporadaNumero());
		ListaEquiposRegistrados = Equipo.cargarEquipos(0);

		Boolean hayActiva = false;

		// Si hay una temporada ya activa
		for (Temporada temporada : ListaTemporadas) {
			if ("ACTIVA".equals(temporada.getEstado())) {
				hayActiva = true;
				break;
			}
		}

		if (!hayActiva) {
			btnIniciar.setBounds(484, 355, 119, 45);
			btnGuardar.setBounds(640, 355, 119, 45);
		} else {
			btnIniciar.setVisible(false);
			btnGuardar.setBounds(558, 355, 119, 45);
		}

		if (Seleccion.getTemporadaSeleccionada().getEstado().equals("ACTIVA")) {
			lstEquiposRegistrados.setVisible(false);
			lstEquipos.setVisible(false);
			panelListaEquipos.setVisible(false);
			panelListaEquiposRegistrados.setVisible(false);
			lblEquiposRegistrados.setVisible(false);
			lblListaEquipos.setVisible(false);
			scrollPanelst2.setVisible(false);
			scrollPanelst.setVisible(false);
			lblFlecha.setVisible(false);
			tabbedPaneJornadas.setBounds(39, 55, 570, 290);
			tableClasificacion.setBounds(633, 55, 573, 286);
			btnFinalizar.setVisible(true);
			btnFinalizar.setBounds(484, 355, 119, 45);
			btnGuardar.setBounds(640, 355, 119, 45);
			btnIniciar.setVisible(false);
		}

		for (Equipo equipo : ListaEquiposRegistrados) {
			if (!equipo.getNombre().equalsIgnoreCase("Equipo para Descansar")) {
				elm2.addElement(equipo);
			}
		}
		for (Equipo equipo : ListaEquipos) {
			if (!equipo.getNombre().equalsIgnoreCase("Equipo para Descansar")) {
				elm.addElement(equipo);
			}
		}

		// Llama al método para crear los paneles y pestañas de las jornadas
		Jornada.crearPanelesJornadas(ListaJornadas, tabbedPaneJornadas, true);
		llenarTabla();

		// Crear una instancia de DefaultTableCellRenderer
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		// Establecer la alineación del renderizador en el centro
		centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

		// Aplicar el renderizador a todas las columnas de la tabla
		for (int i = 0; i < tableClasificacion.getColumnCount(); i++) {
			tableClasificacion.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
	}

	/**
	 * Funcion Action performed.
	 *
	 * @param e el Evento Accionado
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAtras) {
			Atras();
		} else if (e.getSource() == btnCerrarSesion) {
			CerrarSesion();
		} else if (e.getSource() == btnGuardar) {
			Guardar(true);
		} else if (e.getSource() == btnFinalizar) {
			Finalizar();
		} else if (e.getSource() == btnListaEquiposAñadir) {
			Añadir();
		} else if (e.getSource() == btnListaEquiposCrear) {
			Crear();
		} else if (e.getSource() == btnListaEquiposEditar) {
			Editar();
		} else if (e.getSource() == btnListaEquiposEliminar) {
			Eliminar();
		} else if (e.getSource() == btnIniciar) {
			Iniciar();
		}
	}

	/**
	 * Funcion para ir Atras.
	 */
	private void Atras() {
		if (modificadoEquipos) {
			int opcion = JOptionPane.showConfirmDialog(this, "¿Estas seguro de que quieres salir sin guardar?",
					"Cierre sin guardar", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			switch (opcion) {
			case 1:
				return;
			case JOptionPane.CLOSED_OPTION:
				return;
			}
		}
		// Creo las variables
		Inicio I = new Inicio();
		// Muestro la ventana Registro
		I.setVisible(true);
		// Centrar la ventana en el centro de la pantalla
		I.setLocationRelativeTo(null);
		// Cierro la ventana Login
		dispose();
	}

	/**
	 * Funcion para Cerrar Sesion.
	 */
	private void CerrarSesion() {
		if (modificadoEquipos) {
			int opcion = JOptionPane.showConfirmDialog(this, "¿Estas seguro de que quieres salir sin guardar?",
					"Cierre sin guardar", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			switch (opcion) {
			case 1:
				return;
			case JOptionPane.CLOSED_OPTION:
				return;
			}
		} else {
			int option = JOptionPane.showConfirmDialog(this, (String) "¿Desea cerrar sesión?", "Cierre de sesión",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);
			switch (option) {
			case JOptionPane.NO_OPTION:
				JOptionPane.showMessageDialog(this, (String) "La sesión sigue iniciada", "Cierre de sesión cancelado",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			case JOptionPane.CLOSED_OPTION:
				return;
			}
		}
		JOptionPane.showMessageDialog(this, (String) "Se ha cerrado sesión. Volviendo a Login.",
				"Cierre de sesión correcto", JOptionPane.INFORMATION_MESSAGE);

		Logger.nuevoMovimiento("Ha cerrado sesión.");

		// Creo las variables
		Login L = new Login();
		// Muestro la ventana Registro
		L.setVisible(true);
		// Centrar la ventana en el centro de la pantalla
		L.setLocationRelativeTo(null);
		// Cierro la ventana

		dispose();
		// Se quita el usuario con el que se ha iniciado sesion
		Sesion.setUsuarioActual(null);
		Seleccion.setTemporadaSeleccionada(null);
		Seleccion.setTemporadaNumero(null);
		Seleccion.setTemporadaPosicion(null);
	}

	/**
	 * Funcion para Añadir un Equipo a la Lista de Equipos de la Temporada.
	 */
	private void Añadir() {
		if (comprobarTemporada()) {
			mostrarError("No se pueden editar los equipos en una temporada Activa.");
			return;
		}
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

			modificadoEquipos = true;
			equiposCambiados = true;
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
		if (comprobarTemporada()) {
			mostrarError("No se pueden editar los equipos en una temporada Activa.");
			return;
		}
		// Creo las variables
		AñadirEquipo AE = new AñadirEquipo();

		setEnabled(false);
		AE.setVentanaEditar(this);

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
			}
		});
		// Muestro la ventana Registro
		AE.setVisible(true);
		// Centrar la ventana en el centro de la pantalla
		AE.setLocationRelativeTo(null);

		modificadoEquipos = true;
	}

	/**
	 * Funcion para Editar un Equipo.
	 */
	private void Editar() {
		if (comprobarTemporada()) {
			mostrarError("No se pueden editar los equipos en una temporada Activa.");
			return;
		}
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

					if (elm.contains(EquipoSeleccion.getEquipoSeleccionado()) && EquipoSeleccion.getGuardado()) {
						elm.removeElement(EquipoSeleccion.getEquipoSeleccionado());

						JOptionPane.showMessageDialog(null,
								"Se ha editado el Equipo y se ha tenido que eliminar de la temporada.", "Equipo Editado",
								JOptionPane.INFORMATION_MESSAGE);

						EquipoSeleccion.setGuardado(false);
					}

					lstEquiposRegistrados.clearSelection();
					lstEquipos.clearSelection();
					Guardar(false);

				}
			});
			// Muestro la ventana Registro
			EE.setVisible(true);
			// Centrar la ventana en el centro de la pantalla
			EE.setLocationRelativeTo(null);
			modificadoEquipos = true;

		} else {
			// No hay ningún elemento seleccionado
			mostrarError("Error, ningún elemento seleccionado en la lista");
		}
	}

	/**
	 * Funcion para Eliminar un equipo de la Lista de Equipos de la Temporada.
	 */
	private void Eliminar() {
		if (comprobarTemporada()) {
			mostrarError("No se pueden editar los equipos en una temporada Activa.");
			return;
		}
		// Obtengo las posiciones de los elementos seleccionados
		int indiceSeleccionado = lstEquipos.getSelectedIndex();

		// Compruebo si hay algún elemento seleccionado
		if (indiceSeleccionado != -1) {
			// Elimino elemento
			elm.remove(indiceSeleccionado);
			ListaEquipos.remove(indiceSeleccionado);

			modificadoEquipos = true;
			equiposCambiados = true;
		} else {
			// No hay ningún elemento seleccionado
			mostrarError("Error, ningún elemento seleccionado en la lista");
		}
	}

	/**
	 * Funcion para Guardar.
	 */
	private void Guardar(Boolean mostrarMensaje) {

		if (!mostrarMensaje && elm.getSize() < 6) {
			return;
		}

		if (modificadoEquipos) {
			if (elm.getSize() < 6) {
				lstEquipos.setBackground(new Color(255, 192, 183));
				mostrarError("El número mínimo de equipos permitidos es de 6");
				return;
			}
			lstEquipos.setBackground(Color.WHITE);

			ListaEquipos.clear();

			for (int i = 0; i < elm.getSize(); i++) {
				Equipo equipo = (Equipo) elm.getElementAt(i);
				ListaEquipos.add(equipo);
			}
			ListaJornadas = Jornada.generarJornadas(ListaEquipos, Seleccion.getTemporadaSeleccionada().getFechaInicio(),
					Seleccion.getTemporadaSeleccionada());

			Seleccion.getTemporadaSeleccionada().setListaEquipos(ListaEquipos);
			Seleccion.getTemporadaSeleccionada().setListaJornadas(ListaJornadas);

			modificadoEquipos = false;
			tabbedPaneJornadas.removeAll();
			Jornada.crearPanelesJornadas(ListaJornadas, tabbedPaneJornadas, true);
			// Cambiar la tabla de Clasificacion
			llenarTabla();

			if (mostrarMensaje) {
				JOptionPane.showMessageDialog(null, "Se han alterado los equipos de la temporada", "Temporada Actualizada",
						JOptionPane.INFORMATION_MESSAGE);
			}

			if (equiposCambiados) {

				Temporada temporadaSele = new Temporada();
				ListaTemporadas = Temporada.cargarTemporadas();

				for (Temporada temporada : ListaTemporadas) {
					if (Seleccion.getTemporadaNumero().equals(temporada.getNumero())) {
						temporadaSele.setNumero(temporada.getNumero());
						temporadaSele.setEstado(temporada.getEstado());
						temporadaSele.setFechaInicio(temporada.getFechaInicio());
						temporadaSele.setListaEquipos(temporada.getListaEquipos());
						temporadaSele.setListaJornadas(temporada.getListaJornadas());

						File EscudosTemporada = new File("ficheros/Escudos/");
						File JugadoresTemporada = new File("ficheros/Jugadores/");

						// Crear una carpeta dentro de Escudos para los escudos de la temporada
						File escudosTemporadaFolder = new File(EscudosTemporada, "Temporada" + temporada.getNumero() + "/");
						if (!escudosTemporadaFolder.exists()) {
							escudosTemporadaFolder.mkdirs();
						}

						// Crear una carpeta dentro de Jugadores para los jugadores de la temporada
						File jugadoresTemporadaFolder = new File(JugadoresTemporada,
								"Temporada" + temporada.getNumero() + "/");
						if (!jugadoresTemporadaFolder.exists()) {
							jugadoresTemporadaFolder.mkdirs();
						}

						// Copiar escudos y fotos de jugadores
						copiarEscudos(temporada, escudosTemporadaFolder);
						copiarFotosJugadores(temporada, jugadoresTemporadaFolder);
					}
				}

				try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/CSLeague", "root", "")) {
					conn.setAutoCommit(false); // Desactivar el modo de autocommit

					for (Equipo equipo : Seleccion.getTemporadaSeleccionada().getListaEquipos()) {
						// Crear la consulta de actualizacion para la relación entre equipo y temporada
						String queryTemporadaParticipada = "UPDATE TemporadaParticipada SET Escudo = ?, Descripcion = ? WHERE Temporada = ? AND Equipo = ?";
						PreparedStatement psTemporadaParticipada = conn.prepareStatement(queryTemporadaParticipada);
						// Asignar los valores a los parámetros
						psTemporadaParticipada.setString(1, equipo.getEscudo());
						psTemporadaParticipada.setString(2, equipo.getDescripcion());
						psTemporadaParticipada.setInt(3, temporadaSele.getNumero());
						psTemporadaParticipada.setString(4, equipo.getNombre());
						psTemporadaParticipada.executeUpdate();
						psTemporadaParticipada.close();

						// Crear la consulta de actualización para el entrenador
						String queryEntrenadorContratado = "UPDATE EntrenadorContratado SET Nombre = ?, Apellido = ?, Nacionalidad = ? WHERE Entrenador = ? AND Equipo = ? AND Temporada = ?";
						PreparedStatement psEntrenadorContratado = conn.prepareStatement(queryEntrenadorContratado);
						psEntrenadorContratado.setString(1, equipo.getEntrenador().getNombre());
						psEntrenadorContratado.setString(2, equipo.getEntrenador().getApellido());
						psEntrenadorContratado.setString(3, equipo.getEntrenador().getNacionalidad());
						psEntrenadorContratado.setString(4, equipo.getEntrenador().getDNI());
						psEntrenadorContratado.setString(5, equipo.getNombre());
						psEntrenadorContratado.setInt(6, temporadaSele.getNumero());
						psEntrenadorContratado.executeUpdate();
						psEntrenadorContratado.close();

						for (Jugador jugador : equipo.getListaJugadores()) {
							// Crear la consulta de inserción para la relación entre equipo y entrenador
							String queryJugadorContratado = "UPDATE JugadorContratado SET Nombre = ?, Apellido = ?, Nacionalidad = ?, Foto = ?, Rol = ? WHERE Jugador = ? AND Equipo = ? AND Temporada = ?";
							PreparedStatement psJugadorContratado = conn.prepareStatement(queryJugadorContratado);
							// Asignar los valores a los parámetros
							psJugadorContratado.setString(1, jugador.getNombre());
							psJugadorContratado.setString(2, jugador.getApellido());
							psJugadorContratado.setString(3, jugador.getNacionalidad());
							psJugadorContratado.setString(4, jugador.getFoto());
							psJugadorContratado.setString(5, jugador.getPosicion());
							psJugadorContratado.setString(6, jugador.getDNI());
							psJugadorContratado.setString(7, equipo.getNombre());
							psJugadorContratado.setInt(8, temporadaSele.getNumero());
							psJugadorContratado.executeUpdate();
							psJugadorContratado.close();
						}
					}

					// Confirmar la transacción
					conn.commit();

					// Cerrar el PreparedStatement
				} catch (SQLException e) {
					e.printStackTrace();
					// Manejar el error apropiadamente
				}
			}

		}
		// Si la Temporada esta Activa
		else if (comprobarTemporada()) {

			// Borra las estadísticas por temporada después de recorrer todas las jornadas
			for (Jornada jornada : ListaJornadas) {
				for (Partido partido : jornada.getListaPartidos()) {
					Equipo.limpiarEstadisticasEquipo(Seleccion.getTemporadaSeleccionada(),
							partido.getEquipoLocal().getNombre());
					Equipo.limpiarEstadisticasEquipo(Seleccion.getTemporadaSeleccionada(),
							partido.getEquipoVisitante().getNombre());
				}
			}

			// Se recorre todas las jornadas que haya en el tabbedpanel
			for (int i = 0; i < tabbedPaneJornadas.getTabCount(); i++) {
				// Se crea una variable sobre el panel de la jornada
				JPanel panelJornadaX = (JPanel) ((JScrollPane) tabbedPaneJornadas.getComponentAt(i)).getViewport()
						.getView();
				// Se busca la Jornada en concreto dentro de la lista de jornadas
				Jornada jornada = ListaJornadas.get(i);

				// Se recorre cada partido de cada jornada
				for (Partido partido : jornada.getListaPartidos()) {

					// Obtener todos los componentes del panel para buscar los JTextField
					Component[] components = panelJornadaX.getComponents();

					// Se recorren todos los componentes de el panel de la jornada
					for (Component component : components) {
						// Se comprueba si el componente es un JTextField
						if (component instanceof JTextField) {
							// Verificar si el componente tiene la propiedad del cliente que lo identifica
							Object partidoIndexObj = ((JTextField) component).getClientProperty("partidoIndex");
							Object equipoObj = ((JTextField) component).getClientProperty("equipo");

							// Se comprueba que los datos de identificación del partido no estén vacíos
							if (partidoIndexObj != null && equipoObj != null) {
								// Se crea una variable para buscar la propiedad de cada componente JTextField
								int partidoIndex = (Integer) partidoIndexObj;
								// Se crea una variable para buscar la propiedad específica de cada componente
								// JTextField y así identificarlo
								String equipo = equipoObj.toString();

								// Comprueba si el partido se encuentra dentro de la lista de partidos de cada
								// jornada
								if (partidoIndex == jornada.getListaPartidos().indexOf(partido)) {
									// Variable para guardar los puntos que se han insertado para cada equipo
									JTextField textField = (JTextField) component;
									String inputText = textField.getText();

									// Se valida que el dato introducido es correcto (\\d) representa lo introducido
									// como digito y {1,2} el rango de digitos minimos que debe tener
									if (inputText.matches("\\d{1,2}") || inputText.isBlank()) {
										int puntos;
										// Si está vacío el Input le asignamos el valor -1, ya que es valor que se ha
										// configurado por defecto de los puntos
										if (inputText.isBlank()) {
											puntos = -1;
										} else {
											puntos = Integer.parseInt(inputText);
										}

										// Comprueba si la identificación del equipo es local o visitante
										if (equipo.equals("local")) {
											if (puntos > 13) {
												JOptionPane.showMessageDialog(null,
														"El máximo valor permitido en un partido es 13.", "Error de entrada",
														JOptionPane.ERROR_MESSAGE);
												return;
											}

											partido.setPuntosLocal(puntos);
										} else if (equipo.equals("visitante")) {
											if (puntos > 13) {
												JOptionPane.showMessageDialog(null,
														"El máximo valor permitido en un partido es 13.", "Error de entrada",
														JOptionPane.ERROR_MESSAGE);
												return;
											}
											// Verificar que ambos equipos no tengan 13 puntos al mismo tiempo
											if (puntos == 13 && partido.getPuntosLocal() == 13) {
												JOptionPane.showMessageDialog(null,
														"En un partido hay dos equipos que tienen 13 puntos.", "Error de entrada",
														JOptionPane.ERROR_MESSAGE);
												return;
											}
											partido.setPuntosVisitante(puntos);

											// Verificar que al menos un equipo tenga 13 puntos
											if (partido.getPuntosLocal() != -1 && partido.getPuntosVisitante() != -1
													&& partido.getPuntosLocal() != 13 && partido.getPuntosVisitante() != 13
													&& partido.getPuntosLocal() != 0 && partido.getPuntosVisitante() != 0) {
												JOptionPane.showMessageDialog(null, "Uno de los equipos debe tener 13 puntos.",
														"Error de entrada", JOptionPane.ERROR_MESSAGE);
												return;
											}
										}

									} else {
										// Mostrar Mensaje de Error
										JOptionPane.showMessageDialog(null,
												"En todos los campos tiene que ingresar un número entero positivo.",
												"Error de entrada", JOptionPane.ERROR_MESSAGE);
										// Se cambia el valor insertado
										textField.setText("");
										return; // Salir de la Funcion
									}

								}
							} else {
								System.out.println("El componente no tiene las propiedades del cliente necesarias");
							}
						}
					}

					// Verifica si los puntos son -1 para ambos equipos o diferentes
					if ((partido.getPuntosLocal() == -1 && partido.getPuntosVisitante() != -1)
							|| (partido.getPuntosVisitante() == -1 && partido.getPuntosLocal() != -1)) {
						// Muestra un mensaje de error y retorna
						JOptionPane.showMessageDialog(null,
								"Hay un partido en el que solo se ha introducido los datos de un equipo.", "Error de entrada",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					// Obtener el nombre de ambos equipos
					String nombreEquipoLocal = partido.getEquipoLocal().getNombre();
					String nombreEquipoVisitante = partido.getEquipoVisitante().getNombre();

					// Actualizar las estadísticas para ambos equipos
					partido.getEquipoLocal().actualizarEstadisticas(partido, Seleccion.getTemporadaSeleccionada(),
							nombreEquipoLocal);
					partido.getEquipoVisitante().actualizarEstadisticas(partido, Seleccion.getTemporadaSeleccionada(),
							nombreEquipoVisitante);

					// Se eliminan y se vuelven a añdir los Equipos de la Lista de Equipos de la
					// Temporada
					Seleccion.getTemporadaSeleccionada().getListaEquipos().remove(partido.getEquipoLocal());
					Seleccion.getTemporadaSeleccionada().getListaEquipos().remove(partido.getEquipoVisitante());
					Seleccion.getTemporadaSeleccionada().getListaEquipos().add(partido.getEquipoLocal());
					Seleccion.getTemporadaSeleccionada().getListaEquipos().add(partido.getEquipoVisitante());

					try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/CSLeague", "root", "")) {
						conn.setAutoCommit(false); // Desactivar el modo de autocommit

						// Crear la consulta SQL para actualizar las estadísticas
						String updateEstadisticasQuery = "UPDATE Partido SET PuntosLocal = ?, PuntosVisitante = ?, Jugado = ? WHERE Temporada = ? AND Jornada = ? AND EquipoLocal = ? AND EquipoVisitante = ?";
						PreparedStatement psUpdateEstadisticas = conn.prepareStatement(updateEstadisticasQuery);

						// Asignar los valores a los parámetros de la consulta
						if (partido.getPuntosLocal() == -1 && partido.getPuntosVisitante() == -1) {
							psUpdateEstadisticas.setInt(1, 0);
							psUpdateEstadisticas.setInt(2, 0);
							psUpdateEstadisticas.setBoolean(3, false);
						} else {
							psUpdateEstadisticas.setInt(1, partido.getPuntosLocal());
							psUpdateEstadisticas.setInt(2, partido.getPuntosVisitante());
							psUpdateEstadisticas.setBoolean(3, true);
						}
						psUpdateEstadisticas.setInt(4, Seleccion.getTemporadaNumero());
						psUpdateEstadisticas.setInt(5, jornada.getNumero());
						psUpdateEstadisticas.setString(6, partido.getEquipoLocal().getNombre());
						psUpdateEstadisticas.setString(7, partido.getEquipoVisitante().getNombre());

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
			}

			// Se actualiza la información de la lista de las Jornadas
			Seleccion.getTemporadaSeleccionada().setListaJornadas(ListaJornadas);
			// Cambiar la tabla de Clasificacion
			llenarTabla();
			// Mensaje que comprueba los cambios
			JOptionPane.showMessageDialog(null, "Se han alterado las Estadisticas", "Temporada Actualizada",
					JOptionPane.INFORMATION_MESSAGE);
		}

		Logger.nuevoMovimiento("Ha editado la Temporada " + Seleccion.getTemporadaSeleccionada().getNumero() + ".");

	}

	/**
	 * Función para llenar la tabla con datos de estadísticas desde la base de
	 * datos.
	 */
	private void llenarTabla() {
		// Ordenar la lista de equipos por estadísticas en orden descendente
		ListaEquipos.sort((equipo1, equipo2) -> {
			Temporada temporadaActual = Seleccion.getTemporadaSeleccionada();
			Estadisticas estadisticas1 = obtenerEstadisticasEquipo(equipo1, temporadaActual);
			Estadisticas estadisticas2 = obtenerEstadisticasEquipo(equipo2, temporadaActual);

			// Utilizar el comparador personalizado para Estadisticas
			if (estadisticas1 != null && estadisticas2 != null) {
				return new Estadisticas().compareTo(estadisticas1, estadisticas2);
			}
			return 0;
		});

		Temporada temporadaActual = Seleccion.getTemporadaSeleccionada();

		// Limpiar el modelo de datos de la tabla
		ctm.setRowCount(0);

		// Añadir la primera fila con los encabezados
		Object[] primeraFila = { "Posición", "Equipo", "Puntos Totales", "Partidas Jugadas", "Partidas Ganadas",
				"Partidas Perdidas", "R. Diferencia" };
		ctm.addRow(primeraFila);

		int posicion = 1;

		for (Equipo equipo : ListaEquipos) {
			if (!equipo.getNombre().equals("Equipo para Descansar")) {
				Estadisticas estadisticas = obtenerEstadisticasEquipo(equipo, temporadaActual);

				Object[] fila = new Object[7];
				fila[0] = posicion;
				fila[1] = equipo.getNombre();

				if (estadisticas != null) {
					fila[2] = estadisticas.getPuntosTotales();
					fila[3] = estadisticas.getPartidosJugados();
					fila[4] = estadisticas.getPartidosGanados();
					fila[5] = estadisticas.getPartidosPerdidos();
					fila[6] = estadisticas.getRondasDiferencia();
				} else {
					fila[2] = 0;
					fila[3] = 0;
					fila[4] = 0;
					fila[5] = 0;
					fila[6] = 0;
				}

				ctm.addRow(fila);
				posicion++;
			}
		}
	}

	/**
	 * Función para obtener las estadísticas de un equipo en una temporada
	 * específica desde la base de datos.
	 *
	 * @param equipo    el equipo del que se desean obtener las estadísticas
	 * @param temporada la temporada de la que se desean obtener las estadísticas
	 * @return las estadísticas del equipo para la temporada especificada
	 */
	private Estadisticas obtenerEstadisticasEquipo(Equipo equipo, Temporada temporada) {
		Estadisticas estadisticas = null;

		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/CSLeague", "root", "")) {
			String query = "SELECT * FROM Estadisticas WHERE Temporada = ? AND Equipo = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, temporada.getNumero());
			ps.setString(2, equipo.getNombre());

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
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

		return estadisticas;
	}

	/**
	 * Funcion para Finalizar Temporada.
	 */
	private void Finalizar() {
		Boolean otraTemporada = false;

		Temporada temporadaSeleccionada = Seleccion.getTemporadaSeleccionada();

		// Verificar si todos los partidos han sido jugados
		if (!todosPartidosJugados(temporadaSeleccionada)) {
			JOptionPane.showMessageDialog(this, "No se pueden finalizar temporadas con partidos pendientes.",
					"Temporada Incompleta", JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		if ("ACTIVA".equals(temporadaSeleccionada.getEstado())) {
			int opcion = JOptionPane.showConfirmDialog(this,
					"¿Estás seguro de que quieres finalizar la temporada? Una vez finalizada, no podrás editarla.",
					"Finalizar Temporada", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			switch (opcion) {
			case 1:
				return;
			case JOptionPane.CLOSED_OPTION:
				return;
			case 0:
				try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/CSLeague", "root", "")) {
					conn.setAutoCommit(false); // Desactivar el modo de autocommit

					// Crear la consulta SQL para actualizar el estado de la temporada a
					// "FINALIZADA"
					String updateTemporadaQuery = "UPDATE Temporada SET Estado = 'FINALIZADA' WHERE Numero = ?";
					PreparedStatement psUpdateTemporada = conn.prepareStatement(updateTemporadaQuery);
					psUpdateTemporada.setInt(1, temporadaSeleccionada.getNumero());

					// Ejecutar la consulta de actualización
					psUpdateTemporada.executeUpdate();

					// Confirmar la transacción
					conn.commit();

					// Cerrar el PreparedStatement
					psUpdateTemporada.close();

					temporadaSeleccionada.setEstado("FINALIZADA");

					Logger.nuevoMovimiento("Ha finalizado la Temporada " + temporadaSeleccionada.getNumero() + ".");

					// Buscar temporada con el estado "PROXIMAMENTE"
					DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
					for (Temporada temporada : ListaTemporadas) {
						if ("PROXIMAMENTE".equals(temporada.getEstado())) {
							comboBoxModel.addElement("Temporada " + temporada.getNumero());
							otraTemporada = true;
						}
					}

					if (otraTemporada) {
						JComboBox<String> comboBox = new JComboBox<>(comboBoxModel);

						int result = JOptionPane.showOptionDialog(null, comboBox, "¿Quieres Activar otra Temporada?",
								JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

						if (result == JOptionPane.YES_OPTION) {
							// Modificar la temporada seleccionada a estado "ACTIVA"
							String temporadaSeleccion = comboBox.getSelectedItem().toString();
							int numeroTemporada = Integer.parseInt(temporadaSeleccion.replaceAll("[\\D]", ""));
							for (Temporada temporada : ListaTemporadas) {
								if (temporada.getNumero() == numeroTemporada) {
									temporada.setEstado("ACTIVA");
									break;
								}
							}
						}
					}

					// Crear las variables
					Inicio I = new Inicio();
					// Mostrar la ventana Inicio
					I.setVisible(true);
					// Centrar la ventana en el centro de la pantalla
					I.setLocationRelativeTo(null);
					// Cerrar la ventana actual
					dispose();

				} catch (SQLException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(this, "Error al finalizar la temporada en la base de datos.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				return;
			}
		}
		JOptionPane.showMessageDialog(this, "No se puede finalizar una temporada que no está activa.",
				"Temporada Errónea", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Funcion para verificar que Todos los Partidos estan Jugados.
	 *
	 * @param temporada la temporada a verificar
	 * @return true si todos los partidos están jugados, false en caso contrario
	 */
	private boolean todosPartidosJugados(Temporada temporada) {
		int totalPartidos = 0;
		int partidosJugados = 0;

		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/CSLeague", "root", "")) {
			// Contar el número total de partidos en la temporada
			String countTotalPartidosQuery = "SELECT COUNT(*) FROM Partido WHERE Temporada = ?";
			PreparedStatement psCountTotalPartidos = conn.prepareStatement(countTotalPartidosQuery);
			psCountTotalPartidos.setInt(1, temporada.getNumero());

			ResultSet rsTotalPartidos = psCountTotalPartidos.executeQuery();
			if (rsTotalPartidos.next()) {
				totalPartidos = rsTotalPartidos.getInt(1);
			}

			// Contar el número de partidos jugados en la temporada
			String countPartidosJugadosQuery = "SELECT COUNT(*) FROM Partido WHERE Temporada = ? AND Jugado = true";
			PreparedStatement psCountPartidosJugados = conn.prepareStatement(countPartidosJugadosQuery);
			psCountPartidosJugados.setInt(1, temporada.getNumero());

			ResultSet rsPartidosJugados = psCountPartidosJugados.executeQuery();
			if (rsPartidosJugados.next()) {
				partidosJugados = rsPartidosJugados.getInt(1);
			}

			// Comparar el número de partidos jugados con el total de partidos
			return partidosJugados == totalPartidos;
		} catch (SQLException e) {
			e.printStackTrace();
			// Manejar el error apropiadamente (lanzar una excepción, mostrar un mensaje,
			// etc.)
			return false;
		}
	}

	/**
	 * Funcion para Inciar la Temporada.
	 */
	public void Iniciar() {
		Temporada temporadaSeleccionada = Seleccion.getTemporadaSeleccionada();

		int opcion = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que quieres iniciar la temporada?",
				"Iniciar Temporada", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

		if (opcion == JOptionPane.YES_OPTION) {
			try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/CSLeague", "root", "")) {
				conn.setAutoCommit(false); // Desactivar el modo de autocommit

				// Crear la consulta SQL para actualizar el estado de la temporada a "ACTIVA"
				String updateTemporadaQuery = "UPDATE Temporada SET Estado = 'ACTIVA' WHERE Numero = ?";
				PreparedStatement psUpdateTemporada = conn.prepareStatement(updateTemporadaQuery);
				psUpdateTemporada.setInt(1, temporadaSeleccionada.getNumero());

				// Ejecutar la consulta de actualización
				psUpdateTemporada.executeUpdate();

				// Confirmar la transacción
				conn.commit();

				// Cerrar el PreparedStatement
				psUpdateTemporada.close();

				// Actualizar el estado localmente
				temporadaSeleccionada.setEstado("ACTIVA");

				Logger.nuevoMovimiento("Ha iniciado la Temporada " + temporadaSeleccionada.getNumero() + ".");

				Seleccion.setTemporadaPosicion(0);

				// Crear las variables para la ventana de inicio
				Inicio I = new Inicio();

				// Mostrar la ventana de inicio
				I.setVisible(true);

				// Centrar la ventana de inicio en el centro de la pantalla
				I.setLocationRelativeTo(null);

				// Cerrar la ventana actual
				dispose();
			} catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, "Error al iniciar la temporada en la base de datos.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
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
	 * Funcion para Comprobar el Estado de una Temporada.
	 *
	 * @return true, if successful
	 */
	private boolean comprobarTemporada() {
		if (Seleccion.getTemporadaSeleccionada().getEstado().equals("ACTIVA")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Funcion para Añadir un Equipo a la Lista de Equipos Registrados.
	 *
	 * @param nuevoEquipo el Nuevo Equipo
	 */
	public void añadirEquipo(Equipo nuevoEquipo) {
		AñadirEquipo AE = new AñadirEquipo();
		// TODO Auto-generated method stub
		elm.addElement(nuevoEquipo);
		ListaEquiposRegistrados.add(nuevoEquipo);
		elm2.addElement(nuevoEquipo);
		ListaEquipos.add(nuevoEquipo);
		AE.setAbiertoEditar();
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

	/**
	 * Funcion para la Apertura de la Ventana.
	 *
	 * @param e el Evento Accionado
	 */
	@Override
	public void windowOpened(WindowEvent e) {
	}

	/**
	 * Funcion previa al Cierre de la Ventana.
	 *
	 * @param e el Evento Accionado
	 */
	@Override
	public void windowClosing(WindowEvent e) {
		if (Sesion.getUsuarioActual() != null) {

			Logger.nuevoMovimiento("Ha cerrado sesión.");

		}
	}

	/**
	 * Funcion posterior al Cierre de la Ventana.
	 *
	 * @param e el Evento Accionado
	 */
	@Override
	public void windowClosed(WindowEvent e) {
	}

	/**
	 * Funcion para la Minimizacion de la Ventana.
	 *
	 * @param e el Evento Accionado
	 */
	@Override
	public void windowIconified(WindowEvent e) {
	}

	/**
	 * Funcion para la Desminimizacion de la Ventana.
	 *
	 * @param e el Evento Accionado
	 */
	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	/**
	 * Funcion para cuando la Ventana esta Activa.
	 *
	 * @param e el Evento Accionado
	 */
	@Override
	public void windowActivated(WindowEvent e) {
	}

	/**
	 * Funcion para cuando la Ventana esta Desactivada.
	 *
	 * @param e el Evento Accionado
	 */
	@Override
	public void windowDeactivated(WindowEvent e) {
	}

	private void copiarEscudos(Temporada temporada, File destinoFolder) {
		for (Equipo equipo : temporada.getListaEquipos()) {
			if (equipo.getNombre().equals("Equipo para Descansar")) {
				continue;
			}
			String extension = obtenerExtension(equipo.getEscudo());
			String nombreEscudo = equipo.getNombre() + extension;
			File destinoArchivo = new File(destinoFolder, nombreEscudo);

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

	private void copiarFotosJugadores(Temporada temporada, File destinoFolder) {
		for (Equipo equipo : temporada.getListaEquipos()) {
			if (equipo.getNombre().equals("Equipo para Descansar")) {
				continue;
			}
			for (Jugador jugador : equipo.getListaJugadores()) {
				String extensionFoto = obtenerExtension(jugador.getFoto());
				String nombreFoto = equipo.getNombre() + "-" + jugador.getDNI() + extensionFoto;
				File destinoArchivoFoto = new File(destinoFolder, nombreFoto);

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

	/**
	 * Función para obtener la extensión de un archivo.
	 */
	private String obtenerExtension(String nombreArchivo) {
		int lastIndex = nombreArchivo.lastIndexOf(".");
		if (lastIndex == -1) {
			return ""; // No se encontró una extensión
		}
		return "."+nombreArchivo.substring(lastIndex + 1);
	}
}
