package livrokotlin.com.br

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.text.NumberFormat
import java.util.*

class MainActivity : AppCompatActivity() {

override fun onCreate(savedInstanceState: Bundle?) {
super.onCreate(savedInstanceState)
setContentView(R.layout.activity_main)


val produtosAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1)


list_view_produtos.adapter = produtosAdapter


btn_adicionar.setOnClickListener {

 val intent = Intent(this, CadastroActivity::class.java)

startActivity(intent)
}

list_view_produtos.setOnItemLongClickListener(
{ adapterView: AdapterView<*>?, view: View?, position: Int, id: Long ->
 val item = produtosAdapter.getItem(position)
produtosAdapter.remove(item)
true })
}


override fun onResume() {
super.onResume()
 val adapter = list_view_produtos.adapter as ProdutoAdapter
adapter.clear()
adapter.addAll(produtosGlobal)
 val soma = produtosGlobal.sumByDouble { it.valor * it.quantidade.toInt() }
 val f = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
txt_total.text = "TOTAL: ${ f.format(soma)}"

}
}
