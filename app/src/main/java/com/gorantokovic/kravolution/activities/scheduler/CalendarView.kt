package com.gorantokovic.kravolution.activities.scheduler

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
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
        recyclerView.adapter = CalendarAdapter(context)
    }
}

private class CalendarAdapter(val context: Context) : RecyclerView.Adapter<CalendarAdapter.CalendarItemHolder>() {

    private var visibleDates: ArrayList<Date> = ArrayList()
    private var selectedIndex: Int = 0

    var startDate: Date = Date()
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
        val selected: Boolean = position == selectedIndex
        holder.update(context, selected, visibleDates[position])
        holder.itemView.setOnClickListener {
            selectedIndex = position
            onClickListener?.let {
                it(visibleDates[position])
            }
            notifyDataSetChanged()
        }
    }

    // View holder

    class CalendarItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var dayInWeakTextView: TextView = itemView.findViewById(R.id.dayInWeakTextView)
        private var dayInMonthTextView: TextView = itemView.findViewById(R.id.dayInMonthTextView)

        fun update(context: Context, selected: Boolean, date: Date) {
            dayInWeakTextView.text = DateFormatter.dayInWeak(date)
            dayInMonthTextView.text = DateFormatter.dayInMonth(date)
            if (selected) {
                itemView.background = context.getDrawable(R.drawable.calendar_background_selected)
                dayInWeakTextView.setTextColor(context.getColor(R.color.lightTextColor))
                dayInMonthTextView.setTextColor(context.getColor(R.color.lightTextColor))
            } else {
                itemView.background = context.getDrawable(R.drawable.calendar_background_unselected)
                dayInWeakTextView.setTextColor(context.getColor(R.color.darkTextColor))
                dayInMonthTextView.setTextColor(context.getColor(R.color.darkTextColor))
            }
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
            dates.add(startCalendar.getTime())
            startCalendar.add(Calendar.DATE, 1)
        }

        visibleDates = dates
        notifyDataSetChanged()
    }
}