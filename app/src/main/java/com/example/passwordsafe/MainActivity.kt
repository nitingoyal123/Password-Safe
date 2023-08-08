package com.example.passwordsafe

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_box_add_user_record.*

val ID = "id"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val context = this
        val intent = intent
        val userRecordUserName = intent.getStringExtra(LOGIN_TO_SIGNUP_USERNAME).toString()

        val database = MyDatabase.getDatabase(this).getPasswordsDao()
        val allRecords : ArrayList<Passwords> = database.selectAllPasswords(userRecordUserName) as ArrayList
        val purposes : ArrayList<String> = database.selectAllPurposes(userRecordUserName) as ArrayList<String>
        val adapter = PasswordsAdapter(this,purposes)
        recyclerView.layoutManager=LinearLayoutManager(this)
        recyclerView.adapter=adapter

        fabAddData.setOnClickListener{
            val dialog = Dialog(this)
            dialog.setContentView(R.layout.dialog_box_add_user_record)
            dialog.show()
            dialog.txtaddData.setOnClickListener {
                val edtPurpose = dialog.edtAddPurpose
                val edtUsername = dialog.edtAddUsername
                val edtEmail = dialog.edtAddEmail
                val edtPassword = dialog.edtAddPassword

                if (edtEmail.text.toString() ==  "" || edtPassword.text.toString() == "" || edtPurpose.text.toString() == "" || edtUsername.text.toString() == "") {
                    Toast.makeText(this,"None of the field can be empty !!!",Toast.LENGTH_SHORT).show()
                } else {
                    database.addPasswordData(Passwords(allRecords.size+1,userRecordUserName,edtPurpose.text.toString(),edtEmail.text.toString(),edtUsername.text.toString(),edtPassword.text.toString()))
                    Toast.makeText(this,"Data successfully added !!!",Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                    allRecords.add(Passwords(allRecords.size+1,userRecordUserName,edtPurpose.text.toString(),edtEmail.text.toString(),edtUsername.text.toString(),edtPassword.text.toString()))
                    purposes.add(edtPurpose.text.toString())
                    adapter.notifyItemInserted(allRecords.size)
                }

            }
        }

        adapter.setOnItemClickListener(object : PasswordsAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                val intent = Intent(context,RecyclerViewItem::class.java)
                intent.putExtra(ID,allRecords[position].id)
                startActivity(intent)
            }

        })



    }
}