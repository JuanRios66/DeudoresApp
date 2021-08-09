package com.juanrios66.deudoresapp.ui.delete

import android.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.system.Os.accept
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.juanrios66.deudoresapp.DeudoresApp
import com.juanrios66.deudoresapp.R
import com.juanrios66.deudoresapp.data.dao.DebtorDao
import com.juanrios66.deudoresapp.data.entities.Debtor
import com.juanrios66.deudoresapp.databinding.FragmentDeleteBinding
import com.juanrios66.deudoresapp.databinding.FragmentReadBinding
import com.juanrios66.deudoresapp.ui.read.ReadViewModel

class DeleteFragment : Fragment() {

    companion object {
        fun newInstance() = DeleteFragment()
    }

    private lateinit var viewModel: DeleteViewModel
    private var _binding: FragmentDeleteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDeleteBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.deleteButton.setOnClickListener {
            deleteDebtor(binding.nameEditText.text.toString())
        }

        return root
    }

    private fun deleteDebtor(name: String) {
        val debtorDao : DebtorDao = DeudoresApp.database.DebtorDao()
        val debtor : Debtor = debtorDao.readDebtor(name)

        if (debtor != null) {
            val alertDialog: AlertDialog? = activity?.let {
                val builder = AlertDialog.Builder(it)
                builder.apply {
                    setTitle(R.string.title_delete)
                    setMessage("Desea eliminar a " + debtor.name + ", su deuda es: " +
                            debtor.amount.toString() + "?")
                    setPositiveButton(R.string.accept) {
                        dialog, id -> debtorDao.deleteDebtor(debtor)
                        Toast.makeText(requireContext(), "Deudor eliminado", Toast.LENGTH_LONG).show()
                        binding.nameEditText.setText("")
                    }
                    setNegativeButton(R.string.cancel) {
                        dialog, id ->
                    }
                }
                builder.create()
            }
            alertDialog?.show()
        } else {
            Toast.makeText(requireContext(), "Deudor no existe", Toast.LENGTH_LONG).show()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DeleteViewModel::class.java)
        // TODO: Use the ViewModel
    }

}