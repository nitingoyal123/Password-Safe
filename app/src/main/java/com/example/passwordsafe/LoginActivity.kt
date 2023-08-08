package com.example.passwordsafe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import java.sql.Types.NULL
val LOGIN_TO_SIGNUP_USERNAME = "UserName"

class LoginActivity : AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        SignUpActivity::finish


        val database = MyDatabase
        btnLogIn.setOnClickListener {
            val userName = edtUserNameLogIn.text.toString()
            val password = edtPasswordLogIn.text.toString()
            if (userName != "") {
                if (password != "") {
                    val userNames = database.getDatabase(this).getUserRecordDao().getAllUserNames()
                    if (userName in userNames) {
                        val originalPassword = database.getDatabase(this).getUserRecordDao().getPassword(userName)
                        if (password == originalPassword) {
                            val intent = Intent(this,MainActivity::class.java)
                            intent.putExtra(LOGIN_TO_SIGNUP_USERNAME,userName)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this,"Wrong Password !!!",Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this,"User does not exist...Click Sign up to create new account ",Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this,"Enter Password to Log in",Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Enter Username to Log in", Toast.LENGTH_SHORT).show()
            }
        }
        txtSignUp.setOnClickListener {
            val intent = Intent(this,SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}