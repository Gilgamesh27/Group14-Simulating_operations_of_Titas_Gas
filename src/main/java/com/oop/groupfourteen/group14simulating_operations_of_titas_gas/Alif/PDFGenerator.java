package com.oop.groupfourteen.group14simulating_operations_of_titas_gas.Alif;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class PDFGenerator {

    public static boolean generateGasSupplyReport(Stage stage,
                                                  List<GenerateGasSupplyReportsController.SupplyReportData> data,
                                                  String type, String fromDate, String toDate) {

        // Add null check for data
        if (data == null || data.isEmpty()) {
            System.err.println("No data provided for report generation");
            return false;
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save PDF Report");
        fileChooser.setInitialFileName("gas_supply_report.pdf");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files (*.pdf)", "*.pdf"));
        File file = fileChooser.showSaveDialog(stage);
        if (file == null) return false;

        try {
            PdfWriter writer = new PdfWriter(file);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // Header
            document.add(new Paragraph("GAS SUPPLY REPORT").setFontSize(18));
            document.add(new Paragraph("Report Type: " + type));
            document.add(new Paragraph("Date Range: " + fromDate + " to " + toDate));
            document.add(new Paragraph("Generated: " + LocalDateTime.now()));
            document.add(new Paragraph("\n"));

            // Summary calculations
            double totalSupply = data.stream().mapToDouble(GenerateGasSupplyReportsController.SupplyReportData::getTotalSupply).sum();
            double avgPressure = data.stream().mapToDouble(GenerateGasSupplyReportsController.SupplyReportData::getPressure).average().orElse(0);
            double maxRate = data.stream().mapToDouble(GenerateGasSupplyReportsController.SupplyReportData::getSupplyRate).max().orElse(0);

            // Summary Table
            Table summaryTable = new Table(2);
            summaryTable.addCell(new Cell().add(new Paragraph("Total Supply (m³):")));
            summaryTable.addCell(new Cell().add(new Paragraph(String.format("%.2f", totalSupply))));
            summaryTable.addCell(new Cell().add(new Paragraph("Average Pressure (PSI):")));
            summaryTable.addCell(new Cell().add(new Paragraph(String.format("%.2f", avgPressure))));
            summaryTable.addCell(new Cell().add(new Paragraph("Peak Supply Rate (m³/hr):")));
            summaryTable.addCell(new Cell().add(new Paragraph(String.format("%.2f", maxRate))));
            summaryTable.addCell(new Cell().add(new Paragraph("Total Records:")));
            summaryTable.addCell(new Cell().add(new Paragraph(String.valueOf(data.size()))));

            document.add(summaryTable);
            document.add(new Paragraph("\n"));

            // Data Table
            Table table = new Table(5);
            table.addHeaderCell(new Cell().add(new Paragraph("Date")));
            table.addHeaderCell(new Cell().add(new Paragraph("Zone")));
            table.addHeaderCell(new Cell().add(new Paragraph("Supply Rate")));
            table.addHeaderCell(new Cell().add(new Paragraph("Total Supply")));
            table.addHeaderCell(new Cell().add(new Paragraph("Pressure")));

            for (var r : data) {
                table.addCell(new Cell().add(new Paragraph(r.getDate())));
                table.addCell(new Cell().add(new Paragraph(r.getZone())));
                table.addCell(new Cell().add(new Paragraph(String.format("%.2f", r.getSupplyRate()))));
                table.addCell(new Cell().add(new Paragraph(String.format("%.2f", r.getTotalSupply()))));
                table.addCell(new Cell().add(new Paragraph(String.format("%.2f", r.getPressure()))));
            }

            document.add(table);
            document.close();

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error generating PDF: " + e.getMessage());
            return false;
        }
    }

    public static boolean generateMonthlyBillPdf(Stage stage, Bill bill) {
        if (bill == null) {
            System.err.println("Bill object is null");
            return false;
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Monthly Bill PDF");
        fileChooser.setInitialFileName("monthly_bill_" + bill.getCustomerId() + ".pdf");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files (*.pdf)", "*.pdf"));
        File file = fileChooser.showSaveDialog(stage);
        if (file == null) return false;

        try {
            PdfWriter writer = new PdfWriter(file);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            document.add(new Paragraph("Monthly Gas Bill").setFontSize(20));
            document.add(new Paragraph("Bill ID: " + bill.getBillId()));
            document.add(new Paragraph("Customer ID: " + bill.getCustomerId()));
            document.add(new Paragraph("Billing Period: " + bill.getBillingPeriod()));
            document.add(new Paragraph("\n"));

            Table billDetailsTable = new Table(2);
            billDetailsTable.addCell(new Cell().add(new Paragraph("Previous Reading:")));
            billDetailsTable.addCell(new Cell().add(new Paragraph(String.valueOf(bill.getPreviousReading()))));
            billDetailsTable.addCell(new Cell().add(new Paragraph("Current Reading:")));
            billDetailsTable.addCell(new Cell().add(new Paragraph(String.valueOf(bill.getCurrentReading()))));
            billDetailsTable.addCell(new Cell().add(new Paragraph("Consumption (m³):")));
            billDetailsTable.addCell(new Cell().add(new Paragraph(String.valueOf(bill.getConsumption()))));
            billDetailsTable.addCell(new Cell().add(new Paragraph("Unit Price:")));
            billDetailsTable.addCell(new Cell().add(new Paragraph(String.format("%.2f", bill.getUnitPrice()))));
            billDetailsTable.addCell(new Cell().add(new Paragraph("Total Amount:")));
            billDetailsTable.addCell(new Cell().add(new Paragraph(String.format("%.2f", bill.getTotalAmount()))));
            billDetailsTable.addCell(new Cell().add(new Paragraph("Due Date:")));
            billDetailsTable.addCell(new Cell().add(new Paragraph(bill.getDueDate())));
            billDetailsTable.addCell(new Cell().add(new Paragraph("Status:")));
            billDetailsTable.addCell(new Cell().add(new Paragraph(bill.getStatus())));

            document.add(billDetailsTable);
            document.add(new Paragraph("\n"));
            document.add(new Paragraph("Thank you for your payment."));

            document.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error generating monthly bill PDF: " + e.getMessage());
            return false;
        }
    }
}