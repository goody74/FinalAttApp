package com.og.randomizer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BannerFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_banner_list, container, false)

        // создаем наш список
        val bannerList: List<Banner> = listOf(
            Banner(R.drawable.abbysiny, "Абисинская"),
            Banner(R.drawable.ragdoll, "Рэгдолл"),
            Banner(R.drawable.bengal, "Бенгальская"),
            Banner(R.drawable.siberian, "Сибирская"),
            Banner(R.drawable.manchkin, "Манчкин")


        )
        val usersRecyclerView: RecyclerView = view.findViewById(R.id.users_recycler_view)
        usersRecyclerView.layoutManager =
            LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
        usersRecyclerView.addItemDecoration(
            DividerItemDecoration(
                view.context,
                DividerItemDecoration.VERTICAL
            )
        )
        usersRecyclerView.adapter = BannerAdapter(bannerList)
        return view
    }

}