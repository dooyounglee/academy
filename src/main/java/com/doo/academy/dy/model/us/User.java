package com.doo.academy.dy.model.us;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.doo.academy.dy.model.base.MasterEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true) // 부모 클래스에 필드를 포함하는 역할
@EqualsAndHashCode(callSuper = true) // 부모 클래스에 필드를 포함하는 역할
@Builder
@Entity
@Table(name = "DYUS001M")
public class User extends MasterEntity implements UserDetails {

	@Id
	private String email;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@Column(nullable = false)
	private String password;
	
	private String name;
	
	@ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    @CollectionTable(name="DYUS002D")
    private List<String> roles = new ArrayList<>();
	
	@Transient
	private String role;
	
	private int vid; // 유저 고유상수. Variables 중에 랜덤으로 고르기 위한 값
	
	private static final long serialVersionUID = 1L;


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}


	@Override
	public String getUsername() {
		return this.email;
	}


	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}
