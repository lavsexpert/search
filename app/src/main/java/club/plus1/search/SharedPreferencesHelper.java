package club.plus1.search;

import android.content.Context;
import android.content.SharedPreferences;

class SharedPreferencesHelper {
    private static final String SHARED_PREF_NAME = "SHARED_PREF_NAME";
    private static final String SEARCH_SYSTEM_KEY = "SEARCH_SYSTEM_KEY";

    private SharedPreferences sharedPreferences;

    // Создание класса на основе общих SharedPreferences из контекста
    SharedPreferencesHelper(Context context){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
    }

    // Получение поисковой системы
    int getSearchSystem(){
        int idSearchSystem = 0;
        return sharedPreferences.getInt(SEARCH_SYSTEM_KEY, idSearchSystem);
    }

    // Сохраниение поисковой системы
    void setSearchSystem(int idSearchSystem){
        sharedPreferences.edit().putInt(SEARCH_SYSTEM_KEY, idSearchSystem).apply();
    }
}
