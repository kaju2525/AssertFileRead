package mymap.map.com.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main_splash.*
import org.json.JSONArray
import java.nio.charset.Charset


class AssertActivity : AppCompatActivity() {
    val list=ArrayList<Model>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_splash)

		  //Asert Folder
         val data = assets.open("country/country.json")
		
		//raw folder
		// val data = getResources().openRawResource(R.raw.country);
		
        val size = data.available()
        val buffer = ByteArray(size)
        data.read(buffer)
        data.close()
        var charset: Charset = Charsets.UTF_8
        val json = String(buffer, charset)

        val arr= JSONArray(json)
        for(i in 0..arr.length()-1){
            val obj=arr.getJSONObject(i)
           val model= Gson().fromJson(obj.toString(),Model::class.java)
            list.add(model)
        }
        Dis()
    }

    fun Dis(){
        val li=ArrayList<String>()
        for(model :Model in list){
            li.add(" ${model.name} - ${model.code}  \n  \n  ${model.dial_code}")
        }
        val adp=ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,li)
        listview.adapter=adp
    }
}