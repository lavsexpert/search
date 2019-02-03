package club.plus1.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends BaseFragmentActivity {

    // Создание и получение фрагмента первой страницы
    protected Fragment getFragment(){
        return MainFragment.newInstance(R.layout.fragment_main);
    }

    // Показ меню
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // Выбор пунктов меню
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

}
