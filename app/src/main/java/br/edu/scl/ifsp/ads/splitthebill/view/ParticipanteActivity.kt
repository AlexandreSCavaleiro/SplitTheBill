package br.edu.scl.ifsp.ads.splitthebill.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.scl.ifsp.ads.splitthebill.databinding.ActivityParticipanteBinding


class ParticipanteActivity: AppCompatActivity() {
    private val apb: ActivityParticipanteBinding by lazy {
        ActivityParticipanteBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(apb.root)

        setSupportActionBar(apb.toolbarIn.toolbar)

    }

}