package com.sytoss.gptgradle.ui

import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.content.ContentFactory

class PlagintToolWindowFactory : ToolWindowFactory, DumbAware {

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val form = PluginToolWindowContent()
        val content = ContentFactory.getInstance().createContent(form.contentPanel, "MyName", false)
        toolWindow.contentManager.addContent(content)
    }
}