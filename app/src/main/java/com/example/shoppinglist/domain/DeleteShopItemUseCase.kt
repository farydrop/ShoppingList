package com.example.shoppinglist.domain

// Domain слой содержит всю бизнес-логику приложения - самый независимый слой
// работает только с интерфейсои репозитория. Он не должен знать как просиходит обработка данных

// Interactor (UseCase) - класс, который отвечает за одно действие бизнес-логики - зависят именно
//  от интерфейса репоизтория, а не от конкретной реализации

class DeleteShopItemUseCase(private val shopListRepository: ShopListRepository) {

    fun deleteShopItem(shopItem: ShopItem){
        shopListRepository.deleteShopItem(shopItem)
    }

}