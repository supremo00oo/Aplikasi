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
import id.ac.sttpyk.myinventory.databinding.FragmentInventoryBinding
import id.ac.sttpyk.myinventory.helper.AdapterInventory
import id.ac.sttpyk.myinventory.models.PupukModel
import id.ac.sttpyk.myinventory.models.SimpanModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class InventoryFragment : Fragment() {

    private lateinit var binding : FragmentInventoryBinding
    private val api by lazy{ Api.create()}
    private val navController  by lazy { findNavController() }

    companion object{
        const val PUPUK = "PUPUK"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInventoryBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        api.tampilpupuk().enqueue(object : Callback<PupukModel>{
            override fun onResponse(call: Call<PupukModel>, response: Response<PupukModel>) {
                if (response.isSuccessful){
                    Log.e("TAG", "onResponse: ${response.body()}",)
                    val result = response.body()!!.data
                    binding.listdatabarang.layoutManager =
                        LinearLayoutManager(requireContext())
                    binding.listdatabarang.itemAnimator = null
                    val adapterInventory = AdapterInventory(result as ArrayList<PupukModel.Data>,
                        object : AdapterInventory.OnAdapterListener {
                            override fun OnClick(pupuk: PupukModel.Data) {
                                var bundle = Bundle().apply {
                                    putParcelable(PUPUK, pupuk)
                                }
                                navController.navigate(R.id.action_inventoryFragment_to_tambahDataFragment,bundle)
                            }
                        })
                    adapterInventory.setOnItemClickCallback {
                        api.hapuspupuk(it.id).enqueue(object : Callback<SimpanModel>{
                            override fun onResponse(call: Call<SimpanModel>, response: Response<SimpanModel>
                            ) {
                                val submit = response.body()
                                if (response.isSuccessful){
                                    Toast.makeText(requireContext(), submit!!.message, Toast.LENGTH_SHORT).show()
                                    val deleteindex = result.indexOf(it)
                                    result.removeAt(deleteindex)
                                    adapterInventory.notifyItemChanged(deleteindex)
                                }
                            }

                            override fun onFailure(call: Call<SimpanModel>, t: Throwable) {
                                Toast.makeText(requireContext(), "Error : ${t.message}", Toast.LENGTH_SHORT).show()
                            }

                        })
                    }
                    binding.listdatabarang.adapter = adapterInventory
                }
            }

            override fun onFailure(call: Call<PupukModel>, t: Throwable) {
                Toast.makeText(requireContext(),"Error : ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })

        binding.kembali.setOnClickListener{
            requireActivity().onBackPressed()
        }
        binding.tambah.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_inventoryFragment_to_tambahDataFragment)
        )
    }

}