package com.mincoms.security;

import com.mincoms.domain.UserInfo;

public interface AuthListener {
	public void onAuthSucceed(UserInfo user);
	 public void onAuthFail(String error);
}
