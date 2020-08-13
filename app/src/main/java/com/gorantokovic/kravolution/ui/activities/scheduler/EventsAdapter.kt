package com.gorantokovic.kravolution.ui.activities.scheduler

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gorantokovic.kravolution.R
import com.gorantokovic.kravolution.extensions.dayEndsAt
import com.gorantokovic.kravolution.extensions.dayStartsAt
import com.gorantokovic.kravolution.helpers.DateFormatter
import com.gorantokovic.kravolution.models.Event
import java.util.*

class EventsAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private enum class EventRowType {
        NoEvents, Event;
    }

    private var events: List<Event> = listOf()
    private var filteredEvents: List<Event> = listOf()

    // Interface
    var selectedDate: Date = Date()
        set(value) {
            field = value
            filterEvents()
        }

    fun updateData(events: List<Event>) {
        this.events = events
        filterEvents()
    }

    // Init

    init {
        Log.d("EventsAdapter", "Initialized")
    }

    // Adapter

    override fun getItemViewType(position: Int): Int {
        if (filteredEvents.isEmpty()) {
            return EventRowType.NoEvents.ordinal
        }
        return EventRowType.Event.ordinal
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == EventRowType.NoEvents.ordinal) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.row_no_events, parent, false)
            return NoEventViewHolder(view)
        }

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_event, parent, false)
        return EventViewHolder(view)
    }

    override fun getItemCount(): Int {
        if (filteredEvents.isNotEmpty()) {
            return filteredEvents.count()
        }
        return 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is EventViewHolder) {
            holder.update(context, filteredEvents[position])
        }
    }

    class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val startsAtTextView: TextView = itemView.findViewById(R.id.startAtTextView)
        private val endsAtTextView: TextView = itemView.findViewById(R.id.endAtTextView)
        private val verticalIndicator: View = itemView.findViewById(R.id.verticalIndicatorView)
        private val eventTitleTextView: TextView = itemView.findViewById(R.id.eventTitleTextView)
        private val eventStatusTextView: TextView = itemView.findViewById(R.id.eventStatusTextView)

        fun update(context: Context, event: Event) {
            // Time
            startsAtTextView.text = DateFormatter.getTime(event.startsAt)
            endsAtTextView.text = DateFormatter.getTime(event.endsAt)

            // Indicator
            if (event.member) {
                verticalIndicator.setBackgroundColor(context.getColor(R.color.colorAccent))
            } else {
                verticalIndicator.setBackgroundColor(Color.DKGRAY)
            }

            // Info
            eventTitleTextView.text = event.name
            if (event.canceled) {
                eventStatusTextView.text = context.getString(R.string.event_status_canceled)
                itemView.alpha = 0.7F
            } else {
                itemView.alpha = 1F
                eventStatusTextView.text = ""
            }
        }
    }

    class NoEventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private fun filterEvents() {
        val startTimestamp = selectedDate.dayStartsAt()
        val endTimestamp = selectedDate.dayEndsAt()
        filteredEvents = events.filter {
            it.startsAt >= startTimestamp && it.startsAt <= endTimestamp
        }.sortedBy {
            it.startsAt
        }

        notifyDataSetChanged()
    }
}