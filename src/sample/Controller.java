package sample;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Controller implements Initializable {
    //    final CategoryAxis xAxis = new CategoryAxis();
    @FXML // fx:id="xAxis"
    CategoryAxis xAxis;
    @FXML // fx:id="yAxis"
    NumberAxis yAxis;

    @FXML // fx:id="chart"
    LineChart<String, Number> chart;
    XYChart.Series series = new XYChart.Series();


    public void dayClicked() {
        String addr = "https://min-api.cryptocompare.com/data/histoday?aggregate=1&e=CCCAGG&extraParams=CryptoCompare&fsym=BTC&limit=10&tryConversion=false&tsym=USD";

        try {
            URL address = new URL(addr);
            JsonReader reader = new JsonReader(new InputStreamReader(address.openStream()));
            Gson gson = new Gson();
            JsonObject root = gson.fromJson(reader, JsonObject.class);
            JsonArray data = root.getAsJsonArray("Data");

//            SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
            Calendar c = Calendar.getInstance();
            String year = "";
            xAxis.setLabel("Day");

            series.getData().clear();
            for (int i = 0; i < data.size(); i++) {
                long time_data = data.get(i).getAsJsonObject().get("time").getAsLong() * 1000;
                String time_data_str = Long.toString(time_data);
                Number price = data.get(i).getAsJsonObject().get("open").getAsNumber();
                Date d = new Date(time_data);
                c.setTime(d);

                int month = c.get(Calendar.MONTH) + 1;
                int day = c.get(Calendar.DAY_OF_MONTH);
                String date = Integer.toString(month) + "/" + Integer.toString(day);
                if (i == 0) {
                    year = Integer.toString(c.get(Calendar.YEAR));
                }

                XYChart.Data<String, Number> item = new XYChart.Data<String, Number>(date, price);
                series.getData().add(item);

                series.setName(year);

            }
            chart.getData().removeAll(series);
            chart.getData().add(series);
            System.out.println("end");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void hourClicked() {
        String addr = "https://min-api.cryptocompare.com/data/histohour?aggregate=1&e=CCCAGG&extraParams=CryptoCompare&fsym=BTC&limit=10&tryConversion=false&tsym=USD";

        try {
            URL address = new URL(addr);
            JsonReader reader = new JsonReader(new InputStreamReader(address.openStream()));
            Gson gson = new Gson();
            JsonObject root = gson.fromJson(reader, JsonObject.class);
            JsonArray data = root.getAsJsonArray("Data");

//            SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
            Calendar c = Calendar.getInstance();
            String date = "";
            xAxis.setLabel("Hour");

            series.getData().clear();
            for (int i = 0; i < data.size(); i++) {
                long time_data = data.get(i).getAsJsonObject().get("time").getAsLong() * 1000;
                String time_data_str = Long.toString(time_data);
                Number price = data.get(i).getAsJsonObject().get("open").getAsNumber();
                Date d = new Date(time_data);
                c.setTime(d);

                int am_pm = c.get(Calendar.AM_PM);
                int hour_int = c.get(Calendar.HOUR) + 1;
                if (am_pm == 1 && hour_int != 0) {
                    hour_int += 12;
                }
                String hour = Integer.toString(hour_int);

                String year = Integer.toString(c.get(Calendar.YEAR));
                String month = Integer.toString(c.get(Calendar.MONTH) + 1);
                String day = Integer.toString(c.get(Calendar.DAY_OF_MONTH));

                if (i == 0) {
                    date = month + "/" + day + "/" + year;
                }

                XYChart.Data<String, Number> item = new XYChart.Data<String, Number>(hour, price);
                series.getData().add(item);

                series.setName(date);

            }
            chart.getData().removeAll(series);
            chart.getData().add(series);
            System.out.println("end");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void minClicked() {
        String addr = "https://min-api.cryptocompare.com/data/histominute?aggregate=1&e=CCCAGG&extraParams=CryptoCompare&fsym=BTC&limit=10&tryConversion=false&tsym=USD";

        try {
            URL address = new URL(addr);
            JsonReader reader = new JsonReader(new InputStreamReader(address.openStream()));
            Gson gson = new Gson();
            JsonObject root = gson.fromJson(reader, JsonObject.class);
            JsonArray data = root.getAsJsonArray("Data");

//            SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
            Calendar c = Calendar.getInstance();
            String date = "";
            xAxis.setLabel("Minute");

            series.getData().clear();
            for (int i = 0; i < data.size(); i++) {
                long time_data = data.get(i).getAsJsonObject().get("time").getAsLong() * 1000;
                String time_data_str = Long.toString(time_data);
                Number price = data.get(i).getAsJsonObject().get("open").getAsNumber();
                Date d = new Date(time_data);
                c.setTime(d);

                int am_pm = c.get(Calendar.AM_PM);
                int hour_int = c.get(Calendar.HOUR) + 1;
                int min = c.get(Calendar.MINUTE);
                if (am_pm == 1 && hour_int != 0) {
                    hour_int += 12;
                }
                String time = Integer.toString(hour_int) + ":" + Integer.toString(min);

                String year = Integer.toString(c.get(Calendar.YEAR));
                String month = Integer.toString(c.get(Calendar.MONTH) + 1);
                String day = Integer.toString(c.get(Calendar.DAY_OF_MONTH));

                if (i == 0) {
                    date = month + "/" + day + "/" + year;
                }

                XYChart.Data<String, Number> item = new XYChart.Data<String, Number>(time, price);
                series.getData().add(item);

                series.setName(date);

            }
            chart.getData().removeAll(series);
            chart.getData().add(series);
            System.out.println("end");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // This happens on first load
        chart.setTitle("Crypto currency");
        chart.setAnimated(false);

        xAxis.setAutoRanging(true);
        xAxis.setAnimated(false); // axis animations are removed
        yAxis.setLabel("Price");
        yAxis.setAutoRanging(true);
        yAxis.setAnimated(false); // axis animations are removed
    }

    //
}
