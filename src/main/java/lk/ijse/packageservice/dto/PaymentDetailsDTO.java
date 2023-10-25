package lk.ijse.packageservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Lahiru Dilshan
 * @created Wed 1:47 PM on 10/25/2023
 * @project package-service
 **/
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentDetailsDTO {

    private Integer id;

    private String packageId;

    private String status;

    private Double packageValue;

    private Double paidValue;

    private byte[] paymentSlip;
}
