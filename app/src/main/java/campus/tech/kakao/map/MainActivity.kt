package campus.tech.kakao.map

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import campus.tech.kakao.map.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var placeDao: PlaceDao
    private lateinit var adapter: PlaceAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        placeDao = PlaceDao(this)
        adapter = PlaceAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        binding.buttonSearch.setOnClickListener {
            val query = binding.editSearch.text.toString()
            val places = placeDao.getPlaces(query)
            if (places.isEmpty()) {
                binding.textNoSearch.visibility = View.VISIBLE
                binding.recyclerView.visibility = View.GONE
            } else {
                binding.textNoSearch.visibility = View.GONE
                binding.recyclerView.visibility = View.VISIBLE
                adapter.submitList(places)
            }
        }
    }
}
