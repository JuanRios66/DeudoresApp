package com.juanrios66.deudoresapp.ui.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.juanrios66.deudoresapp.DeudoresApp
import com.juanrios66.deudoresapp.data.dao.DebtorDao
import com.juanrios66.deudoresapp.data.entities.Debtor
import com.juanrios66.deudoresapp.databinding.FragmentCreateBinding
import java.sql.Types

class CreateFragment : Fragment() {

    private lateinit var createViewModel: CreateViewModel
    private var _binding: FragmentCreateBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        createViewModel =
            ViewModelProvider(this).get(CreateViewModel::class.java)

        _binding = FragmentCreateBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //val textView: TextView = binding.textHome
        createViewModel.text.observe(viewLifecycleOwner, Observer {
            //    textView.text = it
        })

        with(binding) {
            createButton.setOnClickListener {
                val name = nameEdittext.text.toString()
                val phone = phoneEdittext.text.toString()
                val amount = amountEdittext.text.toString().toLong()

                createDebtor(name, phone, amount)
            }
        }

        return root
    }

    private fun createDebtor(name: String, phone: String, amount: Long) {
        val debtor = Debtor(id = Types.NULL, name = name, phone = phone, amount = amount)
        val debtorDao: DebtorDao = DeudoresApp.database.DebtorDao()
        debtorDao.createDebtor(debtor)
        cleanviews()
    }

    private fun cleanviews() {
        with(binding) {
            nameEdittext.setText("")
            phoneEdittext.setText("")
            amountEdittext.setText("")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}