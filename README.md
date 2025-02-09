# CentroJuegos
El proyecto Centro Juegos es una aplicación web desarrollada en Java utilizando JSP (JavaServer Pages) y Servlets. La aplicación permite a los usuarios navegar y comprar videojuegos, gestionando la información de usuarios y productos mediante sesiones y JDBC (Java Database Connectivity) para la conexión a una base de datos MySQL.

## 📷 Screenshots

![App Screenshot](https://borjacamara.es/src/images/app_centrogame.JPG)

## 📚 Arquitectura y Tecnologías Utilizadas

- 📝 **Lenguaje de Programación: Java**
- 🌐 **Tecnologías Web: JavaServer Pages (JSP), Servlets**
- 🖥️ **Servidor Web: Apache Tomcat**
- 💾 **Base de Datos: MySQL**
- 🔐 **Gestión de Sesiones: Utilización de sesiones HTTP para mantener el estado del usuario**
- 
## 🚀 Funcionalidades Principales

- 🏠 **Página de Inicio** <br>

Una página de bienvenida que muestra las categorías de videojuegos disponibles y las ofertas destacadas.

- 👤 **Gestión de Usuarios** <br>

📝 Registro: Los usuarios pueden registrarse proporcionando información básica (nombre, email, contraseña, etc.). <br>
🔑 Autenticación: Ingreso de usuarios con verificación de credenciales. Uso de sesiones para mantener el estado de usuario autenticado. <br>
🛠️ Perfil de Usuario: Los usuarios pueden ver y editar su perfil, así como su historial de compras. <br>

- 🎮 **Catálogo de Videojuegos** <br>

🔍 Visualización: Lista de videojuegos con detalles como nombre, descripción, precio y disponibilidad. <br>
🔎 Búsqueda y Filtrado: Opciones para buscar videojuegos por título, género, plataforma, etc. <br>
📄 Detalles del Producto: Página detallada para cada videojuego, con información adicional y la opción de añadir al carrito. <br>

- 🛒 **Gestión del Carrito de Compras** <br>

➕ Añadir al Carrito: Los usuarios pueden agregar videojuegos al carrito. <br>
🛍️ Visualización del Carrito: Lista de productos en el carrito, con la opción de modificar cantidades o eliminar artículos. <br>
💳 Checkout: Proceso para realizar la compra, con recopilación de información de envío y pago. <br>

- 📦 **Gestión del Stock** <br>

🔧 Administración: Interfaz para administradores para agregar, actualizar o eliminar videojuegos del stock. <br>
🔄 Actualización Automática: Reducción automática del stock al realizar una compra. <br>

- 🗄️ **Base de Datos** <br>

👥 Usuarios: Tabla para almacenar información de usuarios (id, nombre, email, contraseña, etc.). <br>
🎮 Videojuegos: Tabla para almacenar información de videojuegos (id, título, descripción, género, precio, stock, etc.). <br>
🕹️ Consolas: Tabla para almacenar información de consolas (id, nombre, fabricante, etc.). <br>
📅 Generaciones de Videojuegos: Tabla para almacenar información de generaciones de videojuegos (id, generación, año_lanzamiento, etc.). <br>

- 🔐 **Gestión de Sesiones** <br>
  
🔓 Inicio de Sesión: Creación de una sesión al autenticar al usuario. <br>
💼 Mantenimiento de Sesión: Mantenimiento del estado de la sesión para funcionalidades personalizadas (perfil, historial, carrito, etc.). <br>
🔒 Finalización de Sesión: Cierre de sesión y destrucción de la sesión al salir el usuario. <br>

- 🛡️ **Seguridad** <br>

🔒 Cifrado de Contraseñas: Almacenamiento de contraseñas cifradas en la base de datos. <br>
🔐 Protección de Datos: Validación y sanitización de entradas para prevenir inyecciones SQL y otros ataques. <br>


