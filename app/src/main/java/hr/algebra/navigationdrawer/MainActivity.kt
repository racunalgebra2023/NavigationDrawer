package hr.algebra.navigationdrawer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var mDrawerLayout : DrawerLayout

    override fun onCreate( savedInstanceState: Bundle? ) {
        super.onCreate( savedInstanceState )
        setContentView( R.layout.activity_main )

        val toolbar : Toolbar = findViewById( R.id.toolbar )
        setSupportActionBar( toolbar )

        val actionbar : ActionBar? = supportActionBar
        actionbar?.apply {
            setDisplayHomeAsUpEnabled( true )
            setHomeAsUpIndicator( R.mipmap.ic_launcher_round )
        }

        mDrawerLayout = findViewById( R.id.drawer_layout )
        val mView : NavigationView = findViewById( R.id.nav_view )

        mView.setNavigationItemSelectedListener {
            it.isChecked = true
            mDrawerLayout.closeDrawers( )

            when( it.itemId ) {
                R.id.nav_profile -> {
                    supportFragmentManager
                        .beginTransaction( )
                        .replace( R.id.fragmentContainer, BlankFragment( ) )
                        .commit( )
                }
                R.id.nav_offer -> {
                    supportFragmentManager
                        .beginTransaction( )
                        .replace( R.id.fragmentContainer, GreenFragment( ) )
                        .commit( )
                }
                R.id.nav_wallet -> {
                    supportFragmentManager
                        .beginTransaction( )
                        .replace( R.id.fragmentContainer, RedFragment( ) )
                        .commit( )

                }
                R.id.nav_setting -> {
                    supportFragmentManager.fragments.forEach { fr ->
                        supportFragmentManager
                            .beginTransaction( )
                            .remove( fr )
                            .commit( )
                    }
                }
            }

            true
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when( item.itemId ) {
            android.R.id.home -> {
                mDrawerLayout.openDrawer( GravityCompat.START )
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}