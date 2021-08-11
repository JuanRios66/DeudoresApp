package com.juanrios66.deudoresapp.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged

import com.juanrios66.deudoresapp.MainActivity
import com.juanrios66.deudoresapp.R
import com.juanrios66.deudoresapp.databinding.ActivityLoginBinding
import com.juanrios66.deudoresapp.utils.EMPTY
import com.juanrios66.deudoresapp.utils.emailValidator
import com.juanrios66.deudoresapp.utils.passValidator

class LoginActivity : AppCompatActivity() {

    private lateinit var loginBinding: ActivityLoginBinding
    private var condicion = booleanArrayOf(false, false)
    private var banEmail = false
    private var banPass = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)

        if (intent.hasExtra("user")) {
            //TODO agregar el usuario que viene de la actividad register a la Database
        }

        loginBinding.textPassword.setOnClickListener {
            if (loginBinding.password.error == getString(R.string.errorpass)) {
                loginBinding.password.error = null
                loginBinding.textPassword.setText(EMPTY)
            }
        }

        loginBinding.mostrar.setOnClickListener {
            if (!loginBinding.mostrar.isChecked) {
                loginBinding.textPassword.transformationMethod =
                    PasswordTransformationMethod.getInstance()
            } else {
                loginBinding.textPassword.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
            }
        }

        /*Verifica cada momneto como cambia lo que hay en la barra de correo
        * para verificar si es un correo valido o no*/
        loginBinding.textPassword.doAfterTextChanged {
            if (!passValidator(loginBinding.textPassword.text.toString())) {
                loginBinding.password.error = getString(R.string.digits6)
                condicion[1] = false
            } else {
                loginBinding.password.error = null
                condicion[1] = true
            }
            loginBinding.send.isEnabled = condicion.all { it }
        }

        /*Verifica cada momento como cambia lo que hay en la barra de contraseña
        * para verificar si es valida o no*/
        loginBinding.textEmail.doAfterTextChanged {
            if (!emailValidator(loginBinding.textEmail.text.toString())) {
                loginBinding.password.error = getString(R.string.email_invalido)
                condicion[0] = false
            } else {
                loginBinding.password.error = null
                condicion[0] = true
            }
            loginBinding.send.isEnabled = condicion.all { it }
        }

        /*Funcion que se ejecuta cuando damos en el boton de inicio de sesion*/
        loginBinding.send.setOnClickListener {
            //TODO primero verificar que los usuarios se encuentren regstrados

            if (banEmail) {
                if (banPass) {
                    banEmail = false
                    banPass = false
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("email", loginBinding.textEmail.text.toString())
                    intent.putExtra("pass", loginBinding.textPassword.text.toString())
                    startActivity(intent)
                    finish()
                } else {
                    loginBinding.password.error = getString(R.string.errorpass)
                }
            } else {
                Toast.makeText(this, getString(R.string.errorlogin), Toast.LENGTH_LONG).show()
            }
        }

        loginBinding.register.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivityForResult(intent, 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            //TODO se toma el usuario enviado de registro y se agrega a la base datos
            //val user: Users = data!!.getSerializableExtra("user") as Users
            loginBinding.textEmail.setText(EMPTY)
            loginBinding.textPassword.setText(EMPTY)
            //usuarios.add(user)
            //guardarusuario(user.nickname.toString(), user.email.toString(), user.password.toString())
        }
    }


}