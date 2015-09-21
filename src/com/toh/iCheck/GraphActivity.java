package com.toh.iCheck;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.SeriesSelection;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.app.Activity;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

public class GraphActivity extends Activity implements OnClickListener {

	private View mViewZoomIn;
	private View mViewZoomOut;
	private View mViewZoomReset;
	private GraphicalView mChart;
	RegistrationAdapter adapter_ob;
	RegistrationOpenHelper helper_ob;
	SQLiteDatabase db_ob;
	// String cursor;
	double value;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.graphactivity);
		adapter_ob = new RegistrationAdapter(this);
		// nameList = (TextView) findViewById(R.id.lv_name);

		mViewZoomIn = findViewById(R.id.zoom_in);
		mViewZoomOut = findViewById(R.id.zoom_out);
		mViewZoomReset = findViewById(R.id.zoom_reset);
		mViewZoomIn.setOnClickListener((OnClickListener) this);
		mViewZoomOut.setOnClickListener((OnClickListener) this);
		mViewZoomReset.setOnClickListener((OnClickListener) this);
		openChart();
	}

	private void openChart() {

		ArrayList<String> numsy1 = new ArrayList<String>();
		numsy1 = adapter_ob.queryNameString();

		// Creating TimeSeries for Visits
		TimeSeries visitsSeries = new TimeSeries("BAC");

		// Creating TimeSeries for Views
		// TimeSeries viewsSeries = new TimeSeries("Views");

		// Adding data to Visits and Views Series
		// for (int i = 0; i < dt.length; i++) {
		// visitsSeries.add(dt[i], value);
		// // viewsSeries.add(dt[i], views[i]);
		// }

		for (int i = 0; i < numsy1.size(); i++) {
			try {
				// System.out.println(cursor.toString());
				// double[] hehe;
				String sv = numsy1.get(i).toString();
				value = Double.valueOf(sv.toString());
				// System.out.println(sv);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(value);
			visitsSeries.add(i, value);
		}
		// adapter_ob.Close();
		if (mChart != null) {
			mChart.repaint();
		}

		// Creating a dataset to hold each series
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();

		// Adding Visits Series to the dataset
		dataset.addSeries(visitsSeries);

		// Adding Visits Series to dataset
		// dataset.addSeries(viewsSeries);

		// Creating XYSeriesRenderer to customize visitsSeries
		XYSeriesRenderer visitsRenderer = new XYSeriesRenderer();
		visitsRenderer.setColor(Color.WHITE);
		visitsRenderer.setPointStyle(PointStyle.CIRCLE);
		visitsRenderer.setFillPoints(true);
		visitsRenderer.setLineWidth(2);
		visitsRenderer.setChartValuesTextSize(15);
		visitsRenderer.setDisplayChartValues(true);
		visitsRenderer.setChartValuesTextAlign(Align.CENTER);

		// Creating XYSeriesRenderer to customize viewsSeries
		// XYSeriesRenderer viewsRenderer = new XYSeriesRenderer();
		// viewsRenderer.setColor(Color.YELLOW);
		// viewsRenderer.setPointStyle(PointStyle.CIRCLE);
		// viewsRenderer.setFillPoints(true);
		// viewsRenderer.setLineWidth(2);
		// viewsRenderer.setDisplayChartValues(true);

		// Creating a XYMultipleSeriesRenderer to customize the whole chart
		XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();

		multiRenderer.setChartTitle("BAC Chart");
		multiRenderer.setXTitle("Number of test");
		multiRenderer.setYTitle("BAC");
		multiRenderer.setShowGrid(true);
		multiRenderer.setYAxisMin(0);
		multiRenderer.setYAxisMax(0.1);
		multiRenderer.setXAxisMin(0);
		multiRenderer.setXAxisMax(5);
		multiRenderer.setLabelsTextSize(15);
		multiRenderer.setLegendTextSize(20);
		multiRenderer.setAxisTitleTextSize(25);
		multiRenderer.setChartTitleTextSize(40);
		multiRenderer.setPointSize(5f);
		multiRenderer.setZoomButtonsVisible(false);
		multiRenderer.setZoomEnabled(true);
		multiRenderer.setExternalZoomEnabled(true);
		multiRenderer.setLabelsColor(Color.WHITE);
		// Adding visitsRenderer and viewsRenderer to multipleRenderer
		// Note: The order of adding dataseries to dataset and renderers to
		// multipleRenderer
		// should be same
		multiRenderer.addSeriesRenderer(visitsRenderer);
		// multiRenderer.addSeriesRenderer(viewsRenderer);

		// Getting a reference to LinearLayout of the MainActivity Layout
		LinearLayout chartContainer = (LinearLayout) findViewById(R.id.chart_container);

		// Creating a Time Chart
		mChart = (GraphicalView) ChartFactory.getLineChartView(
				getBaseContext(), dataset, multiRenderer);

		multiRenderer.setClickEnabled(true);
		multiRenderer.setSelectableBuffer(10);

		// Setting a click event listener for the graph
		mChart.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Format formatter = new SimpleDateFormat("dd-MMM-yyyy");

				SeriesSelection seriesSelection = mChart
						.getCurrentSeriesAndPoint();

				if (seriesSelection != null) {
					int seriesIndex = seriesSelection.getSeriesIndex();
					String selectedSeries = "Visits";
					if (seriesIndex == 0)
						selectedSeries = "Visits";
					else
						selectedSeries = "Views";

					// Getting the clicked Date ( x value )
					long clickedDateSeconds = (long) seriesSelection
							.getXValue();
					Date clickedDate = new Date(clickedDateSeconds);
					String strDate = formatter.format(clickedDate);

					// Getting the y value
					int amount = (int) seriesSelection.getValue();

					// Displaying Toast Message
					Toast.makeText(getBaseContext(),
							selectedSeries + " on " + strDate + " : " + amount,
							Toast.LENGTH_SHORT).show();
				}
			}
		});

		// Adding the Line Chart to the LinearLayout
		chartContainer.addView(mChart);
	}

	// @Override
	// public boolean onCreateOptionsMenu(Menu menu) {
	// getMenuInflater().inflate(R.menu.activity_main, menu);
	// return true;
	// }
	@Override
	public void onClick(final View v) {
		switch (v.getId()) {
		case R.id.zoom_in:
			mChart.zoomIn();
			break;

		case R.id.zoom_out:
			mChart.zoomOut();
			break;

		case R.id.zoom_reset:
			mChart.zoomReset();
			break;

		default:
			break;
		}
	}
}
