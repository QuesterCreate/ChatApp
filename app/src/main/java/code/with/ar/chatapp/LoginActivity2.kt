package code.with.ar.chatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity2 : AppCompatActivity() {
    private lateinit var edtEmail:EditText
    private lateinit var edtPassword:EditText
    private lateinit var btnLogIn:Button
    private lateinit var btnSignUp: Button
    private lateinit var mAuth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login2)

        supportActionBar?.hide()


        mAuth= FirebaseAuth.getInstance()
        edtEmail=findViewById(R.id.editTextemail)
        edtPassword=findViewById(R.id.editTextpassword)
        btnLogIn=findViewById(R.id.buttonLogin)
        btnSignUp=findViewById(R.id.buttonSign_in)

        btnSignUp.setOnClickListener {
            val intent = Intent(this,SignUpActivity::class.java )
            startActivity(intent)
        }

        btnLogIn.setOnClickListener {
            val email= edtEmail.text.toString()
            val password= edtPassword.text.toString()

            login(email,password)
        }



    }

    private fun login(email:String, password:String){
//logic in logging user

        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                   //Code for logging in user
                    val intent= Intent(this@LoginActivity2, MainActivity::class.java)

                    finish()
                    startActivity(intent)

                } else {
              Toast.makeText(this@LoginActivity2, "user doesn't exist" , Toast.LENGTH_SHORT).show()
                }
            }



    }







}