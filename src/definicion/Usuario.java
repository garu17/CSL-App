package definicion;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Id;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 * La Clase Usuario.
 */
@Entity
public class Usuario implements Serializable {

	/** La Constante serialVersionUID generada para identificarse. */
	private static final long serialVersionUID = 1307437293073492995L;

	/** El Nombre. */
	@Id
	private String Nombre;

	/** La Contraseña. */
	private String Contraseña;

	/** Si es Privilegiado. */
	private Boolean Privilegiado;

	/**
	 * Instancia para crear un nuevo Usuario por defecto.
	 */
	// Contrustor por defecto
	public Usuario() {
		this.Nombre = "";
		this.Contraseña = "";
		this.Privilegiado = false;
	}

	/**
	 * Instancia para crear un nuevo Usuario copia.
	 *
	 * @param u el Usuario
	 */
	// Contructor copia
	public Usuario(Usuario u) {
		this.Nombre = u.Nombre;
		this.Contraseña = u.Contraseña;
		this.Privilegiado = u.Privilegiado;
	}

	/**
	 * Instancia para crear un nuevo Usuario personalizado.
	 *
	 * @param n el Nombre
	 * @param c la Contraseña
	 * @param p si es Privilegiado
	 */
	// Contructor personalizado
	public Usuario(String n, String c, Boolean p) {
		this.Nombre = n;
		this.Contraseña = c;
		this.Privilegiado = p;
	}

	/**
	 * Instancia para crear un nuevo Usuario personalizado.
	 *
	 * @param n el Nombre
	 */
	// Contructor personalizado
	public Usuario(String n) {
		this.Nombre = n;
		this.Contraseña = "";
		this.Privilegiado = false;
	}

	/**
	 * Funcion To string.
	 *
	 * @return Informacion en formato String
	 */
	public String toString() {
		return ("Usuario: " + Nombre + " | Privilegiado: " + Privilegiado);
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
		this.Nombre = nombre;
	}

	/**
	 * Obtener la Contraseña.
	 *
	 * @return la Contraseña
	 */
	public String getContraseña() {
		return Contraseña;
	}

	/**
	 * Asignar la Contraseña.
	 *
	 * @param contraseña la nueva Contraseña
	 */
	public void setContraseña(String contraseña) {
		this.Contraseña = contraseña;
	}

	/**
	 * Obtener si es Privilegiado.
	 *
	 * @return si es Privilegiado
	 */
	public Boolean getPrivilegiado() {
		return Privilegiado;
	}

	/**
	 * Asignar si es Privilegiado.
	 *
	 * @param privilegiado indica si es Privilegiado.
	 */
	public void setPrivilegiado(Boolean privilegiado) {
		this.Privilegiado = privilegiado;
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
		Usuario other = (Usuario) obj;
		// Si tienen el mismo contenido devuelvo true
		return Objects.equals(this.Nombre, other.Nombre);
	}



	/**
	 * Funcion para Obtener usuarios por defecto.
	 *
	 */
	// Constructor para añadir usuarios por defecto
	public static void obtenerUsuariosPorDefecto() {
	    // Se conecta a la base de datos
	    // crea una base de datos si todavía no existe
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/usuarios.odb");
	    EntityManager em = emf.createEntityManager();

	    // Inicia una transacción
	    em.getTransaction().begin();

	    // Utiliza una consulta JPQL para obtener todos los usuarios
	    TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u", Usuario.class);
	    List<Usuario> usuarios = query.getResultList();

	    // Crea los usuarios por defecto
	    Usuario admin = new Usuario("admin", "admin", true);
	    Usuario usuario = new Usuario("usuario", "usuario", false);

	    // Verifica si los usuarios por defecto ya existen en la base de datos
	    boolean adminExists = usuarios.stream().anyMatch(u -> u.getNombre().equals(admin.getNombre()));
	    boolean userExists = usuarios.stream().anyMatch(u -> u.getNombre().equals(usuario.getNombre()));

	    // Si no existen, guárdalos en la base de datos
	    if (!adminExists) {
	        em.persist(admin);
	    }
	    if (!userExists) {
	        em.persist(usuario);
	    }

	    // Finaliza la transacción
	    em.getTransaction().commit();

	    // Cierra la conexión con la base de datos
	    em.close();
	    emf.close();
	}

}
