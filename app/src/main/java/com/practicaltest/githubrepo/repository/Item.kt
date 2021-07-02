package com.practicaltest.githubrepo.repository


import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("id", "node_id", "name", "full_name", "private", "owner", "html_url", "description", "fork", "url", "forks_url", "keys_url", "collaborators_url", "teams_url", "hooks_url", "issue_events_url", "events_url", "assignees_url", "branches_url", "tags_url", "blobs_url", "git_tags_url", "git_refs_url", "trees_url", "statuses_url", "languages_url", "stargazers_url", "contributors_url", "subscribers_url", "subscription_url", "commits_url", "git_commits_url", "comments_url", "issue_comment_url", "contents_url", "compare_url", "merges_url", "archive_url", "downloads_url", "issues_url", "pulls_url", "milestones_url", "notifications_url", "labels_url", "releases_url", "deployments_url", "created_at", "updated_at", "pushed_at", "git_url", "ssh_url", "clone_url", "svn_url", "homepage", "size", "stargazers_count", "watchers_count", "language", "has_issues", "has_projects", "has_downloads", "has_wiki", "has_pages", "forks_count", "mirror_url", "archived", "disabled", "open_issues_count", "license", "forks", "open_issues", "watchers", "default_branch", "score")
class Item {
    @JsonProperty("id")
    var id: Int? = null

    @JsonProperty("node_id")
    var nodeId: String? = null

    @JsonProperty("name")
    var name: String? = null

    @JsonProperty("full_name")
    var fullName: String? = null

    @JsonProperty("private")
    var _private: Boolean? = null

    @JsonProperty("owner")
    var owner: Owner? = null

    @JsonProperty("html_url")
    var htmlUrl: String? = null

    @JsonProperty("description")
    var description: String? = null

    @JsonProperty("fork")
    var fork: Boolean? = null

    @JsonProperty("url")
    var url: String? = null

    @JsonProperty("forks_url")
    var forksUrl: String? = null

    @JsonProperty("keys_url")
    var keysUrl: String? = null

    @JsonProperty("collaborators_url")
    var collaboratorsUrl: String? = null

    @JsonProperty("teams_url")
    var teamsUrl: String? = null

    @JsonProperty("hooks_url")
    var hooksUrl: String? = null

    @JsonProperty("issue_events_url")
    var issueEventsUrl: String? = null

    @JsonProperty("events_url")
    var eventsUrl: String? = null

    @JsonProperty("assignees_url")
    var assigneesUrl: String? = null

    @JsonProperty("branches_url")
    var branchesUrl: String? = null

    @JsonProperty("tags_url")
    var tagsUrl: String? = null

    @JsonProperty("blobs_url")
    var blobsUrl: String? = null

    @JsonProperty("git_tags_url")
    var gitTagsUrl: String? = null

    @JsonProperty("git_refs_url")
    var gitRefsUrl: String? = null

    @JsonProperty("trees_url")
    var treesUrl: String? = null

    @JsonProperty("statuses_url")
    var statusesUrl: String? = null

    @JsonProperty("languages_url")
    var languagesUrl: String? = null

    @JsonProperty("stargazers_url")
    var stargazersUrl: String? = null

    @JsonProperty("contributors_url")
    var contributorsUrl: String? = null

    @JsonProperty("subscribers_url")
    var subscribersUrl: String? = null

    @JsonProperty("subscription_url")
    var subscriptionUrl: String? = null

    @JsonProperty("commits_url")
    var commitsUrl: String? = null

    @JsonProperty("git_commits_url")
    var gitCommitsUrl: String? = null

    @JsonProperty("comments_url")
    var commentsUrl: String? = null

    @JsonProperty("issue_comment_url")
    var issueCommentUrl: String? = null

    @JsonProperty("contents_url")
    var contentsUrl: String? = null

    @JsonProperty("compare_url")
    var compareUrl: String? = null

    @JsonProperty("merges_url")
    var mergesUrl: String? = null

    @JsonProperty("archive_url")
    var archiveUrl: String? = null

    @JsonProperty("downloads_url")
    var downloadsUrl: String? = null

    @JsonProperty("issues_url")
    var issuesUrl: String? = null

    @JsonProperty("pulls_url")
    var pullsUrl: String? = null

    @JsonProperty("milestones_url")
    var milestonesUrl: String? = null

    @JsonProperty("notifications_url")
    var notificationsUrl: String? = null

    @JsonProperty("labels_url")
    var labelsUrl: String? = null

    @JsonProperty("releases_url")
    var releasesUrl: String? = null

    @JsonProperty("deployments_url")
    var deploymentsUrl: String? = null

    @JsonProperty("created_at")
    var createdAt: String? = null

    @JsonProperty("updated_at")
    var updatedAt: String? = null

    @JsonProperty("pushed_at")
    var pushedAt: String? = null

    @JsonProperty("git_url")
    var gitUrl: String? = null

    @JsonProperty("ssh_url")
    var sshUrl: String? = null

    @JsonProperty("clone_url")
    var cloneUrl: String? = null

    @JsonProperty("svn_url")
    var svnUrl: String? = null

    @JsonProperty("homepage")
    var homepage: Any? = null

    @JsonProperty("size")
    var size: Int? = null

    @JsonProperty("stargazers_count")
    var stargazersCount: Long? = null

    @JsonProperty("watchers_count")
    var watchersCount: Long? = null

    @JsonProperty("language")
    var language: String? = null

    @JsonProperty("has_issues")
    var hasIssues: Boolean? = null

    @JsonProperty("has_projects")
    var hasProjects: Boolean? = null

    @JsonProperty("has_downloads")
    var hasDownloads: Boolean? = null

    @JsonProperty("has_wiki")
    var hasWiki: Boolean? = null

    @JsonProperty("has_pages")
    var hasPages: Boolean? = null

    @JsonProperty("forks_count")
    var forksCount: Int? = null

    @JsonProperty("mirror_url")
    var mirrorUrl: Any? = null

    @JsonProperty("archived")
    var archived: Boolean? = null

    @JsonProperty("disabled")
    var disabled: Boolean? = null

    @JsonProperty("open_issues_count")
    var openIssuesCount: Int? = null

    @JsonProperty("license")
    var license: Any? = null

    @JsonProperty("forks")
    var forks: Long? = null

    @JsonProperty("open_issues")
    var openIssues: Int? = null

    @JsonProperty("watchers")
    var watchers: Long? = null

    @JsonProperty("default_branch")
    var defaultBranch: String? = null

    @JsonProperty("score")
    var score: Float? = null
}
