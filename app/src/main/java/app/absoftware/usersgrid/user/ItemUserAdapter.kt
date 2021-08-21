package app.absoftware.usersgrid.user

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import app.absoftware.usersgrid.R
import java.util.concurrent.Executors



class ItemUserAdapter: BaseAdapter {

    //variables
    var usersList = ArrayList<ItemUser>()
    var context: Context? = null

    constructor(usersList: ArrayList<ItemUser>, context: Context?) : super() {
        this.usersList = usersList
        this.context = context
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val nameGridList = this.usersList[position]
        var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var userView = inflator.inflate(R.layout.user_card, null)

        //------image view------
        val executor = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())

        var image: Bitmap? = null

        executor.execute {

            // Image URL
            val imageURL = nameGridList.urlImage!!

            // Tries to get the image and post it in the ImageView
            try {
                val `in` = java.net.URL(imageURL).openStream()
                image = BitmapFactory.decodeStream(`in`)
                handler.post {
                    userView.findViewById<ImageView>(R.id.imgUser).setImageBitmap(image)
                }
            }
            catch (e: Exception) {
                e.printStackTrace()
            }
        }

        // ------ text view -----
        userView.findViewById<TextView>(R.id.txtFullname).setText(nameGridList.fullname!!)
        userView.findViewById<TextView>(R.id.txtCountry).setText(nameGridList.country!!)

        return  userView
    }

    override fun getItem(position: Int): Any {
       return usersList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


    override fun getCount(): Int {
        return usersList.size
    }








}