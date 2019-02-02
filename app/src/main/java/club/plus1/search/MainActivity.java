package club.plus1.search;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState != null){
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, getFragment(R.layout.fragment_main))
                    .commit();
        }
    }

    protected Fragment getFragment(int id){
        Fragment fragment;
        fragment = getSupportFragmentManager().findFragmentById(id);
        if (fragment == null ) {
            fragment = MainFragment.newInstance(id);
        } else {
            Toast. makeText ( this , "Не меняем" , Toast. LENGTH_SHORT ).show();
        }
        return fragment;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.itemOptions:
                Toast.makeText(this, R.string.menu_options, Toast.LENGTH_LONG).show();
                switchFragment(R.layout.fragment_options);
                break;
            case R.id.itemSearch:
                Toast.makeText(this, R.string.menu_search, Toast.LENGTH_LONG).show();
                switchFragment(R.layout.fragment_search);
                break;
            case R.id.itemExit:
                Toast.makeText(this, R.string.menu_exit, Toast.LENGTH_LONG).show();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    void switchFragment(int id) {
        Fragment fragment;
        fragment = getSupportFragmentManager().findFragmentById(id);
        if (fragment == null ) {
            fragment = MainFragment.newInstance(id);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer , fragment)
                    .commit();
        } else {
            Toast.makeText( this, "Не меняем", Toast. LENGTH_SHORT ).show();
        }
    }
}
