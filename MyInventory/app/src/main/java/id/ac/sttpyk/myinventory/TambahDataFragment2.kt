package id.ac.sttpyk.myinventory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import id.ac.sttpyk.myinventory.api.Api
import id.ac.sttpyk.myinventory.databinding.FragmentTambahData2Binding
import id.ac.sttpyk.myinventory.models.SimpanModel
import id.ac.sttpyk.myinventory.models.SimpanModel2
import kotlinx.android.synthetic.main.fragment_menu.*
import kotlinx.android.synthetic.main.fragment_tambah_data.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TambahDataFragment2 : Fragment() {

    private lateinit var binding: FragmentTambahData2Binding
    val api by lazy{ Api.create()}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTambahData2Binding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.inputnamabarang2.setText(benih?.namabarang.toString())
        binding.inputjumlahbarang2.setText(benih?.jumlahbarang.toString())
        binding.btnSimpan2.setOnClickListener {
            if (benih!=null){
                editdata()
            }else{
                simpandata()
            }
        }

        binding.cancel2.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun editdata(){
        val id = benih?.id
        val benih = binding.inputnamabarang2.text.toString()
        val jumlah = binding.inputnamabarang2.text.toString()

        api.editbenih(id, benih, jumlah).enqueue(object :Callback<SimpanModel2>{
            override fun onResponse(call: Call<SimpanModel2>, response: Response<SimpanModel2>) {
                val result = response.body()
                if (result?.response_code==200){
                    Toast.makeText(requireContext(), result?.message, Toast.LENGTH_SHORT).show()
                    requireActivity().onBackPressed()
                }else{
                    Toast.makeText(requireContext(),result?.message, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<SimpanModel2>, t: Throwable) {
                Toast.makeText(requireContext(), "Error ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun simpandata() {
        val benih = binding.inputnamabarang2.text.toString()
        val jumlah = binding.inputjumlahbarang2.text.toString()
        api.entribenih(benih, jumlah).enqueue(object : Callback<SimpanModel2>{
            override fun onResponse(call: retrofit2.Call<SimpanModel2>, response: Response<SimpanModel2>){
                Toast.makeText(requireContext(), "Data Berhasi di Simpan", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: retrofit2.Call<SimpanModel2>, t: Throwable) {
                Toast.makeText(requireContext(), "Error : ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

}