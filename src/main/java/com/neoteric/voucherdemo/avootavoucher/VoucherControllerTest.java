package com.neoteric.voucherdemo.avootavoucher;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/vouchers")
public class VoucherControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(VoucherControllerTest.class);
    private final VoucherService voucherService;

    public VoucherControllerTest(VoucherService voucherService) {
        this.voucherService = voucherService;
    }

    @PostMapping("/generate")
    public ResponseEntity<Resource> generateVoucher(@RequestBody VoucherDetails voucherRequest) {
        try {
            logger.info("Received request to generate voucher for booking ID: {}", voucherRequest.getBookingId());

            byte[] pdfData = voucherService.generateVoucherPDF(voucherRequest);
            ByteArrayResource resource = new ByteArrayResource(pdfData);

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Voucher_Details.pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(pdfData.length)
                    .contentType(org.springframework.http.MediaType.APPLICATION_PDF)
                    .body(resource);
        } catch (Exception e) {
            logger.error("Error generating voucher: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
