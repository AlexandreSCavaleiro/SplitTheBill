package br.edu.scl.ifsp.ads.splitthebill.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Participante (
    var id: Int,
    var nome: String,
    var itensComprados: String,
    var valorGasto: Double

): Parcelable