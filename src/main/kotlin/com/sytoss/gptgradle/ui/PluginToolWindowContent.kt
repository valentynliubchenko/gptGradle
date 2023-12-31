package com.sytoss.gptgradle.ui

import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory
import com.intellij.openapi.ui.DialogPanel
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.ui.TextFieldWithBrowseButton
import com.intellij.ui.dsl.builder.panel
import com.sytoss.gptgradle.services.GptService
import java.awt.GridLayout
import javax.swing.BorderFactory
import javax.swing.JPanel

class PluginToolWindowContent {
    val contentPanel: JPanel = JPanel()
    val listener: CodePickerListener = CodePickerListener(FileChooserDescriptorFactory.createMultipleFilesNoJarsDescriptor())

    init {
        contentPanel.layout = GridLayout()
        contentPanel.border = BorderFactory.createEmptyBorder()
        contentPanel.add(pluginForm())
    }

    private fun pluginForm(): DialogPanel {
        return panel {
            row {
                cell(filePicker())
            }
            row {
                button("Send") { _ -> showReport() }
            }
        }
    }

    private fun filePicker(): JPanel {
        val filePickerPanel = JPanel()

        val fileChooser = TextFieldWithBrowseButton()
        fileChooser.addBrowseFolderListener(listener)
        filePickerPanel.add(fileChooser)
        return filePickerPanel
    }

    private fun showReport() {
        Messages.showMessageDialog(
                null,
                GptService.generateReport(listener.getFiles()),
                "Reviews Result",
                Messages.getInformationIcon()
        )
    }
}