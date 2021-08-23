package app.absoftware.usersgrid.services

import android.content.Context
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley



// una de las caracteristicas del patron es crear el contructor privado
class RequestQueueSingleton private constructor(private var context: Context) {

    private var requestQueue: RequestQueue?

    fun getRequestQueue(): RequestQueue? {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context!!.getApplicationContext())
        }
        // retorna este objeto cuando ya hasido instanciado
        return requestQueue
    }

    companion object {
        private var requestQueueSingleton: RequestQueueSingleton? = null
        @Synchronized
        fun getInstance(context: Context): RequestQueueSingleton? {
            if (requestQueueSingleton == null) {
                requestQueueSingleton = RequestQueueSingleton(context)
            }
            // retorna este objeto cuando ya hasido instanciado
            return requestQueueSingleton
        }
    }

    init {
        requestQueue = getRequestQueue()
    }
} //END class
