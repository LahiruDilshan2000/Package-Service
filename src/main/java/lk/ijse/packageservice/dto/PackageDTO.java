package lk.ijse.packageservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author Lahiru Dilshan
 * @created Sat 10:41 AM on 10/7/2023
 * @project nexttravel
 **/
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PackageDTO {

    private String packageId;

    private String packageCategory;

    private String userNic;

    private Integer vehicleId;

    private Integer hotelId;

    private String hotelName;

    private List<String> travelArea;

    private String contact;

    private String email;

    private Double packageValue;

    private Double paidValue;

    private Date startDate;

    private Date endDate;

    private Date bookedDate;

    private List<RoomDetailsDTO> roomDetailList;

    private Integer adultCount;

    private Integer childrenCount;

    private Integer headCount;

    private String withPetOrNo;

    private String guideId;

}
