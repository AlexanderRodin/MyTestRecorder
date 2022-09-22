package com.example.testrecorder.listRecord

import androidx.lifecycle.ViewModel
import com.example.testrecorder.database.RecordDatabaseDao

class ListRecordViewModel(
    dataSource: RecordDatabaseDao
) : ViewModel() {
    val database = dataSource
    val records = database.getAllRecords()
}