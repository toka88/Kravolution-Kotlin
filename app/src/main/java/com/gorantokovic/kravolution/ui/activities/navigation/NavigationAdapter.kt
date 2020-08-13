package com.gorantokovic.kravolution.ui.activities.navigation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gorantokovic.kravolution.R

class NavigationAdapter(
    private val context: Context,
    private val onClickListener: (MenuItemType) -> Unit
) :
    RecyclerView.Adapter<NavigationAdapter.PrimaryViewHolder>() {

    private val menuItems: Array<MenuItemType> = arrayOf(
        MenuItemType.YourProfile,
        MenuItemType.MyPassport,
        MenuItemType.YourSeminars,
        MenuItemType.HowTo,
        MenuItemType.SignOut
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrimaryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_menu_primary, parent, false)
        return PrimaryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return menuItems.count()
    }

    override fun getItemViewType(position: Int): Int {
        return menuItems[position].ordinal
    }

    override fun onBindViewHolder(holder: PrimaryViewHolder, position: Int) {
        val itemType = menuItems[position]
        holder.update(itemType.title(context))
        holder.itemView.setOnClickListener {
            onClickListener(itemType)
        }
    }

    enum class MenuItemType {
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


