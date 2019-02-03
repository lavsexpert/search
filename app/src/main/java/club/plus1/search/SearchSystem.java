package club.plus1.search;

import android.content.Context;
import android.net.Uri;

class SearchSystem {
    String Name;
    private String Address;

    // Создание класса
    SearchSystem(Context context, int idSearchSystem){
        setSystem(context, idSearchSystem);
    }

    // Создание Uri для открытия в баузере
    Uri searchUri(String Query){
        return Uri.parse(Address+Query);
    }

    // Заполнение имени и адреса поисковой системы
    void setSystem(Context context, int idSearchSystem){
        Name = context.getString(R.string.google);
        Address = "https://www.google.com/search?q=";
        switch (idSearchSystem){
            case R.id.radioGoogle:
                Name = context.getString(R.string.google);
                Address = "https://www.google.com/search?q=";
                break;
            case R.id.radioYandex:
                Name = context.getString(R.string.yandex);
                Address = "https://yandex.ru/search/?text=";
                break;
            case R.id.radioBing:
                Name = context.getString(R.string.bing);
                Address = "https://www.bing.com/search?q=";
                break;
        }
    }
}
