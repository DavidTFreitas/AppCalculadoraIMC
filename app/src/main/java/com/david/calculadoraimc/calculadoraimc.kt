package com.david.calculadoraimc

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_calculadoraimc.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class calculadoraimc : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculadoraimc)

            btnCalcular.setOnClickListener {
                val peso = editTextPeso.text.toString()
                val altura = editTextAltura.text.toString()
                val idade = editTextIdade.text.toString()


            if(peso.isEmpty() || altura.isEmpty() || idade.isEmpty() ){
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_LONG).show()
            }else{
                val armazenaDados = getSharedPreferences("imc$peso", Context.MODE_PRIVATE)

                val editArmazenaDados = armazenaDados.edit()

                editArmazenaDados.putString("Peso", peso)
                editArmazenaDados.putString("Altura", altura)
                editArmazenaDados.putString("Idade",idade)

                editArmazenaDados.apply()

                val mIntent = Intent(this, MainActivity::class.java)
                mIntent.putExtra("INTENT_PESO",peso)
                startActivity(mIntent)
            }
        }

        btnSair.setOnClickListener {
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