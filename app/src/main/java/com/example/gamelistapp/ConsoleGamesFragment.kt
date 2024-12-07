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

class ConsoleGamesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var gameAdapter: GameAdapter
    private lateinit var searchView: SearchView
    private lateinit var games: List<Game>
    private lateinit var filteredGames: MutableList<Game>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_console_games_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Inisialisasi SearchView
        searchView = view.findViewById(R.id.searchView)

        // Daftar 10 game konsol
        games = listOf(
            Game("GTA V", "Rilis: 17 September 2013", R.drawable.console_game1_image, "https://www.rockstargames.com/V/"),
            Game("Spider-Man: Miles Morales", "Rilis: 12 November 2020", R.drawable.console_game2_image, "https://www.playstation.com/en-us/games/marvels-spider-man-miles-morales/"),
            Game("Red Dead Redemption 2 (RDR 2)", "Rilis: 26 Oktober 2018", R.drawable.console_game3_image, "https://www.rockstargames.com/reddeadredemption2/"),
            Game("The Last of Us Part II", "Rilis: 19 Juni 2020", R.drawable.console_game4_image, "https://www.thelastofus.playstation.com/part-ii/"),
            Game("God of War: Ragnarok", "Rilis: 9 November 2022", R.drawable.console_game5_image, "https://www.playstation.com/en-us/games/god-of-war-ragnarok/"),
            Game("Horizon Zero Dawn", "Rilis: 28 Februari 2017", R.drawable.console_game6_image, "https://www.playstation.com/en-us/games/horizon-zero-dawn/"),
            Game("Uncharted 4: A Thief's End", "Rilis: 10 Mei 2016", R.drawable.console_game7_image, "https://www.playstation.com/en-us/games/uncharted-4-a-thiefs-end/"),
            Game("Ghost of Tsushima", "Rilis: 17 Juli 2020", R.drawable.console_game8_image, "https://www.playstation.com/en-us/games/ghost-of-tsushima/"),
            Game("FIFA 23", "Rilis: 30 September 2022", R.drawable.console_game9_image, "https://www.ea.com/games/fifa/fifa-23"),
            Game("Assassin's Creed Valhalla", "Rilis: 10 November 2020", R.drawable.console_game10_image, "https://www.ubisoft.com/en-us/game/assassins-creed/valhalla")
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
