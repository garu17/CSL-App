package aplicacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.border.LineBorder;

import definicion.Equipo;
import definicion.Jugador;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

/**
 * La Clase MostrarEquipo.
 * 
 */
public class MostrarEquipo extends JFrame implements ActionListener {

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

	/** El Lable de Entrenador. */
	private JLabel lblEntrenador;

	/** El Panel de Entrenador. */
	private JPanel panelEntrenador;

	/** El Label del Nombre del Entrenador. */
	private JLabel lblEntrenadorNombre;

	/** El Label del Apellido del Entrenador. */
	private JLabel lblEntrenadorApellido;

	/** El Label de la Nacionalidad del Entrenador. */
	private JLabel lblEntrenadorNacionalidad;

	/** El Label de la Fecha de Alta del Entrenador. */
	private JLabel lblEntrenadorFechaAlta;

	/** El Label de la Descripcion. */
	private JLabel lblDescripcion;

	/** El TextField de la Descripcion. */
	private JTextArea textDescripcion;

	/** El Scroll Pane de la Descripcion. */
	private JScrollPane scrollPane;

	/** El Scroll Pane de la Lista de Jugadores. */
	private JScrollPane scrollPanelst;

	/** El Label del DNI del Entrenador. */
	private JLabel lblEntrenadorDNI;

	/** El Label del Escudo. */
	private JLabel lblEscudo;

	/** El Label de los Datos del DNI del Entrenador. */
	private JLabel lblEntrenadorDatosDNI;

	/** El Panel de Jugadores. */
	private JPanel panelJugadores;

	/** El Label del Nombre de los Jugadores. */
	private JLabel lblJugadoresNombre;

	/** El Label del Apellido de los Jugadores. */
	private JLabel lblJugadoresApellido;

	/** El Label de la Nacionalidad de los Jugadores. */
	private JLabel lblJugadoresrNacionalidad;

	/** El Label de la Posicion de los Jugadores. */
	private JLabel lblJugadoresRol;

	/** El Label del DNI de los Jugadores. */
	private JLabel lblJugadoresDNI;

	/** El Equipo Seleccionado de la Lista */
	private Equipo equipoSeleccionado;

	/** El Label de los Datos del Nombre del Entrenador. */
	private JLabel lblEntrenadorDatosNombre;

	/** El Label de los Datos del Apellido del Entrenador. */
	private JLabel lblEntrenadorDatosApellido;

	/** El Label de los Datos de la Nacionalidad del Entrenador. */
	private JLabel lblEntrenadorDatosNacionalidad;

	/** El Label de los Datos del Dia de la Fecha del Alta del Entrenador. */
	private JLabel lblEntrenadorDatosFechaDia;

	/** El Label de los Datos del Mes de la Fecha del Alta del Entrenador. */
	private JLabel lblEntrenadorDatosFechaMes;

	/** El Label de los Datos del Año de la Fecha del Alta del Entrenador. */
	private JLabel lblEntrenadorDatosFechaAño;

	/**
	 * El Label de la Primera Barra de la Fecha de Alta del Entrenador.
	 */
	private JLabel lblEntrenadorDatosFechaBarra1;

	/**
	 * El Label de la Segunda Barra de la Fecha de Alta del Entrenador.
	 */
	private JLabel lblEntrenadorDatosFechaBarra2;

	/**
	 * El Label para la Fecha de Nacimiento de los Jugadores.
	 */
	private JLabel lblJugadoresFechaNac;

	/**
	 * El Label para la Fecha de Creación.
	 */
	private JLabel lblFechaCreacion;

	/**
	 * El panel de creación.
	 */
	private JPanel panelCreacion;

	/**
	 * El Label para el Día de Creación.
	 */
	private JLabel lblCreacionDia;

	/**
	 * El Label para el Mes de Creación.
	 */
	private JLabel lblCreacionMes;

	/**
	 * El Label para el Año de Creación.
	 */
	private JLabel lblCreacionAño;

	/**
	 * El Label de la Primera Barra de la Fecha de Creación.
	 */
	private JLabel lblCreacionBarra1;

	/**
	 * El Label de la Segunda Barra de la Fecha de Creación.
	 */
	private JLabel lblCreacionBarra2;

	/**
	 * El Label para la Foto de los Jugadores.
	 */
	private JLabel lblJugadoresFoto;


