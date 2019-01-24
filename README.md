# GestionDatos Usando HashMap

Este proyecto es un cliente desarrollado en Java que contiene diferentes tipos de accesos de datos. 

Tanto como ficheros, bases de datos relacionales (usando MySQL, Hibernate y JSON) y bases de datos no relacionales (MongoDB) .

## Entorno y condiciones utilizadas:

```
Eclipse.

Xampp (PHPMyAdmin).

MongoDB instalado en local.
```

### El proyecto se puede dividir en:

```
Ficheros de configuración.

Ficheros PHP usados con el tipo de dato JSON.

Scrips de las bases de datos para los diferentes datos tratados.

Librerias utilizadas para el funcionamiento.
```
### Funcionalidad:

Tienes Actores que dentro del actor, contienes un identificador, un nombre, una descripción, un color de ojos, un color de pelo y el identificador de un Representante, esto último puede ser opcional.

Los Representantes contienen un identificador, su nombre y su edad.

Los identificadores de ambos son unicos.

El cliente puede leer, agregar, actualizar y borrar (uno o todos), de los actores y los representantes. A su vez también se puede importar datos y exportar datos a los diferentes tipos de datos comentarios anteriormente.

### Carpetas del proyecto:

Ficheros

```
Contiene los ficheros de configuración de la base de datos MySQL, JSON y MongoDB y los ficheros de
persistencia de datos de los actores y los representantes.
```

Ficheros PHP

```
Necesarios para 
```

### Sobre el código:

Esta estructurado en la arquitectura MVC (Modelo,Vista,Controlador).

Modelo:

```

```

Vista:

```
 
```

Controlador:

```
Está desarrollado con una interfaz que gestiona los métodos que interactúan con el usuario.
Cada tipo de datos tiene su propia clase que implementa esa interfaz.

La clase controlador, se usa para identificar el tipo de dato selecciónado por el cliente y gestionar 
la interacción del usuario con las clases anteriormente mencionadas.
```
