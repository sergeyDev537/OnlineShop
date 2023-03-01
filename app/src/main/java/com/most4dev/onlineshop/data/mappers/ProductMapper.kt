package com.most4dev.onlineshop.data.mappers

import com.most4dev.onlineshop.data.network.models.*
import com.most4dev.onlineshop.domain.entities.ItemProductEntity
import com.most4dev.onlineshop.domain.entities.ProductEntity
import com.most4dev.onlineshop.domain.entities.SaleProductEntity
import java.util.*

class ProductMapper {

    fun mapListLatestProductDtoToEntity(listLatestProductDto: LatestProductListDto) =
        listLatestProductDto.latest.map {
            mapLatestProductDtoToEntity(it)
        }

    private fun mapLatestProductDtoToEntity(latestProductDto: LatestProductDto): ProductEntity {
        return ProductEntity(
            id = UUID.randomUUID(),
            category = latestProductDto.category,
            name = latestProductDto.name,
            price = latestProductDto.price.toDouble(),
            image_urls = latestProductDto.image_url
        )
    }

    fun mapListSalesProductDtoToEntity(flashSaleListDto: FlashSaleListDto) =
        flashSaleListDto.flash_sale.map {
            mapSalesProductDtoToEntity(it)
        }

    private fun mapSalesProductDtoToEntity(flashSaleDto: FlashSaleDto): SaleProductEntity {
        return SaleProductEntity(
            category = flashSaleDto.category,
            discount = flashSaleDto.discount,
            image_url = flashSaleDto.image_url,
            name = flashSaleDto.name,
            price = flashSaleDto.price
        )
    }

    fun mapSaleProductEntityToProductEntity(saleProductEntity: SaleProductEntity): ProductEntity{
        return ProductEntity(
            id = UUID.randomUUID(),
            category = saleProductEntity.category,
            name = saleProductEntity.name,
            price = saleProductEntity.price,
            image_urls = saleProductEntity.image_url
        )
    }

    fun mapItemDtoToEntity(itemProductDto: ItemProductDto): ItemProductEntity {
        return ItemProductEntity(
            name = itemProductDto.name,
            description = itemProductDto.description,
            rating = itemProductDto.rating,
            number_of_reviews = itemProductDto.number_of_reviews,
            price = itemProductDto.price,
            colors = itemProductDto.colors,
            image_urls = itemProductDto.image_urls
        )
    }

    companion object {

        const val EMPTY_DESCRIPTION = ""
        const val EMPTY_RATING = 0

    }

}