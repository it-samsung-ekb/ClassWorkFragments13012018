package ru.rsvpu.fragments;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnFragmentA, btnFragmentB;

    FragmentA fragmentA = new FragmentA();
    FragmentB fragmentB = new FragmentB();
    FragmentManager fragmentManager;
    boolean showB = false, showA = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        fragmentManager = getSupportFragmentManager();
    }

    void initView() {
        btnFragmentA = findViewById(R.id.activity_main_btn_showFirstFragment);
        btnFragmentB = findViewById(R.id.activity_main_btn_showSecondFragment);
        btnFragmentA.setOnClickListener(this);
        btnFragmentB.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.activity_main_btn_showFirstFragment:

                showA = true; //говорим, что сейчас будет показываться фрагмент А
                if (showB) { // проверяем был ли показан фрагмент В
                    showB = false; // говорим, что фрагмент В больше показывается
                    fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("b")).commit(); // скрываем фрагмент В
                }
                if (fragmentManager.findFragmentByTag("a") == null) // проверяем не был ли у нас уже инициализирован фрагмент А
                    fragmentManager.beginTransaction().add(R.id.activity_main_container, fragmentA, "a").commit(); // если не был, то добавляем его
                else
                    fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag("a")).commit(); // если был, то просто показываем
                break;

            case R.id.activity_main_btn_showSecondFragment: // логика индентична

                showB = true;

                if(showA){
                    showA = false;
                    fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("a")).commit();
                }
                if (fragmentManager.findFragmentByTag("b") == null)
                    fragmentManager.beginTransaction().add(R.id.activity_main_container, fragmentB, "b").commit();
                else
                    fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag("b")).commit();
                break;
        }
    }
}
