package com.example.shoppinglist.data

import com.example.shoppinglist.R
import com.example.shoppinglist.domain.ShopItem
import com.example.shoppinglist.domain.ShopListRepository
import java.lang.RuntimeException

// Конкретная реализация репозитория
// Data слой зависит от Domain слоя. Отвечает за работу с данными

// Impl - классы которые являются только реализацией какого-то интерфейса

// Object - является синглтоном, т.е где бы мы не обратились к данному объекту -
//это всегда будет один и тот же экземпляр. Это нужно, чтобы не получилось так,
//что на одном экране мы работаем с одним репозиторием, а на другом экране с другим

object ShopListRepositoryImpl: ShopListRepository {

    //храним все в переменной, в которой будем хранить коллекцию эллементов
    private val shopList = mutableListOf<ShopItem >()

    //переменная которая будет хранить id элементов
    private var autoIncrementId = 0

    init {
        for (i in 0 until 10){
            val item = ShopItem("Name $i",i,true)
            addShopItem(item)
        }
    }

    override fun addShopItem(shopItem: ShopItem) {
        //при редоктировании элемента надо сохранить его id
        //если id данного элемента неопределен, то в этом случае мы его установим
        if (shopItem.id == ShopItem.UNDEFINED_ID){
            shopItem.id = autoIncrementId++
        }
        shopList.add(shopItem)
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
    }

    override fun editShopItem(shopItem: ShopItem) {
        // нужно удалить старый объект и положить новый
        // но в кач-ве параметра прилетает уже новый объект с измененными полями
        // т.е мы не можем удалить его из коллекции - элемент найден не будет

        // поэтому надо сначала найти старый элемент по его id - удалить и добаыить новый
        val oldElement = getShopItem(shopItem.id)
        shopList.remove(oldElement)
        addShopItem(shopItem)
    }

    override fun getShopItem(shopItemId: Int): ShopItem {
        // ищем элемент по id и возвращаем его
        // если вдруг элемент не найден, приложение упадет с исключением
        return shopList.find {
            it.id == shopItemId
        } ?: throw RuntimeException("Element with id $shopItemId not found")
    }

    override fun getShopList(): List<ShopItem> {
        // лучше возвращать копию листа
        // если в getShopList() добавлять какие-то элементы или удалять их,
        // то на исходную коллекцию это никак не повлияет
        return shopList.toList()
    }

}