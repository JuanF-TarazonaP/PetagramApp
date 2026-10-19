# Petagram

Aplicación Android (Java + Material Design) que muestra una lista de mascotas con RecyclerView.

## Parte 1: Lista de mascotas
- **DataSet:** `pojo/Mascota.java` (POJO con nombre, foto y raiting).
- **Adapter:** `adapter/MascotaAdaptador.java`, que infla y recicla `cardview_mascota.xml`.
- **ViewHolder:** `MascotaAdaptador.MascotaViewHolder` (clase anidada).
- **LayoutManager:** `LinearLayoutManager` vertical en `MainActivity`.
- Cada mascota tiene un **hueso blanco** para darle raiting y un **hueso amarillo** con el total de raiting.

## Parte 2: Action View de estrella
- Ícono de estrella en la barra (`menu/menu_main.xml`) que abre `FavoritosActivity`.
- `FavoritosActivity` muestra un RecyclerView con **5 mascotas dummy**.
- Botón **subir** (flecha atrás) hacia el Activity padre (`parentActivityName` + `setDisplayHomeAsUpEnabled`).

## Cómo ejecutarla
1. Clonar el repositorio.
2. Abrir la carpeta en Android Studio y esperar la sincronización de Gradle.
3. Ejecutar en un emulador o dispositivo.

> Las fotos de las mascotas son ilustraciones vectoriales (`res/drawable/mascota_1.xml` … `mascota_7.xml`).
> Se pueden reemplazar por fotos reales con el mismo nombre (por ejemplo `mascota_1.jpg`).
