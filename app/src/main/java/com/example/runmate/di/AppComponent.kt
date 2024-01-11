package com.example.runmate.di

import android.app.Application
import com.example.runmate.di.modules.AppModule
import com.example.runmate.presenter.bottomMenu.BottomMainMenuFragment
import com.example.runmate.presenter.eventList.EventListFragment
import com.example.runmate.presenter.home.HomePageFragment
import com.example.runmate.presenter.main.MainFragment
import com.example.runmate.presenter.profile.ProfilePageFragment
import com.example.runmate.presenter.progressTotal.ProgressTotalFragment
import com.example.runmate.presenter.progressWeek.ProgressWeekFragment
import com.example.runmate.presenter.running.NowInRunningFragment
import com.example.runmate.presenter.welcome.WelcomePageFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [ AppModule::class ]
)
@Singleton
interface AppComponent {
    fun inject(fragment: MainFragment)
    fun inject(fragment: HomePageFragment)
    fun inject(fragment: ProfilePageFragment)
    fun inject(fragment: BottomMainMenuFragment)
    fun inject(fragment: EventListFragment)
    fun inject(fragment: WelcomePageFragment)
    fun inject(fragment: ProgressWeekFragment)
    fun inject(fragment: ProgressTotalFragment)
    fun inject(fragment: NowInRunningFragment)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): Builder

        fun build(): AppComponent
    }
}