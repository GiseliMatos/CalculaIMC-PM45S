package br.edu.utfpr.calculaimc

import android.icu.text.DecimalFormat
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var etPeso : EditText
    private lateinit var etAltura : EditText
    private lateinit var tvResultado : TextView
    private lateinit var btCalcular : Button
    private lateinit var btLimpar : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        etPeso = findViewById(R.id.etPeso)
        etAltura = findViewById(R.id.etAltura)
        tvResultado = findViewById(R.id.tvResultado)
        btCalcular = findViewById(R.id.btCalcular)
        btLimpar = findViewById(R.id.btLimpar)

        btCalcular.setOnClickListener {
            calcularIMC()
        }

        btLimpar.setOnClickListener {
            limpar()
        }

        btCalcular.setOnLongClickListener {
            Toast.makeText(this, "Faz o calculo do IMC", Toast.LENGTH_SHORT).show()
            true
        }
    }

    private fun limpar() {
        etPeso.setText("")
        etAltura.setText("")
        tvResultado.setText("0.0")
        etPeso.requestFocus()
        Toast.makeText(this, "Campos Limpos", Toast.LENGTH_SHORT).show()
    }

    private fun calcularIMC() {
        if (etPeso.text.isEmpty()){
            etPeso.error = "Campo Obrigatório"
            return
        }
        if (etAltura.text.isEmpty()){
            etAltura.error = "Campo Obrigatório"
            return
        }

        val peso = etPeso.text.toString().toDouble()
        val altura = etAltura.text.toString().toDouble()

        val imc = peso / Math.pow(altura, 2.0)

        var formatador = DecimalFormat("0.00")
        tvResultado.text = formatador.format(imc)
    }
}