package com.example.materialdrawer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.materialdrawer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolbar))

        binding.navigationView.setNavigationItemSelectedListener{
            when (it.itemId) {
                R.id.nav_home -> {
                    true
                }
            }
            true
        }

        val drawerToggle = ActionBarDrawerToggle(this, binding.drawer, R.string.open, R.string.close)
        binding.drawer.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        animationSideDrawer()

    }

    val END_SCALE: Float = 1.0f

    private fun animationSideDrawer() {
        binding.drawer.addDrawerListener(object : DrawerLayout.DrawerListener {
            override fun onDrawerSlide(view: View, v: Float) {
                val diffScaledOffset: Float = v * (1 - END_SCALE)
                val offsetScale = 1 - diffScaledOffset
                binding.containter.scaleX = offsetScale
                binding.containter.scaleY = offsetScale
                val xOffset = view.width * v
                val xOffsetDiff: Float = binding.containter.width * diffScaledOffset / 2
                val xTranslation = xOffset - xOffsetDiff
                binding.containter.translationX = xTranslation
            }
            override fun onDrawerOpened(view: View) {
            }
            override fun onDrawerClosed(view: View) {}
            override fun onDrawerStateChanged(i: Int) {}
        })
    }

    override fun onBackPressed() {
        if (binding.drawer.isDrawerOpen(GravityCompat.START)) {
            binding.drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        return when (item.itemId) {
            android.R.id.home -> {
                binding.drawer.openDrawer(GravityCompat.START)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}