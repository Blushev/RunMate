package com.example.runmate.presenter.eventList

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.runmate.R
import com.example.runmate.databinding.FragmentEventListBinding
import com.example.runmate.di.ViewModelFactory
import com.example.runmate.di.appComponent
import javax.inject.Inject

class EventListFragment: Fragment(R.layout.fragment_event_list) {

    private val binding: FragmentEventListBinding by viewBinding()

    private var listLimit: Int? = null

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val adapter = EventListAdapter()

    private val eventsViewModel: EventsViewModel by viewModels() { viewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding.eventListRecyclerView) {
            adapter = this@EventListFragment.adapter
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(createItemDecorator())
        }

        eventsViewModel.events.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        if (listLimit != null) {
            eventsViewModel.getEventsLimited(listLimit ?: 3)
        } else {
            eventsViewModel.getAllEvents()
        }
    }

    private fun createItemDecorator() =
        DividerItemDecoration(requireContext(), RecyclerView.VERTICAL).apply {
            ContextCompat.getDrawable(requireContext(), R.drawable.list_item_divider)
                ?.let { this@apply.setDrawable(it) }
        }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.inject(this)
    }

    companion object {
        @JvmStatic
        fun newInstance(listLimit: Int? = null) =
            EventListFragment().apply {
                this.listLimit = listLimit
            }
    }
}