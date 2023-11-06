package com.spzx.commonutil.entity.product;

import com.spzx.commonutil.entity.base.BaseEntity;
import lombok.Data;

@Data
public class ProductDetails extends BaseEntity {

    private Long productId;
    private String imageUrls;

}