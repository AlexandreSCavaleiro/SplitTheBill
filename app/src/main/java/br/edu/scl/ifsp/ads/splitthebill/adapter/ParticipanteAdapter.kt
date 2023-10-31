package br.edu.scl.ifsp.ads.splitthebill.adapter

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.content.getSystemService
import br.edu.scl.ifsp.ads.splitthebill.R
import br.edu.scl.ifsp.ads.splitthebill.model.GerenteFesta
import br.edu.scl.ifsp.ads.splitthebill.model.Participante

class ParticipanteAdapter(context: Context, private val gerenteFesta: GerenteFesta
): ArrayAdapter<Participante>(context, R.layout.tile_participante, gerenteFesta.participantes)  {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val participante = gerenteFesta.participantes[position]

        var participanteTileView = convertView
        if(participanteTileView == null){
            participanteTileView = (context.getSystemService(LAYOUT_INFLATER_SERVICE) as
                    LayoutInflater).inflate(R.layout.tile_participante, parent, false)
        }

        var valorAPagar = participante.valorGasto - gerenteFesta.getValorIndividual()

        participanteTileView!!.findViewById<TextView>(R.id.nomeTV).setText(participante.nome)
        participanteTileView!!.findViewById<TextView>(R.id.valorTGTV).setText(participante.valorGasto.toString())
        participanteTileView!!.findViewById<TextView>(R.id.valorSFTV).setText(valorAPagar.toString())


        return participanteTileView
    }
}