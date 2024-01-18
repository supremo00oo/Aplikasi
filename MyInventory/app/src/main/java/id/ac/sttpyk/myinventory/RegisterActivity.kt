package id.ac.sttpyk.myinventory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        supportActionBar?.hide()

        txtLoginListener()
        btnBackRegisterListener()
        btnRegisterListener()
    }

    private fun btnBackRegisterListener(){
        rg1.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun txtLoginListener(){
        txtlog.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun btnRegisterListener(){
        rg1.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}