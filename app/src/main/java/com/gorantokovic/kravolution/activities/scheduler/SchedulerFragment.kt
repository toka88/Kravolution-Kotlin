package com.gorantokovic.kravolution.activities.scheduler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gorantokovic.kravolution.R
import com.gorantokovic.kravolution.activities.navigation.BaseNavigationFragment
import com.gorantokovic.kravolution.extensions.dayEndsAt
import com.gorantokovic.kravolution.extensions.dayStartsAt
import com.gorantokovic.kravolution.extensions.shiftedForMonths
import com.gorantokovic.kravolution.networking.InfiniteApi
import com.gorantokovic.kravolution.networking.Result
import com.gorantokovic.kravolution.views.Loader
import java.util.*

class SchedulerFragment : BaseNavigationFragment() {

    private var firstTimeLoading: Boolean = true
    private lateinit var eventsAdapter: EventsAdapter
    private lateinit var startDate: Date

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_scheduler, container, false)
        customizeView(view)
        return view
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)

        if (!hidden) {
            fetchEvents()
        }
    }

    override fun getBackgroundDrawableId(): Int {
        return R.drawable.background_primary
    }

    private fun customizeView(view: View) {
        // Title
        val titleTextView: TextView = view.findViewById(R.id.titleTextView)
        titleTextView.text = getString(R.string.scheduler_screen_title)

        // Menu button
        val menuButton: ImageButton = view.findViewById(R.id.menuButton)
        menuButton.setOnClickListener {
            openDrawer()
        }

        // Events recycler view
        val eventsRecyclerView: RecyclerView = view.findViewById(R.id.eventsRecyclerView)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        eventsRecyclerView.layoutManager = layoutManager
        eventsAdapter = EventsAdapter(context!!)
        eventsRecyclerView.adapter = eventsAdapter
        val dividerItemDecoration = DividerItemDecoration(context, layoutManager.orientation)
        eventsRecyclerView.addItemDecoration(dividerItemDecoration)

        // Calendar view
        val calendarView: CalendarView = view.findViewById(R.id.calendarView)
        startDate = calendarView.startDate
        calendarView.onClickListener = {
            eventsAdapter.selectedDate = it
        }
    }

    // Networking

    private fun fetchEvents() {
        if (firstTimeLoading) {
            firstTimeLoading = false
            context?.let {
                Loader.show(it)
            }
        }
        val startsAt = startDate.dayStartsAt()
            .toString()
        val endsAt = startDate.shiftedForMonths(1)
            .dayEndsAt()
            .toString()
        InfiniteApi.fetchScheduler(startsAt, endsAt) {
            Loader.remove()
            when (it) {
                is Result.Success -> {
                    it.response.body()?.let { events ->
                        eventsAdapter.updateData(events)
                    }
                }
                is Result.Failure -> {
                    showToast(it.error.message)
                }
            }
        }
    }
}