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
import id.ac.sttpyk.myinventory.databinding.FragmentAlatBinding
import id.ac.sttpyk.myinventory.helper.AdapterAlat
import id.ac.sttpyk.myinventory.models.AlatModel
import id.ac.sttpyk.myinventory.models.SimpanModel3
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AlatFragment : Fragment() {

    private lateinit var binding : FragmentAlatBinding
    private val api by lazy{ Api.create()}
    private val navController  by lazy { findNavController() }

    companion object{
        const val ALAT = "ALAT"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAlatBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        api.tampilalat().enqueue(object : Callback<AlatModel>{
            override fun onResponse(call: Call<AlatModel>, response: Response<AlatModel>) {
                if (response.isSuccessful){
                    Log.e("TAG", "onResponse: ${response.body()}",)
                    val result = response.body()!!.data
                    binding.listdatabarang.layoutManager =
                        LinearLayoutManager(requireContext())
                    binding.listdatabarang.itemAnimator = null
                    val adapterAlat = AdapterAlat(result as ArrayList<AlatModel.Data>,
                        object : AdapterAlat.OnAdapterListener {
                            override fun OnClick(alat: AlatModel.Data) {
                                var bundle = Bundle().apply {
                                    putParcelable(ALAT, alat)
                                }
                                navController.navigate(R.id.action_alatFragment_to_tambahDataFragment3,bundle)
                            }
                        })
                    adapterAlat.setOnItemClickCallback {
                        api.hapusalat(it.id).enqueue(object : Callback<SimpanModel3>{
                            override fun onResponse(call: Call<SimpanModel3>, response: Response<SimpanModel3>
                            ) {
                                val submit = response.body()
                                if (response.isSuccessful){
                                    Toast.makeText(requireContext(), submit!!.message, Toast.LENGTH_SHORT).show()
                                    val deleteindex = result.indexOf(it)
                                    result.removeAt(deleteindex)
                                    adapterAlat.notifyItemChanged(deleteindex)
                                }
                            }

                            override fun onFailure(call: Call<SimpanModel3>, t: Throwable) {
                                Toast.makeText(requireContext(), "Error : ${t.message}", Toast.LENGTH_SHORT).show()
                            }

                        })
                    }
                    binding.listdatabarang.adapter = adapterAlat
                }
            }

            override fun onFailure(call: Call<AlatModel>, t: Throwable) {
                Toast.makeText(requireContext(),"Error : ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })

        binding.kembali.setOnClickListener{
            requireActivity().onBackPressed()
        }
        binding.tambah.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_alatFragment_to_tambahDataFragment3)
        )
    }

}