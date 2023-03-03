package com.most4dev.onlineshop.data.impl

import android.app.SearchManager
import android.content.Context
import android.database.MatrixCursor
import android.provider.BaseColumns
import com.most4dev.onlineshop.R
import com.most4dev.onlineshop.data.mappers.ProductMapper
import com.most4dev.onlineshop.data.network.api.ApiService
import com.most4dev.onlineshop.domain.entities.*
import com.most4dev.onlineshop.domain.repositories.ProductRepository

class ProductRepositoryImpl(
    private val context: Context,
    private val productsApi: ApiService,
    private val productMapper: ProductMapper,
) : ProductRepository {

    override suspend fun searchProduct(nameProduct: String): MatrixCursor {
        val wordsEntity = productsApi.getSearchWords().body()?.let {
            productMapper.mapSearchWordsDtoToEntity(it)
        } ?: WordsEntity(listOf())
        val cursor =
            MatrixCursor(arrayOf(BaseColumns._ID, SearchManager.SUGGEST_COLUMN_TEXT_1))
        wordsEntity.words.forEachIndexed { index, suggestion ->
            if (suggestion.contains(nameProduct, true))
                cursor.addRow(arrayOf(index, suggestion))
        }
        return cursor
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
        return productsApi.getLatestProducts().body()?.let {
            productMapper.mapListLatestProductDtoToEntity(it)
        } ?: listOf()
    }

    override suspend fun getFlashSaleProducts(): List<SaleProductEntity> {
        return productsApi.getSaleProducts().body()?.let {
            productMapper.mapListSalesProductDtoToEntity(it)
        } ?: listOf()
    }

    override fun getBrands(): List<BrandEntity> {
        val listBrands = arrayListOf<BrandEntity>()
        listBrands.add(BrandEntity("https://cdn.britannica.com/94/193794-050-0FB7060D/Adidas-logo.jpg"))
        listBrands.add(BrandEntity("https://static.vecteezy.com/system/resources/previews/010/994/232/original/nike-logo-black-clothes-design-icon-abstract-football-illustration-with-white-background-free-vector.jpg"))
        listBrands.add(BrandEntity("https://preview.thenewsmarket.com/Previews/RBOK/StillAssets/1920x1080/551064.png"))
        return listBrands
    }

    override suspend fun getProduct(): ItemProductEntity? {
        val itemProduct = productsApi.getItemProduct().body()?.let {
            productMapper.mapItemDtoToEntity(it)
        }
        return itemProduct
    }
}