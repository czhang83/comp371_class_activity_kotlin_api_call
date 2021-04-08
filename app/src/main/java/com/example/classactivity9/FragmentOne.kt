package com.example.classactivity9

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels

class FragmentOne : Fragment(){
    // retrieve the view model in the activity scope
    private val viewModel:UserViewModel by activityViewModels()

    private lateinit var editTextName : EditText
    private lateinit var editTextZipCode : EditText
    private lateinit var button : Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_one, container, false)
        editTextName = view.findViewById(R.id.editText_name)
        editTextZipCode = view.findViewById(R.id.editText_zip)
        button = view.findViewById(R.id.button_submit)

        // when button is clicked
        // update the user information in the view model with the new data entered
        button.setOnClickListener {
            // it -> View
            // this similar to it
            // should do user input validation here
            val userInfor = UserInformation(editTextName.text.toString(), editTextZipCode.text.toString())
            viewModel.setInformation(userInfor)
        }

        return view
    }

}