package ro.kudostech;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {

  @GetMapping("/me")
  @PreAuthorize("hasRole('ROLE_read')")
  public ResponseEntity<UserInfo> getMeInfo() {
    JwtAuthenticationToken authentication =
        (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
    Jwt jwt = (Jwt) authentication.getPrincipal();
    UserInfo userInfo =
        UserInfo.builder()
            .name(jwt.getClaim("user"))
            .roles(jwt.getClaimAsStringList("authorities"))
            .build();

    return ResponseEntity.ok(userInfo);
  }
}
