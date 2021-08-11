package com.juanrios66.deudoresapp.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import com.juanrios66.deudoresapp.R
import com.juanrios66.deudoresapp.databinding.ActivityRegisterBinding
import com.juanrios66.deudoresapp.utils.emailValidator
import com.juanrios66.deudoresapp.utils.nameValidator
import com.juanrios66.deudoresapp.utils.passValidator

class RegisterActivity : AppCompatActivity() {

    private lateinit var registerBinding: ActivityRegisterBinding
    private var condicion = booleanArrayOf(false, false, false)

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
                    val bundle = Bundle()
                    //val user = Users(name, email, password)
                    //bundle.putSerializable("user", user)
                    intent.putExtras(bundle)
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
                condicion[1] = false
            } else {
                registerBinding.username.error = null
                condicion[1] = true
            }
            registerBinding.registrar.isEnabled = condicion.all { it }
        }

        registerBinding.textEmail.doAfterTextChanged {
            if (!emailValidator(registerBinding.textEmail.text.toString())) {
                registerBinding.email.error = getString(R.string.email_invalido)
                condicion[2] = false
            } else {
                registerBinding.email.error = null
                condicion[2] = true
            }
            registerBinding.registrar.isEnabled = condicion.all { it }
        }

        registerBinding.textPassword.doAfterTextChanged {
            if (!passValidator(registerBinding.textPassword.text.toString())) {
                registerBinding.password.helperText = getString(R.string.digits6)
                condicion[0] = false
            } else {
                registerBinding.password.helperText = null
                condicion[0] = true
            }
            registerBinding.registrar.isEnabled = condicion.all { it }
        }
    }
}