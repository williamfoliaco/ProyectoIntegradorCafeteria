# Proyecto integrador AA4 - Cafetería (Java + Swing + H2)

## Descripción
Aplicación de escritorio que calcula el descuento de un pedido, muestra el subtotal,
el descuento y el total a pagar, y guarda el resultado en una base de datos H2 local.

## Regla implementada
Siguiendo el ejemplo del contexto de Unidad 3: si el total supera $10, se aplica
un descuento del 15 %; en caso contrario, el descuento es 0 %. El umbral y el
porcentaje están definidos en `CalculadorEdEDescuento.java` como constantes para
facilitar su modificación.

> Nota: el ejemplo académico usa el umbral de $10. Si el docente espera otro
> umbral o una moneda/unidad específica, ajuste `UMBRAL` antes de entregar.

## Requisitos
- JDK 8 o superior.
- Apache Maven (NetBeans puede abrir directamente este proyecto Maven).
- Conexión a Internet la primera vez para que Maven descargue el controlador H2.
  Después de descargar la dependencia, puede ejecutarse sin conexión.

## Abrir y ejecutar en NetBeans
1. Descomprima el ZIP.
2. En NetBeans seleccione **File > Open Project** y elija la carpeta que contiene `pom.xml`.
3. Espere a que Maven descargue la dependencia H2.
4. Abra `src/main/java/cafeteria/VentanaCafeteria.java`.
5. Ejecute el proyecto. La clase principal es `cafeteria.VentanaCafeteria`.
6. Ingrese un monto y pulse **Calcular descuento**.

## Base de datos
H2 funciona en modo integrado (embedded); no necesita XAMPP ni un servidor MySQL.
La base de datos se crea automáticamente en `base_datos/cafeteria.mv.db`.
Usuario: `sa`. Contraseña inicial: vacía.

## Clases
- `Producto`: clase abstracta base.
- `Bebida` y `Comida`: heredan de Producto.
- `ReglaDescuento`: interfaz funcional.
- `CalculadoraDescuento`: aplica la regla mediante una expresión lambda.
- `ConexionBD`: crea y administra la conexión H2 y la tabla.
- `PedidoDAO`: guarda los pedidos.
- `VentanaCafeteria`: interfaz Swing.

## Evidencias que debes tomar
No se incluyen capturas ficticias. Ejecuta el programa y toma capturas reales de:
1. Ventana abierta y mensaje de conexión H2 exitosa.
2. Cálculo de un monto que no supere el umbral (sin descuento).
3. Cálculo de un monto que supere el umbral (con 15 % de descuento).
4. Confirmación de que el pedido se guardó correctamente.

## Git: commits sugeridos
```bash
git init
git add .
git commit -m "Estructura inicial y modelo orientado a objetos"
git add .
git commit -m "Implementa regla funcional de descuentos y persistencia H2"
git add .
git commit -m "Agrega interfaz Swing y validaciones"
```
Luego cree un repositorio vacío en GitHub y siga las instrucciones de GitHub para
conectar el repositorio local y hacer `git push`.

## Entrega
Complete los datos de portada, agregue las capturas reales, actualice los enlaces
de repositorio y exporte el informe final a PDF. Incluya el PDF y `codificacion.csv`
en el ZIP que se sube a la plataforma.
