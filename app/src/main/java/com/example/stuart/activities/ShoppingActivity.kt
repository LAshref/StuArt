package com.example.stuart.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.stuart.R
import com.example.stuart.databinding.ActivityShoppingBinding
import com.example.stuart.util.Resource
import com.example.stuart.viewmodel.CartViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class ShoppingActivity : AppCompatActivity() {

    val binding by lazy {
        ActivityShoppingBinding.inflate(layoutInflater)
    }

    val viewModel by viewModels<CartViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val navController = findNavController(R.id.shoppingHostFragment)
        binding.bottomNavigation.setupWithNavController(navController)

        lifecycleScope.launchWhenStarted {
            viewModel.cartProducts.collectLatest {
                when (it) {
                    is Resource.Success -> {
                        val count = it.data?.size ?: 0
                        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomNavigation)
                        bottomNavigation.getOrCreateBadge(R.id.cartFragment).apply {
                            number = count
                            backgroundColor = resources.getColor(R.color.g_blue)
                        }
                    }
                    else -> Unit
                }
            }
        }
    }

}

/*.
ViewBinding Initialization:
    The val binding by lazy { ActivityShoppingBinding.inflate(layoutInflater) } line initializes the ViewBinding for the "ShoppingActivity." ViewBinding is a feature in Android that allows for direct access to views in layout files. By using ViewBinding, you can access views without the need for findViewById(). The lazy delegate ensures that the ViewBinding is only initialized when it's first accessed, providing efficiency.

onCreate() Method:
    The onCreate() method is a standard Android lifecycle method that is called when the activity is created. In this method, setContentView(R.layout.activity_shopping) is used to set the content view of the activity to the layout resource activity_shopping.xml. This links the activity to its layout file.

Navigation Setup:
    The findNavController(R.id.host_fragment) method retrieves the NavController associated with the NavHostFragment in the layout. The NavController is a key component of the Android Jetpack Navigation component, responsible for navigating between different destinations within the app. The binding.bottomNavigation.setupWithNavController(navController) line sets up the BottomNavigationView to be controlled by the NavController, enabling navigation between different destinations in the app.

In summary, the code initializes the ViewBinding for the activity, sets the content view to the specified layout, and sets up navigation using the NavController and BottomNavigationView.
 */
