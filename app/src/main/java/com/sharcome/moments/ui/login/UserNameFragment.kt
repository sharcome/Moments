package com.sharcome.moments.ui.login

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.google.firebase.database.*
import com.sharcome.moments.HomeActivity
import com.sharcome.moments.R
import com.sharcome.moments.databinding.FragmentUserNameBinding
import com.sharcome.moments.domain.models.LoginModel

class UserNameFragment : Fragment(), TextWatcher {

    lateinit var viewBinding: FragmentUserNameBinding
    lateinit var databaseReference: DatabaseReference
    lateinit var model: LoginModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        viewBinding = FragmentUserNameBinding.inflate(inflater,container,false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val name = arguments?.getString("name")
        val phone = arguments?.getString("phone")
        val birthday = arguments?.getString("birthday")

        model = LoginModel(
            name = name,phone = phone,birthday = birthday
        )
        databaseReference = FirebaseDatabase.getInstance().getReference("users")

        viewBinding.userNameEditText.addTextChangedListener(this)

        viewBinding.buttonConfirm.setOnClickListener {
            val key = databaseReference.push().key
            model.key = key
            key?.let {it1 ->
                viewBinding.userNameEditText.removeTextChangedListener(this)
                viewBinding.userNameTextInput.isErrorEnabled = false
                 databaseReference.child(it1).setValue(model).addOnCompleteListener {
                if (it.isSuccessful){
                    val intent = Intent(requireContext(),HomeActivity::class.java)
                    startActivity(intent)
                }
            } }
        }
    }

    override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun afterTextChanged(s: Editable?) {
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (ds in snapshot.children){
                    val model = ds.getValue(LoginModel::class.java)
                    if (model?.username == s.toString()){
                        viewBinding.userNameTextInput.error = "Bunday foydalanuvchi mavjud"
                        viewBinding.buttonConfirm.isEnabled = false
                        return
                    }
                    else{
                        viewBinding.userNameTextInput.isErrorEnabled = false
                        viewBinding.buttonConfirm.isEnabled = true

                        viewBinding.userNameTextInput.helperText = "Bunday nom bo'sh"
                        viewBinding.userNameTextInput.setHelperTextColor(ColorStateList.valueOf(
                            ContextCompat.getColor(requireContext(), R.color.green)))

                    }
                }
                model.username = s.toString()
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

    }


}