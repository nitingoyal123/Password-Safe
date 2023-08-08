package com.example.passwordsafe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Database
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val database = MyDatabase.getDatabase(this).getUserRecordDao()
        txtLogIn.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }

        btnSignUp.setOnClickListener {
            val userName = edtUserNameSignUp.text.toString()
            val password = edtPasswordSignUp.text.toString()
            val confirmPassword = edtConfirmPasswordSignUp.text.toString()
            LoginActivity::finish

            if (userName != "") {
                if (password != "") {
                    if (confirmPassword != "") {
                        val userNames = database.getAllUserNames()
                        if (userName in userNames) {
                            Toast.makeText(this,"Username already exist !!!",Toast.LENGTH_SHORT).show()
                        } else {
                            if (password == confirmPassword) {
                                database.insertUserRecord(UserRecord(userName,password))
                                val intent = Intent(this,LoginActivity::class.java)
                                startActivity(intent)
                                finish()
                            } else {
                                Toast.makeText(this,"Passwords are not same !!!",Toast.LENGTH_SHORT).show()
                            }
                        }
                    } else {
                        Toast.makeText(this,"Confirm your password to continue...",Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this,"Create password to continue...",Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this,"Create Username to continue...",Toast.LENGTH_SHORT).show()
            }
        }
    }
}