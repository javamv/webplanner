package com.autowebinar.core.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by vmoskalenko on 16.01.2017.
 *
 */
public class UserAccount implements UserDetails {
    private String openIdIdentifier = null;
    private String googleId = null;
    private boolean enabled = true;
    private boolean accountNonLocked = true;
    private boolean accountNonExpired = true;
    private boolean credentialsNonExpired = true;
    private Set<GrantedAuthority> grantedAuthorities;

    public UserAccount(String openIdIdentifier, String googleId) {
        this.openIdIdentifier = openIdIdentifier;
        this.googleId = googleId;
        grantedAuthorities = new HashSet<GrantedAuthority>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    public String getPassword() {
        return openIdIdentifier;
    }

    public String getGoogleId() {
        return googleId;
    }

    public String getUsername() {
        return openIdIdentifier;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public boolean isEnabled() {
        return enabled;
    }


}
