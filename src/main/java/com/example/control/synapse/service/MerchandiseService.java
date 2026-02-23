package com.example.control.synapse.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.example.control.synapse.dto.request.DeleteCredentialsDto;
import com.example.control.synapse.dto.response.MerchandiseResponseDto;

import com.example.control.synapse.models.Merchandise;
import com.example.control.synapse.models.Stadium;
import com.example.control.synapse.models.User;
import com.example.control.synapse.repository.MerchandiseRepository;
import com.example.control.synapse.repository.StadiumRepository;
import com.example.control.synapse.repository.UserRepository;
import com.example.control.synapse.service.interfaces.IMerchandiseService;

@Service
public class MerchandiseService implements IMerchandiseService {

    private final MerchandiseRepository merchandiseRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final StadiumRepository stadiumRepository;

    public MerchandiseService(MerchandiseRepository merchandiseRepository, UserRepository userRepository,
            PasswordEncoder passwordEncoder, StadiumRepository stadiumRepository) {
        this.merchandiseRepository = merchandiseRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.stadiumRepository = stadiumRepository;
    }

    private MerchandiseResponseDto convertToDto(Merchandise merchandise) {
        MerchandiseResponseDto dto = new MerchandiseResponseDto();
        dto.setId(merchandise.getId());
        dto.setName(merchandise.getName());
        dto.setDescription(merchandise.getDescription());
        dto.setPrice(merchandise.getPrice());
        dto.setRating(merchandise.getRating());
        dto.setStadiumId(merchandise.getStadium() != null ? merchandise.getStadium().getId() : null);
        dto.setType(merchandise.getType());
        dto.setSize(merchandise.getSize());
        dto.setStock(merchandise.getStock());
        return dto;
    }

    public MerchandiseResponseDto getMerchandiseById(Long id) {
        Merchandise merchandise = merchandiseRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Merchandise not found with id " + id));
        return convertToDto(merchandise);
    }

    public List<MerchandiseResponseDto> getAllMerchandise() {
        return merchandiseRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<MerchandiseResponseDto> getMerchandiseByStadiumId(Long stadiumId) {
        return merchandiseRepository.findByStadium_Id(stadiumId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public Map<String, String> uploadMerchandise(String name, String description, double price, double rating,
            Long stadiumId, String size, String type, Integer stock) {
        Merchandise merchandise = new Merchandise();
        merchandise.setName(name);
        merchandise.setDescription(description);
        merchandise.setPrice(price);
        merchandise.setRating(rating);
        merchandise.setSize(size);
        merchandise.setType(type);
        merchandise.setStock(stock);

        Stadium stadium = stadiumRepository.findById(stadiumId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Stadium not found with id " + stadiumId));

        merchandise.setStadium(stadium);
        merchandiseRepository.save(merchandise);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Merchandise uploaded successfully!");
        return response;
    }

    @Transactional
    public Map<String, String> updateMerchandise(Long merchandiseId, String name, String description, Double price,
            Double rating, Long stadiumId, String type, String size, Integer stock) {
        Merchandise merchandise = merchandiseRepository.findById(merchandiseId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Merchandise not found with id " + merchandiseId));

        if (name != null)
            merchandise.setName(name);
        if (description != null)
            merchandise.setDescription(description);
        if (price != null)
            merchandise.setPrice(price);
        if (rating != null)
            merchandise.setRating(rating);
        if (type != null)
            merchandise.setType(type);
        if (size != null)
            merchandise.setSize(size);
        if (stock != null)
            merchandise.setStock(stock);

        if (stadiumId != null) {
            Stadium stadium = stadiumRepository.findById(stadiumId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "Stadium not found with id " + stadiumId));
            merchandise.setStadium(stadium);
        }

        merchandiseRepository.save(merchandise);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Merchandise updated successfully!");
        return response;
    }

    @Transactional
    public Map<String, String> deleteMerchandise(Long merchandiseId, DeleteCredentialsDto deleteCredentialsDto) {
        Long userId = deleteCredentialsDto.getUserId();
        String password = deleteCredentialsDto.getPassword();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No such user exists with id " + userId));

        Merchandise merchandise = merchandiseRepository.findById(merchandiseId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No such merchandise exists with id " + merchandiseId));

        if (passwordEncoder.matches(password, user.getPassword())) {
            merchandiseRepository.delete(merchandise);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Merchandise successfully deleted with Merchandise Id " + merchandise.getId());
            return response;
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Password did not match");
        }
    }
}
