package com.david.calculadoraimc

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_calculadoraimc.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val peso = intent.getStringExtra("INTENT_PESO")

        val arquivoDeDados = getSharedPreferences("imc$peso", Context.MODE_PRIVATE)

        val pesoParaConta = arquivoDeDados.getString("Peso","")
        val altura = arquivoDeDados.getString("Altura","")
        val idade = arquivoDeDados.getString("Idade","")
        val laranja = Color.rgb(255,165,0)


        var testePeso = pesoParaConta!!.toDouble()
        var testeAltura = altura!!.toDouble()
        var testeIdade = idade!!.toInt()
        var resultado:Double

        if(testeIdade <= 65) {
            resultado = testePeso / (testeAltura * testeAltura)
            txtViewImc.text = resultado.toString()

            if(resultado <18.5 && resultado != 0.0) {
                txtViewTabelaImc.text = "Baixo Peso"
                txtViewTabelaImc.setTextColor(laranja)
            }else if(resultado in 18.5..24.9){
                txtViewTabelaImc.text = "Peso Normal"
                txtViewTabelaImc.setTextColor(Color.GREEN)
            }else if(resultado in 25.0..29.9){
                txtViewTabelaImc.text = "Excesso de Peso"
                txtViewTabelaImc.setTextColor(Color.YELLOW)
            }else if(resultado in 30.0..34.9) {
                txtViewTabelaImc.text = "Obesidade de Classe 1"
                txtViewTabelaImc.setTextColor(Color.RED)
            }else if(resultado in 35.0..39.9){
                txtViewTabelaImc.text = "Obesidade de Classe 2"
                txtViewTabelaImc.setTextColor(Color.RED)
            }else if(resultado >= 40.0){
                txtViewTabelaImc.text = "Obesidade de Classe 3"
                txtViewTabelaImc.setTextColor(Color.RED)
            }
        } else{
            resultado = testePeso / (testeAltura * testeAltura)
            txtViewImc.text = resultado.toString()

            if(resultado <=22.0 && resultado != 0.0) {
                txtViewTabelaImc.text = "Baixo Peso"
                txtViewTabelaImc.setTextColor(Color.rgb(255,165,0))
            }else if(resultado in 22.0..26.9){
                txtViewTabelaImc.text = "Peso Adequado"
                txtViewTabelaImc.setTextColor(Color.GREEN)
            }else if(resultado >=27.0) {
                txtViewTabelaImc.text = "Sobrepeso"
                txtViewTabelaImc.setTextColor(Color.YELLOW)
            }
        }
        btnReCalc.setOnClickListener {
            val alert = AlertDialog.Builder(this)
            alert.setTitle("Atenção")

            alert.setMessage("Deseja mesmo Voltar?")

            alert.setPositiveButton("Voltar") { _, _ ->
                val mIntent = Intent(this, calculadoraimc::class.java)
                startActivity(mIntent)
            }

            alert.setNeutralButton("Não") { _, _ -> }
            alert.show()
        }

        btnSairMain.setOnClickListener {
            val alert = AlertDialog.Builder(this)
            alert.setTitle("Atenção")

            alert.setMessage("Deseja mesmo sair?")

            alert.setPositiveButton("Sair") { _, _ ->
                finishAffinity()
            }

            alert.setNeutralButton("Não") { _, _ -> }
            alert.show()
        }
    }
}