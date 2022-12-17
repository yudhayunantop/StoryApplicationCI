package com.submission.storyapplication.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.submission.storyapplication.R
import com.submission.storyapplication.add.AddActivity
import com.submission.storyapplication.core.data.local.entity.StoriesEntity
import com.submission.storyapplication.core.domain.model.Stories
import com.submission.storyapplication.core.ui.StoriesAdapter
import com.submission.storyapplication.databinding.ActivityMainBinding
import com.submission.storyapplication.core.utils.Preferences
import com.submission.storyapplication.core.utils.Preferences.clearData
import com.submission.storyapplication.detail.DetailActivity
import com.submission.storyapplication.login.LoginActivity
import com.submission.storyapplication.maps.MapsActivity
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getSupportActionBar()!!.setTitle("List Story");

        Preferences.init(this)

        binding.listStory.layoutManager = LinearLayoutManager(this)

        getAllStories()

        binding.fabCreate.setOnClickListener {
            intent = Intent(this@MainActivity, AddActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getAllStories(){
        val adapter= StoriesAdapter()
        binding.listStory.adapter=adapter
        mainViewModel.viewModelScope.launch {
            mainViewModel.storiesFlow.collectLatest {
                adapter.submitData(it)
            }
        }

//        mainViewModel.stories.observe(this, {
//            adapter.submitData(lifecycle, it)
//        })

        adapter.setOnClickListener(object : StoriesAdapter.OnClickListener{
            override fun onItemClick(stories: Stories) {
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra("data", stories)
                startActivity(intent)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.maps ->{
                val i = Intent(this, MapsActivity::class.java)
                startActivity(i)
                return true
            }
            R.id.logout -> {
                val i = Intent(this, LoginActivity::class.java)
                clearData()
                startActivity(i)
                finish()
                return true
            }
            R.id.favorite ->{
                val uri = Uri.parse("storyapplication://favorite")
                startActivity(Intent(Intent.ACTION_VIEW, uri))
                return true
            }
            else -> return true
        }
    }
}