package id.ac.sttpyk.myinventory

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.dikodei.sipasan.helper.SessionManager
import id.ac.sttpyk.myinventory.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {

    lateinit var binding: FragmentMenuBinding
    val session by lazy { SessionManager.init(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.pupuk.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_menuFragment_to_inventoryFragment)
        )
            binding.benih.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_menuFragment_to_benihFragment)
            )
            binding.alat.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_menuFragment_to_alatFragment)
            )
        binding.logout.setOnClickListener {
            session.logout()
            val intent = Intent(requireContext(), MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            requireActivity().finish()
        }
    }
}