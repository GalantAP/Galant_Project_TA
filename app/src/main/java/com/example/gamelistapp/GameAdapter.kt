import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gamelistapp.Game
import com.example.gamelistapp.R

// Pastikan class GameAdapter menggunakan model Game yang benar
class GameAdapter(private val games: List<Game>) : RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    class GameViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val gameImage: ImageView = view.findViewById(R.id.game_image)
        val gameTitle: TextView = view.findViewById(R.id.game_title)
        val gameDescription: TextView = view.findViewById(R.id.game_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_game, parent, false) // Pastikan ini mengarah pada layout yang benar
        return GameViewHolder(view)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val game = games[position]

        // Set data pada view
        holder.gameImage.setImageResource(game.imageResId) // Memastikan image resource sesuai
        holder.gameTitle.text = game.name // Menampilkan nama game
        holder.gameDescription.text = game.releaseDate // Menampilkan tanggal rilis game

        // Listener untuk membuka tautan saat judul diklik
        holder.gameTitle.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(game.url))
            it.context.startActivity(intent)
        }

        // Listener untuk membuka tautan saat gambar diklik
        holder.gameImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(game.url))
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = games.size // Mengembalikan jumlah item
}
