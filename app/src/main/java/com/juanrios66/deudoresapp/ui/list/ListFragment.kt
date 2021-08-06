package com.juanrios66.deudoresapp.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.juanrios66.deudoresapp.DeudoresApp
import com.juanrios66.deudoresapp.data.dao.DebtorDao
import com.juanrios66.deudoresapp.data.entities.Debtor
import com.juanrios66.deudoresapp.databinding.FragmentListBinding

class ListFragment : Fragment() {

    private lateinit var listViewModel: ListViewModel
    private var _binding: FragmentListBinding? = null
    private lateinit var debtorsAdapter: DebtorsAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        listViewModel =
            ViewModelProvider(this).get(ListViewModel::class.java)

        _binding = FragmentListBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //val textView: TextView = binding.textDashboard
        listViewModel.text.observe(viewLifecycleOwner, Observer {
        //    textView.text = it
        })

        debtorsAdapter = DebtorsAdapter()
        binding.debtorRecyclerView.apply{
            layoutManager = LinearLayoutManager(this@ListFragment.context)
            adapter = debtorsAdapter
            setHasFixedSize(false)
        }

        val debtorDAO : DebtorDao = DeudoresApp.database.DebtorDao()
        val listDebtors : MutableList<Debtor> = debtorDAO.getDebtors()
        debtorsAdapter.appendItem(listDebtors)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}