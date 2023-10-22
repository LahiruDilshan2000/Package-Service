package lk.ijse.packageservice.service.custom.impl;

import lk.ijse.packageservice.dto.GuideDTO;
import lk.ijse.packageservice.dto.GuideDatesDTO;
import lk.ijse.packageservice.dto.PackageDTO;
import lk.ijse.packageservice.entity.Package;
import lk.ijse.packageservice.exception.NotFoundException;
import lk.ijse.packageservice.feign.Guide;
import lk.ijse.packageservice.feign.Hotel;
import lk.ijse.packageservice.feign.User;
import lk.ijse.packageservice.feign.Vehicle;
import lk.ijse.packageservice.repository.PackageRepository;
import lk.ijse.packageservice.service.custom.PackageService;
import lk.ijse.packageservice.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Lahiru Dilshan
 * @created Sat 10:33 AM on 10/7/2023
 * @project nexttravel
 **/
@Service
@Transactional
@RequiredArgsConstructor
public class PackageServiceImpl implements PackageService {

    private final PackageRepository packageRepository;

    private final Vehicle vehicle;

    private final Hotel hotel;

    private final Guide guide;

    private final User user;

    private final ModelMapper modelMapper;

    @Override
    public String getNextPk() {

        Optional<Package> isExistId = packageRepository.getLastId();

        if (isExistId.isPresent()) {

            String[] split = isExistId.get().getPackageId().split("[-]");
            int lastDigits = Integer.parseInt(split[1]);
            lastDigits++;
            return String.format("MD-%08d", lastDigits);
        } else {
            return "MD-00000001";
        }
    }

    @Override
    public PackageDTO savePackage(PackageDTO packageDTO) {

        if (packageRepository.existsById(packageDTO.getPackageId()))
            throw new NotFoundException(packageDTO.getPackageId() + " Package already exist");

        return modelMapper
                .map(packageRepository.save(modelMapper.map(packageDTO, Package.class)), PackageDTO.class);
    }

    @Override
    public PackageDTO updatePackage(PackageDTO packageDTO) {

        if (!packageRepository.existsById(packageDTO.getPackageId()))
            throw new NotFoundException(packageDTO.getPackageId() + " Package doesn't exist");

        return modelMapper
                .map(packageRepository.save(modelMapper.map(packageDTO, Package.class)), PackageDTO.class);
    }

    @Override
    public void deletePackage(String packageId) {

        if (!packageRepository.existsById(packageId))
            throw new NotFoundException(packageId + " Package doesn't exist");

        packageRepository.deleteById(packageId);
    }

    @Override
    public List<PackageDTO> getPageablePackage(Integer page, Integer count) {

        PageRequest pageRequest = PageRequest.of(page, count);

        return modelMapper
                .map(packageRepository.getPackageHQLWithPageable(pageRequest),
                        new TypeToken<List<PackageDTO>>(){}.getType());
    }

    @Override
    public PackageDTO getById(String packageId) {

        if (!packageRepository.existsById(packageId))
            throw new NotFoundException(packageId + " Package doesn't exist");

        return modelMapper
                .map(packageRepository.findById(packageId).get(), PackageDTO.class);
    }

    @Override
    public ResponseUtil getVehicles(Integer page, Integer count, String category) {

        return vehicle.getVehicleWithCategory(page, count, category);
    }

    @Override
    public ResponseUtil getHotels(Integer page, Integer count, Integer category) {

        return hotel.getHotelWithCategory(page, count, category);
    }

    @Override
    public ResponseUtil getHotelById(Integer hotelId) {

        return hotel.getHotelById(hotelId);
    }

    @Override
    public ResponseUtil getVehicleById(Integer vehicleId) {

        return vehicle.getVehicleById(vehicleId);
    }

    @Override
    public ResponseUtil getAllUsers() {

        return user.getAll();
    }

    @Override
    public ResponseUtil getUserByNic(String nic) {

        return user.findByNic(nic);
    }

    @Override
    public ResponseUtil getGuideById(Integer guideId) {

        return guide.getGuideById(guideId);
    }

    @Override
    public List<GuideDTO> getFreeGuides(String startDate, String endDate) {

        List<GuideDTO> all = guide.getAll();
        List<GuideDTO> freeList = new ArrayList<>();

        all.forEach(guideDTO -> {
            if (!guideDTO.getDatesList().isEmpty()) {
                if (isFree(guideDTO.getDatesList(), startDate, endDate)) {
                    freeList.add(guideDTO);
                    System.out.println("added");
                }
            }
        });


        return freeList;
    }

    private boolean isFree(List<GuideDatesDTO> dateList, String startDate, String endDate) {

        boolean isFree = false;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            Date sdate = dateFormat.parse(startDate);
            Date edate = dateFormat.parse(endDate);

            dateList.forEach(guideDatesDTO -> {

                System.out.println((sdate.toInstant().isAfter(guideDatesDTO.getStartDate().toInstant()) &&
                         sdate.toInstant().isBefore(guideDatesDTO.getEndDate().toInstant()))
                        ||
                        (edate.toInstant().isAfter(guideDatesDTO.getStartDate().toInstant())
                                && edate.toInstant().isBefore(guideDatesDTO.getEndDate().toInstant())));


                //targetDate.isBefore(startDate) || targetDate.isAfter(endDate)

            });
            return true;

        } catch (
                ParseException e) {
            throw new RuntimeException(e);
        }
    }
}