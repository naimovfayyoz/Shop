package uz.fayyoz.a1shop.domain

import uz.fayyoz.a1shop.data.repository.product.ProductsRepository

class GetByCategoryUseCase(
    private val repository: ProductsRepository,
) {

    //@TODO make it suspend
//     fun execute(
//        id: Int,
//    ) = repository.getByCategory(id)
}