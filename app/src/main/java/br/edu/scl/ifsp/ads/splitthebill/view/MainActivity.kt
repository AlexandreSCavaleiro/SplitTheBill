package br.edu.scl.ifsp.ads.splitthebill.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import br.edu.scl.ifsp.ads.splitthebill.R
import br.edu.scl.ifsp.ads.splitthebill.adapter.ParticipanteAdapter
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
    private val participanteAdapter: ParticipanteAdapter by lazy {
        ParticipanteAdapter( this, participanteList)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)
        //fillParticipantes()

        setSupportActionBar(amb.toolbarIn.toolbar)
        amb.participantelv.adapter= participanteAdapter

        carl = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult())
            {result ->
                if (result.resultCode == RESULT_OK){
                    val participante =result.data?.getParcelableExtra<Participante>(PARTICIPANTE_EXTRA)
                    participante?.let{_participante ->

                        if (participanteList.any{it.id == participante.id}){
                            val pos =participanteList.indexOfFirst {it.id == participante.id}
                            participanteList[pos] = _participante
                        }else{
                            participanteList.add(_participante)
                        }
                        participanteAdapter.notifyDataSetChanged()
                    }
                }
            }
        registerForContextMenu(amb.participantelv)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        menuInflater.inflate(R.menu.context_menu_main,menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val position = (item.menuInfo as AdapterView.AdapterContextMenuInfo).position
        return when (item.itemId){
            R.id.removeParticipanteMi -> {
                participanteList.removeAt(position)
                participanteAdapter.notifyDataSetChanged()
                Toast.makeText(this, "Removido com sucesso!", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.editParticipanteMi -> {
                val participante = participanteList[position]
                val editPartIntent = Intent(this, ParticipanteActivity::class.java)
                editPartIntent.putExtra(PARTICIPANTE_EXTRA, participante)
                carl.launch(editPartIntent)
                true
            }
            else -> {true}
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

    /*
    private fun fillParticipantes(){
        for (i in 1..5){
            participanteList.add(Participante(i,"nome$i","produtos", 2.00))
        }
    }
    */
}