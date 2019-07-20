package id.go.bps.jpa.mapping.config.security;

public class SecurityConstants {
	public static final String SECRET = "9E6F65E668E5B292F299B7F3BB823";
	public static final Long EXPIRATION_DATE = 864_000_000L; // 10 hari
	public static final String TOKEN_PREFIX = "Bearer";
	public static final String HEADERS_STRING = "Authorization";
}
