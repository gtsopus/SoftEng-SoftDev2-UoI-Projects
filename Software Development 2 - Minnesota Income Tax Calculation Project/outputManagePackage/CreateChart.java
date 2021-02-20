package outputManagePackage;

import java.awt.Dialog.ModalExclusionType;

import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import dataManagePackage.Database;
import dataManagePackage.Taxpayer;

public abstract class CreateChart {
	protected DefaultPieDataset chartPieDataset;
	protected DefaultCategoryDataset chartDataset;
	protected PiePlot piePlot;	
	protected JFreeChart taxAnalysisJFreeChart;
	protected JFreeChart receiptPieJFreeChart;
		
	public DefaultPieDataset getReceiptPieChartDataset() {
		return chartPieDataset;
	}
	
	public DefaultCategoryDataset getTaxAnalysisBarChartDataset() {
	return chartDataset;
}
	
	public void createTaxpayerJFreeChart(int taxpayerIndex){	
		
		getDataset();
		Database database = Database.getInstance();
		Taxpayer taxpayer = database.getTaxpayerFromArrayList(taxpayerIndex);
		
		setValues(taxpayer);
		createChart(); 

		ChartFrame receiptPieChartFrame = getChartFrame(taxpayerIndex, database);
		receiptPieChartFrame.pack();
		receiptPieChartFrame.setResizable(false);
		receiptPieChartFrame.setLocationRelativeTo(null);
		receiptPieChartFrame.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		receiptPieChartFrame.setVisible(true);
	}
	
	protected abstract void getDataset();
	protected abstract void setValues(Taxpayer taxpayer);
	protected abstract void  createChart();
	protected abstract ChartFrame getChartFrame(int taxpayerIndex, Database database);
}
