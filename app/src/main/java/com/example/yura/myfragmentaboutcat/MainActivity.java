package com.example.yura.myfragmentaboutcat;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements Fragment1.OnSelectedButtonListener {

    private boolean mIsDynamic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment2 fragment2 = (Fragment2) fragmentManager
                .findFragmentById(R.id.fragment2);
        mIsDynamic = fragment2 == null || !fragment2.isInLayout();
        Toast.makeText(getApplicationContext(), mIsDynamic + "", Toast.LENGTH_SHORT).show();

        // Зная, что второго фрагмента нет, загружаем первый

        if (mIsDynamic) {
            // начинаем транзакцию
            FragmentTransaction ft = fragmentManager.beginTransaction();
            // Создаем и добавляем первый фрагмент
            Fragment1 fragment1 = new Fragment1();
            ft.add(R.id.container, fragment1, "fragment1");
            // Подтверждаем операцию
            ft.commit();
        }

/*
        if (mIsDynamic) {
            // Динамическое переключение на другой фрагмент
            FragmentTransaction ft = fragmentManager.beginTransaction();
            fragment2 = new Fragment2();
            ft.replace(R.id.container, fragment2, "fragment2");
            ft.addToBackStack(null);
            ft.setCustomAnimations(
                    android.R.animator.fade_in, android.R.animator.fade_out);
            ft.commit();
        }
*/

    }

    @Override
    public void onButtonSelected(int buttonIndex) {
        // подключаем FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment2 fragment2;

        // Если фрагмент недоступен
        if (mIsDynamic) {
            // Динамическое переключение на другой фрагмент
            FragmentTransaction ft = fragmentManager.beginTransaction();
            fragment2 = new Fragment2();

            // Подготавливаем аргументы
            Bundle args = new Bundle();
            args.putInt(Fragment2.BUTTON_INDEX, buttonIndex);
            fragment2.setArguments(args);

            ft.replace(R.id.container, fragment2, "fragment2");
            ft.addToBackStack(null);
            ft.setCustomAnimations(
                    android.R.animator.fade_in, android.R.animator.fade_out);
            ft.commit();
        } else {
            // Если фрагмент доступен
            fragment2 = (Fragment2) fragmentManager
                    .findFragmentById(R.id.fragment2);
            fragment2.setDescription(buttonIndex);
        }
    }


}

