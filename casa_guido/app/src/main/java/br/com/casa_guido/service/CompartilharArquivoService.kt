package br.com.casa_guido.service

import android.content.Context
import android.content.Intent
import androidx.core.content.FileProvider
import java.io.File

class CompartilharArquivoService {
    fun compartilharPdf(context: Context, file: File) {
        val uri = FileProvider.getUriForFile(
            context,
            "${context.packageName}.provider",
            file
        )

        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "application/pdf"
            putExtra(Intent.EXTRA_STREAM, uri)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }

        val chooser = Intent.createChooser(intent, "Compartilhar PDF com...")
        context.startActivity(chooser)
    }
}