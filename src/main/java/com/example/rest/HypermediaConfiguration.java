package com.example.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
import org.springframework.hateoas.mediatype.hal.HalConfiguration;
import org.springframework.hateoas.mediatype.hal.HalConfiguration.RenderSingleLinks;

@Configuration
@EnableHypermediaSupport(type = HypermediaType.COLLECTION_JSON)
public class HypermediaConfiguration {

	@Bean
	public HalConfiguration halConfiguration() {
		HalConfiguration halConfiguration = new HalConfiguration();
		halConfiguration.withRenderSingleLinks(RenderSingleLinks.AS_SINGLE);
		return halConfiguration;
	}
}
