package com.example.android.loginscreen.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.INotificationSideChannel;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import com.example.android.loginscreen.DiaHorarioActivity;
import com.example.android.loginscreen.R;

import java.util.Calendar;
import java.util.Date;

public class HorariosFragment extends Fragment {

    private CalendarView calendarView;
    private Calendar data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_horarios, container, false);

        calendarView = view.findViewById(R.id.calendarView);

        /** Obter a data do dia clicado e instanciar a activity do horario diario **/
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

                /** obter data do dia **/
                data = Calendar.getInstance();
                data.set(Calendar.YEAR, year);
                data.set(Calendar.MONTH, month);
                data.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                /** converter a data para String por forma a ser enviada para a activity seguinte **/
                long proximaActivity = data.getTimeInMillis();
                String proximaActivityString = Long.toString(proximaActivity);

                /** instanciar a nova activity e enviar a data (em formato String) **/
                Intent intent = new Intent(getContext(), DiaHorarioActivity.class);
                intent.putExtra("Data", proximaActivityString);
                startActivity(intent);
            }
        });
        return view;
    }
}