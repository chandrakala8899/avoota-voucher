package com.neoteric.voucherdemo;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.layout.property.VerticalAlignment;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;

public class VoucherPDFGenerator {

    private static void addFareRow(Table table, String leftText, String rightText) {
        addFareRow(table, leftText, rightText, false);
    }

    private static void addFareRow(Table table, String leftText, String rightText, boolean isBold) {
        // Left-aligned text (Fare Type)
        Cell leftCell = new Cell()
                .add(new Paragraph(leftText).setBold())
                .setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.LEFT);

        // Right-aligned text (Cost)
        Cell rightCell = new Cell()
                .add(new Paragraph(rightText).setBold())
                .setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.RIGHT);

        table.addCell(leftCell);
        table.addCell(rightCell);

        // Add separator line
        table.addCell(new Cell(1, 2)
                .add(new LineSeparator(new SolidLine()))
                .setBorder(Border.NO_BORDER));
    }

    public static void main(String[] args) {
        try {
            // Define output PDF file
            String dest = "Voucher_Details.pdf";
            PdfWriter writer = new PdfWriter(dest);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);
            document.setMargins(10, 30, 10, 30);

            Table headerTable = new Table(new float[]{1, 1}).setWidth(UnitValue.createPercentValue(100));
            ImageData logo = ImageDataFactory.create("src/main/resources/image/img_1.png");
            Image logoImage = new Image(logo).setWidth(30).setHeight(30);
            headerTable.addCell(new Cell().add(logoImage).setBorder(Border.NO_BORDER));
            headerTable.addCell(new Cell().add(new Paragraph("Vouchered").setFontSize(16).setFontColor(ColorConstants.GREEN))
                    .add(new Paragraph("Booking ID: TJS20960133855").setFontSize(14))
                    .setBorder(Border.NO_BORDER)
                    .setVerticalAlignment(VerticalAlignment.MIDDLE));
            document.add(headerTable);
            document.add(new LineSeparator(new SolidLine(0.5f)));

            document.add(new Paragraph("Hotel Neem Tree" ));
            document.add(new Paragraph("Shamshabad Road, " ));
             document.add(new Paragraph("Hyderabad, Telangana, India, 501218"));

            document.add(new Paragraph("Phone No:91-8500912345"));
            document.add(new Paragraph("Last Cancellation Date: 28-02-2025").setBold().setFontSize(14).setFontColor(new DeviceRgb(0, 0, 128)));

            // Booking Details Table
            Table table = new Table(UnitValue.createPercentArray(new float[]{2,2,2,2}))
                    .useAllAvailableWidth()
                    .setTextAlignment(TextAlignment.LEFT);
            table.addCell(new Cell().add(new Paragraph("Check In").setFontColor(new DeviceRgb(0, 0, 128))));
            table.addCell(new Cell().add(new Paragraph("Check Out").setFontColor(new DeviceRgb(0, 0, 128))));
            table.addCell(new Cell().add(new Paragraph("Total Rooms").setFontColor(new DeviceRgb(0, 0, 128))));
            table.addCell(new Cell().add(new Paragraph("Total Stay").setFontColor(new DeviceRgb(0, 0, 128))));
            table.addCell(new Cell().add(new Paragraph("28-02-2025")));
            table.addCell(new Cell().add(new Paragraph("01-03-2025")));
            table.addCell(new Cell().add(new Paragraph("1")));
            table.addCell(new Cell().add(new Paragraph("1 Night(s)")));
            document.add(table);
//            document.add(new LineSeparator(new SolidLine()));
            document.add(new Paragraph("Standard Room,1 Queen incl:ROOM ONLY Name:Mrs Tirummala Tamma").setFontSize(12));
            document.add(new Paragraph("Bed-1 Queen Bed -"));
            document.add(new Paragraph("package deal"));
            SolidLine line = new SolidLine(0.5f); // Thickness reduced to 0.5
            LineSeparator separator = new LineSeparator(line);
            document.add(separator);
            // Special Request Section
            document.add(new Paragraph("Special Request(s)").setBold().setFontSize(12));
            document.add(new Paragraph("- Add New"));
            SolidLine line4 = new SolidLine(0.5f); // Thickness reduced to 0.5
            LineSeparator separator4 = new LineSeparator(line4);
            document.add(separator4);
            document.add(new Paragraph("Contact Details").setBold().setFontSize(12));
            document.add(new Paragraph("Email: tirumalatamma17@gmail.com"));
            document.add(new Paragraph("Mobile: 9849527319"));
            SolidLine line1= new SolidLine(0.5f); // Thickness reduced to 0.5
            LineSeparator separator1 = new LineSeparator(line1);
            document.add(separator1);

            // Cancellation Policy Table
            document.add(new Paragraph("Cancellation Policy").setFontSize(14));

            Table cancelTable = new Table(UnitValue.createPercentArray(new float[]{3, 3, 3}))
                    .useAllAvailableWidth()
                    .setTextAlignment(TextAlignment.CENTER);

            cancelTable.addCell(new Cell().add(new Paragraph("Cancellation on or After").setBold()));
            cancelTable.addCell(new Cell().add(new Paragraph("Cancellation on or Before").setBold()));
            cancelTable.addCell(new Cell().add(new Paragraph("Cancellation Charges/Comments").setBold()));
            cancelTable.addCell(new Cell().add(new Paragraph("28-02-2025")));
            cancelTable.addCell(new Cell().add(new Paragraph("01-03-2025")));
            cancelTable.addCell(new Cell().add(new Paragraph("₹1,061.79")));

            document.add(cancelTable);
            // Additional Cancellation Notes
            document.add(new Paragraph("* Each booking is applicable to ₹20 per room per night non-refundable service fees."));
            document.add(new Paragraph("* No Show will attract full cancellation charge unless otherwise specified."));
            document.add(new Paragraph("* Early check-out will attract full cancellation charge unless otherwise specified."));
            SolidLine line2 = new SolidLine(0.5f); // Thickness reduced to 0.5
            LineSeparator separator2 = new LineSeparator(line2);
            document.add(separator2);

            document.add(new Paragraph("Booking Notes").setFontSize(14).setBold());

            document.add(new Paragraph("Policies").setFontSize(10));
            document.add(new Paragraph("Know Before You Go: Social distancing measures are in place.").setBold());

            document.add(new Paragraph("Checkin Instructions").setFontSize(14));
            document.add(new Paragraph("Special Instructions: This property's policy is to accept bookings only from citizens or residents of India. Front desk staff will greet guests on arrival."));
            document.add(new Paragraph("Instructions: Extra-person charges may apply and vary depending on property policy. Government-issued photo identification and a credit card, debit card, or cash deposit may be required at check-in for incidental charges. Special requests are subject to availability upon check-in and may incur additional charges; special requests cannot be guaranteed. Be prepared: check the latest COVID-19 travel requirements and measures in place for this destination before you travel."));

            document.add(new Paragraph("Fees").setBold().setFontSize(12));
            document.add(new Paragraph("Optional: Rollaway bed fee: INR 300.0 per night. The above list may not be comprehensive. Fees and deposits may not include tax and are subject to change."));
            SolidLine line3 = new SolidLine(0.5f); // Thickness reduced to 0.5
            LineSeparator separator3 = new LineSeparator(line3);
            document.add(separator3);

            // General Terms & Conditions
            document.add(new Paragraph("General Terms & Conditions").setBold().setFontSize(12));
            String[] terms = {
                    "1.Each country/state may have its own set of COVID-19 guidelines and restrictions. Please check with the hotel or visit the country's/state's website for the same.",
                    "2.The booking is non-transferable and valid only for the mentioned dates.",
                    "3.Modifications to the booking are subject to availability and additional charges.",
                    "4.Guests must present a valid government-issued ID at check-in.",
                    "5.The hotel is not responsible for any loss, damage, or theft of personal belongings.",
                    "6.Guests are required to follow hotel policies, including noise regulations and safety guidelines.",
                    "7.The hotel reserves the right to refuse service to any guest behaving inappropriately.",
                    "8.Early check-in and late check-out are subject to availability and additional charges.",
                    "9.Pets are not allowed unless specified in the booking.",
                    "10.Smoking is strictly prohibited in non-smoking rooms. Violation may result in penalties.",
                    "11.The hotel reserves the right to cancel a booking due to unforeseen circumstances, with a full refund provided.",
                    "12.Any damages caused to hotel property by guests will be charged accordingly.",
                    "13.Complimentary services such as Wi-Fi and breakfast are provided based on the booking package.",
                    "14.Visitors are allowed in guest rooms only with prior approval from the hotel management.",
                    "15.The hotel shall not be held liable for any unforeseen events, including natural disasters and emergencies.",
                    "16.Disputes arising from this booking will be governed by local laws and settled in the respective jurisdiction."
            };

            for (String term : terms) {
                document.add(new Paragraph("• " + term));
            }

            document.add(new Paragraph("FARE SUMMARY")
                     .setFontSize(12)
                    .setMarginBottom(10));

            // Create a table with two columns
            Table table1 = new Table(new float[]{3, 2}); // 3:2 column width ratio
            table1.setWidth(UnitValue.createPercentValue(100));

            // Add fare details
            addFareRow(table1, "Base Fare", "₹1,061.79");
            addFareRow(table1, "Taxes and Fees", "₹11.80");
            addFareRow(table1, "Total Amount Payable", "₹1,073.59", true);

            // Add table to document
            document.add(table1);



            // Close Document
            document.close();
            System.out.println("✅ PDF Created Successfully: Voucher_Details.pdf");

        } catch (FileNotFoundException e) {
            System.err.println(" Error: " + e.getMessage());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
