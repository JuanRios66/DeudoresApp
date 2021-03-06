package com.juanrios66.deudoresapp.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.juanrios66.deudoresapp.R
import com.juanrios66.deudoresapp.databinding.ActivityRegisterBinding
import com.juanrios66.deudoresapp.utils.emailValidator
import com.juanrios66.deudoresapp.utils.nameValidator
import com.juanrios66.deudoresapp.utils.passValidator

class RegisterActivity : AppCompatActivity() {

    private lateinit var registerBinding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(registerBinding.root)

        registerBinding.textRepeatPassword.setOnClickListener {
            if (registerBinding.repeatPassword.error == getString(R.string.no_coincidencia)) {
                registerBinding.repeatPassword.error = null
                registerBinding.textRepeatPassword.setText("")
            }
        }

        registerBinding.registrar.setOnClickListener {
            val name = registerBinding.textUsername.text.toString()
            val email = registerBinding.textEmail.text.toString()
            val password = registerBinding.textPassword.text.toString()
            if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                if (password == registerBinding.textRepeatPassword.text.toString()) {
                    registerBinding.repeatPassword.error = null
                    val intent = Intent()
                    intent.putExtra("name", name)
                    intent.putExtra("email", email)
                    intent.putExtra("password", password)
                    setResult(Activity.RESULT_OK, intent)
                    finish()
                } else {
                    registerBinding.repeatPassword.error = getString(R.string.no_coincidencia)
                }
            } else {
                Toast.makeText(this, getString(R.string.errorregister), Toast.LENGTH_LONG).show()
            }
        }

        registerBinding.textUsername.doAfterTextChanged {
            if (!nameValidator(registerBinding.textUsername.text.toString())) {
                registerBinding.username.error = getString(R.string.digits8)
            } else {
                registerBinding.username.error = null
            }
        }

        registerBinding.textEmail.doAfterTextChanged {
            if (!emailValidator(registerBinding.textEmail.text.toString())) {
                registerBinding.email.error = getString(R.string.email_invalido)
            } else {
                registerBinding.email.error = null
            }
        }

        registerBinding.textPassword.doAfterTextChanged {
            if (!passValidator(registerBinding.textPassword.text.toString())) {
                registerBinding.password.error = getString(R.string.digits6)
            } else {
                registerBinding.password.error = null
            }
        }
    }
}
