package com.sgh.potatodev.sami.ui.travel_budget

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView
import com.sgh.potatodev.sami.R
import com.sgh.potatodev.sami.databinding.FragmentTravelBudgetBinding

class TravelBudgetFragment : Fragment() {

    private var _binding: FragmentTravelBudgetBinding? = null
    private lateinit var topAppBar: MaterialToolbar
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("TravelBudgetFragment", "onCreateView: Setting up navigation")
        val travelBudgetViewModel =
            ViewModelProvider(this).get(TravelBudgetViewModel::class.java)

        _binding = FragmentTravelBudgetBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Find views from the inflated layout
        topAppBar = root.findViewById(R.id.topAppBar)
        drawerLayout = root.findViewById(R.id.drawer_layout)
        navView = root.findViewById(R.id.nav_view)

        // Customize the toolbar as needed
        topAppBar.title = "Travel Budget"

        // Set up navigation
        val navController = NavHostFragment.findNavController(this)
//        val appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_dashboard,  // ID of your Travel Budget Fragment
                R.id.navigation_travel_budget,
                R.id.navigation_notifications
            ),
            drawerLayout
        )

        // Ensure the hosting activity is an AppCompatActivity
        val activity = requireActivity()
        if (activity is AppCompatActivity) {
            activity.setSupportActionBar(topAppBar)
            setupActionBarWithNavController(activity, navController, appBarConfiguration)
        }

        setupWithNavController(navView, navController)

        topAppBar.setNavigationOnClickListener {
            Log.d("TravelBudgetFragment", "topAppBar Clicked!")

            // Check if the drawer is open, if yes, close it; if no, open it
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("TravelBudgetFragment", "onDestroyView: Cleaning up")
        _binding = null
    }
}
