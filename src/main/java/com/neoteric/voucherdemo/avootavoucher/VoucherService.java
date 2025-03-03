package com.neoteric.voucherdemo.avootavoucher;
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
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class VoucherService {

    public byte[] generateVoucherPDF(VoucherDetails details) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(outputStream);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);
        document.setMargins(20, 30, 10, 30);
        Table headerTable = new Table(UnitValue.createPercentArray(new float[]{1, 3})).useAllAvailableWidth();
        headerTable.setBorder(Border.NO_BORDER);

        try {
            // Load the logo
            ImageData logo = ImageDataFactory.create("src/main/resources/image/img_1.png");
            Image logoImage = new Image(logo)
                    .setWidth(20) // Adjust width
                    .setHeight(20) // Adjust height
                    .setAutoScale(false); // Prevent automatic scaling

            Cell logoCell = new Cell()
                    .add(logoImage)
                    .setBorder(Border.NO_BORDER)
                    .setVerticalAlignment(VerticalAlignment.MIDDLE)
                    .setPadding(0)
                    .setMargin(0);

            headerTable.addCell(logoCell);
        } catch (Exception e) {
            headerTable.addCell(new Cell()
                    .add(new Paragraph("✔").setFontSize(16).setBold().setFontColor(ColorConstants.GREEN))
                    .setBorder(Border.NO_BORDER)
                    .setVerticalAlignment(VerticalAlignment.MIDDLE)
                    .setPadding(0)
                    .setMargin(0));
        }

