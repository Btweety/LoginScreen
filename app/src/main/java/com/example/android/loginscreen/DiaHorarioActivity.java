package com.example.android.loginscreen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.alamkanak.weekview.DateTimeInterpreter;
import com.alamkanak.weekview.MonthLoader;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DiaHorarioActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private WeekView weekView;
    private Calendar calendar;
    private String data;
    private long datamili;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dia_horario);

        weekView = findViewById(R.id.weekview);
        toolbar = findViewById(R.id.nav_action);

        /** Indicar que a Action Bar é a toolbar que criámos, tirar o seu título automático e acrescentar a seta para voltar */
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /** Dar uma ação à seta para voltar */
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        /**  Receber data escolhida no HorariosFragment e convertê-la na vista de Dias da WeekView*/
        calendar = Calendar.getInstance();
        data = getIntent().getStringExtra("Data");
        datamili = Long.parseLong(data);
        calendar.setTimeInMillis(datamili);
        weekView.goToDate(calendar);

        weekView.setMonthChangeListener(new MonthLoader.MonthChangeListener() {
            @Override
            public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {
                List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();

                /** Exemplo de criação de um evento (comentado) */
                /*Calendar startTime = Calendar.getInstance();
                startTime.set(Calendar.HOUR_OF_DAY, 3);
                startTime.set(Calendar.MINUTE, 0);
                startTime.set(Calendar.MONTH, newMonth - 1);
                startTime.set(Calendar.YEAR, newYear);
                Calendar endTime = (Calendar) startTime.clone();
                endTime.add(Calendar.HOUR, 1);
                endTime.set(Calendar.MONTH, newMonth - 1);
                WeekViewEvent event = new WeekViewEvent(1, "test", startTime, endTime);
                events.add(event); */

                return events;
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
