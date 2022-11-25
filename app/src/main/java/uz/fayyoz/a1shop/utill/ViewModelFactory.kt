package uz.fayyoz.a1shop.utill

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import uz.fayyoz.a1shop.di.RepositoryModule
import uz.fayyoz.a1shop.domain.*
import uz.fayyoz.a1shop.domain.login.*
import uz.fayyoz.a1shop.ui.products.category.vm.CategoryVM
import uz.fayyoz.a1shop.ui.login.LoginVM
import uz.fayyoz.a1shop.ui.products.allProducts.vm.AllProductsVM
import uz.fayyoz.a1shop.ui.products.favorites.vm.FavoritesVM
import uz.fayyoz.a1shop.ui.products.product_details.vm.ProductsDetailsVM
import uz.fayyoz.a1shop.ui.signUp.SignUpVM

class ViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val productRepo = RepositoryModule.bindProductsRepo()
        val loginRepo = RepositoryModule.bindLoginRepo()
        val signUpRepo = RepositoryModule.bindSignUpRepo()
        return if (modelClass.isAssignableFrom(CategoryVM::class.java)) {
            CategoryVM(productRepo) as T
        } else if (modelClass.isAssignableFrom(FavoritesVM::class.java)) {
            FavoritesVM(productRepo) as T
        } else if (modelClass.isAssignableFrom(AllProductsVM::class.java)) {
            AllProductsVM(productRepo) as T
        } else if (modelClass.isAssignableFrom(LoginVM::class.java)) {
            LoginVM(LoginUseCase(loginRepo),
                SaveAccessTokenUseCase(loginRepo),
                GetAccessTokenUseCase(loginRepo),
                ClearAccessTokenUseCase(loginRepo),
                GetUserDataUseCase(loginRepo),
                InsertUserDataUseCase(loginRepo),
                DeleteUserDataUseCase(loginRepo)) as T
        } else if (modelClass.isAssignableFrom(SignUpVM::class.java)) {
            SignUpVM(SignUpUseCase(signUpRepo)) as T
        }
        else if (modelClass.isAssignableFrom(ProductsDetailsVM::class.java)) {
            ProductsDetailsVM(productRepo) as T

        }else {
            throw IllegalArgumentException("Unknown ViewModel Class")
        }
    }

}

