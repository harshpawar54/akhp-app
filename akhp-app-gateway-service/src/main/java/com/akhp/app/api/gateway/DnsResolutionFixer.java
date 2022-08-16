package com.akhp.app.api.gateway;

import org.springframework.cloud.gateway.config.HttpClientCustomizer;
import org.springframework.stereotype.Component;

import io.netty.resolver.DefaultAddressResolverGroup;
import reactor.netty.http.client.HttpClient;

@Component
public class DnsResolutionFixer implements HttpClientCustomizer {
  @Override
  public HttpClient customize(HttpClient httpClient) {
    return httpClient.resolver(DefaultAddressResolverGroup.INSTANCE);
  }
}