package club.plus1.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public abstract class BaseFragmentActivity extends AppCompatActivity {

    // Создание фрагмента и подключение его к Activity
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState == null){
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, getFragment())
                    .commit();
        }
    }

    // Переключение фрагментов по переданному layout
    protected void switchFragment(int id) {
        Fragment fragment = getSupportFragmentManager().findFragmentById(id);
        if (fragment == null) {
            fragment = MainFragment.newInstance(id);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, fragment)
                    .commit();
        } else {
            Toast.makeText(this, "Не меняем", Toast.LENGTH_SHORT ).show();
        }
    }

    // Абстрактный метод получения фрагмента, требующий реализации в классе-наследнике
    protected abstract Fragment getFragment();
}
