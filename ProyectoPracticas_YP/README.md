# Akihabara Market - Java Console App

Aplicación de gestión de inventario otaku desarrollada en Java con MySQL y funciones de IA (OpenRouter).

Enlace a GitHub: https://github.com/yeraipinto/ProyectoPractias_YP.git

### Módulo 1: Base de datos y lógica DAO
- Script `crear_tabla.sql` para crear tabla `productos`
- `ProductoOtaku.java`: clase POJO
- `ClienteOtaku.java`: clase POJO
- `ProductoDAO.java`: CRUD con JDBC
- `ClienteDAO.java`: CRUD con JDBC
- `SetupDatos.java`: inserta productos iniciales (productos) si la BD está vacía

### Módulo 2: Interfaz de consola
- `InterfazConsola.java`: menú y entrada/salida
- `Main.java`: lógica principal con menú y control de flujo

### Módulo 3: Interfaz gráfica
- `VentanaGrafica.java`: menú gráfico y entrada/salida
- `MainGUI.java`: lógica principal y control de flujo e inicialización de la ventana gráfica

### Módulo 4: IA con OpenRouter
- `LlmService.java`: llama a la API LLM y procesa la respuesta
- Permite generar descripciones y sugerir categorías

### Módulo 5: Pruebas y documentación
- `config.properties`: contiene datos para el funcionamiento de la aplicación
- `README.md`: este documento
- `EjecutableConsola.jar`: ejecutable para la interfaz de consola
- `EjecutableConsola.bat`: bat para lanzar el ejecutable anterior
- `EjecutableGrafico.jar`: ejecutable para la interfaz gráfica
- `EjecutableGrafico.bat`: bat para lanzar el ejecutable anterior

## Configuración
1. Crea la base de datos:
```sql
CREATE DATABASE akihabara_db;
```
2. Ejecuta `crear_tabla.sql` para crear la tabla `productos`
3. Rellena `config.properties` con:

	DB_URL=jdbc:mysql://localhost:3306/tu_bbdd
	
	DB_USER=tu_usuario
	
	DB_PASSWORD=tu_contraseña
	
	LLM_API_KEY=sk-xxxxxxxxxxxxxxxxxxxxxxxxxxx

4. Añade `gson-2.x.x.jar` en `/lib` y al Build Path
5. Ejecuta `SetupDatos.java` y luego `Main.java`

## Recomendaciones de prueba
- Añadir un producto o cliente y comprobarlo con la opción 3
- Usar opción 6 y 7 para verificar conexión con IA, en Clientes no es necesario
- Probar casos límites: producto inexistente, stock negativo, etc.
- `ClienteDAOTest.java`: contiene instrucciones básicas para comprobar el correcto funcionamento de la clase ClienteDAO
- `ProductoDAOTest.java`: contiene instrucciones básicas para comprobar el correcto funcionamento de la clase ProductoDAO
- `LlmServiceTest`: contiene instrucciones básicas para comprobar el correcto funcionamento de la clase LlmService