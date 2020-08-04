package com.gorantokovic.kravolution.activities.navigation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gorantokovic.kravolution.R

class NavigationAdapter(
    private val context: Context,
    private val onClickListener: (View, MenyItemType) -> Unit
) :
    RecyclerView.Adapter<NavigationAdapter.PrimaryViewHolder>() {

    private val menyItems: Array<MenyItemType> = arrayOf(
        MenyItemType.YourProfile,
        MenyItemType.MyPassport,
        MenyItemType.YourSeminars,
        MenyItemType.HowTo,
        MenyItemType.SignOut
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrimaryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.raw_menu_primary, parent, false)
        return PrimaryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return menyItems.count()
    }

    override fun getItemViewType(position: Int): Int {
        return menyItems[position].ordinal
    }

    override fun onBindViewHolder(holder: PrimaryViewHolder, position: Int) {
        val itemType = menyItems[position]
        holder.update(itemType.title(context))
        holder.itemView.setOnClickListener {
            onClickListener(it, itemType)
        }
    }

    enum class MenyItemType {
        YourProfile, MyPassport, YourSeminars, HowTo, SignOut;

        fun title(context: Context): String {
            return when (this) {
                YourProfile -> context.getString(R.string.your_profile_menu_item)
                MyPassport -> context.getString(R.string.my_passport_menu_item)
                YourSeminars -> context.getString(R.string.your_seminars_menu_item)
                HowTo -> context.getString(R.string.how_to_menu_item)
                SignOut -> context.getString(R.string.sign_out_menu_item)
            }
        }
    }

    class PrimaryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        fun update(title: String) {
            titleTextView.text = title
        }
    }
}


