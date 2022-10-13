package com.example.shoppinglist.domain

// Domain слой содержит всю бизнес-логику приложения - самый независимый слой
// работает только с интерфейсои репозитория. Он не должен знать как просиходит обработка данных

// Interactor (UseCase) - класс, который отвечает за одно действие бизнес-логики - зависят именно
//  от интерфейса репоизтория, а не от конкретной реализации

class AddShopItemUseCase(private val shopListRepository: ShopListRepository) {

    fun addShopItem(shopItem: ShopItem){
        shopListRepository.addShopItem(shopItem)
    }

}