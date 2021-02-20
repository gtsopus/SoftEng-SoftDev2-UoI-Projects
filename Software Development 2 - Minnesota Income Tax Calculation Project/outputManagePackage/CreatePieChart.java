package outputManagePackage;

import java.text.DecimalFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import dataManagePackage.Database;
import dataManagePackage.Taxpayer;

public class CreatePieChart extends CreateChart{	

	@Override
	protected void setValues(Taxpayer taxpayer) {
		chartPieDataset.setValue("Basic", taxpayer.getReceiptsTotalAmount("Basic"));
		chartPieDataset.setValue("Entertainment", taxpayer.getReceiptsTotalAmount("Entertainment"));
		chartPieDataset.setValue("Travel", taxpayer.getReceiptsTotalAmount("Travel"));
		chartPieDataset.setValue("Health", taxpayer.getReceiptsTotalAmount("Health"));
		chartPieDataset.setValue("Other", taxpayer.getReceiptsTotalAmount("Other"));
	}

	@Override
	protected void createChart() {
		receiptPieJFreeChart = ChartFactory.createPieChart("Receipt Pie Chart", chartPieDataset);
		piePlot = (PiePlot)receiptPieJFreeChart.getPlot();
		PieSectionLabelGenerator generator = new StandardPieSectionLabelGenerator("{0}: {1}$ ({2})", new DecimalFormat("0.00"), new DecimalFormat("0.00%"));
		piePlot.setLabelGenerator(generator);
	}

	@Override
	protected ChartFrame getChartFrame(int taxpayerIndex, Database database) {
		return new ChartFrame(database.getTaxpayerNameAfmValuesPairList(taxpayerIndex), receiptPieJFreeChart);
	}

	@Override
	protected void getDataset() {
		chartPieDataset = new DefaultPieDataset();
	}
}
