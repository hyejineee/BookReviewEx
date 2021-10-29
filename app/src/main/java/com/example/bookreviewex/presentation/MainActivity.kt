package com.example.bookreviewex.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookreviewex.databinding.ActivityMainBinding
import com.example.bookreviewex.presentation.viewmodel.ReviewListState
import com.example.bookreviewex.repository.localdb.entity.ReviewEntity
import com.example.bookreviewex.repository.service.model.BookDTO
import com.example.bookreviewex.usecase.InsertReviewListUseCase
import com.example.bookreviewex.presentation.viewmodel.ReviewListViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding
    private val reviewListViewModel:ReviewListViewModel by inject()

    private val insetReviewListUseCase : InsertReviewListUseCase by inject()

    private val adapter = ReviewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        attachListView()
        observeData()

    }

    private fun attachListView(){
        binding.bookList.layoutManager = LinearLayoutManager(this)
        binding.bookList.adapter = adapter
        adapter.submitList(null)

    }

    private fun observeData(){
        reviewListViewModel.reviewList.observe(this){
            Log.d(TAG, "state : ${it}")

            when(it){
                is ReviewListState.Error -> handleErrorState()
                is ReviewListState.Idle -> attachListView()
                is ReviewListState.Loading -> handleLoadingState()
                is ReviewListState.Success -> handleSuccessState(it)
            }


        }
    }

    private fun handleErrorState(){

    }

    private fun handleLoadingState(){

    }

    private fun handleSuccessState(state:ReviewListState.Success){
        adapter.submitList(state.reviewList.toMutableList())
    }



    private val handleSearchButton = {
        val keyword = binding.searchEditTextView.text
        //todo 도서 검색 api로 요청하기
    }

    companion object{
        private const val TAG = "ReviewListActivity"
    }
}