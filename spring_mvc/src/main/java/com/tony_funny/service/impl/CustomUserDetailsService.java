
package com.tony_funny.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tony_funny.constant.SystemConstant;
import com.tony_funny.dto.MyUser;
import com.tony_funny.entity.RoleEntity;
import com.tony_funny.entity.UserEntity;
import com.tony_funny.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findOneByUserNameAndStatus(username, SystemConstant.ACTIVE_STATUS);
		if (userEntity == null) {
			throw new UsernameNotFoundException("User not found");
		}
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (RoleEntity i : userEntity.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(i.getCode()));
		}
		//Put thong tin vao security de luu tru khi User login vao System
		MyUser myUser = new MyUser(userEntity.getUserName(), userEntity.getPassword(), true, true, true,
				true, authorities);
		myUser.setFullName(userEntity.getFullName());
		return myUser;
	}

}
