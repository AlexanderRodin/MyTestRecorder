package com.example.testrecorder.listRecord

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.testrecorder.R
import com.example.testrecorder.database.RecordDatabase
import com.example.testrecorder.databinding.FragmentListRecordBinding


class ListRecordFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentListRecordBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_list_record, container, false
        )
        val application = requireNotNull(this.activity).application
        val dataSource = RecordDatabase.getInstance(application).recordDatabaseDao
        val viewModelFactory = ListRecordViewModelFactory(dataSource)
        val listRecordViewVodel = ViewModelProviders.of(this,
            viewModelFactory).get(ListRecordViewModel::class.java)
        binding.listRecordViewModel = listRecordViewVodel
        val adapter = ListRecordAdapter()
        binding.recyclerView.adapter = adapter
        listRecordViewVodel.records.observe(viewLifecycleOwner, Observer {
            it?.let { adapter.data = it }
        })
        binding.lifecycleOwner = this
        return binding.root
    }
}