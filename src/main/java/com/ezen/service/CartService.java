package com.ezen.service;

import java.util.List;

import com.ezen.entity.Cart;

public interface CartService {
	void insertCart(Cart cart);
	
	void updateCart(Cart cart);
	
	void deleteCart(Cart cart);
	
	List<Cart> getCartList(String username);
}
