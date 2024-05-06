package test_junit;
import org.junit.jupiter.api.Test;

import aplicacion.Registro;
import definicion.Usuario;
import junit.framework.TestCase;

class RegistroTest extends TestCase {

   @Test
   public void testRegistroUsuarioExistente() {
      // Obtener la lista de usuarios por defecto
      Usuario.obtenerUsuariosPorDefecto();

      // Crear una instancia de la clase Registro
      Registro registro = new Registro();

      // Establecer un nombre de usuario que ya está en la lista de usuarios
      String usuarioExistente = "admin";
      String contraseña = "admin";

      // Establecer los campos de nombre de usuario y contraseña en el formulario de registro
      registro.txtRegistroUsuario.setText(usuarioExistente);
      registro.txtRegistroPassword.setText(contraseña);
      registro.txtRegistroPassword2.setText(contraseña);

      // Simular un clic en el botón de registro para activar el proceso de registro
      registro.btnRegistrarse.doClick();
   }

   @Test
   public void testRegistroCorrecto() {
      // Crear una instancia de la clase Registro
      Registro registro = new Registro();

      // Establecer un nombre de usuario que no está en la lista de usuarios
      String usuarioNuevo = "usuarioPrueba";
      String contraseña = "contraseñaPrueba";

      // Establecer los campos de nombre de usuario y contraseña en el formulario de registro
      registro.txtRegistroUsuario.setText(usuarioNuevo);
      registro.txtRegistroPassword.setText(contraseña);
      registro.txtRegistroPassword2.setText(contraseña);

      // Simular un clic en el botón de registro para activar el proceso de registro
      registro.btnRegistrarse.doClick();
   }
}
