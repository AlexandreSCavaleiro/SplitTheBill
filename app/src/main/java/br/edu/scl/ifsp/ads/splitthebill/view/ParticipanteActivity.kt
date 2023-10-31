package br.edu.scl.ifsp.ads.splitthebill.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.scl.ifsp.ads.splitthebill.databinding.ActivityParticipanteBinding
import br.edu.scl.ifsp.ads.splitthebill.model.Constants.PARTICIPANTE_EXTRA
import br.edu.scl.ifsp.ads.splitthebill.model.Participante
import kotlin.random.Random


class ParticipanteActivity: AppCompatActivity() {
    private val apb: ActivityParticipanteBinding by lazy {
        ActivityParticipanteBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(apb.root)

        with (apb){
            salvarBt.setOnClickListener{
                val participante: Participante = Participante(
                    id = generateId(),
                    nome = nomeEt.text.toString(),
                    valorGasto = valorGastoEt.text.toString().toDouble(),
                    itensComprados = itemsCompradosEt.text.toString()
                )

                val resultIntent = Intent()
                resultIntent.putExtra(PARTICIPANTE_EXTRA, participante)
                setResult(RESULT_OK, resultIntent)
                finish()
            }
        }

        setSupportActionBar(apb.toolbarIn.toolbar)
    }

    private fun generateId(): Int = Random(System.currentTimeMillis()).nextInt()
}