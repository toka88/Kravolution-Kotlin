package com.gorantokovic.kravolution.activities.scheduler

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gorantokovic.kravolution.R
import com.gorantokovic.kravolution.activities.navigation.BaseNavigationFragment
import com.gorantokovic.kravolution.networking.InfiniteApi
import com.gorantokovic.kravolution.networking.Result
import com.gorantokovic.kravolution.views.Loader
import kotlinx.android.synthetic.main.fragment_scheduler.*
import java.util.*

class SchedulerFragment : BaseNavigationFragment() {

    private var firstTimeLoading: Boolean = true
    private lateinit var eventsAdapter: EventsAdapter
    private lateinit var evetsRecyclerView: RecyclerView

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
        val titleTextView: TextView = view.findViewById(R.id.titleTextView)
        titleTextView.text = getString(R.string.scheduler_screen_title)

        val menuButton: ImageButton = view.findViewById(R.id.menuButton)
        menuButton.setOnClickListener {
            openDrawer()
        }

        val calendarView: CalendarView = view.findViewById(R.id.calendarView)
        calendarView.startDate = Date()
        calendarView.onClickListener = {
            Log.i("SchedulerFragment", "Date $it")
        }

        evetsRecyclerView = view.findViewById(R.id.eventsRecyclerView)
        eventsAdapter = EventsAdapter()

        evetsRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        eventsRecyclerView.adapter = eventsAdapter
    }

    // Networking

    private fun fetchEvents() {
        if (firstTimeLoading) {
            firstTimeLoading = false
            context?.let {
                Loader.show(it)
            }
        }
        InfiniteApi.fetchScheduler("1597149216", "1598443200") {
            Loader.remove()
            when (it) {
                is Result.Success -> {
                    val events = it.response.body()?.let { events ->
//                        eventsAdapter.updateData(events)
                    }
                }
                is Result.Failure -> {
                    showToast(it.error.message)
                }
            }
        }
    }
}