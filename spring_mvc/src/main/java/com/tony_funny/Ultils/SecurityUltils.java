package com.tony_funny.Ultils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.tony_funny.dto.MyUser;

public class SecurityUltils {
	
	public static MyUser getPrincipal() {
		MyUser myUser = (MyUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return myUser;
	}
	
	@SuppressWarnings("unchecked")
	public static List<String> getAuthorities(){
		List<String> result = new ArrayList<String>();
		List<GrantedAuthority> grantedAuthority = (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		for (GrantedAuthority i : grantedAuthority) {
			result.add(i.getAuthority());
		}
		return result;
	}

}
