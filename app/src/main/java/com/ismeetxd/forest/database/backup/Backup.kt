package com.ismeetxd.forest.database.backup

import com.ismeetxd.forest.database.objects.CommandTemplate
import com.ismeetxd.forest.database.objects.DownloadedVideoInfo
import com.ismeetxd.forest.database.objects.OptionShortcut
import kotlinx.serialization.Serializable

@Serializable
data class Backup(
    val templates: List<CommandTemplate>? = null,
    val shortcuts: List<OptionShortcut>? = null,
    val downloadHistory: List<DownloadedVideoInfo>? = null,
)
