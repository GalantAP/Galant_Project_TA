package com.example.gamelistapp

import GameAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MobileGamesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var gameAdapter: GameAdapter
    private lateinit var searchView: SearchView
    private lateinit var games: List<Game>
    private lateinit var filteredGames: MutableList<Game>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_mobile_games_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Inisialisasi SearchView
        searchView = view.findViewById(R.id.searchView)

        // Daftar 10 game mobile
        games = listOf(
            Game("Call Of Duty Mobile", "Rilis: 1 Oktober 2019", R.drawable.mobile_game1_image, "https://www.callofduty.com/mobile"),
            Game("PUBG Mobile", "Rilis: 19 Maret 2018", R.drawable.mobile_game2_image, "https://www.pubgmobile.com/en-US/home.shtml"),
            Game("Blood Strike", "Rilis: Tidak diketahui, game ini populer sekitar tahun 2015", R.drawable.mobile_game3_image, "https://www.blood-strike.com/id/"),
            Game("Mobile Legends", "Rilis: 11 Juli 2016", R.drawable.mobile_game4_image, "https://www.mobilelegends.com/"),
            Game("Genshin Impact", "Rilis: 28 September 2020", R.drawable.mobile_game5_image, "https://genshin.mihoyo.com/"),
            Game("Clash of Clans", "Rilis: 2 Agustus 2012", R.drawable.mobile_game6_image, "https://supercell.com/en/games/clashofclans/"),
            Game("Free Fire", "Rilis: 23 Agustus 2017", R.drawable.mobile_game7_image, "https://ff.garena.com/"),
            Game("Asphalt 9", "Rilis: 25 Juli 2018", R.drawable.mobile_game8_image, "https://www.gameloft.com/en/game/asphalt-9"),
            Game("Pokemon Unite", "Rilis: 21 Juli 2021", R.drawable.mobile_game9_image, "https://unite.pokemon.com/"),
            Game("Brawl Stars", "Rilis: 12 Desember 2018", R.drawable.mobile_game10_image, "https://supercell.com/en/games/brawlstars/")
        )

        // Salin data awal ke daftar filteredGames
        filteredGames = games.toMutableList()

        gameAdapter = GameAdapter(filteredGames)
        recyclerView.adapter = gameAdapter

        // Tambahkan listener ke SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterGames(newText)
                return true
            }
        })
    }

    private fun filterGames(query: String?) {
        filteredGames.clear()

        if (query.isNullOrEmpty()) {
            filteredGames.addAll(games)
        } else {
            val lowerCaseQuery = query.lowercase()
            filteredGames.addAll(games.filter {
                it.name.lowercase().contains(lowerCaseQuery)
            })
        }

        gameAdapter.notifyDataSetChanged()
    }
}
