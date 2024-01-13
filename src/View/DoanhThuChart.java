package View;
import javax.swing.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class DoanhThuChart extends JFrame {
    public DoanhThuChart(String title) {
        super(title);

        // Tạo dataset (dữ liệu biểu đồ)
        CategoryDataset dataset = createDataset();

        // Tạo biểu đồ từ dataset
        JFreeChart chart = ChartFactory.createBarChart(
                "Biểu đồ Doanh thu",
                "Tháng",
                "Doanh thu (đơn vị)",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, true
        );

        // Thêm biểu đồ vào giao diện
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 370));
        setContentPane(chartPanel);
    }

    private CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Thêm dữ liệu doanh thu cho từng tháng
        dataset.addValue(2000, "Doanh thu", "Tháng 1");
        dataset.addValue(2500, "Doanh thu", "Tháng 2");
        dataset.addValue(3000, "Doanh thu", "Tháng 3");
        dataset.addValue(3500, "Doanh thu", "Tháng 4");
        dataset.addValue(5000, "Doanh thu", "Tháng 5");
        dataset.addValue(2000, "Doanh thu", "Tháng 6");
        dataset.addValue(2500, "Doanh thu", "Tháng 7");
        dataset.addValue(3000, "Doanh thu", "Tháng 8");
        dataset.addValue(3500, "Doanh thu", "Tháng 9");
        dataset.addValue(5000, "Doanh thu", "Tháng 10");

        return dataset;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DoanhThuChart example = new DoanhThuChart("Biểu đồ Doanh thu");
            example.setSize(800, 600);
            example.setLocationRelativeTo(null);
            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            example.setVisible(true);
        });
    }
}
