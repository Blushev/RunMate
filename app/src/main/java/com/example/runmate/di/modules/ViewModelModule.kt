package com.example.runmate.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.runmate.di.ViewModelFactory
import com.example.runmate.di.ViewModelKey
import com.example.runmate.presenter.eventList.EventsViewModel
import com.example.runmate.presenter.main.MainViewModel
import com.example.runmate.presenter.progressTotal.ProgressTotalViewModel
import com.example.runmate.presenter.progressWeek.ProgressWeekViewModel
import com.example.runmate.presenter.running.TrainingViewModel
import com.example.runmate.presenter.welcome.WelcomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(WelcomeViewModel::class)
    abstract fun bindWelcomeViewModel(viewModel: WelcomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EventsViewModel::class)
    abstract fun bindEventsViewModel(viewModel: EventsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProgressWeekViewModel::class)
    abstract fun bindProgressWeekViewModel(viewModel: ProgressWeekViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProgressTotalViewModel::class)
    abstract fun bindProgressTotalViewModel(viewModel: ProgressTotalViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TrainingViewModel::class)
    abstract fun bindTrainingViewModel(viewModel: TrainingViewModel): ViewModel
}