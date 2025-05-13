package br.com.casa_guido.service

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.print.PrintAttributes
import android.print.pdf.PrintedPdfDocument
import br.com.casa_guido.R
import br.com.casa_guido.screens.Paciente
import java.io.File
import java.io.FileOutputStream
import androidx.core.graphics.scale


class CriarPdfService {
    fun gerarFichaIdentificacaoPdf(context: Context, paciente: Paciente): File {
        val file = File(context.cacheDir, "ficha_identificacao.pdf")

        val printAttrs = PrintAttributes.Builder()
            .setMediaSize(PrintAttributes.MediaSize.ISO_A4)
            .setResolution(PrintAttributes.Resolution("id", "pdf", 300, 300))
            .setMinMargins(PrintAttributes.Margins.NO_MARGINS)
            .build()

        val pdf = PrintedPdfDocument(context, printAttrs)
        val page = pdf.startPage(0)
        val canvas = page.canvas

        val paint = Paint().apply {
            color = Color.BLACK
            textSize = 14f
            isAntiAlias = true
        }

        val boldPaint = Paint(paint).apply {
            typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
        }

        val x = 30f        // margem lateral
        var y = 30f        // margem superior
        val width = canvas.width.toFloat() - x * 2
        val gap = 30f      // espaço vertical entre linhas
        val lineGap = 20f  // altura da caixa de diagnóstico

        // Cabeçalho com logo e dados
        val logo = BitmapFactory.decodeResource(context.resources, R.drawable.logo)
        val logoHeight = 80f
        val logoWidth = logo.width * (logoHeight / logo.height)
        canvas.drawBitmap(
            logo.scale(logoWidth.toInt(), logoHeight.toInt(), false), x, y, null
        )

        val rightX = canvas.width - x
        val infoPaint = Paint(paint).apply {
            textSize = 10f
            textAlign = Paint.Align.RIGHT
        }

        val contatoInfo = listOf(
            "Rua Santo Antônio, 790 - Centro - Criciúma/SC",
            "CEP 88811-040 - (48) 3045.6211",
            "contato@guido.org.br - www.guido.org.br",
            "CNPJ: 12.927.890/0001-60"
        )

        var infoY = y + 10f
        contatoInfo.forEach {
            canvas.drawText(it, rightX, infoY, infoPaint)
            infoY += 14f
        }

        y += logoHeight + 20f // Move Y para baixo depois do cabeçalho

        // Título
        boldPaint.textSize = 18f
        canvas.drawText("FICHA DE IDENTIFICAÇÃO", x + width / 4, y, boldPaint)

        y += gap * 1.5f
        paint.textSize = 14f

        fun campo(label: String, chave: String, deslocamento: Float = 0f) {
            canvas.drawText("$label $chave", x + deslocamento, y, paint)
            y += gap
        }

        campo("PACIENTE:", paciente.pessoa.nome)
        campo("DATA DE NASCIMENTO:", paciente.pessoa.dataNascimento)
        campo("MÃE:", paciente.nomeMae)
        campo("PAI:", paciente.nomePai)
        campo("OUTRO:", paciente.nomeOutro)
        campo("TELEFONES:", paciente.pessoa.telefone)
        campo("ENDEREÇO:", paciente.pessoa.endereco.logradouro)

        // Bairro e cidade na mesma linha
        canvas.drawText("BAIRRO: ${paciente.pessoa.endereco.bairro}", x, y, paint)
        canvas.drawText("CIDADE: ${paciente.pessoa.endereco.localidade}", x + width / 2, y, paint)
        y += gap

        campo("PRÓXIMO:", "proximo")

        // Pré-cadastro e Atualização na mesma linha
        canvas.drawText("PRÉ-CADASTRO EM: ", x, y, paint)
        canvas.drawText("ATUALIZAÇÃO EM: ", x + width / 2, y, paint)
        y += gap

        // Diagnóstico com box
        val diagnostico = "DIAGNÓSTICO: LEOCEMIA"
        val boxTop = y - lineGap
        val boxBottom = y + lineGap
        canvas.drawRect(
            x,
            boxTop,
            x + width,
            boxBottom,
            Paint().apply { style = Paint.Style.STROKE })
        canvas.drawText(diagnostico, x + 10, y, paint)
        y += gap * 2

        // Título seção Profissionais
        boldPaint.textSize = 16f
        canvas.drawText("PROFISSIONAIS RESPONSÁVEIS", x, y, boldPaint)
        y += gap

        // Profissionais
        paint.textSize = 14f
        campo("MÉDICA(O):", paciente.profissionalResponsavel.nome)
        campo("ASSISTENTE SOCIAL:", "assistente")
        campo("PSICÓLOGA(O):", "psicologo")
        campo("OUTROS PROFISSIONAIS:", "outrosProfissionais")

        pdf.finishPage(page)

        val out = FileOutputStream(file)
        pdf.writeTo(out)
        out.close()
        pdf.close()

        return file
    }
}