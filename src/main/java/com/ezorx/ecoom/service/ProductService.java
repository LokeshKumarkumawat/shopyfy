package com.ezorx.ecoom.service;

import com.ezorx.ecoom.configuration.JwtRequestFilter;
import com.ezorx.ecoom.dao.CartDao;
import com.ezorx.ecoom.dao.ProductDao;
import com.ezorx.ecoom.dao.UserDao;
import com.ezorx.ecoom.entity.Cart;
import com.ezorx.ecoom.entity.Product;
import com.ezorx.ecoom.entity.User;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductService {

    private final ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }


//    @Autowired
//    private ProductDao productDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private CartDao cartDao;

    public Product addNewProduct(Product product){
        return productDao.save(product);
    }

    public List<Product> getAllProducts(int pageNumber , String searchKey){
        Pageable pageable  = PageRequest.of(pageNumber,4);

//        if (searchKey.equals("")){
//            return (List<Product>) productDao.findAll(pageable);
//
//        }else {
//            return (List<Product>) productDao.findByProductNameContainingIgnoreCaseOrProductDescriptionContainingIgnoreCase(
//                    searchKey , searchKey , pageable
//            );
//
//
//
//        }

//        return (List<Product>) productDao.findAll(pageable);
        return (List<Product>) productDao.findAll();


    }




    public Product getProductDetailsById(Integer productId){
        return productDao.findById(productId).get();
    }

    public void deleteProductDetailes(Integer productId){
        productDao.deleteById(productId);
    }

    public List<Product> getProductDetails(boolean isSingleProductCheckout , Integer productId){
        if(isSingleProductCheckout && productId !=0){
            //we are goving to buy a single Product

            List<Product> list = new ArrayList<>();
            Product product = productDao.findById(productId).get();
            list.add(product);
            return list;

        }else {
            //we are goving to checkout entiire cart
            String username = JwtRequestFilter.CURRENT_USER;
            User user = userDao.findById(username).get();
            List<Cart> carts = cartDao.findByUser(user);
            return carts.stream().map(x -> x.getProduct()).collect(Collectors.toList());
        }
    }
}
