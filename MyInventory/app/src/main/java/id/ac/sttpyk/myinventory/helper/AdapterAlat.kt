package id.ac.sttpyk.myinventory.helper

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.ac.sttpyk.myinventory.R
import id.ac.sttpyk.myinventory.databinding.ItemInventoryBinding
import id.ac.sttpyk.myinventory.models.AlatModel

class AdapterAlat(private val items:ArrayList<AlatModel.Data>, val listener: OnAdapterListener):
    RecyclerView.Adapter<AdapterAlat.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemInventoryBinding) :RecyclerView.ViewHolder(binding.root) {
        fun init(data: AlatModel.Data){
            binding.namabarang.text=data.alat
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
        fun onItemClicked(data: AlatModel.Data)
    }

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(body: (AlatModel.Data) -> Unit) {
        onItemClickCallback = object : OnItemClickCallback {
            override fun onItemClicked(data: AlatModel.Data){
                body(data)
            }
        }
    }
    interface OnAdapterListener{
        fun OnClick(alat: AlatModel.Data)
    }
}