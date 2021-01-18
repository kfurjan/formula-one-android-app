package hr.algebra.formula1

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import hr.algebra.formula1.databinding.ActivityHostBinding
import hr.algebra.formula1.model.Circuit
import hr.algebra.formula1.model.Constructor
import hr.algebra.formula1.model.Driver
import hr.algebra.formula1.model.Season

private lateinit var binding: ActivityHostBinding
private lateinit var toggle: ActionBarDrawerToggle

class HostActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

        binding = ActivityHostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initNavigationDrawer()
    }

    private fun initNavigationDrawer() {
        val navDrawer = binding.drawerLayout
        toggle = ActionBarDrawerToggle(
            this,
            navDrawer,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        navDrawer.addDrawerListener(toggle)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        binding.navView.setNavigationItemSelectedListener(this)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        toggle.syncState()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                toggleNavigationDrawer()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun toggleNavigationDrawer() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawers()
        } else {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navItemDrivers -> Toast.makeText(this, Driver::class.simpleName, Toast.LENGTH_SHORT).show()
            R.id.navItemConstructors -> Toast.makeText(this, Constructor::class.simpleName, Toast.LENGTH_SHORT).show()
            R.id.navItemCircuits -> Toast.makeText(this, Circuit::class.simpleName, Toast.LENGTH_SHORT).show()
            R.id.navItemSeasons -> Toast.makeText(this, Season::class.simpleName, Toast.LENGTH_SHORT).show()
            R.id.navItemAbout -> Toast.makeText(this, "About", Toast.LENGTH_SHORT).show()
        }
        return true
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
