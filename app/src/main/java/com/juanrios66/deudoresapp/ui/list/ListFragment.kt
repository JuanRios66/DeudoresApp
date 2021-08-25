package com.juanrios66.deudoresapp.ui.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.juanrios66.deudoresapp.DeudoresApp
import com.juanrios66.deudoresapp.data.local.dao.DebtorDao
import com.juanrios66.deudoresapp.data.local.entities.Debtor
import com.juanrios66.deudoresapp.data.server.DebtorServer
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

        debtorsAdapter = DebtorsAdapter(onItemClicked = { onDebtorItemClicked(it) })
        binding.debtorRecyclerView.apply{
            layoutManager = LinearLayoutManager(this@ListFragment.context)
            adapter = debtorsAdapter
            setHasFixedSize(false)
        }

        loadFromserver()

        return root
    }

    private fun loadFromserver() {
        val db = Firebase.firestore
        db.collection("deudores").get().addOnSuccessListener { result->
            var listDebtors: MutableList<DebtorServer> = arrayListOf()
            for (document in result) {
                Log.d("nombre", document.data.toString())
                listDebtors.add(document.toObject<DebtorServer>())
            }
            debtorsAdapter.appendItem(listDebtors)
        }


    }

    private fun onDebtorItemClicked(debtor: DebtorServer) {
        //findNavController().navigate(ListFragmentDirections.actionNavigationListToNavigationDetail(debtor = debtor))
    }

    /*private fun loadForlocal(){
        val debtorDAO : DebtorDao = DeudoresApp.database.DebtorDao()
        val listDebtors : MutableList<Debtor> = debtorDAO.getDebtors()
        debtorsAdapter.appendItem(listDebtors)
    }*/

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}