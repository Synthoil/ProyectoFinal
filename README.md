# ProyectoFinal
Integrantes de Grupo Proyecto 8:

- Matias Concha @Synthoil
- Lucas Maldonado @EleMado
- Benjamin Diaz @Venja6

# Enunciado

Este simulador ofrece al usuario la experiencia de administrar su propia tienda de mascotas virtual. El jugador comienza con un presupuesto inicial y puede adoptar diferentes tipos de mascotas (perros, gatos, pájaros y peces), cada una con atributos y necesidades específicas como alimentación, higiene, felicidad y salud. El usuario deberá gestionar su inventario de objetos comprables (comida, juguetes y medicinas), los cuales solo pueden utilizarse dentro del contexto de interacción con una mascota. Las estadísticas de cada mascota cambian con el tiempo y deben ser monitoreadas para evitar enfermedades o disminución del bienestar. Además, se pueden comprar mejoras permanentes como camas, jaula o acuario, que amplían la capacidad de la tienda. También es posible vender mascotas, obteniendo ingresos según su estado, lo que permite al jugador continuar expandiendo y cuidando su tienda.

# Patrones de Diseño

1. Factory Method

Este patrón se utiliza para encapsular la lógica de creacion de objetos Mascotas, permitiendo instanciar diferentes tipos de concretos (como Perro, Gato, Pez y Pajaro) 
sin depender directamente de sus clases. Esto proporciona una alta flexibidad y desacopla la lógica de adopción del tipo específico de mascota que se genera.
Gracias a este patrón, es posible agregar nuevos tipos de mascotas en el futuro (por ejemplo, un hámster o tortugas) sin modificar la lógica existente. Además, este patrón facilita la asignación de nombre aleatorios y la integración con hábitats específicos, al centralizar la lógica de creación.

Clases utilizadas:
- MascotaFactory
- PerroFactory
- GatoFactory 
- PezFactory 
- PajaroFactory

2. Observer 

El comportamiento dinámico de las mascotas, como la disminución de estadísticas con el tiempo o el cambio de estado por enfermedad o lesión, se modela utilizando una versión simplificada del patrón Observer. En este patrón, las mascotas actúan como sujetos observables que cambian con el tiempo, mientras que distintas partes de la aplicación pueden observar sus estados y reaccionar ante esos cambios.
Este patrón es especialmente útil para mantener actualizadas las estadísticas visibles por el usuario sin acoplar directamente la lógica de negocio con la vista. Permite que la lógica de actualización del estado esté completamente encapsulada en la clase Mascota, y que las vistas o interfaces simplemente reflejen esos cambios cuando sea necesario, mejorando así la cohesión y reduciendo el acoplamiento 

Clases utilizadas:
- Mascota
- ObservadorMascota
- PanelInicio

# Decisiones Importantes

Al principio del proyecto, se había planteado que los objetos comprables (como comida, juguetes y medicina) se podrían usar directamente desde el inventario. Sin embargo, se tomó la decisión de restringir su uso exclusivamente al contexto de interacción con una mascota. Esta elección buscó darle más coherencia y realismo a la mecánica del juego, además de evitar errores de uso o confusión en la interfaz. Así, cada acción (alimentar, curar, jugar) está vinculada claramente a una mascota específica, reforzando la lógica de cuidado individual.

# Problemas Identificados y Autocritica

En las primeras etapas, muchas funciones estaban fuertemente acopladas a la interfaz gráfica, lo que dificultaba realizar pruebas o cambios independientes. Esto se corrigio posteriormente aplicando una separación más clara entre la lógica y la presentación. Tambien, aunque las mascotas cambiaban de estado con el tiempo, al principio no existía una forma de reflejar esos cambios en la interfaz. Fue necesario implementar un sistema de observadores para que las estadisticas se actualizaran en tiempo real y así detectar estados críticos. Una autocritica podria ser una falta de manejo de errores lógico sin excepciones, en versiones tempranas se usaban if-else para manejar todos los errores posibles. Luego se introdujeron excepciones personalizadas que simplificaron el flujo de control y mejoraron la legibilidad del código. Y tambien la implementación de tests unitarios fue incorporado en etapas finales, lo que hizo que muchos errores se detectaran más tarde de lo deseado.