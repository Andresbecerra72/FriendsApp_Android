package app.absoftware.usersgrid

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.DisplayMetrics
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import app.absoftware.usersgrid.services.WebService
import java.util.concurrent.Executors


class UserInfoPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info_page)

        val service: WebService? = WebService();

        // elements
        val txtName = findViewById(R.id.txtNombre) as TextView
        val txtEmail = findViewById(R.id.txtCorreo) as TextView
        val txtCell = findViewById(R.id.txtCelular) as TextView
        val txtCountry = findViewById(R.id.txtPais) as TextView
        val txtState = findViewById(R.id.txtEstado) as TextView
        val txtCity = findViewById(R.id.txtCiudad) as TextView
        val txtAddress = findViewById(R.id.txtDireccion) as TextView
        val imageView = findViewById(R.id.imageView) as ImageView




        // Codigo para reducir las medidas del Activity
        val medidasVentana = DisplayMetrics() // instancia para capturar las medidas del display

        windowManager.defaultDisplay.getMetrics(medidasVentana)

        val ancho = medidasVentana.widthPixels
        val alto = medidasVentana.heightPixels

        window.setLayout((ancho * 0.85).toInt(), (alto * 0.75).toInt()) // los decimales son en %



        // Get Local DATA
        val nombre = service?.getLocalData(this,"Fullname")
        val correo = service?.getLocalData(this,"Email")
        val celular = service?.getLocalData(this,"Cell")
        val pais = service?.getLocalData(this,"Country")
        val estado = service?.getLocalData(this,"State")
        val ciudad = service?.getLocalData(this,"City")
        val direccion = service?.getLocalData(this,"Address")
        val imgUrl = service?.getLocalData(this,"ImageUrl")

        txtName.setText(nombre)
        txtEmail.setText(correo)
        txtCell.setText(celular)
        txtCountry.setText(pais)
        txtState.setText(estado)
        txtCity.setText(ciudad)
        txtAddress.setText(direccion)

        //------image view------
        val executor = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())

        var image: Bitmap? = null

        executor.execute {
            // Tries to get the image and post it in the ImageView
            try {
                val `in` = java.net.URL(imgUrl).openStream()
                image = BitmapFactory.decodeStream(`in`)
                handler.post {
                    imageView.setImageBitmap(image)
                }
            }
            catch (e: Exception) {
                e.printStackTrace()
            }
        }

    } // end OnCreaete()


}