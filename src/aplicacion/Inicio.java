package aplicacion;

//Eventos importados
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import definicion.Equipo;
import definicion.EquipoSeleccion;
import definicion.Jornada;
import definicion.Jugador;
import definicion.Logger;
import definicion.Partido;
import definicion.Seleccion;
import definicion.Sesion;
import definicion.Temporada;
import definicion.Usuario;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.LineBorder;

/**
 * La Clase Inicio.
 */
public class Inicio extends JFrame implements ActionListener, WindowListener, ListSelectionListener {

	/** La Constante serialVersionUID generada para identificarse. */
	private static final long serialVersionUID = 1L;

	/** El Content Pane principal. */
	private JPanel contentPane;

	/** El Content Pane Superior. */
	private JPanel panelSuperior;

	/** El Boton de Cerrar Sesion. */
	private JButton btnCerrarSesion;

	/** El Boton de Exportar XML. */
	private JButton btnXML;

	/** El Content Pane Lateral. */
	private JPanel panelLateral;

	/** El Content Pane Central. */
	private JPanel panelCentral;

	/** El Boton de Temporadas. */
	private JButton btnTemporadas;

	/** El Boton de Clasificacion. */
	private JButton btnClasificacion;

	/** El Boton de Jornadas. */
	private JButton btnJornadas;

	/** El Boton de Equipos. */
	private JButton btnEquipos;

	/** El Boton de Panel. */
	private JButton btnPanel;

	/** El Boton de Editar Temporada. */
	private JButton btnEditarTemp;

	/** El Label de Temporada. */
	private JLabel lblTemporada;

	/** El Boton de Añadir Temporada. */
	private JButton btnAñadirTemp;

	/** El Boton de Eliminar Temporada. */
	private JButton btnEliminarTemp;

	/** La Lista de Temporadas. */
	private JList<Temporada> lstTemporada;

	/** El Modelo de la Lista de Tempordas. */
	private DefaultListModel<Temporada> tlm;

	/** El Scroll Pane de la Lista de Temporadas. */
	private JScrollPane scrollPane;

	/** La Lista de Temporadas Totales que hay Registradas. */
	private ArrayList<Temporada> ListaTemporadas;

	/** La Lista de Usuarios Registrados. */
	private List<Usuario> ListaUsuarios;

