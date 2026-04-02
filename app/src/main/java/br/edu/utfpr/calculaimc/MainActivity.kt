package br.edu.utfpr.calculaimc

import android.icu.text.DecimalFormat
import android.icu.text.NumberFormat
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Locale
import kotlin.math.pow

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
            Toast.makeText(this, getString(R.string.toast_calcular), Toast.LENGTH_SHORT).show()
            true
        }
    }

    private fun limpar() {
        etPeso.setText("")
        etAltura.setText("")
        tvResultado.setText("0.0")
        etPeso.requestFocus()
        Toast.makeText(this, getString(R.string.toast_limpar), Toast.LENGTH_SHORT).show()
        //log.i("MainActivity", "Exemplo de information")
        //log.e("MainActivity", "Exemplo de information")
    }

    private fun calcularIMC() {
        if (etPeso.text.isEmpty()){
            etPeso.error = getString(R.string.toast_campoObrigatorio)
            return
        }
        if (etAltura.text.isEmpty()){
            etAltura.error = getString(R.string.toast_campoObrigatorio)
            return
        }

        val peso = etPeso.text.toString().toDouble()
        val altura = etAltura.text.toString().toDouble()

        var imc : Double

        if(Locale.getDefault().language.equals("en")){
            imc = 703 * ( peso / altura.pow(2.0))
        }else{
            imc = peso / altura.pow(2.0)
        }

        val nf = NumberFormat.getNumberInstance(Locale.getDefault())
        val df = nf as DecimalFormat
        tvResultado.text = df.format(imc)
    }
}