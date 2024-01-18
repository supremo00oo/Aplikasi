package id.ac.sttpyk.myinventory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import id.ac.sttpyk.myinventory.api.Api
import id.ac.sttpyk.myinventory.databinding.FragmentTambahData3Binding
import id.ac.sttpyk.myinventory.models.SimpanModel
import id.ac.sttpyk.myinventory.models.SimpanModel3
import kotlinx.android.synthetic.main.fragment_menu.*
import kotlinx.android.synthetic.main.fragment_tambah_data.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TambahDataFragment3 : Fragment() {

    private lateinit var binding: FragmentTambahData3Binding
    val api by lazy{ Api.create()}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTambahData3Binding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.inputnamabarang3.setText(alat?.namabarang.toString())
        binding.inputjumlahbarang3.setText(alat?.jumlahbarang.toString())
        binding.btnSimpan3.setOnClickListener {
            if (alat!=null){
                editdata()
            }else{
                simpandata()
            }
        }

        binding.cancel3.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun editdata() {
        val id = alat?.id
        val alat = binding.inputnamabarang3.text.toString()
        val jumlah = binding.inputnamabarang3.text.toString()

        api.editalat(id, alat, jumlah).enqueue(object :Callback<SimpanModel3>{
            override fun onResponse(call: Call<SimpanModel3>, response: Response<SimpanModel3>) {
                val result = response.body()
                if (result?.response_code==200){
                    Toast.makeText(requireContext(), result?.message, Toast.LENGTH_SHORT).show()
                    requireActivity().onBackPressed()
                }else{
                    Toast.makeText(requireContext(),result?.message, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<SimpanModel3>, t: Throwable) {
                Toast.makeText(requireContext(), "Error ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
    
    private fun simpandata() {
        val alat = binding.inputnamabarang3.text.toString()
        val jumlah = binding.inputjumlahbarang3.text.toString()
        api.entrialat(alat, jumlah).enqueue(object : Callback<SimpanModel3>{
            override fun onResponse(call: retrofit2.Call<SimpanModel3>, response: Response<SimpanModel3>){
                Toast.makeText(requireContext(), "Data Berhasi di Simpan", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: retrofit2.Call<SimpanModel3>, t: Throwable) {
                Toast.makeText(requireContext(), "Error : ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

}