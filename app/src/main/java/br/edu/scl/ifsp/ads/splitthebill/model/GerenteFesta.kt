package br.edu.scl.ifsp.ads.splitthebill.model

class GerenteFesta {

    var participantes: MutableList<Participante> = mutableListOf()
    private var valorTotalFesta: Double = 0.0
    private var valorIndividual: Double = 0.0

    fun getValorTotal():Double {
        return valorTotalFesta
    }

    fun getValorIndividual(): Double{
        atualizaValores()
        return valorIndividual
    }

    fun getLista(): MutableList<Participante> {
        return participantes
    }

    fun atualizaValores() {
        valorTotalFesta = 0.0
        for (participante in participantes){ //percorre a lista e faz a soma total
            valorTotalFesta+= participante.valorGasto
        }
        valorIndividual = valorTotalFesta/participantes.count() //faz a conta de quanto cada um deve
    }

    private fun novoParticipante(part: Participante){
        participantes.add(part)
        atualizaValores()
    }

}