// Add "Vouchered" text tightly next to the logo
        Cell voucheredCell = new Cell()
                .add(new Paragraph("Booking ID: " +details.getBookingId()))
                .add(new Paragraph("Vouchered")
                        .setFontSize(16)
                        .setBold()
                        .setFontColor(ColorConstants.GREEN))
                .setBorder(Border.NO_BORDER)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .setPadding(0)
                .setMargin(0);

        headerTable.addCell(voucheredCell);
        document.add(headerTable);
        document.add(new LineSeparator(new SolidLine(1))); // Thin separator line


        SolidLine line = new SolidLine(0.5f); // Thickness reduced to 0.5
        LineSeparator separator = new LineSeparator(line);
        document.add(separator);
        document.add(new Paragraph(details.getHotelName()).setBold().setFontSize(16));
        document.add(new Paragraph(details.getAddress()));
        document.add(new Paragraph("Phone: " + details.getPhoneNumber()));
        document.add(new Paragraph("Last Cancellation Date: " + details.getLastCancellationDate()).setBold().setFontSize(14).setFontColor(new DeviceRgb(0, 0, 128)));

        SolidLine line1 = new SolidLine(0.5f); // Thickness reduced to 0.5
        LineSeparator separator1= new LineSeparator(line1);
        document.add(separator1);
        float[] columnWidths = {2, 2, 2, 2};
        Table table = new Table(UnitValue.createPercentArray(columnWidths)).useAllAvailableWidth();
    BookingDetails booking = new BookingDetails();
        if (booking != null) {
            table.addCell(new Cell().add(new Paragraph("Check-In:")).setBold().setFontColor(new DeviceRgb(0, 0, 128)));
            table.addCell(new Cell().add(new Paragraph(booking.getCheckIn() != null ? booking.getCheckIn() : "N/A")).setTextAlignment(TextAlignment.LEFT));

            table.addCell(new Cell().add(new Paragraph("Check-Out:")).setBold().setFontColor(new DeviceRgb(0, 0, 128)));
            table.addCell(new Cell().add(new Paragraph(booking.getCheckOut() != null ? booking.getCheckOut() : "N/A")).setTextAlignment(TextAlignment.LEFT));

            table.addCell(new Cell().add(new Paragraph("Total Rooms:")).setBold().setFontColor(new DeviceRgb(0, 0, 128)));
            Integer totalRooms = booking.getTotalRooms(); // Fetch the total rooms
            String totalRoomsText = (totalRooms != null) ? String.valueOf(totalRooms) : "0"; // Handle null safely

            table.addCell(
                    new Cell()
                            .add(new Paragraph(totalRoomsText))
                            .setTextAlignment(TextAlignment.LEFT)
            );


            table.addCell(new Cell().add(new Paragraph("Total Stay:")).setBold().setFontColor(new DeviceRgb(0, 0, 128)));
            table.addCell(new Cell().add(new Paragraph((booking.getTotalStay() != null ? booking.getTotalStay() : "0") + " Night(s)")).setTextAlignment(TextAlignment.LEFT));

            // Adding table to document
            document.add(table);
        } else {
            document.add(new Paragraph("Booking details are not available.").setBold().setFontColor(ColorConstants.RED));
        }


        SolidLine line3 = new SolidLine(0.5f); // Thickness reduced to 0.5
        LineSeparator separator3 = new LineSeparator(line3);
        document.add(separator3);

        // Room Details
        RoomDetails room = details.getRoomDetails();
        document.add(new Paragraph("Room Type: " + room.getRoomType()));
        document.add(new Paragraph("Bed Type: " + room.getBedType()));
        SolidLine line4 = new SolidLine(0.5f); // Thickness reduced to 0.5
        LineSeparator separator4 = new LineSeparator(line4);
        document.add(separator4);

        // Contact Details
        ContactDetails contact = details.getContactDetails();
        document.add(new Paragraph("Guest Name: " + contact.getGuestName()));
        document.add(new Paragraph("Email: " + contact.getEmail()));
        document.add(new Paragraph("Mobile: " + contact.getMobile()));

        SolidLine line5 = new SolidLine(0.5f); // Thickness reduced to 0.5
        LineSeparator separator5 = new LineSeparator(line5);
        document.add(separator5);

        Table cancelTable = new Table(UnitValue.createPercentArray(new float[]{3, 3, 4})).useAllAvailableWidth();
        cancelTable.addCell(new Cell().add(new Paragraph("Cancellation on or After")).setBold().setTextAlignment(TextAlignment.CENTER));
        cancelTable.addCell(new Cell().add(new Paragraph("Cancellation on or Before")).setBold().setTextAlignment(TextAlignment.CENTER));
        cancelTable.addCell(new Cell().add(new Paragraph("Cancellation Charges/Comments")).setBold().setTextAlignment(TextAlignment.CENTER));

        CancellationPolicy policy = booking.getPolicy();

        if (policy != null) {
            cancelTable.addCell(new Cell().add(new Paragraph(policy.getCancelAfter() != null ? policy.getCancelAfter() : "N/A")).setTextAlignment(TextAlignment.CENTER));
            cancelTable.addCell(new Cell().add(new Paragraph(policy.getCancelBefore() != null ? policy.getCancelBefore() : "N/A")).setTextAlignment(TextAlignment.CENTER));
            cancelTable.addCell(new Cell().add(new Paragraph(policy.getCancellationCharges() != null ? policy.getCancellationCharges() : "N/A")).setTextAlignment(TextAlignment.CENTER));
            if (policy.getPolicies() != null && !policy.getPolicies().isEmpty()) {
                for (String policyText : policy.getPolicies()) {
                    cancelTable.addCell(new Cell(1, 3).add(new Paragraph("* " + policyText)));
                }
            }
        } else {
            cancelTable.addCell(new Cell(1, 3).add(new Paragraph("No cancellation policy available")).setBold().setTextAlignment(TextAlignment.CENTER));
        }
        document.add(cancelTable);

        SolidLine line6 = new SolidLine(0.5f); // Thickness reduced to 0.5
        LineSeparator separator6= new LineSeparator(line6);
        document.add(separator6);


        // Booking Notes
        document.add(new Paragraph("Booking Notes").setBold().setFontSize(12));
        for (String note : details.getBookingNotes()) {
            document.add(new Paragraph("• " + note));
        }

        // General Terms
        document.add(new Paragraph("General Terms & Conditions").setBold().setFontSize(12));
        for (String term : details.getGeneralTerms()) {
            document.add(new Paragraph("• " + term));
        }

        // Fare Summary
        FareSummary fare = details.getFareSummary();
        Table fareTable = new Table(new float[]{3, 2}).setWidth(UnitValue.createPercentValue(100));
        addFareRow(fareTable, "Base Fare", "₹" + fare.getBaseFare());
        addFareRow(fareTable, "Taxes and Fees", "₹" + fare.getTaxesAndFees());
        addFareRow(fareTable, "Total Amount Payable", "₹" + fare.getTotalAmountPayable(), true);
        document.add(fareTable);

        // Close Document
        document.close();
        return outputStream.toByteArray();
    }

    private static void addFareRow(Table table, String leftText, String rightText) {
        addFareRow(table, leftText, rightText, false);
    }

    private static void addFareRow(Table table, String leftText, String rightText, boolean isBold) {
        Cell leftCell = new Cell().add(new Paragraph(leftText).setBold()).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
        Cell rightCell = new Cell().add(new Paragraph(rightText).setBold()).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT);
        table.addCell(leftCell);
        table.addCell(rightCell);
        table.addCell(new Cell(1, 2).add(new LineSeparator(new SolidLine())).setBorder(Border.NO_BORDER));
    }
}
