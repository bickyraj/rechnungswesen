package com.rechnungswesen.app.controllers.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ArbeitNowResponse(
	List<Job> data,
	Links links,
	Meta meta
) {
	public record Links(
			String first,
			String last,
			String prev,
			String next
	) {}

	public record Meta(
			@JsonProperty("current_page")
			int currentPage,

			@JsonProperty("current_page_url")
			String currentPageUrl,

			int from,

			String path,

			@JsonProperty("per_page")
			int perPage,

			int to,

			String terms,

			String info
	) {}

	public record Job(
			String slug,

			@JsonProperty("company_name")
			String companyName,

			String title,
			String description,
			boolean remote,
			String url,
			List<String> tags,

			@JsonProperty("job_types")
			List<String> jobTypes,

			String location,

			@JsonProperty("created_at")
			long createdAt
	) {
	}
}
