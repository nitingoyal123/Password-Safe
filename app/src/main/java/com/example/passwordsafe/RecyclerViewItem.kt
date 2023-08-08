package com.example.passwordsafe

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_recycler_view_item.*
import kotlinx.android.synthetic.main.dialog_box_update_password_record.*
import kotlinx.android.synthetic.main.recycler_view_item.*

class RecyclerViewItem : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view_item)
        val intent = intent
        val database = MyDatabase.getDatabase(this).getPasswordsDao()
        val id = intent.getIntExtra(ID,0)
        val singleRec = database.selectSingle(id)

        val purposeToShowOnActivity = singleRec.purpose
        val userNameToShowOnActivity = singleRec.userName
        val emailToShowOnActivity = singleRec.email
        val passwordToShowOnActivity = singleRec.password
        val userNameUserRecord = singleRec.userNameUserData


        txtRecyclerViewItemPurpose?.text=purposeToShowOnActivity
        txtRecyclerViewItemUsername?.text=userNameToShowOnActivity
        txtRecyclerViewItemEmail?.text=emailToShowOnActivity
        txtRecyclerViewItemPassword?.text=passwordToShowOnActivity


        txtRecyclerViewItemCancel.setOnClickListener {
            finish()
        }

        txtRecyclerViewItemDelete.setOnClickListener {
            database.deletePasswordData(Passwords(id,userNameUserRecord,purposeToShowOnActivity,emailToShowOnActivity,userNameToShowOnActivity,passwordToShowOnActivity))
            Toast.makeText(this,"Record successfully deleted !!!",Toast.LENGTH_SHORT).show()
            MainActivity::finish
            val intent = Intent(this,MainActivity::class.java)
            intent.putExtra("UserName",userNameUserRecord)
            startActivity(intent)
            finish()
        }

        txtRecyclerViewItemUpdate.setOnClickListener {
            val dialog = Dialog(this)
            dialog.setContentView(R.layout.dialog_box_update_password_record)
            dialog.findViewById<TextView>(R.id.txtUpdateRecyclerViewItemPurpose).text = purposeToShowOnActivity
            val edtUpdateRecyclerViewItemUsername = dialog.findViewById<EditText>(R.id.edtUpdateRecyclerViewItemUsername)
            edtUpdateRecyclerViewItemUsername.text = Editable.Factory.getInstance().newEditable(userNameToShowOnActivity)
            val edtUpdateRecyclerViewItemEmail = dialog.findViewById<EditText>(R.id.edtUpdateRecyclerViewItemEmail)
            edtUpdateRecyclerViewItemEmail.text=Editable.Factory.getInstance().newEditable(emailToShowOnActivity)
            val edtUpdateRecyclerViewItemPassword = dialog.findViewById<EditText>(R.id.edtUpdateRecyclerViewItemPassword)
            edtUpdateRecyclerViewItemPassword.text = Editable.Factory.getInstance().newEditable(passwordToShowOnActivity)
            dialog.show()

            dialog.txtUpdateRecyclerViewItemUpdate.setOnClickListener {
                if (edtUpdateRecyclerViewItemEmail.text.toString() == "" || edtUpdateRecyclerViewItemPassword.text.toString() == "" || edtUpdateRecyclerViewItemUsername.text.toString() == "") {
                    Toast.makeText(this,"None of the field can be empty !!!",Toast.LENGTH_SHORT).show()
                } else {
                    database.updatePasswordData(id,edtUpdateRecyclerViewItemUsername.text.toString(),edtUpdateRecyclerViewItemEmail.text.toString(),edtUpdateRecyclerViewItemPassword.text.toString())
                    txtRecyclerViewItemUsername?.text=Editable.Factory.getInstance().newEditable(edtUpdateRecyclerViewItemUsername.text.toString())
                    txtRecyclerViewItemEmail?.text=Editable.Factory.getInstance().newEditable(edtUpdateRecyclerViewItemEmail.text.toString())
                    txtRecyclerViewItemPassword?.text=Editable.Factory.getInstance().newEditable(edtUpdateRecyclerViewItemPassword.text.toString())
                    dialog.dismiss()
                    Toast.makeText(this,"Record successfully updated !!!",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}