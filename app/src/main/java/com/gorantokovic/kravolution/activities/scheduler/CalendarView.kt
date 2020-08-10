package com.gorantokovic.kravolution.activities.scheduler

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gorantokovic.kravolution.R
import com.gorantokovic.kravolution.helpers.DateFormatter
import java.util.*
import kotlin.collections.ArrayList

class CalendarView(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    private val recyclerView: RecyclerView

    // Interface

    var startDate: Date
        get() {
            return (recyclerView.adapter as? CalendarAdapter)?.startDate ?: Date()
        }
        set(value) {
            (recyclerView.adapter as? CalendarAdapter)?.startDate = value
        }

    var onClickListener: ((Date) -> Unit)?
        get() = (recyclerView.adapter as? CalendarAdapter)?.onClickListener
        set(value) {
            (recyclerView.adapter as? CalendarAdapter)?.onClickListener = value
        }

    // Initialization

    init {
        inflate(context, R.layout.calendar_view, this)

        recyclerView = findViewById(R.id.calendarRecyclerView)
        recyclerView.adapter = CalendarAdapter()
    }
}

private class CalendarAdapter() : RecyclerView.Adapter<CalendarAdapter.CalendarItemHolder>() {

    private var visibleDates: ArrayList<Date> = ArrayList()

    var startDate: Date = Date()
        get() = field
        set(value) {
            field = value
            reloadData()
        }

    var onClickListener: ((Date) -> Unit)? = null

    init {
        reloadData()
    }

    // Adapter's methods

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarItemHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_calendar, parent, false)
        return CalendarItemHolder(view)
    }

    override fun getItemCount(): Int {
        return visibleDates.count()
    }

    override fun onBindViewHolder(holder: CalendarItemHolder, position: Int) {
        holder.update(visibleDates[position])
        holder.itemView.setOnClickListener {
            onClickListener?.let {
                it(visibleDates[position])
            }
        }
    }

    // View holder

    class CalendarItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val dayInWeakTextView: TextView = itemView.findViewById(R.id.dayInWeakTextView)
        private val dayInMonthTextView: TextView = itemView.findViewById(R.id.dayInMonthTextView)

        fun update(date: Date) {
            dayInWeakTextView.text = DateFormatter.dayInWeak(date)
            dayInMonthTextView.text = DateFormatter.dayInMonth(date)
        }
    }

    // Helpers

    private fun reloadData() {
        val startCalendar = Calendar.getInstance()
        startCalendar.time = startDate

        val endCalendar = Calendar.getInstance()
        endCalendar.time = startDate
        endCalendar.add(Calendar.MONTH, 1)   // Date shifted for 1 month
        var dates: ArrayList<Date> = ArrayList()

        while (!startCalendar.after(endCalendar)) {
            dates.add(startCalendar.getTime());
            startCalendar.add(Calendar.DATE, 1);
        }

        visibleDates = dates
        notifyDataSetChanged()
    }
}