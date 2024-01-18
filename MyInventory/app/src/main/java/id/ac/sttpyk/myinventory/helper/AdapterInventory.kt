package id.ac.sttpyk.myinventory.helper

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import androidx.recyclerview.widget.RecyclerView
import id.ac.sttpyk.myinventory.R
import id.ac.sttpyk.myinventory.databinding.ItemInventoryBinding
import id.ac.sttpyk.myinventory.models.PupukModel
import retrofit2.http.Body

class AdapterInventory(private val items:ArrayList<PupukModel.Data>, val listener: OnAdapterListener):
    RecyclerView.Adapter<AdapterInventory.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemInventoryBinding) :RecyclerView.ViewHolder(binding.root) {
        fun init(data: PupukModel.Data){
            binding.namabarang.text=data.pupuk
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
        fun onItemClicked(data: PupukModel.Data)
    }

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(body: (PupukModel.Data) -> Unit) {
        onItemClickCallback = object : OnItemClickCallback {
            override fun onItemClicked(data: PupukModel.Data){
                body(data)
            }
        }
    }
    interface OnAdapterListener{
        fun OnClick(pupuk: PupukModel.Data)
    }
}