package com.gorantokovic.kravolution.activities.onboarding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gorantokovic.kravolution.R

class OnboardingAdapter() : RecyclerView.Adapter<OnboardingAdapter.OnboardingViewHolder>() {

    private val pageTypes: Array<OnboardingType> = arrayOf(
        OnboardingType.Classes,
        OnboardingType.Progress,
        OnboardingType.Kit,
        OnboardingType.KeepInformed
    )

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OnboardingAdapter.OnboardingViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.onboarding_page_layout, parent, false)
        return OnboardingViewHolder(view)
    }

    override fun getItemCount(): Int {
        return pageTypes.count()
    }

    override fun onBindViewHolder(holder: OnboardingAdapter.OnboardingViewHolder, position: Int) {
        val pageType = pageTypes[position]
        holder.titleTextView.text = pageType.title()
        holder.descriptionTextView.text = pageType.description()
    }

    class OnboardingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var titleTextView: TextView = view.findViewById(R.id.titleTextView)
        var descriptionTextView: TextView = view.findViewById(R.id.descriptionTextView)
    }
}

private enum class OnboardingType {
    Classes, Progress, Kit, KeepInformed;

    fun title(): String {
        when (this) {
            OnboardingType.Classes -> return "CLASSES"
            OnboardingType.Progress -> return "PROGRESS"
            OnboardingType.Kit -> return "KIT"
            OnboardingType.KeepInformed -> return "KEEP\nINFORMED"
        }
    }

    fun description(): String {
        return "Praesent nec porta arcu.\n Duis quis purus nibh"
    }
}
