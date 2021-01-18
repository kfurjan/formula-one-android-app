package hr.algebra.formula1

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
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
        toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        binding.drawerLayout.addDrawerListener(toggle)

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
            R.id.menuExit -> {
                exitApp()
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

    private fun exitApp() {
        AlertDialog.Builder(this).apply {
            setTitle(R.string.exit)
            setMessage(getString(R.string.really))
            setCancelable(true)
            setPositiveButton("Ok") { _, _ -> finish() }
            setNegativeButton(getString(R.string.cancel), null)
            show()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navItemDrivers -> Toast.makeText(
                this,
                Driver::class.simpleName,
                Toast.LENGTH_SHORT
            ).show()
            R.id.navItemConstructors -> Toast.makeText(
                this,
                Constructor::class.simpleName,
                Toast.LENGTH_SHORT
            ).show()
            R.id.navItemCircuits -> Toast.makeText(
                this,
                Circuit::class.simpleName,
                Toast.LENGTH_SHORT
            ).show()
            R.id.navItemSeasons -> Toast.makeText(
                this,
                Season::class.simpleName,
                Toast.LENGTH_SHORT
            ).show()
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.host_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}
