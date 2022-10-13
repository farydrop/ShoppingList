package com.example.shoppinglist.domain

// Repository должен уметь делать все что требуется нашим UseCase
// Этот репозиторий умеет работать с данными

interface ShopListRepository {

    fun addShopItem(shopItem: ShopItem)

    fun deleteShopItem(shopItem: ShopItem)

    fun editShopItem(shopItem: ShopItem)

    fun getShopItem(shopItemId: Int): ShopItem

    fun getShopList(): List<ShopItem>
}