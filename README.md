# conversor-moneda-java
Conversor de divisas en Java que consume una API de tipos de cambio.
# 💱 Conversor de Monedas en Java

Este proyecto es un **Conversor de Monedas** desarrollado en Java como parte del desafío del programa **ONE - Oracle Next Education + Alura**.

Permite **consultar tasas de cambio en tiempo real** utilizando la API de [ExchangeRate API](https://www.exchangerate-api.com/) y realizar conversiones entre diferentes monedas latinoamericanas y el dólar.

---

## 🚀 Funcionalidades

✅ Consulta en tiempo real las tasas de cambio usando **HttpClient**  
✅ Analiza la respuesta JSON con **Gson**  
✅ Filtra y muestra monedas específicas (ARS, BOB, BRL, CLP, COP, USD)  
✅ Permite convertir entre dos monedas elegidas por el usuario  
✅ Interfaz textual con menú interactivo en la consola

---

## 🛠️ Tecnologías utilizadas

- **Java 11+**
- **Gson 2.10.1** (para procesar JSON)
- **HttpClient / HttpRequest / HttpResponse** (para consumir la API)
- **ExchangeRate API** (fuente de datos de tasas de cambio)

---

## 📂 Estructura del proyecto  
📁 conversor-moneda-java
├── src/
│ ├── ApiResponse.java # Clase que representa la respuesta JSON
│ ├── ApiService.java # Lógica para consumir API y convertir monedas
│ └── Conversor.java # Menú interactivo para el usuario
├── libs/
│ └── gson-2.10.1.jar # Librería Gson para manejar JSON
├── out/ # Archivos compilados
└── README.md # Documentación del proyecto


---

## ⚙️ Instalación y ejecución

### Uso versión web

- La versión web está publicada en GitHub Pages y se puede usar directamente en:  
  (https://kimjesus22.github.io/conversor-moneda-java/)

- No es necesario abrir localmente el archivo `index.html`, solo abre el enlace para usar el conversor en línea.


1️⃣ **Clonar el repositorio**

```bash
git clone https://github.com/tu-usuario/conversor-moneda-java.git
cd conversor-moneda-java
2️⃣ Descargar Gson y colocarlo en la carpeta libs/
https://repo1.maven.org/maven2/com/google/code/gson/gson/2.10.1/
3️⃣ Compilar el proyecto
javac -cp libs/gson-2.10.1.jar src/ApiResponse.java src/ApiService.java src/Conversor.java -d out
4️⃣ Ejecutar el conversor
java -cp "libs/gson-2.10.1.jar;out" Conversor

Ejemplo de uso
=== Bienvenido/a al Conversor de Moneda ===
1. ARS - Peso argentino
2. BOB - Boliviano
3. BRL - Real brasileño
4. CLP - Peso chileno
5. COP - Peso colombiano
6. USD - Dólar estadounidense
0. Salir
Elija una opción válida: 1
Elija moneda destino: 3
Ingrese la cantidad a convertir: 1000
1000.00 ARS equivalen a 18.50 BRL
------------------------------------------

AUTOR

Desarrollado por Jose de jesus ceron lopez
Programa ONE - Oracle Next Education + Alura Latam


