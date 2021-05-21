package handong.capstone2019s.salesb.domain.model;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SellerProduct implements Serializable {
	private static final long serialVersionUID = 2L;
	 
    private String productCode;
 
    private String productModel;
    
    private String productBodyStyle;

    private String productYear;
    
    private String productMonth;

    private String productPrice;

    private String productMake;

    private String productMileage;
    
    private String productEngine;

    private String productFuel;
    
    private String productTransmission;
    
    private String productColor;
	    
    public SellerProduct(String productCode) {
        this.productCode = productCode;
    }    
}
