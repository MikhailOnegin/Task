package com.example.task.presentation.activity

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.task.R
import com.example.task.di.App
import com.example.task.data.helpers.enums.Error
import com.example.task.domain.pojo.Article
import com.example.task.presentation.adapters.recycler.ArticlesAdapter
import com.example.task.presentation.presenters.NewsPresenter
import com.example.task.presentation.view.NewsView
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(),
    NewsView {

    private var isLoading = false
    private var page = 1

    @Inject lateinit var newsPresenter:NewsPresenter
    private lateinit var rVNews:RecyclerView

    private val adapter = ArticlesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        App.appComponent.inject(this)
        newsPresenter.attachView(this)
        rVNews = recyclerView_main_activity_news
        val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rVNews.layoutManager = layoutManager
        rVNews.adapter = adapter

        val scrollListener: RecyclerView.OnScrollListener =
            object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val visibleItemCount =
                        layoutManager.childCount //смотрим сколько элементов на экране
                    val totalItemCount = layoutManager.itemCount //сколько всего элементов
                    val firstVisibleItems =
                        layoutManager.findFirstVisibleItemPosition();//какая позиция первого элемента

                    if (!isLoading) { //проверяем, грузим мы что-то или нет, эта переменная должна быть вне класса  OnScrollListener
                        if ((visibleItemCount + firstVisibleItems) + 4 >= totalItemCount) {
                            val checkConnect = checkConnection()
                            if(checkConnect){
                                isLoading = true //ставим флаг что мы попросили еще элемены
                                Log.d("eeee","page: $page")
                                newsPresenter.loadNews(page)
                            }
                            else{
                                Toast.makeText(this@MainActivity, getString(R.string.toast_mission_connection), Toast.LENGTH_LONG).show()
                            }
                        }
                    }
                }
            }
        rVNews.addOnScrollListener(scrollListener)
        val checkConnect = checkConnection()

        if(checkConnect){
            newsPresenter.loadNews(page)
        }
        else{
            Toast.makeText(this, getString(R.string.toast_mission_connection),Toast.LENGTH_SHORT).show()
        }
    }

    override fun showNews(news: List<Article>) {
        isLoading = false
        ++page
        adapter.addArticles(news)
    }

    override fun showError(error: Error) {
        when(error){
            Error.LOADING_ERROR -> {
                Toast.makeText(this, getString(R.string.error_loading),Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        isLoading = false
        newsPresenter.clear()
    }

    fun checkConnection():Boolean {
        val manager =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val wifiConnection =
            manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI) // С deprecated сейчас разбираться не буду
        val mobileConnection = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
        return wifiConnection != null && wifiConnection.isConnected || mobileConnection != null && mobileConnection.isConnected
    }

}