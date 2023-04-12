package com.daengnyangffojjak.dailydaengnyang.security;

import com.daengnyangffojjak.dailydaengnyang.utils.JwtTokenUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter {

	private final JwtTokenUtil jwtTokenUtil;
	private final RedisTemplate redisTemplate;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain) throws ServletException, IOException {
		//권한 여부 결정
		//권한 부여 X case
		//1. Token X —> Request할 때 Token을 안넣고 호출하는 경우
		//2. 만료된 Token일 경우
		//3. 적절하지 않은 Token일 경우

		final String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		log.info("authorizationHeader:{}", authorizationHeader);


		//헤더 형식 확인
		if(authorizationHeader == null){
			SecurityContextHolder.getContext().setAuthentication(null);
			filterChain.doFilter(request, response);
			return;
		}

		String token = "";
		if (authorizationHeader.startsWith("Bearer ")){
			token = authorizationHeader.replace("Bearer ", "");
		} else{
			token = "";
			log.error("Authorization 헤더 형식이 틀립니다. : {}", authorizationHeader);
			filterChain.doFilter(request, response);
			return;
		}
//
//		String token;
//		try {
//			token = authorizationHeader.split(" ")[1];
//		} catch (Exception e) {
//			log.error("token 추출에 실패했습니다.");
//			filterChain.doFilter(request, response);
//			return;
//		}

		UserDetails userDetails = jwtTokenUtil.getUserDetails(token);
		//Redis에 해당 accessToken logout 여부 확인
		// 해당 accssToken은 블랙리스트에 있는지 확인
		String isLogout = (String) redisTemplate.opsForValue().get(token);
		if (ObjectUtils.isEmpty(isLogout)) {
			//문열어주기 >> 허용
			//Role 바인딩
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
					userDetails, null, userDetails.getAuthorities());
			authenticationToken.setDetails(
					new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		}

		filterChain.doFilter(request, response);
	}


}