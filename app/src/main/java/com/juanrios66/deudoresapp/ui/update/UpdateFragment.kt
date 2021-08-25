package com.juanrios66.deudoresapp.ui.update

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.juanrios66.deudoresapp.DeudoresApp
import com.juanrios66.deudoresapp.R
import com.juanrios66.deudoresapp.data.local.dao.DebtorDao
import com.juanrios66.deudoresapp.data.local.entities.Debtor
import com.juanrios66.deudoresapp.data.server.DebtorServer
import com.juanrios66.deudoresapp.databinding.FragmentUpdateBinding

class UpdateFragment : Fragment() {

    companion object {
        fun newInstance() = UpdateFragment()
    }

    private lateinit var viewModel: UpdateViewModel
    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!
    private var isSearching = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)
        val root: View = binding.root

        var idDebtor:String? = ""

        binding.updateButton.setOnClickListener {

            val name = binding.nameEditText.text.toString()
            if(isSearching){
                val db = Firebase.firestore
                db.collection("deudores").get().addOnSuccessListener { result ->
                    var debtorExist =  false
                    for (document in result) {
                        val debtor = document.toObject<DebtorServer>()
                        if (debtor.name == name) {
                            idDebtor = debtor.id
                            debtorExist = true
                            with(binding) {
                                binding.amountEditText.setText(debtor.amount.toString())
                                binding.phoneEditText.setText(debtor.phone)
                                binding.updateButton.text = getString(R.string.title_update)
                                isSearching = false
                            }
                        }
                    }
                    if(!debtorExist)
                        Toast.makeText(requireContext(), "Deudor no Existe", Toast.LENGTH_SHORT).show()
                }
            }else { //actualizar
                val documentUpdate = HashMap<String, Any>()
                documentUpdate["name"] = binding.nameEditText.text.toString()
                documentUpdate["amount"] = binding.amountEditText.text.toString()
                documentUpdate["phone"] = binding.phoneEditText.text.toString()

                val db = Firebase.firestore
                idDebtor?.let { id -> db.collection("deudores").document(id).update(documentUpdate).addOnSuccessListener {
                    Toast.makeText(requireContext(), "Deudor Actualizado con Exito", Toast.LENGTH_SHORT).show()
                } }

                binding.updateButton.text = getString(R.string.title_read)
                isSearching = true
                cleanWidgets()

            }

        }
        return root
    }

    private fun cleanWidgets() {
        with(binding) {
            nameEditText.setText("")
            phoneEditText.setText("")
            amountEditText.setText("")
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UpdateViewModel::class.java)
        // TODO: Use the ViewModel
    }

    //Esta funcion no es usada porque es para dao local
    /*private fun searchinginlocal(){
        var debtorDao: DebtorDao = DeudoresApp.database.DebtorDao()
        var name = binding.nameEditText.text.toString()
        var idDebtor = 0
        if (isSearching) {
            var debtor: Debtor = debtorDao.readDebtor(name)
            if (debtor != null) {
                idDebtor = debtor.id
                binding.amountEditText.setText(debtor.amount.toString())
                binding.phoneEditText.setText(debtor.phone)
                binding.updateButton.text = getString(R.string.title_update)
                isSearching = false
            } else {
                Toast.makeText(requireContext(), "No existe", Toast.LENGTH_LONG).show()
                cleanWidgets()
            }
        } else {
            val debtor = Debtor(
                id = idDebtor,
                name = binding.nameEditText.text.toString(),
                amount = binding.amountEditText.text.toString().toLong(),
                phone = binding.phoneEditText.text.toString()
            )
            debtorDao.updateDebtor(debtor)
            binding.updateButton.text = getString(R.string.title_read)
            isSearching = true
            cleanWidgets()
            Toast.makeText(requireContext(), "Deudor actualizado", Toast.LENGTH_LONG).show()
        }
    }*/

}