	/**
	 * Ejecuta la aplicacion.
	 *
	 * @param args los Argumentos
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MostrarEquipo frame = new MostrarEquipo();
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
	public MostrarEquipo() {
		// Imagen de la aplicación
		setIconImage(new ImageIcon(getClass().getResource("/Imagenes/CSL.png")).getImage());
		setResizable(false);
		setTitle("Informacion Equipo");
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
		btnVolver.setBackground(new Color(29, 29, 27));
		btnVolver.setFont(new Font("Dialog", Font.BOLD, 13));
		btnVolver.setFocusable(false);
		btnVolver.setBounds(225, 648, 85, 40);
		contentPane.add(btnVolver);

		lblNombreEquipo = new JLabel("Nombre del Equipo");
		lblNombreEquipo.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreEquipo.setFont(new Font("Dialog", Font.BOLD, 25));
		lblNombreEquipo.setBounds(10, 10, 516, 33);
		contentPane.add(lblNombreEquipo);

		lblListaJugadores = new JLabel("Lista de Jugadores:");
		lblListaJugadores.setFont(new Font("Dialog", Font.BOLD, 18));
		lblListaJugadores.setBounds(42, 243, 200, 33);
		contentPane.add(lblListaJugadores);

		jlm = new DefaultListModel<>();

		lstJugadores = new JList<Jugador>();
		lstJugadores.setFont(new Font("Dialog", Font.BOLD, 10));
		lstJugadores.setBorder(new LineBorder(new Color(0, 0, 0)));
		lstJugadores.setCellRenderer(new JugadorListCellRenderer());
		lstJugadores.setBounds(42, 278, 437, 75);
		// asocio el DefaultListModel a la lista
		lstJugadores.setModel(jlm);
		contentPane.add(lstJugadores);

		lblEntrenador = new JLabel("Entrenador:");
		lblEntrenador.setFont(new Font("Dialog", Font.BOLD, 18));
		lblEntrenador.setBounds(42, 126, 234, 33);
		contentPane.add(lblEntrenador);

		panelEntrenador = new JPanel();
		panelEntrenador.setLayout(null);
		panelEntrenador.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelEntrenador.setBackground(Color.WHITE);
		panelEntrenador.setBounds(42, 169, 437, 54);
		contentPane.add(panelEntrenador);

		lblEntrenadorNombre = new JLabel("Nombre:");
		lblEntrenadorNombre.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblEntrenadorNombre.setBounds(81, 10, 51, 13);
		panelEntrenador.add(lblEntrenadorNombre);

		lblEntrenadorApellido = new JLabel("Apellido:");
		lblEntrenadorApellido.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblEntrenadorApellido.setBounds(156, 10, 51, 13);
		panelEntrenador.add(lblEntrenadorApellido);

		lblEntrenadorNacionalidad = new JLabel("Nacionalidad:");
		lblEntrenadorNacionalidad.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblEntrenadorNacionalidad.setBounds(226, 10, 68, 13);
		panelEntrenador.add(lblEntrenadorNacionalidad);

		lblEntrenadorFechaAlta = new JLabel("Fecha de Alta:");
		lblEntrenadorFechaAlta.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblEntrenadorFechaAlta.setBounds(317, 10, 74, 13);
		panelEntrenador.add(lblEntrenadorFechaAlta);

		lblEntrenadorDNI = new JLabel("DNI:");
		lblEntrenadorDNI.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblEntrenadorDNI.setBounds(10, 10, 30, 13);
		panelEntrenador.add(lblEntrenadorDNI);

		lblEntrenadorDatosDNI = new JLabel("...");
		lblEntrenadorDatosDNI.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblEntrenadorDatosDNI.setBounds(10, 33, 68, 13);
		panelEntrenador.add(lblEntrenadorDatosDNI);

		lblDescripcion = new JLabel("Descripción:");
		lblDescripcion.setFont(new Font("Dialog", Font.BOLD, 18));
		lblDescripcion.setBounds(42, 411, 200, 33);
		contentPane.add(lblDescripcion);

		// Reemplaza la declaración de JTextArea existente con la siguiente
		textDescripcion = new JTextArea();
		textDescripcion.setFont(new Font("Dialog", Font.PLAIN, 13));
		textDescripcion.setEditable(false);
		textDescripcion.setBounds(42, 484, 351, 50);
		textDescripcion.setLineWrap(true);
		textDescripcion.setWrapStyleWord(true);

		// Crea un JScrollPane y envuelve el JTextArea con él
		scrollPane = new JScrollPane(textDescripcion);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(42, 454, 437, 70);
		// Añade el JScrollPane al contenido en lugar del JTextArea directamente
		contentPane.add(scrollPane);

		// Crea un JScrollPane y envuelve el lstJugadores con él
		scrollPanelst = new JScrollPane(lstJugadores);
		scrollPanelst.setBorder(null);
		scrollPanelst.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPanelst.setBounds(42, 318, 437, 75);
		// Añade el JScrollPane al contenido en lugar del JTextArea directamente
		contentPane.add(scrollPanelst);

		lblEscudo = new JLabel("");
		lblEscudo.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblEscudo.setBounds(225, 53, 80, 74);
		contentPane.add(lblEscudo);

		panelJugadores = new JPanel();
		panelJugadores.setLayout(null);
		panelJugadores.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelJugadores.setBackground(Color.WHITE);
		panelJugadores.setBounds(42, 285, 437, 33);
		contentPane.add(panelJugadores);

		lblJugadoresNombre = new JLabel("Nombre:");
		lblJugadoresNombre.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblJugadoresNombre.setBounds(108, 10, 43, 13);
		panelJugadores.add(lblJugadoresNombre);

		lblJugadoresApellido = new JLabel("Apellido:");
		lblJugadoresApellido.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblJugadoresApellido.setBounds(161, 10, 50, 13);
		panelJugadores.add(lblJugadoresApellido);

		lblJugadoresrNacionalidad = new JLabel("Nacionalidad:");
		lblJugadoresrNacionalidad.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblJugadoresrNacionalidad.setBounds(221, 10, 68, 13);
		panelJugadores.add(lblJugadoresrNacionalidad);

		lblJugadoresRol = new JLabel("Rol:");
		lblJugadoresRol.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblJugadoresRol.setBounds(299, 10, 20, 13);
		panelJugadores.add(lblJugadoresRol);

		lblJugadoresDNI = new JLabel("DNI/NIE:");
		lblJugadoresDNI.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblJugadoresDNI.setBounds(48, 10, 50, 13);
		panelJugadores.add(lblJugadoresDNI);
		
		lblJugadoresFoto = new JLabel("Foto:");
		lblJugadoresFoto.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblJugadoresFoto.setBounds(10, 10, 28, 13);
		panelJugadores.add(lblJugadoresFoto);
		
		lblJugadoresFechaNac = new JLabel("Fecha Nacimiento:");
		lblJugadoresFechaNac.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblJugadoresFechaNac.setBounds(329, 10, 98, 13);
		panelJugadores.add(lblJugadoresFechaNac);

		lblEntrenadorDatosNombre = new JLabel((String) null);
		lblEntrenadorDatosNombre.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblEntrenadorDatosNombre.setBounds(81, 33, 68, 13);
		panelEntrenador.add(lblEntrenadorDatosNombre);

		lblEntrenadorDatosApellido = new JLabel((String) null);
		lblEntrenadorDatosApellido.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblEntrenadorDatosApellido.setBounds(156, 33, 68, 13);
		panelEntrenador.add(lblEntrenadorDatosApellido);

		lblEntrenadorDatosNacionalidad = new JLabel((String) null);
		lblEntrenadorDatosNacionalidad.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblEntrenadorDatosNacionalidad.setBounds(226, 33, 81, 13);
		panelEntrenador.add(lblEntrenadorDatosNacionalidad);

		lblEntrenadorDatosFechaDia = new JLabel((String) null);
		lblEntrenadorDatosFechaDia.setBorder(null);
		lblEntrenadorDatosFechaDia.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblEntrenadorDatosFechaDia.setBounds(317, 33, 24, 13);
		panelEntrenador.add(lblEntrenadorDatosFechaDia);

		lblEntrenadorDatosFechaMes = new JLabel((String) null);
		lblEntrenadorDatosFechaMes.setBorder(null);
		lblEntrenadorDatosFechaMes.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblEntrenadorDatosFechaMes.setBounds(355, 33, 24, 13);
		panelEntrenador.add(lblEntrenadorDatosFechaMes);

		lblEntrenadorDatosFechaAño = new JLabel((String) null);
		lblEntrenadorDatosFechaAño.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblEntrenadorDatosFechaAño.setBounds(391, 33, 38, 13);
		panelEntrenador.add(lblEntrenadorDatosFechaAño);

		lblEntrenadorDatosFechaBarra1 = new JLabel("/");
		lblEntrenadorDatosFechaBarra1.setBounds(341, 33, 14, 13);
		panelEntrenador.add(lblEntrenadorDatosFechaBarra1);
		lblEntrenadorDatosFechaBarra1.setHorizontalAlignment(SwingConstants.CENTER);
		lblEntrenadorDatosFechaBarra1.setFont(new Font("Tahoma", Font.BOLD, 10));

		lblEntrenadorDatosFechaBarra2 = new JLabel("/");
		lblEntrenadorDatosFechaBarra2.setHorizontalAlignment(SwingConstants.CENTER);
		lblEntrenadorDatosFechaBarra2.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblEntrenadorDatosFechaBarra2.setBounds(377, 33, 14, 13);
		panelEntrenador.add(lblEntrenadorDatosFechaBarra2);
		
		lblFechaCreacion = new JLabel("Fecha de Creación:");
		lblFechaCreacion.setFont(new Font("Dialog", Font.BOLD, 18));
		lblFechaCreacion.setBounds(42, 534, 234, 33);
		contentPane.add(lblFechaCreacion);
		
		panelCreacion = new JPanel();
		panelCreacion.setLayout(null);
		panelCreacion.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelCreacion.setBackground(Color.WHITE);
		panelCreacion.setBounds(42, 573, 437, 54);
		contentPane.add(panelCreacion);
		
		lblCreacionDia = new JLabel((String) null);
		lblCreacionDia.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblCreacionDia.setBorder(null);
		lblCreacionDia.setBounds(10, 20, 24, 13);
		panelCreacion.add(lblCreacionDia);
		
		lblCreacionMes = new JLabel((String) null);
		lblCreacionMes.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblCreacionMes.setBorder(null);
		lblCreacionMes.setBounds(48, 20, 24, 13);
		panelCreacion.add(lblCreacionMes);
		
		lblCreacionAño = new JLabel((String) null);
		lblCreacionAño.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblCreacionAño.setBounds(84, 20, 38, 13);
		panelCreacion.add(lblCreacionAño);
		
		lblCreacionBarra1 = new JLabel("/");
		lblCreacionBarra1.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreacionBarra1.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblCreacionBarra1.setBounds(34, 20, 14, 13);
		panelCreacion.add(lblCreacionBarra1);
		
		lblCreacionBarra2 = new JLabel("/");
		lblCreacionBarra2.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreacionBarra2.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblCreacionBarra2.setBounds(70, 20, 14, 13);
		panelCreacion.add(lblCreacionBarra2);

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
			dispose();
		}
	}

	/**
	 * Funcion para Enviar el Equipo Seleccionado.
	 *
	 * @param equipo el Equipo
	 */
	public void EnviarEquipo(Equipo equipo) {

		this.equipoSeleccionado = equipo; // Guarda el equipo seleccionado
		VisualizarEquipo();
	}

