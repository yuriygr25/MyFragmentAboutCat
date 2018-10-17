package com.example.yura.myfragmentaboutcat;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class Fragment2 extends Fragment {
    private TextView mInfoTextView;
    private ImageView mCatImageView;
    private String[] mCatDescriptionArray;
    // Имя для аргумента
    public static final String BUTTON_INDEX = "button_index";
    // Значение по умолчанию
    private static final int BUTTON_INDEX_DEFAULT = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =
                inflater.inflate(R.layout.fragment2, container, false);

        mInfoTextView = (TextView) rootView.findViewById(R.id.textView);
        mCatImageView = (ImageView) rootView.findViewById(R.id.imageView);

        // загружаем массив из ресурсов
        mCatDescriptionArray = getResources().getStringArray(R.array.cats);

        // Получим индекс, если имеется
        Bundle args = getArguments();
        int buttonIndex = args != null ? args.getInt(BUTTON_INDEX,
                BUTTON_INDEX_DEFAULT) : BUTTON_INDEX_DEFAULT;
        // Если индекс обнаружен, то используем его
        if (buttonIndex != BUTTON_INDEX_DEFAULT)
            setDescription(buttonIndex);

        return rootView;
    }

    public interface OnSelectedButtonListener {
        void onButtonSelected(int buttonIndex);
    }

    public void setDescription(int buttonIndex) {
        String catDescription = mCatDescriptionArray[buttonIndex];
        mInfoTextView.setText(catDescription);

        switch (buttonIndex) {
            case 1:
                mCatImageView.setImageResource(R.drawable.cat_yellow);
                break;
            case 2:
                mCatImageView.setImageResource(R.drawable.cat_white);
                break;
            case 3:
                mCatImageView.setImageResource(R.drawable.cat_green);
                break;

            default:
                break;
        }
    }

}


