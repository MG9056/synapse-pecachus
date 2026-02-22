package com.example.control.synapse.service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;


import com.example.control.synapse.models.Seat;
import com.example.control.synapse.models.Stadium;

import com.example.control.synapse.models.Merchandise;
import com.example.control.synapse.models.MerchandiseOrder;

import com.example.control.synapse.dto.response.MerchandiseOrderResponseDto;
import com.example.control.synapse.models.Event;
import com.example.control.synapse.models.EventMerchandise;
import com.example.control.synapse.models.User;
import com.example.control.synapse.repository.*;

@Service
public class MerchandiseOrderService {

    private final SeatRepository seatRepository;
    private final EventMerchandiseRepository eventMerchandiseRepository;
    private final MerchandiseOrderRepository merchandiseOrderRepository;
    private final UserRepository userRepository;
    private final MerchandiseRepository merchandiseRepository;
    private final StadiumRepository stadiumRepository;
    private final EventRepository eventRepository;
    


    public MerchandiseOrderService(EventMerchandiseRepository eventMerchandiseRepository, MerchandiseOrderRepository merchandiseOrderRepository, UserRepository userRepository, MerchandiseRepository merchandiseRepository, SeatRepository seatRepository, StadiumRepository stadiumRepository, EventRepository eventRepository)
    {this.eventMerchandiseRepository=eventMerchandiseRepository;
        this.merchandiseOrderRepository=merchandiseOrderRepository;
        this.userRepository=userRepository;
        this.merchandiseRepository= merchandiseRepository;
        this.seatRepository = seatRepository;
        this.stadiumRepository= stadiumRepository;
        this.eventRepository= eventRepository;
    
        


    }

    public Map<String,String> bookMerchandiseOrder(List<Long>merchandiseIdlist, Long userId,float price, Long seatId, Long stadiumId,Long eventId, LocalDateTime orderTime)
    {int size= merchandiseIdlist.size();

         User user = (User) userRepository.findById(userId)
         .orElseThrow(() -> new RuntimeException("User not found"));
        

        Seat seat= seatRepository.findById(seatId)
        .orElseThrow(() -> new RuntimeException("Seat not found"));

        Stadium stadium= stadiumRepository.findById(stadiumId)
        .orElseThrow(() -> new RuntimeException("Stadium not found"));

        Event event= eventRepository.findById(stadiumId)
        .orElseThrow(() -> new RuntimeException("Event not found"));






        MerchandiseOrder merchandiseOrder = new MerchandiseOrder();
        merchandiseOrder.setPrice(price);
        merchandiseOrder.setUser(user);
        merchandiseOrder.setSeat(seat);
        merchandiseOrder.setStadium(stadium);
        merchandiseOrder.setEvent(event);
        merchandiseOrder.setOrderTime(orderTime);
        merchandiseOrderRepository.save(merchandiseOrder);
        

        for(int i=0; i<size; i++)
        {Merchandise bookedMerchandise= merchandiseRepository.findById(merchandiseIdlist.get(i))
            .orElseThrow(() -> new RuntimeException("Merchandise not found"));

            EventMerchandise eventMerchandise= new EventMerchandise();

            eventMerchandise.setName(bookedMerchandise.getName());
            eventMerchandise.setDescription(bookedMerchandise.getDescription());
            eventMerchandise.setPrice(bookedMerchandise.getPrice());
            eventMerchandise.setRating(bookedMerchandise.getRating());
            eventMerchandise.setStadium(stadium);
            eventMerchandise.setEvent(event);

            eventMerchandise.setMerchandiseOrder(merchandiseOrder);
            eventMerchandiseRepository.save(eventMerchandise);
        










        }

  Map<String,String> response = new HashMap<>();
        response.put("message", "Merchandise Order booked!");
        return response;

        


    }




    public List<MerchandiseOrderResponseDto> getMerchandiseOrderByUserId(Long userId)
    {List<MerchandiseOrder> orders= merchandiseOrderRepository.findByUser_Id((userId));

        List<MerchandiseOrderResponseDto> dtoList= new ArrayList<>();

        for(MerchandiseOrder order:orders)
        {MerchandiseOrderResponseDto orderResponseDto= new MerchandiseOrderResponseDto();

             orderResponseDto.setId(order.getId());
            orderResponseDto.setPrice(order.getPrice());
            orderResponseDto.setSeatId(order.getSeat().getId());
            orderResponseDto.setUserId(order.getUser().getId());
            orderResponseDto.setStadiumId(order.getStadium().getId());
        


        dtoList.add(orderResponseDto);



        }

        return dtoList;


    }

    public List<MerchandiseOrderResponseDto> getAllMerchandiseOrders()
    {List<MerchandiseOrder> orders= merchandiseOrderRepository.findAll();

        List<MerchandiseOrderResponseDto> dtoList= new ArrayList<>();

        for(MerchandiseOrder order:orders)
        {MerchandiseOrderResponseDto orderResponseDto= new MerchandiseOrderResponseDto();

             orderResponseDto.setId(order.getId());
            orderResponseDto.setPrice(order.getPrice());
            orderResponseDto.setSeatId(order.getSeat().getId());
            orderResponseDto.setUserId(order.getUser().getId());
            orderResponseDto.setStadiumId(order.getStadium().getId());
        


        dtoList.add(orderResponseDto);



        }

        return dtoList;


    }



    public List<MerchandiseOrderResponseDto> getMerchandiseOrderByStadiumId(Long userId)
    {List<MerchandiseOrder> orders= merchandiseOrderRepository.findByStadium_Id((userId));

        List<MerchandiseOrderResponseDto> dtoList= new ArrayList<>();

        for(MerchandiseOrder order:orders)
        {MerchandiseOrderResponseDto orderResponseDto= new MerchandiseOrderResponseDto();


            orderResponseDto.setId(order.getId());
            orderResponseDto.setPrice(order.getPrice());
            orderResponseDto.setSeatId(order.getSeat().getId());
            orderResponseDto.setUserId(order.getUser().getId());
            orderResponseDto.setStadiumId(order.getStadium().getId());
        


        dtoList.add(orderResponseDto);



        }

        return dtoList;


    }

    public MerchandiseOrderResponseDto getMerchandiseOrderById(Long id)
    {MerchandiseOrder order= merchandiseOrderRepository.findById(id).orElseThrow();
        MerchandiseOrderResponseDto orderResponseDto= new MerchandiseOrderResponseDto();


            orderResponseDto.setId(order.getId());
            orderResponseDto.setPrice(order.getPrice());
            orderResponseDto.setSeatId(order.getSeat().getId());
            orderResponseDto.setUserId(order.getUser().getId());
            orderResponseDto.setStadiumId(order.getStadium().getId());

            return orderResponseDto;






    }




    
}
