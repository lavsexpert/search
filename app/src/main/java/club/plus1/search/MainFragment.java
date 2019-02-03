package club.plus1.search;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainFragment extends Fragment {

    private static final String FRAGMENT = "FRAGMENT";
    private int fragmentID;
    private SharedPreferencesHelper sharedPreferencesHelper;
    private SearchSystem searchSystem;

    // Используется только во фрагменте Настройки
    private RadioGroup radioGroup;
    private RadioGroup.OnCheckedChangeListener onCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            // Сохранение выбранной системы в SharedPreferences и SearchSystem
            int idSearchSystem = radioGroup.getCheckedRadioButtonId();
            sharedPreferencesHelper.setSearchSystem(idSearchSystem);
            searchSystem.setSystem(getActivity(), idSearchSystem);
        }
    };

    // Используется только во фрагменте Поиск
    private Button buttonSearch;
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // Готовим запрос для поиска
            if(getActivity() != null) {
                EditText editSearch = getActivity().findViewById(R.id.editSearch);
                String query = editSearch.getText().toString();
                // Открываем браузер в выбранной поисковой систме с поиском
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, searchSystem.searchUri(query));
                startActivity(browserIntent);
            }
        }
    };

    // Создание экземпляра фрагмента с передачей ему соответствующего layout
    public static MainFragment newInstance(int id) {
        Bundle args = new Bundle();
        args.putInt(FRAGMENT, id);

        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    // Создание View фрагмента с подключением обработчиков и интерфейсных элементов
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // Получение и отрисовка View фрагмента
        Bundle args = getArguments();
        if (args != null ) {
            fragmentID = args.getInt(FRAGMENT);
        }
        View v = inflater.inflate(fragmentID, container, false);

        // Создание и заполнение SharedPreferencesHelper и SearchSystem
        if (sharedPreferencesHelper == null && getActivity() != null) {
            sharedPreferencesHelper = new SharedPreferencesHelper(getActivity());
        }
        if (searchSystem == null) {
            searchSystem = new SearchSystem(getActivity(), sharedPreferencesHelper.getSearchSystem());
        }

        // Для фрагмента Настройки включаем переключатели
        if (fragmentID == R.layout.fragment_options) {
            radioGroup = v.findViewById(R.id.radioGroup);
            radioGroup.setOnCheckedChangeListener(onCheckedChangeListener);
            // Переключатель устанавливается на основе настроек SharedPreferences
            RadioButton radioSearchSystem = v.findViewById(sharedPreferencesHelper.getSearchSystem());
            if(radioSearchSystem != null) {
                radioSearchSystem.toggle();
            }
        }

        // Для фрагмента Поиск включаем кнопку поиска и переименуем её
        if (fragmentID == R.layout.fragment_search) {
            buttonSearch = v.findViewById(R.id.buttonSearch);
            buttonSearch.setOnClickListener(onClickListener);
            buttonSearch.setText(String.format("Искать в %s", searchSystem.Name));
        }

        return v;
    }

    // Когда фрагмент уже создан
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
