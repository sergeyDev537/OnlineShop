package com.most4dev.onlineshop.data.impl

import android.content.Context
import com.most4dev.onlineshop.R
import com.most4dev.onlineshop.data.mappers.ProductMapper
import com.most4dev.onlineshop.data.network.api.ProductsApi
import com.most4dev.onlineshop.domain.entities.BrandEntity
import com.most4dev.onlineshop.domain.entities.ItemCategory
import com.most4dev.onlineshop.domain.entities.ProductEntity
import com.most4dev.onlineshop.domain.entities.SaleProductEntity
import com.most4dev.onlineshop.domain.repositories.ProductRepository
import java.util.*

class ProductRepositoryImpl(
    private val context: Context,
    private val productsApi: ProductsApi,
    private val productMapper: ProductMapper,
) : ProductRepository {

    private val listAllProducts = arrayListOf<ProductEntity>()

    override fun searchProduct(nameProduct: String): List<ProductEntity> {
        TODO("Not yet implemented")
    }

    override fun getCategoryProducts(): List<ItemCategory> {
        val listCategories = arrayListOf<ItemCategory>()
        listCategories.add(ItemCategory(context.getString(R.string.phones), R.drawable.ic_phones))
        listCategories.add(
            ItemCategory(
                context.getString(R.string.headphones),
                R.drawable.ic_headphones
            )
        )
        listCategories.add(ItemCategory(context.getString(R.string.games), R.drawable.ic_games))
        listCategories.add(ItemCategory(context.getString(R.string.cars), R.drawable.ic_cars))
        listCategories.add(
            ItemCategory(
                context.getString(R.string.furniture),
                R.drawable.ic_furniture
            )
        )
        listCategories.add(ItemCategory(context.getString(R.string.kids), R.drawable.ic_kids))
        return listCategories
    }

    override suspend fun getLatestProducts(): List<ProductEntity> {
        val listLatestProducts = productsApi.getLatestProducts().body()?.let {
            productMapper.mapListLatestProductDtoToEntity(it)
        } ?: listOf()
        listAllProducts.plus(listLatestProducts)
        return listLatestProducts
    }

    override suspend fun getFlashSaleProducts(): List<SaleProductEntity> {
        val listSaleProducts = productsApi.getSaleProducts().body()?.let {
            productMapper.mapListSalesProductDtoToEntity(it)
        } ?: listOf()
        addListSale(listSaleProducts)
        return listSaleProducts
    }

    private fun addListSale(listSaleProducts: List<SaleProductEntity>) {
        val listProducts = listSaleProducts.map {
            productMapper.mapSaleProductEntityToProductEntity(it)
        }
        listAllProducts.plus(listProducts)
    }

    override fun getBrands(): List<BrandEntity> {
        val listBrands = arrayListOf<BrandEntity>()
        listBrands.add(BrandEntity("https://www.bitrefill.com/content/cn/b_rgb%3AFFFFFF%2Cc_pad%2Ch_600%2Cw_800/v1553032023/adidas.jpg"))
        listBrands.add(BrandEntity("https://c.static-nike.com/a/images/w_1920,c_limit/bzl2wmsfh7kgdkufrrjq/image.jpg"))
        listBrands.add(BrandEntity("https://preview.thenewsmarket.com/Previews/RBOK/StillAssets/1920x1080/551064.png"))
        return listBrands
    }

    override fun getProduct(id: UUID): ProductEntity? {
        return listAllProducts.find { it.id == id }
    }
}