	/**
	 * Ejecuta la aplicacion.
	 *
	 * @param args los Argumentos
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicio frame = new Inicio();
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
	public Inicio() {
		// Imagen de la aplicación
		setIconImage(new ImageIcon(getClass().getResource("/Imagenes/CSL.png")).getImage());

		// Añado acción listener
		this.addWindowListener(this);
		// Eliminar posibilidad de reescalar la ventana
		setResizable(false);
		// Establece el título
		setTitle("Inicio ");
		// Establece que hacer cuando se cierra la ventana
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Establece posicion y tamaño
		setBounds(100, 100, 1050, 550);
		// Centrar la ventana en el centro de la pantalla
		setLocationRelativeTo(null);
		// Se crea el panel para insertar los elementos
		contentPane = new JPanel();
		// Establece los bordes del panel
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		// Hace el panel absoluto
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Crea un panel
		panelSuperior = new JPanel();
		// Establece el color de fondo
		panelSuperior.setBackground(new Color(29, 29, 27));
		// Establece posicion y tamaño
		panelSuperior.setBounds(0, 0, 1036, 90);
		// Hace el panel absoluto
		contentPane.add(panelSuperior);
		panelSuperior.setLayout(null);

		// Crea un botón
		btnCerrarSesion = new JButton("Cerrar Sesión");
		btnCerrarSesion.setFont(new Font("Dialog", Font.BOLD, 12));
		// Añade accion de tipo listener
		btnCerrarSesion.addActionListener(this);
		// Establece el color de fondo del botón
		btnCerrarSesion.setBackground(new Color(230, 230, 230));
		btnCerrarSesion.setForeground(new Color(12, 12, 12));
		// Establece que el botón no se puede tabular
		btnCerrarSesion.setFocusable(false);
		// Establece el tipo de cursor
		btnCerrarSesion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		// Establece los bordes
		btnCerrarSesion.setBorder(null);
		// Establece posicion y tamaño
		btnCerrarSesion.setBounds(22, 22, 119, 45);
		// Añade el botón al panel
		panelSuperior.add(btnCerrarSesion);

		// Crea un botón
		btnPanel = new JButton("Panel Administracion");
		btnPanel.setFont(new Font("Dialog", Font.BOLD, 12));
		// Añade accion de tipo listener al boton
		btnPanel.addActionListener(this);
		// Propiedades del boton
		btnPanel.setBackground(new Color(230, 230, 230));
		btnPanel.setForeground(new Color(12, 12, 12));
		btnPanel.setBorder(null);
		btnPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPanel.setFocusable(false);
		btnPanel.setBounds(684, 22, 140, 45);
		panelSuperior.add(btnPanel);

		// Crea un botón
		btnEditarTemp = new JButton("Editar Temporada");
		btnEditarTemp.setFont(new Font("Dialog", Font.BOLD, 13));
		// Añade accion de tipo listener al boton
		btnEditarTemp.addActionListener(this);
		// Propiedades del boton
		btnEditarTemp.setBackground(new Color(56, 56, 52));
		btnEditarTemp.setForeground(new Color(255, 255, 9));
		btnEditarTemp.setBorder(null);
		btnEditarTemp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEditarTemp.setFocusable(false);
		btnEditarTemp.setBounds(848, 22, 156, 45);
		panelSuperior.add(btnEditarTemp);

		// Crea un label
		lblTemporada = new JLabel("Seleccionar Temporada");
		lblTemporada.setForeground(Color.WHITE);
		// Establece el tipo de orientacion horizontal
		lblTemporada.setHorizontalAlignment(SwingConstants.CENTER);
		// Establece la fuente
		lblTemporada.setFont(new Font("Dialog", Font.BOLD, 25));
		// Establece posicion y tamaño
		lblTemporada.setBounds(303, 22, 429, 45);
		// Añade el label al panel
		panelSuperior.add(lblTemporada);

		// Crea un panel
		panelLateral = new JPanel();
		// Propiedades de panel
		panelLateral.setBackground(new Color(128, 255, 255));
		panelLateral.setBounds(0, 88, 158, 435);
		contentPane.add(panelLateral);
		panelLateral.setLayout(null);

		// Crea un botón
		btnTemporadas = new JButton("Temporadas");
		btnTemporadas.setFont(new Font("Dialog", Font.BOLD, 15));
		btnTemporadas.setEnabled(false);
		// Añade accion de tipo listener al botón
		btnTemporadas.addActionListener(this);
		btnTemporadas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnTemporadas.setFocusable(false);
		btnTemporadas.setForeground(new Color(255, 255, 9));
		btnTemporadas.setBackground(new Color(56, 56, 52));
		btnTemporadas.setBounds(0, 0, 158, 108);
		panelLateral.add(btnTemporadas);

		// Crea un botón
		btnClasificacion = new JButton("Clasificación");
		btnClasificacion.setFont(new Font("Dialog", Font.BOLD, 15));
		// Añade accion de tipo listener al botón
		btnClasificacion.addActionListener(this);
		// Propiedades del botón
		btnClasificacion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnClasificacion.setFocusable(false);
		btnClasificacion.setForeground(new Color(255, 255, 9));
		btnClasificacion.setBackground(new Color(56, 56, 52));
		btnClasificacion.setBounds(0, 106, 158, 108);
		panelLateral.add(btnClasificacion);

		// Crea un botón
		btnJornadas = new JButton("Jornadas");
		btnJornadas.setFont(new Font("Dialog", Font.BOLD, 15));
		// Añade accion de tipo listener al botón
		btnJornadas.addActionListener(this);
		// Propiedades del botón
		btnJornadas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnJornadas.setFocusable(false);
		btnJornadas.setForeground(new Color(255, 255, 9));
		btnJornadas.setBackground(new Color(56, 56, 52));
		btnJornadas.setBounds(0, 213, 158, 108);
		panelLateral.add(btnJornadas);

		// Crea un botón
		btnEquipos = new JButton("Equipos");
		btnEquipos.setFont(new Font("Dialog", Font.BOLD, 15));
		btnEquipos.setForeground(Color.WHITE);
		// Añade accion de tipo listener al botón
		btnEquipos.addActionListener(this);
		// Propiedades del botón
		btnEquipos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEquipos.setFocusable(false);
		btnEquipos.setForeground(new Color(255, 255, 9));
		btnEquipos.setBackground(new Color(56, 56, 52));
		btnEquipos.setBounds(0, 318, 158, 108);
		panelLateral.add(btnEquipos);

		panelCentral = new JPanel();
		panelCentral.setFocusable(false);
		panelCentral.setBounds(159, 91, 874, 432);
		panelCentral.setBackground(new Color(220, 220, 220));
		contentPane.add(panelCentral);
		panelCentral.setLayout(null);

		btnAñadirTemp = new JButton("Añadir temporada");
		btnAñadirTemp.setForeground(new Color(255, 255, 255));
		btnAñadirTemp.setFocusable(false);
		btnAñadirTemp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAñadirTemp.setBorder(null);
		btnAñadirTemp.setForeground(Color.WHITE);
		btnAñadirTemp.setBackground(new Color(29, 29, 27));
		btnAñadirTemp.setFont(new Font("Dialog", Font.BOLD, 13));
		btnAñadirTemp.addActionListener(this);
		btnAñadirTemp.setBounds(79, 348, 186, 46);
		panelCentral.add(btnAñadirTemp);

		btnEliminarTemp = new JButton("Eliminar temporada");
		btnEliminarTemp.setFocusable(false);
		btnEliminarTemp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEliminarTemp.setBorder(null);
		btnEliminarTemp.setForeground(Color.WHITE);
		btnEliminarTemp.setBackground(new Color(29, 29, 27));
		btnEliminarTemp.setFont(new Font("Dialog", Font.BOLD, 13));
		btnEliminarTemp.addActionListener(this);
		btnEliminarTemp.setBounds(609, 348, 186, 46);
		panelCentral.add(btnEliminarTemp);

		tlm = new DefaultListModel<Temporada>();

		lstTemporada = new JList<Temporada>();
		lstTemporada.setFocusable(false);
		lstTemporada.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Creo las variables
				Clasificacion C = new Clasificacion();
				// Muestro la ventana Registro
				C.setVisible(true);
				// Centrar la ventana en el centro de la pantalla
				C.setLocationRelativeTo(null);
				// Cierro la ventana Login
				dispose();
			}
		});
		lstTemporada.setBackground(new Color(240, 240, 240));
		lstTemporada.setFont(new Font("Dialog", Font.BOLD, 18));
		lstTemporada.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lstTemporada.setBounds(123, 49, 628, 262);
		lstTemporada.addListSelectionListener(this);
		// asocio el DefaultListModel a la lista
		lstTemporada.setModel(tlm);
		panelCentral.add(lstTemporada);

		// Crear JScrollPane y agregar la lista a él
		scrollPane = new JScrollPane(lstTemporada);
		scrollPane.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		scrollPane.setBounds(123, 49, 628, 262);
		panelCentral.add(scrollPane);

		btnXML = new JButton("Exportar XML");
		btnXML.addActionListener(this);
		btnXML.setFont(new Font("Dialog", Font.BOLD, 13));
		btnXML.setFocusable(false);
		btnXML.setBorder(null);
		btnXML.setForeground(Color.white);
		btnXML.setBackground(new Color(29, 29, 27));
		btnXML.setBounds(344, 348, 186, 46);
		panelCentral.add(btnXML);

		// Después de cargar las temporadas desde el archivo
		ListaTemporadas = Temporada.cargarTemporadas();

		ordenarTemporadas();

		if (Seleccion.getTemporadaSeleccionada() == null && !tlm.isEmpty()
				&& tlm.firstElement().getEstado().equals("ACTIVA")) {
			Seleccion.setTemporadaSeleccionada(tlm.firstElement());
			Seleccion.setTemporadaPosicion(tlm.indexOf(tlm.firstElement()));
			Seleccion.setTemporadaNumero(tlm.firstElement().getNumero());
			lstTemporada.setSelectedIndex(tlm.indexOf(tlm.firstElement()));
		}

		if (Seleccion.getTemporadaSeleccionada() != null
				&& Seleccion.getTemporadaSeleccionada().getEstado().equals("FINALIZADA")) {
			btnEditarTemp.setVisible(false);
			btnPanel.setBounds(864, 22, 140, 45);
		}

		if (Seleccion.getTemporadaPosicion() != null) {
			lstTemporada.setSelectedIndex(Seleccion.getTemporadaPosicion());
		}
		EquipoSeleccion.setGuardado(false);

		if (tlm.isEmpty()) {
			btnXML.setVisible(false);
			btnAñadirTemp.setBounds(204, 348, 186, 46);
			btnEliminarTemp.setBounds(464, 348, 186, 46);
		}

		// Se conecta a la base de datos
		// crea una base de datos si todavía no existe
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/usuarios.odb");
		EntityManager em = emf.createEntityManager();

		// Utiliza una consulta JPQL para obtener todos los usuarios
		TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u", Usuario.class);
		ListaUsuarios = query.getResultList();

		// Cierro la conexion con la base de datos
		em.close();
		emf.close();
	}

	/**
	 * Funcion Action performed.
	 *
	 * @param e el Evento Accionado
	 */
	// Acción para los objetos
	@Override
	public void actionPerformed(ActionEvent e) {
		// Condicion para dirigirnos a la funcion que corresponda
		if (e.getSource() == btnPanel) {
			Panel();
		} else if (e.getSource() == btnEditarTemp) {
			EditarTemporada();
		} else if (e.getSource() == btnTemporadas) {
			botonTemporadas();
		} else if (e.getSource() == btnClasificacion) {
			botonClasificacion();
		} else if (e.getSource() == btnJornadas) {
			botonJornadas();
		} else if (e.getSource() == btnCerrarSesion) {
			CerrarSesion();
		} else if (e.getSource() == btnEquipos) {
			botonEquipos();
		} else if (e.getSource() == btnPanel) {
			Panel();
		} else if (e.getSource() == btnAñadirTemp) {
			AñadirTemporada();
		} else if (e.getSource() == btnEliminarTemp) {
			EliminarTemporada();
		} else if (e.getSource() == btnXML) {
			toXML();
		}
	}

