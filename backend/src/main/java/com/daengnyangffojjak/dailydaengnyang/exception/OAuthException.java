package com.daengnyangffojjak.dailydaengnyang.exception;

import lombok.Getter;
import org.springframework.security.core.AuthenticationException;

@Getter
public class OAuthException extends AuthenticationException {

	public OAuthException(String msg) {
		super(msg);
	}
}
