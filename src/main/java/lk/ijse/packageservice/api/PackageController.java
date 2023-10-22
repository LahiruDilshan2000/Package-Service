package lk.ijse.packageservice.api;

import lk.ijse.packageservice.dto.PackageDTO;
import lk.ijse.packageservice.service.custom.PackageService;
import lk.ijse.packageservice.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Lahiru Dilshan
 * @created Sat 10:38 AM on 10/7/2023
 * @project nexttravel
 **/
@RestController
@RequestMapping("/api/v1/package")
@CrossOrigin
@RequiredArgsConstructor
public class PackageController {

    private final PackageService packageService;

    @GetMapping(value = "/getVehicles", params = {"page", "count", "category"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseUtil getVehiclesByCategory(@RequestParam Integer page,
                                              @RequestParam Integer count,
                                              @RequestParam String category){

        return packageService.getVehicles(page, count, category);

    }

    @GetMapping(value = "/getHotels", params = {"page", "count", "category"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseUtil getHotelsByCategory(@RequestParam Integer page,
                                              @RequestParam Integer count,
                                              @RequestParam Integer category){

        return packageService.getHotels(page, count, category);

    }

    @GetMapping(value = "/getHotelById", params = {"hotelId"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseUtil getHotelsById(@RequestParam Integer hotelId){

        return packageService.getHotelById(hotelId);

    }

    @GetMapping(value = "/getVehicleById", params = {"vehicleId"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseUtil getVehicleById(@RequestParam Integer vehicleId){

        return packageService.getVehicleById(vehicleId);

    }

    @GetMapping(value = "/getGuideById", params = {"guideId"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseUtil getGuideById(@RequestParam Integer guideId){

        return packageService.getGuideById(guideId);

    }

    @GetMapping(value = "/getUsers", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseUtil getAllUsers(){

        return packageService.getAllUsers();

    }

    @GetMapping(value = "/getUserByNic", params = {"nic"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseUtil getUserByNic(@RequestParam String nic){

        return packageService.getUserByNic(nic);

    }

    @GetMapping(value = "/getNextPk")
    public ResponseUtil getNextId(){

        return ResponseUtil
                .builder()
                .code(200)
                .data(packageService.getNextPk())
                .message("Next Id get successful")
                .build();
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseUtil savePackage(@RequestBody PackageDTO packageDTO){

        return ResponseUtil
                .builder()
                .code(200)
                .data(packageService.savePackage(packageDTO))
                .message("Package save successful")
                .build();
    }

    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseUtil updatePackage(@RequestBody PackageDTO packageDTO){

        return ResponseUtil
                .builder()
                .code(200)
                .data(packageService.updatePackage(packageDTO))
                .message("Package update successful")
                .build();
    }

    @DeleteMapping(params = {"packageId"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseUtil updatePackage(@RequestParam String packageId){

        packageService.deletePackage(packageId);
        return ResponseUtil
                .builder()
                .code(200)
                .message("Package delete successful")
                .build();
    }

    @GetMapping(params = {"packageId"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseUtil getById(@RequestParam String packageId){

        return ResponseUtil
                .builder()
                .code(200)
                .data(packageService.getById(packageId))
                .message("Package getting successful")
                .build();
    }

    @GetMapping(value = "/getAll", params = {"page", "count"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseUtil getPackagePageable(@RequestParam Integer page,@RequestParam Integer count){

        return ResponseUtil
                .builder()
                .code(200)
                .message("Getting pageable package successfully !")
                .data(packageService.getPageablePackage(page, count))
                .build();
    }

    @GetMapping(value = "/getFreeGuide", params = {"startDate", "endDate"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseUtil GetFreeGuide(@RequestParam String startDate, @RequestParam String endDate){

        return ResponseUtil
                .builder()
                .code(200)
                .data(packageService.getFreeGuides(startDate, endDate))
                .message("Package getting all successful")
                .build();
    }
}
