package aplicacion;

//Eventos importados
import java.awt.EventQueue;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import definicion.Equipo;
import definicion.Estadisticas;
import definicion.Logger;
import definicion.Seleccion;
import definicion.Sesion;
import definicion.Temporada;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.ActionEvent;
import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.border.LineBorder;

/**
 * La Clase Clasificacion.
 */
public class Clasificacion extends JFrame implements ActionListener, WindowListener {

	/** La Constante serialVersionUID generada para identificarse. */
	private static final long serialVersionUID = 1L;

	/** El Content Pane principal. */
	private JPanel contentPane;

	/** El Content Pane Superior. */
	private JPanel panelSuperior;

	/** El Boton de Cerrar Sesion. */
	private JButton btnCerrarSesion;

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

	/** La Tabla de Clasificacion. */
	private JTable tableClasificacion;

	/** El Modelo de la Tabla de Clasificacion. */
	private DefaultTableModel ctm;

	/** La Lista de Equipos de la Temporada. */
	private List<Equipo> ListaEquipos;

	/** El Boton de Exportar PDF. */
	private JButton btnExportarPDF;

	/**
	 * Ejecuta la aplicacion.
	 *
	 * @param args los Argumentos
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Clasificacion frame = new Clasificacion();
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
	public Clasificacion() {
		// Imagen de la aplicación
		setIconImage(new ImageIcon(getClass().getResource("/Imagenes/CSL.png")).getImage());
		// Añado acción listener
		this.addWindowListener(this);
		// Eliminar posibilidad de reescalar la ventana
		setResizable(false);
		// Establece el título
		setTitle("Clasificacion");
		// Establece que hacer cuando se cierra la ventana
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Establece posición y tamaño
		setBounds(100, 100, 1050, 550);
		// Centrar la ventana en el centro de la pantalla
		setLocationRelativeTo(null);
		// Crea un panel
		contentPane = new JPanel();
		// Establece los bordes del panel
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		// Hacer el panel absoluto
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Crea un panel
		panelSuperior = new JPanel();
		// Propiedades del panel
		panelSuperior.setBackground(new Color(29, 29, 27));
		panelSuperior.setBounds(0, 0, 1036, 90);
		contentPane.add(panelSuperior);
		panelSuperior.setLayout(null);

		// Crea un botón
		btnCerrarSesion = new JButton("Cerrar Sesión");
		btnCerrarSesion.setFont(new Font("Dialog", Font.BOLD, 12));
		// Propiedades del botón
		btnCerrarSesion.addActionListener(this);
		btnCerrarSesion.setBackground(new Color(230, 230, 230));
		btnCerrarSesion.setFocusable(false);
		btnCerrarSesion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCerrarSesion.setBorder(null);
		btnCerrarSesion.setBounds(22, 22, 119, 45);
		panelSuperior.add(btnCerrarSesion);

		// Crea un botón
		btnPanel = new JButton("Panel Administracion");
		btnPanel.setFont(new Font("Dialog", Font.BOLD, 12));
		// Añade accion de tipo listener al boton
		btnPanel.addActionListener(this);
		// Propiedades del boton
		btnPanel.setBackground(new Color(230, 230, 230));
		btnPanel.setBorder(null);
		btnPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPanel.setFocusable(false);
		btnPanel.setBounds(684, 22, 140, 45);
		panelSuperior.add(btnPanel);

		// Crea un botón
		btnEditarTemp = new JButton("Editar Temporada");
		btnEditarTemp.setForeground(new Color(255, 255, 255));
		btnEditarTemp.setFont(new Font("Dialog", Font.BOLD, 13));
		// Propiedades del botón
		btnEditarTemp.addActionListener(this);
		btnEditarTemp.setBackground(new Color(56, 56, 52));
		btnEditarTemp.setForeground(new Color(255, 255, 9));
		btnEditarTemp.setBorder(null);
		btnEditarTemp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEditarTemp.setFocusable(false);
		btnEditarTemp.setBounds(848, 22, 156, 45);
		panelSuperior.add(btnEditarTemp);

		// Crea un label
		lblTemporada = new JLabel("Temporada X");
		lblTemporada.setForeground(new Color(255, 255, 255));
		// Propiedades del label
		lblTemporada.setHorizontalAlignment(SwingConstants.CENTER);
		lblTemporada.setFont(new Font("Dialog", Font.BOLD, 25));
		lblTemporada.setBounds(303, 22, 429, 45);
		panelSuperior.add(lblTemporada);

		// Crea un panel
		panelLateral = new JPanel();
		// Propiedades del panel
		panelLateral.setBackground(new Color(128, 255, 255));
		panelLateral.setBounds(0, 88, 158, 435);
		contentPane.add(panelLateral);
		panelLateral.setLayout(null);

		// Crea un botón
		btnTemporadas = new JButton("Temporadas");
		btnTemporadas.setFont(new Font("Dialog", Font.BOLD, 15));
		// Propiedades del botón
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
		// Propiedades del botón
		btnClasificacion.setEnabled(false);
		btnClasificacion.addActionListener(this);
		btnClasificacion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnClasificacion.setFocusable(false);
		btnClasificacion.setForeground(new Color(255, 255, 9));
		btnClasificacion.setBackground(new Color(56, 56, 52));
		btnClasificacion.setBounds(0, 106, 158, 108);
		panelLateral.add(btnClasificacion);

		// Crea un botón
		btnJornadas = new JButton("Jornadas");
		btnJornadas.setFont(new Font("Dialog", Font.BOLD, 15));
		// Propiedades del botón
		btnJornadas.addActionListener(this);
		btnJornadas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnJornadas.setFocusable(false);
		btnJornadas.setForeground(new Color(255, 255, 9));
		btnJornadas.setBackground(new Color(56, 56, 52));
		btnJornadas.setBounds(0, 213, 158, 108);
		panelLateral.add(btnJornadas);

		// Crea un botón
		btnEquipos = new JButton("Equipos");
		btnEquipos.setFont(new Font("Dialog", Font.BOLD, 15));
		// Propiedades del botón
		btnEquipos.addActionListener(this);
		btnEquipos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEquipos.setFocusable(false);
		btnEquipos.setForeground(new Color(255, 255, 9));
		btnEquipos.setBackground(new Color(56, 56, 52));
		btnEquipos.setBounds(0, 318, 158, 108);
		panelLateral.add(btnEquipos);

		// Crea un panel
		panelCentral = new JPanel();
		// Propiedades del panel
		panelCentral.setFocusable(false);
		panelCentral.setBounds(159, 91, 874, 432);
		panelCentral.setBackground(new Color(220, 220, 220));
		contentPane.add(panelCentral);
		panelCentral.setLayout(null);

		ctm = new DefaultTableModel();
		ctm.addColumn("Posición");
		ctm.addColumn("Equipo");
		ctm.addColumn("P.Totales");
		ctm.addColumn("P.Jugadas");
		ctm.addColumn("P.Ganadas");
		ctm.addColumn("P.Perdidas");
		ctm.addColumn("R.Diferencia");

		tableClasificacion = new JTable();
		tableClasificacion.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		tableClasificacion.setEnabled(false);
		tableClasificacion.setBounds(74, 55, 725, 251);
		// Asignar el modelo de tabla a la JTable
		tableClasificacion.setModel(ctm);
		panelCentral.add(tableClasificacion);

		btnExportarPDF = new JButton("Exportar PDF");
		btnExportarPDF.setFont(new Font("Dialog", Font.BOLD, 13));
		btnExportarPDF.addActionListener(this);
		btnExportarPDF.setFocusable(false);
		btnExportarPDF.setBorder(null);
		btnExportarPDF.setForeground(Color.white);
		btnExportarPDF.setBackground(new Color(29, 29, 27));
		btnExportarPDF.setBounds(380, 341, 113, 63);
		panelCentral.add(btnExportarPDF);

		// Obtener la lista de equipos
		if (Seleccion.getTemporadaSeleccionada() != null) {
			lblTemporada.setText("Temporada " + Seleccion.getTemporadaNumero());
			ListaEquipos = Seleccion.getTemporadaSeleccionada().getListaEquipos();

			llenarTabla();
		}

		if (Seleccion.getTemporadaSeleccionada().getEstado().equals("FINALIZADA")) {
			btnEditarTemp.setVisible(false);
			btnPanel.setBounds(864, 22, 140, 45);
		}

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
	// Acción para los objetos
	@Override
	public void actionPerformed(ActionEvent e) {
		// Condición para dirigirnos a la funcion que corresponda
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
		} else if (e.getSource() == btnExportarPDF) {
			toPDF();
		}
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
		if (Seleccion.getTemporadaSeleccionada().getEstado().equals("FINALIZADA")) {
			JOptionPane.showMessageDialog(this, "La temporada esta Finalizada no se puede editar", "Temporada Errónea",
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
		// Pregunta al usuario si quiere cerrar sesion
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
	 * Funcion para Llenar la Tabla.
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
		Object[] primeraFila = { "Posición", "Equipo", "P.Totales", "P.Jugadas", "P.Ganadas",
				"P.Perdidas", "R.Diferencia" };
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
	 * Funcion To PDF.
	 */
	private void toPDF() {
	    // Definir el nombre por defecto del archivo PDF
	    String defaultFileName = "ClasificacionTemporada" + Seleccion.getTemporadaNumero() + ".pdf";

	    try {
	        // Crear un archivo temporal para el PDF
	        File tempFile = File.createTempFile("ClasificacionTemporada" + Seleccion.getTemporadaNumero(), ".pdf");
	        String tempFilePath = tempFile.getAbsolutePath();

	        // Crear el contenido del documento PDF
	        try (PDDocument document = new PDDocument()) {
	            PDPage page = new PDPage(PDRectangle.A4);
	            document.addPage(page);

	            PDPageContentStream contentStream = new PDPageContentStream(document, page);
	            contentStream.setFont(PDType1Font.HELVETICA, 12);

	            // Crear la tabla para la clasificación
	            float margin = 50;
	            float yStart = page.getMediaBox().getHeight() - margin;
	            float tableWidth = page.getMediaBox().getWidth() - 2 * margin;

	            int numberOfRows = tableClasificacion.getRowCount();
	            int numberOfColumns = tableClasificacion.getColumnCount();
	            float[] columnWidths = new float[numberOfColumns];
	            for (int i = 0; i < numberOfColumns; i++) {
	                columnWidths[i] = tableWidth / (float) numberOfColumns;
	            }

	            // Dibujar encabezados de columna
	            float yPosition = yStart;
	            float rowHeight = 20;

	            // Dibujar filas de la tabla
	            for (int row = 0; row < numberOfRows; row++) {
	                contentStream.beginText();
	                contentStream.newLineAtOffset(margin, yPosition - rowHeight);
	                for (int column = 0; column < numberOfColumns; column++) {
	                    String cellValue = String.valueOf(tableClasificacion.getValueAt(row, column));
	                    contentStream.showText(cellValue);

	                    // Ajustar el desplazamiento horizontal solo después de la segunda columna
	                    if (column == 1) {
	                        float xOffset = 25; // Ajusta este valor según el espacio deseado entre la segunda y tercera columna
	                        contentStream.newLineAtOffset(xOffset, 0);
	                    }

	                    contentStream.newLineAtOffset(columnWidths[column], 0);
	                }
	                contentStream.endText();
	                yPosition -= rowHeight;
	            }

	            contentStream.close();

	            // Guardar el documento PDF en el archivo temporal
	            document.save(tempFilePath);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        // Abrir un cuadro de diálogo de selección de archivos para que el usuario elija la ubicación donde guardar el PDF
	        JFileChooser fileChooser = new JFileChooser();
	        fileChooser.setSelectedFile(new File(defaultFileName));
	        int result = fileChooser.showSaveDialog(this);
	        if (result == JFileChooser.APPROVE_OPTION) {
	            File selectedFile = fileChooser.getSelectedFile();
	            if (selectedFile.exists()) { // Verificar si el archivo ya existe
	                selectedFile.delete(); // Eliminar el archivo existente
	            }
	            // Copiar el archivo temporal al destino seleccionado por el usuario
	            tempFile.renameTo(selectedFile);
	            JOptionPane.showMessageDialog(this, "PDF exportado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	        } else {
	            // Eliminar el archivo temporal si el usuario cancela la selección
	            tempFile.delete();
	        }

	        Logger.nuevoMovimiento("Ha exportado en formato PDF la clasificación de la Temporada " + Seleccion.getTemporadaSeleccionada().getNumero() + ".");
	    } catch (IOException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(this, "Error al exportar a PDF", "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}


	/**
	 * Funcion para la Apertura de la Ventana.
	 *
	 * @param e el Evento Accionado
	 */
	@Override
	public void windowOpened(WindowEvent e) {
		if (Sesion.getUsuarioActual() != null) {
			// Si el usuario actual NO es un usuario con privilegios
			if (!Sesion.getUsuarioActual().getPrivilegiado()) {
				// Oculta el botón de logs
				btnPanel.setVisible(false);
				// Oculta el botón de editar temporada
				btnEditarTemp.setVisible(false);
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