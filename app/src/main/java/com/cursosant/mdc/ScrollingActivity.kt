package com.cursosant.mdc

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.webkit.URLUtil
import android.widget.Toast
import com.bumptech.glide.Glide
import com.cursosant.mdc.databinding.ActivityScrollingBinding
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.snackbar.Snackbar
import javax.net.ssl.SNIServerName

class ScrollingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScrollingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityScrollingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fab.setOnClickListener {
            if (binding.bottonAppBar.fabAlignmentMode == BottomAppBar.FAB_ALIGNMENT_MODE_CENTER) {
                binding.bottonAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
            } else {
                binding.bottonAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
            }
        }

        binding.bottonAppBar.setNavigationOnClickListener({
            Snackbar.make(binding.root, "Evento exitoso", Snackbar.LENGTH_SHORT)
                .setAnchorView(binding.fab)
                .show()
        })

        // Esconde el encabezado
        binding.contenido.btnSkip.setOnClickListener {
            binding.contenido.cvAdd.visibility = View.GONE
            // binding.contenido.btnSee.visibility = View.VISIBLE
        }

        // Toast de boton BUY
        binding.contenido.BtnBuy.setOnClickListener {
            Snackbar.make(it, "Comprado", Snackbar.LENGTH_SHORT)
                .setAnchorView(binding.fab)
                .setAction("IR", {
                    Toast.makeText(this, "HISTORIAL", Toast.LENGTH_SHORT).show()
                })
                .show()
        }

    loadImage()

        binding.contenido.cbEnablePass.setOnClickListener{
            binding.contenido.tilpassword.isEnabled = !binding.contenido.tilpassword.isEnabled
        }

        binding.contenido.etUrl.onFocusChangeListener = View.OnFocusChangeListener{ view, focused ->
            var errorStr: String? = null
            var url = binding.contenido.etUrl.text.toString()

            if (focused){
                if (url.isEmpty()){
                    errorStr =getString(R.string.card_required)
                } else if (URLUtil.isValidUrl(url)){
                    loadImage(url)
                } else {
                    errorStr = getString(R.string.card_invalid_url)
                }
            }

            binding.contenido.tilUrl.error = errorStr
        }

        binding.contenido.toggleButton.addOnButtonCheckedListener{group, checkedId, isChecked ->
            binding.contenido.tvMessageAlt.setTextColor(
                when(checkedId){
                    R.id.btnRed -> Color.RED
                    R.id.btnBlue -> Color.BLUE
                    R.id.btnGren -> Color.GREEN
                    else -> Color.BLACK
                }
            )
        }
    }

    private fun loadImage(url :String = "https://i.blogs.es/fe1fc0/android-14-actualizacion-recomendaciones/375_142.jpeg"){
        Glide.with(this)
            .load(url)
            .centerCrop()
            .into(binding.contenido.imgCover)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_scrolling, menu)
        return  true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return  when(item.itemId){
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}