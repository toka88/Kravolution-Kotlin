package com.gorantokovic.kravolution.activities.scheduler

import android.content.res.Resources
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gorantokovic.kravolution.R
import com.gorantokovic.kravolution.helpers.DateFormatter
import com.gorantokovic.kravolution.models.Event

class EventsAdapter : RecyclerView.Adapter<EventsAdapter.EventItemHolder>() {

    private var events: List<Event> = listOf()
    private

    // Interface

    fun updateData(events: List<Event>) {
        this.events = events
        notifyDataSetChanged()
    }

    // Init

    init {
        Log.d("EventsAdapter", "Initialized")
    }

    // Adapter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventItemHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_event, parent, false)
        return EventItemHolder(view)
    }

    override fun getItemCount(): Int {
        return events.count()
    }

    override fun onBindViewHolder(holder: EventItemHolder, position: Int) {
        holder.update(events[position])
    }

    class EventItemHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val startsAtTextView: TextView = itemView.findViewById(R.id.startAtTextView)
        private val endsAtTextView: TextView = itemView.findViewById(R.id.endAtTextView)
        private val verticalIndicator: View = itemView.findViewById(R.id.verticalIndicatorView)
        private val eventTitleTextView: TextView = itemView.findViewById(R.id.eventTitleTextView)
        private val eventStatusTextView: TextView = itemView.findViewById(R.id.eventStatusTextView)

        fun update(event: Event) {
            // Time
            startsAtTextView.text = DateFormatter.getTime(event.startsAt)
            endsAtTextView.text = DateFormatter.getTime(event.endsAt)

            // Indicator
//            if (event.member) {
//                verticalIndicator.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent))
//            } else {
//                verticalIndicator.setBackgroundColor(Color.DKGRAY)
//            }

            // Info
            eventTitleTextView.text = event.name
            if (event.canceled ){
                eventStatusTextView.text = Resources.getSystem().getString(R.string.event_status_canceled)
            } else {
                eventStatusTextView.text = ""
            }
        }
    }
}