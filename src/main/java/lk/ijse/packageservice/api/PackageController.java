package lk.ijse.packageservice.api;

import lk.ijse.packageservice.dto.PackageDTO;
import lk.ijse.packageservice.entity.Role;
import lk.ijse.packageservice.exception.UnauthorizedException;
import lk.ijse.packageservice.service.custom.PackageService;
import lk.ijse.packageservice.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
                                              @RequestParam String category,
                                              @RequestHeader("X-ROLE") Role role) {

        if (role.equals(Role.ADMIN_PACKAGE) || role.equals(Role.USER))
            return packageService.getVehicles(page, count, category);

        throw new UnauthorizedException("Un authorized access to application");

    }

    @GetMapping(value = "/getHotels", params = {"page", "count", "category"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseUtil getHotelsByCategory(@RequestParam Integer page,
                                            @RequestParam Integer count,
                                            @RequestParam Integer category,
                                            @RequestHeader("X-ROLE") Role role) {

        if (role.equals(Role.ADMIN_PACKAGE) || role.equals(Role.USER))
            return packageService.getHotels(page, count, category);

        throw new UnauthorizedException("Un authorized access to application");
    }

    @GetMapping(value = "/getHotelById", params = {"hotelId"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseUtil getHotelsById(@RequestParam Integer hotelId, @RequestHeader("X-ROLE") Role role) {

        if (role.equals(Role.ADMIN_PACKAGE) || role.equals(Role.USER))
            return packageService.getHotelById(hotelId);

        throw new UnauthorizedException("Un authorized access to application");
    }

    @GetMapping(value = "/getVehicleById", params = {"vehicleId"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseUtil getVehicleById(@RequestParam Integer vehicleId, @RequestHeader("X-ROLE") Role role) {

        if (role.equals(Role.ADMIN_PACKAGE) || role.equals(Role.USER))
            return packageService.getVehicleById(vehicleId);

        throw new UnauthorizedException("Un authorized access to application");
    }

    @GetMapping(value = "/getGuideById", params = {"guideId"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseUtil getGuideById(@RequestParam Integer guideId, @RequestHeader("X-ROLE") Role role) {

        if (role.equals(Role.ADMIN_PACKAGE) || role.equals(Role.USER))
            return packageService.getGuideById(guideId);

        throw new UnauthorizedException("Un authorized access to application");
    }

    @GetMapping(value = "/getUsers", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseUtil getAllUsers(@RequestHeader("X-ROLE") Role role) {

        if (!role.equals(Role.ADMIN_PACKAGE))
            throw new UnauthorizedException("Un authorized access to application");

        return packageService.getAllUsers();

    }

    @GetMapping(value = "/getUserByNic", params = {"nic"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseUtil getUserByNic(@RequestParam String nic, @RequestHeader("X-ROLE") Role role) {

        if (!role.equals(Role.ADMIN_PACKAGE))
            throw new UnauthorizedException("Un authorized access to application");

        return packageService.getUserByNic(nic);

    }

    @GetMapping(value = "/getNextPk")
    public ResponseUtil getNextId(@RequestHeader("X-ROLE") Role role) {

        if (role.equals(Role.ADMIN_PACKAGE) || role.equals(Role.USER))
            return ResponseUtil
                    .builder()
                    .code(200)
                    .data(packageService.getNextPk())
                    .message("Next Id get successful")
                    .build();

        throw new UnauthorizedException("Un authorized access to application");
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseUtil savePackage(@RequestBody PackageDTO packageDTO, @RequestHeader("X-ROLE") Role role) {

        if (role.equals(Role.ADMIN_PACKAGE) || role.equals(Role.USER))
            return ResponseUtil
                    .builder()
                    .code(200)
                    .data(packageService.savePackage(packageDTO))
                    .message("Package save successful")
                    .build();

        throw new UnauthorizedException("Un authorized access to application");
    }

    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseUtil updatePackage(@RequestBody PackageDTO packageDTO, @RequestHeader("X-ROLE") Role role) {

        if (!role.equals(Role.ADMIN_PACKAGE))
            throw new UnauthorizedException("Un authorized access to application");

        return ResponseUtil
                .builder()
                .code(200)
                .data(packageService.updatePackage(packageDTO))
                .message("Package update successful")
                .build();
    }

    @PutMapping(value = "/updateUserPackage", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseUtil updateUserPackage(@RequestBody PackageDTO packageDTO, @RequestHeader("X-ROLE") Role role) {

        if (!role.equals(Role.USER))
            throw new UnauthorizedException("Un authorized access to application");

        return ResponseUtil
                .builder()
                .code(200)
                .data(packageService.updateNotExpirePackage(packageDTO))
                .message("Package update successful")
                .build();
    }

    @DeleteMapping(params = {"packageId"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseUtil updatePackage(@RequestParam String packageId, @RequestHeader("X-ROLE") Role role) {

        if (!role.equals(Role.ADMIN_PACKAGE))
            throw new UnauthorizedException("Un authorized access to application");

        packageService.deletePackage(packageId);
        return ResponseUtil
                .builder()
                .code(200)
                .message("Package delete successful")
                .build();
    }

    @GetMapping(params = {"packageId"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseUtil getById(@RequestParam String packageId, @RequestHeader("X-ROLE") Role role) {

        if (!role.equals(Role.ADMIN_PACKAGE))
            throw new UnauthorizedException("Un authorized access to application");

        return ResponseUtil
                .builder()
                .code(200)
                .data(packageService.getById(packageId))
                .message("Package getting successful")
                .build();
    }

    @GetMapping(value = "/getAll", params = {"page", "count"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseUtil getPackagePageable(@RequestParam Integer page,
                                           @RequestParam Integer count,
                                           @RequestHeader("X-ROLE") Role role) {

        if (!role.equals(Role.ADMIN_PACKAGE))
            throw new UnauthorizedException("Un authorized access to application");

        return ResponseUtil
                .builder()
                .code(200)
                .message("Getting pageable package successfully !")
                .data(packageService.getPageablePackage(page, count))
                .build();
    }

    @GetMapping(value = "/getFreeGuide", params = {"startDate", "endDate"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseUtil GetFreeGuide(@RequestParam String startDate,
                                     @RequestParam String endDate,
                                     @RequestHeader("X-ROLE") Role role) {

        if (role.equals(Role.ADMIN_PACKAGE) || role.equals(Role.USER))
            return ResponseUtil
                    .builder()
                    .code(200)
                    .data(packageService.getFreeGuides(startDate, endDate))
                    .message("Package getting all successful")
                    .build();

        throw new UnauthorizedException("Un authorized access to application");
    }

    @GetMapping(value = "/getPackageByNic", params = {"page", "count", "nic"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseUtil getPackagesByUserNic(@RequestParam Integer page,
                                             @RequestParam Integer count,
                                             @RequestParam String nic,
                                             @RequestHeader("X-ROLE") Role role) {

        if (!role.equals(Role.USER))
            throw new UnauthorizedException("Un authorized access to application");

        return ResponseUtil
                .builder()
                .code(200)
                .data(packageService.getPackageByUserNic(page, count, nic))
                .message("User packages getting successful")
                .build();
    }

    @PutMapping(value = "/payment", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseUtil addPayment(@RequestPart("slip") MultipartFile file,
                                   @RequestPart("packageId") String packageId,
                                   @RequestHeader("X-ROLE") Role role) {

        if (!role.equals(Role.USER))
            throw new UnauthorizedException("Un authorized access to application");

        packageService.addPaymentSlip(packageId, file);
        return ResponseUtil
                .builder()
                .code(200)
                .message("Payment add successful")
                .build();
    }

    @PutMapping(value = "/confirm", params = "packageId", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseUtil confirmPayment(@RequestParam String packageId, @RequestHeader("X-ROLE") Role role) {

        if (!role.equals(Role.ADMIN_PACKAGE))
            throw new UnauthorizedException("Un authorized access to application");

        packageService.confirmPayment(packageId);
        return ResponseUtil
                .builder()
                .code(200)
                .message("Package payment confirm successful")
                .build();
    }

    @PutMapping(value = "/reject", params = "packageId", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseUtil rejectPayment(@RequestParam String packageId, @RequestHeader("X-ROLE") Role role) {

        if (!role.equals(Role.ADMIN_PACKAGE))
            throw new UnauthorizedException("Un authorized access to application");

        packageService.rejectPayment(packageId);
        return ResponseUtil
                .builder()
                .code(200)
                .message("Package payment rejected successful")
                .build();
    }

    @GetMapping(value = "/getPending", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseUtil getPendingAllPayment(@RequestHeader("X-ROLE") Role role) {


        if (!role.equals(Role.ADMIN_PACKAGE))
            throw new UnauthorizedException("Un authorized access to application");

        return ResponseUtil
                .builder()
                .code(200)
                .data(packageService.getPending())
                .message("All pending payment getting successful")
                .build();
    }

    @GetMapping(value = "/getPendingOne", params = "packageId", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseUtil getPendingPayment(@RequestParam String packageId, @RequestHeader("X-ROLE") Role role) {

        if (!role.equals(Role.ADMIN_PACKAGE))
            throw new UnauthorizedException("Un authorized access to application");

        return ResponseUtil
                .builder()
                .code(200)
                .data(packageService.getPendingPaymentByPackageId(packageId))
                .message("Selected payment getting successful")
                .build();
    }

    @GetMapping(value = "/getAllPackages", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<PackageDTO> getAllPackages() {

        return packageService.getAllPackages();
    }

    @GetMapping(value = "/search", params = {"text"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseUtil searchByText(@RequestParam String text, @RequestHeader("X-ROLE") Role role) {

        if (!role.equals(Role.ADMIN_PACKAGE))
            throw new UnauthorizedException("Un authorized access to application");

        return ResponseUtil
                .builder()
                .code(200)
                .message("Search package by text successfully !")
                .data(packageService.searchByText(text))
                .build();
    }
}
