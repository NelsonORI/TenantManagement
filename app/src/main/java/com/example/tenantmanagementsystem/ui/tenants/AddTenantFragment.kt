package com.example.tenantmanagementsystem.ui.tenants

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tenantmanagementsystem.databinding.FragmentAddTenantBinding
import android.widget.Toast
import androidx.navigation.fragment.findNavController
//import com.example.tenantmanagementsystem.AddTenantFragmentDirections
import com.example.tenantmanagementsystem.data.model.Tenant
import com.example.tenantmanagementsystem.data.repository.TenantRepository
import androidx.fragment.app.activityViewModels
import com.example.tenantmanagementsystem.ui.viewmodel.TenantViewModel

class AddTenantFragment : Fragment() {
    private var _binding: FragmentAddTenantBinding? = null
    private val binding get() = _binding!!
    private val viewModel: TenantViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddTenantBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // saveButton ← android:id="@+id/saveButton" in fragment_add_tenant.xml
        binding.saveButton.setOnClickListener {
            // Hand input over to the ViewModel to trim, check, and save
            val tenant = viewModel.addTenant(
                binding.tenantNameEditText.text.toString(),
                binding.phoneEditText.text.toString(),
                binding.rentEditText.text.toString()
            )

            // If the ViewModel returns null, fields were missing -> show error Toast
            if (tenant == null) {
                Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Otherwise, navigate forward using Safe Args
            val action = AddTenantFragmentDirections.actionAddTenantFragmentToViewTenantsFragment(tenant)
            findNavController().navigate(action)
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}