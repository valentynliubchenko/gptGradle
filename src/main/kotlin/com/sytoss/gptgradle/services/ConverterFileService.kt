package com.sytoss.gptgradle.services

import com.intellij.openapi.vfs.VirtualFile
import com.sytoss.gptgradle.data.RequestFile
import java.nio.file.Files
import java.nio.file.Path

object ConverterFileService {
    fun toRequestFile(file:VirtualFile): RequestFile{
        return RequestFile(file.nameWithoutExtension, Files.readString(Path.of(file.path)))
    }
}