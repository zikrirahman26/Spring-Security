package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.request.ProductRequest;
import com.example.demo.dto.response.ProductResponse;
import com.example.demo.model.Member;
import com.example.demo.model.Product;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MemberRepository memberRepository;

    public ProductResponse addProduct(ProductRequest request, Long memberId){
        Optional<Member> optMember = memberRepository.findById(memberId);
        if (optMember != null) {
            Member member = optMember.get();
            Product product = new Product();
            product.setProductName(request.getProductName());
            product.setProductModel(request.getProductModel());

            List<Product> products = member.getProducts();
            products.add(product);

            productRepository.save(product);
            return responseProduct(product);
        }
        return null;
    }

    public ProductResponse getProductById(Long id){
        Optional<Product> optProduct = productRepository.findById(id);
        if (optProduct.isPresent()) {
            Product product = optProduct.get();
            return responseProduct(product);
        }
        return null;
    }

    public ProductResponse responseProduct(Product product){
        return ProductResponse.builder()
                    .productName(product.getProductName())
                    .productModel(product.getProductModel())
                    .build();
    }
}
