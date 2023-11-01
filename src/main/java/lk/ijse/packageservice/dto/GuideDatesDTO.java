package lk.ijse.packageservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
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

    @NotNull(message = "Start date cannot be null !")
    @NotEmpty(message = "Start date cannot be empty !")
    @Past(message = "Start date shouldn 't be before current date")
    private Date startDate;

    @NotNull(message = "End date cannot be null !")
    @NotEmpty(message = "End date cannot be empty !")
    private Date endDate;

}
