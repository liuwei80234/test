package com.aisile.shop.service;




import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.aisile.pojo.TbSeller;
import com.aisile.sellergoods.service.SellerService;

public class UserDetailsServiceImpl implements UserDetailsService{

	private SellerService sellerService;
	
	public void setSellerService(SellerService sellerService) {
		this.sellerService = sellerService;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
 	   List<GrantedAuthority>  GrantedAuths =new ArrayList<GrantedAuthority>();
 	  GrantedAuths.add(new SimpleGrantedAuthority("ROLE_SELLER"));
 	 TbSeller sell = sellerService.findOne(username);
 	 if(sell!=null){
 		 //账号审核
 		 if(sell.getStatus().equals("1")){
 			return new User(username,sell.getPassword(),GrantedAuths);
 		 }else{
 			 return null;
 		 }
 		
 	 }else{
 		 return null;
 	 }
		
	}

}
