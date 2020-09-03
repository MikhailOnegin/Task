package com.example.task.presentation.presenters

import android.util.Log
import com.example.task.data.helpers.enums.Error
import com.example.task.domain.repositories.NewsRepository
import com.example.task.presentation.view.NewsView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.lang.ref.WeakReference

class NewsPresenterImpl(private val data: NewsRepository): NewsPresenter {

    private val compositeDisposable = CompositeDisposable()
    private var viewState:WeakReference<NewsView>? = null
    override fun attachView(view:NewsView){
        viewState = WeakReference(view)
    }
    override fun loadNews(page:Int) {
        val disposable = data.getEverything(page).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe({ everything ->
                everything.articles?.let {
                    viewState?.get()?.showNews(it.toList())
                }
            },{
                viewState?.get()?.showError(Error.LOADING_ERROR)
                Log.d("eeee" ,"${it.message}")
            })
        compositeDisposable.add(disposable)
    }

    override fun clear(){
        compositeDisposable.dispose()
    }

}