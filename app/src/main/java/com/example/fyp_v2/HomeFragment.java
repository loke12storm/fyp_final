package com.example.fyp_v2;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.example.fyp_v2.Class.Receipt;
import com.example.fyp_v2.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class HomeFragment extends Fragment {

    private AnyChartView mChartView;
    private DatabaseReference mDatabseRef;
    private Calendar mDate = Calendar.getInstance();
    private int curYear=0, month=0;

    private String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    private double[] expenses;
    //private double[] expense = {13,345,67,234,568,234,567,897,456,678,456,375};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        mChartView = rootView.findViewById(R.id.chart_view);
        expenses = new double[]{0,0,0,0,0,0,0,0,0,0,0,0};

        curYear = mDate.get(Calendar.YEAR);

        mDatabseRef = FirebaseDatabase.getInstance().getReference("Receipt").child(FirebaseAuth.getInstance().getUid());

        mDatabseRef.addValueEventListener(new ValueEventListener() {
              @Override
              public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                  for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                      Receipt receipt = postSnapshot.getValue(Receipt.class);
                      String tempYear = receipt.getYear();


                      if(Integer.parseInt(tempYear) == curYear){
                          String temp = receipt.getDate();
                          int month = Integer.parseInt(receipt.getMonth());
                          double tempTotal = Double.parseDouble(receipt.getTotal());

                          switch(month){
                              case 1:
                                  expenses[0] += tempTotal;
                                  break;
                              case 2:
                                  expenses[1] += tempTotal;
                                  break;
                              case 3:
                                  expenses[2] += tempTotal;
                                  break;
                              case 4:
                                  expenses[3] += tempTotal;
                                  break;
                              case 5:
                                  expenses[4] += tempTotal;
                                  break;
                              case 6:
                                  expenses[5] += tempTotal;
                                  break;
                              case 7:
                                  expenses[6] += tempTotal;
                                  break;
                              case 8:
                                  expenses[7] += tempTotal;
                                  break;
                              case 9:
                                  expenses[8] += tempTotal;
                                  break;
                              case 10:
                                  expenses[9] += tempTotal;
                                  break;
                              case 11:
                                  expenses[10] += tempTotal;
                                  break;
                              case 12:
                                  expenses[11] += tempTotal;
                                  break;
                          }
                      }
                  }

                  setupPieChart(expenses);
              }

              @Override
              public void onCancelled(@NonNull DatabaseError databaseError) {
                  Toast.makeText(getActivity(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
              }
          });

        return rootView;
    }

    private void setupPieChart(double[] expenses) {
        Pie pie = AnyChart.pie();
        List<DataEntry> dataEntries = new ArrayList<>();

        for(int i=0; i< months.length; i++)
            dataEntries.add(new ValueDataEntry(months[i], expenses[i]));

        pie.palette(new String[]{"#ABCDEF", "#BBCDEF", "#CCCBDE", "#FF2D00", "#FFA600", "#FFF700",
                "#8BFF00", "#00FF6C", "#00D1FF", "#0008FF", "#969AFF", "#9237FF"});
        pie.data(dataEntries);
        mChartView.setChart(pie);
    }
}