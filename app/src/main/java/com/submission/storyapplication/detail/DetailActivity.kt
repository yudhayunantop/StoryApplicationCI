package com.submission.storyapplication.detail

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.submission.storyapplication.R
import com.submission.storyapplication.core.data.local.entity.StoriesEntity
import com.submission.storyapplication.core.domain.model.Stories
import com.submission.storyapplication.core.utils.DataMapper
import com.submission.storyapplication.core.utils.Resources
import com.submission.storyapplication.databinding.ActivityDetailBinding
import com.submission.storyapplication.main.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {
    private val DetailViewModel: DetailViewModel by viewModel()
    private lateinit var binding: ActivityDetailBinding
    private var isFavorite= MutableLiveData<Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getSupportActionBar()!!.setTitle("Detail Story")
        statusObserver()

//        tinggal masukkan data ke activity
        val data: Stories = intent.getSerializableExtra("data") as Stories
        Glide.with(this)
            .load(data.photoUrl)
            .apply(RequestOptions().override(55, 55))
            .into(binding.ivDetailPhoto)
        binding.tvDetailName.text = data.name
        binding.tvDetailDescription.text = data.description

        DetailViewModel.viewModelScope.launch(Dispatchers.IO) {
            DetailViewModel.isRowExist(data.id).collect{
                when(it){
                    is Resources.Success->{
                        DetailViewModel.viewModelScope.launch(Dispatchers.Main) {
                            if (it.data==true){
                                isFavorite.value=true
                                binding.fabFavorite.setOnClickListener {
                                    isFavorite.value=false
                                    DetailViewModel.viewModelScope.launch(Dispatchers.IO){
                                        DetailViewModel.delete(data).collect{
                                            when(it){
                                                is Resources.Success->{
                                                    DetailViewModel.viewModelScope.launch(Dispatchers.Main) {
                                                        Toast.makeText(applicationContext, "Favorite Deleted!!!", Toast.LENGTH_SHORT)
                                                            .show()
                                                        startActivity(
                                                            Intent(this@DetailActivity, MainActivity::class.java)
                                                        )
                                                        finish()
                                                    }
                                                }
                                                is Resources.Error -> {
                                                    DetailViewModel.viewModelScope.launch(Dispatchers.Main) {
                                                        Toast.makeText(applicationContext, it.message, Toast.LENGTH_SHORT)
                                                            .show()
                                                    }
                                                }
                                                is Resources.Loading-> {}
                                            }
                                        }
                                    }
                                }
                            }else{
                                isFavorite.value=false
                                binding.fabFavorite.setOnClickListener {
                                    isFavorite.value=true
                                    DetailViewModel.viewModelScope.launch(Dispatchers.IO){
                                        DetailViewModel.addFavorite(data).collect{
                                            when(it){
                                                is Resources.Success->{
                                                    DetailViewModel.viewModelScope.launch(Dispatchers.Main) {
                                                        Toast.makeText(applicationContext, "Favorite Added!!!", Toast.LENGTH_SHORT)
                                                            .show()
                                                        startActivity(
                                                            Intent(this@DetailActivity, MainActivity::class.java)
                                                        )
                                                        finish()
                                                    }
                                                }
                                                is Resources.Error -> {
                                                    DetailViewModel.viewModelScope.launch(Dispatchers.Main) {
                                                        Toast.makeText(applicationContext, it.message, Toast.LENGTH_SHORT)
                                                            .show()
                                                    }
                                                }
                                                is Resources.Loading-> {}
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    is Resources.Error -> {
                        DetailViewModel.viewModelScope.launch(Dispatchers.Main) {
                            Toast.makeText(applicationContext, it.message, Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                    is Resources.Loading-> {}
                }
            }
        }
    }

    private fun statusObserver(){
        isFavorite.observe(this@DetailActivity){ favoriteState->
            if(favoriteState){
                binding.fabFavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
            }else{
                binding.fabFavorite.setImageResource(R.drawable.favoritewhite)
            }
        }
    }
}