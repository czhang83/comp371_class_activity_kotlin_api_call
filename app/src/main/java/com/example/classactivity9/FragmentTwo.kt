package com.example.classactivity9

import android.content.Intent
import android.content.SharedPreferences.Editor
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONException
import org.json.JSONObject

class FragmentTwo : Fragment() {

    private val viewModel:UserViewModel by activityViewModels()
    private lateinit var textView : TextView

    private val api_url = "http://api.zippopotam.us/us/" // the dad joke url
    private val client = AsyncHttpClient()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_two, container, false)
        textView = view.findViewById(R.id.text_view_two)

        // call get information, return a live data
        // observe on this live data to get changes
        // display information in the UI
        /**
        viewModel.getInformation().observe(viewLifecycleOwner, object: Observer<UserInformation> {
            override fun onChanged(t: UserInformation?) {
                if (t != null){
                    textView.text = t.name + " : " + t.zipcode
                }
            }

        })*/

        // use lambda
        // let -> scope functions
        // the context object is available as an argument called it
        // the return value is a lambda result

        // ?. -> safe call operator
        // let only works with non null objects
        viewModel.getInformation().observe(viewLifecycleOwner, Observer { userInfo ->
            userInfo?.let {
                client.get(api_url + it.zipcode, object : AsyncHttpResponseHandler() {
                    override fun onSuccess(
                        statusCode: Int,
                        headers: Array<Header>?,
                        responseBody: ByteArray?
                    ) {
                        println(api_url + it.zipcode)
                        if (responseBody == null) {
                            textView.text = it.name + " : Error No city exist"
                        } else {
                            Log.d("api resonse", String(responseBody))
                            try {
                                val json = JSONObject(String(responseBody))
                                val jsonObject : JSONObject = json.getJSONArray("places").getJSONObject(0)
                                val city = jsonObject.getString("place name")
                                textView.text = it.name + " : " + city
                            } catch (e: JSONException) {
                                e.printStackTrace()
                            }
                        }

                    }

                    override fun onFailure(
                        statusCode: Int,
                        headers: Array<Header>?,
                        responseBody: ByteArray?,
                        error: Throwable?
                    ) {
                        textView.text = it.name + " : Error No city exist"
                    }
                })
            }
        })

        return view
    }
}