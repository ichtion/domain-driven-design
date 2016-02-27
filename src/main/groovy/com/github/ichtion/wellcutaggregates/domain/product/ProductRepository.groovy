package com.github.ichtion.wellcutaggregates.domain.product

import com.github.ichtion.strongtypes.ProductId

interface ProductRepository {
    Product getProduct(ProductId id)
}