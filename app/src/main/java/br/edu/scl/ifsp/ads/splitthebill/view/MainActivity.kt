package br.edu.scl.ifsp.ads.splitthebill.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import br.edu.scl.ifsp.ads.splitthebill.R
import br.edu.scl.ifsp.ads.splitthebill.databinding.ActivityMainBinding
import br.edu.scl.ifsp.ads.splitthebill.model.Constants.PARTICIPANTE_EXTRA
import br.edu.scl.ifsp.ads.splitthebill.model.Participante

class MainActivity : AppCompatActivity() {
    //view
    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    //ARL
    private lateinit var carl: ActivityResultLauncher<Intent>

    //data source
    private val participanteList: MutableList<Participante> = mutableListOf()

    //adapter
    private val participanteAdapter: ArrayAdapter<String> by lazy {
        ArrayAdapter(this,
            android.R.layout.simple_list_item_1,
            participanteList.map{participante ->
                participante.nome
            }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)
        fillParticipantes()

        setSupportActionBar(amb.toolbarIn.toolbar)
        amb.participantelv.adapter= participanteAdapter

        carl = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult())
            {result ->
                if (result.resultCode == RESULT_OK){
                    val participante =result.data?.getParcelableExtra<Participante>(PARTICIPANTE_EXTRA)
                    participante?.let{_participante ->
                        participanteList.add(_participante)
                        participanteAdapter.add(_participante.nome)
                        participanteAdapter.notifyDataSetChanged()
                    }
                }
            }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.addParticipanteMi -> {
                carl.launch(Intent(this,ParticipanteActivity::class.java))
                true
            }
            else -> true
        }
    }

    private fun fillParticipantes(){
        for (i in 1..5){
            participanteList.add(Participante(i,"nome$i","produtos", 2.00))
        }
    }

}