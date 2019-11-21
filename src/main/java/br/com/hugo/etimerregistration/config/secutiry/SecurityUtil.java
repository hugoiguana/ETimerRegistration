package br.com.hugo.etimerregistration.config.secutiry;

import javax.servlet.http.HttpServletRequest;

public class SecurityUtil {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_TOKEN_HEADER = "Bearer ";
    public static final int START_TOKEN_VALUE_BEARER = 7;

    public static String getTokenBearerFromHeaderRequest(HttpServletRequest request) {
        String headerToken = request.getHeader(SecurityUtil.AUTHORIZATION_HEADER);
        if (headerToken != null && headerToken.startsWith(SecurityUtil.BEARER_TOKEN_HEADER)) {
            return getValueTokenBearer(headerToken);
        }
        return null;
    }

    public static String getValueTokenBearer(String token) {
        return token.substring(START_TOKEN_VALUE_BEARER);
    }
}
