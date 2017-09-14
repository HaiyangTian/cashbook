package com.example.sun.cashbook;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.LineRadarDataSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.LineChartView;

/**
 * Created by Sun on 2017/9/14.
 */

public class ChartActivity extends Activity{
    private LineChartView mChart;
    private LineChartData mDate;
    private Map<String,Integer> table = new TreeMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chart);
        mChart=(LineChartView) findViewById(R.id.chart);
        mDate = new LineChartData();
        List<CashBean> lists = (List<CashBean>) getIntent().getSerializableExtra("cost_list");
        generateValues(lists);
        generateDate();
    }
    private void generateDate() {
        List<Line> lines = new ArrayList<>();
        List<PointValue> values = new ArrayList<>();
        int indexX = 0;
        for(Integer value : table.values()){
            values.add(new PointValue(indexX, value));
            indexX++;
        }
        Line line = new Line(values);
        line.setColor(ChartUtils.COLORS[0]);
        line.setShape(ValueShape.CIRCLE);
        line.setPointColor(ChartUtils.COLORS[1]);
        lines.add(line);
        mDate.setLines(lines);
        mChart.setLineChartData(mDate);
    }

    private void generateValues(List<CashBean> lists) {
        if(lists!=null){
            for (int i = 0 ;i<lists.size();i++){
                CashBean cashBean = lists.get(i);
                String costDate = cashBean.CashDate;
                int costMoney = Integer.parseInt(cashBean.CashMoney);
                if (!table.containsKey(costDate)){
                    table.put(costDate,costMoney);
                }else{
                    int oldMoney = table.get(costDate);
                    table.put(costDate,oldMoney+costMoney);
                }

            }

        }

    }
}
