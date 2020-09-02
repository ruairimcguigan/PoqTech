package com.demo.poqtech.rx

import com.fruit.app.tech.main.util.schedulers.BaseSchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SchedulerProvider : BaseSchedulerProvider {

  override fun computation() = Schedulers.computation()
  override fun io() = Schedulers.io()
  override fun ui() = AndroidSchedulers.mainThread()
}