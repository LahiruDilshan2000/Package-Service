package lk.ijse.packageservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Lahiru Dilshan
 * @created Thu 12:14 PM on 10/19/2023
 * @project guide-service
 **/

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GuideDatesDTO {

    private Date startDate;

    private Date endDate;

}
