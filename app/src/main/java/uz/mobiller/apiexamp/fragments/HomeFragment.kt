package uz.mobiller.apiexamp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import retrofit2.Call
import retrofit2.Response
import uz.mobiller.apiexamp.R
import uz.mobiller.apiexamp.adapters.RecyclerViewAdapter
import uz.mobiller.apiexamp.databinding.FragmentHomeBinding
import uz.mobiller.apiexamp.model.JsonObjectItem
import uz.mobiller.apiexamp.retrofit.ApiClient
import javax.security.auth.callback.Callback

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var adapter: RecyclerViewAdapter
    private lateinit var list: ArrayList<JsonObjectItem>
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)
        loadData()
    }

    fun loadData(){

        val retrofitData = ApiClient.getRetrofit().getCurrentData()
        list = ArrayList()
        retrofitData.enqueue(object : retrofit2.Callback<List<JsonObjectItem>>{
            override fun onFailure(call: Call<List<JsonObjectItem>>?, t: Throwable?) {
                Log.d("HomeFragment","onFailure: " + t!!.message)
            }

            override fun onResponse(
                call: Call<List<JsonObjectItem>>?,
                response: Response<List<JsonObjectItem>>?
            ) {
                var responseBody = response!!.body()
                for(itemModel in responseBody){
                    list.add(itemModel)
                }

                adapter = RecyclerViewAdapter(list,object : RecyclerViewAdapter.OnItemClickListener{
                    override fun onItemClick(itemModel: JsonObjectItem, position: Int) {
                        var bundle = bundleOf("user" to itemModel)
                        Navigation.findNavController(binding.root).navigate(R.id.firstFragment, bundle)
                    }
                })

                binding.rv.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
                binding.rv.adapter = adapter
            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}