	/**
	 * Funcion para Visualizar el Equipo Seleccionado.
	 */
	public void VisualizarEquipo() {

		lblNombreEquipo.setText(equipoSeleccionado.getNombre());
		// Cargar y mostrar el escudo
		String escudoPath = equipoSeleccionado.getEscudo();
		if (escudoPath != null && !escudoPath.isEmpty()) {
			ImageIcon escudoIcon = new ImageIcon(escudoPath);
			Image escudoImage = escudoIcon.getImage().getScaledInstance(lblEscudo.getWidth(), lblEscudo.getHeight(),
					Image.SCALE_SMOOTH);
			lblEscudo.setIcon(new ImageIcon(escudoImage));
		} else {
			lblEscudo.setIcon(new ImageIcon("src/Imagenes/BOFlogo.png"));
		}
		lblEntrenadorDatosDNI.setText(equipoSeleccionado.getEntrenador().getDNI());
		lblEntrenadorDatosNombre.setText(equipoSeleccionado.getEntrenador().getNombre());
		lblEntrenadorDatosApellido.setText(equipoSeleccionado.getEntrenador().getApellido());
		lblEntrenadorDatosNacionalidad.setText(equipoSeleccionado.getEntrenador().getNacionalidad());
		lblEntrenadorDatosFechaDia.setText("" + equipoSeleccionado.getEntrenador().getFechaAlta().getDia());
		lblEntrenadorDatosFechaMes.setText("" + equipoSeleccionado.getEntrenador().getFechaAlta().getMes());
		lblEntrenadorDatosFechaAño.setText("" + equipoSeleccionado.getEntrenador().getFechaAlta().getAño());

		lblCreacionDia.setText(""+equipoSeleccionado.getFechaCreacion().getDia());
		lblCreacionMes.setText(""+equipoSeleccionado.getFechaCreacion().getMes());
		lblCreacionAño.setText(""+equipoSeleccionado.getFechaCreacion().getAño());

		// Limpiar el DefaultListModel antes de agregar nuevos jugadores
		jlm.clear();

		// Añadir los jugadores al DefaultListModel
		for (Jugador jugador : equipoSeleccionado.getListaJugadores()) {
			jlm.addElement(jugador);
		}

		textDescripcion.setText(equipoSeleccionado.getDescripcion());
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
				// Si no se encuentra la ruta de la imagen, establecer el ícono como nulo
				setIcon(null);
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
