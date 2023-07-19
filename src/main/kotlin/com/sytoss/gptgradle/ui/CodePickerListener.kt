package com.sytoss.gptgradle.ui

import com.intellij.openapi.fileChooser.FileChooserDescriptor
import com.intellij.openapi.ui.TextBrowseFolderListener
import com.intellij.openapi.vfs.VirtualFile
import com.sytoss.gptgradle.data.RequestFile
import com.sytoss.gptgradle.services.ConverterFileService

class CodePickerListener(descriptor: FileChooserDescriptor) : TextBrowseFolderListener(descriptor) {
    val selectedPaths: MutableList<VirtualFile> = mutableListOf()

    override fun onFileChosen(chosenFile: VirtualFile) {
        super.onFileChosen(chosenFile)
        selectedPaths.add(chosenFile)
    }

    fun getFiles(): List<RequestFile> {
        return selectedPaths.stream().map{ el -> ConverterFileService.toRequestFile(el) }.toList()
    }
}