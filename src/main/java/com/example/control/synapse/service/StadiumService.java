package com.example.control.synapse.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.control.synapse.dto.request.DeleteCredentialsDto;
import com.example.control.synapse.dto.response.StadiumResponseDto;
import com.example.control.synapse.models.Stadium;
import com.example.control.synapse.models.User;
import com.example.control.synapse.repository.StadiumRepository;
import com.example.control.synapse.repository.UserRepository;

@Service
public class StadiumService {

    private final StadiumRepository stadiumRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public StadiumService(StadiumRepository stadiumRepository, UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        this.stadiumRepository = stadiumRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Map<String, String> uploadStadium(String city, String state, String country, Integer capacity, String name,
            String adminEmail) {
        Stadium stadium = new Stadium();
        stadium.setCity(city);
        stadium.setState(state);
        stadium.setCountry(country);
        stadium.setCapacity(capacity);
        stadium.setName(name);
        stadium.setAdminEmail(adminEmail);

        stadiumRepository.save(stadium);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Stadium uploaded successfully!");
        return response;
    }

    public StadiumResponseDto getStadiumById(Long stadiumId) {
        Stadium stadium = stadiumRepository.findById(stadiumId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Stadium not found with id " + stadiumId));

        return convertToResponseDto(stadium);
    }

    public List<StadiumResponseDto> getAllStadiums() {
        List<Stadium> stadiums = stadiumRepository.findAll();
        List<StadiumResponseDto> dtoList = new ArrayList<>();

        for (Stadium stadium : stadiums) {
            dtoList.add(convertToResponseDto(stadium));
        }

        return dtoList;
    }

    public Map<String, String> updateStadium(Long stadiumId, String city, String state, String country,
            Integer capacity, String name, String adminEmail) {
        Stadium stadium = stadiumRepository.findById(stadiumId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Stadium not found with id " + stadiumId));

        if (city != null)
            stadium.setCity(city);
        if (state != null)
            stadium.setState(state);
        if (country != null)
            stadium.setCountry(country);
        if (capacity != null)
            stadium.setCapacity(capacity);
        if (name != null)
            stadium.setName(name);
        if (adminEmail != null)
            stadium.setAdminEmail(adminEmail);

        stadiumRepository.save(stadium);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Stadium updated successfully!");
        return response;
    }

    public Map<String, String> deleteStadium(Long stadiumId, DeleteCredentialsDto deleteCredentialsDto) {
        Long userId = deleteCredentialsDto.getUserId();
        String password = deleteCredentialsDto.getPassword();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No such user exists with id " + userId));

        Stadium stadium = stadiumRepository.findById(stadiumId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No such stadium exists with id " + stadiumId));

        if (passwordEncoder.matches(password, user.getPassword())) {
            stadiumRepository.delete(stadium);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Stadium successfully deleted with Stadium Id " + stadium.getId());
            return response;
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Password did not match");
        }
    }

    private StadiumResponseDto convertToResponseDto(Stadium stadium) {
        StadiumResponseDto dto = new StadiumResponseDto();
        dto.setId(stadium.getId());
        dto.setName(stadium.getName());
        dto.setCity(stadium.getCity());
        dto.setState(stadium.getState());
        dto.setCountry(stadium.getCountry());
        dto.setCapacity(stadium.getCapacity());
        dto.setAdminEmail(stadium.getAdminEmail());
        return dto;
    }
}