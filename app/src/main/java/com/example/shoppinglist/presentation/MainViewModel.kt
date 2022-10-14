package com.example.shoppinglist.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppinglist.data.ShopListRepositoryImpl
import com.example.shoppinglist.domain.*

class MainViewModel: ViewModel() {

    //для всех юзкейсов в качестве параметра конструткора надо передать репозитрой
    // в котором они работают
    private val repository = ShopListRepositoryImpl

    //понадобиться три элемента бизнес-логики
    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val  editShopItemUseCase = EditShopItemUseCase(repository)

    //отображение списка элементов
    // Взаимодействие activity и viewModel должно происходить через LiveData<List<ShopItem>>.
    // В этом случае будет успешно обрабатываться, например, переворот экрана.
    // Если переврнем экран то activity отпищется от объекта LiveData<List<ShopItem>>,
    // экран будет ничтожен, вызовется метод onDestroy(), activity пересоздастся и
    // снова подпишется на объект LiveData<List<ShopItem>>.

    val shopList = MutableLiveData<List<ShopItem>>()

    //получим список элементов из юзкейса и установим их в лайвдату
    fun getShopList(){
        val list = getShopListUseCase.getShopList()
        shopList.value = list   //value можно вызывать только из главного потока
                                //postValue можно вызывать из любого потока
    }

    fun deleteShopItem(shopItem: ShopItem) {
         deleteShopItemUseCase.deleteShopItem(shopItem)
        getShopList()
    }

    fun changeEnabledState(shopItem: ShopItem) {
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newItem)
        getShopList()
    }

}