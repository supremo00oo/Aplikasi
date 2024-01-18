package id.ac.sttpyk.myinventory.helper

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import androidx.recyclerview.widget.RecyclerView
import id.ac.sttpyk.myinventory.R
import id.ac.sttpyk.myinventory.databinding.ItemInventoryBinding
import id.ac.sttpyk.myinventory.models.BenihModel
import retrofit2.http.Body

class AdapterBenih(private val items:ArrayList<BenihModel.Data>, val listener: OnAdapterListener):
    RecyclerView.Adapter<AdapterBenih.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemInventoryBinding) :RecyclerView.ViewHolder(binding.root) {
        fun init(data: BenihModel.Data){
            binding.namabarang.text=data.benih
            binding.kapasitas.text=data.jumlah.toString()
            binding.imgDelete.setOnClickListener{
                onItemClickCallback.onItemClicked(data)
                Log.e("TAG", "init: Tombol di klik",)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val V = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_inventory, parent, false)
        val binding = ItemInventoryBinding.bind(V)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.init(items[position])
        holder.itemView.setOnClickListener {
            listener.OnClick(items[position])
        }
    }

    private interface OnItemClickCallback {
        fun onItemClicked(data: BenihModel.Data)
    }

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(body: (BenihModel.Data) -> Unit) {
        onItemClickCallback = object : OnItemClickCallback {
            override fun onItemClicked(data: BenihModel.Data){
                body(data)
            }
        }
    }
    interface OnAdapterListener{
        fun OnClick(benih: BenihModel.Data)
    }
}