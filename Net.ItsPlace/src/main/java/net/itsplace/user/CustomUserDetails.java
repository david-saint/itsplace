package net.itsplace.user;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.inject.Named;

import net.itsplace.domain.Place;
import net.itsplace.domain.User;

import org.springframework.context.annotation.Scope;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * @author Administrator
 *
 */
public class CustomUserDetails implements Serializable, UserDetails {
	private static final long serialVersionUID = 1L;

	private User user;
	private String username;
	
	
	private String password;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	private boolean enabled;
	private Set<String> roles;
	private Set<String> privileges;
	private Collection<? extends GrantedAuthority> authorities;
	
	private int fid; // 선택한 가맹
	private String placeListSelect;
	
	public CustomUserDetails(User user, String username, String password,
			boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {

		this.username = username;
		this.user = user;		
		this.password = password;
		this.enabled = enabled;
		this.accountNonExpired = accountNonExpired;
		this.accountNonLocked = accountNonLocked;
		this.credentialsNonExpired = credentialsNonExpired;
		this.authorities = authorities;

	}
	
	
	public String getPlaceListSelect() {
		return placeListSelect;
	}


	public void setPlaceList(List<Place> placeList) {
		this.placeListSelect ="<select id=\"places\">";
		for(int i=0;i<placeList.size();i++){
			if(fid == placeList.get(i).getFid()){
				this.placeListSelect += "<option value=\""+placeList.get(i).getFid()+"\" selected>"+placeList.get(i).getFname()+"</option>";
			}else{
				this.placeListSelect += "<option value=\""+placeList.get(i).getFid()+"\">"+placeList.get(i).getFname()+"</option>";
			}
		}
		this.placeListSelect +="</select>";
		 
	}


	public int getFid() {
		return fid;
	}


	public void setFid(int fid) {
		this.fid = fid;
	}

	public User getUser() {
		return user;
	}

	public void setUSer(User user) {
		this.user = user;
	}


	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	public Set<String> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Set<String> privileges) {
		this.privileges = privileges;
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		return (Collection<GrantedAuthority>) this.authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	


	@Override
	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setAuthorities(Collection<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
