package br.edu.scl.ifsp.ads.splitthebill.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import br.edu.scl.ifsp.ads.splitthebill.R
import br.edu.scl.ifsp.ads.splitthebill.databinding.ActivityMainBinding
import br.edu.scl.ifsp.ads.splitthebill.model.Participante

class MainActivity : AppCompatActivity() {
    //view
    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

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

        setSupportActionBar(amb.toolbarIn.toolbar)
        amb.participantelv.adapter= participanteAdapter
        
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.addParticipanteMi -> {
                //abrir a outra tela
                true
            }
            else -> true
        }
    }


}