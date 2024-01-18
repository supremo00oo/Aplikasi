import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.sttpyk.myinventory.R
import id.ac.sttpyk.myinventory.api.Api
import id.ac.sttpyk.myinventory.databinding.FragmentBenihBinding
import id.ac.sttpyk.myinventory.helper.AdapterBenih
import id.ac.sttpyk.myinventory.helper.AdapterInventory
import id.ac.sttpyk.myinventory.models.BenihModel
import id.ac.sttpyk.myinventory.models.SimpanModel2
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class BenihFragment : Fragment() {

    private lateinit var binding : FragmentBenihBinding
    private val api by lazy{ Api.create()}
    private val navController  by lazy { findNavController() }

    companion object{
        const val BENIH = "BENIH"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBenihBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        api.tampilbenih().enqueue(object : Callback<BenihModel>{
            override fun onResponse(call: Call<BenihModel>, response: Response<BenihModel>) {
                if (response.isSuccessful){
                    Log.e("TAG", "onResponse: ${response.body()}",)
                    val result = response.body()!!.data
                    binding.listdatabarang.layoutManager =
                        LinearLayoutManager(requireContext())
                    binding.listdatabarang.itemAnimator = null
                    val adapterBenih = AdapterBenih(result as ArrayList<BenihModel.Data>,
                        object : AdapterBenih.OnAdapterListener {
                            override fun OnClick(benih: BenihModel.Data) {
                                var bundle = Bundle().apply {
                                    putParcelable(BENIH, benih)
                                }
                                navController.navigate(R.id.action_benihFragment_to_tambahDataFragment2,bundle)
                            }
                        })
                    adapterBenih.setOnItemClickCallback {
                        api.hapusbenih(it.id).enqueue(object : Callback<SimpanModel2>{
                            override fun onResponse(call: Call<SimpanModel2>, response: Response<SimpanModel2>
                            ) {
                                val submit = response.body()
                                if (response.isSuccessful){
                                    Toast.makeText(requireContext(), submit!!.message, Toast.LENGTH_SHORT).show()
                                    val deleteindex = result.indexOf(it)
                                    result.removeAt(deleteindex)
                                    adapterBenih.notifyItemChanged(deleteindex)
                                }
                            }

                            override fun onFailure(call: Call<SimpanModel2>, t: Throwable) {
                                Toast.makeText(requireContext(), "Error : ${t.message}", Toast.LENGTH_SHORT).show()
                            }

                        })
                    }
                    binding.listdatabarang.adapter = adapterBenih
                }
            }

            override fun onFailure(call: Call<BenihModel>, t: Throwable) {
                Toast.makeText(requireContext(),"Error : ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })

        binding.kembali.setOnClickListener{
            requireActivity().onBackPressed()
        }
        binding.tambah.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_benihFragment_to_tambahDataFragment2)
        )
    }

}