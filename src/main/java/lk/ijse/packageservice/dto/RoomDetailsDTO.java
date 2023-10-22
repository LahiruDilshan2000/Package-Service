package lk.ijse.packageservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Lahiru Dilshan
 * @created Thu 10:52 AM on 10/19/2023
 * @project package-service
 **/

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoomDetailsDTO {

    private String RoomType;

    private Integer qty;

    private Double price;
}
