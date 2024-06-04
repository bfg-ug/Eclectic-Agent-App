package com.example.eclecticbank.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class tabviewpagerAdapter(list: ArrayList<Fragment>, fm: FragmentActivity):  FragmentStateAdapter(fm) {

    private val fragmentList = list


    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

}