	/**
	 * Funcion para Añadir Temporada.
	 */
	private void AñadirTemporada() {
		// Creo las variables
		AñadirTemporada AT = new AñadirTemporada();
		AT.setVentanaInicio(this);
		// Muestro la ventana Temporadas
		AT.setVisible(true);
		// Desabilito la Ventana de Inicio
		setEnabled(false);
		// Centrar la ventana en el centro de la pantalla
		AT.setLocationRelativeTo(null);

		AT.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				if (AT != null) {
					AT.dispose();
				}
				// Habilitar la ventana de inicio
				setEnabled(true);
				// Establece que la ventana se quede en primer plano
				requestFocus();

				ListaTemporadas = Temporada.cargarTemporadas();
				ordenarTemporadas();

				if (Seleccion.getTemporadaSeleccionada() == null && !tlm.isEmpty()
						&& tlm.firstElement().getEstado().equals("ACTIVA")) {
					Seleccion.setTemporadaSeleccionada(tlm.firstElement());
					Seleccion.setTemporadaPosicion(tlm.indexOf(tlm.firstElement()));
					Seleccion.setTemporadaNumero(tlm.firstElement().getNumero());
					lstTemporada.setSelectedIndex(tlm.indexOf(tlm.firstElement()));
				}

				if (Seleccion.getTemporadaPosicion() != null) {
					lstTemporada.setSelectedIndex(Seleccion.getTemporadaPosicion());
				}
				repaint();

				if (!tlm.isEmpty()) {
					btnXML.setVisible(true);
					btnAñadirTemp.setBounds(79, 348, 186, 46);
					btnEliminarTemp.setBounds(609, 348, 186, 46);
				}
			}
		});
	}

	/**
	 * Método para eliminar una temporada seleccionada y sus imágenes asociadas de
	 * escudos de equipos y jugadores. Se verifica si los equipos de la temporada
	 * están almacenados en {@code ListaEquipos}. Si un equipo no está en
	 * {@code ListaEquipos}, se eliminan su escudo y las imágenes de los jugadores
	 * asociados.
	 */
	private void EliminarTemporada() {
		Boolean otraTemporada = false;

		// Obtengo el índice del elemento seleccionado
		int indiceSeleccionado = lstTemporada.getSelectedIndex();

		// Compruebo si hay algún elemento seleccionado
		if (indiceSeleccionado != -1) {
			// Preguntamos al usuario si está seguro de eliminar la temporada
			int opcion = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que quieres eliminar la temporada?",
					"Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

			if (opcion == JOptionPane.YES_OPTION) {

				// Obtener la temporada a eliminar
				Temporada Temporada = ListaTemporadas.get(indiceSeleccionado);

				// Eliminar las carpetas correspondientes a la temporada en Escudos y Jugadores
				String carpetaEscudos = "ficheros/Escudos/Temporada" + Temporada.getNumero();
				String carpetaJugadores = "ficheros/Jugadores/Temporada" + Temporada.getNumero();

				File carpetaEscudosFile = new File(carpetaEscudos);
				File carpetaJugadoresFile = new File(carpetaJugadores);

				// Eliminar carpeta de escudos
				if (carpetaEscudosFile.exists()) {
					eliminarCarpetaRecursivamente(carpetaEscudosFile);
				}

				// Eliminar carpeta de jugadores
				if (carpetaJugadoresFile.exists()) {
					eliminarCarpetaRecursivamente(carpetaJugadoresFile);
				}

				Logger.nuevoMovimiento("Ha eliminado la Temporada " + Seleccion.getTemporadaSeleccionada().getNumero() + ".");

				try {
					// Crear la conexión a la base de datos
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/CSLeague", "root", "");
					conn.setAutoCommit(false); // Desactivar el modo de autocommit

					// Eliminar registros de temporadaparticipada para la temporada
					String deleteTemporadaParticipadaQuery = "DELETE FROM TemporadaParticipada WHERE Temporada = ?";
					PreparedStatement psDeleteTemporadaParticipada = conn.prepareStatement(deleteTemporadaParticipadaQuery);
					psDeleteTemporadaParticipada.setInt(1, Temporada.getNumero());
					psDeleteTemporadaParticipada.executeUpdate();

					// Eliminar registros de entrenadorcontratado para la temporada
					String deleteEntrenadorContratadoQuery = "DELETE FROM EntrenadorContratado WHERE Temporada = ?";
					PreparedStatement psDeleteEntrenadorContratado = conn.prepareStatement(deleteEntrenadorContratadoQuery);
					psDeleteEntrenadorContratado.setInt(1, Temporada.getNumero());
					psDeleteEntrenadorContratado.executeUpdate();

					// Eliminar registros de estadisticas para la temporada
					String deleteEstadisticasQuery = "DELETE FROM Estadisticas WHERE Temporada = ?";
					PreparedStatement psDeleteEstadisticas = conn.prepareStatement(deleteEstadisticasQuery);
					psDeleteEstadisticas.setInt(1, Temporada.getNumero());
					psDeleteEstadisticas.executeUpdate();

					// Eliminar registros de jugadorcontratado para la temporada
					String deleteJugadorContratadoQuery = "DELETE FROM JugadorContratado WHERE Temporada = ?";
					PreparedStatement psDeleteJugadorContratado = conn.prepareStatement(deleteJugadorContratadoQuery);
					psDeleteJugadorContratado.setInt(1, Temporada.getNumero());
					psDeleteJugadorContratado.executeUpdate();

					// Eliminar registros de jornada para la temporada
					String deleteJornadaQuery = "DELETE FROM Jornada WHERE Temporada = ?";
					PreparedStatement psDeleteJornada = conn.prepareStatement(deleteJornadaQuery);
					psDeleteJornada.setInt(1, Temporada.getNumero());
					psDeleteJornada.executeUpdate();

					// Eliminar temporadas anteriores con el mismo número
					String deleteTemporadaQuery = "DELETE FROM Temporada WHERE Numero = ?";
					PreparedStatement psDeleteTemporada = conn.prepareStatement(deleteTemporadaQuery);
					psDeleteTemporada.setInt(1, Temporada.getNumero());
					psDeleteTemporada.executeUpdate();

					// Confirmar la transacción
					conn.commit();

					// Cerrar las conexiones preparadas
					psDeleteTemporada.close();
					psDeleteTemporadaParticipada.close();
					psDeleteEntrenadorContratado.close();
					psDeleteEstadisticas.close();
					psDeleteJugadorContratado.close();
					psDeleteJornada.close();

					// Cerrar la conexión
					conn.close();

				} catch (SQLException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(this, "Error al eliminar datos de la temporada en la base de datos.",
							"Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				tlm.removeElementAt(indiceSeleccionado);

				// Verificar si la temporada eliminada es la activa
				if (Temporada.getEstado().equals("ACTIVA")) {

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
							String temporadaSeleccionada = comboBox.getSelectedItem().toString();
							int numeroTemporada = Integer.parseInt(temporadaSeleccionada.replaceAll("[\\D]", ""));
							for (Temporada temporada : ListaTemporadas) {
								if (temporada.getNumero() == numeroTemporada) {
									temporada.setEstado("ACTIVA");
									break;
								}
							}
						}
					}

				}
				

				Seleccion.setTemporadaPosicion(null);
				Seleccion.setTemporadaSeleccionada(null);
				lblTemporada.setText("Seleccionar Temporada");
				
				ordenarTemporadas();
				
				if (Seleccion.getTemporadaSeleccionada() == null && !tlm.isEmpty()
						&& tlm.firstElement().getEstado().equals("ACTIVA")) {
					Seleccion.setTemporadaSeleccionada(tlm.firstElement());
					Seleccion.setTemporadaPosicion(tlm.indexOf(tlm.firstElement()));
					Seleccion.setTemporadaNumero(tlm.firstElement().getNumero());
					lstTemporada.setSelectedIndex(tlm.indexOf(tlm.firstElement()));
				}

				if (tlm.isEmpty()) {
					btnXML.setVisible(false);
					btnAñadirTemp.setBounds(204, 348, 186, 46);
					btnEliminarTemp.setBounds(464, 348, 186, 46);
				}

				// Creo las variables
				Inicio I = new Inicio();
				// Muestro la ventana Registro
				I.setVisible(true);
				
				dispose();

			}

		} else {
			// No hay ningún elemento seleccionado
			JOptionPane.showMessageDialog(this, "No hay ningún elemento seleccionado", "Acción Errónea",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	// Método para eliminar una carpeta recursivamente
	private void eliminarCarpetaRecursivamente(File carpeta) {
		if (carpeta.isDirectory()) {
			File[] archivos = carpeta.listFiles();
			if (archivos != null) {
				for (File archivo : archivos) {
					eliminarCarpetaRecursivamente(archivo);
				}
			}
		}
		carpeta.delete();
	}

	/**
	 * Funcion para el Panel.
	 */
	private void Panel() {
		// Creo las variables
		Panel L = new Panel();
		// Muestro la ventana Registro
		L.setVisible(true);
		setEnabled(false);
		// Centrar la ventana en el centro de la pantalla
		L.setLocationRelativeTo(null);

		L.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				if (L != null) {
					L.dispose();
				}
				// Habilitar la ventana de inicio
				setEnabled(true);
				// Establece que la ventana se quede en primer plano
				requestFocus();
			}
		});
	}

	/**
	 * Funcion para Editar Temporada.
	 */
	private void EditarTemporada() {
		if (Seleccion.getTemporadaSeleccionada() == null) {
			JOptionPane.showMessageDialog(this, "No hay ninguna temporada seleccionada", "Temporada Errónea",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		// Creo las variables
		EditarTemp ET = new EditarTemp();
		// Muestro la ventana Registro
		ET.setVisible(true);
		// Centrar la ventana en el centro de la pantalla
		ET.setLocationRelativeTo(null);
		// Cierro la ventana Login
		dispose();
	}

	/**
	 * Funcion para Cerrar Sesion.
	 */
	private void CerrarSesion() {
		// Pregunta al usuario si quiere cerrar sesión
		int opcion = JOptionPane.showConfirmDialog(this, (String) "¿Desea cerrar sesión?", "Cierre de sesión",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);
		switch (opcion) {
		// En el caso de darle a si
		case JOptionPane.YES_OPTION:
			JOptionPane.showMessageDialog(this, (String) "Se ha cerrado sesión. Volviendo a Login.",
					"Cierre de sesión correcto", JOptionPane.INFORMATION_MESSAGE);

			Logger.nuevoMovimiento("Ha cerrado sesión.");

			// Creo las variables
			Login L = new Login();
			// Muestro la ventana Registro
			L.setVisible(true);
			// Centrar la ventana en el centro de la pantalla
			L.setLocationRelativeTo(null);
			// Cierro la ventana Login
			dispose();
			// Se quita el usuario con el que se ha iniciado sesion
			Sesion.setUsuarioActual(null);
			Seleccion.setTemporadaSeleccionada(null);
			Seleccion.setTemporadaNumero(null);
			Seleccion.setTemporadaPosicion(null);
			break;
		// En el caso de darle a no
		case JOptionPane.NO_OPTION:
			JOptionPane.showMessageDialog(this, (String) "La sesión sigue iniciada", "Cierre de sesión cancelado",
					JOptionPane.INFORMATION_MESSAGE);
			break;
		}
	}

	/**
	 * Funcion To XML.
	 */
	@SuppressWarnings("unlikely-arg-type")
	private void toXML() {
		// Establecer el nombre predeterminado de la carpeta

		// Establecer la ruta base de la carpeta
		File basePath = new File("C:\\inetpub\\wwwroot\\BOF\\SitioWeb\\xml");
		File defaultFolder = new File(basePath, "XML_temporada" + Seleccion.getTemporadaNumero());
		File folderToSave = basePath;
		File escudosFolder = new File("ficheros/Escudos");
		File jugadoresFolder = new File("ficheros/Jugadores");

		// Verificar si la carpeta base existe
		if (!basePath.exists()) {
			// Si no existe, utiliza la carpeta por defecto del sistema
			basePath = new File(System.getProperty("user.home"));

			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			fileChooser.setSelectedFile(defaultFolder);
			int userSelection = fileChooser.showSaveDialog(this);

			if (userSelection == JFileChooser.APPROVE_OPTION) {
				folderToSave = fileChooser.getSelectedFile();
			} else {
				return;
			}

		}

		// Crear una carpeta dentro de xmlFolder para los escudos de la temporada
		File escudosTemporadaFolder = new File(folderToSave, "Escudos");
		if (!escudosTemporadaFolder.exists()) {
			escudosTemporadaFolder.mkdirs();
		}

		// Crear una carpeta dentro de xmlFolder para las Fotos de los Jugadores
		File jugadoresTemporadaFolder = new File(folderToSave, "Jugadores");
		if (!jugadoresTemporadaFolder.exists()) {
			jugadoresTemporadaFolder.mkdirs();
		}

		// Obtener la lista de temporadas existentes
		List<Temporada> temporadas = Temporada.cargarTemporadas();
		List<String> temporadaNumbers = new ArrayList<>();
		for (Temporada temporada : temporadas) {
			temporadaNumbers.add(String.valueOf(temporada.getNumero()));
		}

		// Eliminar las carpetas que no están en la lista de temporadas
		eliminarCarpetasNoEnLista(escudosTemporadaFolder, temporadaNumbers);
		eliminarCarpetasNoEnLista(jugadoresTemporadaFolder, temporadaNumbers);

		File temporadaescudosTemporadaFolder = new File(escudosTemporadaFolder, "TemporadaX");
		File temporadajugadoresTemporadaFolder = new File(jugadoresTemporadaFolder, "TemporadaX");

		// Archivo XML para todas las temporadas
		File listatemporadaFile = new File(folderToSave, "csleague.xml");

		try (PrintWriter writer = new PrintWriter(listatemporadaFile)) {
			// Convertir la tabla de clasificación a XML
			writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n"
					+ "<?xml-stylesheet type=\"text/xsl\" href=\"ejemplo.xsl\"?>");
			writer.println("<Temporadas>");
			
			writer.println("	<usuarios>");

			for (Usuario usuario : ListaUsuarios) {

				writer.println("		<usuario>");

				writer.println("			<nombre>" + usuario.getNombre() + "</nombre>");
				writer.println("			<password>" + usuario.getContraseña() + "</password>");
				writer.println("			<privilegiado>" + usuario.getPrivilegiado() + "</privilegiado>");

				writer.println("		</usuario>");
			}

			writer.println("</usuarios>");

			for (Temporada temporada : Temporada.cargarTemporadas()) {

				// Crear una carpeta dentro de xmlFolder para los escudos de la temporada
				temporadaescudosTemporadaFolder = new File(escudosTemporadaFolder, "Temporada" + temporada.getNumero());
				if (!temporadaescudosTemporadaFolder.exists()) {
					temporadaescudosTemporadaFolder.mkdirs();
				}

				// Crear una carpeta dentro de xmlFolder para las Fotos de los Jugadores
				temporadajugadoresTemporadaFolder = new File(jugadoresTemporadaFolder, "Temporada" + temporada.getNumero());
				if (!temporadajugadoresTemporadaFolder.exists()) {
					temporadajugadoresTemporadaFolder.mkdirs();
				}

				writer.println("	<Temporada>");

				writer.println("		<Numero>" + temporada.getNumero() + "</Numero>");
				writer.println("		<Estado>" + temporada.getEstado() + "</Estado>");
				writer.println("		<FechaInicio>");
				writer.println("			<Dia>" + temporada.getFechaInicio().getDia() + "</Dia>");
				writer.println("			<Mes>" + temporada.getFechaInicio().getMes() + "</Mes>");
				writer.println("			<Año>" + temporada.getFechaInicio().getAño() + "</Año>");
				writer.println("		</FechaInicio>");

				writer.println("		<Equipos>");
				for (Equipo equipo : temporada.getListaEquipos()) {
					if (equipo.getNombre().equals("Equipo para Descansar")) {
						continue;
					}

					escudosFolder = new File("ficheros/Escudos/Temporada" + temporada.getNumero());

					String extension = obtenerExtension(equipo.getEscudo());
					String nombreEscudo = equipo.getNombre() + extension;
					File escudoArchivo = new File(escudosFolder, nombreEscudo);
					File destinoArchivo = new File(temporadaescudosTemporadaFolder, nombreEscudo);

					try {
						Files.copy(escudoArchivo.toPath(), destinoArchivo.toPath(), StandardCopyOption.REPLACE_EXISTING);
					} catch (IOException e) {
						e.printStackTrace();
					}

					writer.println("			<Equipo>");

					writer.println("				<Nombre>" + equipo.getNombre() + "</Nombre>");
					writer.println("				<Escudo>Escudos/Temporada" + temporada.getNumero() + "/" + equipo.getNombre()
							+ obtenerExtension(equipo.getEscudo()) + "</Escudo>");
					writer.println("				<Descripcion>" + equipo.getDescripcion() + "</Descripcion>");
					writer.println("				<FechaCreacion>");
					writer.println("					<Dia>" + equipo.getFechaCreacion().getDia() + "</Dia>");
					writer.println("					<Mes>" + equipo.getFechaCreacion().getMes() + "</Mes>");
					writer.println("					<Año>" + equipo.getFechaCreacion().getAño() + "</Año>");
					writer.println("				</FechaCreacion>");
					writer.println("				<Entrenador>");
					writer.println("					<DNI>" + equipo.getEntrenador().getDNI() + "</DNI>");
					writer.println("					<Nombre>" + equipo.getEntrenador().getNombre() + "</Nombre>");
					writer.println("					<Apellido>" + equipo.getEntrenador().getApellido() + "</Apellido>");
					writer.println(
							"					<Nacionalidad>" + equipo.getEntrenador().getNacionalidad() + "</Nacionalidad>");
					writer.println("					<FechaAlta>");
					writer.println("						<Dia>" + equipo.getEntrenador().getFechaAlta().getDia() + "</Dia>");
					writer.println("						<Mes>" + equipo.getEntrenador().getFechaAlta().getMes() + "</Mes>");
					writer.println("						<Año>" + equipo.getEntrenador().getFechaAlta().getAño() + "</Año>");
					writer.println("					</FechaAlta>");
					writer.println("				</Entrenador>");

					writer.println("				<Jugadores>");
					for (Jugador jugador : equipo.getListaJugadores()) {

						jugadoresFolder = new File("ficheros/Jugadores/Temporada" + temporada.getNumero());

						String extensionFoto = obtenerExtension(jugador.getFoto());
						String nombreFoto = equipo.getNombre() + "-" + jugador.getDNI() + extensionFoto;
						File FotoArchivo = new File(jugadoresFolder, nombreFoto);
						File destinoArchivoFoto = new File(temporadajugadoresTemporadaFolder, nombreFoto);

						try {
							Files.copy(FotoArchivo.toPath(), destinoArchivoFoto.toPath(), StandardCopyOption.REPLACE_EXISTING);
						} catch (IOException e) {
							e.printStackTrace();
						}

						writer.println("					<Jugador>");

						writer.println("						<DNI>" + jugador.getDNI() + "</DNI>");
						writer.println("						<Nombre>" + jugador.getNombre() + "</Nombre>");
						writer.println("						<Apellido>" + jugador.getApellido() + "</Apellido>");
						writer.println("						<Nacionalidad>" + jugador.getNacionalidad() + "</Nacionalidad>");
						writer.println(
								"						<Foto>Jugadores/Temporada" + temporada.getNumero() + "/" + equipo.getNombre()
										+ "-" + jugador.getDNI() + obtenerExtension(jugador.getFoto()) + "</Foto>");
						writer.println("						<Rol>" + jugador.getPosicion() + "</Rol>");
						writer.println("						<FechaNacimiento>");
						writer.println("							<Dia>" + jugador.getFechaNacimiento().getDia() + "</Dia>");
						writer.println("							<Mes>" + jugador.getFechaNacimiento().getMes() + "</Mes>");
						writer.println("							<Año>" + jugador.getFechaNacimiento().getAño() + "</Año>");
						writer.println("						</FechaNacimiento>");

						writer.println("					</Jugador>");
					}
					writer.println("				</Jugadores>");

					writer.println("			</Equipo>");
				}
				writer.println("		</Equipos>");

				writer.println("		<Jornadas>");
				for (Jornada jornada : temporada.getListaJornadas()) {
					writer.println("			<Jornada>");

					writer.println("				<Numero>" + jornada.getNumero() + "</Numero>");
					writer.println("				<Fecha>");
					writer.println("					<Dia>" + jornada.getFecha().getDia() + "</Dia>");
					writer.println("					<Mes>" + jornada.getFecha().getMes() + "</Mes>");
					writer.println("					<Año>" + jornada.getFecha().getAño() + "</Año>");
					writer.println("				</Fecha>");
					writer.println("				<Partidos>");
					for (Partido partido : jornada.getListaPartidos()) {
						writer.println("					<Partido>");

						if (!partido.getEquipoLocal().equals("Equipo para Descansar")) {
							writer.println(
									"						<EquipoLocal>" + partido.getEquipoLocal().getNombre() + "</EquipoLocal>");
						} else {
							writer.println("						<EquipoLocal>Descansa</EquipoLocal>");
						}
						if (!partido.getEquipoVisitante().equals("Equipo para Descansar")) {
							writer.println("						<EquipoVisitante>" + partido.getEquipoVisitante().getNombre()
									+ "</EquipoVisitante>");
						} else {
							writer.println("						<EquipoVisitante>Descansa</EquipoVisitante>");
						}
						if (partido.getPuntosLocal() == -1) {
							writer.println("						<PuntosLocal>0</PuntosLocal>");
						} else {
							writer.println("						<PuntosLocal>" + partido.getPuntosLocal() + "</PuntosLocal>");
						}
						if (partido.getPuntosVisitante() == -1) {
							writer.println("						<PuntosVisitante>0</PuntosVisitante>");
						} else {
							writer.println(
									"						<PuntosVisitante>" + partido.getPuntosVisitante() + "</PuntosVisitante>");
						}

						writer.println("					</Partido>");
					}
					writer.println("				</Partidos>");

					writer.println("			</Jornada>");
				}
				writer.println("		</Jornadas>");

				writer.println("	</Temporada>");
			}

			writer.println("</Temporadas>");
			

		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error al exportar a XML de Temporadas", "Error",
					JOptionPane.ERROR_MESSAGE);
		}

		JOptionPane.showMessageDialog(this, "XML exportado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);

		Logger.nuevoMovimiento("Ha exportado en formato XML los datos de la Temporadas.");
	}

	/**
	 * Método para eliminar las carpetas que no están en la lista de temporadas.
	 * 
	 * @param folder           El directorio principal que se examinará.
	 * @param temporadaNumbers La lista de números de temporadas existentes.
	 */
	private void eliminarCarpetasNoEnLista(File folder, List<String> temporadaNumbers) {
		File[] subfolders = folder.listFiles(File::isDirectory);
		if (subfolders != null) {
			for (File subfolder : subfolders) {
				String subfolderName = subfolder.getName();
				if (subfolderName.startsWith("Temporada")) {
					String temporadaNumber = subfolderName.substring("Temporada".length());
					if (!temporadaNumbers.contains(temporadaNumber)) {
						eliminarDirectorioRecursivo(subfolder);
					}
				}
			}
		}
	}

	/**
	 * Método para eliminar un directorio de forma recursiva.
	 * 
	 * @param directory El directorio que se eliminará.
	 */
	private void eliminarDirectorioRecursivo(File directory) {
		File[] files = directory.listFiles();
		if (files != null) {
			for (File file : files) {
				if (file.isDirectory()) {
					eliminarDirectorioRecursivo(file);
				} else {
					if (!file.delete()) {
						System.err.println("No se pudo eliminar el archivo: " + file.getAbsolutePath());
					}
				}
			}
		}
		if (!directory.delete()) {
			System.err.println("No se pudo eliminar el directorio: " + directory.getAbsolutePath());
		}
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
	 * Funcion para el Boton Temporadas.
	 */
	private void botonTemporadas() {
		// Creo las variables
		Inicio T = new Inicio();
		// Muestro la ventana Registro
		T.setVisible(true);
		// Centrar la ventana en el centro de la pantalla
		T.setLocationRelativeTo(null);
		// Cierro la ventana Login
		dispose();
	}

	/**
	 * Funcion para el Boton Clasificacion.
	 */
	private void botonClasificacion() {
		if (Seleccion.getTemporadaSeleccionada() == null) {
			JOptionPane.showMessageDialog(this, "No hay ninguna temporada seleccionada", "Temporada Errónea",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		// Creo las variables
		Clasificacion C = new Clasificacion();
		// Muestro la ventana Registro
		C.setVisible(true);
		// Centrar la ventana en el centro de la pantalla
		C.setLocationRelativeTo(null);
		// Cierro la ventana Login
		dispose();
	}

	/**
	 * Funcion para el Boton Jornadas.
	 */
	private void botonJornadas() {
		if (Seleccion.getTemporadaSeleccionada() == null) {
			JOptionPane.showMessageDialog(this, "No hay ninguna temporada seleccionada", "Temporada Errónea",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		// Creo las variables
		Jornadas J = new Jornadas();
		// Muestro la ventana Registro
		J.setVisible(true);
		// Centrar la ventana en el centro de la pantalla
		J.setLocationRelativeTo(null);
		// Cierro la ventana Login
		dispose();
	}

	/**
	 * Funcion para el Boton Equipos.
	 */
	private void botonEquipos() {
		if (Seleccion.getTemporadaSeleccionada() == null) {
			JOptionPane.showMessageDialog(this, "No hay ninguna temporada seleccionada", "Temporada Errónea",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		// Creo las variables
		Equipos E = new Equipos();
		// Muestro la ventana Registro
		E.setVisible(true);
		// Centrar la ventana en el centro de la pantalla
		E.setLocationRelativeTo(null);
		// Cierro la ventana Login
		dispose();
	}

	/**
	 * Funcion para que se Añada una Temporada a Lista.
	 *
	 * @param nuevaTemporada la Nueva Temporada
	 */
	public void añadirTemporada(Temporada nuevaTemporada) {
		// TODO Auto-generated method stub
		tlm.addElement(nuevaTemporada);
	}

	/**
	 * Funcion que Guarda los cambios de Seleccion de las Listas de Temporadas.
	 *
	 * @param e el Evento de Seleccion de la Lista
	 */
	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		// Obtener el índice seleccionado en la lista activa
		int selectedIndex = lstTemporada.getSelectedIndex();

		if (selectedIndex != -1) {
			// Obtener el Alumno seleccionado
			Temporada selectedTemporada = tlm.getElementAt(selectedIndex);

			// Se llama a los elementos de la clase Fecha
			lblTemporada.setText("Temporada " + selectedTemporada.getNumero());

			// Se guarda el usuario con el que se ha iniciado sesion
			Seleccion.setTemporadaPosicion((selectedIndex));
			Seleccion.setTemporadaNumero(tlm.getElementAt(selectedIndex).getNumero());
			Seleccion.setTemporadaSeleccionada(tlm.getElementAt(selectedIndex));

			if (Seleccion.getTemporadaSeleccionada().getEstado().equals("FINALIZADA")) {
				btnEditarTemp.setVisible(false);
				btnPanel.setBounds(864, 22, 140, 45);
			} else {
				btnEditarTemp.setVisible(true);
				btnPanel.setBounds(684, 22, 140, 45);
			}
		}
	}

	/**
	 * Funcion para Ordenar las Temporadas de la Lista.
	 */
	public void ordenarTemporadas() {
		Collections.sort(ListaTemporadas, new Comparator<Temporada>() {

			@Override
			public int compare(Temporada t1, Temporada t2) {
				// Comparar por estado
				int estadoComparison = t1.getEstado().compareTo(t2.getEstado());
				if (estadoComparison != 0) {
					return estadoComparison;
				}

				// Si tienen el mismo estado, comparar por nombre
				return t1.getEstado().compareTo(t2.getEstado());
			}
		});

		// Limpiar el DefaultListModel antes de agregar las temporadas ordenadas
		tlm.clear();

		// Agregar las temporadas ordenadas al DefaultListModel
		for (Temporada temporada : ListaTemporadas) {
			tlm.addElement(temporada);
		}
	}

	/**
	 * Funcion para la Apertura de la Ventana.
	 *
	 * @param e el Evento Accionado
	 */
	@Override
	public void windowOpened(WindowEvent e) {
		// Si el usuario actual NO es un usuario con
		if (Sesion.getUsuarioActual() != null) {
			if (!Sesion.getUsuarioActual().getPrivilegiado()) {
				// Oculta el botón de logs
				btnPanel.setVisible(false);
				// Oculta el botón de editar temporada
				btnEditarTemp.setVisible(false);
				// Oculta el botón de logs
				btnAñadirTemp.setVisible(false);
				// Oculta el botón de editar temporada
				btnEliminarTemp.setVisible(false);
			}
		}
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
}