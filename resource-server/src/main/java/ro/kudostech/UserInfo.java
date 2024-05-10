package ro.kudostech;

import lombok.Builder;

import java.util.List;

@Builder
public record UserInfo(String name, List<String> roles) {}
