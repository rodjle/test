package org.salesanalysis.model;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Item {
    private Long id;
    private BigDecimal quantity;
    private BigDecimal price;

}
