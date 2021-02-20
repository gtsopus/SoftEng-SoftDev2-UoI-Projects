package outputManagePackage;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import dataManagePackage.Database;
import dataManagePackage.Taxpayer;

public class CreateBarChart extends CreateChart{

	@Override
	protected void setValues(Taxpayer taxpayer) {
		String taxVariationType = taxpayer.getTaxInxrease()!=0? "Tax Increase" : "Tax Decrease";
		double taxVariationAmount = taxpayer.getTaxInxrease()!=0? taxpayer.getTaxInxrease() : taxpayer.getTaxDecrease()*(-1);
		
		chartDataset.setValue(taxpayer.getBasicTax(), "Tax", "Basic Tax");
		chartDataset.setValue(taxVariationAmount, "Tax", taxVariationType);
		chartDataset.setValue(taxpayer.getTotalTax(), "Tax", "Total Tax");
	}

	@Override
	protected void createChart() {
		taxAnalysisJFreeChart = ChartFactory.createBarChart("Tax Analysis Bar Chart", "",  "Tax Analysis in $", (CategoryDataset) chartDataset, PlotOrientation.VERTICAL, true, true, false);
	}

	@Override
	protected ChartFrame getChartFrame(int taxpayerIndex, Database database) {
		return new ChartFrame(database.getTaxpayerNameAfmValuesPairList(taxpayerIndex), taxAnalysisJFreeChart);
	}

	@Override
	protected void getDataset() {
		chartDataset = new DefaultCategoryDataset();
	}
}
