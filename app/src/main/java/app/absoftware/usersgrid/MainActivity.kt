package app.absoftware.usersgrid

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.GridView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import app.absoftware.usersgrid.services.VolleyCallback
import app.absoftware.usersgrid.services.WebService
import app.absoftware.usersgrid.user.ItemUser
import app.absoftware.usersgrid.user.ItemUserAdapter
import org.json.JSONObject


class MainActivity : AppCompatActivity() {


    // variables
    var adapter: ItemUserAdapter? = null
    var usersList = ArrayList<ItemUser>()

    var gridView: GridView? = null
    var progressBar: ProgressBar? = null

    var service: WebService? = WebService();


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // elements
        progressBar = findViewById(R.id.progressBar) as ProgressBar
        gridView = findViewById(R.id.gridView) as GridView



        progressBar!!.setVisibility(View.VISIBLE); // Loading
        gridView!!.setVisibility(View.INVISIBLE);



        // call al API
        getUserData()



        //--------event Grid-----------------

        gridView!!.setOnItemClickListener(OnItemClickListener { parent, v, position, id ->


            //Toast.makeText(this@MainActivity, "" + position + "_" + id, Toast.LENGTH_LONG).show()


            // println(usersList.get(position).fullname)

            service?.saveLocalData(this, "Fullname", usersList.get(position).fullname)
            service?.saveLocalData(this, "Country", usersList.get(position).country)
            service?.saveLocalData(this, "State", usersList.get(position).state)
            service?.saveLocalData(this, "City", usersList.get(position).city)
            service?.saveLocalData(this, "Address", usersList.get(position).addres)
            service?.saveLocalData(this, "Email", usersList.get(position).email)
            service?.saveLocalData(this, "Cell", usersList.get(position).cell)
            service?.saveLocalData(this, "ImageUrl", usersList.get(position).urlImage)

            val intent = Intent(applicationContext, UserInfoPage::class.java)
            startActivity(intent)


        })




    } // end OnCreate()


    // -----funtions----------
     fun getUserData() {

        val url = getResources().getString(R.string.url);

        service?.getData(this, url, object : VolleyCallback {

            override fun onSuccessResponse(result: Any?) {

                val data = JSONObject(result.toString())
                val jsonArray = data.getJSONArray("results")

                // println(jsonArray[0])

                val listObject = ArrayList<Any>()
                for (i in 0 until jsonArray.length()) {
                    listObject.add(i, jsonArray[i])
                }

                // println(listObject[0])

                for (item in listObject) {

                    // data from API
                    val userData = JSONObject(item.toString())
                    // objects
                    val nameObj = userData.getJSONObject("name")
                    val locationObj = userData.getJSONObject("location")
                    val pictureObj = userData.getJSONObject("picture")
                    val addressObj = locationObj.getJSONObject("street")

                    val fullname = nameObj.getString("first") + " " + nameObj.getString("last")
                    val address =
                        addressObj.getString("number") + " " + addressObj.getString("name")

                    usersList.add(
                        ItemUser(
                            fullname,
                            locationObj.getString("country"),
                            address,
                            locationObj.getString("city"),
                            locationObj.getString("state"),
                            userData.getString("email"),
                            userData.getString("cell"),
                            pictureObj.getString("large")
                        )
                    )

                }


                adapter = ItemUserAdapter(usersList, this@MainActivity)
                gridView!!.adapter = adapter
                progressBar!!.setVisibility(View.INVISIBLE); // Loading
                gridView!!.setVisibility(View.VISIBLE);

            }

            override fun onErrorResponse(error: String?) {

                //println(error)

                Toast.makeText(this@MainActivity, "¡Error!" + error, Toast.LENGTH_LONG).show()

            }

        })



    }


}

/////////code for test/////////////////////
// set data into gridVIew
/*  usersList.add(
      ItemUser(
          "Andres Becerra",
          "Colombia",
          R.drawable.ic_launcher_foreground,
          "",
          "",
          "",
          "",
          ""
      )
  )


*/



// Log.d("Here", "...")