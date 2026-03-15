package com.example.calculadora

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Declaración de variables para los componentes de la interfaz
    private lateinit var etNumero1: EditText
    private lateinit var etNumero2: EditText
    private lateinit var btnSumar: Button
    private lateinit var btnRestar: Button
    private lateinit var btnMultiplicar: Button
    private lateinit var btnDividir: Button
    private lateinit var tvResultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicialización de componentes vinculándolos con el XML
        inicializarComponentes()

        // Configuración de los eventos OnClickListener para cada botón
        btnSumar.setOnClickListener { calcular("+") }
        btnRestar.setOnClickListener { calcular("-") }
        btnMultiplicar.setOnClickListener { calcular("*") }
        btnDividir.setOnClickListener { calcular("/") }
    }

    /**
     * Función para vincular los IDs del XML con las variables de Kotlin
     */
    private fun inicializarComponentes() {
        etNumero1 = findViewById(R.id.etNumero1)
        etNumero2 = findViewById(R.id.etNumero2)
        btnSumar = findViewById(R.id.btnSumar)
        btnRestar = findViewById(R.id.btnRestar)
        btnMultiplicar = findViewById(R.id.btnMultiplicar)
        btnDividir = findViewById(R.id.btnDividir)
        tvResultado = findViewById(R.id.tvResultado)
    }

    /**
     * Función principal para realizar las operaciones.
     * Recibe el tipo de operación como un String.
     */
    private fun calcular(operacion: String) {
        // 1. Obtener los textos de los EditText
        val num1Str = etNumero1.text.toString()
        val num2Str = etNumero2.text.toString()

        // 2. Validación: Si algún campo está vacío, mostrar un Toast
        if (num1Str.isEmpty() || num2Str.isEmpty()) {
            Toast.makeText(this, "Por favor, ingrese ambos números", Toast.LENGTH_SHORT).show()
            return
        }

        // 3. Convertir a Double para permitir decimales
        val n1 = num1Str.toDouble()
        val n2 = num2Str.toDouble()
        var resultado = 0.0

        // 4. Determinar y ejecutar la operación matemática
        when (operacion) {
            "+" -> resultado = n1 + n2
            "-" -> resultado = n1 - n2
            "*" -> resultado = n1 * n2
            "/" -> {
                // Validación especial: División por cero
                if (n2 == 0.0) {
                    Toast.makeText(this, "Error: No se puede dividir por cero", Toast.LENGTH_LONG).show()
                    return
                }
                resultado = n1 / n2
            }
        }

        // 5. Mostrar el resultado en el TextView redondeando a 2 decimales si es necesario
        tvResultado.text = "Resultado: ${String.format("%.2f", resultado)}"
    }
}
