package com.omkumis.projectpam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.omkumis.projectpam.databinding.ActivityRegisterBinding
import java.util.regex.Pattern

class RegisterActivity : AppCompatActivity() {
    lateinit var binding : ActivityRegisterBinding
    lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.tvToLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.btnRegister.setOnClickListener{
            val username = binding.edtUsernameRegister.text.toString()
            val email = binding.edtEmailRegister.text.toString()
            val password = binding.edtPasswordRegister.text.toString()

            //Validasi Email dan Password
            if (username.isEmpty()){
                binding.edtUsernameRegister.error = "Berikan UserName yang Valid"
                binding.edtEmailRegister.requestFocus()
                binding.edtPasswordRegister.requestFocus()
                return@setOnClickListener
            }
            if (email.isEmpty()){
                binding.edtUsernameRegister.requestFocus()
                binding.edtEmailRegister.error = "Email Harus Diisi"
                binding.edtPasswordRegister.requestFocus()
                return@setOnClickListener
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.edtUsernameRegister.requestFocus()
                binding.edtEmailRegister.error = "Email Tidak Valid"
                binding.edtPasswordRegister.requestFocus()
                return@setOnClickListener
            }
            if (password.isEmpty()){
                binding.edtUsernameRegister.requestFocus()
                binding.edtEmailRegister.requestFocus()
                binding.edtPasswordRegister.error = "Password Harus Diisi"
                return@setOnClickListener
            }
            if (password.length < 6){
                binding.edtUsernameRegister.requestFocus()
                binding.edtEmailRegister.requestFocus()
                binding.edtPasswordRegister.error = "Password Harus Lebih dari 6 Karakter"
                return@setOnClickListener
            }

            RegisterFirebase(username, email, password)
        }
    }

    private fun RegisterFirebase(username: String, email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){
                if (it.isSuccessful){
                    val user = FirebaseAuth.getInstance().currentUser
                    val profilUpdate = UserProfileChangeRequest.Builder()
                        .setDisplayName(username)
                        .build()
                    user?.updateProfile(profilUpdate)
                    Toast.makeText(this, "Register Sukses", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
