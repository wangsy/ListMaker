package com.wangsy.listmaker.ui.detail

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import com.wangsy.listmaker.MainActivity
import com.wangsy.listmaker.R
import com.wangsy.listmaker.databinding.ListDetailActivityBinding
import com.wangsy.listmaker.models.TaskList
import com.wangsy.listmaker.ui.main.MainViewModel
import com.wangsy.listmaker.ui.main.MainViewModelFactory

class ListDetailActivity : AppCompatActivity() {
    lateinit var binding: ListDetailActivityBinding
    lateinit var viewModel: MainViewModel
    lateinit var fragment: ListDetailFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ListDetailActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.addTaskButton.setOnClickListener {
            showCreateTaskDialog()
        }

        viewModel = ViewModelProvider(this,  MainViewModelFactory(PreferenceManager.getDefaultSharedPreferences(this))).get(MainViewModel::class.java)
        viewModel.list = intent.getParcelableExtra(MainActivity.INTENT_LIST_KEY)!!

        // 2
        title = viewModel.list.name

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.detail_container, ListDetailFragment.newInstance())
                .commitNow()
        }
    }
    private fun showCreateTaskDialog() {
        //1
        val taskEditText = EditText(this)
        taskEditText.inputType = InputType.TYPE_CLASS_TEXT

        //2
        AlertDialog.Builder(this)
            .setTitle(R.string.task_to_add)
            .setView(taskEditText)
            .setPositiveButton(R.string.add_task) { dialog, _ ->
                // 3
                val task = taskEditText.text.toString()
                // 4
                viewModel.addTask(task)
                //5
                dialog.dismiss()
            }
            //6
            .create()
            .show()
    }

    override fun onBackPressed() {
        val bundle = Bundle()
        bundle.putParcelable(MainActivity.INTENT_LIST_KEY, viewModel.list)

        val intent = Intent()
        intent.putExtras(bundle)
        setResult(Activity.RESULT_OK, intent)
        super.onBackPressed()
    }

}