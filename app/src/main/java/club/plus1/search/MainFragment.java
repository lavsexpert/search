package club.plus1.search;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainFragment extends Fragment {

    private static final String FRAGMENT = "FRAGMENT";
    private int fragmentID;

    // Используется только во фрагменте Настройки
    private RadioGroup radioGroup;
    private RadioGroup.OnCheckedChangeListener onCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            Toast.makeText(getActivity(),"Переключатель", Toast.LENGTH_LONG).show();
        }
    };


    // Используется только во фрагменте Поиск
    private Button buttonSearch;
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(getActivity(),"Кнопка", Toast.LENGTH_LONG).show();
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

        Bundle args = getArguments();
        if (args != null ) {
            fragmentID = args.getInt(FRAGMENT);
        }
        View v = inflater.inflate(fragmentID, container, false);

        // Для фрагмента Настройки включаем переключатели
        if (fragmentID == R.layout.fragment_options) {
            radioGroup = v.findViewById(R.id.radioGroup);
            radioGroup.setOnCheckedChangeListener(onCheckedChangeListener);
        }

        // Для фрагмента Поиск включаем кнопку поиска
        if (fragmentID == R.layout.fragment_search) {
            buttonSearch = v.findViewById(R.id.buttonSearch);
            buttonSearch.setOnClickListener(onClickListener);
        }

        return v;
    }

    // Когда фрагмент уже создан
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
