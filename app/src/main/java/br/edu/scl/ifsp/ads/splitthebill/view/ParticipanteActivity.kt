package br.edu.scl.ifsp.ads.splitthebill.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import br.edu.scl.ifsp.ads.splitthebill.databinding.ActivityParticipanteBinding
import br.edu.scl.ifsp.ads.splitthebill.model.Constants.PARTICIPANTE_EXTRA
import br.edu.scl.ifsp.ads.splitthebill.model.Constants.PARTICIPANTE_VIEW
import br.edu.scl.ifsp.ads.splitthebill.model.ItemsComprados
import br.edu.scl.ifsp.ads.splitthebill.model.Participante
import kotlin.random.Random


class ParticipanteActivity: AppCompatActivity() {
    private val apb: ActivityParticipanteBinding by lazy {
        ActivityParticipanteBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(apb.root)

        val participanteRecebido = intent.getParcelableExtra<Participante>(PARTICIPANTE_EXTRA)
        participanteRecebido?.let { partRecebido->
            val viewParticipante = intent.getBooleanExtra(PARTICIPANTE_VIEW, false)
            with(apb){
                if (viewParticipante){
                    nomeEt.isEnabled = false
                    item1Et.isEnabled = false
                    item1valorEt.isEnabled = false
                    item2Et.isEnabled = false
                    item2valorEt.isEnabled = false
                    item3Et.isEnabled = false
                    item3valorEt.isEnabled = false
                    salvarBt.isVisible = false
                }
                nomeEt.setText(partRecebido.nome)
                item1Et.setText(partRecebido.itensComprados[0].descricao)
                item1valorEt.setText(partRecebido.itensComprados[0].valor.toString())
                item2Et.setText(partRecebido.itensComprados[1].descricao)
                item2valorEt.setText(partRecebido.itensComprados[1].valor.toString())
                item3Et.setText(partRecebido.itensComprados[2].descricao)
                item3valorEt.setText(partRecebido.itensComprados[2].valor.toString())

            }
        }

        with (apb){
            salvarBt.setOnClickListener{
                var itensComprados: MutableList<ItemsComprados> = mutableListOf()

                var item1: ItemsComprados = ItemsComprados(
                    descricao = item1Et.text.toString(),
                    valor = item1valorEt.text.toString().toDouble()
                )
                itensComprados.add(item1)
                var item2: ItemsComprados = ItemsComprados(
                    descricao = item2Et.text.toString(),
                    valor = item2valorEt.text.toString().toDouble()
                )
                itensComprados.add(item2)

                var item3: ItemsComprados = ItemsComprados(
                    descricao = item3Et.text.toString(),
                    valor = item3valorEt.text.toString().toDouble()
                )
                itensComprados.add(item3)

                var valorGasto: Double = 0.0
                for (item in itensComprados) {
                    valorGasto += item.valor
                }


                val participante: Participante = Participante(
                    id = participanteRecebido?.id?:generateId(), // se o id n for nulo gera id
                    nome = nomeEt.text.toString(),
                    valorGasto = valorGasto,
                    itensComprados = itensComprados
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