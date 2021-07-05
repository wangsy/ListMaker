package com.wangsy.listmaker.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wangsy.listmaker.MainActivity
import com.wangsy.listmaker.R
import com.wangsy.listmaker.models.TaskList
import com.wangsy.listmaker.ui.detail.ListDetailFragment

class ListDetailActivity : AppCompatActivity() {

    lateinit var list: TaskList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_detail_activity)
        // 1
        list = intent.getParcelableExtra(MainActivity.INTENT_LIST_KEY)!!
        // 2
        title = list.name

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ListDetailFragment.newInstance())
                .commitNow()
        }
    }

}