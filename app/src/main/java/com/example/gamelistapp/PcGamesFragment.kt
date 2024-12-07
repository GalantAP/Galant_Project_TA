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

class PcGamesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var gameAdapter: GameAdapter
    private lateinit var searchView: SearchView
    private lateinit var games: List<Game>
    private lateinit var filteredGames: MutableList<Game>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Ganti dengan layout yang sesuai
        return inflater.inflate(R.layout.activity_pc_games_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inisialisasi RecyclerView
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Inisialisasi SearchView
        searchView = view.findViewById(R.id.searchView)

        // Daftar 10 game
        games = listOf(
            Game("PUBG PC", "Rilis: 23 Maret 2017", R.drawable.game1_image, "https://www.pubg.com/"),
            Game("Valorant", "Rilis: 2 Juni 2020", R.drawable.game2_image, "https://playvalorant.com/"),
            Game("Fornite", "Rilis: 25 Juli 2017", R.drawable.game3_image, "https://www.epicgames.com/fortnite/"),
            Game("Call of Duty: Warzone PC", "Rilis: 10 Maret 2020", R.drawable.game4_image, "https://www.callofduty.com/warzone"),
            Game("Apex Legends PC", "Rilis: 4 Februari 2019", R.drawable.game5_image, "https://www.ea.com/games/apex-legends"),
            Game("CS:GO (Counter-Strike: Global Offensive)", "Rilis: 21 Agustus 2012", R.drawable.game6_image, "https://blog.counter-strike.net/"),
            Game("DOTA 2", "Rilis: 9 Juli 2013", R.drawable.game7_image, "https://www.dota2.com/"),
            Game("League of Legends PC", "Rilis: 27 Oktober 2009", R.drawable.game8_image, "https://www.leagueoflegends.com/"),
            Game("Overwatch PC", "Rilis: 24 Mei 2016", R.drawable.game9_image, "https://overwatch.blizzard.com/"),
            Game("Minecraft", "Rilis: 18 November 2011", R.drawable.game10_image, "https://www.minecraft.net/")
        )

        // Salin data awal ke daftar filteredGames
        filteredGames = games.toMutableList()

        // Set adapter untuk RecyclerView
        gameAdapter = GameAdapter(filteredGames)
        recyclerView.adapter = gameAdapter

        // Tambahkan listener ke SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Tidak digunakan, hasil sudah diperbarui secara real-time
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
            // Jika pencarian kosong, tampilkan semua game
            filteredGames.addAll(games)
        } else {
            // Filter daftar game berdasarkan query
            val lowerCaseQuery = query.lowercase()
            filteredGames.addAll(games.filter {
                it.name.lowercase().contains(lowerCaseQuery)
            })
        }

        // Beritahu adapter bahwa data telah berubah
        gameAdapter.notifyDataSetChanged()
    }
}
