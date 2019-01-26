# GestionDatos Usando HashMap

Este proyecto es un cliente desarrollado en Java que contiene diferentes tipos de accesos de datos. 

Tanto como ficheros, bases de datos relacionales (usando MySQL, Hibernate y JSON) y bases de datos no relacionales (MongoDB) .

## Entorno y condiciones utilizadas:

```
Eclipse.

Xampp (PHPMyAdmin).

MongoDB instalado en local.

Windows 10
```

### El proyecto se puede dividir en:

Ficheros de configuración.

Ficheros PHP usados con el tipo de dato JSON.

Scrips de las bases de datos para los diferentes datos tratados.

Librerias utilizadas para el funcionamiento.

### Funcionalidad:

Tienes Actores que dentro del actor, contienes un identificador, un nombre, una descripción, un color de ojos, un color de pelo y el identificador de un Representante, esto último puede ser opcional.

Los Representantes contienen un identificador, su nombre y su edad.

Los identificadores de ambos son unicos.

El cliente puede leer, agregar, actualizar y borrar (uno o todos), de los actores y los representantes. A su vez también se puede importar datos y exportar datos a los diferentes tipos de datos comentarios anteriormente.

### Carpetas del proyecto:

Ficheros de Configuracion: Contiene los ficheros de configuración de la base de datos MySQL, JSON y MongoDB.

Ficheros: Contiene los ficheros de persistencia de datos de los actores y los representantes.

Ficheros PHP: Necesarios para la gestion del tipo de dato JSON.

Librerias: La carpeta lib contiene todas las librerias que necesita el proyecto para su correcto funcionamiento.

Scrip BBDD: Directorio que se compone de tres scrip .sql siendo "acceso_a_datos" (MySql), "acceso_a_datos_2 (Hibernate) y
"acceso_a_datos_3" (JSON).

Scrip Mongo: Directorio que compone dos json con los scrips de la base de datos de Mongodb.

### Sobre el código:

En la carpeta raiz del proyecto (src) encontraremos un fichero llamado "hibernate.cfg.xml", necesario para la configuracion de Hibernate.

Esta estructurado en la arquitectura MVC (Modelo,Vista,Controlador), dentro de la raiz del proyecto (src), existen tres paquetes
llamados "Modelo", "Vista" y "Controlador".

Modelo:

Contiene los modelos de datos de los actores y los representantes. En dicho paquete a parte de existir las dos clases para las
entidades anteriormente mencionadas existen dos ficheros de mapeo para el uso de Hibernate, llamados "Actor.hbm.xml" y
"Representante.hbm.xml".

Vista:

Solo se compone de una clase llamada Test que ejecuta un menu principal, el cual seleccionas el tipo de dato con el que se desea
trabajar y a continuación se muestra un menu con las opciones que se le permiten al cliente.

Controlador:

Está desarrollado con una interfaz que gestiona los métodos que interactúan con el usuario.
Cada tipo de datos tiene su propia clase que implementa esa interfaz.

La clase controlador, se usa para identificar el tipo de dato selecciónado por el cliente y gestionar 
la interacción del usuario con las clases anteriormente mencionadas.

Se implementa la clase "ApiRequests.java" como auxiliar para gestionar mas facil las peticiones con internet.

### Configuración de los tipos de datos:

Ficheros. No necesita ningun tipo de configuración ya que se tratan como ficheros de texto normales y estan incluidos en el proyecto.

MySql, Hibernate y Json. Lo primero que se debe realizar es descargarse Xammp, dejo una url a continuación.

```
https://www.apachefriends.org/es/download.html
```

Una vez hayas terminado el proceso de descarga e instalacción, ejecuta el programa y dale a "Start" a Apache y MySql, a continuación
pincha en el botón "Admin" de MySql, se te abrira un navegador y en la pestaña SQL deberas copiar y pegar los scrips MySQL proporcionados (es necesario importar los 3).

Para el correcto uso de JSON debes localizar la carpeta "htdocs" situada donde instalastes el Xammp, deberas crearte una carpeta,
en mi caso se llama "David", dentro de esta deberas crear otra llamada "GestorDatos" y dentro de esta ultima, copia los archivos
PHP proporcionados en el proyecto. Para saber si lo has hecho correctamente debes poder verlos aqui, con el Xammp iniciado.

```
http://localhost/David/GestorDatos/
```

MongoDB. Lo primero que deberas hacer es descargar MongoDB, te proporciono la url.

```
https://www.mongodb.com/download-center/community
```

Una vez descargado, deberemos ir a nuestro ordenador, en la raiz de C, debemos crear una carpeta llamada "Mongo".
En la carpeta "Mongo" deberemos descomprimir lo que nos acabamos de descargar. Navegaremos hasta dicha ruta con la consola de comandos (CMD) y una vez dentro del directorio navegaremos a la carpeta "bin" y escribiremos "mongod" para ejecutar la base de datos.

Importante: Debes importar los dos json situados en Scrip_Mongo, usando mongoimport.

### ¿Lo quieres probar?

Te invito a que pruebes este cliente, para su correcto funcionamiento con los 5 tipos de datos necesitaras configurar todos. Para ejecutar el programa debes ir a la carpeta src, dentro de esta al paquete Vista, y en la clase "Test.java" es donde se encuentra el main del programa.

### Contribuidores:

Linkedin: https://www.linkedin.com/in/alberto-gurpegui-ramon/ 
Github: https://github.com/albertogurpegui
