package app.absoftware.usersgrid.services

import android.content.Context
import android.widget.Toast
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest


class WebService {


    //variables
    val MY_DEFAULT_TIMEOUT = 15000



    //-------------------------------------------------------------
    //  METODOS PARA GESTIONAR EL API
    //-------------------------------------------------------------
    // Get Request For JSONObject  METODO : GET

        fun getData(context: Context?, url: String?, callback: VolleyCallback?) {


        val requestQueue: RequestQueue? = RequestQueueSingleton.getInstance(context!!)!!.getRequestQueue()
        //val queue = Volley.newRequestQueue(context)

        // ejecuta metodo GET con Volley
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
                { response ->
                    callback!!.onSuccessResponse(response)

                    // Toast.makeText(context, messageResponse, Toast.LENGTH_LONG).show()

                },
                { error ->

                   // println(error)
                    callback!!.onErrorResponse(error.message)
                    Toast.makeText(context, "Error: " + error.message, Toast.LENGTH_LONG).show()
                })

            // politica de espera de la respuesta HTTP
            jsonObjectRequest.setRetryPolicy(DefaultRetryPolicy(
                    MY_DEFAULT_TIMEOUT,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT))

        requestQueue!!.add(jsonObjectRequest)
        // queue.add(jsonObjectRequest)


    }




    // --------------------------------------------
    // LOCALSTORAGE SharedPreferences
    // --------------------------------------------
    fun saveLocalData(context: Context, key: String?, value: String?) {
        val sharedPreferences = context.getSharedPreferences("myPref", 0)
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getLocalData(context: Context, key: String?): String? {
        val sharedPreferences = context.getSharedPreferences("myPref", 0)
        return if (sharedPreferences.contains(key)) {
            sharedPreferences.getString(key, null)
        } else {
            null
        }
    }

    fun removeLocalData(context: Context, key: String?) {
        val sharedPreferences = context.getSharedPreferences("myPref", 0)
        val editor = sharedPreferences.edit()
        editor.remove(key)
        editor.apply()
    }




}