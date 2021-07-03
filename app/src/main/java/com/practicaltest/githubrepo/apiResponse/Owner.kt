package com.practicaltest.githubrepo.apiResponse



import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("login", "id", "node_id", "avatar_url", "gravatar_id", "url", "html_url", "followers_url", "following_url", "gists_url", "starred_url", "subscriptions_url", "organizations_url", "repos_url", "events_url", "received_events_url", "type", "site_admin")
class Owner {
    @JsonProperty("login")
    var login: String? = null

    @JsonProperty("id")
    var id: Int? = null

    @JsonProperty("node_id")
    var nodeId: String? = null

    @JsonProperty("avatar_url")
    var avatarUrl: String? = null

    @JsonProperty("gravatar_id")
    var gravatarId: String? = null

    @JsonProperty("url")
    var url: String? = null

    @JsonProperty("html_url")
    var htmlUrl: String? = null

    @JsonProperty("followers_url")
    var followersUrl: String? = null

    @JsonProperty("following_url")
    var followingUrl: String? = null

    @JsonProperty("gists_url")
    var gistsUrl: String? = null

    @JsonProperty("starred_url")
    var starredUrl: String? = null

    @JsonProperty("subscriptions_url")
    var subscriptionsUrl: String? = null

    @JsonProperty("organizations_url")
    var organizationsUrl: String? = null

    @JsonProperty("repos_url")
    var reposUrl: String? = null

    @JsonProperty("events_url")
    var eventsUrl: String? = null

    @JsonProperty("received_events_url")
    var receivedEventsUrl: String? = null

    @JsonProperty("type")
    var type: String? = null

    @JsonProperty("site_admin")
    var siteAdmin: Boolean? = null
}
