package com.example.control.synapse.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeedbackPageResponseDto {

    // ✅ Fixed: was named 'content' but getter was getData() — mismatch caused
    //    Jackson to output both 'content' and 'data' keys in JSON response.
    //    Renamed field to 'data' to match the intended getter name.
    private List<FeedbackResponseDto> data;

    private int page;
    private int size;
    private Long totalElements;
    private int totalPages;
    private Boolean last;
}
