package br.edu.scl.ifsp.ads.splitthebill.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemsComprados (
    var descricao: String,
    var valor: Double
): Parcelable