package com.example.bookreviewex.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookreviewex.databinding.ActivityMainBinding
import com.example.bookreviewex.presentation.viewmodel.BookListState
import com.example.bookreviewex.presentation.viewmodel.BooksViewModel
import com.example.bookreviewex.presentation.viewmodel.ReviewListState
import com.example.bookreviewex.presentation.viewmodel.ReviewListViewModel
import com.example.bookreviewex.repository.service.model.BookDTO
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding
    private val reviewListViewModel:ReviewListViewModel by inject()
    private val bookListViewModel : BooksViewModel by inject()

    private val reviewAdapter = ReviewAdapter()
    private val bookAdapter = BooksAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        observeData()
    }

    private fun initViews(){

        binding.bookListView.layoutManager = LinearLayoutManager(this)
        binding.bookListView.adapter = bookAdapter
        bookAdapter.submitList(null)

        binding.reviewListView.layoutManager = LinearLayoutManager(this)
        binding.reviewListView.adapter = reviewAdapter
        reviewAdapter.submitList(null)

        binding.searchEditTextView.handleIconClick = handleSearchButtonClick

        handleSearchTextViewClick()
        handleSearchTextViewTextChange()

    }

    private fun handleSearchTextViewTextChange(){
        binding.searchEditTextView.doAfterTextChanged {
            if(it?.length?:0 <=0) changeVisibleListView(false)
        }

    }

    private fun changeVisibleListView( bookListViewVisible :Boolean){
        if(bookListViewVisible.not()){
            bookAdapter.submitList(null)
            bookAdapter.notifyDataSetChanged()
        }

        binding.bookListView.isVisible = bookListViewVisible
        binding.reviewListView.isVisible = !bookListViewVisible
    }

    private fun handleSearchTextViewClick(){
        binding.searchEditTextView.setOnClickListener {
            changeVisibleListView(true)
        }
    }

    private fun observeData(){
        reviewListViewModel.reviewList.observe(this){
            Log.d(TAG, "state : ${it}")

            when(it){
                is ReviewListState.Error -> handleErrorState()
                is ReviewListState.Idle -> changeVisibleListView(false)
                is ReviewListState.Loading -> handleLoadingState()
                is ReviewListState.Success -> handleReviewListSuccessState(it)
            }
        }

        bookListViewModel.book.observe(this){
            Log.d(TAG, "state : ${it}")
            when(it){
                BookListState.Idle -> changeVisibleListView(true)
                BookListState.Loading -> handleLoadingState()
                is BookListState.Success -> handleBookListSuccessState(it)
            }
        }


    }

    private fun handleErrorState(){

    }

    private fun handleLoadingState(){

    }

    private fun handleReviewListSuccessState(state: ReviewListState.Success){
        reviewAdapter.submitList(state.reviewList.toMutableList())
    }

    private fun handleBookListSuccessState(state:BookListState.Success){
        changeVisibleListView(true)
        bookAdapter.submitList(state.books.toMutableList())
    }

    private val handleSearchButtonClick:()->Unit = {
        val keyword = binding.searchEditTextView.text.toString()
        bookListViewModel.searchBook(keyword)
    }

    companion object{
        private const val TAG = "ReviewListActivity"
    }
}