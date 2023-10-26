package com.sharcome.moments.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.database.DatabaseReference
import com.sharcome.moments.R
import com.sharcome.moments.databinding.FragmentLoginBinding
import com.sharcome.moments.domain.models.LoginModel

class LoginFragment : Fragment() {
    
    lateinit var viewBinding: FragmentLoginBinding
    lateinit var databaseReference: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentLoginBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment
        return viewBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.buttonNext.setOnClickListener {
            val name = viewBinding.textInputLayout.editText?.text.toString()
            val phone = viewBinding.textInputLayout2.editText?.text.toString()
            val birthday = viewBinding.textInputLayout3.editText?.text.toString()

            if (name.isEmpty()){
                Toast.makeText(requireContext(), "Ismni kiriting!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (phone.isEmpty()){
                Toast.makeText(requireContext(), "Telefonni kiriting!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (birthday.isEmpty()){
                Toast.makeText(requireContext(), "Tug'ilgan kunni  kiriting!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val  model = LoginModel(
                name = name,
                phone = phone,
                birthday = birthday
            )
            val bundle = Bundle()
            bundle.putString("name", name)
            bundle.putString("phone", phone)
            bundle.putString("birthday", birthday)
            val  navigation =  findNavController()
            navigation.navigate(R.id.userNameFragment,bundle)
        }
    }

}
