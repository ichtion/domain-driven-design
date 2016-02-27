package com.github.ichtion.wellcutaggregates.infrastructure

import com.github.ichtion.strongtypes.ProductId
import com.github.ichtion.wellcutaggregates.domain.product.Product
import com.github.ichtion.wellcutaggregates.domain.product.ProductRepository

class ProductRepositoryImpl implements ProductRepository {
    @Override
    Product getProduct(ProductId id) {
        return null
    }
}
